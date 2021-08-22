package UPDATES.src.examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import UPDATES.src.FTCBot;
import UPDATES.src.ROBOT_DATA.*;


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
  
  @Override
  public void init() {
    bot = new FTCBot(hardwareMap);
  }
  
  
  @Override
  public void loop() {
  
    if (gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick.x > 0) {
      bot.driveWithController(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick.x, true);
    } else {
      bot.driveTowardsPoint(24d * COUNTS_PER_INCH, 0d, 0d, 0.5d);
    }
    
    bot.updateOdometry();
    double[] pos = bot.getImperialPosition();
    telemetry.addData("X", pos[0].toString());
    telemetry.addData("Y", pos[1].toString());
    telemetry.addData("Heading", pos[2].toString());
  }
}
