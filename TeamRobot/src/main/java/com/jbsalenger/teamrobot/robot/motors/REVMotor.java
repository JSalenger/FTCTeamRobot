package com.jbsalenger.teamrobot.robot.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.jbsalenger.teamrobot.robot.motors.Motor;
import com.jbsalenger.teamrobot.robot.enums.WheelType;

public class REVMotor implements Motor {

    private DcMotor motor;
    private REVTicker ticker;
    private WheelType wheelType;

    public REVMotor(DcMotor _motor, int ticksPerRotation, WheelType _wheelType) {
        motor = _motor;
        ticker = new REVTicker(this, ticksPerRotation);
        wheelType = _wheelType;
    }

    @Override
    public void set(double speed) {
        motor.setPower(speed);
    }

    @Override
    public double get() {
        return motor.getPower();
    }

    @Override
    public void disable() {
        motor.close();
    }

    @Override
    public String getMotorType() {
        return motor.getDeviceName();
    }

    @Override
    public void stopMotor() {
        motor.setPower(0);
    }

    @Override
    public void resetTicker() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public DcMotor getMotor() {
        return motor;
    }

    @Override
    public void setMotor(DcMotor motor) {
        this.motor = motor;
    }

    @Override
    public REVTicker getTicker() {
        return ticker;
    }

    @Override
    public void setWheelType(WheelType wheelType) {
        this.wheelType = wheelType;
    }

    @Override
    public WheelType getWheelType() {
        return wheelType;
    }
}
