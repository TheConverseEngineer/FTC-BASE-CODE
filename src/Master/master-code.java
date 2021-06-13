package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.lang.Math.*;

@TeleOp(name = "Teleop_2020_V1", group = "default")
public class Teleop_2020_V1 extends OpMode
{
	// Declare Elapsed Time
	private ElapsedTime runtime = new ElapsedTime();

	// Declare Motors
	private DcMotor leftFrontDrive, shootDrive, rightRearDrive, rightFrontDrive, leftRearDrive, liftMotor, chainDrive;
	private Servo shootServo, otherServo, pickServo;
	double volts;
	boolean ohioButton = false;

	// Other variables
	double drivePower = 1;
	double shootPower = 0d;
	double shootMax = 1;
	double squeze = 2;
	boolean shootType = true;
	int yButton = 0;
	int xButton = 0;
	int defaultVoltage = 13;

	// Front servo positions
	double retracted = 0.3;
	double servoUp = 0.6;
	double servoDown = 2;

	// Boolean to prevent sweeping when in retracted mode
	boolean isRetracted = true;

	// Integer that stores drive orientation and boolean to prevent spamming
	boolean lBumpDown = false;
	int driveDir = -1;




	//Code to run ONCE when the driver hits INIT
	@Override
	public void init() {
		telemetry.addData("Status", "Initializing");

		// Initialize the motor hardware variables
		leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFrontDrive");
		leftRearDrive  = hardwareMap.get(DcMotor.class, "leftRearDrive");
		rightFrontDrive  = hardwareMap.get(DcMotor.class, "rightFrontDrive");
		rightRearDrive  = hardwareMap.get(DcMotor.class, "rightRearDrive");
		shootDrive  = hardwareMap.get(DcMotor.class, "shootDrive");
		chainDrive = hardwareMap.get(DcMotor.class, "chainDrive");
		liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
		shootServo = hardwareMap.get(Servo.class, "shootServo");
		pickServo = hardwareMap.get(Servo.class, "pickServo");

		// set motor orientations (positive power should move wheel clockwise)
		leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
		leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
		rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
		rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
		shootDrive.setDirection(DcMotor.Direction.REVERSE);
		chainDrive.setDirection(DcMotor.Direction.REVERSE);
		shootServo.setDirection(Servo.Direction.REVERSE);
		pickServo.setDirection(Servo.Direction.REVERSE);
		//otherServo.setDirection(Servo.Direction.REVERSE);

		// Tell the driver that initialization is complete.
		telemetry.addData("Status", "Initialized");
		telemetry.addData("ToggleDir", driveDir);
	}


	/*
	this function uses math to make the robot move
	x and y are both doubles between 1 and negative 1
	this function will cause the bot to move at an angle towords the coordinate given
	Note: It will not stop until given a new set of instructions
	*/
	public void move(double x, double y){
		 // Convert input to direction (radians)
		 double heading = Math.atan2(x, y);
		 double intensity = Math.hypot(x, y);

		 // Calculate motor power
		 double simplifiedHeading = Math.toDegrees(heading) / 45;
		 double frontLeftDiagonalPower = Math.sin(( 1.57 / 2 )  *  ( simplifiedHeading - 1 ))	*	-1.4;
		 double frontRightDiagonalPower = Math.cos(( 1.57 / 2 )  *  ( simplifiedHeading - 1 ))	*	1.4;

		 // Make sure that the motor power is between -1 and 1
		 frontLeftDiagonalPower = Range.clip(frontLeftDiagonalPower, -1, 1);
		 frontRightDiagonalPower = Range.clip(frontRightDiagonalPower, -1, 1);

		 //Multiply by intensity
		 frontLeftDiagonalPower *= intensity;
		 frontRightDiagonalPower *= intensity;

		 frontLeftDiagonalPower *= drivePower;
		 frontRightDiagonalPower *= drivePower;

		 // Move the robot
		 leftFrontDrive.setPower(frontLeftDiagonalPower);
		 leftRearDrive.setPower(frontRightDiagonalPower);
		 rightFrontDrive.setPower(frontRightDiagonalPower);
		 rightRearDrive.setPower(frontLeftDiagonalPower);
	}


	/*
	this function allows the robor to spinn in place
	here direction is a double between 1 and negative 1
	if a positive number is given it will rotate clockwise
	if a negative number is given it will rotate counterclockwise
	the number controlls its speed of rotation
	*/
	public void rotate(double dir){
		leftFrontDrive.setPower(dir*drivePower);
		leftRearDrive.setPower(dir*drivePower);
		rightFrontDrive.setPower(-dir*drivePower);
		rightRearDrive.setPower(-dir*drivePower);
	}



	@Override
	public void start() {
		runtime.reset();
	}




