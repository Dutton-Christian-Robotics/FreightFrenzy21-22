package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
	0) Start red side, camera centered on front barcode
	1) Detect barcode
	2) Drive to alliance shipping hub
	3) Deposit pre-loaded freight according to barcode
	4) Drive into warehouse
 */

@Disabled
@Autonomous(name = "Red Front Freight Warebouse", group = "Red")
public class OriginalRedFrontFreightWarehouse extends LinearOpMode {
    ProductionBot bot;
    int barcodePosition = -1;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();
	   bot.claw.close();
	   while (barcodePosition < 1) {
	       sleep(100);
		  barcodePosition = bot.vision.barcodePosition();
	   }

	   telemetry.addData("barcode", barcodePosition);
	   telemetry.update();

	   bot.navigation.driveToPosition(34, 6);
	   bot.navigation.driveToPosition(34, 18);
	   if (barcodePosition == 1) {
	       bot.arm.setTiltMotorPosition(64);
//	       bot.arm.setExtendMotorPosition(551);
	       while (bot.arm.isTiltMotorBusy()) {
	           sleep(100);
		  }
		bot.arm.setExtendMotorPosition(500);
		  while (bot.arm.isExtendMotorBusy()) {
			 sleep(100);
		  }

//	       bot.navigation.driveToPosition(34, 19);
	       bot.claw.open();
	       sleep(1000);
//	       bot.arm.setExtendMotorPosition(0);
//	       bot.arm.setTiltMotorPosition(0);
//	       bot.claw.close();
	   } else if (barcodePosition == 2) {
//opens claw too early
		  bot.arm.setTiltMotorPosition(80);
		  bot.arm.setExtendMotorPosition(1026);
		  while (bot.arm.isTiltMotorBusy() && bot.arm.isExtendMotorBusy()) {
			 sleep(100);
		  }
		  bot.claw.open();
		  sleep(1000);
		  bot.arm.setExtendMotorPosition(0);

		  bot.arm.setTiltMotorPosition(100);
		  while (bot.arm.isTiltMotorBusy()) {
			 sleep(100);
		  }
		  bot.claw.close();
	   } else if (barcodePosition == 3) {

		  bot.arm.setTiltMotorPosition(154);
		  while (bot.arm.isTiltMotorBusy()) {
			 sleep(100);
		  }
		  bot.arm.setExtendMotorPosition(1500);
		  while (bot.arm.isExtendMotorBusy()) {
			 sleep(100);
		  }
//		  bot.arm.setTiltMotorPosition(130);
//		  while (bot.arm.isTiltMotorBusy()) {
//			 sleep(100);
//		  }
		  bot.claw.open();
		  sleep(1000);
		  bot.arm.setTiltMotorPosition(0);
		  bot.arm.setExtendMotorPosition(0);

	   }

		bot.navigation.driveToPosition(34, 18);
	   bot.navigation.comeToHeading(-90);
	   bot.drive(1, 0, 0);
	   sleep(2500);
	   bot.stopDriving();

//	   bot.navigation.driveToPosition(36, 8);
//	   bot.navigation.driveToPosition(0, 8);
//	   bot.spinner.spinCounterClockwise();
//	   bot.navigation.driveToPosition(-12, 8);
//	   bot.navigation.comeToHeading(90);
//	   bot.navigation.driveToPosition(6, 10, 90);
//	   bot.stopDriving();
//
//	   sleep(5000);


//	   bot.spinner.stopSpinner();
//	   bot.navigation.driveToPosition(12, -288);
//	   while (opModeIsActive()) {
//	   }
    }
}