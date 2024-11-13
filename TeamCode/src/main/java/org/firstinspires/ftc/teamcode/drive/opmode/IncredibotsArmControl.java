package org.firstinspires.ftc.teamcode.drive.opmode;

import android.util.Log;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.lang.Math;

@Config
public class IncredibotsArmControl
{
    Gamepad gamepad2;
    RobotHardware robotHardware;

    // Claw Arm constants
    public static int CLAW_ARM_RESTING_BACK = 0;
    public static int CLAW_ARM_VELOCITY = 700;

    public static int CLAW_ARM_PICK_SAMPLE_A = 1420;

    public static int CLAW_ARM_DROP_SAMPLE_HIGH = 847;
    public static int CLAW_ARM_DROP_SAMPLE_LOW = CLAW_ARM_DROP_SAMPLE_HIGH; //896

    public static int CLAW_ARM_PICK_SPECIMEN = 95;
    public static int CLAW_ARM_AFTER_DROP_SAMPLE_HIGH = 720;

    public static int CLAW_ARM_AUTO_HANG_SPECIMEN = 795;
    public static int CLAW_ARM_AUTO_SNAP_SPECIMEN = 600;

    public static int CLAW_ARM_TELE_HANG_SPECIMEN_HIGH_Y = 795;
    public static int CLAW_ARM_TELE_SNAP_SPECIMEN_HIGH_B = 652;

    public static int CLAW_ARM_ENTER_SUB = 1330;

    //claw positions
    public static double CLAW_OPEN_POSITION = 0.55;
    public static double CLAW_CLOSE_POSITION = 0.42;

    //Slide movement position
    public static int SLIDE_POSITION_RESTING = 0;
    public static int SLIDE_VELOCITY = 1000;

    public static int SLIDE_POSITION_HANG_SPECIMEN_HIGH = 320;

    public static int SLIDE_POSITION_HIGH_BASKET = 2926;
    public static int SLIDE_POSITION_LOW_BASKET = SLIDE_POSITION_RESTING; //1170

    public static int MAX_SLIDE_POSITION_IN_OVERRIDE = 2500;

    //override...
    private boolean MANUAL_OVERRIDE = false;
    private static int MANUAL_OVERRIDE_POSITION_DELTA = 150;

    private enum BUTTON_STATE {
        NONE,
        BUTTON_BACK,
        BUTTON_HANG,
        BUTTON_LT_A,
        BUTTON_RT_A,
        BUTTON_LT_B,
        BUTTON_RT_B,
        BUTTON_LT_X,
        BUTTON_RT_X,
        BUTTON_LT_Y,
        BUTTON_RT_Y,
        CLAW_ARM_AFTER_HIGH_SAMPLE
    }

    private BUTTON_STATE buttonState;

    private boolean readyToDropHighSample = false;

    public IncredibotsArmControl(Gamepad gamepad, RobotHardware robotHardware) {
        gamepad2 = gamepad;
        this.robotHardware = robotHardware;
        buttonState = BUTTON_STATE.NONE;
    }

    public void ProcessInputs(Telemetry telemetry) {
        //left trigger + buttons controls left arm (claw)
        //right trigger + buttons controls right arm (intake)
        Log.i("=== INCREDIBOTS ===","===========================");
        Log.i("=== INCREDIBOTS ===", "PROCESSING INPUTS FOR ARMS");
        Log.i("=== INCREDIBOTS ===","===========================");

        CreateStateFromButtonPress();
        ProcessButtons();

        ProcessBumpers();

        ProcessDPad();

        HandleManualOverride();
    }

