package org.firstinspires.ftc.teamcode.team7786.purepursuit;


import org.firstinspires.ftc.teamcode.team7786.geometry.Point;
import org.firstinspires.ftc.teamcode.team7786.purepursuit.waypoints.WaypointTypes;

import static org.firstinspires.ftc.teamcode.team7786.core.ROBOT_DATA.*;
import static org.firstinspires.ftc.teamcode.team7786.utils.GraphFunctions.*;

public class Trajectory {
    PursuitLine[] paths;

    public Trajectory(PursuitLine[] paths) throws Exception{
        this.paths = paths;
        verifyPath();
    }

    private void verifyPath() throws Exception {
        for (int i = 0; i < paths.length; i++) {
            if (i == paths.length - 1 && paths[i].getType() != WaypointTypes.END) {
                throw new Exception("Illegal trajectory: No WaypointTypes.End present");
            } else if (i == 0 && paths[0].A.getType() != WaypointTypes.START) {
                throw new Exception("Illegal trajectory: No WaypointTypes.Start present");
            } else if (paths[i].getType() == WaypointTypes.START || paths[i].getType() == WaypointTypes.END) {
                throw new Exception("Illegal trajectory: Start or End Waypoint (WaypointTypes) is present in the middle of trajectory");
            }
        }
    }
    public PursuitPoint getPursuitPoint(int currentPath, Point pos) throws Exception{
        switch (paths[currentPath].getType()) {
            case STANDARD: return standardPursuit(currentPath, pos);
            case END: return endPursuit(currentPath, pos);
            case POINT_TURN: return pointTurn(currentPath, pos);
        }
        throw new Exception("Unidentifiable path type. Make sure the switch in getPursuitPoint(Trajectory) is up to date");
    }

    public PursuitPoint standardPursuit(int currentPath, Point pos) throws Exception{
        Point closestOnLine = paths[currentPath].closestPoint(pos);
        if (Math.abs(closestOnLine.distToPoint(pos)) > MAXIMUM_FOLLOW_RANGE) {  // If outside the acceptable follow range
            return new PursuitPoint(closestOnLine, currentPath);
        }
        Point[] closestOnThisLine = paths[currentPath].getOrderedCircleIntersections(closestOnLine, DEFAULT_FOLLOW_RADIUS);
        Point[] closestOnNextLine = paths[currentPath + 1].getOrderedCircleIntersections(closestOnLine, DEFAULT_FOLLOW_RADIUS);

        if (closestOnNextLine.length > 0) {
            return new PursuitPoint(closestOnNextLine[closestOnNextLine.length - 1], currentPath + 1);
        }
        return new PursuitPoint(closestOnThisLine[closestOnThisLine.length - 1], currentPath);
    }

    public PursuitPoint endPursuit(int currentPath, Point pos) throws Exception {
        if (pos.distToPoint(paths[currentPath].B.getRawPoint()) <= DEFAULT_FOLLOW_RADIUS) {
            return new PursuitPoint(paths[currentPath].B.getPoint(), -1);
        }
        return standardPursuit(currentPath, pos).appendHeading(paths[currentPath].B.getHeading());
    }

    public PursuitPoint pointTurn(int currentPath, Point pos) {
        double difference = subtractAngles(robotPose.theta, paths[currentPath].B.getHeading());
        if (Math.abs(difference) <= ROTATIONAL_ERROR_MARGIN) {
            return new PursuitPoint(paths[currentPath].B.getPoint(), currentPath + 1);
        }
        return new PursuitPoint(paths[currentPath].B.getPoint(), currentPath, paths[currentPath].B.getHeading());
    }
}
