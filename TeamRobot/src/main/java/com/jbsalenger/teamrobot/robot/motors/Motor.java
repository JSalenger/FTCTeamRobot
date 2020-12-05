package com.jbsalenger.teamrobot.robot.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.jbsalenger.teamrobot.robot.enums.WheelType;

public interface Motor {

    /**
     * Change motor power.
     *
     * @param power The power level to attatch to the motor
     */
    public void set(double power);

    /**
     * Get motor power.
     *
     * @return The current power of the motor.
     */
    public double get();

    /**
     * Get Motor Type
     *
     * @return Motor type as a String.
     */
    public String getMotorType();

    /**
     * Stop the motor.
     */
    public void stopMotor();

    /**
     * Resets the ticker
     */
    public void resetTicker();

    /**
     * Disable the motor.
     */
    public void disable();

    /**
     * Get the DcMotor object attatched to the Motor.
     *
     * @return DcMotor child of Motor
     */
    public DcMotor getMotor();

    /**
     * Set the DcMotor child of Motor
     *
     * @param motor DcMotor to be set as child of Motor
     */
    public void setMotor(DcMotor motor);

    /**
     * Set WheelType of motor
     *
     * @param wheelType WheelType of Motor parent object
     */
    public void setWheelType(WheelType wheelType);

    /**
     * Get WheelType of motor
     *
     * @return child WheelType enum
     */
    public WheelType getWheelType();


    /**
     * Get child MotorTicker object
     *
     * @return MotorTicker object
     */
    public MotorTicker getTicker();

    /**
     * Set ZeroPowerBehavior of motor
     *
     * @param zeroPowerBehavior the behavior of the motor when power is 0
     */
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior);

}
