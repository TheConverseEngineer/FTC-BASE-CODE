package org.firstinspires.ftc.teamcode.team7786.drive;

import org.firstinspires.ftc.teamcode.team7786.geometry.Pose2d;

public interface Localizer {

    /**
     * Current robot pose estimate.
     */
    public abstract Pose2d estimatePose();

    /**
     * Current robot pose velocity (optional)
     */
    public abstract Pose2d estimateVelocity();

    /**
     * Completes a single localization update.
     */
    public abstract update();
}
