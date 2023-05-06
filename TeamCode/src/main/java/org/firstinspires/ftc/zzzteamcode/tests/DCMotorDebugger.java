/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;

@TeleOp
@Config

public class DCMotorDebugger extends LinearOpMode {
    SampleMecanumDrive drive;

    @Override


    public void runOpMode(){

        drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        while(opModeIsActive()){

            if (gamepad1.right_bumper){
                drive.rightFront.setPower(0.3);
            }
            else if(gamepad1.left_bumper){
                drive.leftFront.setPower(0.3);
            }
            else if(gamepad1.left_trigger != 0){
                drive.leftRear.setPower(0.3);
            }
            else if(gamepad1.right_trigger != 0){
                drive.rightRear.setPower(0.3);
            }
            else{
                drive.rightRear.setPower(0);
                drive.rightFront.setPower(0);
                drive.leftRear.setPower(0);
                drive.leftFront.setPower(0);
            }

        }

    }

}
*/
