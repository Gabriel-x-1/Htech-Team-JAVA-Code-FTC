/*
package org.firstinspires.ftc.zzzteamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.zzzteamcode.configs.Glisiere;
import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ArmServoPosition;
import org.firstinspires.ftc.zzzteamcode.configs.Servos;

@Config
@TeleOp


public class SampleTeleOp2 extends LinearOpMode {

    public static double speedx = 0.5;
    public static double VitezaGlisieraMare = 1;
    public static double VitezaGlisiereOrizontale = 0.8;
    public static double VitezaGlisieraMare_MANUAL = 0.3;
    public static double VitezaGlisiereOrizontale_MANUAL = 0.3;

    public boolean aux = false;

    public SampleMecanumDrive drive;
    public Servos servos;
    public Glisiere glisiere;
    ArmServoPosition p1 = new ArmServoPosition(0, 0.7, 0.50);
    ArmServoPosition p2 = new ArmServoPosition(0, 0.6, 0.45);
    ArmServoPosition p3 = new ArmServoPosition(0, 0.53, 0.40);
    ArmServoPosition p4 = new ArmServoPosition(0, 0.48, 0.34);
    ArmServoPosition pozitieNormala = new ArmServoPosition(0.4, 0.37, 0.28);
    ArmServoPosition p7 = new ArmServoPosition(0.65, 0.36, 0.80); //pozitie gspot


    ArmServoPosition p12  = new ArmServoPosition(0, 0.66, 0.54);
    ArmServoPosition p22 = new ArmServoPosition(0, 0.62, 0.50);
    ArmServoPosition p32 = new ArmServoPosition(0, 0.54, 0.43);
    ArmServoPosition p42 = new ArmServoPosition(0, 0.50, 0.36);
    ArmServoPosition pNormala  = new ArmServoPosition(0.4, 0.42, 0.43);



    public ArmServoPosition pozitii(ArmServoPosition p){
        if(glisiere.glSt.getCurrentPosition() < 1000)
            return p;
        if(p == p1)
            return p12;
        if(p == p2)
            return p22;
        if(p == p3)
            return p32;
        if(p == p4)
            return p42;
        return pNormala;
    }
    ArmServoPosition pozitieCleste90grade = new ArmServoPosition(0.65, 0.36, 0.60); //pozitie verticala a bratului clestelui
    public int gSpot = 880; // pozitie de punere a conurilor in sleeve (glisiere orizontale)
    public int maxOr = 1550; // poz max glisiere orizontale
    public int j1 = 10, j2 = 1200, j3 = 2840; // poz junctionuri (glisiera principala)
    //poz de la high junction sa se mareasca cu putin. Cand este con, se lasa si opritoarea loveste
    public boolean manual = false;
    public ColorSensor color;
    boolean areCon = false;

    public void miscare() {
        drive.setWeightedDrivePower(
                new Pose2d(
                        -(Math.pow(gamepad1.left_stick_y, 1) * speedx),
                        -(Math.pow(gamepad1.left_stick_x, 1) * speedx),
                        -(Math.pow(gamepad1.right_trigger - gamepad1.left_trigger, 3) * 0.5)
                )
        );
    }



    @Override

    public void runOpMode() {
        color = this.hardwareMap.get(ColorSensor.class, "cSensor");

        drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        servos = new Servos(hardwareMap);
        glisiere = new Glisiere(hardwareMap);

        glisiere.glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        glisiere.glSt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        glisiere.glDr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiere.glSt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiere.glDr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        posCleste(pozitieNormala, servos);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.options) { //manual mode
                manual = true;
                glisiere.glisiera.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                glisiere.glSt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                glisiere.glDr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } else if (gamepad1.share) { // restart / config
                manual = false;

                glisiere.glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                glisiere.glSt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                glisiere.glDr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                glisiere.glisiera.setTargetPosition(0);
                glisiere.glSt.setTargetPosition(0);
                glisiere.glDr.setTargetPosition(0);

                glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }



            if (!manual) {

                miscare();
                // miscarea gamepad.1

                if (gamepad2.a && areCon) {

                    servos.bSlv.setPosition(0.018);
                    sleep(500);
                    
                    posCleste(pozitieCleste90grade, servos);
                    glisiere.glSt.setTargetPosition(20);
                    glisiere.glDr.setTargetPosition(20);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                    glisiere.glisiera.setTargetPosition(j1);
                    glisiere.glisiera.setPower(VitezaGlisieraMare);
                    while (glisiere.glisiera.isBusy()) {
                        //brat sleeve
                        miscare();
                    }



                } 
                else if (gamepad2.b && areCon) {

                    glisiere.glisiera.setTargetPosition(j2);
                    glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glisiera.setPower(VitezaGlisieraMare);

                    while (glisiere.glisiera.isBusy()) {
                        miscare();
                    }

                    servos.bSlv.setPosition(0.018); //brat sleeve
                    posCleste(pozitieCleste90grade, servos);
                    glisiere.glSt.setTargetPosition(20);
                    glisiere.glDr.setTargetPosition(20);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                } 
                else if (gamepad2.y && areCon) {
                    glisiere.glisiera.setTargetPosition(j3);
                    glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glisiera.setPower(VitezaGlisieraMare);
                    posCleste(pozitieCleste90grade, servos);
                    glisiere.glSt.setTargetPosition(20);
                    glisiere.glDr.setTargetPosition(20);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                    while (glisiere.glisiera.getCurrentPosition() < j3) {

                        glisiere.glisiera.setTargetPosition(j3);
                        if(glisiere.glisiera.getCurrentPosition() >1900)
                            servos.bSlv.setPosition(0.018); //brat sleeve

                        miscare(); // miscarea gamepad.1
                    }

                    servos.bSlv.setPosition(0.018); //brat sleeve //edit fix cleste --rares
                    posCleste(pozitieCleste90grade, servos);
                    glisiere.glSt.setTargetPosition(20);
                    glisiere.glDr.setTargetPosition(20);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                } 
                else if (gamepad1.square) { // TODO: Implement this
                    {
                        if (servos.Slv.getPosition() == 1)
                            servos.Slv.setPosition(0);
                        else
                            servos.Slv.setPosition(1);
                    }

                }
                else if (gamepad2.dpad_up && !aux) { //brat sleeve
                    aux = true;
                    if (1 - servos.bSlv.getPosition() < 0.0001) { //inseamna servos.bSlv.getPosition() == 1 (am fct asa pt ca nu e recomandat sa compari double cu egal)
                        servos.bSlv.setPosition(0.018);


                    } else {
                        servos.bSlv.setPosition(1);
                    }
                }
                else if (gamepad2.x) { // automatizare dupa ce iei con
                    drive.setWeightedDrivePower(
                            new Pose2d(
                                    0,
                                    0,
                                    0
                            )
                    );
                    areCon = true;
                    servos.Cl.setPosition(0.65);
                    sleep(500);
                    servos.Slv.setPosition(0);


                    if(glisiere.glisiera.getCurrentPosition() > 100)
                    {
                        glisiere.glisiera.setTargetPosition(j1);
                        glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        glisiere.glisiera.setPower(1);
                        while(glisiere.glisiera.isBusy()) idle();
                    }


                    if(servos.bSlv.getPosition() != 1) {
                        servos.bSlv.setPosition(1);
                        sleep(500);
                    }
                    servos.bSlv.setPosition(1);

                    glisiere.glDr.setTargetPosition(gSpot);
                    glisiere.glSt.setTargetPosition(gSpot);

                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);

                    posCleste(p7, servos);
                    sleep(1000);
                    //sleep(1000);  +

                    servos.Cl.setPosition(0.4);
                    sleep(500);

                    posCleste(pozitieNormala, servos);
                    sleep(500);
                    servos.Slv.setPosition(1);
                    //sleep(1000);


                    servos.bSlv.setPosition(0.35);
                    glisiere.glSt.setTargetPosition(10);
                    glisiere.glDr.setTargetPosition(10);

                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                    */
