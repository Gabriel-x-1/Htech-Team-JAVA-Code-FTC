/*
package org.firstinspires.ftc.zzzteamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.camera.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

import java.util.ArrayList;

@Autonomous(preselectTeleOp = "SampleTeleOp")
@Config

public class AutoFinal extends LinearOpMode {
    
    public Servos servos;
    public SampleMecanumDrive drive;
    public Glisiere glisiere;
    public ColorSensor color;

    ArmServoPosition p1 = new ArmServoPosition(0, 0.7, 0.50);
    ArmServoPosition p2 = new ArmServoPosition(0, 0.6, 0.45);
    ArmServoPosition p3 = new ArmServoPosition(0, 0.53, 0.40);
    ArmServoPosition p4 = new ArmServoPosition(0, 0.48, 0.34);
    ArmServoPosition pozitieNormala = new ArmServoPosition(0, 0.38, 0.25);
    ArmServoPosition p6 = new ArmServoPosition(0, 0, 0);
    ArmServoPosition p7 = new ArmServoPosition(1, 0.36, 0.80); //pozitie gspot

    ArmServoPosition p12 = new ArmServoPosition(0, 0.66, 0.54);
    ArmServoPosition p22 = new ArmServoPosition(0, 0.62, 0.50);
    ArmServoPosition p32 = new ArmServoPosition(0, 0.54, 0.43);
    ArmServoPosition p42 = new ArmServoPosition(0, 0.50, 0.38);
    ArmServoPosition pNormala = new ArmServoPosition(0, 0.42, 0.30);

    ArmServoPosition pos90deg = new ArmServoPosition(1, 0.36, 0.60);

    Pose2d startPose = new Pose2d(36, 60, 0); //!!! pozitie autonomie

    public void posCleste(ArmServoPosition position, Servos servo) {
        servo.Cl.setPosition(position.posCl); // inchide deschide clestele
        servo.bCl.setPosition(position.posbCl); // unghiul orientarii clestelui
        servo.zCl.setPosition(position.poszCl); // bratul clestelui
    }

    public float gradeRotire = 34f;
    public int sleepTime = 225;
    public static int t1 = 58, t2 = 3, t3 = 0;
    public int j1 = 10, j2 = 1100, j3 = 2840, gSpot = 870; // poz junctionuri (glisiera principala)
    ArmServoPosition pozitieCleste90grade = new ArmServoPosition(0, 0.36, 0.60);

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
        sleep(sleepTime + 100);//edit important
        servos.Slv.setPosition(0);
        sleep(sleepTime);
        //posCleste(pozitieCleste90grade, servos);
        servos.bSlv.setPosition(1); //lasa sleeve

        */
/*
        glisiere.glSt.setTargetPosition(20);
        glisiere.glDr.setTargetPosition(20);
        glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glSt.setPower(1);
        glisiere.glDr.setPower(1);//test
        *//*


        glisiere.glisiera.setTargetPosition(j1);
        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glisiera.setPower(1);

    }

    public void ridGl(int poz) {

        glisiere.glisiera.setTargetPosition(poz);
        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glisiera.setPower(1);
        while (glisiere.glisiera.isBusy()) {
            if (poz - glisiere.glisiera.getCurrentPosition() < 1000) {
                servos.bSlv.setPosition(0.018);
            }
        }

    }


    public void ciclu(ArmServoPosition position){
        while(opModeIsActive()){

            posCleste(position, servos);

            glisiere.glSt.setTargetPosition(1500);
            glisiere.glDr.setTargetPosition(1500);

            glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            glisiere.glSt.setPower(0.4);
            glisiere.glDr.setPower(0.4);

            if(glisiere.glDr.isBusy()) {
                if (color.red() > 200 || color.blue() > 200) {
                    glisiere.glDr.setPower(0);
                    glisiere.glSt.setPower(0);

                    servos.Cl.setPosition(1);
                    sleep(sleepTime);

                    servos.bCl.setPosition(0.7);
                    sleep(sleepTime-100);

                    telemetry.addData("Debug", "Debug");

                    glisiere.glDr.setTargetPosition(gSpot);
                    glisiere.glSt.setTargetPosition(gSpot);

                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    glisiere.glSt.setPower(0.4);
                    glisiere.glDr.setPower(0.4);
                    while (glisiere.glSt.isBusy())
                        idle();

                    if(servos.bSlv.getPosition() != 1)
                    {
                        servos.bSlv.setPosition(1);    //lasa sleeve
                        sleep(sleepTime);
                    }

                    servos.Slv.setPosition(0);//da piedica deoparte
                    sleep(sleepTime);
                    posCleste(p7, servos);//ridica cleste
                    sleep(sleepTime);
                    servos.Cl.setPosition(0);//da drumul la con
                    sleep(sleepTime+100);
                    servos.Slv.setPosition(1);//pune piedica
                    //posCleste(pozitieNormala,servos);
                    posCleste(pozitieCleste90grade, servos);
                    //nu stiu

                    */
