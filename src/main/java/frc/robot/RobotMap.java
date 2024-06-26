package frc.robot;

import edu.wpi.first.math.util.Units;

public class RobotMap {

    public static final int SRX_ENCODER_PPR = 4096;
    public static final int NEO_ENCODER_PPR = 42;

    public static final int DRIVE_FRONT_RIGHT = 1;
    public static final int DRIVE_BACK_RIGHT = 2;
    public static final int DRIVE_FRONT_LEFT = 3;
    public static final int DRIVE_BACK_LEFT = 4;
    public static final int DRIVE_PIGEON = 5;
    public static final double DRIVE_GEAR_RATIO = 5.71; // 5.71 : 1 (driver/driven)
    public static final double DRIVE_WHEEL_RADIUS_M = Units.inchesToMeters(3);

    public static final int TURRET_MOTOR = 7;
    public static final int TURRET_CENTER_SWITCH = 0;
    public static final int TURRET_RIGHT_SWITCH = 1;
    public static final int TURRET_LEFT_SWITCH = 2;
    public static final double TURRET_GEAR_RATIO = 32.0 / 5.0; // 32 : 5 (driver/driven)

    public static final int ELEVATOR_MOTOR = 8;
    public static final int ELEVATOR_TOP_SWITCH = 5;
    public static final int ELEVATOR_BOTTOM_SWITCH = 6;
    public static final double ELEVATOR_GEAR_RATIO = 32.0 / 2.0; // 32 : 3 (driver/driven)
    public static final double ELEVATOR_DRUM_RADIUS_M = 0.5;
    public static final double ELEVATOR_MIN_HEIGHT = 0;
    public static final double ELEVATOR_MAX_HEIGHT = 2;
    public static final double ELEVATOR_STATIC_POW = 0.35;
}
