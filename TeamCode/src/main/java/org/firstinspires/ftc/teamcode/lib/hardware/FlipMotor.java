package org.firstinspires.ftc.teamcode.lib.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class FlipMotor
{
    public final DcMotor motor;

    public final double[] powers;
    public int powerIndex;

    public FlipMotor(DcMotor motor, double defaultPower, double... powers)
    {
        if (motor == null)
        {
            throw new IllegalArgumentException("Motor must not be null");
        }

        if (powers == null || powers.length == 0)
        {
            throw new IllegalArgumentException(motor.getDeviceName() + ": Powers array must not be empty");
        }

        this.motor = motor;
        this.powers = powers;

        this.powerIndex = -1;
        for (int i = 0; i < powers.length; i++)
        {
            if (powers[i] == defaultPower) this.powerIndex = i;
        }

        if (this.powerIndex == -1)
        {
            throw new IllegalArgumentException(motor.getDeviceName() + ": Powers array must include default power");
        }
    }

    public void update(boolean justPressed)
    {
        if (justPressed) powerIndex++;
        if (powerIndex == powers.length) powerIndex = 0;

        motor.setPower(powers[powerIndex]);
    }
}
