package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name = "Nav 1 Test", group = "Testing")
public class Nav1TestOpMode extends LinearOpMode
{
    ProductionBot bot;
    int barcodePosition;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();
	   barcodePosition = bot.vision.barcodePosition();
	   telemetry.addData("barcode", barcodePosition);
	   telemetry.update();

	   bot.navigation.driveToPosition(0, 10);

//	   bot.navigation.driveToPosition(-12, 8);
//	   bot.navigation.comeToHeading(90);
	   bot.navigation.driveToPosition(-10, 10, 90);
	   bot.stopDriving();

	   sleep(5000);


//	   bot.spinner.stopSpinner();
//	   bot.navigation.driveToPosition(12, -288);
//	   while (opModeIsActive()) {
//	   }
    }
}