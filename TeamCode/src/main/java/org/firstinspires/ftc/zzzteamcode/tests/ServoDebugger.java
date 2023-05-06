/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

//import org.firstinspires.ftc.teamcode.drive.FlipServo;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@TeleOp
@Config

public class ServoDebugger extends LinearOpMode {

    ArmServoPosition p1 = new ArmServoPosition(0, 0.7, 0.50);
    ArmServoPosition p2 = new ArmServoPosition(0, 0.6, 0.45);
    ArmServoPosition p3 = new ArmServoPosition(0, 0.53, 0.40);
    ArmServoPosition p4 = new ArmServoPosition(0, 0.48, 0.34);
    ArmServoPosition p5 = new ArmServoPosition(0, 0.38, 0.26);
    ArmServoPosition p6 = new ArmServoPosition(0, 0, 0);
    ArmServoPosition p7 = new ArmServoPosition(0, 0.40, 0.80); //pozitie gspot


    public static double maxCl = 1, maxzCl = 1, maxbCl = 1;
    public static double minCl = 0, minzCl = 0, minbCl = 0;


    @Override

    public void runOpMode(){

        Servos servos = new Servos(hardwareMap);
        DcMotorEx glDr = this.hardwareMap.get(DcMotorEx.class, "glDr");
        glDr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ColorSensor color = this.hardwareMap.get(ColorSensor.class, "cSensor");

        double sCl = 0, szCl = 0, sbCl = 0;

        servos.Cl.setPosition(sCl);
        servos.zCl.setPosition(0.4);
        servos.bCl.setPosition(0.2);

        servos.bSlv.setPosition(0.8);

        waitForStart();

        while (opModeIsActive()){

            if (gamepad1.left_bumper){
                //sCl -= 0.05;
                servos.Cl.setPosition(minCl);
                //sleep(500);
            }

            else if (gamepad1.right_bumper){
                //sCl += 0.05;
                servos.Cl.setPosition(maxCl);
                //sleep(500);
            }
            else if (gamepad1.left_trigger != 0){
                //szCl -= 0.05;
                servos.zCl.setPosition(minzCl);
                //sleep(500);
            }
            else if (gamepad1.right_trigger != 0){
                //szCl += 0.05;
                servos.zCl.setPosition(maxzCl);
                //sleep(500);
            }
            else if (gamepad1.dpad_left){
                //sbCl -= 0.05;
                servos.bCl.setPosition(minbCl);
                //sleep(500);
            }
            else if (gamepad1.dpad_right){
                //sbCl += 0.05;
                servos.bCl.setPosition(maxbCl);
                //sleep(500);
            }
            else if (gamepad1.left_stick_button)
                servos.bSlv.setPosition(1);
            else if (gamepad1.right_stick_button)
                servos.bSlv.setPosition(0);

            telemetry.addData("zCl", szCl);
            telemetry.addData("bCl", sbCl);
            telemetry.addData("gl", glDr.getCurrentPosition());
            telemetry.addData("red", color.red());
            telemetry.update();

        }
    }
}
*/
