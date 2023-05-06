package org.firstinspires.ftc.teamcode.threads;

import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.threads.state.State;
import org.firstinspires.ftc.teamcode.threads.state.StateMachine;

public class Unreset implements Runnable
{
    private final Hardware hardware;
    private final StateMachine opMode;

    public Unreset(Hardware hardware, StateMachine stateMachine)
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
            opMode.setState(State.HAS_CONE);
    }

    public void runThread() throws InterruptedException
    {
        hardware.bratCleste.moveToIdle();
        Thread.sleep(350);

        hardware.sleeve.moveToIdle();
        hardware.glisOriz.moveToIdle();
        hardware.cleste.close();
    }
}
