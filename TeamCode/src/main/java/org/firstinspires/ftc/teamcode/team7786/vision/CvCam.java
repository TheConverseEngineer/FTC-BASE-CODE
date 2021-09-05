package org.firstinspires.ftc.teamcode.team7786.vision;

import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class CvCam
{
  // The vuforia instance
  private VuforiaLocalizer vuforia;
  
  private final String license = "PUT THE VUFORIA KEY HERE";
  
  public CvCam (HardwareMap hwMap) {
    initVuforia(hwMap);
  }
  
  private void initVuforia(HardwareMap hwMap) {
    int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

    parameters.vuforiaLicenseKey = license;
    parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
    vuforia = ClassFactory.createVuforiaLocalizer(parameters);
  }
  
}
