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
  static const double COUNTS_PER_ROTATIONS = 2048d;
  static const double DEAD_WHEEL_CIRCUMFRENCE_INCHES = 2.5d;
  static const double TRACK_WIDTH_INCHES = 10d;                                                    // Distance between the 2 vertical encoders
  static const double COUNTS_PER_INCH = COUNTS_PER_ROTATIONS * DEAD_WHEEL_CIRCUMFRENCE_INCHES      // DO NOT EDIT
  static const double TRACK_WIDTH = COUNTS_PER_INCH * TRACK_WIDTH_INCHES;                          // DO NOT EDIT
  
  
  // Drive to point ad trajectory tuning values
  static const double DRIVE_TO_POINT_TURN_TUNE = 1d;
  static const double DEFAULT_LINE_FOLLOW_RANGE_INCHES = 5d;
  static const double ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES = 0.5d;
  static const double DEFAULT_LINE_FOLLOW_RANGE = DEFAULT_LINE_FOLLOW_RANGE_INCHES * COUNTS_PER_INCH;               // DO NOT EDIT
  static const double ABSOLUTE_TRAJECTORY_REACH_RANGE = ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES * COUNTS_PER_INCH;   // DO NOT EDIT
  
  
  
  /****************************** STATES - DO NOT EDIT ******************************/
  
  // Robot positioning
  static double m_X = 0d;
  static double m_Y = 0d;
  static double m_THETA = 0d;  // This value will only be between 0 and 2pi.
  
  // Encoder offsets
  static int m_LEFT_ENCODER_OFFSET = 0;
  static int m_RIGHT_ENCODER_OFFSET = 0;
  static int m_BACK_ENCODER_OFFSET = 0;

  
  
}
