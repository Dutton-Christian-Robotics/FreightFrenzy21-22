package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "1 Controller Test", group = "Testing")
public class OneControllerTestOpMode extends LinearOpMode
{
	ProductionBot bot;
	int spinnerDirection = 0;
    private DefenderDebouncer rightBumperDebouncer, leftBumperDebouncer;

	@Override
	public void runOpMode() {
		bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
	    rightBumperDebouncer = new DefenderDebouncer(500, () -> {
		   if (spinnerDirection != 1) {
			  bot.spinner.spinClockwise();
			  spinnerDirection = 1;
		   } else {
			  spinnerDirection = 0;
			  bot.spinner.stopSpinner();
		   }
	    });
	    leftBumperDebouncer = new DefenderDebouncer(500, () -> {
		   if (spinnerDirection != -1) {
			  bot.spinner.spinCounterClockwise();
			  spinnerDirection = -1;
		   } else {
			  spinnerDirection = 0;
			  bot.spinner.stopSpinner();
		   }
	    });

		waitForStart();
		while (opModeIsActive()) {
		  bot.drivetrain.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.left_stick_x);

			if (gamepad1.right_bumper) {
			    rightBumperDebouncer.run();
			} else if (gamepad1.left_bumper) {
			    leftBumperDebouncer.run();
			}
			if (gamepad1.dpad_up) {
			    bot.arm.raise(0.5);
			} else if (gamepad1.dpad_down) {
			    bot.arm.lower(0.5);
			} else {
//			    bot.arm.stopTiltMotor();
			    bot.arm.holdPosition();
			}

			if (gamepad1.dpad_right) {
			    bot.arm.extend();
			} else if (gamepad1.dpad_left) {
			    bot.arm.retract();
			} else {
			    bot.arm.stopExtendMotor();
			}

			if (gamepad1.b) {
			    bot.claw.close();
			} else if (gamepad1.a) {
			    bot.claw.open();
			}

			if (gamepad1.x) {
			    if (bot.vision.knowsBarcodePosition()) {
				   telemetry.addData("position", bot.vision.barcodePosition());

			    } else {
				   telemetry.addData("barcode", "NOT FOUND");
			    }
			}
//		    telemetry.addData("Back Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backLeft.getCurrentPosition());
//			telemetry.addData("Front Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontLeft.getCurrentPosition());
//		    telemetry.addData("Front Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontRight.getCurrentPosition());
//		    telemetry.addData("Back Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backRight.getCurrentPosition());
		    telemetry.addData("tilt", bot.arm.getTiltMotorPosition());
		    telemetry.addData("extend", bot.arm.getExtendMotorPosition());
			 telemetry.update();


		}
	}
}