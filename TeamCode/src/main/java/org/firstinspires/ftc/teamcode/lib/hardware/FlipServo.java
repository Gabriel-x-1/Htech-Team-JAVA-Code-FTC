package org.firstinspires.ftc.teamcode.lib.hardware;

import com.qualcomm.robotcore.hardware.CRServo;

public final class FlipServo
{
    public final CRServo servo;

    public final double[] powers;
    public int powerIndex;

    public FlipServo(CRServo servo, double defaultPower, double... powers)
    {
        if (servo == null)
        {
            throw new IllegalArgumentException("Servo must not be null");
        }

        if (powers == null || powers.length == 0)
        {
            throw new IllegalArgumentException(servo.getDeviceName() + ": Powers array must not be empty");
        }

        this.servo = servo;
        this.powers = powers;

        this.powerIndex = -1;
        for (int i = 0; i < powers.length; i++)
        {
            if (powers[i] == defaultPower) this.powerIndex = i;
        }

        if (this.powerIndex == -1)
        {
            throw new IllegalArgumentException(servo.getDeviceName() + ": Powers array must include default power");
        }
    }

    public void update(boolean justPressed)
    {
        if (justPressed) powerIndex++;
        if (powerIndex == powers.length) powerIndex = 0;

        servo.setPower(powers[powerIndex]);
    }
}
