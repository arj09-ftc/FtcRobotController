package org.firstinspires.ftc.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.lang.reflect.Field;


public class MeepMeepTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(150, 125, Math.toRadians(180), Math.toRadians(180), 13.5)
                .build();

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
        
        myBot.runAction(myBot.getDrive().actionBuilder(SampCoord.INIT_POS)
                .strafeToLinearHeading(SampCoord.HANG_SPECIMEN.position, SampCoord.HANG_SPECIMEN.heading)
                .strafeToLinearHeading(SampCoord.ALIGN_WITH_SAMP_1.position, SampCoord.ALIGN_WITH_SAMP_1.heading)
                .strafeToLinearHeading(SampCoord.DROP_SAMP.position, SampCoord.DROP_SAMP.heading)
                .strafeToLinearHeading(SampCoord.ALIGN_WITH_SAMP_2.position, SampCoord.ALIGN_WITH_SAMP_2.heading)
                .strafeToLinearHeading(SampCoord.DROP_SAMP.position, SampCoord.DROP_SAMP.heading)
                .strafeToLinearHeading(SampCoord.PICK_3RD_SAMP.position, SampCoord.PICK_3RD_SAMP.heading)
                .strafeToLinearHeading(SampCoord.DROP_SAMP.position, SampCoord.DROP_SAMP.heading)
                .strafeToLinearHeading(SampCoord.COMMON_ARM_CLOSE_POS.position, SampCoord.PUSH_SAMP_1.heading)
                .strafeToLinearHeading(SampCoord.IN_FRONT_OF_ASCENT_ZONE.position, SampCoord.IN_FRONT_OF_ASCENT_ZONE.heading)
                .strafeToLinearHeading(SampCoord.PARK_POS.position, SampCoord.PARK_POS.heading)
                .build());
    }
}