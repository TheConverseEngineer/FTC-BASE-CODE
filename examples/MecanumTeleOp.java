package examples;

import FTC-BASE-CODE.src.Drivetrain;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

@TeleOp(name="Example: Mecanum OpMode", group="examples")
// @Disabled
public class MecanumTeleOp extends LinearOpMode {
  
  private Drivetrain drive;
  
  /*
   * Code to run ONCE when the driver hits INIT
  */
  @Override
  public void init() {
    telemetry.addData("Status", "Initializing");
    
    // Setup the Drivetrain
    drive = new Drivetrain(hardwareMap, new double[]{true, false, true, false});
    
    telemetry.addData("Status", "Initialized");
    
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
      
    }

  }
  
  
  
  
    

}

