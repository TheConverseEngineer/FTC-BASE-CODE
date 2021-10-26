
public class Vector2d {
  public double x;
  public double y;
  
  public Vector2d (double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public Vector2d polar() {
    return new Vector2d(Math.hypot(x, y), Math.atan2(x, y));
  }
  
  public Vector2d cartesian() {
    return new Vector2d(x * cos(y), x * sin(y));
  }
  
  public Vector2d add(Vector2d other) {
    return new Vector2d(x + other.x, y + other.y);
  }
  
  public Vector2d minus(Vector2d other) {
    return new Vector2d(x - other.x, y - other.y);
  }
  
  public Vector2d times(double scalar) {
    return new Vector2d(x * scalar, y * scalar);
  }
  
  public Vector2d divide(double scalar) {
    return new Vector2d(x * scalar, y * scalar);
  }
  
  public double dot(Vector2d other) {
    return (x * other.x + y * other.y);
  }
  
  public Vector2d assign(Vector2d other) {
    this.x = other.x;
    this.y = other.y;
  }
  
  public Vector2d translate(Vector2d other) {
    this.assign(this.add(other));
    return this;
  }
  
  public Vector2d translate(double x, double y) {
    this.x += x;
    this.y += y;
    return this;
  }
  
  public Vector2d rotate(double rotation) {
    Vector2d asPolar = this.polar();
    asPolar.x += rotation
    this.assign(asPolar.cartesian());
  }
}
