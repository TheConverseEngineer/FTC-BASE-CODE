package org.firstinspires.ftc.teamcode.team7786;

public class ROBOT_DATA
{


  /****************************** CONSTANTS ******************************/
  // Hardware map names
  public static final String LEFT_FRONT_NAME = "leftFrontDrive";
  public static final String RIGHT_FRONT_NAME= "rightFrontDrive";
  public static final String LEFT_REAR_NAME = "leftRearDrive";
  public static final String RIGHT_REAR_NAME = "rightRearDrive";

  // Drivetrain inversions
  public static final boolean[] MOTOR_INVERTED = {false, true, false, true};

  // Encoder positioning and info
  public static final double COUNTS_PER_ROTATIONS = 2048d;
  public static final double DEAD_WHEEL_CIRCUMFRENCE_INCHES = 2.5d;
  public static final double TRACK_WIDTH_INCHES = 10d;                                                    // Distance between the 2 vertical encoders
  public static final double COUNTS_PER_INCH = COUNTS_PER_ROTATIONS * DEAD_WHEEL_CIRCUMFRENCE_INCHES;      // DO NOT EDIT
  public static final double TRACK_WIDTH = COUNTS_PER_INCH * TRACK_WIDTH_INCHES;                          // DO NOT EDIT


  // Drive to point ad trajectory tuning values
  public static final double DRIVE_TO_POINT_TURN_TUNE = 1d;
  public static final double DEFAULT_LINE_FOLLOW_RANGE_INCHES = 5d;
  public static final double ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES = 0.5d;
  public static final double DEFAULT_LINE_FOLLOW_RANGE = DEFAULT_LINE_FOLLOW_RANGE_INCHES * COUNTS_PER_INCH;               // DO NOT EDIT
  public static final double ABSOLUTE_TRAJECTORY_REACH_RANGE = ABSOLUTE_TRAJECTORY_REACH_RANGE_INCHES * COUNTS_PER_INCH;   // DO NOT EDIT



  /****************************** STATES - DO NOT EDIT ******************************/

  // Robot positioning
  public static double m_X = 0d;
  public static double m_Y = 0d;
  public static double m_THETA = 0d;  // This value will only be between 0 and 2pi.

  // Encoder offsets
  public static int m_LEFT_ENCODER_OFFSET = 0;
  public static int m_RIGHT_ENCODER_OFFSET = 0;
  public static int m_BACK_ENCODER_OFFSET = 0;



}
