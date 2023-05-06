/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@Autonomous

public class TestPosAuto extends LinearOpMode {

    ArmServoPosition p1 = new ArmServoPosition(0, 0.6, 0.55);
    ArmServoPosition p2 = new ArmServoPosition( 0, 0.55, 0.5);
    ArmServoPosition p3 = new ArmServoPosition(0, 0.47, 0.43);
    ArmServoPosition p4 = new ArmServoPosition(0, 0.43, 0.38);
    ArmServoPosition p5 = new ArmServoPosition(0, 0.38, 0.32);
    ArmServoPosition p6 = new ArmServoPosition( 0, 0, 0);

    @Override

    public void runOpMode(){

        Servos servos = new Servos(hardwareMap);

        waitForStart();

        servos.setPositions(servos.Cl, servos.bCl, servos.zCl, p1);
        sleep(1000);
        servos.setPositions(servos.Cl, servos.bCl, servos.zCl, p2);
        sleep(1000);
        servos.setPositions(servos.Cl, servos.bCl, servos.zCl, p3);
        sleep(1000);
        servos.setPositions(servos.Cl, servos.bCl, servos.zCl, p4);
        sleep(1000);
        servos.setPositions(servos.Cl, servos.bCl, servos.zCl, p5);
        sleep(1000);

        while(opModeIsActive()){
            idle();
        }


    }

}
*/
