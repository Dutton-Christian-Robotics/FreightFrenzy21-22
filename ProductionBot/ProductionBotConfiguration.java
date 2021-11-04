package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;

import java.lang.reflect.Field;

public class ProductionBotConfiguration extends DefenderBotConfiguration {

    public double DRIVETRAIN_POWER_MAX;

    public String ARM_TILT_MOTOR_NAME;
    public DcMotorSimple.Direction ARM_TILT_MOTOR_DIRECTION;
    public double ARM_TILT_POWER_MAX;
    public int ARM_TILT_VELOCITY_MAX;
    public int ARM_TILT_POSITION_DOWN;
    public int ARM_TILT_POSITION_LEVEL;
    public int ARM_TILT_POSITION_UP;

    public String ARM_EXTEND_MOTOR_NAME;
    public DcMotorSimple.Direction ARM_EXTEND_MOTOR_DIRECTION;
    public double ARM_EXTEND_POWER_MAX;
    public int ARM_EXTEND_VELOCITY_MAX;
    public int ARM_EXTEND_POSITION_IN;
    public int ARM_EXTEND_POSITION_OUT;
    public int ARM_EXTEND_POSITION_MAX;

    public String CLAW_SERVO_NAME;
    public double CLAW_POSITION_OPEN;
    public double CLAW_POSITION_CLOSED;

    public String SPINNER_MOTOR_NAME;
    public DcMotorSimple.Direction SPINNER_MOTOR_DIRECTION;
    public double SPINNER_MOTOR_POWER_MAX;

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

    public String CAMERA_NAME;
    public double VISION_THRESHOLD_DETECTION;

    ProductionBotConfiguration() {
	   super();
	   DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left";
	   DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left";
	   DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right";
	   DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right";
	   DRIVETRAIN_POWER_MAX = 1.0;

	   SPINNER_MOTOR_NAME = "spinner";
	   SPINNER_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   SPINNER_MOTOR_POWER_MAX = 1;

	   ARM_TILT_MOTOR_NAME = "arm_tilt";
	   ARM_TILT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
	   ARM_TILT_POWER_MAX = 1;
	   ARM_TILT_VELOCITY_MAX = 900;
	   ARM_TILT_POSITION_DOWN = 50;
	   ARM_TILT_POSITION_LEVEL = 0;
	   ARM_TILT_POSITION_UP = -29;

	   ARM_EXTEND_MOTOR_NAME = "arm_extend";
	   ARM_EXTEND_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
	   ARM_EXTEND_POWER_MAX = 1;
	   ARM_EXTEND_VELOCITY_MAX = 900;
	   ARM_EXTEND_POSITION_IN = 0;
	   ARM_EXTEND_POSITION_OUT = 661;
	   ARM_EXTEND_POSITION_MAX = 1978;

	   CLAW_SERVO_NAME = "claw";
	   CLAW_POSITION_OPEN = 0.15;
	   CLAW_POSITION_CLOSED = 0.8;

	   IMU_SENSOR_NAME = "imu";
	   IMU_AXES_ORDER = AxesOrder.XYZ;

	   NAVIGATION_POWER_DEFAULT = 0.65;
	   NAVIGATION_TIMEOUT_DEFAULT = 10000;
	   NAVIGATION_TOLERANCE_ROTATION = 0.3;
	   NAVIGATION_TOLERANCE_X = 1.0;
	   NAVIGATION_TOLERANCE_Y = 1.0;

	   NAVIGATION_GEAR_RATIO = 0.26; //is this right?
	   NAVIGATION_TICKS_PER_ROTATION = 280;
	   NAVIGATION_WHEEL_RADIUS = 2; // 4 inches diameter
	   NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;;

	   CAMERA_NAME = "Webcam 1";
	   VISION_THRESHOLD_DETECTION = 18;


	   for (Field f : ProductionBotConfiguration.class.getDeclaredFields()) {
		  fieldHashtable.put(f.getName(), f);
	   }

    }

}
