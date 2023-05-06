//package org.firstinspires.ftc.zzzteamcode.opmodes;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.roadrunner.trajectorysequence.TrajectorySequence;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.camera.CameraDetector;
//import org.firstinspires.ftc.teamcode.hardware.Hardware;
//import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
//import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.ArmServoPosition;
//import org.firstinspires.ftc.zzzteamcode.configs.Servos;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//
//@Autonomous(preselectTeleOp = "SampleTeleOp2")
//public class MainAuto extends LinearOpMode
//{
//    public SampleMecanumDrive drive;
//    public Hardware hardware;
//    public CameraDetector camera;
//
//    public static Pose2d START_POSE;
//
//    @Override
//    public void runOpMode()
//    {
//        TrajectorySequence chestie = drive.trajectorySequenceBuilder(startPose)
//                .setReversed((false))
//                .lineTo(new Vector2d(-25, -37))
//                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
//                    gl.glisiera.setTargetPosition(j3);
//                    gl.glisiera.setPower(1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-1, () -> {
//                    sv.bSlv.setPosition(0.018);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-0.05, () -> {
//                    sv.Slv.setPosition(0);
//                })
//
//                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
//                    sv.bSlv.setPosition(0.4);
//                })
//
//                .splineTo(new Vector2d(-4.5,-31.5), Math.toRadians(42))
//                .setReversed(true)
//                .waitSeconds(3)
//                .UNSTABLE_addTemporalMarkerOffset(2.3, () -> {
//                    posCleste(p1, sv);
//
//                })
//
///*
//                servos.bSlv.setPosition(0);
//            sleep(500);
//
//            posCleste(pozitieNormala, servos);
//
//            sleep(500);
//            servos.bSlv.setPosition(1);
//                 *//*
//
//
//                .UNSTABLE_addTemporalMarkerOffset(2, () -> {
//                    gl.glisiera.setTargetPosition(10);
//                    gl.glisiera.setPower(1);
//                })
//
//
//
//                .splineTo(new Vector2d(-12.25, -43.5), Math.toRadians(-90))
//
//                .UNSTABLE_addTemporalMarkerOffset( 0.2, () -> {
//                    grab(p1);
//
//
//                })
//
//
//                */
///*.UNSTABLE_addTemporalMarkerOffset(3.5, () -> {
//                    gl.glSt.setTargetPosition(gSpot);
//                    gl.glDr.setTargetPosition(gSpot);
//                    gl.glDr.setPower(1);
//                    gl.glSt.setPower(1);
//                    posCleste(pgSpot, sv);
//                })*//*
//
//     */
///*.UNSTABLE_addTemporalMarkerOffset(5.5  , () -> {
//                    sv.Cl.setPosition(0.4);
//                })*//*
//
//                .waitSeconds(5)
//
//                //.setReversed(true)
//                //.splineTo(new Vector2d(43, -12), Math.toRadians(0))
//                //.setReversed(false)
//                //.splineTo(new Vector2d(30, -5), Math.toRadians(45))
//                //.setReversed(true)
//                //.splineTo(new Vector2d(43, -12), Math.toRadians(0))
//                //.setReversed(false)
//                //.splineTo(new Vector2d(30, -5), Math.toRadians(45))
//                .build();
//
//        Trajectory test = drive.trajectoryBuilder(startPose)
//                .forward(20)
//                .build();
//        waitForStart();
//
//        drive.followTrajectorySequence(chestie);
//
//        while (opModeIsActive()) {
//            telemetry.addData("red", color.red());
//            telemetry.addData("blue", color.blue());
//            telemetry.update();
//        }
//        //drive.followTrajectory(test);
//
//        /* BABI OLD CODE
//
//        drive.setPoseEstimate(START_POSE);
//
//        Trajectory trstart = drive.trajectoryBuilder(START_POSE)
//                .forward(58)
//                .build();
//
//        Trajectory tr1 = drive.trajectoryBuilder(trstart.end().plus(new Pose2d(0, 0, Math.toRadians(-90))))
//                .forward(5)
//                .build();
//
//        Trajectory trMinus1 = drive.trajectoryBuilder(tr1.end())
//                .back(5)
//                .build();
//
//        Trajectory trpoz2 = drive.trajectoryBuilder(trMinus1.end())
//                .strafeRight(14)
//                .build();
//
//        Trajectory trpoz1 = drive.trajectoryBuilder(trpoz2.end())
//                .back(23.5)
//                .build();
//
//        Trajectory trpoz3 = drive.trajectoryBuilder(trpoz2.end())
//                .forward(23.5)
//                .build();
//
//        Trajectory trx = drive.trajectoryBuilder(trpoz1.end().plus(new Pose2d(0, 0, Math.toRadians(180))))
//                .forward(4)
//                .build();
//
//        CameraDetector.Randomizer randomizer = camera.detect();
//
//        waitForStart();
//
//        drive.followTrajectory(trstart);
//        drive.turn(Math.toRadians(-90));
//        drive.followTrajectory(tr1);
//        junction(j3);
//        drive.followTrajectory(trMinus1);
//        drive.followTrajectory(trpoz2);
//
//        if (tagOfInterest != null)
//        {
//            if (tagOfInterest.id == 1)
//            {
//                drive.followTrajectory(trpoz1); // left
//                drive.turn(Math.toRadians(180));
//                drive.followTrajectory(trx);
//
//
//                if (tagOfInterest.id == 3)
//                {
//                    drive.followTrajectory(trpoz3); // right
//                }
//            }
//
//        }
//
//
//        drive.turn(Math.toRadians(90));
//        servos.bSlv.setPosition(0);
//        sleep(500);
//
//        posCleste(pozitieNormala, servos);
//
//        sleep(500);
//        servos.bSlv.setPosition(1);
//
//         */
////    }
////}
//
//
