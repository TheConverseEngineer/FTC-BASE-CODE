package team7786.main2021;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math.*;
import team7786.main2021.ROBOT_DATA.*;

// This code serves as a base class for a 4 motor drivetrain
public class Robot
{

  // Declare Drive Motors and Array
	private DcMotor leftFrontDrive, rightRearDrive, rightFrontDrive, leftRearDrive;
	private DcMotor[] drivers;

  /** Constructor for base class robot
   * @param hwMap     the hardware map
   */
  public Robot(HardwareMap hwMap) {
    // Initialize the motor hardware variables
		leftFrontDrive = hwMap.get(DcMotor.class, LEFT_FRONT_NAME);
		leftRearDrive = hwMap.get(DcMotor.class, RIGHT_FRONT_NAME);
		rightFrontDrive = hwMap.get(DcMotor.class, LEFT_REAR_NAME);
		rightRearDrive = hwMap.get(DcMotor.class, RIGHT_REAR_NAME);

		// Initialize the motor array

		//this right here is causing just so many problem
		drivers = [leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive];

    // set motor orientations (positive power should move wheel clockwise)
		for (i = 0; i < 4; i++) {
			if (MOTOR_INVERTED[i] == true) {
				drivers[i].setDirection(DcMotor.Direction.REVERSE);
			}
		}
	}


  /** Provides power to all motors.
   * @param powers     Motor order is LF, RF, LR, RR.
   */
  public void tankDrive (double[] powers) {
    for (int i = 0; i < 4; i++) {
      drivers[i].setPower(powers[i]);
    }
  }


  /** Stops all motors */
  public void stop () {
    tankDrive(0d, 0d, 0d, 0d);
  }


  /** Small utility function that clips a double between two values
   * @param x         the value to check
   * @param min       the minumum value x can be
   * @param max       the maximum value x can be
   * @return          the clipped x
   */
	public double clipRange(double x, double min, double max) {
		return (x < min ? min : x) > max ? max : x;
	}


	/** Overload of clipRange (previous) for increased conveniance. Assumes min to be -1 and max to be 1.*/
	public double clipRange(double x) {
		return clipRange(x, -1d, 1d);
}
  /** Square magnitude of number while keeping the sign.
	 * @param input      the number to square
	 * @return           the number squared*/
	public double squareInput(double input) {
			return input * Math.abs(input);
	}

	public double reduceRotation() {
		while (m_THETA >= Math.PI * 2 || m_THETA < 0) {
			m_THETA = m_THETA - Math.PI * (m_THETA > 0 ? -2 : 2);
		}
	}


	/** Slightly less small utility function to normalize a double array
   * This function takes an array and scales it down until the largest value is equal to the magnitude
   * @param x          the array of doubles to scale
   * @param magnitude  the largest value that an item in x can be
   */
	public void normalize(double[] x, double magnitude) {
		// The variable maxMagnitude signifies the largest absolute value in the array
		double maxMagnitude = Math.abs(x[0]);
		for (int i = 1; i < x.length; i++) {
				double temp = Math.abs(x[i]);
				if (maxMagnitude < temp) {
						maxMagnitude = temp;
				}
		}

    maxMagnitude = (maxMagnitude == 0) ? 1d : maxMagnitude;

		// Normalize each unit in the array with the maxMagnitude
		for (int i = 0; i < x.length; i++) {
				x[i] = (x[i] / maxMagnitude) * magnitude;
		}
	}


  /** Get the encoder counts for the left side encoder (y1)
   * @return    encoder counts
   */
  public int getLEncoder() {
    return leftFrontDrive.getCurrentPosition() + m_LEFT_ENCODER_OFFSET;
  }


  /** Get the encoder counts for the right side encoder (y2)
   * @return    encoder counts
   */
  public int getREncoder() {
    return rightFrontDrive.getCurrentPosition() + m_RIGHT_ENCODER_OFFSET;

  }

  /** Get the encoder counts for the back encoder (x)
   * @return    encoder counts
   */
  public int getBEncoder() {
    return leftRearDrive.getCurrentPosition() + m_BACK_ENCODER_OFFSET;
  }

  /** Get the encoder counts for the all three encoders
   * @return    encoder counts (Left, Right, Back)
   */
  public int[] getAllEncoder() {
    return new int[]{getLEncoder(), getREncoder(), getBEncoder()};
  }


  /** Resets all encoder values to 0
   * Utilizes an offset variable to prevent freezing encoders mid-program
   * Offsets are stored in the ROBOT_DATA static class, and are saved as long as the app remains open
   */
  public void resetEncoders() {
    m_LEFT_ENCODER_OFFSET = -getLEncoder();
    m_RIGHT_ENCODER_OFFSET = -getREncoder();
    m_BACK_ENCODER_OFFSET = -getBEncoder();
  }

}