    private void CreateStateFromButtonPress() {
        if (gamepad2.back) {
            buttonState = BUTTON_STATE.BUTTON_BACK;
        }

        if (gamepad2.start && gamepad2.left_trigger > 0 && gamepad2.right_trigger > 0) {
            buttonState = BUTTON_STATE.BUTTON_HANG;
        }

        if (gamepad2.a){
            if (gamepad2.left_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_LT_A;
            }
            else if (gamepad2.right_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_RT_A;
            }
        }

        if (gamepad2.b) {
            if (gamepad2.left_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_LT_B;
            }
            else if (gamepad2.right_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_RT_B;
            }
        }

        if (gamepad2.x) {
            if (gamepad2.left_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_LT_X;
            }
            else if (gamepad2.right_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_RT_X;
            }
        }

        if (gamepad2.y) {
            if (gamepad2.left_trigger > 0) {
                buttonState = BUTTON_STATE.BUTTON_LT_Y;
            }
            else if (gamepad2.right_trigger > 0){
                buttonState = BUTTON_STATE.BUTTON_RT_Y;
            }
        }
    }

    //functions to take gamepad inputs and turn it into movements
    private void ProcessButtons() {

        switch (buttonState) {
            case BUTTON_BACK:
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING BACK BUTTON - SLIDE IS BUSY");
                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_RESTING, SLIDE_VELOCITY);
                }

