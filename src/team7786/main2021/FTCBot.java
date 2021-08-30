package src.team7786.main2021;

import src.team7786.main2021.MecanumRobot;

import static src.team7786.main2021.ROBOT_DATA.*;
import src.team7786.main2021.path.Trajectory;
import src.team7786.main2021.path.PathState;
import src.team7786.main2021.geometry.Point;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math.*;

public class FTCBot extends MecanumRobot
{
  private Trajectory traj;
  private int currentState;
  private bool complete;
  
  public FTCBot(HardwareMap hwMap) {
    super(hwMap);
  }
  
  
  /**  Make the robot follow a trajectory
   * @param traj    the trajectory that the robot should follow
   */
  public void startTrajectory(Trajectory _traj) {
    traj = _traj;
    currentState = 0;
    complete = false;
    while (!complete) {
      PathState state = traj.getRobotTarget(new Point(m_X, m_Y), currentState);
      currentState = state.stage;
      complete = state.complete;
      driveTowardsPoint(state.point.x, state.point.y, m_THETA, 1d);
      updateOdometry();
    }
    stop();
  }
  
  
  public int updateTrajectory() {
    PathState state = traj.getRobotTarget(new Point(m_X, m_Y), currentState);
    currentState = state.stage;
    complete = state.complete;
    driveTowardsPoint(state.point.x, state.point.y, m_THETA, 1d);
    updateOdometry():
    if (complete) {
    
    }
  }
  
  private


  /** Returns the position and rotation of the robot in inches and degrees
   * @return   a double list with {x, y, rotation}
   */
  public double[] getImperialPosition() {
    return new double[]{m_X / COUNTS_PER_INCH, m_Y / COUNTS_PER_INCH, Math.toDegrees(m_THETA)};
  }

}
