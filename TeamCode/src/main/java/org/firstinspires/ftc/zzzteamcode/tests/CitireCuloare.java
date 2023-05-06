/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous

public class CitireCuloare extends LinearOpMode {

    public ColorSensor color;

    @Override

    public void runOpMode(){

        color = this.hardwareMap.get(ColorSensor.class, "cSensor");

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Rosu", color.red());
            telemetry.addData("Albastru", color.blue());
            telemetry.update();
        }

    }

}
*/
