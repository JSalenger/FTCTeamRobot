package com.jbsalenger.teamrobot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.jbsalenger.teamrobot.robot.driving.RobotDriver;
import com.jbsalenger.teamrobot.robot.enums.WheelType;
import com.jbsalenger.teamrobot.robot.motors.REVMotor;



/**
 * This is a fairly basic DriverControlled OpMode, but the advantage here is that we can focus more on the button mappings and good
 * UX design for the controller instead of filling the file with front_left.set(1) front_right.set(1) etc.
 */

@TeleOp(name="Example: Driver Controlled", group="Linear Opmode")
public class ExampleDriverControl extends LinearOpMode {

    // Declare OpMode members.
    private final REVMotor front_left = new REVMotor(null, 1, WheelType.FRONT_LEFT);
    private final REVMotor front_right = new REVMotor(null, 1, WheelType.FRONT_RIGHT);
    private final REVMotor back_left = new REVMotor(null, 1, WheelType.BACK_LEFT);
    private final REVMotor back_right = new REVMotor(null, 1, WheelType.BACK_RIGHT);
    private RobotDriver robotD = new RobotDriver(front_left, front_right, back_left, back_right);


    @Override
    public void runOpMode() {

        waitForStart();
        while(opModeIsActive()) {

            float front_left_pwr = -1 * gamepad1.left_stick_x + gamepad1.left_stick_y;
            float front_right_pwr = gamepad1.left_stick_y + gamepad1.left_stick_x;

            float front_left_strafe_pwr = gamepad1.right_stick_x;
            float front_right_strafe_pwr = -1 * gamepad1.right_stick_x;

            /*
             * Compute which function is recieveing more power'
             * For example if I turn the stick to the right more than forward the numbers to strafe would be higher
             */
            if((Math.abs(front_left_pwr) + Math.abs(front_right_pwr)/2) > (Math.abs(front_right_strafe_pwr) + Math.abs(front_right_strafe_pwr)/2)) {
                // The stick is pushed forward/backwards than right/left
                robotD.drive(front_left_pwr, front_right_pwr, front_left_pwr, front_right_pwr);
            } else {
                robotD.driveHorizontal(front_left_strafe_pwr, front_right_strafe_pwr);
            }
        }

    }
}
