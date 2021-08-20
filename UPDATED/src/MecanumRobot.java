package UPDATED.src;

import UPDATED.src.Robot;

import UPDATED.src.ROBOT_DATA.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math.*;

public class MecanumRobot extends Robot 
{
  
  // Stores the robot pose found in the last update.
  private double[] lastPose;
  
  
  public MecanumRobot(HardwareMap hwMap) {
    super(hwMap);
    lastPose = {0d, 0d, 0d};
  }
  
  /* Read the current encoder values and update the last values.
   * @return a double array {change in Left, Change in Right, Change in Back}
   */
  private double[] getNewReadings() {
    double[] readings = {getLEncoder(), getREncoder(), getBEncoder()};
    double[] deltaReadings = {readings[0] - lastPose[0], readings[1] - lastPose[1], readings[2] - lastPose[2]};
    lastPose = {readings[0], readings[1], readings[2]};
    return deltaReadings
    
  
  }
  
  /* Calculates the heading and location of the robot using only the odometry encoders
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
    dPose = getNewReadings();
    dL = dPose[0];
    dR = dPOse[1];
    dB = dPose[2];
    
    double dM = (dR+dL) / 2;
    double dTheta = (dR-dL) / TRACK_WIDTH;
    
    m_X = m_X + ((dM / dTheta) * Math.sin(dTheta) / Math.cos(dTheta)) * Math.cos(m_THETA + dTheta/2) + dB * Math.cos(m_Theta - Math.PI/2 + dTheta/2);
    m_Y = m_Y + ((dM / dTheta) * Math.sin(dTheta) / Math.cos(dTheta)) * Math.sin(m_THETA + dTheta/2) + dB * Math.sin(m_Theta - Math.PI/2 + dTheta/2);
    m_THETA = m_THETA + dTheta;
  }
  
}
