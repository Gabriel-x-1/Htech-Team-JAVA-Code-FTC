package org.firstinspires.ftc.teamcode.hardware;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.base.TwoMotorSystem;
import org.openftc.easyopencv.LIFO_OpModeCallbackDelegate;

@Config
public class GlisiereOrizontale extends TwoMotorSystem
{
    public GlisiereOrizontale(HardwareMap map, String nume1, String nume2)
    {
        super(map, nume1, nume2);
    }

    //public static int POZITIE_GSPOT = 430;
    public static int POZITIE_GSPOT = 400;

    public static int POZITIE_IDLE = 160;
    
    public static int POZITIE_MIN = 50;
    
    public static int POZITIE_MAX = 1100;
    
    public void moveToGSpot()
    {
        setTargetPosition(POZITIE_GSPOT);
        setMode(RUN_TO_POSITION);
        setPower(1);
    }
    
    public void moveToIdle()
    {
        setTargetPosition(POZITIE_IDLE);
        setMode(RUN_TO_POSITION);
        setPower(1);
        
        stopOnceTargetReached();
    }
    
    public void moveToMax()
    {
        setTargetPosition(POZITIE_MAX);
        setMode(RUN_TO_POSITION);
        setPower(1);
    }
    public void moveToMax(int poz)
    {
        setTargetPosition(POZITIE_MAX - poz);
        setMode(RUN_TO_POSITION);
        setPower(1);
    }
    public void moveToZero()
    {
        setTargetPosition(POZITIE_MIN);
        setMode(RUN_TO_POSITION);
        setPower(1);
        stopOnceTargetReached();
    }
}
