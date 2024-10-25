package org.firstinspires.ftc.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTest {
    public static void main(String[] args) {
        MeepMeep meepMeep =  new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-25, 62, Math.toRadians(0)))

                  /*
                        POS 2

                        //strafes in front of blue tape for net zone
                        .strafeTo(new Vector2d(35, 40))
                        .turn(Math.toRadians(45))
                //EXTEND ARM
                        .strafeTo(new Vector2d(55, 55))
                */

                .strafeTo(new Vector2d(35, 38))
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(55, 55))

                .strafeTo(new Vector2d(45, 45))
                .strafeTo(new Vector2d(-55, 55))
                .turn(Math.toRadians(-135))
                .lineToY(62)

                .build());
    }
}