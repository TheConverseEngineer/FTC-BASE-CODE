package org.firstinspires.ftc.teamcode.team7786.core;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team7786.purepursuit.Trajectory;

public class AutoBot extends MecanumRobot{

    private Trajectory currentTraj;

    public AutoBot (HardwareMap hwmap) {
        super(hwmap);
    }

    public void beginTrajectory(Trajectory traj) {
        currentTraj = traj;
    }
}
