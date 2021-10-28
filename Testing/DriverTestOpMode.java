package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Driver Test", group = "Testing")
public class DriverTestOpMode extends LinearOpMode
{
	ProductionBot bot;
	boolean isShooting = false;
    private DefenderDebouncer rightBumperDebouncer;

	@Override
	public void runOpMode() {
		bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
//	    rightBumperDebouncer =
		int spinnerDirection = 0;
		waitForStart();
		while (opModeIsActive()) {
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

			if (gamepad1.right_bumper) {
			    if (spinnerDirection != 1) {
				   bot.spinner.spinClockwise();
				   spinnerDirection = 1;
			    } else {
				   spinnerDirection = 0;
				   bot.spinner.stopSpinner();
			    }
			} else if (gamepad1.left_bumper) {
			    if (spinnerDirection != -1) {
				   bot.spinner.spinCounterClockwise();
				   spinnerDirection = -1;
			    } else {
				   spinnerDirection = 0;
				   bot.spinner.stopSpinner();
			    }
			}
			if (gamepad1.dpad_up) {
			    bot.arm.raise();
			} else if (gamepad1.dpad_down) {
			    bot.arm.lower();
			} else {
			    bot.arm.stopTiltMotor();
			}

			if (gamepad1.dpad_right) {
			    bot.arm.extend();
			} else if (gamepad1.dpad_left) {
			    bot.arm.retract();
			} else {
			    bot.arm.stopExtendMotor();
			}

//			if (gamepad1.right_bumper) {
//				bot.shooter.shoot();
//				isShooting = true;
//			}
//			if (gamepad1.left_bumper) {
//				if (isShooting) {
//					bot.shooter.stopShooter();
//					isShooting = false;
//				} else {
//					bot.shooter.startShooter();
//					isShooting = true;
//				}
//			}
			
//			telemetry.addData("X Value: ", gamepad1.left_stick_x);
//			telemetry.addData("Y Value: ", gamepad1.left_stick_y);
//			telemetry.update();


		}
	}
}