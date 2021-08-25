package UPDATES.examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import UPDATES.src.FTCBot;
import UPDATES.src.ROBOT_DATA.*;
import UPDATES.src.path.Trajectory;
import UPDATES.src.path.Waypoint;


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
  public FTCBot bot;
  public Trajectory traj;
  
  @Override
  public void init() {
    bot = new FTCBot(hardwareMap);
    traj = new Trajectory(new Waypoint[]{new Waypoint(0d, 0d), 
                                         new Waypoint(0d, 24d),
                                         new Waypoint(24d, 24d),
                                         new Waypoint(24d, 0d),
                                         new Waypoint(0d, 0d)});
  }
  
  
  @Override
  public void start() {
    bot.executeTrajectory(traj);
  }
}