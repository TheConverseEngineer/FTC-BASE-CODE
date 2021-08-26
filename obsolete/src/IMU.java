package src;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/* This code serves as a base class for interpreting the REV IMU
 * To minimize lag, this code does NOT reset the gyro and only fetches angle measures when called.
 * For maximum efficiency, call updateHeading() at the start of every loop iteration
 */
public class IMU
{
  
  private BNO055IMU imu;
  private double offset;
  private double globalHeading;
  private double localHeading;
  public int multiplier;
  
  
  public IMU (HardwareMap hwMap) {
    // Set up the parameters with which we will use for our IMU
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
    parameters.loggingEnabled      = true;
    parameters.loggingTag          = "IMU";
    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    // Retrieve and initialize the IMU
    imu = hwMap.get(BNO055IMU.class, "imu");
    imu.initialize(parameters); 
    
    // Initailize variables
    globalHeading = 0;
    relativeHeading = 0;
    offset = 0;
    multiplier = 1;
  }
  
  /* Flips the heading angle (changes multiplier times -1) */
  public void invertHeading() {
    multiplier = -multiplier;
  }
  
  /* Access the gyro and get the new heading. 
   * RECOMMENDED: CALL THIS METHOD AT THE START OF EACH LOOP ITERATION */
  public void updateHeading() {
    globalHeading =  imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle * multiplier; 
    localHeading = (globalHeading + offset) * multiplier;
  }
  
  /* Returns the heading */
  public double getHeading() {
     return localHeading
  }
  
  /* Returns the heading, ignoring all resets */
  public double getGlobalHeading() {
    return globalHeading * multiplier;
  }
  
  /* Resets the heading */
  public void resetHeading() {
    offset = -localHeading;
  }
}
  
  
