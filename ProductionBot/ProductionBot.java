package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ProductionBot extends DefenderBot {

    public ProductionBotSpinner spinner;
    public ProductionBotArm arm;


    ProductionBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(ProductionBotMecanumDrivetrain.class);
	   spinner = addSystem(ProductionBotSpinner.class);
	   arm = addSystem(ProductionBotArm.class);


//		vision = addSystem(VisionSystem.class);

//		sensors = addSystem(RockyBotSensors.class);
//		navigation = addSystem(RockyBotNavigation.class);

	}
}
