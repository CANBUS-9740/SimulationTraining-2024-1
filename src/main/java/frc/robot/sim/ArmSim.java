package frc.robot.sim;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.RobotMap;
import frc.robot.SimSettings;

public class ArmSim {

    private static final double MECHANISM_BASE_POS = 10;
    private static final double MECHANISM_ARM_LENGTH = 5;

    private final SparkMaxSim motorBottom;
    private final SparkMaxSim motorTop;

    private final SingleJointedArmSim simBottom;
    private final SingleJointedArmSim simTop;

    private final Mechanism2d mechanism;
    private final MechanismLigament2d mechanismLigamentBottom;
    private final MechanismRoot2d mechanismRootTop;
    private final MechanismLigament2d mechanismLigamentTop;

    public ArmSim(CANSparkMax motorBottom, CANSparkMax motorTop) {
        NetworkTable rootTable = NetworkTableInstance.getDefault().getTable("SimDebug").getSubTable("Arm");
        MotorShaft motorShaft = new MotorShaft(RobotMap.NEO_ENCODER_PPR, SimSettings.ARM_MOTOR_TO_ARM_GEAR_RATIO, 360);

        this.motorBottom = new SparkMaxSim(
                rootTable,
                "MotorBottom",
                motorBottom,
                motorShaft,
                false,
                true);
        this.motorTop = new SparkMaxSim(
                rootTable,
                "MotorTop",
                motorTop,
                motorShaft,
                false,
                true);

        simBottom = new SingleJointedArmSim(
                DCMotor.getNEO(SimSettings.ARM_MOTOR_COUNT),
                SimSettings.ARM_MOTOR_TO_ARM_GEAR_RATIO,
                SimSettings.ARM_MOMENT_OF_INERTIA,
                SimSettings.ARM_LENGTH,
                SimSettings.ARM_MIN_ANGLE,
                SimSettings.ARM_MAX_ANGLE,
                SimSettings.ARM_SIMULATE_GRAVITY,
                0,
                VecBuilder.fill(0.001)
        );

        simTop = new SingleJointedArmSim(
                DCMotor.getNEO(SimSettings.ARM_MOTOR_COUNT),
                SimSettings.ARM_MOTOR_TO_ARM_GEAR_RATIO,
                SimSettings.ARM_MOMENT_OF_INERTIA,
                SimSettings.ARM_LENGTH,
                SimSettings.ARM_MIN_ANGLE,
                SimSettings.ARM_MAX_ANGLE,
                SimSettings.ARM_SIMULATE_GRAVITY,
                0,
                VecBuilder.fill(0.001)
        );

        mechanism = new Mechanism2d(20, 20);
        MechanismRoot2d mechanismRoot = mechanism.getRoot("ArmBottom", MECHANISM_BASE_POS, 0);
        mechanismLigamentBottom = mechanismRoot.append(new MechanismLigament2d("ArmBottom", MECHANISM_ARM_LENGTH, 0, 10, new Color8Bit(44, 78, 235)));
        mechanismRootTop = mechanism.getRoot("ArmTop", MECHANISM_BASE_POS, 0);
        mechanismLigamentTop = mechanismRootTop.append(new MechanismLigament2d("ArmTop", MECHANISM_ARM_LENGTH, 0, 10, new Color8Bit(235, 137, 52)));
        SmartDashboard.putData("Arm-Mechanism", mechanism);
    }

    public void update() {
        updateSims();

        double positionBottom = simBottom.getAngleRads();
        double positionTop = simTop.getAngleRads();

        mechanismLigamentBottom.setAngle(Math.toDegrees(positionBottom));

        double x = MECHANISM_ARM_LENGTH * Math.cos(positionBottom);
        double y = MECHANISM_ARM_LENGTH * Math.sin(positionBottom);
        mechanismRootTop.setPosition(MECHANISM_BASE_POS + x, y);
        mechanismLigamentTop.setAngle(Math.toDegrees(positionTop));
    }

    private void updateSims() {
        double motorOutput = motorBottom.updateOutput();
        simBottom.setInput(motorOutput);

        simBottom.update(0.02);

        double position = Math.toDegrees(simBottom.getAngleRads());
        motorBottom.updateOdometry(position, Math.toDegrees(simBottom.getVelocityRadPerSec()));

        motorOutput = motorTop.updateOutput();
        simTop.setInput(motorOutput);

        simTop.update(0.02);

        position = Math.toDegrees(simTop.getAngleRads());
        motorTop.updateOdometry(position, Math.toDegrees(simTop.getVelocityRadPerSec()));
    }
}