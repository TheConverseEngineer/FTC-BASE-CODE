package org.firstinspires.ftc.teamcode.team7786;

import org.firstinspires.ftc.teamcode.team7786.MecanumRobot;

import static org.firstinspires.ftc.teamcode.team7786.ROBOT_DATA.*;
import org.firstinspires.ftc.teamcode.team7786.path.Trajectory;
import org.firstinspires.ftc.teamcode.team7786.path.PathState;
import org.firstinspires.ftc.teamcode.team7786.geometry.Point;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.Math.*;

public class FTCBot extends MecanumRobot
{
  public FTCBot(HardwareMap hwMap) {
    super(hwMap);
  }

  /**  Make the robot follow a trajectory
   * @param traj    the trajectory that the robot should follow
   */
  public void executeTrajectory(Trajectory traj) {
    boolean complete = false;
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


  /** Returns the position and rotation of the robot in inches and degrees
   * @return   a double list with {x, y, rotation}
   */
  public double[] getImperialPosition() {
    return new double[]{m_X / COUNTS_PER_INCH, m_Y / COUNTS_PER_INCH, Math.toDegrees(m_THETA)};
  }

}
