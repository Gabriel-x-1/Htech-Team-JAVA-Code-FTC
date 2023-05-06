/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;
import org.firstinspires.ftc.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous


public class TestAutonomie2 extends LinearOpMode {

    public SampleMecanumDrive drive;
    public Glisiere gl;
    public Servos sv;
    public ColorSensor color;

    public int j1 = 10, j2 = 1200, j3 = 2790, gSpot = 880; //1020

    public ArmServoPosition pozitieNormala = new ArmServoPosition(0, 0.42, 0.32);
    ArmServoPosition pozitieCleste90grade = new ArmServoPosition(0.65, 0.36, 0.60); //pozitie verticala a bratului clestelui
    //public ServoPosition pgSpot = new ServoPosition(1, 0.68, 0.8);
    public ArmServoPosition pgSpot = new ArmServoPosition(0.65, 0.36, 0.80);


    double OpenCl = 0.4, CloseCl = 0.65;

    public ArmServoPosition p1 = new ArmServoPosition(0, 0.68, 0.54);
    public ArmServoPosition p2 = new ArmServoPosition(0, 0.6, 0.48);
    public ArmServoPosition p3 = new ArmServoPosition(0, 0.53, 0.42);
    public ArmServoPosition p4 = new ArmServoPosition(0, 0.48, 0.38);

    public ArmServoPosition poz90 = new ArmServoPosition(1, 1, 0.6);
    Pose2d startPose = new Pose2d(-60, -35.75, Math.toRadians(0));

    public void posCleste(ArmServoPosition position, Servos servo) {
        servo.Cl.setPosition(position.posCl); // inchide deschide clestele
        servo.bCl.setPosition(position.posbCl); // unghiul orientarii clestelui
        servo.zCl.setPosition(position.poszCl); // bratul clestelui
    }

