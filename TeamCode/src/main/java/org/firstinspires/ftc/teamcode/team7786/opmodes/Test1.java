package org.firstinspires.ftc.teamcode.team7786.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="test1", group="default")
public class Test1 extends OpMode
{

    private DcMotorEx dave;

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
        dave.setDirection(DcMotorEx.Direction.FORWARD);
        dave.setTargetPosition(0);
        dave.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
        double drive = gamepad1.left_stick_y;

        if (gamepad1.a) {
            dave.setTargetPosition(5);
            davePower = 0.3;
            dave.setPower(davePower);
        }
        else if (gamepad1.b) {
            dave.setTargetPosition(40);
            davePower = 0.3;
            dave.setPower(davePower);
        }
        else if (gamepad1.x) {
            dave.setTargetPosition(100);
            davePower = 0.3;
            dave.setPower(davePower);
        }
        else if (gamepad1.y) {
            dave.setTargetPosition(150);
            davePower = 0.3;
            dave.setPower(davePower);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
