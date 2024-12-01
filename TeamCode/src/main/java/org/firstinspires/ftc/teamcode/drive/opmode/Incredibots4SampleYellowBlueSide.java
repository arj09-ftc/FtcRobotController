package org.firstinspires.ftc.teamcode.drive.opmode;

import static org.firstinspires.ftc.teamcode.drive.opmode.SampCoords.DROP_SAMP;
import static org.firstinspires.ftc.teamcode.drive.opmode.SampCoords.INIT_POS;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "Incredibots3SampleYellowBlueSide", group = "Autonomous")

public class Incredibots4SampleYellowBlueSide extends LinearOpMode {
    RobotHardware myHardware;
    IncredibotsArmControl armControl;
    MecanumDrive drive;

    private Action GetArmControlAction(int position, int velocity, boolean waitForAction) {
        return new ArmMotionAsRRAction(myHardware, position, velocity, waitForAction);
    }

    private Action GetSlideControlAction(int position, boolean waitForAction) {
        return new SlideMotionAsRRAction(myHardware, position, waitForAction);
    }

    private Action GetClawControlAction(boolean open, double openPosition, double closePosition, boolean waitForAction, boolean shortWait) {
        return new ClawMotionAsRRAction(myHardware, open, openPosition, closePosition, waitForAction, shortWait);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new RobotHardware(this.hardwareMap);
        armControl = new IncredibotsArmControl(gamepad2, myHardware);
        drive = new MecanumDrive(this.hardwareMap, INIT_POS);

        Action DropPreLoadAndPickAndDropFirstSample = drive.actionBuilder(INIT_POS)

                .strafeToLinearHeading(DROP_SAMP.position, DROP_SAMP.heading)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_DROP_SAMPLE_HIGH, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .stopAndAdd(GetClawControlAction(true, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                //just dropped the preload in the high basket
                //check this position. unsure
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_PICK_SAMPLE_A, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .strafeToLinearHeading(SampCoords.ALIGN_WITH_SAMP_1.position, SampCoords.ALIGN_WITH_SAMP_1.heading)
                .stopAndAdd(GetClawControlAction(false, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .strafeToLinearHeading(SampCoords.DROP_SAMP.position, SampCoords.DROP_SAMP.heading)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_DROP_SAMPLE_HIGH, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .stopAndAdd(GetClawControlAction(true, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .build();


        Action PickAndDrop2ndSample = drive.actionBuilder(DROP_SAMP)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_PICK_SAMPLE_A, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .strafeToLinearHeading(SampCoords.ALIGN_WITH_SAMP_2.position, SampCoords.ALIGN_WITH_SAMP_2.heading)
                .stopAndAdd(GetClawControlAction(false, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .strafeToLinearHeading(SampCoords.DROP_SAMP.position, SampCoords.DROP_SAMP.heading)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_DROP_SAMPLE_HIGH, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .stopAndAdd(GetClawControlAction(true, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .build();

        Action PickAndDrop3rdSample = drive.actionBuilder(DROP_SAMP)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_PICK_SAMPLE_A, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .strafeToLinearHeading(SampCoords.PICK_3RD_SAMP.position, SampCoords.PICK_3RD_SAMP.heading)
                .stopAndAdd(GetClawControlAction(false, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .strafeToLinearHeading(SampCoords.DROP_SAMP.position, SampCoords.DROP_SAMP.heading)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_DROP_SAMPLE_HIGH, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .stopAndAdd(GetClawControlAction(true, IncredibotsArmControl.CLAW_OPEN_POSITION, IncredibotsArmControl.CLAW_CLOSE_POSITION, true, false))
                .build();
        Action Park = drive.actionBuilder(DROP_SAMP)
                .stopAndAdd(GetArmControlAction(IncredibotsArmControl.CLAW_ARM_PICK_SAMPLE_A, IncredibotsArmControl.CLAW_ARM_VELOCITY, false))
                .strafeToLinearHeading(SampCoords.IN_FRONT_OF_ASCENT_ZONE.position, SampCoords.IN_FRONT_OF_ASCENT_ZONE.heading)
                .strafeToLinearHeading(SampCoords.PARK_POS.position, SampCoords.PARK_POS.heading)
                .build();

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(
                    new SequentialAction(
                            DropPreLoadAndPickAndDropFirstSample,
                            PickAndDrop2ndSample,
                            PickAndDrop3rdSample,
                            Park)
            );
        }
    }

}
