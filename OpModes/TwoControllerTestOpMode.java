package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "2 Controller Test", group = "Testing")
public class TwoControllerTestOpMode extends LinearOpMode
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

			if (gamepad2.right_bumper) {
			    rightBumperDebouncer.run();
			} else if (gamepad2.left_bumper) {
			    leftBumperDebouncer.run();
			}
			if (gamepad2.dpad_up) {
			    bot.arm.raise(0.5);
			} else if (gamepad2.dpad_down) {
			    bot.arm.lower(0.5);
			} else {
//			    bot.arm.stopTiltMotor();
			    bot.arm.holdPosition();
			}

			if (gamepad2.dpad_right) {
			    bot.arm.extend();
			} else if (gamepad2.dpad_left) {
			    bot.arm.retract();
			} else {
			    bot.arm.stopExtendMotor();
			}

			if (gamepad2.b) {
			    bot.claw.close();
			} else if (gamepad2.a) {
			    bot.claw.open();
			}

			if (gamepad1.x) {
			    if (bot.vision.knowsBarcodePosition()) {
				   telemetry.addData("position", bot.vision.barcodePosition());
				   telemetry.addData("confidence", bot.vision.barcodeConfidence());
			    } else {
				   telemetry.addData("barcode", "NOT FOUND");
			    }
			}
		    telemetry.addData("Back Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backLeft.getCurrentPosition());
			telemetry.addData("Front Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontLeft.getCurrentPosition());
		    telemetry.addData("Front Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontRight.getCurrentPosition());
		    telemetry.addData("Back Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backRight.getCurrentPosition());
			telemetry.update();


		}
	}
}