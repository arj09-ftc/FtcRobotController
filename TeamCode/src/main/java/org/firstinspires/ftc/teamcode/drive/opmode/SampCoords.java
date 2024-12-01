package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.Pose2d;

public class SampCoords {
    public static final double HEADING = Math.toRadians(-90);
    public static final double DROP_SAMPLE_HEADING = Math.toRadians(45);

    public static Pose2d INIT_POS = new Pose2d(38, 62, HEADING);
    public static Pose2d OPEN_ARM = new Pose2d(38, 40, HEADING);
    public static Pose2d HANG_SPECIMEN = new Pose2d(5, 33, HEADING);
    public static Pose2d BACK_POST_HANG = new Pose2d(-36, 35, HEADING);
    public static Pose2d ALIGN_NEXT_TO_SAMP_1 = new Pose2d(-36, 9, HEADING);
    public static Pose2d SLIDE_UNDER_SAMP_1 = new Pose2d(-42, 9, HEADING);
    public static Pose2d PUSH_SAMP_1 = new Pose2d(-43, 60, HEADING);
    public static Pose2d BACK_OPEN_ARM = new Pose2d(-62, 40, HEADING);

    public static Pose2d PARK_POS = new Pose2d(22, 10, HEADING);


    public static Pose2d DROP_SAMP = new Pose2d(53, 53, DROP_SAMPLE_HEADING);
    public static Pose2d COMMON_ARM_CLOSE_POS = new Pose2d(37, 37, HEADING);

    public static Pose2d DROP_UNDER_SAMPLES = new Pose2d(37, 4, HEADING);
    public static Pose2d ALIGN_WITH_SAMP_1 = new Pose2d(48, 38, HEADING);
    public static Pose2d OPEN_ARM_WHILE_MOVING = new Pose2d(48, 48, HEADING);
    public static Pose2d IN_FRONT_OF_ASCENT_ZONE = new Pose2d(37, 10, HEADING);


    public static Pose2d ALIGN_WITH_SAMP_2 = new Pose2d(58, 38, HEADING);
    public static Pose2d PICK_SPEC = new Pose2d(-62, 50, HEADING);
    public static Pose2d PICK_3RD_SAMP = new Pose2d(62, 45, HEADING);
}
