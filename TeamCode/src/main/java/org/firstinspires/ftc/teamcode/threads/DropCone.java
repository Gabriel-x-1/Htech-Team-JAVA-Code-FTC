package org.firstinspires.ftc.teamcode.threads;

import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

public class DropCone implements Runnable
{
    private final Hardware hardware;
    private final boolean dropClaw;
    private final StateMachine opMode;

    public DropCone(Hardware hardware, boolean dropClaw, StateMachine stateMachine)
    {
        this.hardware = hardware;
        this.dropClaw = dropClaw;
        this.opMode = stateMachine;
    }

    public DropCone(Hardware hardware, boolean dropClaw)
    {
        this(hardware, dropClaw, null);
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
        {
            if (dropClaw) opMode.setState(State.EMPTY);
            else opMode.setState(State.MOVING);
        }
    }

    public void runThread() throws InterruptedException
    {
        hardware.piedica.open();
        hardware.cleste.open();
        if (dropClaw) hardware.bratCleste.moveToGrab(1);
        Thread.sleep(250);

        hardware.sleeve.moveToIdle();
        Thread.sleep(250);
        
        hardware.glisVert.moveToLow();
    }
}
