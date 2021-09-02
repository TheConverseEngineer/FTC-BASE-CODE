package org.firstinspires.ftc.teamcode.team7786.core;

import static org.firstinspires.ftc.teamcode.team7786.core.ROBOT_DATA.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumRobot extends Robot
{

  // Stores the robot pose found in the last update.
  private double[] lastPose;


  public MecanumRobot(HardwareMap hwMap) {
    super(hwMap);
    lastPose = new double[]{0d, 0d, 0d};
  }

  /** Read the current encoder values and update the last values.
   * @return a double array {change in Left, Change in Right, Change in Back}
   */
  private double[] getNewReadings() {
    double[] readings = {getLEncoder(), getREncoder(), getBEncoder()};
    double[] deltaReadings = {readings[0] - lastPose[0], readings[1] - lastPose[1], readings[2] - lastPose[2]};
    lastPose = new double[]{readings[0], readings[1], readings[2]};
    return deltaReadings;


  }

  /** Calculates the heading and location of the robot using only the odometry encoders
   * Variable breakdown for readability:
   *   dPose                   An array consisting of {dL, dR, dB}
   *   dL                      The number of left encoder ticks between now and last update
   *   dR                      The number of right encoder ticks between now and last update
   *   dB                      The number of back encoder ticks between now and last update
   *   dM                      The distance traveled forward (not strafe) between now and last update
   *   dTheta                  The change in rotation between now and the last update
   *   m_X, m_Y, m_Theta       The x, y, and heading of the robot. Located in the static class ROBOT_DATA
   */
  public void updateOdometry() {
    double[] dPose = getNewReadings();
    double dL = dPose[0];
    double dR = dPose[1];
    double dB = dPose[2];

    double dM = (dR+dL) / 2;
    double dTheta = (dR-dL) / TRACK_WIDTH;

    m_X = m_X + ((dM / dTheta) * Math.sin(dTheta) / Math.cos(dTheta)) * Math.cos(m_THETA + dTheta/2) + dB * Math.cos(m_THETA - Math.PI/2 + dTheta/2);
    m_Y = m_Y + ((dM / dTheta) * Math.sin(dTheta) / Math.cos(dTheta)) * Math.sin(m_THETA + dTheta/2) + dB * Math.sin(m_THETA - Math.PI/2 + dTheta/2);
    m_THETA = m_THETA + dTheta;

    reduceRotation();
  }


  /** Drives the robot in a specified direction
   * @param theta       the direction the robot should travel
   * @param speed       the speed at which the robot should travel
   * @param turnSpeed   the speed at which the robot should turn whilst moving
   */
  public void driveWithHeading(double theta, double speed, double turnSpeed) {
		turnSpeed = clipRange(turnSpeed);
		speed = clipRange(speed);

		// Calculate wheel speeds for lateral movement only (no rotation)
		double[] wheelSpeeds = new double[4];
		wheelSpeeds[0] = Math.sin(theta + Math.PI / 4);
		wheelSpeeds[1] = Math.sin(theta - Math.PI / 4);
		wheelSpeeds[2] = Math.sin(theta - Math.PI / 4);
		wheelSpeeds[3] = Math.sin(theta + Math.PI / 4);

		// Normalize data
		normalize(wheelSpeeds, speed);

		// Factor in turning
		wheelSpeeds[0] += turnSpeed;
		wheelSpeeds[1] -= turnSpeed;
		wheelSpeeds[2] += turnSpeed;
		wheelSpeeds[3] -= turnSpeed;

		// Re-Normalize data
		normalize(wheelSpeeds, speed);

		// Apply Movement
		tankDrive(wheelSpeeds);
  }


  /** Drives the robot using parameters that can be inputed from a controller
   * @param x                  the x value of the drive joystick
   * @param y                  the y value of the drive joystick
   * @param rotX               the x value of the turn joystick
   * @param fieldCentric       setting this to true will drive the robot relative to the field (reccomended)
   */
  public void driveWithController(double x, double y, double rotX, boolean fieldCentric){
    double squareX = clipRange(squareInput(x));
    double squareY = clipRange(squareInput(y));
    double squareRotX = clipRange(squareInput(rotX));

    double heading = fieldCentric ? Math.atan2(squareX, squareY) - m_THETA : Math.atan2(squareX, squareY);
    double speed = Math.hypot(squareX, squareY);
    driveWithHeading(heading, speed, squareRotX);
	}


	/** Drives the robot towards a specified point on the field
	 * @param x       the x value of the point
	 * @param y       the y value of the point
	 * @param fRot    the rotation that the robot should end up at
	 * @param speed   the speed at which the robot should move
	 */
	public void driveTowardsPoint(double x, double y, double fRot, double speed) {
		double deltaX = x - m_X;
		double deltaY = y - m_Y;
		double deltaRot = fRot - m_THETA;

		if (Math.abs(deltaRot) > Math.PI) {
			deltaRot = 2*Math.PI - Math.abs(deltaRot) * -(deltaRot / Math.abs(deltaRot));
		}

		double turnSpeed = deltaRot / Math.PI * DRIVE_TO_POINT_TURN_TUNE;
		double heading = Math.atan2(deltaX, deltaY);
		driveWithHeading(heading, speed, turnSpeed);
	}


	/** Overload of previous to drives the robot towards a specified point on the field and specify turn speed
	 * @param x       the x value of the point
	 * @param y       the y value of the point
	 * @param fRot    the rotation that the robot should end up at
	 * @param speed   the speed at which the robot should move
	 */
	public void driveTowardsPoint(double x, double y, double fRot, double speed, double turnSpeed) {
		double deltaX = x - m_X;
		double deltaY = y - m_Y;
		double deltaRot = fRot - m_THETA;
		double heading = Math.atan2(deltaX, deltaY);
		driveWithHeading(heading, speed, turnSpeed);
	}

  public void resetEncoders(double nL, double nR, double nB) {
		m_LEFT_ENCODER_OFFSET = (int) (m_LEFT_ENCODER_OFFSET - getLEncoder() + nL);
		m_RIGHT_ENCODER_OFFSET = (int) (m_RIGHT_ENCODER_OFFSET - getREncoder() + nR);
		m_BACK_ENCODER_OFFSET = (int) (m_BACK_ENCODER_OFFSET - getBEncoder() + nB);
		resetLastEncoders(nL, nR, nB);

	}

	public void resetEncodersImperial(double nL, double nR, double nB) {
		m_LEFT_ENCODER_OFFSET = (int) (m_LEFT_ENCODER_OFFSET - getLEncoder() + (nL * COUNTS_PER_INCH));
		m_RIGHT_ENCODER_OFFSET = (int) (m_RIGHT_ENCODER_OFFSET - getREncoder() + (nR * COUNTS_PER_INCH));
		m_BACK_ENCODER_OFFSET = (int) (m_BACK_ENCODER_OFFSET - getBEncoder() + (nB * COUNTS_PER_INCH));
		resetLastEncoders(nL * COUNTS_PER_INCH, nR * COUNTS_PER_INCH, nB * COUNTS_PER_INCH);
	}

	private void resetLastEncoders(double nL, double nR, double nB) {
		lastPose = new double[]{nL, nR, nB};
	}

}
