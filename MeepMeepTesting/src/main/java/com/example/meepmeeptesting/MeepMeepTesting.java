package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;


public class MeepMeepTesting
{
    public static Pose2d START_POSE = new Pose2d(-35.75, -60, Math.toRadians(90));
    public static Pose2d HIGH_JUNCTION = new Pose2d(-29, -6.409553609918726, Math.toRadians(180 - 127.17570686340325));
    public static Pose2d SUBSTATION = new Pose2d(-38.5, -12.224028253751761, Math.toRadians(0));
    public static Pose2d MID_JUNCTION = new Pose2d(-31.3, -17.125836277763197, Math.toRadians(180 - 219.97944763302803));
    
    public static void main(String[] args) 
    {
        MeepMeep meepMeep = new MeepMeep(700);

        Pose2d START_POSE = new Pose2d(-35.75, -60, Math.toRadians(90)); //!!! pozitie autonomie

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 30, 2.2122051239013674, Math.toRadians(60), 16.15)
                .setStartPose(START_POSE)
                .setDimensions(13.976, 16.929)
                .followTrajectorySequence(drive -> {
                    TrajectorySequenceBuilder traiectorie = drive.trajectorySequenceBuilder(START_POSE);

                    // Place preload on high junction
                    traiectorie
                            .setReversed(false)
                            .forward(22)
                            .splineTo(HIGH_JUNCTION.vec(), HIGH_JUNCTION.getHeading());
                            for (int i = 5; i > 4; i--)
                            {
                                traiectorie
                                // Grab cone from substation
                                .setReversed(true)
                                    .lineToLinearHeading(SUBSTATION);

                                traiectorie
                                        .setReversed(false)
                                        .lineToLinearHeading(MID_JUNCTION);
                            }

                            traiectorie
                                    .setReversed(true)
                                    //.splineTo(new Vector2d(-60, -12), Math.toRadians(180)); right = 12 -- left
                                    //.splineTo(new Vector2d(-35, -30), Math.toRadians(-90)); --mid
                                    .splineTo(new Vector2d(-12, -25), Math.toRadians(-90));
                    return traiectorie.build();
                });
        
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}