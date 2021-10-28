package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Vistion Testing 1", group = "Testing")
public class VisionTestOpMode1 extends LinearOpMode {

	RockyBot bot;

	@Override
	public void runOpMode() {
		bot = new RockyBot(hardwareMap, VisionTestingConfiguration.class, telemetry);

	}
}