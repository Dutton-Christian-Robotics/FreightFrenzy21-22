package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ComplexDriveRobot {
	HardwareMap hwMap;
	public DcMotor backLeft, frontLeft, frontRight, backRight;
	private final double maxPower = 1.0;

	ComplexDriveRobot(HardwareMap hm) {
		hwMap = hm;
		backLeft = hwMap.dcMotor.get("back_left_motor");
		frontLeft = hwMap.dcMotor.get("front_left_motor");
		frontRight = hwMap.dcMotor.get("front_right_motor");
		backRight = hwMap.dcMotor.get("back_right_motor");

	}

	public void setPower(double bl, double fl, double fr, double br) {
		backLeft.setPower(bl);
		frontLeft.setPower(fl);
		frontRight.setPower(fr);
		backRight.setPower(br);

		setMotorDirection(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE,
		DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);
	}

	public void setPower(double p) {
		setPower(p, p, p, p);
	}

	public void setMotorDirection(DcMotorSimple.Direction bl, DcMotorSimple.Direction fl,
							DcMotorSimple.Direction fr, DcMotorSimple.Direction br) {
		backLeft.setDirection(bl);
		frontLeft.setDirection(fl);
		frontRight.setDirection(fr);
		backRight.setDirection(br);
	}

	public void setMotorDirection(DcMotorSimple.Direction left, DcMotorSimple.Direction right) {
		setMotorDirection(left, left, right, right);
	}

	private void setProportionalPower(double bl, double fl, double fr, double br) {
		double largest = maxPower;
		largest = Math.max(largest, Math.abs(bl));
		largest = Math.max(largest, Math.abs(fl));
		largest = Math.max(largest, Math.abs(fr));
		largest = Math.max(largest, Math.abs(br));

		backLeft.setPower(bl / largest);
		frontLeft.setPower(fl / largest);
		frontRight.setPower(fr / largest);
		backRight.setPower(br / largest);
	}


	public void drive(double forward, double strafe, double rotate) {
		double backLeftPower = forward - strafe + rotate;
		double frontLeftPower = forward + strafe + rotate;
		double frontRightPower = forward - strafe - rotate;
		double backRightPower = forward + strafe - rotate;

		setProportionalPower(backLeftPower, frontLeftPower, frontRightPower, backRightPower);
	}

	public void stopDriving() {
		setPower(0);
	}
}