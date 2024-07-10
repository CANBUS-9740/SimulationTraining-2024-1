package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {
    private final ArmSim sim;
    private final CANSparkMax motorBottom;
    private final CANSparkMax motorTop;
    private final RelativeEncoder encoderBottom;
    private final RelativeEncoder encoderTop;

    public ArmSystem() {
        motorBottom = new CANSparkMax(RobotMap.ARM_MOTOR_BOTTOM, CANSparkLowLevel.MotorType.kBrushless);
        motorTop = new CANSparkMax(RobotMap.ARM_MOTOR_TOP, CANSparkLowLevel.MotorType.kBrushless);
        encoderBottom = motorBottom.getAlternateEncoder(RobotMap.NEO_ENCODER_PPR);
        encoderTop = motorTop.getAlternateEncoder(RobotMap.NEO_ENCODER_PPR);

        sim = new ArmSim(motorBottom, motorTop);
    }

    public double getEncoderTop() {
        return encoderTop.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public double getEncoderBottom() {
        return encoderBottom.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public void moveToTarget(double bottomSpeed, double topSpeed) {
        if ((topSpeed < 0 && getEncoderTop() <= RobotMap.ARM_TOP_MIN_ANGLE) || (topSpeed > 0 && getEncoderTop() >= RobotMap.ARM_TOP_MAX_ANGLE)) {
            motorTop.stopMotor();
        } else {
            motorTop.set(topSpeed + 0.3);
        }

        if ((bottomSpeed < 0 && getEncoderBottom() <= RobotMap.ARM_BOTTOM_MIN_ANGLE) || (bottomSpeed > 0 && getEncoderBottom() >= RobotMap.ARM_BOTTOM_MAX_ANGLE)) {
            motorBottom.stopMotor();
        } else {
            motorBottom.set(bottomSpeed + 0.3);
        }
    }

    public void stop() {
        motorBottom.stopMotor();
        motorTop.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("encoderTop", getEncoderTop());
        SmartDashboard.putNumber("encoderBottom", getEncoderBottom());
        SmartDashboard.putNumber("motorTop", motorTop.get());
        SmartDashboard.putNumber("motorBottom", motorBottom.get());

        sim.update();
    }
}
