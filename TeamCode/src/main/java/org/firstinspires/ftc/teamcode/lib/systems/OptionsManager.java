package org.firstinspires.ftc.teamcode.lib.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.checkerframework.framework.qual.FromByteCode;
import org.firstinspires.ftc.teamcode.lib.hardware.StickyGamepad;

import java.util.ArrayList;
import java.util.List;

public final class OptionsManager
{
    public final OpMode opMode;
    public final StickyGamepad stickyGamepad;

    public int[] values = {};
    private final List<DriverOption> list = new ArrayList<>();
    private int optionsIndex = 0;

    private int lastStickDirection = 0;
    private double canUseStickAfter = 0;
    
    public OptionsManager(OpMode opMode, StickyGamepad stickyGamepad) 
    {
        this.opMode = opMode;
        this.stickyGamepad = stickyGamepad;
    }
    
    public void start()
    {
        // Assign values to all options with default values until an option without a default value is encountered
        while (optionsIndex < list.size() && list.get(optionsIndex).defaultIndex != -1)
        {
            DriverOption option = list.get(optionsIndex);
            option.selectionIndex = option.defaultIndex;
            optionsIndex++;
        }

        // If there are options which have not been set yet, abort the OpMode
        if (optionsIndex < list.size())
        {
            opMode.requestOpModeStop();
        }
        else
        {
            values = new int[list.size()];
            int index = 0;
            for (DriverOption o : list)
            {
                values[index] = o.getValue();
                index++;
            }
        }
    }
    
    public boolean isReady()
    {
        for (int i = optionsIndex; i < list.size(); i++)
        {
            DriverOption option = list.get(i);
            if (option.defaultIndex == -1) return false;
        }
        return true;
    }
    
    public void update()
    {
        // Display all options until the currently selected one
        for (int i = 0; i < optionsIndex; i++)
        {
            DriverOption option = list.get(i);
            opMode.telemetry.addLine(option.name + ": " + option.options[option.selectionIndex] + (option.selectionIndex == option.defaultIndex ? " (DEFAULT)" : ""));
        }

        // If there is an option selected
        if (optionsIndex < list.size())
        {
            // Display the option with a dropdown of all of the possible values
            DriverOption option = list.get(optionsIndex);
            opMode.telemetry.addLine((optionsIndex != 0 ? "\n" : "") + option.name + ": ...");
            for (int i = 0; i < option.options.length; i++)
            {
                opMode.telemetry.addLine((i == option.selectionIndex ? ">" : "  ") + option.options[i] +
                        (i == option.defaultIndex ? " (DEFAULT)" : "") + 
                        (i == option.options.length - 1 && optionsIndex != list.size() - 1 ? "\n" : ""));
            }

            // Change currently selected value based on up/down presses
            if (stickyGamepad.dpad_down || (opMode.gamepad1.left_stick_y < 0 && canUseStickDown()))
            {
                lastStickDirection = -1;
                canUseStickAfter = opMode.getRuntime() + 0.25;

                option.selectionIndex++;
                if (option.selectionIndex == option.options.length) option.selectionIndex = 0;

                opMode.telemetry.clear();
            }
            else if (stickyGamepad.dpad_up || (opMode.gamepad1.left_stick_y > 0 && canUseStickUp()))
            {
                lastStickDirection = 1;
                canUseStickAfter = opMode.getRuntime() + 0.25;

                option.selectionIndex--;
                if (option.selectionIndex < 0) option.selectionIndex = option.options.length - 1;

                opMode.telemetry.clear();
            }

            // If A is pressed, move to next option
            if (stickyGamepad.a && !stickyGamepad.b && !opMode.gamepad1.start)
            {
                optionsIndex++;

                opMode.telemetry.clear();
            }
        }

        // Display all options after the currently selected one
        for (int i = optionsIndex + 1; i < list.size(); i++)
        {
            DriverOption option = list.get(i);
            opMode.telemetry.addLine(option.name + ": " + (option.defaultIndex == -1 ? "..." : option.options[option.defaultIndex] + " (DEFAULT)"));
        }

        // If B is pressed, move to previous option
        if (stickyGamepad.b && !stickyGamepad.a && !opMode.gamepad1.start)
        {
            optionsIndex--;
            if (optionsIndex < 0) optionsIndex = 0;

            opMode.telemetry.clear();
        }
        
        opMode.telemetry.update();
    }
    
    public void add(String name, String[] options, int def)
    {
       list.add(new DriverOption(name, options, def));
    }
    
    public void add(String name, int def, String... options)
    {
        list.add(new DriverOption(name, options, def));
    }

    public void add(String name, String... options)
    {
        add(name, options, -1);
    }
    
    private boolean canUseStickUp()
    {
        return lastStickDirection != 1 || opMode.getRuntime() > canUseStickAfter;
    }

    private boolean canUseStickDown()
    {
        return lastStickDirection != -1 || opMode.getRuntime() > canUseStickAfter;
    }

    private static class DriverOption
    {
        String name;
        String[] options;
        int selectionIndex;
        int defaultIndex;

        DriverOption(String name, String[] options, int def)
        {
            this.name = name;
            this.options = options;
            this.selectionIndex = 0;
            this.defaultIndex = def;
        }

        int getValue()
        {
            return selectionIndex;
        }
    }
}
