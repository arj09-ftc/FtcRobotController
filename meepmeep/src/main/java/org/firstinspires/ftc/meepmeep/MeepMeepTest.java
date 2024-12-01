package org.firstinspires.ftc.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.lang.reflect.Field;


public class MeepMeepTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(180), Math.toRadians(180), 13.5)
                .build();

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

        myBot.runAction(myBot.getDrive().actionBuilder(BlueSpecimen.INIT_POS)
                        //.strafeToLinearHeading(BlueSpecimen.BACK_POST_HANG.position, BlueSpecimen.BACK_POST_HANG.heading)
                        .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_1, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_1, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_1, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_2, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_2, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_2, BlueSpecimen.H)
                .splineToConstantHeading(BlueSpecimen.SLIDE_UNDER_SAMP_3, BlueSpecimen.H)
                        .splineToConstantHeading(BlueSpecimen.PUSH_SAMP_3, BlueSpecimen.H)


                .build());
    }
}