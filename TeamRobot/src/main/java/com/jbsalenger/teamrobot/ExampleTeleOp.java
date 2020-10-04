package com.jbsalenger.teamrobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.jbsalenger.teamrobot.robot.enums.*;
import com.jbsalenger.teamrobot.robot.TeamRobot;

import java.util.List;

/**
 * This is an example OpMode using the TeamRobot class to perform autonomous movement.
 */

@TeleOp(name="Example: Autonomous Robot Movement", group="Linear Opmode")
public class ExampleTeleOp extends LinearOpMode {

    // Declare OpMode members.
    final TeamRobot robot = new TeamRobot(hardwareMap, "---KEY---", "skystone.tflite", new String[]{"Skystone", "Stone"});

    @Override
    public void runOpMode() {
        waitForStart();

        //rotation in ticks
        int r = 1120;
        //inch in rotation
        int in = r/10;

        robot.driveStraight(in*12, 1);
        // TODO: FIGURE OUT TICKS TO DEGREES RATIO
        robot.turnOnCenter(HorizontalDirection.LEFT, 90, 1);
        robot.driveHorizontal(HorizontalDirection.LEFT, 10*in,1);
        robot.turnOnCorners(Corner.BACK_RIGHT_CORNER, 180, 1);
        robot.diagonalDrive(DiagonalDirection.RIGHT_FORWARD, in,1);
        robot.turnOnFrontCenter(HorizontalDirection.RIGHT, 100, 1);

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
        
        telemetry.update();
        robot.turnOnBackCenter(HorizontalDirection.LEFT, 200, 1);
    }
}