    public void grab(ArmServoPosition pos){

        posCleste(pos, sv);

        sv.bSlv.setPosition(0.8);
        gl.glSt.setTargetPosition(1600);
        gl.glDr.setTargetPosition(1600);
        gl.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        gl.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        gl.glDr.setPower(0.5);
        gl.glSt.setPower(0.5);

        while(gl.glSt.isBusy() && opModeIsActive()) {

            sv.bSlv.setPosition(0.4);

            if (color.red() > 180 || color.blue() > 180) {
                sv.bSlv.setPosition(1);

                gl.glSt.setPower(0);
                gl.glDr.setPower(0);

                */
/*
                gl.glSt.setTargetPosition(gSpot);
                gl.glDr.setTargetPosition(gSpot);
                *//*



                sv.Cl.setPosition(0.65);

                break;
            }
        }
    }

    public void release(){
        */
/*glisiere.glSt.setPower(1);
                glisiere.glDr.setPower(1);

                while(glisiere.glSt.isBusy() && opModeIsActive()) {
                    if (glisiere.glDr.getCurrentPosition() - gSpot < 500)
                        posCleste(pgSpot, servos);

                }
                 *//*

    }

    @Override
    public void runOpMode() {

        drive = new SampleMecanumDrive(hardwareMap);
        gl = new Glisiere(hardwareMap);
        sv = new Servos(hardwareMap);
        color = this.hardwareMap.get(ColorSensor.class, "cSensor");

        gl.glisiera.setTargetPosition(0);
        gl.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        gl.glDr.setTargetPosition(0);
        gl.glSt.setTargetPosition(0);

        gl.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        gl.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Vector2d posBengos = new Vector2d(-5,-30);

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        TrajectorySequence chestie = drive.trajectorySequenceBuilder(startPose)
                .setReversed(false)
                .lineTo(new Vector2d(-25, -37))

                .UNSTABLE_addTemporalMarkerOffset(-2, () -> {
                    gl.glisiera.setTargetPosition(j3);
                    gl.glisiera.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    sv.bSlv.setPosition(0.018);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.005, () -> {
                    sv.Slv.setPosition(0);
                })
                */
/*
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    sv.bSlv.setPosition(0.018); // 0.4
                })
                *//*

                .UNSTABLE_addTemporalMarkerOffset(-0.6, () -> {
                    posCleste(pozitieCleste90grade, sv);
                })
                .splineTo(posBengos, Math.toRadians(42))
                .setReversed(true)
                //.waitSeconds(3)
                */
/*.UNSTABLE_addTemporalMarkerOffset(2, () -> {
                    gl.glisiera.setTargetPosition(10);
                    gl.glisiera.setPower(1);
                })
                *//*

                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    gl.glisiera.setTargetPosition(10);
                    gl.glisiera.setPower(1);
                    sv.bSlv.setPosition(0.4);
                })

                .splineTo(new Vector2d(-12.25, -43.5), Math.toRadians(-90))

//TODO: pus bSlv la 0.6, folosit grab, pus la 1

                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    grab(p1);
                })

                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset( 0.5, () -> {
                    posCleste(pozitieCleste90grade, sv);
                })

                .UNSTABLE_addTemporalMarkerOffset( 0.9, () -> {
                    gl.glSt.setTargetPosition(gSpot);
                    gl.glDr.setTargetPosition(gSpot);
                    gl.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    gl.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    gl.glDr.setPower(1);
                    gl.glSt.setPower(1);

                    sv.Slv.setPosition(0);

                })

                .UNSTABLE_addTemporalMarkerOffset(1.1, () -> {
                    posCleste(pgSpot, sv);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.6, () -> {
                    sv.Cl.setPosition(0.4);
                })

                .UNSTABLE_addTemporalMarkerOffset(2, () -> {
                    posCleste(pozitieCleste90grade, sv);
                    gl.glisiera.setTargetPosition(j3);
                    gl.glisiera.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(2.3, () -> {
                    gl.glSt.setTargetPosition(10);
                    gl.glDr.setTargetPosition(10);
                    gl.glSt.setPower(1);
                    gl.glDr.setPower(1);

                })

                */
/*.UNSTABLE_addTemporalMarkerOffset( 4, () -> {
                    sv.bSlv.setPosition(1);
                    gl.glisiera.setTargetPosition(j3);
                    gl.glisiera.setPower(1);

                    while(gl.glisiera.isBusy()) idle();

                    sv.bSlv.setPosition(0.018); //brat sleeve //edit fix cleste --rares
                    posCleste(pozitieCleste90grade, sv);
                    gl.glSt.setTargetPosition(20);
                    gl.glDr.setTargetPosition(20);
                    gl.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    gl.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    gl.glSt.setPower(1);
                    gl.glDr.setPower(1);

                })*//*


                .waitSeconds(3)

                .setReversed(false)

                */
/*
                .UNSTABLE_addTemporalMarkerOffset(-0.05, () -> {
                    sv.Slv.setPosition(0);
                })
*//*



                .splineTo(posBengos, Math.toRadians(42)) //junction punem con 2

                .setReversed(true)

                .UNSTABLE_addTemporalMarkerOffset(-1, () ->{
                    sv.Slv.setPosition(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.7, () ->{
                    sv.bSlv.setPosition(0.018);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.05, () ->{
                    sv.Slv.setPosition(0);
                })

                .waitSeconds(2)
                .setReversed(true)
                //.waitSeconds(3)
                */
/*.UNSTABLE_addTemporalMarkerOffset(2, () -> {
                    gl.glisiera.setTargetPosition(10);
                    gl.glisiera.setPower(1);
                })
                *//*

                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    gl.glisiera.setTargetPosition(10);
                    gl.glisiera.setPower(1);
                    sv.bSlv.setPosition(0.4);
                })

                //.splineTo(new Vector2d(-12.25, -43.5), Math.toRadians(-90))

                .build();




        waitForStart();

        drive.followTrajectorySequence(chestie);

        while (opModeIsActive()) {
            telemetry.addData("red", color.red());
            telemetry.addData("blue", color.blue());
            telemetry.update();
        }
        //drive.followTrajectory(test);
    }
}*/
