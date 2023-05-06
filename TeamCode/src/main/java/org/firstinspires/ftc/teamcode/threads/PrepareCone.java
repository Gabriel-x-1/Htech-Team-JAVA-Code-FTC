package org.firstinspires.ftc.teamcode.threads;

import org.firstinspires.ftc.teamcode.hardware.GlisiereVerticale;
import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

public class PrepareCone implements Runnable
{
    private final Hardware hardware;
    private final StateMachine opMode;
    private final Position junction;

    public PrepareCone(Hardware hardware, Position junction, StateMachine opMode)
    {
        this.hardware = hardware;
        this.opMode = opMode;
        this.junction = junction;
    }
    
    public PrepareCone(Hardware hardware, Position junction)
    {
        this(hardware, junction, null);
    }

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

        if (opMode != null)
            opMode.setState(State.READY_TO_PLACE);
    }

    public void runThread() throws InterruptedException
    {
        hardware.piedica.close();
        
        // Ridicam glisiera verticala pana la junction-ul corect
        switch (junction)
        {
            case LOW:
                hardware.glisVert.moveToLow();
                break;
            
            case MID:
                hardware.glisVert.moveToMid();
                break;
            
            case HIGH:
                hardware.glisVert.moveToHigh();
                break;
        }
        
        if (junction == Position.HIGH)
        {
            while (hardware.glisVert.isBusy())
            {
                if (hardware.glisVert.getCurrentPosition() > GlisiereVerticale.POZITIE_FLIP_JUNCTION_HIGH)
                    hardware.sleeve.moveToDrop();
            }
        }

        hardware.sleeve.moveToDrop();
    }
    
    public enum Position
    {
        LOW,
        MID,
        HIGH
    }
}
