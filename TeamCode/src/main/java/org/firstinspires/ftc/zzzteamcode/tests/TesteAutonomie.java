/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
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


public class TesteAutonomie extends LinearOpMode {

    public SampleMecanumDrive drive;
    public Glisiere gl;
    public Servos sv;
    public ColorSensor color;

    public int j1 = 10, j2 = 1200, j3 = 2790, gSpot = 1020;

    public ArmServoPosition pozitieNormala = new ArmServoPosition(0, 0.42, 0.32);
    public ArmServoPosition pgSpot = new ArmServoPosition(1, 0.68, 1);

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
        gl.glDr.setPower(0.7);
        gl.glSt.setPower(0.7);
        while(gl.glSt.isBusy() && opModeIsActive())
            if(color.red() > 200 || color.blue() > 200){

                gl.glSt.setPower(0);
                gl.glDr.setPower(0);

                gl.glSt.setTargetPosition(gSpot);
                gl.glDr.setTargetPosition(gSpot);

                sv.Cl.setPosition(1);

                break;

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

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        TrajectorySequence chestie = drive.trajectorySequenceBuilder(startPose)
                .setReversed((false))
                .lineTo(new Vector2d(-25, -37))
                .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
                    gl.glisiera.setTargetPosition(j3);
                    gl.glisiera.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(-1, () -> {
                    sv.bSlv.setPosition(0.018);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.05, () -> {
                    sv.Slv.setPosition(0);
                })

                .UNSTABLE_addTemporalMarkerOffset(1, () -> {
                    sv.bSlv.setPosition(0.4);
                })

                .splineTo(new Vector2d(-4.5,-31.5), Math.toRadians(42))
                .setReversed(true)
                .waitSeconds(3)
                .UNSTABLE_addTemporalMarkerOffset(2.3, () -> {
                    posCleste(p1, sv);

                })

                */
/*
                servos.bSlv.setPosition(0);
            sleep(500);

            posCleste(pozitieNormala, servos);

            sleep(500);
            servos.bSlv.setPosition(1);
                 *//*


                .UNSTABLE_addTemporalMarkerOffset(2, () -> {
                    gl.glisiera.setTargetPosition(10);
                    gl.glisiera.setPower(1);
                })



                .splineTo(new Vector2d(-12.25, -43.5), Math.toRadians(-90))

                .UNSTABLE_addTemporalMarkerOffset( 0.2, () -> {
                    grab(p1);


                })


                */
/*.UNSTABLE_addTemporalMarkerOffset(3.5, () -> {
                    gl.glSt.setTargetPosition(gSpot);
                    gl.glDr.setTargetPosition(gSpot);
                    gl.glDr.setPower(1);
                    gl.glSt.setPower(1);
                    posCleste(pgSpot, sv);
                })*//*

                */
/*.UNSTABLE_addTemporalMarkerOffset(5.5  , () -> {
                    sv.Cl.setPosition(0.4);
                })*//*

                .waitSeconds(5)

                //.setReversed(true)
                //.splineTo(new Vector2d(43, -12), Math.toRadians(0))
                //.setReversed(false)
                //.splineTo(new Vector2d(30, -5), Math.toRadians(45))
                //.setReversed(true)
                //.splineTo(new Vector2d(43, -12), Math.toRadians(0))
                //.setReversed(false)
                //.splineTo(new Vector2d(30, -5), Math.toRadians(45))
                .build();

        Trajectory test = drive.trajectoryBuilder(startPose)
                .forward(20)
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
}
*/
