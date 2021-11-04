package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProductionBotArm extends DefenderBotSystem {

    private DcMotorEx tiltMotor;
    private DcMotorEx extendMotor;

    ProductionBotArm(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
	   super(hm, config, b);

	   tiltMotor = hm.get(DcMotorEx.class, configString("ARM_TILT_MOTOR_NAME"));
	   tiltMotor.setDirection(configMotorDirection("ARM_TILT_MOTOR_DIRECTION"));
	   tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	   tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	   extendMotor = hm.get(DcMotorEx.class, configString("ARM_EXTEND_MOTOR_NAME"));
	   extendMotor.setDirection(configMotorDirection("ARM_EXTEND_MOTOR_DIRECTION"));
	   extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

// ----------------------------------------

    public void setTiltMotorPower(double p) {
	   tiltMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        tiltMotor.setPower(p);
    }

    public void setTiltMotorPowerByRatio(double r) {
	   setTiltMotorPower(r * configDouble("ARM_TILT_POWER_MAX"));
    }

    public int getTiltMotorPosition() {
	   return tiltMotor.getCurrentPosition();
    }

    public void setTiltMotorPosition(int p) {
		setTiltMotorPosition(p, 1);
    }

    public void setTiltMotorPosition(int p, double r) {
        tiltMotor.setTargetPosition(p);
        tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tiltMotor.setVelocity(r * configInt("ARM_TILT_VELOCITY_MAX"));
    }

    public void awaitTiltMotorPosition(int p, double r) {
        setTiltMotorPosition(p);
	   while (isTiltMotorBusy()) {
		  sleep(100);
	   }
    }

    public void awaitTiltMotorPosition(int p) {
        awaitTiltMotorPosition(p, 1);
    }

    public void stopTiltMotor() {
	   setTiltMotorPower(0);
    }

    public boolean isTiltMotorBusy() {
	   return tiltMotor.isBusy();
    }




// ----------------------------------------


    public void setExtendMotorPower(double p) {
	   extendMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extendMotor.setPower(p);
    }

    public void setExtendMotorPowerByRatio(double r) {
	   setExtendMotorPower(r * configDouble("ARM_TILT_POWER_MAX"));
    }

    public int getExtendMotorPosition() {
	   return extendMotor.getCurrentPosition();
    }

    public void setExtendMotorPosition(int p, double r) {
	   extendMotor.setTargetPosition(p);
	   extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   extendMotor.setVelocity(r * configInt("ARM_EXTEND_VELOCITY_MAX"));
    }

    public void setExtendMotorPosition(int p) {
	   setExtendMotorPosition(p, 1);
    }

    public void awaitExtendMotorPosition(int p, double r) {
	   setExtendMotorPosition(p);
	   while (isExtendMotorBusy()) {
		  sleep(100);
	   }
    }

    public void awaitExtendMotorPosition(int p) {
	   awaitExtendMotorPosition(p, 1);
    }


    public void stopExtendMotor() {
	   setExtendMotorPower(0);
    }

    public boolean isExtendMotorBusy() {
        return extendMotor.isBusy();
    }

// ----------------------------------------

    public void awaitTiltAndExtendMotorPositions(int tiltPosition, int extendPosition) {
        awaitTiltAndExtendMotorPositions(tiltPosition, extendPosition, 1, 1);
    }

    public void awaitTiltAndExtendMotorPositions(int tiltPosition, int extendPosition, double tiltRatio, double extendRatio) {
        setTiltMotorPosition(tiltPosition, tiltRatio);
	   setExtendMotorPosition(extendPosition, extendRatio);
	   while (isTiltMotorBusy() && isExtendMotorBusy()) {
	       sleep(100);
	   }
    }

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

    public void holdPosition() {
	   int currentPosition = tiltMotor.getCurrentPosition();
//	   bot.telemetry.addData("position", currentPosition);
//	   bot.telemetry.update();
	   tiltMotor.setTargetPosition(currentPosition);
	   tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	   tiltMotor.setVelocity(configInt("ARM_TILT_VELOCITY_MAX"));
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
	   setExtendMotorPowerByRatio(-r);
    }


}
