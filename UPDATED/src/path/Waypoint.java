package UPDATED.src.path;

import UPDATED.src.geometry.Point;

public class Waypoint
{
  public Point point;
  public bool absolute;
  
  
  /* Constructor for Waypoint 
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * Will allow the robot to interpolate and form a spline to maximize smoothness
   */
  public Waypoint(double x, double y) {
    point = new Point(x, y);    
    absolute = false;
  }
  
  
  /* Constructor for Waypoint 
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * @param abs  Setting this to false will force the robot to follow this line exactly
   */
  public Waypoint(double x, double y, bool _abs) {
    point = new Point(x, y);   
    absolute = _abs;
  }
  
}
