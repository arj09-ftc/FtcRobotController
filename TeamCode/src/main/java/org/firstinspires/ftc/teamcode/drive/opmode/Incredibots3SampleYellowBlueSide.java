package org.firstinspires.ftc.teamcode.drive.opmode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "Incredibots3SampleYellowBlueSide", group = "Autonomous")

public class Incredibots3SampleYellowBlueSide {
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

}
