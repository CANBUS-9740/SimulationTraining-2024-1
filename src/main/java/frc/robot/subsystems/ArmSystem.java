package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {

    private final CANSparkMax motorBottom;
    private final CANSparkMax motorTop;

    // uncomment this
    private final ArmSim sim;

    public ArmSystem() {
        motorBottom = new CANSparkMax(RobotMap.ARM_MOTOR_BOTTOM, CANSparkLowLevel.MotorType.kBrushless);
        motorTop = new CANSparkMax(RobotMap.ARM_MOTOR_TOP, CANSparkLowLevel.MotorType.kBrushless);

        // initialize system components and uncomment this, passing these components to the sim
        sim = new ArmSim(motorBottom, motorTop);
    }

    public double getAngleBottom() {
        return motorBottom.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR).getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public double getAngleTop() {
        return motorTop.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR).getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public void move(double speedBottom, double speedTop) {
        motorBottom.set(speedBottom);
        motorTop.set(speedTop);
    }

    public void stop() {
        motorBottom.stopMotor();
        motorTop.stopMotor();
    }

    @Override
    public void periodic() {
        // uncomment this
        sim.update();
    }
}
