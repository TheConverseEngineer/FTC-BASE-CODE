package org.firstinspires.ftc.teamcode.team7786.controllers;

public abstract class ArmJointBase {

  /** Sets the target position for this motor
   * @param newTarget  the position (in degrees) that the motor should travel to
   */
  public abstract void toPosition(double newTarget);
  
  /** Returns the current position of the motor in degrees
   * @return  the current motor position in degrees
   */
  public abstract double getPosition();
  
  /** Returns the current position of the motor in encoder counts
   * @return  the current motor position in encoder counts
   */
  public abstract double getPositionRaw();
  
  /** Determins if the current motor position is within the specified bounds
   * @return  if the current motor position is in the specified range
   */
  public abstract boolean atTarget();
  
  /** Normalizes an angle between 0 and 359 (inclusive)
   * @param rawAngle   the angle to normalize
   * @return           the normalized angle
   */
  public double normalizeAngle(double rawAngle) {
    while (rawAngle > 359d) {
      rawAngle = rawAngle - 360;
    }
    while (rawAngle < 0) {
      rawAngle = rawAngle + 360; 
    }
    return rawAngle;
  }
  
  /** Determines if a number is within a set range
   * @param x    the number to test
   * @param min  the minimum value x can be
   * @param max  the maximum value x can be
   * @return     if x is in bounds
   */
  public boolean inBoundedRange(double x, double min, double max) {
    return !(x > max || x < min);
  }
  
  /** Determines if a number is within a set range
   * @param x        the number to test
   * @param center   the ideal value x can be
   * @param max      the maximum amount that x can be off by
   * @return         if x is in bounds
   */
  public boolean inRadialRange(double x, double center, double radius) {
    return !(x > (center + radius) || x < (center - radius));
  }
  
}
