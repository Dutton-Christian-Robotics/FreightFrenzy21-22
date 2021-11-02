package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ProductionBot extends DefenderBot {

    public ProductionBotSpinner spinner;
    public ProductionBotArm arm;
    public ProductionBotClaw claw;
    public ProductionBotVision vision;
    public ProductionBotSensors sensors;
    public ProductionBotNavigation navigation;
//    public ProductionBotMecanumDrivetrain drivetrain;


    ProductionBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ProductionBotMecanumDrivetrain.class);
	   spinner = addSystem(ProductionBotSpinner.class);
	   arm = addSystem(ProductionBotArm.class);
	   claw = addSystem(ProductionBotClaw.class);
	   vision = addSystem(ProductionBotVision.class);
	   sensors = addSystem(ProductionBotSensors.class);
	   navigation = addSystem(ProductionBotNavigation.class);

	}
}
