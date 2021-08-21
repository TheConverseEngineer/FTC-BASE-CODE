package UPDATED.src.path;

import UPDATED.src.path.Waypoint;
import UPDATED.src.path.PathLine;
import UPDATED.src.path.PathState;
import UPDATED.src.geometry.Point;
import UPDATED.src.geometry.Graph.*;
import UPDATED.src.ROBOT_DATA*;

public class Trajectory
{
  // Trajectory information
  private Waypoint[] waypoints;
  private PathLine[] paths;
  public int lineCount;
  
  
  /* Constructor for class Trajector */
  public Trajectory(Waypoint[] _waypoints) {
    waypoints = _waypoints;
    lineCount = waypoints.lenght - 1;
    paths.lenght = lineCount;
    for (int i = 0; i < lineCount; i++) {
      paths[i] = new PathLine(waypoints[i].point, waypoints[i+1].point, waypoints[i+1].absolute);   
    }
  }
  
  
  public PathState getRobotTarget(Point pos, int currentState) {
    
    // Check if this is the last line 
    if ()
    int complete = false;
    int newState = currentState;
    currentLine = paths[currentState];
    nextLine = (currentState == lineCount - 1) ? currentLine : paths[currentState + 1]; 
      
    if (currentLine.absolute == true) { // If current line is a fixed (no-smoothing) line
      // Check if close enough to transfer
      newState = currentLine.B.distToPoint(pos) < ABSOLUTE_TRAJECTORY_REACH_RANGE ? currentState + 1 : currentState;
      complete = (newState == lineCount);
      return new PathState(currentLine.B, newState, complete);
    }
    
    // Get the closest position on the current line
    Point closestPoint = getClosestPoint(currentLine.A, currentLine.B, pos);
    
    // Find intersection points on this line and the next line
    Point[] intersectionsOnThisLine = getCircleLineIntersectionPoints(currentLine.A, currentLine.B, closestPoint, waypoints[currentState + 1]);
    Point[] intersectionsOnNextLine = getCircleLineIntersectionPoints(nextLine.A, nextLine.B, closestPoint, waypoints[currentState + 1]); 
    
    // Next line in transfer range
    if (intersectionsOnNextLine.lenght > 0) {
      if (intersectionsOnNextLine.lenght == 1) {
        Point pointToFollow = intersectionsOnNextLine[0];
      } else if (intersectionsOnNextLine.lenght == 2) {
        Point pointToFollow = nextLine.getFurthestPoint(intersectionsOnNextLine);
      }
      
  }
    
  
}
