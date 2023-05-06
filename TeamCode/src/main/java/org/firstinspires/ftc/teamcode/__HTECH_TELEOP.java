package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.GlisiereOrizontale;
import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.lib.hardware.StickyGamepad;
import org.firstinspires.ftc.teamcode.lib.utils.MathUtils;
import org.firstinspires.ftc.teamcode.threads.DropCone;
import org.firstinspires.ftc.teamcode.threads.GrabCone;
import org.firstinspires.ftc.teamcode.threads.PrepareCone;
import org.firstinspires.ftc.teamcode.threads.Reset;
import org.firstinspires.ftc.teamcode.threads.Unreset;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

@TeleOp
public class __HTECH_TELEOP extends LinearOpMode implements StateMachine
{
    public SampleMecanumDrive drive;
    public Hardware hardware;

    public StickyGamepad stickyGamepad1;
    public StickyGamepad stickyGamepad2;
    
    public State state = State.MOVING;


    
    @Override
    public void runOpMode()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        stickyGamepad1 = new StickyGamepad(gamepad1, this);
        stickyGamepad2 = new StickyGamepad(gamepad2, this);
        
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(__HTECH_AUTO.START_POSE);
        
        hardware = new Hardware(hardwareMap);

        hardware.resetEncoders();

        //ColorSensor color = null;

        waitForStart();
        
        hardware.glisOriz.moveToIdle();
        hardware.sleeve.moveToIdle();
        hardware.bratCleste.moveToIdle();
        hardware.cleste.close();

        while (opModeIsActive())
        {
            //color = this.hardwareMap.get(ColorSensor.class, "cSensor");

            stickyGamepad1.update();
            stickyGamepad2.update();
            
            drive.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger);
            
            if (gamepad1.touchpad) requestOpModeStop();
            
            switch (state)
            {
                case EMPTY:
                {
                    double glisPow = MathUtils.expPower(gamepad2.right_trigger - gamepad2.left_trigger, 2);

                    //apucare conurilor din stack
                    if(Math.abs(gamepad2.left_stick_y) > Math.abs(gamepad2.left_stick_x))
                    {
                        if(gamepad2.left_stick_y < 0) //5 conuri in stack
                        {
                            hardware.bratCleste.moveToGrab(5);
                        }
                        else //3 conuri in stack
                        {
                            hardware.bratCleste.moveToGrab(3);
                        }
                    }
                    else if(Math.abs(gamepad2.left_stick_x) > Math.abs(gamepad2.left_stick_y))
                    {
                        if(gamepad2.left_stick_x > 0) //4 conuri in stack
                        {
                            hardware.bratCleste.moveToGrab(4);
                        }
                        else //2 conuri in stack
                        {
                            hardware.bratCleste.moveToGrab(2);
                        }
                    }

                    //apucare con de pe jos
                    if(gamepad2.dpad_down)
                        hardware.bratCleste.moveToGrab(1);

                    // Retractarea glisierelor orizontale
                    if (glisPow < 0 && hardware.glisOriz.getCurrentPosition() > GlisiereOrizontale.POZITIE_MIN)
                    {
                        hardware.glisOriz.setMode(RUN_WITHOUT_ENCODER);
                        hardware.glisOriz.setPower(glisPow);
                    }
                    // Extinderea gliserelor orizontale
                    else if (glisPow > 0 && hardware.glisOriz.getCurrentPosition() < GlisiereOrizontale.POZITIE_MAX)
                    {
                        hardware.glisOriz.setMode(RUN_WITHOUT_ENCODER);
                        hardware.glisOriz.setPower(glisPow);
                    }
                    else
                    {
                        hardware.glisOriz.setPower(0);
                    }

                    // Claw movement
                    if (stickyGamepad2.left_bumper)
                    {
                        if (hardware.cleste.isClosed) hardware.cleste.open();
                        else hardware.cleste.close();
                    }

                    //telemetry.addData("red", color.red());
                    //telemetry.addData("blue", color.blue());

                    // Grab cone
                    if (stickyGamepad2.x)
                    {
                        state = State.BUSY;
                        new Thread(new GrabCone(hardware, this)).start();
                    }

                    // Lift claw
                    if (stickyGamepad2.dpad_up)
                    {
                        state = State.BUSY;
                        new Thread(new Unreset(hardware, this)).start();
                    }

                    if (stickyGamepad1.left_bumper)
                    {
                        hardware.glisOriz.moveToIdle();
                        hardware.bratCleste.moveToIdle();
                        state = State.MOVING;
                    }
                    
                    break;
                }

                case MOVING:
                {
                    if (stickyGamepad1.left_bumper)
                    {
                        hardware.bratCleste.moveToGrab(1);
                        state = State.EMPTY;
                    }
                    
                    break;
                }
                
                case READY_TO_PLACE:
                {
                    // Drop cone
                    if (stickyGamepad1.x)
                    {
                        state = State.BUSY;
                        new Thread(new DropCone(hardware, true, this)).start();
                    }
                    
                    // Drop cone without dropping claw
                    if (stickyGamepad1.b)
                    {
                        state = State.BUSY;
                        new Thread(new DropCone(hardware, false, this)).start();
                    }

                    // Move to low junction
                    if (stickyGamepad2.a)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.LOW, this)).start();
                    }

                    // Move to mid junction
                    if (stickyGamepad2.b)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.MID, this)).start();
                    }

                    // Move to high junction
                    if (stickyGamepad2.y)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.HIGH, this)).start();
                    }
                    break;
                }
                    
                case HAS_CONE:
                {
                    // Reset
                    if (stickyGamepad2.dpad_down)
                    {
                        state = State.BUSY;
                        new Thread(new Reset(hardware, this)).start();
                    }

                    // Prepare for placing on low junction
                    if (stickyGamepad2.a)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.LOW, this)).start();
                    }

                    // Prepare for placing on mid junction
                    if (stickyGamepad2.b)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.MID, this)).start();
                    }

                    // Prepare for placing on high junction
                    if (stickyGamepad2.y)
                    {
                        state = State.BUSY;
                        new Thread(new PrepareCone(hardware, PrepareCone.Position.HIGH, this)).start();
                    }
                    break;
                }
            }

            telemetry.addData("GlisOrizMotor1", hardware.glisOriz.motor1.isMotorEnabled());
            telemetry.addData("GlisOrizMotor2", hardware.glisOriz.motor2.isMotorEnabled());
            telemetry.addData("GlisVertMotor1", hardware.glisVert.motor1.isMotorEnabled());
            telemetry.addData("GlisVertMotor2", hardware.glisVert.motor2.isMotorEnabled());
            telemetry.addData("leftFront", drive.leftFront.isMotorEnabled());
            telemetry.addData("leftRear", drive.leftRear.isMotorEnabled());
            telemetry.addData("rightFront", drive.rightFront.isMotorEnabled());
            telemetry.addData("rightRear", drive.rightRear.isMotorEnabled());
            telemetry.addData("Poz glis vert", hardware.glisVert.getTargetPosition());
            //telemetry.addData("x = ", )
            telemetry.update();

        }
    }
    
    @Override
    public void setState(State state)
    {
        this.state = state;
    }
}
