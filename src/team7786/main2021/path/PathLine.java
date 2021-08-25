package team7786.main2021.path;

import team7786.main2021.geometry.Point;
import team7786.main2021.path.Waypoint;

public class PathLine
{
  public Point A;
  public Point B;
  public Waypoint wA;
  public Waypoint wB;
  public bool absolute;


  /* Constructor for class PathLine
   * @param _A     The starting point on this line
   * @param _B     The ending point on this line
   * @param _abs   if the robot should follow this line or interpolate for smoothness
   */
  public PathLine (Waypoint _A, Waypoint _B, _abs) {
    A = _A.point;
    B = _B.point;
    wA = _A;
    wB = _B;
    absolute = _abs;
  }


  /* Takes two points and returns the one closest to the end of the line
   * @param points   the array consisting of the two points
   * @return         the point closest to the end
   */
  public Point getFurthestPoint(Point[] points) {
    if (points.length == 1) {
      return points[0];
    }
    firstDist = B.distToPoint(points[0]);
    secondDist = B.distToPoint(points[1]);
    return points[firstDist > secondDist ? 1 : 0];
  }


  /* Overload of getFurthestPoint (previous) for convenience
   * @param pointA   the first point
   * @param pointB   the second point
   * @return         the point closest to the end
   */
  public Point getFurthestPoint(Point pointA, Point pointB) {
    firstDist = B.distToPoint(pointA);
    secondDist = B.distToPoint(pointB);
    return (firstDist > secondDist ? pointB : pointA);
  }


  /* Calculates the distance between a given point and the end of the line
   * @param p    a point on this line
   * @return     the distance from point p to the end of the line
   */
  public double distToEnd(Point p) {
    return B.distToPoint(p);
  }

}
