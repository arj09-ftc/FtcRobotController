package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.Pose2d;

public class BlueSpecimen {
       // round 1 - drop preloaded specimen and move on sample 1 to observation zone
       public static Pose2d INIT_POS = new Pose2d(-15, 60.75, Math.toRadians(90));
       public static Pose2d HANG_SPEC_1 = new Pose2d(-5, 33, Math.toRadians(90));
       //-
       public static Pose2d BACK_POST_HANG = new Pose2d(-29.5, 35, Math.toRadians(90));
       //public static Pose2d ALIGN_TO_SAMP_AREA = new Pose2d(-35, 35, Math.toRadians(90));
       //-
       public static Pose2d ALIGN_NEXT_TO_SAMP_1 = new Pose2d(-35, 1, Math.toRadians(90));
       //-
       public static Pose2d SLIDE_UNDER_SAMP_1 = new Pose2d(-42, 9, Math.toRadians(90));
       //-
       public static Pose2d PUSH_SAMP_1 = new Pose2d(-43, 60, Math.toRadians(90));

       // round 2 - move sample 2  to observation zone, pick and hang specimen 2
       //-
       public static Pose2d ALIGN_NEXT_TO_SAMP_2 = new Pose2d(-43, 13, Math.toRadians(90));
       //-
       public static Pose2d SLIDE_UNDER_SAMP_2 = new Pose2d(-51, 13, Math.toRadians(90));
       // adjust a bit on x to allow room for hang

       public static Pose2d HANG_SPEC_2 = new Pose2d(-2, 33, Math.toRadians(90));
       //-
       public static Pose2d PUSH_SAMP_2 = new Pose2d(-51, 60, Math.toRadians(90));

       // round 3 - move sample 3  to observation zone, pick and hang specimen 3
       //-
       public static Pose2d ALIGN_NEXT_TO_SAMP_3 = new Pose2d(-55, 13, Math.toRadians(90));
       //-
       public static Pose2d SLIDE_UNDER_SAMP_3 = new Pose2d(-62, 13, Math.toRadians(90));
       //-
       public static Pose2d PICK_SPEC = new Pose2d(-62, 50, Math.toRadians(90));
       //-
       public static Pose2d ALIGN_NEXT_TO_FINAL_SAMP = new Pose2d(-51, 13, Math.toRadians(90));
       // adjust a bit on x to allow room for hang
       public static Pose2d HANG_SPEC_3 = new Pose2d(1, 33, Math.toRadians(90));
       //-
       public static Pose2d BRACE_WALL_SAMP_3 = new Pose2d(-62, 13, Math.toRadians(90));
       //-
       public static Pose2d PUSH_SAMP_3 = new Pose2d(-62, 60, Math.toRadians(90));
       //-
       public static Pose2d BACK_OPEN_ARM = new Pose2d(-62, 40, Math.toRadians(90));

       // round 4 - pick and hand spc 4
       // adjust a bit on x to allow room for hang
       public static Pose2d HANG_SPEC_4 = new Pose2d(3, 33, Math.toRadians(90));
       //-
       public static Pose2d PARK_POS = new Pose2d(-52, 62, Math.toRadians(90));


}
