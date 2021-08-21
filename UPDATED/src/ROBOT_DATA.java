package UPDATED.src;

public static class ROBOT_DATA
{
  
  
  /****************************** CONSTANTS ******************************/
  // Hardware map names
  static const String LEFT_FRONT_NAME = "leftFrontDrive";
  static const String = "rightFrontDrive";
  static const String LEFT_REAR_NAME = "leftRearDrive";
  static const String RIGHT_REAR_NAME = "rightRearDrive";
  
  // Drivetrain inversions
  static const bool[] MOTOR_INVERTED = {false, true, false, true};
  
  // Encoder positioning and info
  static const double COUNTS_PER_ROTATIONS = 2048;
  static const double TRACK_WIDTH_INCHES = 10;                                  // Distance between the 2 vertical encoders
  static const double TRACK_WIDTH = COUNTS_PER_ROTATION * TRACK_WIDTH_INCHES;   // DO NOT EDIT
  
  // Drive to point tuning values
  static const DRIVE_TO_POINT_TURN_TUNE = 1;
  
  /****************************** STATES - DO NOT EDIT ******************************/
  
  // Robot positioning
  static double m_X = 0;
  static double m_Y = 0;
  static double m_THETA = 0;  // This value will only be between 0 and 2pi.
  
  // Encoder offsets
  static int m_LEFT_ENCODER_OFFSET = 0;
  static int m_RIGHT_ENCODER_OFFSET = 0;
  static int m_BACK_ENCODER_OFFSET = 0;

  
  
}