/*
                    glisiere.glSt.setTargetPosition(gSpot+250);
                    glisiere.glDr.setTargetPosition(gSpot+250);

                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    glisiere.glSt.setPower(0.8);
                    glisiere.glDr.setPower(0.8);
                    *//*


                    sleep(sleepTime+100);
                    //servos.bSlv.setPosition(0.5);//ridica sleeve modif
                    //sleep(500);

                    glisiere.glSt.setTargetPosition(500); //270
                    glisiere.glDr.setTargetPosition(500);
                    glisiere.glSt.setPower(0.4);
                    glisiere.glDr.setPower(0.4);

                    servos.Slv.setPosition(0);//lasa piedica jos
                    sleep(sleepTime);
                    //servos.bSlv.setPosition(1);//sleeve inapoi modif
                    sleep(sleepTime);
                    servos.Slv.setPosition(1);

                    telemetry.addData("Ciclu", position.getPosbCl());
                    telemetry.update();

                    break;

                }
            }


            telemetry.addData("Rosu", color.red());
            telemetry.addData("Albastru", color.blue());
            telemetry.addData("motor", glisiere.glDr.isBusy());
            telemetry.addData("pos", glisiere.glDr.getCurrentPosition());
            telemetry.update();

        }
    }


    OpenCvWebcam camera;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    double FEET_PER_METER = 3.28084;

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

    @Override
    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
                dashboard.startCameraStream(camera, 120);
            }

            @Override
            public void onError(int errorCode) {

            }

            //@Override
        });

        servos = new Servos(hardwareMap);
        glisiere = new Glisiere(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);
        color = this.hardwareMap.get(ColorSensor.class, "cSensor");



        Trajectory tr1 = drive.trajectoryBuilder(startPose)
                .forward(60)
                .build();

        Trajectory tr2 = drive.trajectoryBuilder(tr1.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
                .forward(4)//era 3
                .build();

        Trajectory tr3 = drive.trajectoryBuilder(tr2.end())
                .back(16)
                .build();

        Trajectory tr4 = drive.trajectoryBuilder(tr3.end())
                .forward(16)
                .build();

        Trajectory tr1_5 = drive.trajectoryBuilder(startPose)
                .forward(29)
                .build();

        Trajectory tr6 = drive.trajectoryBuilder(tr1.end().plus(new Pose2d(0, 0, Math.toRadians(90))))
                .back(4)//era 3
                .build();

        telemetry.setMsTransmissionInterval(50);

        //NU STIM CE INSEAMNA CODUL DE MAI SUS
        //NU SCHIMBA NIMIC

        */
/*
         * The INIT-loop:
         * This REPLACES waitForStart!
         *//*


        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if (currentDetections.size() != 0) {
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


        drive.followTrajectory(tr1);
        //init
        servos.bSlv.setPosition(0.018);
        sleep(sleepTime + 100);
        sleep(sleepTime + 100);
        posCleste(pozitieNormala, servos);
        sleep(sleepTime + 100);
        servos.bSlv.setPosition(1);
        sleep(sleepTime + 100);

        drive.turn(Math.toRadians(90));
        drive.followTrajectory(tr2);
        //sleep(200); nuj daca merge
        junction(j3); //preloaded

        drive.followTrajectory(tr3);
        drive.turn(Math.toRadians(-gradeRotire));
        ciclu(p1);
        drive.turn(Math.toRadians(gradeRotire));
        drive.followTrajectory(tr4);
        junction(j3);
        drive.followTrajectory(tr3);
        drive.turn(Math.toRadians(-gradeRotire));


        ciclu(p2);
        drive.followTrajectory(tr6);


        while (opModeIsActive()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -(Math.pow(gamepad1.left_stick_y, 3)),
                            -(Math.pow(gamepad1.left_stick_x, 3)),
                            -(Math.pow(gamepad1.right_trigger - gamepad1.left_trigger, 3))
                    )
            );

            if (tagOfInterest == null) {
                telemetry.addLine(String.format("\nNot detected"));
                telemetry.update();
                */
/*
                 * Insert your autonomous code here, presumably running some default configuration
                 * since the tag was never sighted during INIT
                 *//*

            } else {
                */
/*
                 * Insert your autonomous code here, probably using the tag pose to decide your configuration.
                 *//*


                // e.g.
                if (tagOfInterest.id == 1) {
                    telemetry.addLine(String.format("\nDetected 1"));
                    telemetry.update();
                    // do something
                } else if (tagOfInterest.id == 2) {
                    telemetry.addLine(String.format("\nDetected 2"));
                    telemetry.update();
                    // do something else
                } else if (tagOfInterest.id == 3) {
                    telemetry.addLine(String.format("\nDetected 3"));
                    telemetry.update();
                    // do something else
                }
            }


            */
/* You wouldn't have this in your autonomous, this is just to prevent the sample from ending *//*

            while (opModeIsActive()) {
                sleep(20);
                telemetry.addLine(String.format("\nDetected tag ID=%d", tagOfInterest.id));
                telemetry.update();
            }
        }
    }
}*/
