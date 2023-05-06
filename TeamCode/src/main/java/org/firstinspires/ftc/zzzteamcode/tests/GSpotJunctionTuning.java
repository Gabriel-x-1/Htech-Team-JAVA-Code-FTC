/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;

@TeleOp

public class GSpotJunctionTuning extends LinearOpMode {

    @Override

    public void runOpMode(){

        Glisiere glisiere = new Glisiere(hardwareMap);




        while (opModeIsActive()) {

            if (gamepad1.left_bumper)
                glisiere.glisiera.setPower(-0.3);
            else if (gamepad1.right_bumper)
                glisiere.glisiera.setPower(0.3);
            else if (gamepad1.right_trigger != 0){
                glisiere.glDr.setPower(0.3);
                glisiere.glSt.setPower(0.3);
            }
            else if (gamepad1.left_trigger != 0){
                glisiere.glDr.setPower(-0.3);
                glisiere.glSt.setPower(-0.3);
            }
            else{
                glisiere.glDr.setPower(0);
                glisiere.glSt.setPower(0);
                glisiere.glisiera.setPower(0);
            }

            telemetry.addData("Pozitie glisiera mare", glisiere.glisiera.getCurrentPosition());
            telemetry.addData("Pozitie glisiere orizontale", glisiere.glDr.getCurrentPosition());

            telemetry.update();
        }
    }

}
*/
