package com.jbsalenger.teamrobot.robot.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

public class REVTicker implements MotorTicker {
    REVMotor motor;
    int ticksPerRotation;

    public REVTicker(REVMotor _motor, int _ticksPerRotation) {
        motor = _motor;
        ticksPerRotation = _ticksPerRotation;
    }

    @Override
    public void setMotor(REVMotor _motor) {
        motor = _motor;
    }

    @Override
    public Motor getMotor() {
        return motor;
    }

    @Override
    public void setTargetPos(int targetPos) {
        motor.getMotor().setTargetPosition(targetPos);
    }

    @Override
    public int getTargetPos() {
        return 0;
    }

    @Override
    public DcMotor.RunMode getRunMode() {
        return motor.getMotor().getMode();
    }

    @Override
    public void setRunMode(DcMotor.RunMode runMode) {
        motor.getMotor().setMode(runMode);
    }

    @Override
    public boolean getIsBusy() {
        return motor.getMotor().isBusy();
    }

    @Override
    public void setTicksPerRotation(int ticksPerRotation) {
        this.ticksPerRotation = ticksPerRotation;
    }

    @Override
    public int getTicksPerRotation() {
        return ticksPerRotation;
    }
}
