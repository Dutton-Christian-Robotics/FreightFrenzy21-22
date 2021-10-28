package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProductionBotSpinner extends DefenderBotSystem {

    public DcMotor spinnerMotor;
    public double currentSpinnerPower;

    ProductionBotSpinner(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   spinnerMotor = hm.dcMotor.get(configString("SPINNER_MOTOR_NAME"));
	   spinnerMotor.setDirection(configMotorDirection("SPINNER_MOTOR_DIRECTION"));
    }

    public boolean isSpinning() {
        return spinnerMotor.isBusy();
    }

    public void setSpinnerPower(double p) {
	   currentSpinnerPower = p;
	   spinnerMotor.setPower(currentSpinnerPower);
    }

    public void setSpinnerPowerByRatio(double r) {
	   setSpinnerPower(r * configDouble("SPINNER_POWER_MAX"));
    }

    public double getCurrentSpinnerPower() {
	   return currentSpinnerPower;
    }

    public void stopSpinner() {
        setSpinnerPower(0);
    }

    public void spinCounterClockwise() {
        setSpinnerPower(-1);
    }

    public void spinCounterClockwiseWithPower(double p) {
        setSpinnerPower(-p);
    }

    public void spinCounterClockwiseByRatio(double r) {
        setSpinnerPowerByRatio(-r);
    }

    public void spinClockwise() {
	   setSpinnerPower(1);
    }

    public void spinClockwiseWithPower(double p) {
	   setSpinnerPower(p);
    }

    public void spinClockwiseByRatio(double r) {
	   setSpinnerPowerByRatio(r);
    }


}
