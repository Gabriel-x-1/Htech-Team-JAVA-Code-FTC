package org.firstinspires.ftc.teamcode.threads.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@TeleOp(group = "Tests")
@Config
public class TestConfigMotor extends LinearOpMode
{

    public DcMotorEx motor;
    public static String nMotor = "1";
    public static double power = 0;

    @Override
    public void runOpMode(){

        motor = this.hardwareMap.get(DcMotorEx.class, nMotor);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()){

                motor.setPower(power);
        }

    }

}
