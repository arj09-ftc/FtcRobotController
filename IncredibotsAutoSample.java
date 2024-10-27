package org.firstinspires.ftc.teamcode.drive.opmode;

import android.util.Log;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

@Config
@Autonomous(name = "IncredibotsAutoSpecimen", group = "Autonomous")
/* This auto opmode will do the following steps:
 1) Robot will start from the inside of the inside edge of the 5th tile
 2) The robot will drop the first preload into the high basket
 3) The robot will align and pick up the 3rd yellow spike mark
 4) The robot will drop the yellow piece into the basket
 5) The robot will go park in the ascent zone.
 */
public class IncredibotsAutoSample extends LinearOpMode {

    //public static int CLAW_ARM_DIFFERENCE_FOR_SNAP = 250;
    //public static int CLAW_ARM_DIFFERENCE_FOR_PICK = 150;

    //!!!!!!!!!!!***********PLEASE FINETUNE THE COORDINATES AND NUMBERS*********!!!!!!!!!!!

    public static double START_X = 40;
    public static double START_Y = 65.75;
    public static double START_H = Math.toRadians(90);
    Pose2d startPose = new Pose2d(START_X, START_Y, START_H);

    //public static double START2_X = 52;
    //public static double START2_Y = 52;
    //public static double START2_H = Math.toRadians(90);
    //Pose2d start2Pose = new Pose2d(START2_X, START2_Y, START2_H);


    //position to snap the preloaded specimen
//    public static double STEP1_X = 0;
//    public static double STEP1_Y = 62.75; //52.5
//    public static double STEP1_H = START_H;
    public static double STEP1_X = 52;
    public static double STEP1_Y = 52; //52.5
    public static double STEP1_H = Math.toRadians(45);
    Pose2d step1Pose = new Pose2d(STEP1_X, STEP1_Y, STEP1_H);

    public static double SERVO_POSX = 52;
    public static double SERVO_POSY = 52;
    public static double SERVO_POSH = Math.toRadians(45);
    Pose2d servoMovePose = new Pose2d(SERVO_POSX, SERVO_POSY, SERVO_POSH);

    //position to pick the second sample
    public static double STEP2_X = 38;
    public static double STEP2_Y = 38;
    public static double STEP2_H = Math.toRadians(0);
    Pose2d step2Pose = new Pose2d(STEP2_X, STEP2_Y, STEP2_H);

    //position to pick the second specimen
    public static double STEP3_X = 38;
    public static double STEP3_Y = 27;
    public static double STEP3_H = Math.toRadians(0);
    Pose2d step3Pose = new Pose2d(STEP3_X, STEP3_Y, STEP3_H);

    //position to snap the second specimen
    public static double STEP4_X = 52;
    public static double STEP4_Y = 52;
    public static double STEP4_H = Math.toRadians(45);
    Pose2d step4Pose = new Pose2d(STEP4_X, STEP4_Y, STEP4_H);

    public static double STEP5_X = 38;
    public static double STEP5_Y = 13;
    public static double STEP5_H = Math.toRadians(-180);
    Pose2d step5Pose = new Pose2d(STEP5_X, STEP5_Y, STEP5_H);

    //parking position
    public static double END_X = 25;
    public static double END_Y = 13;
    public static double END_H = Math.toRadians(-180);
    Pose2d endPose = new Pose2d(END_X, END_Y, END_H);

    RobotHardware myHardware;
    IncredibotsArmControl armControl;
    MecanumDrive drive;


    //!!!!!!!I AM NOT CERTAIN OF MY TRAJECTORY BUILDING. PLEASE FIX ANY ERRORS!!!!!!!!

    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new RobotHardware(this.hardwareMap);
        armControl = new IncredibotsArmControl(gamepad2, myHardware);
        drive = new MecanumDrive(this.hardwareMap, startPose);

        //takes robot to position 1 (linear strafe heading that turns robot to face the basket)
        Action dropLoadedSample = drive.actionBuilder(startPose)
                .strafeToLinearHeading(step1Pose.position, STEP1_H)
                .build();

        Action servoMove = drive.actionBuilder(step1Pose)
                .strafeToLinearHeading(step1Pose.position, STEP1_H)
                .build();

