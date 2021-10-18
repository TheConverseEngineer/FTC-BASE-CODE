package org.firstinspires.ftc.teamcode.team7786.vision;

/** All measurements in mm. Conversion provided for convenience*/
public class FieldSetup {

    public static final float MM_PER_INCH = 25.4f;

    public static final float TARGET_HEIGHT = 6 * MM_PER_INCH;

    public static final float HALF_FIELD = 72 * MM_PER_INCH;
    public static final float HALF_TILE = 12 * MM_PER_INCH;
    public static final float ONE_AND_HALF_TILE = 3 * HALF_TILE;

    // For each, state the following: dx, dy, dz, rx, ry, rz
    // Rotations occur around the specified GLOBAL axis
    // Rotations are not simultaneous. rx will go first, followed by ry, followed by rz
    public static final float[] BLUE_STORAGE_TRANSFORM       = new float[]{-HALF_FIELD,  ONE_AND_HALF_TILE, TARGET_HEIGHT, 90, 0, 90};
    public static final float[] BLUE_ALLIANCE_WALL_TRANSFORM = new float[]{HALF_TILE,  HALF_FIELD, TARGET_HEIGHT, 90, 0, 0};
    public static final float[] RED_STORAGE_TRANSFORM        = new float[]{-HALF_FIELD,  -ONE_AND_HALF_TILE, TARGET_HEIGHT, 90, 0, 90};
    public static final float[] RED_ALLIANCE_WALL_TRANSFORM  = new float[]{HALF_TILE,  -HALF_FIELD, TARGET_HEIGHT, 90, 0, 180};

    // Transforms for the camera. Follow the same arrangement as the prior
    // For some bizarre reason, the camera rotation order is ry first, followed by rz, followed by rx
    // Origin should be the center of the robot at ground level
    public static final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * MM_PER_INCH;
    public static final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * MM_PER_INCH;
    public static final float CAMERA_LEFT_DISPLACEMENT     = 0.0f * MM_PER_INCH;
    public static final float PHONE_X_ROTATION             = 0;
    public static final float PHONE_Y_ROTATION             = 0;
    public static final float PHONE_Z_ROTATION             = 0;
}
