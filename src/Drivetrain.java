package src;

import Example.Vector2D
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math.*;

// This code serves as a base class for a holonomic, mecanum-style drivetrain
public class Drivetrain
{
	// Declare Drive Motors and Array
	private DcMotor leftFrontDrive, rightRearDrive, rightFrontDrive, leftRearDrive;
	private DcMotor[] drivers;
	
	// Store ticks-per-inch
	int tpi;
  
  /* Constructor for the Drivetrain class.
   * @param hwMap       a hardware map 
	 * @param inversion   array of 4 booleans
	 * @param tpi         the number of motor ticks in one inch of travel
   * Motor order for the second parameter is LF, RF, LR, RR. TRUE = FORWARD and FALSE = REVERSE */
  public Drivetrain(HardwareMap hwMap, bool[] inversion, int _tpi) {
    
    // Initialize the motor hardware variables
		leftFrontDrive  = hwMap.get(DcMotor.class, "leftFrontDrive");
		leftRearDrive  = hwMap.get(DcMotor.class, "leftRearDrive");
		rightFrontDrive  = hwMap.get(DcMotor.class, "rightFrontDrive");
		rightRearDrive  = hwMap.get(DcMotor.class, "rightRearDrive");
    
		// Set the TPI
		tpi = _tpi;
		
    // Initialize the motor array
		drivers = [leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive];
    
    // set motor orientations (positive power should move wheel clockwise)
		for (i = 0; i < 4; i++) {
			if (inversion[i] == false) {
				drivers[i].setDirection(DcMotor.Direction.REVERSE);
			} 
		}
  }
  
  /* Provides power to all motors.
   * Motor order is LF, RF, LR, RR. */
  public void tankDrive (double[] powers) {
   for (int i = 0; i < 4; i++) {
     drivers[i].setPower(powers[i]);
   }
  }
  
  /* Overload of the previous method for convenience */
  public void tankDrive (double p1, double p2, double p3, double p4) {
    double[] powers = {p1, p2, p3, p4};
    for (int i = 0; i < 4; i++) {
      drivers[i].setPower(powers[i]);
    }
  }
  
  /* Stops all motors */
  public void stop () {
   tankDrive(0d, 0d, 0d, 0d); 
  }
	