        //takes robot to position 1 strafing and facing the 3 spike marks, not aligned yet with the spike marks
        Action positionToPickNextSample = drive.actionBuilder(step1Pose)
                .strafeToLinearHeading(step2Pose.position, STEP2_H)
                .build();
        //robot goes from above position to right in front of spike marks
        Action strafeDownToPositionWithSpikeSample = drive.actionBuilder(step2Pose)
                .strafeToLinearHeading(step3Pose.position, STEP3_H)
                .build();

        Action moveBackToBucket = drive.actionBuilder(step3Pose)
                .strafeToLinearHeading(step4Pose.position, STEP4_H)
                .build();

        Action inFrontOfAscentZone = drive.actionBuilder(step4Pose)
                .strafeToLinearHeading(step5Pose.position, STEP5_H)
                .build();

        Action parkInAscent = drive.actionBuilder(step5Pose)
                .strafeToLinearHeading(endPose.position, END_H)
                .build();

        //make sure claw is closed.
        myHardware.operateClawServo(false, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");

        waitForStart();

        //!!!!!!!!!!ARM CONTROL. PLEASE FIX ANY ISSUES THAT ARISE. UNCOMMENT ARM COMMANDS TO TEST!!!!!!!!!!

        while (opModeIsActive()) {
            Log.i("=== heading ===", "start: " + myHardware.getRobotYawRadians());

            Actions.runBlocking(
                    new SequentialAction(
                            dropLoadedSample
                    )
            );
            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.INTAKE_ARM_DROP_SAMPLE_HIGH_Y, IncredibotsArmControl.INTAKE_ARM_VELOCITY);
            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.SLIDE_POSITION_TO_SCORE_HIGH, IncredibotsArmControl.SLIDE_VELOCITY);


            //Actions.runBlocking(
            //        new SequentialAction(
             //               servoMove
            //        )
            //);

            //myHardware.operateIntakeServo(false,true);

            //goes to 38,38
            Actions.runBlocking(
                    new SequentialAction(
                            positionToPickNextSample
                    )
            );

            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.INTAKE_ARM_RESTING_BACK, IncredibotsArmControl.INTAKE_ARM_VELOCITY);
            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.SLIDE_POSITION_RESTING, IncredibotsArmControl.SLIDE_VELOCITY);


            Actions.runBlocking(
                    new SequentialAction(
                            strafeDownToPositionWithSpikeSample
                    )
            );

            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.SLIDE_POSITION_TO_PICK_NEAR_SAMPLE, IncredibotsArmControl.SLIDE_VELOCITY);
           // myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.INTAKE_ARM_PICK_NEAR_SAMPLE_A, IncredibotsArmControl.INTAKE_ARM_VELOCITY);
            //myHardware.operateIntakeServo(true, false);
            /*
            //wait for the claw to close
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.i("Incredibots Auto Specimen", "Sleeping thread interrupted");
            }
            */
            //myHardware.setClawArmPositionAndVelocityAndWait(IncredibotsArmControl.CLAW_ARM_VERTICAL, IncredibotsArmControl.CLAW_ARM_VELOCITY);

            Actions.runBlocking(
                    new SequentialAction(
                            moveBackToBucket
                    )
            );

            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.INTAKE_ARM_RESTING_BACK, IncredibotsArmControl.INTAKE_ARM_VELOCITY);
            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.SLIDE_POSITION_RESTING, IncredibotsArmControl.SLIDE_VELOCITY);


            Actions.runBlocking(
                   new SequentialAction(
                           inFrontOfAscentZone
                   )
           );

            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.SLIDE_POSITION_RESTING, IncredibotsArmControl.SLIDE_VELOCITY);
            //myHardware.setSlideArmPositionAndVelocity(IncredibotsArmControl.INTAKE_ARM_RESTING_BACK, IncredibotsArmControl.INTAKE_ARM_VELOCITY);
            //myHardware.operateIntakeServo(false, false);

            Actions.runBlocking(
                    new SequentialAction(
                            parkInAscent
                    )
            );

            telemetry.update();

            break;
        }



//        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
//                .lineToYSplineHeading(33, Math.toRadians(0))
//                .waitSeconds(2)
//                .setTangent(Math.toRadians(90))
//                .lineToY(48)
//                .lineToX(32)
//                .setTangent(Math.toRadians(0))
//                .strafeTo(new Vector2d(44.5, 30))
//                .turn(Math.toRadians(180))
//                .lineToX(47.5)
//                .waitSeconds(3);

        //strafes in front of blue tape for net zone

        //https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/
        //https://rr.brott.dev/docs/v1-0/guides/centerstage-auto/

    }
}
