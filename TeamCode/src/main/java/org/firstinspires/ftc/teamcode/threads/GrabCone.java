package org.firstinspires.ftc.teamcode.threads;

import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

public class GrabCone implements Runnable
{
    private final Hardware hardware;
    private final StateMachine opMode;
    private boolean autonomousDelay = false;
    
    public GrabCone(Hardware hardware, StateMachine stateMachine)
    {
        this.hardware = hardware;
        this.opMode = stateMachine;
    }
    public GrabCone(Hardware hardware, boolean autonomousDelay)
    {
        this(hardware, null);
        this.autonomousDelay = autonomousDelay;
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
            opMode.setState(State.HAS_CONE);
    }

    public void runThread() throws InterruptedException
    {
        if (!hardware.cleste.isClosed)
        {
            hardware.cleste.close();
            Thread.sleep(200);
        }
        hardware.piedica.open();

        hardware.glisVert.moveToLow();
        while (hardware.glisVert.isBusy()) Thread.yield();

        hardware.sleeve.moveToGSpot();
        hardware.bratCleste.moveToGSpot();
        if (autonomousDelay) Thread.sleep(500);
        
        hardware.glisOriz.moveToGSpot();
        Thread.sleep(1000);

        hardware.cleste.open();
        Thread.sleep(350);
        
        hardware.bratCleste.moveToIdle();
        Thread.sleep(350);

        hardware.sleeve.moveToIdle();
        hardware.glisOriz.moveToIdle();
        hardware.cleste.close();
    }
}
