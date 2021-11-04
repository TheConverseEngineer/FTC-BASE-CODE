package org.firstinspires.ftc.teamcode.team7786.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.team7786.opmodes.gamepadconfigs.SampleConfig;


@TeleOp
public class GamepadExTest extends OpMode
{

    double velocity;

    SampleConfig gamepad;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        velocity = 0;
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        gamepad = new SampleConfig(gamepad1);
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
        gamepad.start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("A button", gamepad.a);
        telemetry.addData("b button", gamepad.b);
        telemetry.addData("left stick x", gamepad.left_stick_x);
        telemetry.addData("Velocity: ", velocity);

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        gamepad.stop();
    }

}
