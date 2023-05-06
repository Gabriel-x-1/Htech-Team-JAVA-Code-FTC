//package org.firstinspires.ftc.zzzteamcode.tests;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.hardware.Hardware;
//import org.firstinspires.ftc.teamcode.storage.cPos;
//
//public class DoamneAjutaFieldCentric extends LinearOpMode
//{
//
//
//    public SampleMecanumDrive drive;
//    public Hardware hardware;
//
//    @Override
//
//    public void runOpMode(){
//
//        drive = new SampleMecanumDrive(hardwareMap);
//        hardware = new Hardware(hardwareMap);
//
//        drive.setPoseEstimate(cPos.posEndAuto);
//
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//
//        waitForStart();
//
//        while (opModeIsActive()){
//
//            drive.update();
//
//            Pose2d currPos = drive.getPoseEstimate();
//
//            telemetry.addData("X", currPos.getX());
//            telemetry.addData("Y", currPos.getY());
//            telemetry.addData("Heading", currPos.getHeading());
//
//            drive.setWeightedDrivePower(
//                    new Pose2d(
//                            -(Math.pow(gamepad1.left_stick_y, SampleMecanumDrive.speedPow) * SampleMecanumDrive.speedMultiplier),
//                            -(Math.pow(gamepad1.left_stick_x, SampleMecanumDrive.speedPow) * SampleMecanumDrive.speedMultiplier),
//                            -(Math.pow(gamepad1.right_trigger - gamepad1.left_trigger, SampleMecanumDrive.speedPow) * SampleMecanumDrive.speedMultiplier)
//                    )
//            );
//
//        }
//
//    }
//
//}