                //has to be a separate check since the previous action will make the slide motor busy
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING BACK BUTTON - SLIDE FINISHED MOVING");
                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_RESTING_BACK, CLAW_ARM_VELOCITY);
                    robotHardware.operateClawServo(false, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_LT_A:   //PICK SPECIMEN
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/A - SLIDE IS BUSY");
                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_RESTING, SLIDE_VELOCITY);
                }

                if (!robotHardware.isSlideMotorBusy()) {    //move arm after moving the slide
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/A - SLIDE IS FINISHED MOVING");
                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_PICK_SPECIMEN, CLAW_ARM_VELOCITY);
                    robotHardware.operateClawServo(false, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_LT_Y:   //HANG SPECIMEN - HIGH RUNG
                if (!robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/Y - CLAW ARM IS BUSY");
                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_TELE_HANG_SPECIMEN_HIGH_Y, CLAW_ARM_VELOCITY);
                }

                if (!robotHardware.isClawArmMotorBusy()) {  //set slide after claw arm
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/Y - CLAW ARM IS DONE MOVING");
                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_HANG_SPECIMEN_HIGH, SLIDE_VELOCITY);
                    robotHardware.operateClawServo(false, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_LT_B:   //SNAP SPECIMEN
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/B - SLIDE IS BUSY");

                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_HANG_SPECIMEN_HIGH, SLIDE_VELOCITY);
                }

                if (!robotHardware.isSlideMotorBusy()) {    //move claw arm after slide is done moving
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/B - SLIDE IS DONE MOVING");

                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_TELE_SNAP_SPECIMEN_HIGH_B, CLAW_ARM_VELOCITY);
                }

                // open claw after both slide and arm are done moving
                if (!robotHardware.isSlideMotorBusy() && !robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING LT/B - BOTH SLIDE AND ARM DONE MOVING");

                    robotHardware.operateClawServo(true, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_RT_A:   //PICK SAMPLE
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/A - SLIDE IS MOVING");

                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_RESTING, SLIDE_VELOCITY);
                }

                if (!robotHardware.isSlideMotorBusy()) {    //move claw arm after slide is done moving
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/A - SLIDE IS DONE MOVING");

                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_PICK_SAMPLE_A, CLAW_ARM_VELOCITY);
                    robotHardware.operateClawServo(true, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_RT_Y:   //HIGH BASKET
                if (!robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/Y - CLAW ARM IS BUSY");

                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_DROP_SAMPLE_HIGH, CLAW_ARM_VELOCITY);
                }

                if (!robotHardware.isClawArmMotorBusy()) { //move slide after claw arm is done moving
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/Y - CLAW ARM IS DONE MOVING");
                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_HIGH_BASKET, SLIDE_VELOCITY);
                    buttonState = BUTTON_STATE.NONE;
                    readyToDropHighSample = true;
                }
                break;
            case BUTTON_RT_B:   //LOW BASKET
                if (!robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/B - CLAW ARM IS BUSY");

                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_DROP_SAMPLE_LOW, CLAW_ARM_VELOCITY);
                }

                if (!robotHardware.isClawArmMotorBusy()) {  //move slide after getting arm in position
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/B CLAW ARM IS DONE MOVING");

                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_LOW_BASKET, SLIDE_VELOCITY);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_RT_X:   //ENTER SUB
                if (!robotHardware.isSlideMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/X - SLIDE ARM IS MOVING");

                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_RESTING, SLIDE_VELOCITY);
                }

                if (!robotHardware.isSlideMotorBusy()) {    //move claw arm after slide is done moving
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/X - SLIDE ARM IS DONE MOVING");
                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_ENTER_SUB, CLAW_ARM_VELOCITY);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
            case BUTTON_HANG:   //HANG THE ROBOT
                readyToDropHighSample = false;
                buttonState = BUTTON_STATE.NONE;
                break;
            case CLAW_ARM_AFTER_HIGH_SAMPLE:
                if (!robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/B - CLAW ARM IS BUSY");
                    robotHardware.setClawArmPositionAndVelocity(CLAW_ARM_AFTER_DROP_SAMPLE_HIGH, CLAW_ARM_VELOCITY);
                }

                if (!robotHardware.isClawArmMotorBusy()) {
                    Log.i("=== INCREDIBOTS ===", "PROCESSING RT/X - CLAW ARM IS DONE MOVING");
                    robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_RESTING, SLIDE_VELOCITY);
                    buttonState = BUTTON_STATE.NONE;
                }
                readyToDropHighSample = false;
                break;
        }
    }

    private void ProcessBumpers() {
        // if the right bumper is pressed it opens the claw
        if (gamepad2.right_bumper) {
            //telemetry.addLine("right bumper pressed");
            robotHardware.operateClawServo(true, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);

            if (readyToDropHighSample) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                //move the robot arm back
                buttonState = BUTTON_STATE.CLAW_ARM_AFTER_HIGH_SAMPLE;

                readyToDropHighSample = false;
            }
        }
        // if the left bumper is pressed it closes the claw
        else if (gamepad2.left_bumper) {
            //telemetry.addLine("left bumper pressed");
            robotHardware.operateClawServo(false, CLAW_OPEN_POSITION, CLAW_CLOSE_POSITION);
        }
    }

    private void HandleManualOverride() {
        // if the back button is pressed it switches manual ovverides value
        if (gamepad2.left_stick_button && gamepad2.right_stick_button){
            MANUAL_OVERRIDE = !MANUAL_OVERRIDE;
        }

        // if manual override is true it will allow the joysticks to control the arms
        if (MANUAL_OVERRIDE) {
            float leftYSignal = gamepad2.left_stick_y * -1;

            // If the left joystick is greater than zero, it moves the left arm up
            if (leftYSignal > 0) {
                robotHardware.setClawArmPositionAndVelocity(robotHardware.getClawArmMotorPos() + MANUAL_OVERRIDE_POSITION_DELTA, leftYSignal * CLAW_ARM_VELOCITY);

            }
            // If the left joystick is less than zero, it moves the left arm down
            else if (leftYSignal < 0){
                robotHardware.setClawArmPositionAndVelocity(robotHardware.getClawArmMotorPos() - MANUAL_OVERRIDE_POSITION_DELTA, leftYSignal * CLAW_ARM_VELOCITY);
            }
        }
    }

    private void ProcessDPad() {
        if (gamepad2.dpad_up){
            //SLIDE CANNOT EXPAND BEYOND THE FAR POSITION FOR IT TO BE UNDER LIMITS
            robotHardware.setSlidePositionAndVelocity(Math.min(robotHardware.getSlidePos() + MANUAL_OVERRIDE_POSITION_DELTA, MAX_SLIDE_POSITION_IN_OVERRIDE), SLIDE_VELOCITY);
        }

        //process Dpad down input to retract linear slide
        if (gamepad2.dpad_down){
            //SLIDE POSITION CANNOT BE LESS THAN 0
            robotHardware.setSlidePositionAndVelocity(Math.max(robotHardware.getSlidePos() - MANUAL_OVERRIDE_POSITION_DELTA, 0), SLIDE_VELOCITY);
        }
    }
}