package org.firstinspires.ftc.teamcode.team7786.geometry;

import org.opencv.core.Mat;

public class Pose2d {
    public double x;
    public double y;
    public double theta;

    public Pose2d(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Pose2d(double x, double y) { this(x, y, 0d); }

    public Pose2d add(Pose2d other) {
        return new Pose2d(x + other.x, y + other.y, theta + other.theta);
    }

    public Pose2d subtract(Pose2d other) {
        return new Pose2d(x - other.x, y - other.y, theta - other.theta);
    }

    public Pose2d scaleSelf(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public Pose2d scaleNew(double scalar) {
        return new Pose2d(x * scalar, y * scalar, theta);
    }

    public Pose2d normalizeSelf() {
        double scalar = 1 / Math.max(Math.abs(x), Math.abs(y));
        return scaleSelf(scalar);
    }

    public Pose2d normalizeNew() {
        double scalar = 1 / Math.max(Math.abs(x), Math.abs(y));
        return scaleNew(scalar);
    }

    public Pose2d rotateSelf(double dTheta) {
        theta += dTheta;
        return this;
    }

    public Pose2d rotateNew(double dTheta) {
        return new Pose2d(x, y, theta + dTheta);
    }

    public double getThetaDegrees() {
        return Math.toDegrees(theta);
    }

    public Vector2d asVector() {
        return new Vector2d(x, y);
    }
}
