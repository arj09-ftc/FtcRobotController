package org.firstinspires.ftc.teamcode.drive.opmode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.RobotHardware;

public class IncredibotsActions {

    private IncredibotsArmControl armControl;
    private RobotHardware robotHardware;

    public IncredibotsActions(RobotHardware robotHardware, IncredibotsArmControl armControl){
        this.robotHardware=robotHardware;
        this.armControl=armControl;
    }

    public class SlideArmActions {
        public class PositionToPickFarSample implements Action {
            // checks if the slide motor has been powered on
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {

                if (!initialized) {
                    robotHardware.intakeServo.setPower(1);
                    initialized = true;
                }

                double pos=robotHardware.getSlideArmMotorPos();
                packet.put("slidePos", pos);
                if (pos<IncredibotsArmControl.INTAKE_ARM_PICK_FAR_SAMPLE_A){
                    // let action continue
                    return true;
                }else{
                    // mark action completed
                    robotHardware.intakeServo.setPower(0);
                    return false;
                }

            }
        }
    }

    public class ClawArmActions {

    }

    public class ClawActions {
        public class OpenClaw implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                double pos=robotHardware.clawServo.getPosition();
                packet.put("clawPos", pos);
                if(robotHardware.clawServo.getPosition()<IncredibotsArmControl.CLAW_OPEN_POSITION) {
                    robotHardware.clawServo.setPosition(IncredibotsArmControl.CLAW_OPEN_POSITION);
                    return true;
                }else {
                    return false;
                }
            }
        }

        public class CloseClaw implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                double pos=robotHardware.clawServo.getPosition();
                packet.put("clawPos", pos);
                if(robotHardware.clawServo.getPosition()>IncredibotsArmControl.CLAW_CLOSE_POSITION) {
                    robotHardware.clawServo.setPosition(IncredibotsArmControl.CLAW_CLOSE_POSITION);
                    return true;
                }else {
                    return false;
                }
            }
        }
    }

    public void setRightArmPositionToHighBasket(){

    }

    public void setRightArmPositionToLowBasket(){

    }


    public void extendArmToHighBasket(){
       // robotHardware.setSlideArmPositionAndVelocity(INTAKE_ARM_DROP_SAMPLE_HIGH_Y, INTAKE_ARM_VELOCITY);
       // robotHardware.setSlidePositionAndVelocity(SLIDE_POSITION_TO_SCORE_HIGH, SLIDE_VELOCITY);
    }

}
