package org.firstinspires.ftc.teamcode.threads.tests;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.BratCleste;
import org.firstinspires.ftc.teamcode.hardware.Cleste;
import org.firstinspires.ftc.teamcode.hardware.GlisiereOrizontale;
import org.firstinspires.ftc.teamcode.hardware.GlisiereVerticale;
import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

@TeleOp
public class CalibrationOpMode extends LinearOpMode
{
    public SampleMecanumDrive drive;
    public Hardware hardware;

    public State state = State.EMPTY;

    public DcMotorEx motor1, motor2;

    @Override
    public void runOpMode()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new SampleMecanumDrive(hardwareMap);
        hardware = new Hardware(hardwareMap);

        motor1 = this.hardwareMap.get(DcMotorEx.class, "gvs");
        motor2 = this.hardwareMap.get(DcMotorEx.class, "gvd");

        waitForStart();

        while (opModeIsActive())
        {
            if ((gamepad1.start && gamepad1.x) || (gamepad2.start && gamepad2.x)) break;

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -(Math.pow(gamepad1.left_stick_y, SampleMecanumDrive.SPEED_POWER) * SampleMecanumDrive.SPEED_MULTIPLIER),
                            -(Math.pow(gamepad1.left_stick_x, SampleMecanumDrive.SPEED_POWER) * SampleMecanumDrive.SPEED_MULTIPLIER),
                            -(Math.pow(gamepad1.right_stick_x, SampleMecanumDrive.SPEED_POWER) * SampleMecanumDrive.SPEED_MULTIPLIER)
                    )
            );
            drive.update();

            if (gamepad1.left_bumper)
            {
                hardware.cleste.open();
            }

            // Retractarea glisierelor orizontale
            if (gamepad2.left_trigger != 0 && gamepad2.right_trigger == 0 && hardware.glisOriz.getCurrentPosition() > GlisiereOrizontale.POZITIE_MIN)
            {
                hardware.glisOriz.setMode(RUN_WITHOUT_ENCODER);
                hardware.glisOriz.setPower(-1);
            }
            // Extinderea gliserelor orizontale
            else if (gamepad2.right_trigger != 0 && gamepad2.left_trigger == 0 && hardware.glisOriz.getCurrentPosition() < GlisiereOrizontale.POZITIE_MAX)
            {
                hardware.glisOriz.setMode(RUN_WITHOUT_ENCODER);
                hardware.glisOriz.setPower(1);
            }
            else
            {
                hardware.glisOriz.setPower(0);
            }

            // Retractarea glisierelor verticale
            if (gamepad1.left_trigger != 0)
            {
                motor1.setMode(RUN_WITHOUT_ENCODER);
                motor2.setMode(RUN_WITHOUT_ENCODER);

                motor1.setPower(-0.2);
                motor2.setPower(-0.2);
            }
            // Extinderea gliserelor verticale
            else if (gamepad1.right_trigger != 0)
            {
                motor1.setMode(RUN_WITHOUT_ENCODER);
                motor2.setMode(RUN_WITHOUT_ENCODER);

                motor1.setPower(0.2);
                motor2.setPower(0.2);
            }
            else
            {
                motor1.setPower(0);
                motor2.setPower(0);
            }

            if (gamepad2.dpad_left) hardware.cleste.open();
            if (gamepad2.dpad_right) hardware.cleste.close();

            telemetry.addData("Glis oriz curr", hardware.glisOriz.getCurrentPosition());
            telemetry.addData("Glis vert curr", hardware.glisVert.getCurrentPosition());
            telemetry.update();
        }

        while (hardware.glisOriz.getCurrentPosition() > 50)
        {
            hardware.glisOriz.setPower(-1);
            idle();
        }

        while (hardware.glisVert.getCurrentPosition() > 50)
        {
            hardware.glisVert.setPower(-1);
            idle();
        }

        telemetry.update();
    }
}
