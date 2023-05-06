/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@Config
@TeleOp

public class TestGlisieraMare extends LinearOpMode {

    DcMotorEx glisiera;
    public Servos servos;
    boolean config = false;
    public static double min = 0, max = 1;



    @Override

    public void runOpMode() {

        servos = new Servos(hardwareMap);

        glisiera = this.hardwareMap.get(DcMotorEx.class, "gl");
        //glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        glisiera.setTargetPosition(0);
        glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        glisiera.setDirection(DcMotorSimple.Direction.REVERSE);

        servos.bSlv.setPosition(0);

        waitForStart();

        int j1 = 0, j2 = 1080, j3 = 2720;


        while (opModeIsActive()) {


            if (gamepad1.right_bumper){
                config = false;
                glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                glisiera.setTargetPosition(0);
                glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if (gamepad1.left_bumper) {
                config = true;
                glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            if (!config) {

                if (gamepad1.triangle) {
                    glisiera.setTargetPosition(j1);
                    glisiera.setPower(1);
                } else if (gamepad1.circle) {
                    glisiera.setTargetPosition(j2);
                    glisiera.setPower(1);
                } else if (gamepad1.cross) {
                    glisiera.setTargetPosition(j3);
                    glisiera.setPower(1);
                }
            }else{

                if (gamepad1.right_trigger != 0){
                    glisiera.setPower(0.3);
                }else if (gamepad1.left_trigger != 0){
                    glisiera.setPower(-0.3);
                }else{
                    glisiera.setPower(0);
                }

            }
            if (gamepad1.dpad_right)
                servos.bSlv.setPosition(max);
            else if(gamepad1.dpad_left)
                servos.bSlv.setPosition(min);

            telemetry.addData("Pozitie Glisiera", glisiera.getCurrentPosition());
            telemetry.addData("Config", config);
            telemetry.update();

        }
    }

}
*/
