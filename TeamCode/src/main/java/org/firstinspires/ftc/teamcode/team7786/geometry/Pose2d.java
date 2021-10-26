import java.lang.math;

public class Pose2d {
  public double x;
  public double y;
  public double theta;
  
  public Pose2d (double x, double y, double theta) {
    this.x = x;
    this.y = y;
    this.theta = theta;
  }
  
  public Pose2d plus(Pose2d other) {
    return new Pose2d(x + other.x, y + other.y, theta + other.theta); 
  }
  
  public Pose2d minus(Pose2d other) {
    return new Pose2d(x - other.x, y - other.y, theta - other.theta); 
  }
  
  public Pose2d times(double scalar) {
    return new Pose2d(x * scalar, y * scalar, theta * scalar);
  }
  
  public Pose2d divide(double scalar) {
    return new Pose2d(x / scalar, y / scalar, theta / scalar);
  }
  
  public Pose2d invert() {
    return new Pose2d(-x, -y, -theta);
  }
  
  public void rotatePose(double rotation) {
    theta += rotation;
  }
  
  public String asString() {
    return String.format("(%.3f, %.3f, %.3f°)", x, y, Math.toDegrees(heading)); 
  }
  
  
}
