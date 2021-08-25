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
  public FTCBot(HardwareMap hwMap) {
    super(hwMap);
  }

  /*  Make the robot follow a trajectory
   * @param traj    the trajectory that the robot should follow
   */
  public void executeTrajectory(Trajectory traj) {
    bool complete = false;
    int currentState = 0;
    while (!complete) {
      PathState state = traj.getRobotTarget(new Point(m_X, m_Y), currentState);
      currentState = state.stage;
      complete = state.complete;
      driveTowardsPoint(state.point.x, state.point.y, m_THETA, 1d);
      updateOdometry();
    }
    stop();
  }


  /* Returns the position and rotation of the robot in inches and degrees
   * @return   a double list with {x, y, rotation}
   */
  public double[] getImperialPosition() {
    return new double[]{m_X / COUNTS_PER_INCH, m_Y / COUNTS_PER_INCH, Math.toDegrees(m_THETA)};
  }

}
