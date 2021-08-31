package org.firstinspires.ftc.teamcode.team7786.testing.fakes.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.team7786.testing.fakes.util.FakeHardwareDevice;

public class FakeDcMotor extends FakeHardwareDevice implements DcMotor{


    private DcMotor.RunMode runMode = RunMode.RUN_WITHOUT_ENCODER;
    private boolean isBusy = false;
    private double motorPower;
    private double maxRPM;
    private double currentRPM;
    private int encoderPos;
    private int targetPos;
    private ZeroPowerBehavior zeroPowerBehavior = ZeroPowerBehavior.UNKNOWN;
    private Direction direction = Direction.FORWARD;
    @Override
    public MotorConfigurationType getMotorType() {
        return null;
    }

    @Override
    public DcMotorController getController(){
        return null;
    }

    @Override
    public void setMotorType(MotorConfigurationType motorType){
    }
    @Override
    public int getCurrentPosition(){
        return encoderPos;
    }
    @Override
    public int getPortNumber(){
        return 0;
    }
    @Override
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior){
        zeroPowerBehavior = behavior;
    }
    public DcMotor.ZeroPowerBehavior getZeroPowerBehavior(){
        return zeroPowerBehavior;
    }
    @Override
    public void setPowerFloat(){

    }
    @Override
    public boolean getPowerFloat(){
        return false;
    }
    @Override
    public void setPower(double pow){
        motorPower = pow;
        currentRPM = maxRPM*pow;
    }
    @Override
    public void setTargetPosition(int pos){
        targetPos = pos;
    }
    @Override
    public int getTargetPosition(){
        return targetPos;
    }
    @Override
    public boolean isBusy(){
        return isBusy;
    }
    @Override
    public void setMode(RunMode mode){
        runMode = mode;
    }
    @Override
    public RunMode getMode(){
        return runMode;
    }
    @Override
    public void setDirection(Direction newDirection){
        direction = newDirection;
    }
    @Override
    public Direction getDirection(){
        return direction;
    }
    @Override
    public double getPower(){
        return motorPower;
    }

}