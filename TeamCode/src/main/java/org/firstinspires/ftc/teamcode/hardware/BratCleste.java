package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class BratCleste
{
    public static class ClawPosition
    {
        public double POZITIE_BRAT;
        public double POZITIE_JOINT;

        public ClawPosition(double posBrat, double posJoint)
        {
            this.POZITIE_BRAT = posBrat;
            this.POZITIE_JOINT = posJoint;
        }
    }

    public Servo brat, joint;

    public BratCleste(HardwareMap map, String nameBrat, String nameJoint)
    {
        brat = map.get(Servo.class, nameBrat);
        joint = map.get(Servo.class, nameJoint);
    }

    public static ClawPosition POZITIE_STACK_5_CONURI = new ClawPosition(0.52, 0.7555);

    public static ClawPosition POZITIE_STACK_4_CONURI = new ClawPosition(0.45, 0.7);

    public static ClawPosition POZITIE_STACK_3_CONURI = new ClawPosition(0.35, 0.57);

    public static ClawPosition POZITIE_STACK_2_CONURI = new ClawPosition(0.32, 0.6);

    // Pozitia bratului clestelui pentru a apuca un con de jos
    public static ClawPosition POZITIE_STACK_1_CON = new ClawPosition(0.17, 0.44);

    // Pozitia bratului clestelui pentru a pune con in sleeve (gspot)
    public static ClawPosition POZITIE_GSPOT = new ClawPosition(0.75, 0.4);

    // Pozitia bratului clestelui pentru a sta vertical
    public static ClawPosition POZITIE_IDLE = new ClawPosition(0.5, 0.6-0.21);
    
    public static ClawPosition POZITIE_CLEAR = new ClawPosition(0.58, 0.8);
    
    public void moveToGSpot()
    {
        brat.setPosition(POZITIE_GSPOT.POZITIE_BRAT);
        joint.setPosition(POZITIE_GSPOT.POZITIE_JOINT);
    }

    public void moveToGrab(int pozitie)
    {
        ClawPosition clawPosition;
        switch (pozitie)
        {
            case 2:
                clawPosition = POZITIE_STACK_2_CONURI;
                break;
                
            case 3:
                clawPosition = POZITIE_STACK_3_CONURI;
                break;
                
            case 4:
                clawPosition = POZITIE_STACK_4_CONURI;
                break;
                
            case 5:
                clawPosition = POZITIE_STACK_5_CONURI;
                break;
            
            default:
                clawPosition = POZITIE_STACK_1_CON;
                break;
        }
        
        brat.setPosition(clawPosition.POZITIE_BRAT);
        joint.setPosition(clawPosition.POZITIE_JOINT);
    }

    public void moveToIdle()
    {
        brat.setPosition(POZITIE_IDLE.POZITIE_BRAT);
        joint.setPosition(POZITIE_IDLE.POZITIE_JOINT);
    }
    
    public void moveToClear()
    {
        brat.setPosition(POZITIE_CLEAR.POZITIE_BRAT);
        joint.setPosition(POZITIE_CLEAR.POZITIE_JOINT);
    }
}
