package frc.robot;

public class SimSettings {

    // general
    public static final double ROBOT_WEIGHT_KG = 40;
    public static final double ROBOT_WIDTH_M = 4.0;
    public static final double ROBOT_LENGTH_M = 5;

    // drive
    public static final double DRIVE_MOTOR_TO_WHEEL_GEAR_RATIO = RobotMap.DRIVE_GEAR_RATIO;
    public static final double DRIVE_WHEEL_RADIUS_M = RobotMap.DRIVE_WHEEL_RADIUS_M;
    public static final double DRIVE_WHEEL_CIRCUMFERENCE_M = 2 * Math.PI * DRIVE_WHEEL_RADIUS_M;
    public static final int DRIVE_SIDE_MOTOR_COUNT = 2;
    public static final double DRIVE_TRACK_WIDTH_M = ROBOT_WIDTH_M;
    public static final double DRIVE_MOMENT_OF_INERTIA = (Math.pow(ROBOT_LENGTH_M, 3) * ROBOT_WIDTH_M) / 12;

    // turret
    public static final int TURRET_MOTOR_COUNT = 1;
    public static final double TURRET_CYLINDER_RADIUS_M = 0.5;
    public static final double TURRET_MASS = 5;
    public static final double TURRET_MOMENT_OF_INERTIA = 0.5 * TURRET_MASS * TURRET_CYLINDER_RADIUS_M;
    public static final double TURRET_MOTOR_TO_TURRET_GEAR_RATIO = RobotMap.TURRET_GEAR_RATIO;
    public static final double TURRET_MIN_ANGLE = -45;
    public static final double TURRET_MAX_ANGLE = 45;
    public static final double TURRET_PHYSICAL_LIMIT_DIFF = 2;

    // elevator
    public static final int ELEVATOR_MOTOR_COUNT = 1;
    public static final double ELEVATOR_DRUM_RADIUS_M = RobotMap.ELEVATOR_DRUM_RADIUS_M;
    public static final double ELEVATOR_MOTOR_TO_ELEVATOR_GEAR_RATIO = RobotMap.ELEVATOR_GEAR_RATIO;
    public static final double ELEVATOR_MIN_HEIGHT = RobotMap.ELEVATOR_MIN_HEIGHT;
    public static final double ELEVATOR_MAX_HEIGHT = RobotMap.ELEVATOR_MAX_HEIGHT;
    public static final double ELEVATOR_CARRIAGE_MASS = 3;
    public static final boolean ELEVATOR_SIMULATE_GRAVITY = true;
    public static final double ELEVATOR_STARTING_HEIGHT = ELEVATOR_MIN_HEIGHT;
    public static final double ELEVATOR_PHYSICAL_LIMIT_DIFF = 0.1;

    // arm
    public static final int ARM_MOTOR_COUNT = 1;
    public static final double ARM_MOTOR_TO_ARM_GEAR_RATIO = RobotMap.ARM_GEAR_RATIO;
    public static final double ARM_LENGTH = 1;
    public static final double ARM_MASS = 2;
    public static final double ARM_MIN_ANGLE = Math.toRadians(RobotMap.ARM_MIN_ANGLE);
    public static final double ARM_MAX_ANGLE = Math.toRadians(RobotMap.ARM_MAX_ANGLE);
    public static final double ARM_MOMENT_OF_INERTIA = (1 / 3.0) * ARM_MASS * ARM_LENGTH * ARM_LENGTH;
    public static final boolean ARM_SIMULATE_GRAVITY = true;
}
