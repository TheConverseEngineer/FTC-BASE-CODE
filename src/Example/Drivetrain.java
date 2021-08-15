
package Example;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.lang.Math.*;

/** *********************** HOW TO USE THIS FILE *************************
**/

// @TeleOp(name = "MASTER BASE CODE", group = "default")
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
}
  
    
    
    
    
    
    
    
    
    
