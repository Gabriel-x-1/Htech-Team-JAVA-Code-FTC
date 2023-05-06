package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Cleste
{
    public Servo cleste;
    public Cleste(HardwareMap map, String name)
    {
        cleste = map.get(Servo.class, name);
    }

    public static double POZITIE_DESCHIS = 0.5;
    
    public static double POZITIE_INCHIS = 0.75;
    
    public boolean isClosed = true;
    
    public void open()
    {
        cleste.setPosition(POZITIE_DESCHIS);
        isClosed = false;
    }
    
    public void close()
    {
        cleste.setPosition(POZITIE_INCHIS);
        isClosed = true;
    }
}
