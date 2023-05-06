package org.firstinspires.ftc.teamcode.threads;

import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

public class Reset implements Runnable
{
    private final Hardware hardware;
    private final StateMachine opMode;

    public Reset(Hardware hardware, StateMachine stateMachine)
    {
        this.hardware = hardware;
        this.opMode = stateMachine;
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
            opMode.setState(State.EMPTY);
    }

    public void runThread() throws InterruptedException
    {
        hardware.glisVert.moveToLow();
        hardware.cleste.open();
        hardware.bratCleste.moveToGrab(1);
    }
}
