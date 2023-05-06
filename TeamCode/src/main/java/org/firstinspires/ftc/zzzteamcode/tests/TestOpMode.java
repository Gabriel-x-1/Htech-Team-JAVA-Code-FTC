/*
package org.firstinspires.ftc.zzzteamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestOpMode extends LinearOpMode
{
    final int j1 = 1800; //pozitie glisiera principala pt junction 1
    final int j2 = 3000; //pozitie glisiera principala pt junction 2
    final int j3 = 4200; //pozitie glisiera principala pt junction 3

    final double MAX_poz_glisiera = 4400; //val max absolut ~4486
    final double step = 0.2; //rata de acc

    DcMotorEx front_left_motor;
    DcMotorEx back_left_motor;
    DcMotorEx front_right_motor;
    DcMotorEx back_right_motor;
    DcMotorEx glisiera;

    Servo cleste;
    Servo brat_cleste;

    public void init_hardware()
    {
        front_left_motor = this.hardwareMap.get(DcMotorEx.class, "leftFront");
        front_right_motor = this.hardwareMap.get(DcMotorEx.class, "rightFront");
        back_left_motor = this.hardwareMap.get(DcMotorEx.class, "leftRear");
        back_right_motor = this.hardwareMap.get(DcMotorEx.class, "rightRear");
        //glisiera = this.hardwareMap.get(DcMotorEx.class, "GLISIERA");
        //brat_cleste = this.hardwareMap.get(Servo.class, "BRC");
        //cleste = this.hardwareMap.get(Servo.class, "C");

        front_left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        back_left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        front_right_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        back_right_motor.setDirection(DcMotorSimple.Direction.FORWARD);

        front_left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //glisiera.setDirection(DcMotorSimple.Direction.REVERSE);

        //brat_cleste.setPosition(0); //poz const pt bratul clestelui
        telemetry.addData("STATUS: ", "ON");
    }

    */
/*void glisieraGoTo(int poz, int pow)
    {
        glisiera.setTargetPosition(poz);
        glisiera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        for (double i = step; i <= 1; i+=step) glisiera.setPower(i*pow);
    }*//*


    @Override
    public void runOpMode() throws InterruptedException
    {
        init_hardware();
        waitForStart();
        while(opModeIsActive())
        {
            //double g1LStickX = gamepad1.left_stick_x;
            //double g1RStickY = -gamepad1.right_stick_y;

            double g1RStickX = gamepad1.right_stick_x; //miscare stanga-dreapta
            double g1LStickY = -gamepad1.left_stick_y; //miscarea fata-spate
            double g1LT = gamepad1.left_trigger; //rotire pe loc la stanga
            double g1RT = gamepad1.right_trigger; //rotire pe loc la dreapta

            boolean g1RB = gamepad1.right_bumper; //deschide clestele
            boolean g1LB = gamepad1.left_bumper; //inchide clestele

            //imagineaza-ti un ceas: 12(Y-0), 3(B-j1), 6(A-j2), 9(X-j3)
            boolean g1X = gamepad1.x; //glisiera poz 0
            boolean g1Y = gamepad1.y; //glisiera poz j1
            boolean g1A = gamepad1.a; //glisiera poz j2
            boolean g1B = gamepad1.b; //glisiera poz j3

			*/
/*
			boolean g1Up = gamepad1.dpad_up;
            boolean g1Down = gamepad1.dpad_down;
			boolean g1Right = gamepad1.dpad_right;
            boolean g1Left = gamepad1.dpad_left;
			boolean g1LStickB = gamepad1.left_stick_button;
			boolean g1RStickB = gamepad1.right_stick_button;
			*//*


            //miscare robot
            if(g1LStickY != 0)
            {
                for (double i = step; i <= 1; i+=step)
                {
                    front_left_motor.setPower(i*g1LStickY);
                    back_left_motor.setPower(i*g1LStickY);
                    back_right_motor.setPower(i*g1LStickY);
                    front_right_motor.setPower(i*g1LStickY);
                    sleep(500);
                }
            }
            else if(g1RStickX != 0)
            {
                for (double i = step; i <= 1; i+=step)
                {
                    front_left_motor.setPower(i*g1RStickX);
                    back_left_motor.setPower(i*(-g1RStickX));
                    back_right_motor.setPower(i*g1RStickX);
                    front_right_motor.setPower(i*(-g1RStickX));
                    sleep(500);
                }
            }

            //rotire robot
            else if(g1RT != 0)
            {
                for (double i = step; i <= 1; i+=step)
                {
                    front_left_motor.setPower(i*g1RT);
                    back_left_motor.setPower(i*g1RT);
                    back_right_motor.setPower(i*(-g1RT));
                    front_right_motor.setPower(i*(-g1RT));
                    sleep(500);
                }
            }
            else if(g1LT != 0)
            {
                for (double i = step; i <= 1; i+=step)
                {
                    front_left_motor.setPower(i*(-g1LT));
                    back_left_motor.setPower(i*(-g1LT));
                    back_right_motor.setPower(i*g1LT);
                    front_right_motor.setPower(i*g1LT);
                    sleep(50);
                }
            }

            else {
                front_left_motor.setPower(0);
                back_left_motor.setPower(0);
                back_right_motor.setPower(0);
                front_right_motor.setPower(0);
            }



            //miscare glisiera
            //if (g1Y) glisieraGoTo(0,1);
            //if (g1B) glisieraGoTo(j1,1);
            //if (g1A) glisieraGoTo(j2,1);
            //if (g1X) glisieraGoTo(j3,1);

            //miscare cleste
            //if (g1LB) cleste.setPosition(1);
            //if(g1RB) cleste.setPosition(0);

            //telemetrie
            telemetry.addData("Back Left Motor: ", back_left_motor.getVelocity());
            telemetry.addData("Back Right Motor: ", back_right_motor.getVelocity());
            telemetry.addData("Front Left Motor: ", front_left_motor.getVelocity());
            telemetry.addData("Front Right Motor: ", front_right_motor.getVelocity());
            //telemetry.addData("Pozitie glisiera: ", glisiera.getCurrentPosition());
            //telemetry.addData("Pozitie brat: ", brat_cleste.getPosition());
            //telemetry.addData("Pozitie cleste: ", cleste.getPosition());

            telemetry.update();
        }
    }
}
*/
