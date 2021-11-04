package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Vision System Test", group = "Testing")
public class VisionSystemTestOpMode extends LinearOpMode {
    ProductionBot bot;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();
	   while (opModeIsActive()) {
		  telemetry.addData("region", bot.vision.barcodePosition());
		  telemetry.update();
		  sleep(1500);
	   }
    }
}