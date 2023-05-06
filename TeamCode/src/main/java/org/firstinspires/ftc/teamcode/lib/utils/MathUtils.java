package org.firstinspires.ftc.teamcode.lib.utils;

public class MathUtils
{
    public static double expPower(double input, double pow)
    {
        int sign = input < 0 ? -1 : 1;
        double powd = Math.abs(Math.pow(input, pow));
        return powd * sign;
    }
}
