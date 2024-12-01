package org.firstinspires.ftc.teamcode.drive.opmode;

import static org.firstinspires.ftc.teamcode.drive.opmode.SampCoords.INIT_POS;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Config
@Autonomous(name = "SpecimenAutoWithSpline", group = "Autonomous")

public class SpecimenAutoWithSpline extends LinearOpMode {
    //UPDATED COORDINATES
    public static Pose2d INIT_POS = new Pose2d(-25, 60.75, Math.toRadians(90));
    public static final double H = Math.toRadians(90);
    public static Vector2d SLIDE_UNDER_SAMP_1 = new Vector2d(-42, 9);
    public static Vector2d PUSH_SAMP_1 = new Vector2d(-43, 60);
    public static Vector2d SLIDE_UNDER_SAMP_2 = new Vector2d(-51, 13);
    public static Vector2d PUSH_SAMP_2 = new Vector2d(-51, 60);
    public static Vector2d SLIDE_UNDER_SAMP_3 = new Vector2d(-62, 13);
    public static Vector2d PUSH_SAMP_3 = new Vector2d(-62, 60);

    RobotHardware myHardware;
    IncredibotsArmControl armControl;
    MecanumDrive drive;
    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new RobotHardware(this.hardwareMap);
        armControl = new IncredibotsArmControl(gamepad2, myHardware);
        drive = new MecanumDrive(this.hardwareMap, INIT_POS);

        //Put all the actions here, but replace the sample pushing with continuous splines below

        Action SamplePushingWithSplines = drive.actionBuilder(INIT_POS)

                .splineToConstantHeading(PUSH_SAMP_1, H)
                .splineToConstantHeading(SLIDE_UNDER_SAMP_1, H)
                .splineToConstantHeading(SLIDE_UNDER_SAMP_2, H)
                .splineToConstantHeading(PUSH_SAMP_2, H)
                .splineToConstantHeading(SLIDE_UNDER_SAMP_2, H)
                .splineToConstantHeading(SLIDE_UNDER_SAMP_3, H)
                .splineToConstantHeading(PUSH_SAMP_3, H)
                        .build();

        waitForStart();

        while (opModeIsActive()) {
            break;
            //COMPILE ACTIONS HERE
        }

    }

    /*
    !!!!!!!!!!!!!!!THESE ARE COORDINATES THAT NEED TO BE UPDATED IN THE BLUE SPECIMEN CLASS!!!!!!!!!!!!!!
    public static final double H = Math.toRadians(90);
           public static Vector2d SLIDE_UNDER_SAMP_1 = new Vector2d(-42, 9);
       public static Vector2d PUSH_SAMP_1 = new Vector2d(-43, 60);
       public static Vector2d SLIDE_UNDER_SAMP_2 = new Vector2d(-51, 13);
       public static Vector2d PUSH_SAMP_2 = new Vector2d(-51, 60);
       public static Vector2d SLIDE_UNDER_SAMP_3 = new Vector2d(-62, 13);
       public static Vector2d PUSH_SAMP_3 = new Vector2d(-62, 60);



       !!!THE SPLINES TO PUSH ALL 3 SAMPLES!!!
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_1, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_1, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_2, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_2, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_2, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_3, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_3, BlueSpecimen.H)
     */
}
