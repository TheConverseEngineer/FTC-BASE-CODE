package org.firstinspires.ftc.teamcode.team7786.main2021;

import org.firstinspires.ftc.teamcode.team7786.main2021.MecanumRobot;

import static org.firstinspires.ftc.teamcode.team7786.main2021.ROBOT_DATA.*;
import org.firstinspires.ftc.teamcode.team7786.main2021.path.Trajectory;
import org.firstinspires.ftc.teamcode.team7786.main2021.path.PathState;
import org.firstinspires.ftc.teamcode.team7786.main2021.geometry.Point;
import com.qualcomm.robotcore.hardware.DcMotor;
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


  /** Returns the position and rotation of the robot in inches and degrees
   * @return   a double list with {x, y, rotation}
   */
  public double[] getImperialPosition() {
    return new double[]{m_X / COUNTS_PER_INCH, m_Y / COUNTS_PER_INCH, Math.toDegrees(m_THETA)};
  }

}
