/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class TestGlisiere extends LinearOpMode {

    DcMotorEx glSt, glDr;
    */
/* Servos servo;

    ServoPosition p1 = new ServoPosition(0, 0.7, 0.50);
    ServoPosition p2 = new ServoPosition(0, 0.6, 0.45);
    ServoPosition p3 = new ServoPosition(0, 0.53, 0.40);
    ServoPosition p4 = new ServoPosition(0, 0.48, 0.34);
    ServoPosition p5 = new ServoPosition(0, 0.38, 0.26);
    ServoPosition p6 = new ServoPosition(0, 0, 0);
    ServoPosition p7 = new ServoPosition(1, 0.40, 0.80); //pozitie gspot

    public void  posCleste(ServoPosition position, Servos servo){
        servo.Cl.setPosition(position.posCl); // inchide deschide clestele
        servo.bCl.setPosition(position.posbCl); // bratul clestelui
        servo.zCl.setPosition(position.poszCl); // unghiul orientarii clestelui
    }
*//*

    @Override

    public void runOpMode(){

        glSt = this.hardwareMap.get(DcMotorEx.class, "glSt");
        glSt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        glSt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glSt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        glSt.setDirection(DcMotorSimple.Direction.REVERSE);

        glDr = this.hardwareMap.get(DcMotorEx.class, "glDr");
        glDr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        glDr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glDr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        glDr.setDirection(DcMotorSimple.Direction.FORWARD);


        */
/*
        servo = new Servos(hardwareMap);
        posCleste(p5, servo);
        sleep(1500);
        servo.Cl.setPosition(1);
        sleep(1500);
        posCleste(p7, servo);
*//*


        waitForStart();

        while(!isStopRequested()){
            boolean g1RB = gamepad1.right_bumper;
            boolean g1LB = gamepad1.left_bumper;
*/
/*
            if(gamepad1.right_stick_button){
                servo.bSlv.setPosition(0);
            }
            else if(gamepad1.left_stick_button){
                servo.bSlv.setPosition(1);
            }
            else if(gamepad1.dpad_right){
                servo.Slv.setPosition(0);
            }
            else if(gamepad1.dpad_left)
                servo.Slv.setPosition(1);
*//*




            if(g1RB) {
                glDr.setPower(0.5);
                glSt.setPower(0.5);
            }else if(g1LB){
                glDr.setPower(-0.5);
                glSt.setPower(-0.5);
            }else{
                glDr.setPower(0);
                glSt.setPower(0);
            }

            telemetry.addData("Pozitie glisiere", glDr.getCurrentPosition());
            telemetry.update();

        }

    }
}
*/
