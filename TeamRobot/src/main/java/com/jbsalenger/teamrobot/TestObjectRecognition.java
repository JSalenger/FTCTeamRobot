package com.jbsalenger.teamrobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.jbsalenger.teamrobot.robot.TeamRobot;
import com.jbsalenger.teamrobot.robot.enums.WheelType;
import com.jbsalenger.teamrobot.robot.motors.REVMotor;

import java.util.List;

/**
 * Use to test that your object detection is working
 */

@TeleOp(name="Test: Object Recognition", group="Linear Opmode")
public class TestObjectRecognition extends LinearOpMode {

    // Declare OpMode members.
    private final REVMotor front_left = new REVMotor(null, 1, WheelType.FRONT_LEFT);
    private final REVMotor front_right = new REVMotor(null, 1, WheelType.FRONT_RIGHT);
    private final REVMotor back_left = new REVMotor(null, 1, WheelType.BACK_LEFT);
    private final REVMotor back_right = new REVMotor(null, 1, WheelType.BACK_RIGHT);
    private TeamRobot robot = new TeamRobot(hardwareMap, "", "",
            new String[]{"Skystone", "Stone"}, front_left, front_right, back_left, back_right);

    @Override
    public void runOpMode() {
        waitForStart();

        while(opModeIsActive()) {

            List<Recognition> recognitions = robot.getRobotVision().detect();

            telemetry.addData("Number of objects recognized: ", (recognitions.size() + ""));

            recognitions.forEach(recognition -> {
                telemetry.addData("Found: ", recognition.getLabel());
                telemetry.addData("- Confidence: ", (recognition.getConfidence() + ""));
                telemetry.addData("- Distance from L,R,T,B: ", (
                        recognition.getLeft() + ", " +
                                recognition.getRight() + ", " +
                                recognition.getTop() + ", " +
                                recognition.getBottom() + "."
                ));
            });
        }
    }

}
