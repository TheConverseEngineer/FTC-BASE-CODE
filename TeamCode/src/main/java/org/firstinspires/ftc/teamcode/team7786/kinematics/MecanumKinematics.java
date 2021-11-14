package org.firstinspires.ftc.teamcode.team7786.kinematics;

import org.firstinspires.ftc.teamcode.team7786.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.team7786.geometry.Vector2d;
import org.firstinspires.ftc.teamcode.team7786.utils.MathFunctions;

public class MecanumKinematics {

    private static MecanumKinematics instance = new MecanumKinematics();

    private MecanumKinematics() {}

    public MecanumKinematics getInstance() {
        return instance;
    }

    /** Returns Motor Powers for a Mecanum Drivetrain given a (cartesian) velocity
     * @param velocity  A Pose2d consisting of the velocity. X and Y MUST be cartesian.
     *                  X, Y, and Theta are bounded between -1 and 1. The movement speed is equal to the hypot of x and y.
     *                  Rotation might slow down the movement if unavoidable
     **/
    public double[] inverseKinematics(Pose2d velocity) {
        Vector2d translation = velocity.asVector().asPolar();
        translation.x = Math.abs(MathFunctions.clipRange(translation.x));
        double[] motorPowers = new double[]{Math.sin(translation.y + Math.PI / 4) * translation.x,
                                            Math.sin(translation.y - Math.PI / 4) * translation.x,
                                            Math.sin(translation.y - Math.PI / 4) * translation.x,
                                            Math.sin(translation.y + Math.PI / 4) * translation.x};

        double turnSpeed = MathFunctions.clipRange(velocity.theta);
        double greatestPower = 0;
        for (int i = 0; i < 4; i++) {
            motorPowers[i] += turnSpeed * ((i % 2) == 0 ? 1 : -1);
            if (Math.abs(motorPowers[i]) > greatestPower) {
                greatestPower = Math.abs(motorPowers[i]);
            }
        }

        if (greatestPower > 1) {
            for (double i : motorPowers) { i /= greatestPower; }
        }

        return motorPowers;
    }

    public Pose2d forwardKinematics(double[] motorDeltas) {
        Pose2d robotVelo = new Pose2d(0, 0, 0);
        return robotVelo;
    }

}
