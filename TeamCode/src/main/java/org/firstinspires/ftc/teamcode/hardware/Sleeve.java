package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Sleeve
{
    public Servo sleeveS;
    public Servo sleeveD;

    public Sleeve(HardwareMap map, String nameS, String nameD)
    {
        sleeveS = map.get(Servo.class, nameS);
        sleeveD = map.get(Servo.class, nameD);
    }

    
    // Pozitita sleeve-ului la care este paralel cu pamantul (pentru drop)
    //public static SleeveServoPosition pozitieSleeveDrop = new SleeveServoPosition(1, 0.04);
    public static SleevePosition POZITIE_DROP = new SleevePosition(0.649, 0.468);

    // Pozitia sleeve-ului la care nu se ciocneste cu nimic
    public static SleevePosition POZITIE_IDLE = new SleevePosition(0.585, 0.5385);

    // Pozitita sleeve-ului la care poate primi un nou con
    //public static SleeveServoPosition pozitieSleeveGSpot = new SleeveServoPosition(0.13, 0.85);
    public static SleevePosition POZITIE_GSPOT = new SleevePosition(0.524, 0.595);
    
    public void moveToDrop()
    {
        sleeveS.setPosition(POZITIE_DROP.POZITIE_LEFT);
        sleeveD.setPosition(POZITIE_DROP.POZITIE_RIGHT);
    }

    public void moveToIdle()
    {
        sleeveS.setPosition(POZITIE_IDLE.POZITIE_LEFT);
        sleeveD.setPosition(POZITIE_IDLE.POZITIE_RIGHT);
    }

    public void moveToGSpot()
    {
        sleeveS.setPosition(POZITIE_GSPOT.POZITIE_LEFT);
        sleeveD.setPosition(POZITIE_GSPOT.POZITIE_RIGHT);
    }
    
    public static class SleevePosition
    {
        public double POZITIE_LEFT;
        public double POZITIE_RIGHT;

        public SleevePosition(double posLeft, double posRight)
        {
            this.POZITIE_LEFT = posLeft;
            this.POZITIE_RIGHT = posRight;
        }
    }
}
