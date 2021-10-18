package org.firstinspires.ftc.teamcode.team7786.vision;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionConstants {

    // Vuforia setup constants
    public static final String VUFORIA_KEY = "PUT VUFORIA KEY HERE";
    public static final VuforiaLocalizer.CameraDirection CAMERA_DIRECTION = VuforiaLocalizer.CameraDirection.BACK;
    public static final boolean EXTENDED_TRACKING_ENABLED = false;

    // OpenCV setup constants
    public static final OpenCvCamera.ViewportRenderer VIEWPORT_RENDERER = OpenCvCamera.ViewportRenderer.GPU_ACCELERATED;
    public static final OpenCvCamera.ViewportRenderingPolicy VIEWPORT_RENDERER_POLICY = OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW;
    public static final OpenCvCameraRotation PHONE_ROTATION = OpenCvCameraRotation.UPRIGHT;
}
