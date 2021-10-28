package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProductionBotArm extends DefenderBotSystem {

    public DcMotor tiltMotor;
    public DcMotor extendMotor;

    ProductionBotArm(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   tiltMotor = hm.dcMotor.get(configString("ARM_TILT_MOTOR_NAME"));
	   tiltMotor.setDirection(configMotorDirection("ARM_TILT_MOTOR_DIRECTION"));

	   extendMotor = hm.dcMotor.get(configString("ARM_EXTEND_MOTOR_NAME"));
	   extendMotor.setDirection(configMotorDirection("ARM_EXTEND_MOTOR_DIRECTION"));
    }

// ----------------------------------------

    public void setTiltMotorPower(double p) {
	   tiltMotor.setPower(p);
    }

    public void setTiltMotorPowerByRatio(double r) {
	   setTiltMotorPower(r * configDouble("ARM_TILT_POWER_MAX"));
    }

    public void stopTiltMotor() {
	   setTiltMotorPower(0);
    }

// ----------------------------------------

    public void setExtendMotorPower(double p) {
	   extendMotor.setPower(p);
    }

    public void setExtendMotorPowerByRatio(double r) {
	   setExtendMotorPower(r * configDouble("ARM_TILT_POWER_MAX"));
    }

    public void stopExtendMotor() {
	   setExtendMotorPower(0);
    }

// ----------------------------------------

    public void stopAll() {
        stopTiltMotor();
        stopExtendMotor();
    }

// ----------------------------------------

    public void raise() {
        raise(1);
    }

    public void raise(double r) {
        setTiltMotorPowerByRatio(r);
    }

    public void lower() {
        lower(1);
    }

    public void lower(double r) {
        setTiltMotorPowerByRatio(-r);
    }

// ----------------------------------------

    public void extend() {
	   extend(1);
    }

    public void extend(double r) {
        setExtendMotorPowerByRatio(r);
    }

    public void retract() {
	   retract(1);
    }

    public void retract(double r) {
	   setExtendMotorPowerByRatio(r);
    }


}
