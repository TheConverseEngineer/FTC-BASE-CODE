package examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import src.team7786.main2021.FTCBot;


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

@TeleOp(name="TEST: Controller Test", group="Tests")
//@Disabled
public class ControllerTest extends OpMode
{
  public FTCBot bot;
  
  @Override
  public void init() {
    bot = new FTCBot(hardwareMap);
  }
  
  
  @Override
  public void loop() {
    bot.driveWithController(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick.x, true);
    bot.updateOdometry();
    double[] pos = bot.getImperialPosition();
    telemetry.addData("X", pos[0].toString());
    telemetry.addData("Y", pos[1].toString());
    telemetry.addData("Heading", pos[2].toString());
  
   }
  }
