package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.roadrunner.configs.SampleMecanumDrive;
import org.firstinspires.ftc.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.camera.CameraDetector;
import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.lib.hardware.StickyGamepad;
import org.firstinspires.ftc.teamcode.lib.systems.OptionsManager;
import org.openftc.easyopencv.OpenCvCameraFactory;

@Autonomous(preselectTeleOp = "_HTECH_TELEOP")
@Config
public class testauto extends LinearOpMode
{
    public static int MAX_CONES = 4;

    public static Pose2d START_POSE = new Pose2d(35.75, -62, Math.toRadians(90));
    public static Pose2d HIGH_JUNCTION = new Pose2d(29, -6.409553609918726, Math.toRadians(127.17570686340325));
    public static Pose2d SUBSTATION = new Pose2d(40, -12.224028253751761, Math.toRadians(180));
    public static Pose2d MID_JUNCTION = new Pose2d(31.3, -17.125836277763197, Math.toRadians(219.97944763302803));

    public SampleMecanumDrive drive;
    public Hardware hardware;
    public CameraDetector camera;

    public StickyGamepad stickyGamepad1;
    public OptionsManager options;

    @Override
    public void runOpMode()
    {
        stickyGamepad1 = new StickyGamepad(gamepad1, this);
        options = new OptionsManager(this, stickyGamepad1);
        options.add("SIDE", "LEFT", "RIGHT");
        options.add("PRELOAD", "HIGH", "MID");
        options.add("CYCLING","HIGH", "MID", "NONE");

//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new SampleMecanumDrive(hardwareMap);
        hardware = new Hardware(hardwareMap);
        hardware.resetEncoders();

        camera = new CameraDetector(OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1")));

        SampleMecanumDrive.HEADING_PID.kP = SampleMecanumDrive.TRANSLATIONAL_PID.kP = 8;

        TrajectorySequence mainTrajectory = null;
        TrajectorySequence parkTrajectory = null;
        CameraDetector.Result lastBuilt = null;

        while (opModeInInit() && !options.isReady())
        {
            stickyGamepad1.update();
            options.update();
        }
        if (isStopRequested()) return;

        options.start();

        while (opModeInInit())
        {
            CameraDetector.Result randResult = camera.detect();
            telemetry.addLine("PARKING: " + randResult);
            telemetry.update();

            if (randResult != lastBuilt)
            {
                mainTrajectory = buildMainTrajectory();
                parkTrajectory = buildParkTrajectory(mainTrajectory, randResult);
                lastBuilt = randResult;
            }
        }
        camera.stop();
        if (isStopRequested()) return;

        drive.setPoseEstimate(maybeMirror(START_POSE, false));

        drive.followTrajectorySequence(mainTrajectory);
        drive.followTrajectorySequence(parkTrajectory);
    }

    public TrajectorySequence buildMainTrajectory()
    {
        boolean placeOnHigh = options.values[2] == 0;
        boolean doCycling = options.values[2] != 2;
        boolean preloadOnHigh = options.values[1] == 0;

        TrajectorySequenceBuilder mainTrajectoryBuilder = drive.trajectorySequenceBuilder(maybeMirror(START_POSE, false));

        // Place preload on target junction
        mainTrajectoryBuilder
                .setReversed(false)

                //.lineToLinearHeading(preloadOnHigh ? new Pose2d(35.75,-15 , Math.toRadians(90)) : new Pose2d(35.75,-30 , Math.toRadians(90)))
                .forward(10)
                /*
                .splineTo(maybeMirror(preloadOnHigh ? HIGH_JUNCTION.vec() : MID_JUNCTION.vec()),
                        maybeMirror(preloadOnHigh ? HIGH_JUNCTION.getHeading() : MID_JUNCTION.getHeading(), false))

                 */
                .waitSeconds(0.6);

        if (doCycling)
        {
            MAX_CONES = Math.min(5, Math.max(1, MAX_CONES));
            for (int i = 5; i > 5 - MAX_CONES; i--)
            {
                int m = i;

                // Grab cone from substation
                mainTrajectoryBuilder
                        .setReversed(true)
                        .lineToLinearHeading(maybeMirror(SUBSTATION, true))
                        .waitSeconds(0.7)
                        .waitSeconds(1.8);

                // Place cone on target junction
                mainTrajectoryBuilder
                        .setReversed(false)
                        .lineToLinearHeading(maybeMirror(placeOnHigh ? HIGH_JUNCTION : MID_JUNCTION, true))
                        //.waitSeconds(0.3) //0.5 before
                        .waitSeconds(0.1); //0.75 before
            }
        }

        return mainTrajectoryBuilder.build();
    }

    public TrajectorySequence buildParkTrajectory(TrajectorySequence mainTrajectory, CameraDetector.Result randResult)
    {
        boolean parkFromHigh = options.values[2] != 1;

        switch (randResult)
        {
            case LEFT:
                return drive.trajectorySequenceBuilder(mainTrajectory.end())
                        .UNSTABLE_addTemporalMarkerOffset(0, () ->
                        {
                            hardware.sleeve.moveToDrop();
                            hardware.glisOriz.moveToZero();
                        })

                        .back(6)
                        .turn(maybeMirror(parkFromHigh ? Math.toRadians(53) : -Math.toRadians(40), true))

                        .setReversed(false)
                        .UNSTABLE_addTemporalMarkerOffset(1, () ->
                        {
                            hardware.sleeve.moveToIdle();
                        })
                        .lineToLinearHeading(options.values[0] == 1 ? new Pose2d(10 ,-10 , Math.toRadians(90)) : new Pose2d(-60 ,-10 , Math.toRadians(90)))
                        .back(5)
                        .build();

            case MID:
                return drive.trajectorySequenceBuilder(mainTrajectory.end())
                        .UNSTABLE_addTemporalMarkerOffset(0, () ->
                        {
                            hardware.sleeve.moveToDrop();
                            hardware.glisOriz.moveToZero();
                        })
                        .back(6)


                        .turn(maybeMirror(parkFromHigh ? -Math.toRadians(37) : -Math.toRadians(130), true))

                        .UNSTABLE_addTemporalMarkerOffset(1, () ->
                        {
                            hardware.sleeve.moveToIdle();
                        })
                        .back(12)
                        .build();

            case RIGHT:
                return drive.trajectorySequenceBuilder(mainTrajectory.end())
                        .UNSTABLE_addTemporalMarkerOffset(0, () ->
                        {
                            hardware.sleeve.moveToDrop();
                            hardware.glisOriz.moveToZero();
                        })

                        .back(6)
                        .turn(maybeMirror(parkFromHigh ? Math.toRadians(53) : -Math.toRadians(40), true))

                        .setReversed(true)


                        .UNSTABLE_addTemporalMarkerOffset(1, () ->
                        {
                            hardware.sleeve.moveToIdle();
                        })
                        .lineToLinearHeading(options.values[0] == 1 ? new Pose2d(60 ,-10 , Math.toRadians(90)) : new Pose2d(-10 ,-10 , Math.toRadians(90)))
                        /*
                        .back(parkFromHigh ? 22 : 20)
                        .turn(maybeMirror(-Math.toRadians(90), true))
                        */
                        .build();

            default:
                return drive.trajectorySequenceBuilder(mainTrajectory.end())
                        .forward(1)
                        .build();
        }
    }

    private Pose2d maybeMirror(Pose2d in, boolean rot)
    {
        boolean isReversed = options.values[0] == 0;
        if (!isReversed) return in;

        return new Pose2d(-in.getX(), in.getY(), rot ? maybeMirror(in.getHeading(), false) : in.getHeading());
    }

    private Vector2d maybeMirror(Vector2d in)
    {
        boolean isReversed = options.values[0] == 0;
        if (!isReversed) return in;

        return new Vector2d(-in.getX(), in.getY());
    }

    private double maybeMirror(double in, boolean offset)
    {
        boolean isReversed = options.values[0] == 0;
        if (!isReversed) return in;

        return offset ? -in : Math.toRadians(180) - in;
    }
}