/*
                    while (glisiere.glSt.isBusy()){
                        miscare();
                    }
                    *//*



                    posCleste(pozitieCleste90grade, servos);

                }
                else if (gamepad2.dpad_down) { //deschidere piedica + coborare glisiera mare

                    areCon = false;

                    glisiere.glSt.setTargetPosition(270);
                    glisiere.glDr.setTargetPosition(270);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);

                    servos.Slv.setPosition(0); // piedica deschisa
                    sleep(500);
                    servos.bSlv.setPosition(1); //brat sleeve
                    //sleep(1500);
                    glisiere.glisiera.setTargetPosition(j1);
                    glisiere.glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    glisiere.glisiera.setPower(VitezaGlisieraMare);

                    posCleste(pozitieNormala, servos);
                    while (glisiere.glisiera.isBusy()) {
                        glisiere.glisiera.setTargetPosition(j1);
                        miscare();
                        // idle(); //sterge
                    }

                } 
                else if (gamepad2.left_bumper) { //cleste deschis
                    servos.Cl.setPosition(0.4);
                } 
                else if (gamepad2.right_bumper) { //cleste inchis
                    servos.Cl.setPosition(0.65);
                }
                else if (gamepad2.left_trigger != 0 && glisiere.glDr.getCurrentPosition() > 50) { //glisiere orizontale restrangere
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    glisiere.glDr.setPower(-VitezaGlisiereOrizontale);
                    glisiere.glSt.setPower(-VitezaGlisiereOrizontale);
                } 
                else if (gamepad2.right_trigger != 0 && glisiere.glDr.getCurrentPosition() < maxOr - 50) { //glisiera orizontale extindere
                    glisiere.glDr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    glisiere.glSt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale);
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale);
                } 
                else {
                    glisiere.glDr.setPower(0);
                    glisiere.glSt.setPower(0);
                    glisiere.glisiera.setPower(0);
                }

                if (!gamepad2.dpad_up){
                    aux = false;
                }

            } else if (manual) {

                if (gamepad1.right_bumper) {
                    glisiere.glSt.setPower(VitezaGlisiereOrizontale_MANUAL);
                    glisiere.glDr.setPower(VitezaGlisiereOrizontale_MANUAL);
                } else if (gamepad1.left_bumper) {
                    glisiere.glSt.setPower(-VitezaGlisiereOrizontale_MANUAL);
                    glisiere.glDr.setPower(-VitezaGlisiereOrizontale_MANUAL);
                } else if (gamepad1.dpad_up) {
                    glisiere.glisiera.setPower(VitezaGlisieraMare_MANUAL);
                } else if (gamepad1.dpad_down) {
                    glisiere.glisiera.setPower(-VitezaGlisieraMare_MANUAL);
                } else {
                    glisiere.glSt.setPower(0);
                    glisiere.glDr.setPower(0);
                    glisiere.glisiera.setPower(0);
                }
            }

            telemetry.addData("Pozitie orizontale", glisiere.glDr.getCurrentPosition());
            telemetry.addData("Pozitie Verticala", glisiere.glisiera.getCurrentPosition());
            telemetry.addData("Mod", manual);
            telemetry.addData("Options", gamepad1.options);
            telemetry.addData("Share", gamepad1.share);
            telemetry.addData("Mod motor", glisiere.glisiera.getMode());
            telemetry.addData("Red", color.red());
            telemetry.update();
        }
    }
}*/
