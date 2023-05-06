package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Piedica
{
    public Servo piedica;

    public Piedica(HardwareMap map, String name)
    {
        piedica = map.get(Servo.class, name);
    }

    public static double POZITIE_DESCHISA = 1;
    
    public static double POZITIE_INCHISA = 0;
    
    public void open()
    {
        piedica.setPosition(POZITIE_DESCHISA);
    }
    
    public void close()
    {
        piedica.setPosition(POZITIE_INCHISA);
    }
}
