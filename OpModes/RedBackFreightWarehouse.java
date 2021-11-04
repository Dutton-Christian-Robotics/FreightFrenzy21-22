package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
	0) Start red side, camera centered on front barcode
	1) Detect barcode
	2) Drive to alliance shipping hub
	3) Deposit pre-loaded freight according to barcode
	4) Drive into warehouse
 */

@Autonomous(name = "Red Front Freight Warebouse", group = "Red")
public class RedBackFreightWarehouse extends LinearOpMode {
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

	   bot.navigation.driveToPosition(-34, 6);
	   bot.navigation.driveToPosition(-34, 18);

	   if (barcodePosition == 1) {
	       bot.arm.awaitTiltMotorPosition(64);
	       bot.arm.awaitExtendMotorPosition(500);
//	       bot.navigation.driveToPosition(34, 19);
	       bot.claw.open();
	       sleep(1000);

	   } else if (barcodePosition == 2) {
		  // opens claw too early
		  bot.arm.awaitTiltAndExtendMotorPositions(80, 1026);
		  bot.claw.open();
		  sleep(1000);
		  bot.arm.awaitTiltAndExtendMotorPositions(100, 0);

	   } else if (barcodePosition == 3) {

	       bot.arm.awaitTiltMotorPosition(154);
		  bot.arm.awaitExtendMotorPosition(1500);

		  bot.claw.open();
		  sleep(1000);
		  bot.arm.setTiltMotorPosition(0);
		  bot.arm.setExtendMotorPosition(0);

	   }

	   bot.navigation.resetAndDriveToPosition(0, -2);
	   bot.navigation.comeToHeading(-90);
	   bot.navigation.resetAndDriveToPosition(0, 65);
    }
}