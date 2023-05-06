package org.firstinspires.ftc.teamcode.camera;

import com.acmerobotics.dashboard.FtcDashboard;

import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;


public class CameraDetector
{
    public enum Result
    {
        NONE,
        LEFT,
        MID,
        RIGHT
    }
    
    public OpenCvWebcam camera;

    public CameraDetector(OpenCvWebcam camera)
    {
        this.camera = camera;

        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(640, 480, OpenCvCameraRotation.SIDEWAYS_RIGHT);
                dashboard.startCameraStream(camera, 120);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
    }
    
    FtcDashboard dashboard = FtcDashboard.getInstance();
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // IDs of sleves

    int Left = 1, Middle = 2, Right = 3;

    AprilTagDetection tagOfInterest = null;

    public Result detect()
    {

//         * The INIT-loop:
//         * This REPLACES waitForStart!
        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

        if (currentDetections.size() != 0)
        {
            boolean tagFound = false;

            for (AprilTagDetection tag : currentDetections)
            {
                if (tag.id == Right || tag.id == Middle || tag.id == Left)
                {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }

            if (tagFound)
            {
                return returnTag(tagOfInterest);
            }
            else
            {
                if (tagOfInterest == null)
                {
                    return Result.NONE;
                }
                else
                {
                    return returnTag(tagOfInterest);
                }
            }

        }
        else
        {
            if (tagOfInterest == null)
            {
                return Result.NONE;
            }
            else
            {
                return returnTag(tagOfInterest);
            }

        }

    }

    public void stop()
    {
        dashboard.stopCameraStream();
        camera.stopStreaming();
        camera.closeCameraDevice();
    }
    
    Result returnTag(AprilTagDetection detection)
    {
        if (detection.id == Left) return Result.LEFT;
        if (detection.id == Middle) return Result.MID;
        if (detection.id == Right) return Result.RIGHT;
        return Result.NONE;
    }
}
