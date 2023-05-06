package org.firstinspires.ftc.teamcode.hardware;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.base.TwoMotorSystem;

@Config
public class GlisiereVerticale extends TwoMotorSystem
{
    public GlisiereVerticale(HardwareMap map, String nume1, String nume2)
    {
        super(map, nume1, nume2);
    }

    // Pozitiile glisierelor verticale pentru a ajunge la junctionuri
    public static int POZITIE_JUNCTION_LOW = 10, POZITIE_JUNCTION_MID = 450, POZITIE_JUNCTION_HIGH = 1350;

    public static double VITEZA_MAXIMA = 2;

    public static int POZITIE_FLIP_JUNCTION_HIGH = 600;
    
    public void moveToLow()
    {
        setTargetPosition(POZITIE_JUNCTION_LOW);
        setMode(RUN_TO_POSITION);
        setPower(VITEZA_MAXIMA);
        
        stopOnceTargetReached();
    }

    public void moveToMid()
    {
        setTargetPosition(POZITIE_JUNCTION_MID);
        setMode(RUN_TO_POSITION);
        setPower(VITEZA_MAXIMA);
    }

    public void moveToHigh()
    {
        setTargetPosition(POZITIE_JUNCTION_HIGH);
        setMode(RUN_TO_POSITION);
        setPower(VITEZA_MAXIMA);
    }

}
