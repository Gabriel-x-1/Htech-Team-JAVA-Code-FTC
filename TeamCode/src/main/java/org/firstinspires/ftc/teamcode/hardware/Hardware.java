package org.firstinspires.ftc.teamcode.hardware;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware
{
    public GlisiereOrizontale glisOriz;
    public Cleste cleste;
    public BratCleste bratCleste;
    public GlisiereVerticale glisVert;
    public Sleeve sleeve;
    public Piedica piedica;
    
    public Hardware(HardwareMap hardwareMap)
    {
        glisOriz = new GlisiereOrizontale(hardwareMap, "gos", "god");
        glisOriz.setZeroPowerBehaviour(BRAKE);
        glisOriz.setMode(RUN_WITHOUT_ENCODER);

        cleste = new Cleste(hardwareMap, "cleste");
        
        bratCleste = new BratCleste(hardwareMap, "brat", "joint420");

        glisVert = new GlisiereVerticale(hardwareMap, "gvs", "gvd");
        glisVert.setZeroPowerBehaviour(BRAKE);
        glisVert.setMode(RUN_WITHOUT_ENCODER);

        sleeve = new Sleeve(hardwareMap, "sleeveS", "sleeveD");
        piedica = new Piedica(hardwareMap, "pslv");
    }
    
    public void resetEncoders()
    {
        glisOriz.setMode(STOP_AND_RESET_ENCODER);
        glisOriz.setMode(RUN_WITHOUT_ENCODER);

        glisVert.setMode(STOP_AND_RESET_ENCODER);
        glisVert.setMode(RUN_WITHOUT_ENCODER);
    }
}