	//Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
	@Override
	public void loop() {

		// Rotate or Move robot (gamepad 1) : left and right joysticks
		if (Math.abs(gamepad1.right_stick_x) > 0.05 ) {
			rotate(-gamepad1.right_stick_x);
		} else {
			move(driveDir * gamepad1.left_stick_x, driveDir * gamepad1.left_stick_y);
		}

		// Toggle drive direction (gamepad 1): left bumper
		if (gamepad1.left_bumper && !lBumpDown) {
			lBumpDown = true;
			driveDir = -driveDir;
		} else if (!gamepad1.left_bumper) {
			lBumpDown = false;
		}

		// Add telemetry to tell the driver the current drive direction
		telemetry.addData("ToggleDir", driveDir);

		// Speed up shoot motor with a button (gamepad 2): a
		if (gamepad2.a == true && shootPower < shootMax){
			shootPower += bell(shootPower);
		}
		else if (shootPower > shootMax){
			shootPower = Range.clip(shootPower - bell(shootPower), 0, 1);
		}

		// Slow down shoot motor with a button (gamepad 2): b
		if ((gamepad2.b == true) && shootPower > 0){
			shootPower = Range.clip(shootPower - bell(shootPower), 0, 1);
			telemetry.addData("Shoot Power", shootPower);
		}

		// Slow down (or speed up) the robot so it moves at 1/4 of its usual speed (gamepad 1): right bumper
		if (gamepad1.right_bumper) {
			drivePower = 0.25;
		}
		else{
			drivePower = 1;
		}

		// Shoot ring (gamepad 1): right trigger
		if (gamepad1.right_trigger == 1) {
			shootServo.setPosition(0.41);
			chainDrive.setPower(0);

		}

		// Reset shoot servo (gamepad 1): right trigger
		if (gamepad1.right_trigger == 0) {
			shootServo.setPosition(0.1);
			chainDrive.setPower(1);
		}

		// blah-blah-blah
		if (gamepad2.right_trigger == 1) {
			liftMotor.setPower(0.2);
		} else if (gamepad2.left_trigger == 1) {
			liftMotor.setPower(-0.2);
		} else {
			liftMotor.setPower(0);
		}
		/*
		this allows different limits of shooting speed based on the shoot setting
		pressing the y button will toggle between them (gamepad 2)
		*/
		if (gamepad2.y){
			if (yButton == 1){
				shootType = !shootType;
			}
			yButton = 0;
		}
		else{
			yButton = 1;
		}

		// Toggle the ring collection chain (gamepad 2): x
		if (gamepad2.x) {
			if (xButton == 1) {
				if (chainDrive.getPower() == 0) {
					chainDrive.setPower(1);
				}
				else {
					chainDrive.setPower(0);
				}
			}
			xButton = 0;
		}
		else{
			xButton = 1;
		}

		// The d-pad toggles the servo that sweeps the ring into the collection chain (gamepad 2)
		if (gamepad2.dpad_up){
			pickServo.setPosition(retracted);
			isRetracted = true;
		}
		else if (gamepad2.dpad_down){
			pickServo.setPosition(servoUp);
			isRetracted = false;
		}

		//Trigger the sweeper when the left trigger is used (gamepad 1)
		else if (gamepad1.left_trigger == 1 && !isRetracted){
			pickServo.setPosition(servoDown);
		}

		else if (!isRetracted) {
			pickServo.setPosition(servoUp);
		}


		if (shootType == true){
			shootMax = getShootPower(1);
		}
		else{
			shootMax = getShootPower(0.75);
		}


		// Telemetry updates
		shootDrive.setPower(shootPower);
		telemetry.addData("Shoot Limit", shootMax);
		telemetry.addData("Shoot Power", shootPower);

	}

	public void onStop(){
		while (shootPower > 0) {
			shootPower = Range.clip(shootPower - bell(shootPower), 0, 1);
			telemetry.addData("Shoot Power", shootPower);
			shootDrive.setPower(shootPower);
		}
	}



	/* This function will take the power of a motor and adjust it for the current voltage of the battery based on the default voltage(can be changed at top)
	power is a number between 1 and negative 1 and is the power that the motor would usually be set to */
	public double getShootPower(double power){
		double voltage = getBattery();
		double percentage = (voltage / defaultVoltage);
		telemetry.addData("Voltage adjustment", (100 / percentage));
		return (Math.min(power / percentage, 1));
	}



	// This returns the current voltage of the battery
	public double getBattery(){
		double result = Double.POSITIVE_INFINITY;
		for (VoltageSensor sensor : hardwareMap.voltageSensor){
			double voltage = sensor.getVoltage();
			if(voltage > 0){
				result = Math.min(result, voltage);
			}
		}
		return result;
	}



	/* This is a funtion used for the bell curve that is used to speed up and slow down the shoot drive
	 * speed is the current power being used on the shooting motor
	 * the funtion will return what it should be set to next */
	public double bell(double _speed){
		return 0.0005*Math.exp(Math.pow(-(_speed-0.5), 2d)/(2d*Math.pow(squeze, 2d)));
	}
}
