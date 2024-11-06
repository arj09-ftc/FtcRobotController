package org.firstinspires.ftc.teamcode.drive.opmode;
//STATIC POSITIONS IN THE BLUE SPECIMEN FILE. THE BLUE SPECIMEN FILE HAS ALL THE COORDINATES
import static org.firstinspires.ftc.teamcode.drive.opmode.BlueSpecimen.BACK_OPEN_ARM;
import static org.firstinspires.ftc.teamcode.drive.opmode.BlueSpecimen.BACK_POST_HANG;
import static org.firstinspires.ftc.teamcode.drive.opmode.BlueSpecimen.INIT_POS;
import static org.firstinspires.ftc.teamcode.drive.opmode.BlueSpecimen.PICK_SPEC;
import static org.firstinspires.ftc.teamcode.drive.opmode.BlueSpecimen.PUSH_SAMP_1;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "IncredibotsBlueSpecimen", group = "Autonomous")

public class IncredibotsBlueSpecimen extends LinearOpMode {


    RobotHardware myHardware;
    IncredibotsArmControl armControl;
    MecanumDrive drive;
    BlueSpecimen blueSpecimen;

    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new RobotHardware(this.hardwareMap);
        armControl = new IncredibotsArmControl(gamepad2, myHardware);
        drive = new MecanumDrive(this.hardwareMap, INIT_POS);

        Action stepOne = drive.actionBuilder(INIT_POS)
                //GOES TO HANG PRELOAD AND PUSH 1ST SAMPLE
                .strafeToLinearHeading(BlueSpecimen.HANG_SPEC_1.position, BlueSpecimen.HANG_SPEC_1.heading)
                .waitSeconds(0.25)
                .strafeToLinearHeading(BlueSpecimen.BACK_POST_HANG.position, BlueSpecimen.BACK_POST_HANG.heading)
                .strafeToLinearHeading(BlueSpecimen.SLIDE_UNDER_SAMP_1.position, BlueSpecimen.SLIDE_UNDER_SAMP_1.heading)
                .strafeToLinearHeading(PUSH_SAMP_1.position, PUSH_SAMP_1.heading)
                .build();

        Action stepTwo = drive.actionBuilder(PUSH_SAMP_1)
                //BACKS UP ROBOT AND GOES TO PICK UP 2ND SPECIMEN AND HANGS IT
                .strafeToLinearHeading(BlueSpecimen.BACK_OPEN_ARM.position, BlueSpecimen.BACK_OPEN_ARM.heading)
                .waitSeconds(0.4)
                .strafeToLinearHeading(PICK_SPEC.position, PICK_SPEC.heading)
                .waitSeconds(0.2)
                .strafeToLinearHeading(BlueSpecimen.HANG_SPEC_3.position, BlueSpecimen.HANG_SPEC_3.heading)
                .waitSeconds(0.25)
                .strafeToLinearHeading(BlueSpecimen.BACK_POST_HANG.position, BlueSpecimen.BACK_POST_HANG.heading)
                .build();

        Action stepThree = drive.actionBuilder(BACK_POST_HANG)
                //PUSH SAMPLE 2 AND PICK UP 3RD (?) SPECIMEN
                .strafeToLinearHeading(BlueSpecimen.SLIDE_UNDER_SAMP_2.position, BlueSpecimen.SLIDE_UNDER_SAMP_2.heading)
                .strafeToLinearHeading(BlueSpecimen.PUSH_SAMP_2.position, BlueSpecimen.PUSH_SAMP_2.heading)
                .strafeToLinearHeading(BlueSpecimen.BACK_OPEN_ARM.position, BlueSpecimen.BACK_OPEN_ARM.heading)
                .waitSeconds(0.4)
                .strafeToLinearHeading(PICK_SPEC.position, PICK_SPEC.heading)
                .build();

        Action stepFour = drive.actionBuilder(PICK_SPEC)
                //HANGS 3RD SPECIMEN AND GOES BACK TO "POST HANG POSITION"
                .strafeToLinearHeading(BlueSpecimen.HANG_SPEC_3.position, BlueSpecimen.HANG_SPEC_3.heading)
                .waitSeconds(0.25)
                .strafeToLinearHeading(BlueSpecimen.BACK_POST_HANG.position, BlueSpecimen.BACK_POST_HANG.heading)

                //GOES TO PUSH 3RD SAMPLE
                .strafeToLinearHeading(BlueSpecimen.BRACE_WALL_SAMP_3.position, BlueSpecimen.BRACE_WALL_SAMP_3.heading)
                .strafeToLinearHeading(BlueSpecimen.PUSH_SAMP_3.position, BlueSpecimen.PUSH_SAMP_3.heading)
                .strafeToLinearHeading(BlueSpecimen.BACK_OPEN_ARM.position, BlueSpecimen.BACK_OPEN_ARM.heading)
                .waitSeconds(0.4)
                .build();

        Action stepFive = drive.actionBuilder(BACK_OPEN_ARM)
                //PICKS AND HANGS 4TH SPECIMEN
                .strafeToLinearHeading(BlueSpecimen.PICK_SPEC.position, BlueSpecimen.PICK_SPEC.heading)
                .strafeToLinearHeading(BlueSpecimen.HANG_SPEC_3.position, BlueSpecimen.HANG_SPEC_3.heading)
                .waitSeconds(0.25)

                //GOES BACK FOR 5TH SPECIMEN
                .strafeToLinearHeading(BlueSpecimen.PICK_SPEC.position, BlueSpecimen.PICK_SPEC.heading)
                .strafeToLinearHeading(BlueSpecimen.HANG_SPEC_3.position, BlueSpecimen.HANG_SPEC_3.heading)
                .waitSeconds(0.25)

                //PARKS
                .strafeToLinearHeading(BlueSpecimen.PARK_POS.position, BlueSpecimen.PARK_POS.heading)
                .build();
    }
}
