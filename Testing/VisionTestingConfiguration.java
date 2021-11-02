package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.lang.reflect.Field;

public class VisionTestingConfiguration extends DefenderBotConfiguration {

    public double DRIVETRAIN_POWER_MAX;

    public String IMU_SENSOR_NAME;
    public AxesOrder IMU_AXES_ORDER;

    public double NAVIGATION_POWER_DEFAULT;
    public long NAVIGATION_TIMEOUT_DEFAULT;
    public double NAVIGATION_TOLERANCE_ROTATION;
    public double NAVIGATION_TOLERANCE_X;
    public double NAVIGATION_TOLERANCE_Y;
    public double NAVIGATION_GEAR_RATIO;
    public long NAVIGATION_TICKS_PER_ROTATION;
    public double NAVIGATION_WHEEL_RADIUS;
    public double NAVIGATION_INCHES_PER_TICK;

    VisionTestingConfiguration() {
	   super();
	   DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left";
	   DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left";
	   DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right";
	   DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right";
	   DRIVETRAIN_POWER_MAX = 1.0;

	   IMU_SENSOR_NAME = "imu";
	   IMU_AXES_ORDER = AxesOrder.XYZ;

	   NAVIGATION_POWER_DEFAULT = 0.42;
	   NAVIGATION_TIMEOUT_DEFAULT = 10000;
	   NAVIGATION_TOLERANCE_ROTATION = 0.3;
	   NAVIGATION_TOLERANCE_X = 1.0;
	   NAVIGATION_TOLERANCE_Y = 1.0;

	   NAVIGATION_GEAR_RATIO = 0.26; //is this right?
	   NAVIGATION_TICKS_PER_ROTATION = 280;
	   NAVIGATION_WHEEL_RADIUS = 2; // 4 inches diameter
	   NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;;


	   for (Field f : VisionTestingConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }

    }

}
