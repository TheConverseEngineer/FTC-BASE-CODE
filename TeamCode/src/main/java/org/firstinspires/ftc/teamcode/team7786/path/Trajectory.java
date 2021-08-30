package org.firstinspires.ftc.teamcode.team7786.path;

import org.firstinspires.ftc.teamcode.team7786.path.Waypoint;
import org.firstinspires.ftc.teamcode.team7786.path.PathLine;
import org.firstinspires.ftc.teamcode.team7786.path.PathState;
import org.firstinspires.ftc.teamcode.team7786.geometry.Point;
import org.firstinspires.ftc.teamcode.team7786.geometry.Graph;
import static org.firstinspires.ftc.teamcode.team7786.ROBOT_DATA.*;

public class Trajectory
{
    // Trajectory information
    private Waypoint[] waypoints;
    private PathLine[] paths;
    public int lineCount;


    /** Constructor for class Trajector
     * @param _waypoints     a list of waypoint objects that the spline should follow
     */
    public Trajectory(Waypoint[] _waypoints) {
        waypoints = _waypoints;
        lineCount = waypoints.length - 1;
        paths = new PathLine[lineCount];
        for (int i = 0; i < lineCount; i++) {
            paths[i] = new PathLine(waypoints[i], waypoints[i+1], waypoints[i+1].absolute);
        }
    }


    /** Returns the current state of the trajectory follower, the position that the robot should go towards, and if the trajectory is done'
     * @param pos
     * @param currentState    the current step in the trajectory that the robot is in
     * @return                A path state object with the target position, the trajectory step, and if the trajectory is complete
     */
    public PathState getRobotTarget(Point pos, int currentState) {

        PathLine currentLine = paths[currentState];
        // Get the closest position on the current line
        Point closestPoint = Graph.getClosestPoint(currentLine.A, currentLine.B, pos);
        // Find intersection points on this line
        Point[] intersectionsOnThisLine = Graph.getCircleLineIntersectionPoints(currentLine.A, currentLine.B, closestPoint, currentLine.wB.range);

        PathLine nextLine;

        // Check if this is the last line
        if (currentState + 1 == lineCount) {
            // target
            Point target = currentLine.absolute ? currentLine.B : (intersectionsOnThisLine[intersectionsOnThisLine.length - 1]);
            boolean complete = currentLine.B.distToPoint(pos) < currentLine.wB.range;
            return new PathState(target, currentState, complete);

        } else {
            nextLine = paths[currentState+1];
        }

        Point[] intersectionsOnNextLine = Graph.getCircleLineIntersectionPoints(nextLine.A, nextLine.B, closestPoint, waypoints[currentState + 1].range);


        // Check if this is an absolute line
        if (currentLine.absolute) {
            Point target = currentLine.B;
            int newState = currentState;
            // Check if in range for transfer
            if (currentLine.B.distToPoint(pos) < currentLine.wB.range) {
                target = nextLine.absolute ? nextLine.B : nextLine.getFurthestPoint(intersectionsOnNextLine);
                newState = currentState + 1;
            }
            return new PathState(target, newState, false);
        }

        // This is not an absolute line nor the last line
        Point pointToFollow = (intersectionsOnNextLine.length > 0) ? nextLine.getFurthestPoint(intersectionsOnNextLine) : currentLine.getFurthestPoint(intersectionsOnThisLine);
        int newStage = currentState +  ((intersectionsOnNextLine.length > 0) ? 1 : 0);
        return new PathState(pointToFollow, newStage, false);
    }

}