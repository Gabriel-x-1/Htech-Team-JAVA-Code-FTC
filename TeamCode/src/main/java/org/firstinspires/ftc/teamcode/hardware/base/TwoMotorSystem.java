package org.firstinspires.ftc.teamcode.hardware.base;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Aceasta clasa reprezinta un sistem de 2 motoare unde al doilea este in direction REVERSE
public abstract class TwoMotorSystem
{
    public DcMotorEx motor1, motor2;

    public TwoMotorSystem(HardwareMap map, String nume1, String nume2)
    {
        motor1 = map.get(DcMotorEx.class, nume1);
        motor2 = map.get(DcMotorEx.class, nume2);
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior behaviour)
    {
        motor1.setZeroPowerBehavior(behaviour);
        motor2.setZeroPowerBehavior(behaviour);
    }

    public void setMode(DcMotor.RunMode mode)
    {
        motor1.setMode(mode);
        motor2.setMode(mode);
    }

    public void setTargetPosition(int position)
    {
        motor1.setTargetPosition(position);
        motor2.setTargetPosition(position);
    }

    public void setPower(double power)
    {
        motor1.setPower(power);
        motor2.setPower(power);
    }

    public int getCurrentPosition()
    {
        return motor1.getCurrentPosition();
    }
    
    public int getTargetPosition()
    { 
        return motor1.getTargetPosition();    
    }
    
    public boolean isBusy()
    {
        return motor1.isBusy();
    }
    
    private Thread stopThread;
    
    protected void stopOnceTargetReached()
    {
        if (stopThread == null)
        {
            stopThread = new Thread(new StopMotorPower());
            stopThread.start();
        }
    }
    
    private class StopMotorPower implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                runThread();
            }
            catch (InterruptedException e)
            {
                // ignored
            }
            
            stopThread = null;
        }
        
        public void runThread() throws InterruptedException
        {
            while (Math.abs(getCurrentPosition() - getTargetPosition()) > 5) 
                Thread.yield();

            setMode(RUN_WITHOUT_ENCODER);
            setPower(0);
        }
    }
}
