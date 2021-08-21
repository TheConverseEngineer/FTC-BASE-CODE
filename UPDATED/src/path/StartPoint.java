package UPDATED.src.path;

import UPDATED.src.geometry.Point;


public class StartPoint
{
  public Point point;
  public bool absolute;
  
  
  /* Constructor for StartPoint 
   * @param x    the x value of start point
   * @param y    the y value of the start point
   * Will not require the robot to first go to this point
   */
  public StartPoint(double x, double y) {
    point = new Point(x, y);
    absolute = false;
  }
  
  
  /* Overload of constructor for StartPoint 
   * @param x      the x value of start point
   * @param y      the y value of the start point
   * @param _abs   Setting this to true will require the robot to first go to this point
   */
  public StartPoint(double x, double y, bool _abs) {
    point = new Point(x, y);
    absolute = _abs;
  }
  
  
}
