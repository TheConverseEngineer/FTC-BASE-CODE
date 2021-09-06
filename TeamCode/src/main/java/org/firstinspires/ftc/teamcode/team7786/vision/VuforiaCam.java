package org.firstinspires.ftc.teamcode.team7786.vision;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class VuforiaCam
{
  // The vuforia instance
  public VuforiaLocalizer vuforia;
  
  // Some year-specific constants
  private final int NUM_TRACKABLES = 3;
  private final String VUMARK_NAME = "PUT THE VUMARK ASSET NAME (RELEASED AT KICK-OFF) HERE";
  private final String[] TRACKABLE_NAMES = new String[]{"Generic item 1", "Generic item 2", "Generic item 3"};
  
  // The trackables and target instance
  private VuforiaTrackables vuforiaAssetRegister;
  private List<VuforiaTrackable> trackables = new ArrayList<VuforiaTrackable>();
  
  // The Vuforia license key
  private final String LICENSE_NAME = "PUT THE VUFORIA KEY HERE";
  
  
  
    
  /**
   * Constructer for class CvCam. Sets camera to back.
   * @param hwMap     the hardware map
   */
  public CvCam (HardwareMap hwMap) {
    CvCam(hwMap, VuforiaLocalizer.CameraDirection.BACK);
  }
  
  /**
   * Constructer for class CvCam
   * @param hwMap            the hardware map
   * @param dir              which camera to use
   */
  public CvCam (HardwareMap hwMap, VuforiaLocalizer.CameraDirection dir)) {
    initVuforia(hwMap, dir, numTrackables);
  }
    
  
  /** Initialize the vuforia camera
   * @param hwMap            the hardware map
   * @param dir              which camera to use
   */
  private void initVuforia(HardwareMap hwMap, VuforiaLocalizer.CameraDirection dir) {
    // Create parameters
    int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    parameters.vuforiaLicenseKey = LICENSE_NAME;
    parameters.cameraDirection = dir;
    
    // Create vuforia
    vuforia = ClassFactory.createVuforiaLocalizer(params);
    
    // Set defaults
    Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
    vuforia.setFrameQueueCapacity(4);
    
    // Set reference files
    vuforiaAssetRegister = vuforia.loadTrackablesFromAsset(VUMARK_NAME);
    for (int i = 0; i < numTrackables; i++) {
      VuforiaTrackable currentItem = vuforiaAssetRegister.get(i);
      currentItem.setName(TRACKABLE_NAMES[i]);
      trackables.add(currentItem);
    }
    
  }
  
  
  
}
