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

/** *********************** HOW TO USE THIS FILE *************************




**/

// @TeleOp(name = "MASTER BASE CODE", group = "default")
public class Teleop_2020_V1 extends OpMode
{
	// Declare Elapsed Time
	private ElapsedTime runtime = new ElapsedTime();

	// Declare Drive Motors and Array
	private DcMotor leftFrontDrive, rightRearDrive, rightFrontDrive, leftRearDrive;
	private DcMotor[] drivers;

	// Other variables
	double drivePower;
	int driveDir = -1;
	
	/** EXAMPLE LOOP CODE VARIABLES
	boolean lBumpDown = false;
        **/



	// This code initializes the drive motors
	public void initDriveBase(boolean[] _reverse, double _drivePower) {
		telemetry.addData("Status", "Initializing");
		
		// Initialize the motor hardware variables
		leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFrontDrive");
		leftRearDrive  = hardwareMap.get(DcMotor.class, "leftRearDrive");
		rightFrontDrive  = hardwareMap.get(DcMotor.class, "rightFrontDrive");
		rightRearDrive  = hardwareMap.get(DcMotor.class, "rightRearDrive");
		
		// Initialize the motor array
		drivers = [leftFrontDrive, rightRearDrive, rightFrontDrive, leftRearDrive];
		
		// set motor orientations (positive power should move wheel clockwise)
		for (i = 0; 1 < 4; i++) {
			if (_reverse[i] == true) {
				drivers[i].setDirection(DcMotor.Direction.REVERSE);
			} 
		}
		
		// Set the drive power
		drivePower = _drivePower
		
		// Tell the driver that initialization is complete.
		telemetry.addData("Status", "Initialized");
	}


	/*
	This function uses math to make the robot move
	x and y are both doubles between 1 and negative 1
	this function will cause the bot to move at an angle towords the coordinate given
	Note: It will not stop until given a new set of instructions
	*/
	public void move(double _x, double _y){
		 // Convert input to direction (radians)
		 double x = _x * driveDir
		 double y = _y * driveDir
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
	This function allows the robor to spinn in place
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


	/** Example loop code for movement in accordance to joysticks 
	    MAKE SURE TO ADD THE VARIABLE LISTED IN THE DECLARATION SECTION
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
        }
	**/
	
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



	
}
