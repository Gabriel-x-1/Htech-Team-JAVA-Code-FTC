/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@Config
@TeleOp

public class TestMotor extends LinearOpMode {

    public DcMotor test;

    @Override

    public void runOpMode(){

        test = this.hardwareMap.get(DcMotor.class, "test");
        test.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.right_bumper){
                test.setPower(1);
            }
            else
                test.setPower(0);

            telemetry.addData("IsBusy", test.getPower());
            telemetry.update();

        }

    }

}
*/
