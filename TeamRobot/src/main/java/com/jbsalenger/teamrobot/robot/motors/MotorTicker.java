package com.jbsalenger.teamrobot.robot.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public interface MotorTicker {

    /**
     * Change motor property on ticker
     *
     * @param motor The motor that the ticker is attached to
     */
    void setMotor(REVMotor motor);

    /**
     * Get motor property on ticker
     *
     * @return motor attached to ticker
     */
    Motor getMotor();

    /**
     * Set the target position of the encoder.
     *
     * @param targetPos The number of ticks the motor should go through before stopping.
     */
    void setTargetPos(int targetPos);

    /**
     * Get the target position of the encoder.
     *
     * @return The current target position of the encoder
     */
    int getTargetPos();

    /**
     * Get motor RunMode
     *
     * @return The Supers RunMode
     */
    DcMotor.RunMode getRunMode();

    /**
     * Set Super's RunMode
     *
     * @param runMode The RunMode that should be attatched to the super motor class.
     */
    void setRunMode(DcMotor.RunMode runMode);

    /**
     * Get Motor.isBusy() value
     *
     * @return Super Motor.isBusy() value
     */
    boolean getIsBusy();

    /**
     * Get TicksPerRotation
     *
     * @return TicksPerRotation as an integer
     */
    int getTicksPerRotation();

    /**
     * Set the tickers ticks per rotation or revolution
     *
     * @param ticksPerRotation ticks per rotation of the ticker as an integer
     */
    void setTicksPerRotation(int ticksPerRotation);

}
