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
	
  /* Computes Motor Power based of a vector with rotational axis
	 * Motor order is LF, RF, LR, RR. 
	 * Setting theta to 0 moves the robot forward */
  private double[] formFromVector(double _theta, double rho, double rotation) {
    double[] motorPowers = {0d, 0d, 0d, 0d};
		double theta = _theta - (Math.PI / 4)
		motorPowers[0] = r * Math.cos(theta) + rotation;
		motorPowers[1] = r * Math.sin(theta) -  rotation;
		motorPowers[2] = r * Math.sin(theta) +  rotation;
		motorPowers[3] = r * Math.cos(theta) -  rotation;
		
		return motorPowers;
  }
	
	  
}
