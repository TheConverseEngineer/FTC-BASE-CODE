package org.firstinspires.ftc.teamcode.team7786.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.team7786.geometry.Pose2d;

@Config
public class MecIKTest extends OpMode {

    private DcMotor leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive;

    public static double trackWidth = 10;
    public static double wheelBase = 10;
    public static double rollerGain = Math.sqrt(2) / 2;
    public static double maxSpeed = 435d;
    public static double botSize = 10d;

    private FtcDashboard dash;
    private Pose2d pose;
    private BNO055IMU imu;

    private ElapsedTime timer;

    @Override
    public void init() {
        leftFrontDrive = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        leftRearDrive = hardwareMap.get(DcMotorEx.class, "leftRearDrive");
        rightRearDrive = hardwareMap.get(DcMotorEx.class, "rightRearDrive");

        telemetry.addData("Status", "Initialized");

        dash = FtcDashboard.getInstance();

        pose = new Pose2d(0, 0, 0);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled       = true;
        parameters.loggingTag           = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        timer.reset();
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double w = gamepad1.right_stick_x;

        double tBase = trackWidth + wheelBase;
        double tBaseI = 1 / tBase;
        double R = 1 / rollerGain;
        double RF = rollerGain / 4;

        double dAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - pose.theta;

        double[] motorPowers = new double[]{
                R * (x - y + (w * -tBase)),
                R * (x + y + (w * tBase)),
                R * (x + y + (w * -tBase)),
                R * (x - y + (w * tBase))
        };

        packet.put("IKs", motorPowers);

        motorPowers = normArray(motorPowers, Math.pow(Math.hypot(x, y), 2));

        leftFrontDrive.setPower(motorPowers[0]);
        rightFrontDrive.setPower(motorPowers[1]);
        leftRearDrive.setPower(motorPowers[2]);
        rightRearDrive.setPower(motorPowers[3]);

        for (int i = 0; i < 4; i++) {
            motorPowers[i] = motorPowers[i] * maxSpeed * 11.873736 * (timer.milliseconds() / 60000);
        }

        packet.put("looptime", timer.milliseconds());
        timer.reset();

        // Pseudo-Forward Kinematics
        Pose2d dPose = new Pose2d(
            RF * (motorPowers[0] + motorPowers[1] + motorPowers[2] + motorPowers[3]),
            RF * (-motorPowers[0] + motorPowers[1] + motorPowers[2] - motorPowers[3]),
            Math.toRadians(dAngle)
        );

        if (dAngle != 0) {
            double sinT = Math.sin(dPose.theta);
            double cosT = Math.cos(dPose.theta);
            double sinG = Math.sin(pose.theta);
            double cosG = Math.cos(pose.theta);

            Pose2d rDPose = new Pose2d(
                dPose.x * (((cosG * sinT)/dPose.theta) - ((sinG * (1 - cosT))/dPose.theta)) + dPose.y * (-((sinG * sinT)/dPose.theta) - ((cosG * (1 - cosT))/dPose.theta)),
                dPose.x * (((sinG * sinT)/dPose.theta) + ((cosG * (1 - cosT))/dPose.theta)) + dPose.y * ( ((cosG * sinT)/dPose.theta) - ((sinG * (1 - cosT))/dPose.theta)),
                dPose.theta
            );

            pose = pose.add(rDPose);
        } else {
            pose = pose.add(dPose);
        }

        // Update Telemetry
        packet.put("x", pose.x);
        packet.put("y", pose.y);
        packet.put("theta", pose.theta);

        packet.fieldOverlay()
            .setFill("blue")
            .fillPolygon(getPointsX(), getPointsY());

        dash.sendTelemetryPacket(packet);
    }

    private static double[] normArray(double[] array, double mag) {
        double max = Math.max(Math.max(Math.abs(array[0]), Math.abs(array[1])), Math.max(Math.abs(array[2]), Math.abs(array[3])));
        double[] normed = new double[4];
        for (int i = 0; i < 4; i++) {
            normed[i] = (array[i] / max) * mag;
        }
        return normed;
    }

    private double[] getPointsX() {
        return new double[]{
            Math.cos(pose.theta + (Math.PI / 2)) * botSize + pose.x,
            Math.cos(pose.theta) * botSize + pose.x,
            Math.cos(pose.theta + Math.PI) * botSize + pose.x,
            Math.cos(pose.theta - (Math.PI / 2)) * botSize + pose.x
        };
    }

    private double[] getPointsY() {
        return new double[]{
                Math.sin(pose.theta + (Math.PI / 2)) * botSize + pose.y,
                Math.sin(pose.theta) * botSize + pose.y,
                Math.sin(pose.theta + Math.PI) * botSize + pose.y,
                Math.sin(pose.theta - (Math.PI / 2)) * botSize + pose.y
        };
    }

}
