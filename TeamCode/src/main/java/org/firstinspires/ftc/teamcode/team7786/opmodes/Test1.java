package org.firstinspires.ftc.teamcode.team7786.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.team7786.opmodes.gamepadconfigs.SampleConfig;


@TeleOp(name="test1", group="default")
public class Test1 extends OpMode
{
    SampleConfig gamepad = new SampleConfig(gamepad1);
    private DcMotorEx dave;
    private double velocity = 0;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        dave  = hardwareMap.get(DcMotorEx.class, "dave");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        dave.setDirection(DcMotorEx.Direction.FORWARD);dave.setTargetPosition(0);
        dave.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        dave.setMotorEnable();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double davePower;
        double drive = gamepad.left_stick_y();

        if (gamepad.dpad_up()) {
            velocity += 5;
        }
        else if (gamepad.dpad_down()){
            if (velocity >= 5){
                velocity -= 5;
            }
        }
        dave.setPower(1);
        dave.setVelocity(velocity);
        telemetry.addData("Velocity: ", dave.getVelocity());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        
    }

}
