package UPDATED.src.geometry;


public class Point
{
  public double x;
  public double y;
  
  /* Constructor for the point class
   * @param x    the x value of the point
   * @param y    the y value of the point
   */
  public Point(double _x, double _y) {
    x = _x;
    y = _y;
  }
  
  
  /* Adds another point to this point
   * @param pointToAdd     the point that should be added to this point
   * @return               the sum
   */
  public Point addToPoint(Point pointToAdd) {
    double newX = x + pointToAdd.x;
    double newY = y + pointToAdd.y;
    return new Point(newX, newY);    
  }
  
  
  /* Subtracts another point from this point
   * @param pointToSubtract     the point that should be subtracted to this point
   * @return                    the difference
   */
  public Point subtractPoint(Point pointToSubtract) {
    double newX = x - pointToSubtract.x;
    double newY = y - pointToSubtract.y;
    return new Point(newX, newY);    
  }
  
  
  /* Subtracts this point from another point
   * @param pointToSubtract     the point that this point should be subtracted from 
   * @return                    the difference
   */
  public Point subtractFromPoint(Point pointToSubtract) {
    double newX = pointToSubtract.x - x;
    double newY = pointToSubtract.y - x;
    return new Point(newX, newY);    
  }
  
  public Point multiplyScalar(double a) {
   return new Point(x * a, y * a);
  }
  
  
