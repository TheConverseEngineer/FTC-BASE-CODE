package UPDATED.src;

public static class ROBOT_DATA
{
  
  // Hardware map names
  static const String LEFT_FRONT_NAME = "leftFrontDrive";
  static const String = "rightFrontDrive";
  static const String LEFT_REAR_NAME = "leftRearDrive";
  static const String RIGHT_REAR_NAME = "rightRearDrive";
  
  // Drivetrain inversions
  static const bool[] MOTOR_INVERTED = {false, true, false, true};
  
  // Encoder offsets
  static int m_LEFT_ENCODER_OFFSET = 0;
  static int m_RIGHT_ENCODER_OFFSET = 0;
  static int m_BACK_ENCODER_OFFSET = 0;
  
  // Encoder positioning
  static const double TRACK_WIDTH = 5;   // Distance between the 2 vertical encoders
  
  // Robot positioning
  static double m_X = 0;
  static double m_Y = 0;
  static double m_THETA = 0;  // This value will only be between 0 and 2pi.
  
  // Drive to point tuning values
  static const DRIVE_TO_POINT_TURN_TUNE = 1;
  

  
  
}
