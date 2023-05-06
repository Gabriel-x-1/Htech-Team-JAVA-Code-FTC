/*
package org.firstinspires.ftc.zzzteamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;
import org.firstinspires.ftc.teamcode.camera.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;

@Autonomous(preselectTeleOp = "SampleTeleOp2")
public class Preload_Parcare_Dreapta extends LinearOpMode
{
    public Servos servos;
    public SampleMecanumDrive drive;
    public Glisiere glisiere;

    ArmServoPosition pozitieNormala = new ArmServoPosition(0, 0.42, 0.32);
    public int j3 = 2840, j1 = 10;
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


    public void junction(int poz) {

        glisiere.glisiera.setTargetPosition(poz);
        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glisiera.setPower(1);
        //posCleste(pozitieCleste90grade, servos);

        while (glisiere.glisiera.getCurrentPosition() < j3) {

            glisiere.glisiera.setTargetPosition(j3);
            if (glisiere.glisiera.getCurrentPosition() > 1900)
                servos.bSlv.setPosition(0.018); //brat sleeve

        }
        sleep(1000);//edit important
        servos.Slv.setPosition(0);
        sleep(1000);
        //posCleste(pozitieCleste90grade, servos);
        servos.bSlv.setPosition(0.6); //lasa sleeve


        glisiere.glisiera.setTargetPosition(j1);
        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glisiera.setPower(1);

    }

    public void posCleste(ArmServoPosition position, Servos servo) {
        servo.Cl.setPosition(position.posCl); // inchide deschide clestele
        servo.bCl.setPosition(position.posbCl); // unghiul orientarii clestelui
        servo.zCl.setPosition(position.poszCl); // bratul clestelui
    }


    @Override
    public void runOpMode()
    {

        servos = new Servos(hardwareMap);
        glisiere = new Glisiere(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);

        Trajectory trstart = drive.trajectoryBuilder(startPose)
                .forward(61)
                .build();

        Trajectory tr1 = drive.trajectoryBuilder(trstart.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
                .forward(5)
                .build();

        Trajectory trMinus1 = drive.trajectoryBuilder(tr1.end())
                .back(5)
                .build();

        Trajectory trpoz2 = drive.trajectoryBuilder(trMinus1.end())
                .strafeLeft( 14)
                .build();

        Trajectory trpoz1 = drive.trajectoryBuilder(trpoz2.end())
                .forward(23.5)
                .build();

        Trajectory trpoz3 = drive.trajectoryBuilder(trpoz2.end())
                .back(23.5)
                .build();

        Trajectory trx = drive.trajectoryBuilder(trpoz1.end().plus(new Pose2d(0, 0, Math.toRadians(180))))
                .forward(4)
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
            drive.turn(Math.toRadians(90));
            drive.followTrajectory(tr1);
            junction(j3);
            drive.followTrajectory(trMinus1);
            drive.followTrajectory(trpoz2);

            if(tagOfInterest.id == 1)
            {
                drive.followTrajectory(trpoz1); // left
            }

            if(tagOfInterest.id == 3)
            {
                drive.followTrajectory(trpoz3); // right
                */
/*
                drive.turn(Math.toRadians(180));
                drive.followTrajectory(trx);

                 *//*

            }

            drive.turn(Math.toRadians(-90));

            servos.bSlv.setPosition(0);
            sleep (500);

            posCleste(pozitieNormala, servos);

            sleep(500);
            servos.bSlv.setPosition(1);


        }


        */
/* You wouldn't have this in your autonomous, this is just to prevent the sample from ending *//*

        while (opModeIsActive()) {sleep(20);}
    }
}*/
