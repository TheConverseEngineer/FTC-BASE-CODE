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
  
  // The Vuforia license key
  private final String LICENSE_NAME = "PUT THE VUFORIA KEY HERE";
  
  // The webcam
  private WebcamName webcam;
  /**
   * Constructer for class VuforiaCam
   * @param hwMap            the hardware map
   * @param _webcamName              which camera to use
   */
  public VuforiaCam (HardwareMap hwMap, String _webcamName) {
    initVuforia(hwMap, _webcamName);
  }
    
  
  /** Initialize the vuforia camera
   * @param hwMap            the hardware map
   * @param _webcamName              which camera to use
   */
  private void initVuforia(HardwareMap hwMap, String _webcamName) {
    // Create parameters
    int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    webcam = hardwareMap.get(WebcamName.class, _webcamName);
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    parameters.vuforiaLicenseKey = LICENSE_NAME;
    parameters.cameraName = webcam;
    
    // Create vuforia
    vuforia = ClassFactory.createVuforiaLocalizer(params);
    
    // Set defaults
    Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
    vuforia.setFrameQueueCapacity(4);
  } 
  
  private Mat getCvMat() {
    VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take(); //takes the frame at the head of the queue
    long numImages = frame.getNumImages();

    for (int i = 0; i < numImages; i++) {
      if (frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
        rgb = frame.getImage(i);
        break;
      }
    }

    /*rgb is now the Image object that weve used in the video*/
    Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
    bm.copyPixelsFromBuffer(rgb.getPixels());

    //put the image into a MAT for OpenCV
    Mat tmp = new Mat(rgb.getWidth(), rgb.getHeight(), CvType.CV_8UC4);
    Utils.bitmapToMat(bm, tmp);

    //close the frame, prevents memory leaks and crashing
    frame.close();
    return tmp;
  }
}
