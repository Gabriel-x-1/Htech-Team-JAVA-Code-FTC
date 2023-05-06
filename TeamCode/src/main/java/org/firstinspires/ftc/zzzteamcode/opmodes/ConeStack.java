/*
package org.firstinspires.ftc.zzzteamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@Autonomous ()


public class ConeStack extends LinearOpMode {

    Servos servos;
    Glisiere glisiere;
    SampleMecanumDrive drive;

    public ColorSensor color;
    public int j1 = 10, j2 = 1080 , j3 = 2720; // poz junctionuri (glisiera principala)
    public int gSpot = 900; // pozitie de punere a conurilor in sleeve (glisiere orizontale)
    ArmServoPosition p1 = new ArmServoPosition(0, 0.7, 0.50);
    ArmServoPosition p2 = new ArmServoPosition(0, 0.6, 0.45);
    ArmServoPosition p3 = new ArmServoPosition(0, 0.53, 0.40);
    ArmServoPosition p4 = new ArmServoPosition(0, 0.48, 0.34);
    ArmServoPosition pozitieNormala = new ArmServoPosition(0, 0.38, 0.25);
    ArmServoPosition p6 = new ArmServoPosition(0, 0, 0);
    */
/*

    ServoPosition p1  = new ServoPosition(0, 0.66, 0.54);
    ServoPosition p2 = new ServoPosition(0, 0.62, 0.50);
    ServoPosition p3 = new ServoPosition(0, 0.54, 0.43);
    ServoPosition p4 = new ServoPosition(0, 0.50, 0.38);
    ServoPosition pozitieNormala  = new ServoPosition(0, 0.42, 0.30);

     *//*


    ArmServoPosition pGSpot = new ArmServoPosition(1, 0.36, 0.80); //pozitie gspot
    ArmServoPosition pozitieCleste90grade = new ArmServoPosition(1, 0.36, 0.60); //pozitie verticala a bratului clestelui


    public void  posCleste(ArmServoPosition position, Servos servo){
        servo.Cl.setPosition(position.posCl); // inchide deschide clestele
        servo.bCl.setPosition(position.posbCl); // unghiul orientarii clestelui
        servo.zCl.setPosition(position.poszCl); // bratul clestelui
    }

    public void ciclu(ArmServoPosition position){
        while(opModeIsActive()){

            posCleste(position, servos);

            glisiere.glSt.setTargetPosition(1500);
            glisiere.glDr.setTargetPosition(1500);

            glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            glisiere.glSt.setPower(0.4);
            glisiere.glDr.setPower(0.4);

            if(glisiere.glDr.isBusy()) {
                if (color.red() > 200 || color.blue() > 200) {
                    glisiere.glDr.setPower(0);
                    glisiere.glSt.setPower(0);

                    servos.Cl.setPosition(1);
                    sleep(500);

                    servos.bCl.setPosition(0.7);
                    sleep(200);

                    telemetry.addData("Debug", "Debug");

                    glisiere.glDr.setTargetPosition(gSpot);
                    glisiere.glSt.setTargetPosition(gSpot);

                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    glisiere.glSt.setPower(0.4);
                    glisiere.glDr.setPower(0.4);
                    while (glisiere.glSt.isBusy())
                        idle();
                    servos.bSlv.setPosition(1);    //lasa sleeve
                    sleep(500);
                    servos.Slv.setPosition(0);//da piedica deoparte
                    sleep(500);
                    posCleste(pGSpot, servos);//ridica cleste
                    sleep(500);
                    servos.Cl.setPosition(0);//da drumul la con
                    sleep(500);
                    servos.Slv.setPosition(1);//pune piedica
                    posCleste(pozitieCleste90grade, servos);
                    sleep(700);
                    servos.bSlv.setPosition(0.018);//ridica sleeve
                    sleep(500);
                    glisiere.glSt.setTargetPosition(270);
                    glisiere.glDr.setTargetPosition(270);
                    glisiere.glSt.setPower(0.4);
                    glisiere.glDr.setPower(0.4);
                    sleep(500);
                    servos.Slv.setPosition(0);//lasa piedica jos
                    sleep(500);
                    servos.bSlv.setPosition(1);//sleeve inapoi
                    sleep(500);
                    servos.Slv.setPosition(1);

                    telemetry.addData("Ciclu", position.getPosbCl());
                    telemetry.update();

                    break;


                }
            }


            telemetry.addData("Rosu", color.red());
            telemetry.addData("Albastru", color.blue());
            telemetry.addData("motor", glisiere.glDr.isBusy());
            telemetry.addData("pos", glisiere.glDr.getCurrentPosition());
            telemetry.update();

        }
    }

    @Override

    public void runOpMode(){

        drive = new SampleMecanumDrive(hardwareMap);
        glisiere = new Glisiere(hardwareMap);
        servos = new Servos(hardwareMap);
        color = this.hardwareMap.get(ColorSensor.class, "cSensor");
        glisiere.glDr.setTargetPosition(0);
        glisiere.glSt.setTargetPosition(0);
        glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        while(opModeIsActive()){

            ciclu(p1);
            ciclu(p2);
            ciclu(p3);
            ciclu(p4);
            ciclu(pozitieNormala);

        }




    }

}
*/
