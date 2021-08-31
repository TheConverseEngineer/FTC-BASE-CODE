package org.firstinspires.ftc.teamcode.team7786.testing.fakes.hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.team7786.testing.fakes.util.FakeHardwareDevice;

public class FakeServo extends FakeHardwareDevice implements Servo{

    private Direction direction = Direction.FORWARD;
    private double currentpos = 0;
    private double posMin = 0;
    private double posMax = 1;
    @Override
    public ServoController getController(){
        return null;
    }
    @Override
    public Direction getDirection(){
        return direction;
    }
    @Override
    public int getPortNumber(){
        return 0;
    }
    @Override
    public double getPosition(){
        return currentpos;
    }
    @Override
    public void setPosition(double position){
        currentpos = position;
    }
    @Override
    public void scaleRange(double min, double max){
        posMax = max;
        posMin =  min;
    }
    @Override
    public void setDirection(Direction newDirection){
        direction = newDirection;
    }
}