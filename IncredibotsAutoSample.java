package org.firstinspires.ftc.teamcode.drive.opmode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "IncredibotsAutoSpecimen", group = "Autonomous")
/* This auto opmode will do the following steps:
 1) Robot will start from the inside edge of 5th tile
 2) The robot will go forward 29 and take a 135 degree turn
 3) The robot will go forward and lift arm up to high basket position
 4) The robot will reverse the servo and drop the piece
 5) The robot will back up while retracting the arm to rest position. REST POSITION.
 */
public class IncredibotsAutoSample extends LinearOpMode {

    //Observation starting position - 102.9 - inside edge of 5th tile from observatory zone wall


    public static double START_X = 8.504;
    public static double START_Y = 102.9;
    public static double START_H = Math.toRadians(0);
    Pose2d startPose = new Pose2d(START_X, START_Y, START_H);

    public static double STEP1_X = 29;
    public static double STEP1_Y = 109.65;
    public static double STEP1_H = Math.toRadians(135);
    Pose2d step1Pose = new Pose2d(STEP1_X, STEP1_Y, STEP1_H);
    //ADD ARM MOTIONS HERE

    public static double STEP2_X = 27;
    public static double STEP2_Y = 155.9;
    public static double STEP2_H = Math.toRadians(135);
    Pose2d step2Pose = new Pose2d(STEP2_X, STEP2_Y, STEP2_H);
    //ADD ARM MOTIONS HERE

    public static double STEP3_X = 32;
    public static double STEP3_Y = 155.9;
    public static double STEP3_H = Math.toRadians(0);
    Pose2d step3Pose = new Pose2d(STEP3_X, STEP3_Y, STEP3_H);
    //ADD ARM MOTIONS HERE

    public static double STEP4_X = 38.5;
    public static double STEP4_Y = 117.9;
    public static double STEP4_H = Math.toRadians(90);
    Pose2d step4Pose = new Pose2d(STEP4_X, STEP4_Y, STEP4_H);
    //ADD ARM MOTIONS HERE

    public static double STEP5_X = 27;
    public static double STEP5_Y = 155.9;
    public static double STEP5_H = Math.toRadians(135);
    Pose2d step5Pose = new Pose2d(STEP5_X, STEP5_Y, STEP5_H);

    public static double STEP6_X = 32;
    public static double STEP6_Y = 155.9;
    public static double STEP6_H = Math.toRadians(0);
    Pose2d step6Pose = new Pose2d(STEP6_X, STEP6_Y, STEP6_H);

    public static double STEP7_X = 0;
    public static double STEP7_Y = 0;
    public static double STEP7_H = Math.toRadians(0);
    Pose2d step7Pose = new Pose2d(STEP7_X, STEP7_Y, STEP7_H);


    RobotHardware myHardware;
    IncredibotsArmControl armControl;
    MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new RobotHardware(this.hardwareMap);
        armControl = new IncredibotsArmControl(gamepad2, myHardware);
        drive = new MecanumDrive(this.hardwareMap, startPose);

        Action alignToMoveToHighBasket = drive.actionBuilder(startPose)
                .strafeTo(step1Pose.position)
                .build();
        Action moveToDropSample = drive.actionBuilder(step1Pose)
                .strafeTo(step2Pose.position)
                .build();
        Action moveBackToPickSample = drive.actionBuilder(step2Pose)
                .strafeTo(step3Pose.position)
                .build();
        Action moveToObservationZone = drive.actionBuilder(step1Pose)
                .strafeTo(step2Pose.position)
                .build();


        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new SequentialAction(
                            alignToMoveToHighBasket
                    )
                    new SequentialAction(
                            moveBackToPickSample
                    );
            );
        }
    }
}