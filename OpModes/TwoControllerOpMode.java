package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "2 Controller", group = "Competition")
public class TwoControllerOpMode extends LinearOpMode
{
	ProductionBot bot;
	int spinnerDirection = 0;
    private DefenderDebouncer spinnerClockwiseDebouncer, spinnerCounterClockwiseDebouncer;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);
	   spinnerClockwiseDebouncer = new DefenderDebouncer(500, () -> {
		   if (spinnerDirection != 1) {
			  bot.spinner.spinClockwise();
			  spinnerDirection = 1;
		   } else {
			  spinnerDirection = 0;
			  bot.spinner.stopSpinner();
		   }
	   });
	   spinnerCounterClockwiseDebouncer = new DefenderDebouncer(500, () -> {
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

			if (gamepad2.dpad_right) {
			    spinnerClockwiseDebouncer.run();
			} else if (gamepad2.dpad_left) {
			    spinnerCounterClockwiseDebouncer.run();
			}
			if (gamepad2.left_stick_y < 0) {
			    bot.arm.raise(-1 * gamepad2.left_stick_y);
			} else if (gamepad2.left_stick_y > 0) {
			    bot.arm.lower(gamepad2.left_stick_y);
			} else {
//			    bot.arm.stopTiltMotor();
			    bot.arm.holdPosition();
			}

			if (gamepad2.right_stick_x > 0) {
			    bot.arm.extend(gamepad2.right_stick_x);
			} else if (gamepad2.right_stick_x < 0) {
			    bot.arm.retract(-1 * gamepad2.right_stick_x);
			} else {
			    bot.arm.stopExtendMotor();
			}

			if (gamepad2.right_bumper) {
			    bot.claw.close();
			} else if (gamepad2.left_bumper) {
			    bot.claw.open();
			}

			if (gamepad1.x) {
			    if (bot.vision.knowsBarcodePosition()) {
				   telemetry.addData("position", bot.vision.barcodePosition());
//				   telemetry.addData("confidence", bot.vision.barcodeConfidence());
			    } else {
				   telemetry.addData("barcode", "NOT FOUND");
			    }
			}
//		    telemetry.addData("Back Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backLeft.getCurrentPosition());
//			telemetry.addData("Front Left", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontLeft.getCurrentPosition());
//		    telemetry.addData("Front Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).frontRight.getCurrentPosition());
//		    telemetry.addData("Back Right", ((ProductionBotMecanumDrivetrain)bot.drivetrain).backRight.getCurrentPosition());
			telemetry.update();


		}
	}
}