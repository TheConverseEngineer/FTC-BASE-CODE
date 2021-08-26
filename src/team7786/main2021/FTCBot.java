package team7786.main2021;

import UPDATED.src.MecanumRobot;

import UPDATED.src.ROBOT_DATA.*;
import UPDATED.src.path.Trajectory;
import UPDATED.src.path.PathState;
import UPDATED.src.geometry.Point;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math.*;

public class FTCBot extends MecanumRobot
{
  private ROBOT_DATA data = new ROBOT_DATA;
  public FTCBot(HardwareMap hwMap) {
    super(hwMap);
  }

  /**  Make the robot follow a trajectory
   * @param traj    the trajectory that the robot should follow
   */
  public void executeTrajectory(Trajectory traj) {
    bool complete = false;
    int currentState = 0;
    while (!complete) {
      PathState state = traj.getRobotTarget(new Point(data.m_X, data.m_Y), currentState);
      currentState = state.stage;
      complete = state.complete;
      driveTowardsPoint(state.point.x, state.point.y, data.m_THETA, 1d);
      updateOdometry();
    }
    stop();
  }


  /** Returns the position and rotation of the robot in inches and degrees
   * @return   a double list with {x, y, rotation}
   */
  public double[] getImperialPosition() {
    return new double[]{data.m_X / data.COUNTS_PER_INCH, data.m_Y / data.COUNTS_PER_INCH, Math.toDegrees(data.m_THETA)};
  }

}
