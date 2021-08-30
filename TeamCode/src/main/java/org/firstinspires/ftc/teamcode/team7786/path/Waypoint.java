package org.firstinspires.ftc.teamcode.team7786.path;

import org.firstinspires.ftc.teamcode.team7786.geometry.Point;
import static org.firstinspires.ftc.teamcode.team7786.ROBOT_DATA.*;

public class Waypoint
{
  public Point point;
  public boolean absolute;
  public double range;


  /** Constructor for Waypoint
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * Will allow the robot to interpolate and form a spline to maximize smoothness
   */
  public Waypoint(double x, double y) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);
    absolute = false;
    range = DEFAULT_LINE_FOLLOW_RANGE;
  }


  /**Overload of constructor for Waypoint
   * @param x    the x value of waypoint
   * @param y    the y value of the waypoint
   * @param _abs  Setting this to false will force the robot to follow this line exactly
   */
  public Waypoint(double x, double y, boolean _abs) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);
    absolute = _abs;
    range = DEFAULT_LINE_FOLLOW_RANGE;
  }


  /**Overload of constructor for Waypoint
   * @param x           the x value of waypoint
   * @param y           the y value of the waypoint
   * @param _abs         Setting this to false will force the robot to follow this line exactly
   * @param accuracy    How closely the robot should follow the line (higher values mean more smoothing)
   */
  public Waypoint(double x, double y, boolean _abs, double accuracy) {
    point = new Point(x * COUNTS_PER_INCH, y * COUNTS_PER_INCH);
    absolute = _abs;
    range = accuracy;
  }

}
