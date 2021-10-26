package org.firstinspires.ftc.teamcode.team7786.drive;

abstract class MecanumDrive {
  
  public MecanumDrive(double kV, double kA, double kStatic, double trackWidth, double lateralMultiplier) {
    
  }
  
  public class MecanumLocalizer implements Localizer {
    
    public MecanumLocalizer(MecanumDrive drive, boolean useExternalHeading) {
      
    } 
    
    public Pose2d estimatePosition() {
      
    }
    
    public Pose2d estimateVelocity() {
      
    }
    
    public void update() {
      
    }
  }
  
}
