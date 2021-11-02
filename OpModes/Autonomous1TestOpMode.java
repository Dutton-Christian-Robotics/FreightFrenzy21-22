package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Autonomous 1 Test", group = "Testing")
public class Autonomous1TestOpMode extends LinearOpMode
{
    ProductionBot bot;

    @Override
    public void runOpMode() {
	   bot = new ProductionBot(hardwareMap, ProductionBotConfiguration.class, telemetry);

	   waitForStart();
	   bot.navigation.driveToPosition(0, 24);
	   bot.navigation.comeToHeading(90);
	   bot.stopDriving();
//	   while (opModeIsActive()) {
//	   }
    }
}