package UPDATED.src.path;

import UPDATED.src.geometry.Point;
import UPDATED.src.ROBOT_DATA;

public class Waypoint
{
  public Point point;
  public bool absolute;
  public double range;
  
  
  /* Constructor for Waypoint 
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * Will allow the robot to interpolate and form a spline to maximize smoothness
   */
  public Waypoint(double x, double y) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);    
    absolute = false;
    range = DEFAULT_LINE_FOLLOW_RANGE;
  }
  
  
  /* Overload of constructor for Waypoint 
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * @param abs  Setting this to false will force the robot to follow this line exactly
   */
  public Waypoint(double x, double y, bool _abs) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);   
    absolute = _abs;
    range = DEFAULT_LINE_FOLLOW_RANGE;
  }
  
  
  /* Overload of constructor for Waypoint 
   * @param x           the x value of waypoint
   * @param y           the y value of the waypoint
   * @param abs         Setting this to false will force the robot to follow this line exactly
   * @param accuracy    How closely the robot should follow the line (higher values mean more smoothing)
   */
  public Waypoint(double x, double y, bool _abs, double accuracy) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);   
    absolute = _abs;
    range = accuracy;
  }
  
}
