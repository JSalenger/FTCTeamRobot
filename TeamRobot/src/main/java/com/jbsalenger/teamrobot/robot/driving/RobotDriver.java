package com.jbsalenger.teamrobot.robot.driving;

import com.jbsalenger.teamrobot.robot.motors.REVMotor;
import java.util.ArrayList;
import java.util.Arrays;

public class RobotDriver {
    ArrayList<REVMotor> wheels = new ArrayList<>();

    public RobotDriver(REVMotor... _wheels) {
        wheels.addAll(Arrays.asList(_wheels));
    }


    public void drive(float powerFL, float powerFR, float powerBL, float powerBR) {
        wheels.forEach(wheel -> {
            switch(wheel.getWheelType()) {
                case FRONT_LEFT:
                    wheel.set(powerFL);
                case FRONT_RIGHT:
                    wheel.set(powerFR);
                case BACK_LEFT:
                    wheel.set(powerBL);
                case BACK_RIGHT:
                    wheel.set(powerBR);
            }
        });
    }

    // This function is to be used in the driver controlled TeleOP, it does not require Ticks
    public void driveHorizontal(float powerFL, float powerFR) {
        wheels.forEach(wheel -> {
            switch(wheel.getWheelType()) {
                case FRONT_LEFT:
                case BACK_RIGHT:
                    wheel.set(powerFL);
                case FRONT_RIGHT:
                case BACK_LEFT:
                    wheel.set(powerFR);
            }
        });
    }
}
