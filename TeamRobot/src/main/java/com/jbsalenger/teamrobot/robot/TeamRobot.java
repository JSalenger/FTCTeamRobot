package com.jbsalenger.teamrobot.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.jbsalenger.teamrobot.robot.enums.Corner;
import com.jbsalenger.teamrobot.robot.enums.HorizontalDirection;
import com.jbsalenger.teamrobot.robot.enums.DiagonalDirection;
import com.jbsalenger.teamrobot.robot.enums.WheelType;
import com.jbsalenger.teamrobot.robot.motors.REVMotor;
import com.jbsalenger.teamrobot.robot.vision.RobotVision;

import java.util.ArrayList;
import java.util.Arrays;


public class TeamRobot {

    // TODO: ADD JAVADOC COMMENTS TO METHODS AND CLASS
    ArrayList<REVMotor> wheels = new ArrayList<>();
    RobotVision robotVision;

    public TeamRobot(HardwareMap hMap, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, REVMotor... _wheels) {
        /**
         * Constructor:
         * @param hMap hardware map from OpMode
         * @param KEY Vuforia key
         * @param TFOD_MODEL_ASSET_PATH tfod model file name
         * @param LABELS array of label names
         * @param _wheels wheels in the form of var args
         *
         * Constructor for the TeamRobot Class
         */


        // TODO: TEST IF WE CAN ALSO ADD Gamepad OBJECT TO CONSTRUCTOR
        wheels.addAll(Arrays.asList(_wheels));
        robotVision = new RobotVision(hMap, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
    }

    public TeamRobot(HardwareMap hMap, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, int ticksPerRotation, String[] motorNames) {
        REVMotor fl = new REVMotor(hMap.dcMotor.get(motorNames[0]), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor fr = new REVMotor(hMap.dcMotor.get(motorNames[0]), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor bl = new REVMotor(hMap.dcMotor.get(motorNames[0]), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor br = new REVMotor(hMap.dcMotor.get(motorNames[0]), ticksPerRotation, WheelType.FRONT_LEFT);

        wheels.addAll(Arrays.asList(fl, fr, bl, br));
        robotVision = new RobotVision(hMap, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
    }

    public TeamRobot(HardwareMap hMap, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, int ticksPerRotation) {
        REVMotor fl = new REVMotor(hMap.dcMotor.get("frontLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor fr = new REVMotor(hMap.dcMotor.get("frontRight"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor bl = new REVMotor(hMap.dcMotor.get("backLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor br = new REVMotor(hMap.dcMotor.get("backRight"), ticksPerRotation, WheelType.FRONT_LEFT);

        wheels.addAll(Arrays.asList(fl, fr, bl, br));
        robotVision = new RobotVision(hMap, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
    }

    // TODO: ADD ONE FINAL CONSTRUCTOR THAT USES THE TF DEFAULTS FOR THE ULTIMATE GOAL SEASON

    private void waitForMotor(REVMotor m) {
        while(m.getTicker().getIsBusy()){}

        return;
    }

    public void driveStraight(int ticks, double power) {
        wheels.forEach(wheel -> {
            wheel.set(power);
            wheel.getTicker().setTargetPos(ticks);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        });

        // TODO: TEST THAT THIS IS STABLE THIS IS NOT HOW THE LIBRARY IS MEANT TO BE USED
        // TEST IF THIS FAILS TEST THE CONDITION WITHOUT THE !
        //Awaitility.await().until(() -> !(wheels.get(0).getTicker().getIsBusy()));
        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });
    }

    public void driveHorizontal(HorizontalDirection hDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(hDirection) {
            case LEFT:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
            case RIGHT:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
        }

        //Awaitility.await().until(() -> !(wheels.get(0).getTicker().getIsBusy()));
        while(wheels.get(0).getTicker().getIsBusy()) {}

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public void turnOnCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
            case RIGHT:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
        }

        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public void turnOnCorners(Corner corner, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(corner) {
            case FRONT_LEFT_CORNER:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
            case BACK_LEFT_CORNER:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
            case FRONT_RIGHT_CORNER:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
            case BACK_RIGHT_CORNER:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
        }

        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public void turnOnBackCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case BACK_RIGHT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
            case RIGHT:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case BACK_RIGHT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
        }

        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public void turnOnFrontCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case BACK_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case BACK_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_LEFT:
                        case FRONT_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
            case RIGHT:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case BACK_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case BACK_RIGHT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_LEFT:
                        case FRONT_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
        }

        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public void diagonalDrive(DiagonalDirection dDirection, int ticks, double power) {
        switch(dDirection) {
            case LEFT_FORWARD:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });
            case RIGHT_FORWARD:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.set(power);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
            case LEFT_BACKWARD:
                wheels.forEach(wheel -> {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.set(power * -1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                    }
                });
            case RIGHT_BACKWARD:
                wheels.forEach(wheel ->  {
                    switch(wheel.getWheelType()) {
                        case FRONT_LEFT:
                        case BACK_RIGHT:
                            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                            wheel.set(0);
                        case FRONT_RIGHT:
                        case BACK_LEFT:
                            wheel.set(power*-1);
                            wheel.getTicker().setTargetPos(ticks);
                            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                });

        }

        waitForMotor(wheels.get(0));

        wheels.forEach(wheel -> {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    public RobotVision getRobotVision() {
        return robotVision;
    }

    public ArrayList<REVMotor> getWheels() {
        return wheels;
    }
}