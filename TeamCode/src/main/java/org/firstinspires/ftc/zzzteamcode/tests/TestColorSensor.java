/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@TeleOp

public class TestColorSensor extends LinearOpMode {

    public ColorSensor color;
    public Glisiere glisiere;
    public Servos servos;

    //bCl 0.26, zCl 0.38


    public void colorTest(ColorSensor col, Glisiere gl, Servos sv){

        gl.glDr.setPower(0.3);
        gl.glSt.setPower(0.3);
        */
/*while(gl.glDr.getCurrentPosition() < 1500) {
            if (col.red() > 50 || col.blue() > 50) {
                sv.Cl.setPosition(1);
                gl.glDr.setPower(0);
                gl.glSt.setPower(0);
                sleep(500);
            }
        }



        gl.glDr.setPower(0);
        gl.glSt.setPower(0);
        while (gl.glDr.getCurrentPosition() > 500){
            gl.glDr.setPower(-0.3);
            gl.glSt.setPower(-0.3);
        }
        gl.glDr.setPower(0);
        gl.glSt.setPower(0);
        sv.Cl.setPosition(0);

         *//*

    }

    @Override

    public void runOpMode(){

        glisiere = new Glisiere(hardwareMap);
        color = this.hardwareMap.get(ColorSensor.class, "cSensor");
        servos = new Servos(hardwareMap);
        glisiere.glDr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiere.glSt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //servos.bCl.setPosition(0.26);
        //servos.zCl.setPosition(0.38);
        //servos.Cl.setPosition(0);

        waitForStart();

        while(opModeIsActive()){

        idle();
*/
/*
            if (gamepad1.cross){
                colorTest(color, glisiere, servos);
            }
            if (glisiere.glSt.getPower() != 0){
                if (glisiere.glSt.getCurrentPosition() > 1490 || color.red() > 100 || color.blue() > 100){
                    sleep(250);
                    servos.Cl.setPosition(1);
                    glisiere.glDr.setPower(0);
                    glisiere.glSt.setPower(0);
                }
            }

 *//*


            telemetry.addData("PosGL", glisiere.glSt.getCurrentPosition());
            telemetry.addData("isBusy", glisiere.glSt.isBusy());
            telemetry.addData("Rosu", color.red());
            telemetry.addData("Albastru", color.blue());
            telemetry.update();
        }



    }
}
*/
