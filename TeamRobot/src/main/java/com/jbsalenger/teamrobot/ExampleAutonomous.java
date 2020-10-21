package com.jbsalenger.teamrobot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.jbsalenger.teamrobot.robot.enums.*;
import com.jbsalenger.teamrobot.robot.TeamRobot;

/**
 * This is an example OpMode using the TeamRobot class to perform autonomous movement.
 */

@Autonomous(name="Example: Autonomous Robot Movement", group="Linear Opmode")
@Disabled
public class ExampleAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() {
        int r = 1120; //rotation in ticks
        int in = r/10; //inch in rotation
        TeamRobot robot = new TeamRobot(hardwareMap, 1000);

        waitForStart();

        robot.driveStraight(in*12, 1);
        // TODO: FIGURE OUT TICKS TO DEGREES RATIO
        robot.turnOnCenter(HorizontalDirection.LEFT, 90, 1);
        robot.driveHorizontal(HorizontalDirection.LEFT, 10*in,1);
        robot.turnOnCorners(Corner.BACK_RIGHT_CORNER, 180, 1);
        robot.diagonalDrive(DiagonalDirection.RIGHT_FORWARD, in,1);
        robot.turnOnFrontCenter(HorizontalDirection.RIGHT, 100, 1);

        robot.turnOnBackCenter(HorizontalDirection.LEFT, 200, 1);
    }
}
