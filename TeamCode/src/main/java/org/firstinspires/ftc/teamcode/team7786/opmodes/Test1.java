package org.firstinspires.ftc.teamcode.team7786.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.team7786.opmodes.gamepadconfigs.SampleConfig;


@TeleOp
public class Test1 extends OpMode
{
    DcMotorEx dave, dave2, dave3, dave4;
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
        dave  = hardwareMap.get(DcMotorEx.class, "dave");
        dave2  = hardwareMap.get(DcMotorEx.class, "dave2");
        dave3  = hardwareMap.get(DcMotorEx.class, "dave3");
        dave4  = hardwareMap.get(DcMotorEx.class, "dave4");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

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
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double davePower = 1;




        dave.setPower(davePower);
        dave2.setPower(davePower);
        dave3.setPower(davePower);
        dave4.setPower(davePower);
        telemetry.addData("Speed", davePower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        
    }

}
