
package org.firstinspires.ftc.zzzteamcode.util;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;

public class ControllerInput{


    public Gamepad currentGamepad1 = new Gamepad();
    public Gamepad currentGamepad2 = new Gamepad();

    public Gamepad previousGamepad1 = new Gamepad(), previousGamepad2 = new Gamepad();

    public TouchPadCoordinates coord = new TouchPadCoordinates();

    public void miscareTP(SampleMecanumDrive drive, Gamepad gp1){

        if (currentGamepad1.touchpad_finger_1 && !previousGamepad1.touchpad_finger_1){
            coord.x01 = currentGamepad1.touchpad_finger_1_x;
            coord.y01 = currentGamepad1.touchpad_finger_1_y;
        }
        else if (currentGamepad1.touchpad_finger_1) {
            coord.fx1 = currentGamepad1.touchpad_finger_1_x - coord.x01;
            coord.fy1 = currentGamepad1.touchpad_finger_1_y - coord.y01;
        }
        else{
            coord.fx1 = 0;
            coord.fy1 = 0;
        }


drive.setWeightedDrivePower(
                new Pose2d(
                        -(coord.fy1),
                        -(coord.fx1),
                        -(Math.pow(gamepad1.right_trigger - gamepad1.left_trigger, 3) * 0.5)
                )
        );


    }

    public ControllerInput()
    {

        currentGamepad1 = gamepad1;
        currentGamepad2 = gamepad2;

    }

    public boolean pressOptions(){
        if (!previousGamepad1.options && currentGamepad1.options)
            return true;
        else return false;
    }
    public boolean pressShare(){
        if (!previousGamepad1.share && currentGamepad1.share)
            return true;
        else return false;
    }
    public boolean pressDpadUp2(){
        if (!previousGamepad2.dpad_up && currentGamepad2.dpad_up)
            return true;
        else return false;
    }
    public boolean pressDpadDown2(){
        if (!previousGamepad2.dpad_down && currentGamepad2.dpad_down)
            return true;
        else return false;
    }
    public boolean pressDpadLeft2(){
        if (!previousGamepad2.dpad_left && currentGamepad2.dpad_left)
            return true;
        else return false;
    }
    public boolean pressDpadRight2(){
        if (!previousGamepad2.dpad_right && currentGamepad2.dpad_right)
            return true;
        else return false;
    }
    public boolean pressX2(){
        if (!previousGamepad2.x && currentGamepad2.x)
            return true;
        else return false;
    }
    public boolean pressY2(){
        if (!previousGamepad2.y && currentGamepad2.y)
            return true;
        else return false;
    }
    public boolean pressA2(){
        if (!previousGamepad2.a && currentGamepad2.a)
            return true;
        else return false;
    }
    public boolean pressB2(){
        if (!previousGamepad2.b && currentGamepad2.b)
            return true;
        else return false;
    }

    public boolean pressLB1(){
        if (!previousGamepad1.left_bumper && currentGamepad1.left_bumper)
            return true;
        else return false;
    }

    public boolean pressRB1(){
        if (!previousGamepad1.right_bumper && currentGamepad1.right_bumper)
            return true;
        else return false;
    }

    public boolean pressDpadUp1(){
        if (!previousGamepad1.dpad_up && currentGamepad1.dpad_up)
            return true;
        else return false;
    }
    public boolean pressDpadDown1(){
        if (!previousGamepad1.dpad_down && currentGamepad1.dpad_down)
            return true;
        else return false;
    }
    public boolean pressDpadLeft1(){
        if (!previousGamepad1.dpad_left && currentGamepad1.dpad_left)
            return true;
        else return false;
    }
    public boolean pressDpadRight1(){
        if (!previousGamepad1.dpad_right && currentGamepad1.dpad_right)
            return true;
        else return false;
    }



}
