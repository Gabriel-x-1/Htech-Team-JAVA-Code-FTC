/*
package org.firstinspires.ftc.zzzteamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.camera.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

*/
/*

    DE CAIET

    Clasa importata pentru camera, in perioada de initializare detecteaza april tag-ul din fata robotului

 *//*



@Autonomous
public class AutonomieFAIL extends LinearOpMode
{
    SampleMecanumDrive drive;
    OpenCvWebcam camera;
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

    Pose2d startPose = new Pose2d(36, 60, 0); //!!! pozitie autonomie

    // IDs of sleves

    int Left = 1, Middle = 2, Right = 3;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {

        drive = new SampleMecanumDrive(hardwareMap);

        Trajectory trstart = drive.trajectoryBuilder(startPose)
                .forward(23.5)
                .build();

        Trajectory tr1 = drive.trajectoryBuilder(trstart.end())
                .strafeLeft(23.5)
                .build();

        Trajectory tr3 = drive.trajectoryBuilder(trstart.end())
                .strafeRight(23.5)
                .build();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
                dashboard.startCameraStream(camera, 120);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        //NU STIM CE INSEAMNA CODUL DE MAI SUS
        //NU SCHIMBA NIMIC

        */
/*
         * The INIT-loop:
         * This REPLACES waitForStart!
         *//*


        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == Right || tag.id == Middle || tag.id == Left) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if (tagOfInterest != null) {
                    telemetry.addLine(String.format("\nDetected tag ID=%d", tagOfInterest.id));
                }
            }
            telemetry.update();
            sleep(20);
        }

        */
/*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         *//*


        */
/* Actually do something useful *//*

        if(tagOfInterest == null)
        {
            telemetry.addLine(String.format("\nNot detected"));
            telemetry.update();
            */
/*
             * Insert your autonomous code here, presumably running some default configuration
             * since the tag was never sighted during INIT
             *//*

        }
        else
        {
            drive.followTrajectory(trstart);

            if(tagOfInterest.id == 1)
            {
                drive.followTrajectory(tr1); // left
            }

            if(tagOfInterest.id == 3)
            {
                drive.followTrajectory(tr3); // right
            }
        }


        */
/* You wouldn't have this in your autonomous, this is just to prevent the sample from ending *//*

        while (opModeIsActive()) {sleep(20);}
    }
}*/
