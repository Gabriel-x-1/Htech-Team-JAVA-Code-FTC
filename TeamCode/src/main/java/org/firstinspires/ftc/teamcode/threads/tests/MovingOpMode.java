package org.firstinspires.ftc.teamcode.threads.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;

@TeleOp

public class MovingOpMode extends LinearOpMode
{

    public SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException
    {
        drive = new SampleMecanumDrive(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.left_stick_x > 0.1 && gamepad1.left_stick_y > 0.1 && (gamepad1.right_trigger - gamepad1.left_trigger) > 0.1){
                drive.leftRear.setMotorEnable();
                drive.rightRear.setMotorEnable();
                drive.leftFront.setMotorEnable();
                drive.rightFront.setMotorEnable();
                drive.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger);
            }

            else
            {
                drive.leftRear.setMotorDisable();
                drive.rightRear.setMotorDisable();
                drive.leftFront.setMotorDisable();
                drive.rightFront.setMotorDisable();
            }

            telemetry.addData("leftFront", drive.leftFront.isMotorEnabled());
            telemetry.addData("leftRear", drive.leftRear.isMotorEnabled());
            telemetry.addData("rightFront", drive.rightFront.isMotorEnabled());
            telemetry.addData("rightRear", drive.rightRear.isMotorEnabled());
            telemetry.update();
        }
    }
}
