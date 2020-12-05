package com.jbsalenger.teamrobot.robot;

import android.content.Context;

import com.jbsalenger.teamrobot.robot.motors.Motor;
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
    ArrayList<Motor> wheels = new ArrayList<>();
    RobotVision robotVision = null;



    /**
     * Constructor: all params
     * Leave key string empty ( "" ) to skip vuforia init
     * If vuforia init is skipped, then other vuforia specific arguments (TFOD_MODEL_ASSET_PATH, LABELS, context,  and hMap)
     * may be left as null, KEY must still hold a value, however
     * @param context
     * @param KEY
     * @param TFOD_MODEL_ASSET_PATH
     * @param LABELS
     * @param motors
     */

    public TeamRobot(Context context, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, Motor... motors) {
        wheels.addAll(Arrays.asList(motors));

        if(!(KEY.equals(""))) {
            robotVision = new RobotVision(context, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
        }
    }

    /**
     * Constructor: all params except for yearly vuforia ones
     * Similar to all param constructor except missing TFOD_MODEL_ASSET_PATH and LABELS, these are set by default in the constructor
     * to that years information. ( Ex. 2020-2021 => TFOD_MODEL_ASSET_PATH = "UltimateGoal.tflite" )
     * @param context
     * @param KEY
     * @param motors
     */

    public TeamRobot(Context context, String KEY, Motor... motors) {
        wheels.addAll(Arrays.asList(motors));
        robotVision = new RobotVision(context, KEY, "UltimateGoal.tflite", new String[]{"Quad", "Single"});
    }

    /**
     * Constructor: strings, for motor names, instead of motor objects
     * Motors will be constructed using the motorName strings provided,
     * They will be of type REVMotor which implements the Motor interface
     *
     * Leave key string empty ( "" ) to skip vuforia init
     * hMap may not be left as null during vuforia init skip
     * @param hMap
     * @param context
     * @param KEY
     * @param TFOD_MODEL_ASSET_PATH
     * @param LABELS
     * @param ticksPerRotation
     * @param motorNames
     */

    public TeamRobot(HardwareMap hMap, Context context, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, int ticksPerRotation, String... motorNames) {
        REVMotor fl = new REVMotor(hMap.dcMotor.get(motorNames[0]), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor fr = new REVMotor(hMap.dcMotor.get(motorNames[1]), ticksPerRotation, WheelType.FRONT_RIGHT);
        REVMotor bl = new REVMotor(hMap.dcMotor.get(motorNames[2]), ticksPerRotation, WheelType.BACK_LEFT);
        REVMotor br = new REVMotor(hMap.dcMotor.get(motorNames[3]), ticksPerRotation, WheelType.BACK_RIGHT);

        wheels.addAll(Arrays.asList(fl, fr, bl, br));

        if(!(KEY.equals(""))) {
            robotVision = new RobotVision(context, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
        }
    }

    /**
     * Constructor: all params except for motor names
     * Motor names are assumed to be frontLeft, frontRight, backLeft, backRight
     * Leave key string empty ( "" ) to skip vuforia init
     * hMap may not be left as null during vuforia init skip
     * @param hMap
     * @param context
     * @param KEY
     * @param TFOD_MODEL_ASSET_PATH
     * @param LABELS
     * @param ticksPerRotation
     */

    public TeamRobot(HardwareMap hMap, Context context, String KEY, String TFOD_MODEL_ASSET_PATH, String[] LABELS, int ticksPerRotation) {
        REVMotor fl = new REVMotor(hMap.dcMotor.get("frontLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor fr = new REVMotor(hMap.dcMotor.get("frontRight"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor bl = new REVMotor(hMap.dcMotor.get("backLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor br = new REVMotor(hMap.dcMotor.get("backRight"), ticksPerRotation, WheelType.FRONT_LEFT);

        wheels.addAll(Arrays.asList(fl, fr, bl, br));

        if(!(KEY.equals(""))) {
            robotVision = new RobotVision(context, KEY, TFOD_MODEL_ASSET_PATH, LABELS);
        }
    }

    /**
     * Constructor: only param is ticksPerRotation,
     * Does not init vuforia
     * @param hMap
     * @param ticksPerRotation
     */

    public TeamRobot(HardwareMap hMap, int ticksPerRotation) {
        REVMotor fl = new REVMotor(hMap.dcMotor.get("frontLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor fr = new REVMotor(hMap.dcMotor.get("frontRight"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor bl = new REVMotor(hMap.dcMotor.get("backLeft"), ticksPerRotation, WheelType.FRONT_LEFT);
        REVMotor br = new REVMotor(hMap.dcMotor.get("backRight"), ticksPerRotation, WheelType.FRONT_LEFT);

        wheels.addAll(Arrays.asList(fl, fr, bl, br));
    }



    // TODO: ADD ONE FINAL CONSTRUCTOR THAT USES THE TF DEFAULTS FOR THE ULTIMATE GOAL SEASON

    private void waitForMotor(Motor m) {
        while(m.getTicker().getIsBusy()){}

        return;
    }

    public void drive(double fl, double fr, double bl, double br, int ticks) {
        for(Motor motor : wheels) {
            switch(motor.getWheelType()) {
                case FRONT_LEFT:
                    motor.getTicker().setTargetPos(ticks);
                    motor.set(fl);
                    motor.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                case FRONT_RIGHT:
                    motor.getTicker().setTargetPos(ticks);
                    motor.set(fr);
                    motor.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                case BACK_LEFT:
                    motor.getTicker().setTargetPos(ticks);
                    motor.set(bl);
                    motor.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
                case BACK_RIGHT:
                    motor.getTicker().setTargetPos(ticks);
                    motor.set(br);
                    motor.getTicker().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        }

        waitForMotor(wheels.get(0));

        for(Motor motor : wheels) {
            motor.resetTicker();
            motor.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void driveStraight(int ticks, double power) {
        drive(power, power, power, power, ticks);
    }

    public void driveHorizontal(HorizontalDirection hDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(hDirection) {
            case LEFT:
                drive(power * -1, power, power * -1, power, ticks);
            case RIGHT:
                drive(power, power * -1, power, power * -1, ticks);
        }
    }

    public void turnOnCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                drive(power * -1, power, power * -1, power, ticks);
            case RIGHT:
                drive(power, power * -1, power, power * -1, ticks);
        }
    }

    public void turnOnCorners(Corner corner, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(corner) {
            case FRONT_LEFT_CORNER:
                for (Motor wheel : wheels) {
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
                }
            case BACK_LEFT_CORNER:
                for (Motor wheel : wheels) {
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
                }
            case FRONT_RIGHT_CORNER:
                for (Motor wheel : wheels) {
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
                }
            case BACK_RIGHT_CORNER:
                for (Motor wheel : wheels) {
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
                }
        }

        waitForMotor(wheels.get(0));

        for (Motor wheel : wheels) {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnOnBackCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                for (Motor wheel : wheels) {
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
                }
            case RIGHT:
                for (Motor wheel : wheels) {
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
                }
        }

        waitForMotor(wheels.get(0));

        for (Motor wheel : wheels) {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void turnOnFrontCenter(HorizontalDirection tDirection, int ticks, double power) {
        // https://www.roboteq.com/images/article-images/frontpage/wheel-rotations.jpg
        switch(tDirection) {
            case LEFT:
                for (Motor wheel : wheels) {
                    switch (wheel.getWheelType()) {
                        case BACK_LEFT:
                            wheel.set(power * -1);
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
                }
            case RIGHT:
                for(Motor wheel : wheels) {
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
                }
        }

        waitForMotor(wheels.get(0));

        for (Motor wheel : wheels) {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public void diagonalDrive(DiagonalDirection dDirection, int ticks, double power) {
        switch(dDirection) {
            case LEFT_FORWARD:
                for (Motor wheel : wheels) {
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
                }
            case RIGHT_FORWARD:
                for (Motor wheel : wheels) {
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

                }
            case LEFT_BACKWARD:
                for (Motor wheel : wheels) {
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
                }
            case RIGHT_BACKWARD:
                for(Motor wheel : wheels) {
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
                }
        }

        waitForMotor(wheels.get(0));

        for (Motor wheel : wheels) {
            wheel.stopMotor();
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            wheel.getTicker().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    public RobotVision getRobotVision() {
        return robotVision;
    }

    public ArrayList<Motor> getWheels() {
        return wheels;
    }
}