  /* Small utility function that clips a double between two values */
	private double clipRange(double x, double min, double max) {
		return (x < min ? min : x) > max ? max : x;
		
	/* Overload of previous for increased conveniance */
	private double clipRange(double x) {
		return clipRange(x, -1d, 1d);
	
	/* Slightly less small utility function to normalize a double array*/
	protected void normalize(double[] wheelSpeeds, double magnitude) {
		// The variable maxMagnitude signifies the largest absolute value in the array
		double maxMagnitude = Math.abs(wheelSpeeds[0]);
		for (int i = 1; i < wheelSpeeds.length; i++) {
				double temp = Math.abs(wheelSpeeds[i]);
				if (maxMagnitude < temp) {
						maxMagnitude = temp;
				}
		}

		// Normalize each unit in the array with the maxMagnitude
		for (int i = 0; i < wheelSpeeds.length; i++) {
				wheelSpeeds[i] = (wheelSpeeds[i] / maxMagnitude) * magnitude;
		}
	}	
		
	/* This program computes the neccessary movement vectors to move the robot in a field-centric manner
	 *
	 * @param x  the horizontal speed of the robot, derived from input
	 * @param y the vertical speed of the robot, derived from input
	 * @param turnSpeed    the turn speed of the robot, derived from input
	 * @param gyroAngle    the heading of the robot, derived from the gyro 
	 */	
  public void driveFieldCentric(double x, double y, double turnSpeed, double gyroAngle) {
		// Ensure that x, y, and turnSpeed are between -1 and 1
		x = clipRange(x);
		y = clipRange(y);
		turnSpeed = clipRange(turnSpeed);
		
		// Create a new local Vector2D and rotate it so that it is field-centric
		Vector2d input = new Vector2d(x, y);
		input = input.rotateBy(-gyroAngle);

		// Get the angle of the resultant vector
		double theta = input.angle();

		// Calculate wheel speeds for lateral movement only (no rotation)
		double[] wheelSpeeds = new double[4];
		wheelSpeeds[0] = Math.sin(theta + Math.PI / 4);
		wheelSpeeds[1] = Math.sin(theta - Math.PI / 4);
		wheelSpeeds[2] = Math.sin(theta - Math.PI / 4);
		wheelSpeeds[3] = Math.sin(theta + Math.PI / 4);

		// Normalize data
		normalize(wheelSpeeds, input.magnitude());

		// Factor in turning
		wheelSpeeds[0] += turnSpeed;
		wheelSpeeds[1] -= turnSpeed;
		wheelSpeeds[2] += turnSpeed;
		wheelSpeeds[3] -= turnSpeed;

		// Re-Normalize data
		normalize(wheelSpeeds);
	  
		// Apply Movement
		tankDrive(wheelSpeeds);
  }
		
	/* Square magnitude of number while keeping the sign. */
	protected double squareInput(double input) {
			return input * Math.abs(input);
	}

	/**
	 * This program computes the neccessary movement vectors to move the robot in a field-centric manner
	 *
	 * @param xSpeed         the horizontal speed of the robot, derived from input
	 * @param ySpeed         the vertical speed of the robot, derived from input
	 * @param turnSpeed      the turn speed of the robot, derived from input
	 * @param gyroAngle      the heading of the robot, derived from the gyro
	 * @param squareInputs   Square the value of the input to allow for finer control
	 */
	public void driveFieldCentric(double xSpeed, double ySpeed, double turnSpeed, double gyroAngle, boolean squareInputs) {
			xSpeed = squareInputs ? clipRange(squareInput(xSpeed)) : clipRange(xSpeed);
			ySpeed = squareInputs ? clipRange(squareInput(ySpeed)) : clipRange(ySpeed);
			turnSpeed = squareInputs ? clipRange(squareInput(turnSpeed)) : clipRange(turnSpeed);

			driveFieldCentric(xSpeed, ySpeed, turnSpeed, gyroAngle);
	}
		
	 /**
     * Drives the robot from the perspective of the robot itself rather than that of the driver.
     *
     * @param x            the horizontal speed of the robot, derived from input
     * @param y            the vertical speed of the robot, derived from input
     * @param turnSpeed    the turn speed of the robot, derived from input
     */
    public void driveRobotCentric(double x, double y, double turnSpeed) {
      driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, 0.0);
    }
		
		/* Determines the required encoder ticks to complete autonomous movement
		 * @param rho      the distance in ticks
		 * @param theta    the angle of travel (in radians) 
		 */
		private double[] getHeaderInfo(double rho, double theta) {	
			double leftEncoderTicks = Math.sin(theta + Math.PI / 4) * rho;
			double rightEncoderTicks = Math.sin(theta - Math.PI / 4) * rho;
			return new double[]{leftEncoderTicks, rightEncoderTicks};
		}
		
		private double[] getFieldCentricImperialHeaderInfo(double deltaX, double deltaY. double gyroAngle) {
			// Convert distance to ticks
			double x = deltaX * tpi;
			double y = deltaY * tpi;
			
			// Create and rotate vector
			Vector2D path = new Vector2D(x, y)
			path = path.rotateBy(-gyroAngle);
			
			// Calculate and returnencoder tick values
			return getHeaderInfo(path.magnitude, path.angle);
		}
		
		private void driveAutonmousInEncoderTicks(double deltaX, double deltaY, double maxSpeed, double accelTime, double gyroAngle) {
			// Calculate the required motor speeds
			double[] tickList = getFieldCentricImperialHeaderInfo(deltaX, deltaY);
			double leftTick = tickList[0];
			double rightTick = tickList[1];
			
		}
	
	  
}
