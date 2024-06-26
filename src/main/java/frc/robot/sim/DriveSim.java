package frc.robot.sim;

import com.ctre.phoenix.sensors.BasePigeonSimCollection;
import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.MatBuilder;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.SimSettings;

@SuppressWarnings("removal")
public class DriveSim {

    private final SparkMaxSim motorLeftFront;
    private final SparkMaxSim motorLeftBack;
    private final SparkMaxSim motorRightFront;
    private final SparkMaxSim motorRightBack;
    private final WPI_Pigeon2 pigeon;
    private final BasePigeonSimCollection pigeonSim;

    private final DifferentialDrivetrainSim sim;
    private final Field2d field;

    private final NetworkTableEntry entryYaw;

    public DriveSim(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack,
                    CANSparkMax motorRightFront, CANSparkMax motorRightBack,
                    WPI_Pigeon2 pigeon) {
        NetworkTable rootTable = NetworkTableInstance.getDefault().getTable("SimDebug").getSubTable("Drive");
        entryYaw = rootTable.getEntry("Yaw");

        MotorShaft motorShaft = new MotorShaft(RobotMap.SRX_ENCODER_PPR, SimSettings.DRIVE_MOTOR_TO_WHEEL_GEAR_RATIO, SimSettings.DRIVE_WHEEL_CIRCUMFERENCE_M);

        this.motorLeftFront = new SparkMaxSim(
                rootTable,
                "LeftFront",
                motorLeftFront,
                motorShaft,
                false,
                true);
        this.motorLeftBack = new SparkMaxSim(
                rootTable,
                "LeftBack",
                motorLeftBack,
                motorShaft,
                false);
        this.motorRightFront = new SparkMaxSim(
                rootTable,
                "RightFront",
                motorRightFront,
                motorShaft,
                true,
                true);
        this.motorRightBack = new SparkMaxSim(
                rootTable,
                "RightBack",
                motorRightBack,
                motorShaft,
                true);
        this.pigeon = pigeon;
        pigeonSim = pigeon.getSimCollection();

        sim = new DifferentialDrivetrainSim(
                DCMotor.getCIM(SimSettings.DRIVE_SIDE_MOTOR_COUNT),
                SimSettings.DRIVE_MOTOR_TO_WHEEL_GEAR_RATIO,
                SimSettings.DRIVE_MOMENT_OF_INERTIA,
                SimSettings.ROBOT_WEIGHT_KG,
                SimSettings.DRIVE_WHEEL_RADIUS_M,
                SimSettings.DRIVE_TRACK_WIDTH_M,
                // [x, y, heading, left velocity, right velocity, left distance, right distance]
                MatBuilder.fill(Nat.N7(), Nat.N1(), 0, 0, 0.0001, 0.05, 0.05, 0.005, 0.005)
        );

        field = new Field2d();
        SmartDashboard.putData("Drive-Field2d", field);
    }

    public void update() {
        double leftOutput = motorLeftFront.updateOutput() + motorLeftBack.updateOutput();
        double rightOutput = motorRightFront.updateOutput() + motorRightBack.updateOutput();

        sim.setInputs(leftOutput, rightOutput);

        sim.update(0.02);

        double leftVelocity = sim.getLeftVelocityMetersPerSecond();
        double leftPosition = sim.getLeftPositionMeters();
        double rightVelocity = sim.getRightVelocityMetersPerSecond();
        double rightPosition = sim.getRightPositionMeters();

        motorLeftFront.updateOdometry(leftPosition, leftVelocity);
        motorLeftBack.updateOdometry(leftPosition, leftVelocity);
        motorRightFront.updateOdometry(rightPosition, rightVelocity);
        motorRightBack.updateOdometry(rightPosition, rightVelocity);

        double yaw = sim.getHeading().getDegrees();
        pigeonSim.setRawHeading(yaw);
        entryYaw.setDouble(yaw);

        field.setRobotPose(sim.getPose());
    }
}