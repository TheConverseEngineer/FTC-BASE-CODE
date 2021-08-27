package examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import src.team7786.main2021.FTCBot;
import src.team7786.main2021.ROBOT_DATA.*;
import src.team7786.main2021.path.Trajectory;
import src.team7786.main2021.path.Waypoint;


/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TEST: DriveToPoint Test", group="Tests")
//@Disabled
public class DriveToPointTest extends OpMode
{
  // Declare the FTCBot
  public FTCBot bot;
  
  // Declare the trajectory
  public Trajectory traj;
  
  @Override
  public void init() {
    // Create the robot
    bot = new FTCBot(hardwareMap);
    // Create trajectory
    traj = new Trajectory(new Waypoint[]{new Waypoint(0d, 0d), 
                                         new Waypoint(0d, 24d),
                                         new Waypoint(24d, 24d),
                                         new Waypoint(24d, 0d),
                                         new Waypoint(0d, 0d)});
  }
  
  
  @Override
  public void start() {
    // Execute trajectory
    bot.executeTrajectory(traj);
  }
}
