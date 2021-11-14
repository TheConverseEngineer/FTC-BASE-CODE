package org.firstinspires.ftc.teamcode.team7786.geometry;

import java.util.Vector;

public class Vector2d {
    public double x;
    public double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d asPolar() {
        return new Vector2d(Math.hypot(x, y), Math.atan2(x, y));
    }

    public Vector2d asCartesian() {
        return new Vector2d(x * Math.cos(y), x * Math.sin(y));
    }

    /** Rotates a cartesian vector and returns this
     * @param dTheta   the amount to rotate by
     */
    public Vector2d rotateSelf(double dTheta) {
        Vector2d newSelf = rotateNew(dTheta);
        x = newSelf.x;
        y = newSelf.y;
        return this;
    }

    /** Rotates a cartesian vector and returns a copy
     * @param dTheta   the amount to rotate by
     */
    public Vector2d rotateNew(double dTheta) {
        Vector2d polar =  this.asPolar();
        polar.y += dTheta;
        return polar.asCartesian();
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d scaleSelf(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public Vector2d scaleNew(double scalar) {
        return new Vector2d(x * scalar, y * scalar);
    }

    public Vector2d normalizeSelf() {
        double scalar = 1 / Math.max(Math.abs(x), Math.abs(y));
        return scaleSelf(scalar);
    }

    public Vector2d normalizeNew() {
        double scalar = 1 / Math.max(Math.abs(x), Math.abs(y));
        return scaleNew(scalar);
    }
}
