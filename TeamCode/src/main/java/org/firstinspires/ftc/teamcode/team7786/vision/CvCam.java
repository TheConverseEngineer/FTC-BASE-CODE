package org.firstinspires.ftc.teamcode.team7786.vision;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.team7786.vision.VisionConstants.*;
import static org.firstinspires.ftc.teamcode.team7786.vision.FieldSetup.*;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

public class CvCam {

    private VuforiaLocalizer vuforia;

    private VuforiaTrackables targets;

    private OpenCvCamera passthroughCam;
    private OpenCvPipeline currentPipeline;

    private Telemetry telemetry;

    private OpenGLMatrix lastLocation;

    public CvCam (OpenCvPipeline pipeline, HardwareMap hwMap, Telemetry telemetry) {
        currentPipeline = pipeline;
        initOpenCV(hwMap);
        this.telemetry = telemetry;
    }
    private void initOpenCV(HardwareMap hwMap) {
        // Fetch some viewport IDs
        int cameraMoniterViewID = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        int[] viewportContainerIDs = OpenCvCameraFactory.getInstance().splitLayoutForMultipleViewports(cameraMoniterViewID, 2, OpenCvCameraFactory.ViewportSplitMethod.VERTICALLY);

        VuforiaLocalizer.Parameters params = initVuforia(viewportContainerIDs);

        passthroughCam = OpenCvCameraFactory.getInstance().createVuforiaPassthrough(vuforia, params, viewportContainerIDs[1]);

        passthroughCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                passthroughCam.setViewportRenderer(VIEWPORT_RENDERER);
                passthroughCam.setViewportRenderingPolicy(VIEWPORT_RENDERER_POLICY);
                passthroughCam.setPipeline(currentPipeline);

                passthroughCam.startStreaming(0, 0, PHONE_ROTATION);
            }

            @Override
            public void onError(int errorCode) {
                throw new RuntimeException("OpenCV startup failed. Check error code in CvCam class");
            }
        });
    }

    private VuforiaLocalizer.Parameters initVuforia(int[] viewportContainerIDs) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(viewportContainerIDs[0]);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_DIRECTION;
        parameters.useExtendedTracking = EXTENDED_TRACKING_ENABLED;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        targets = vuforia.loadTrackablesFromAsset("FreightFrenzy");

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targets);

        identifyTrackable(0, "Blue Storage", BLUE_STORAGE_TRANSFORM);
        identifyTrackable(1, "Blue Alliance Wall", BLUE_ALLIANCE_WALL_TRANSFORM);
        identifyTrackable(2, "Red Storage", RED_STORAGE_TRANSFORM);
        identifyTrackable(3, "Red Alliance Wall", RED_ALLIANCE_WALL_TRANSFORM);

        OpenGLMatrix robotToCamera = setCameraTransform();

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotToCamera, parameters.cameraDirection);
        }

        return parameters;
    }

    private void identifyTrackable(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
        VuforiaTrackable aTarget = targets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }

    private OpenGLMatrix setCameraTransform () {
        return OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, PHONE_Y_ROTATION, PHONE_Z_ROTATION, PHONE_X_ROTATION));
    }

    private void identifyTrackable(int targetIndex, String targetName, float[] pos) {
        if (pos.length != 6) { throw new RuntimeException("Invalid VuMark Position supplier. Check FieldSetup.java"); }

        identifyTrackable(targetIndex, targetName, pos[0], pos[1], pos[2], pos[3], pos[4], pos[5]);
    }

    private boolean checkForVisible() {
        // check all the trackable targets to see which one (if any) is visible.
        boolean targetVisible = false;
        for (VuforiaTrackable trackable : targets) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since
                // the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }
        return targetVisible;
    }

    private void updatePosition() {
        // express position (translation) of robot in inches.
        VectorF translation = lastLocation.getTranslation();
        telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                translation.get(0) / MM_PER_INCH, translation.get(1) / MM_PER_INCH, translation.get(2) / MM_PER_INCH);

        // express the rotation of the robot in degrees.
        Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
        telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
    }

    public void updateLocalization() {
        if(checkForVisible()) {
            updatePosition();
        } else {
            telemetry.addData("Visible VuMarks:", "none");
        }

        telemetry.addData("Passthrough FPS", passthroughCam.getFps());
        telemetry.addData("Frame count", passthroughCam.getFrameCount());
    }

    public void deactivateVuforiaTracking() {
        targets.deactivate();
    }

    public VuforiaLocalizer getVuforiaInstance() {
        return vuforia;
    }
}
