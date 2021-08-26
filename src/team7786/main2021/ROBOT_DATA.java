package team7786.main2021;

public static class ROBOT_DATA
{


  /****************************** CONSTANTS ******************************/
  // Hardware map names
  static final String LEFT_FRONT_NAME = "leftFrontDrive";
  static final String RIGHT_FRONT_NAME= "rightFrontDrive";
  static final String LEFT_REAR_NAME = "leftRearDrive";
  static final String RIGHT_REAR_NAME = "rightRearDrive";

  // Drivetrain inversions
  static final bool[] MOTOR_INVERTED = {false, true, false, true};

  // Encoder positioning and info
  static final double COUNTS_PER_ROTATIONS = 2048d;
  static final double DEAD_WHEEL_CIRCUMFRENCE_INCHES = 2.5d;
  static final double TRACK_WIDTH_INCHES = 10d;                                                    // Distance between the 2 vertical encoders
  static final double COUNTS_PER_INCH = COUNTS_PER_ROTATIONS * DEAD_WHEEL_CIRCUMFRENCE_INCHES;     // DO NOT EDIT
  static final double TRACK_WIDTH = COUNTS_PER_INCH * TRACK_WIDTH_INCHES;                          // DO NOT EDIT


  // Drive to point ad trajectory tuning values
  static final double DRIVE_TO_POINT_TURN_TUNE = 1d;
  static final double DEFAULT_LINE_FOLLOW_RANGE_INCHES = 5d;
  static final double ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES = 0.5d;
  static final double DEFAULT_LINE_FOLLOW_RANGE = DEFAULT_LINE_FOLLOW_RANGE_INCHES * COUNTS_PER_INCH;               // DO NOT EDIT
  static final double ABSOLUTE_TRAJECTORY_REACH_RANGE = ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES * COUNTS_PER_INCH;   // DO NOT EDIT



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
