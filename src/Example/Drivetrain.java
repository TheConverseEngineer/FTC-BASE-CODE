package Example;

import Example.Vector2D

public class Drivetrain
{
	// Declare Drive Motors and Array
	private DcMotor leftFrontDrive, rightRearDrive, rightFrontDrive, leftRearDrive;
	private DcMotor[] drivers;
  
  /* Constructor for the Drivetrain class.
   * Pass a hardware map as the first parameter and an array of 4 booleans for the second parameter
   * Motor order for the second parameter is LF, RF, LR, RR. TRUE = FORWARD and FALSE = REVERSE */
  public Drivetrain(HardwareMap hwMap, bool[] inversion) {
    
    // Initialize the motor hardware variables
		leftFrontDrive  = hwMap.get(DcMotor.class, "leftFrontDrive");
		leftRearDrive  = hwMap.get(DcMotor.class, "leftRearDrive");
		rightFrontDrive  = hwMap.get(DcMotor.class, "rightFrontDrive");
		rightRearDrive  = hwMap.get(DcMotor.class, "rightRearDrive");
    
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
	
	
  public void driveFieldCentric(double x, double y, double turnSpeed, double gyroAngle) {
        strafeSpeed = clipRange(strafeSpeed);
        forwardSpeed = clipRange(forwardSpeed);
        turnSpeed = clipRange(turnSpeed);

        Vector2d input = new Vector2d(strafeSpeed, forwardSpeed);
        input = input.rotateBy(-gyroAngle);

        double theta = input.angle();

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[MotorType.kFrontLeft.value] = Math.sin(theta + Math.PI / 4);
        wheelSpeeds[MotorType.kFrontRight.value] = Math.sin(theta - Math.PI / 4);
        wheelSpeeds[MotorType.kBackLeft.value] = Math.sin(theta - Math.PI / 4);
        wheelSpeeds[MotorType.kBackRight.value] = Math.sin(theta + Math.PI / 4);

        normalize(wheelSpeeds, input.magnitude());

        wheelSpeeds[MotorType.kFrontLeft.value] += turnSpeed;
        wheelSpeeds[MotorType.kFrontRight.value] -= turnSpeed;
        wheelSpeeds[MotorType.kBackLeft.value] += turnSpeed;
        wheelSpeeds[MotorType.kBackRight.value] -= turnSpeed;

        normalize(wheelSpeeds);

        motors[MotorType.kFrontLeft.value]
                .set(wheelSpeeds[MotorType.kFrontLeft.value] * maxOutput);
        motors[MotorType.kFrontRight.value]
                .set(wheelSpeeds[MotorType.kFrontRight.value] * rightSideMultiplier * maxOutput);
        motors[MotorType.kBackLeft.value]
                .set(wheelSpeeds[MotorType.kBackLeft.value] * maxOutput);
        motors[MotorType.kBackRight.value]
                .set(wheelSpeeds[MotorType.kBackRight.value] * rightSideMultiplier * maxOutput);
    }
	
	  
}
