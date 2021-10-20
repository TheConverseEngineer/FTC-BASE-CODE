package org.firstinspires.ftc.teamcode.team7786.vision;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionConstants {

    // Vuforia setup constants
    public static final String VUFORIA_KEY = "AUsksgf/////AAABmWS7Sd6xPE1CgkVwdNx9tVtX7xQ2UfepGBbAPdDZARje2qIleA0Qd1+Zf0APPU0YHKb0/M4Ibfk8zrJVwkEYClMJOSoewtuTCzsVqIvFsJbMJk3fh+uaZ+CpZuVx7ALWIC5/R26vIr9YRfuPfaOUh+QEwxD7EMM2JK3j10Z0fYhaImw2tSAwU7UQIu+RSlwI+5rQ4nZN4eI5GZO73CxZYsUI5jBpLntHDWKMsMw42f80WIC8fJbh3nQfntz547AzfySb21QiS4rJCBeV0oUGCPRaRFMmb3NtNTB551Dm4bBhVTA8PJH2F3itzM7pO7Lawh5fcWRgdosrphZZKV+iXyMKwaznfwbCLAoOepf6Q+px";
    public static final VuforiaLocalizer.CameraDirection CAMERA_DIRECTION = VuforiaLocalizer.CameraDirection.BACK;
    public static final boolean EXTENDED_TRACKING_ENABLED = false;

    // OpenCV setup constants
    public static final OpenCvCamera.ViewportRenderer VIEWPORT_RENDERER = OpenCvCamera.ViewportRenderer.GPU_ACCELERATED;
    public static final OpenCvCamera.ViewportRenderingPolicy VIEWPORT_RENDERER_POLICY = OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW;
    public static final OpenCvCameraRotation PHONE_ROTATION = OpenCvCameraRotation.UPRIGHT;
}
