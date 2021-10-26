
public abstract class Drive {
  
  protected Localizer localizer;  
  private double headingOffset = 0d;
  protected double rawGlobalHeading;
  
  public double getExternalHeading () {
    return Angle.normalize(rawGlobalHeading + headingOffset);
  }
  
  public void setHeading (double heading) {
    headingOffset = -rawGlobalHeading + heading;
  }
  
  public Pose2d getEstimatedPose() {
   return localizer.estimatedPose; 
  }
  
  public void setEstimatedPose(Pose2d pose) {
   localizer.estimatedPose = pose;
  }
  
  public Pose2d getEstimatedVelocity() {
    return localizer.estimatedVelocity;
  }
  
  public void updatePoseEstimate() {
    localizer.update(); 
  }
  
  
}
