package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {
    private final CANSparkMax armTopMotor;
    private final CANSparkMax armBottomMotor;

    private final RelativeEncoder armTopEncoder;
    private final RelativeEncoder armBottomEncoder;

    private final ArmSim sim;

    public ArmSystem() {
        armTopMotor = new CANSparkMax(RobotMap.ARM_MOTOR_TOP, CANSparkLowLevel.MotorType.kBrushless);
        armBottomMotor = new CANSparkMax(RobotMap.ARM_MOTOR_BOTTOM, CANSparkLowLevel.MotorType.kBrushless);

        armTopEncoder = armTopMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);
        armBottomEncoder = armBottomMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);

        sim = new ArmSim(armBottomMotor, armTopMotor);
    }

    public double getTopMotorAngle() {
        return armTopEncoder.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public double getBottomMotorAngle() {
        return armBottomEncoder.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public void moveTopArm(double speed) {
        if ((getTopMotorAngle() <= RobotMap.ARM_MIN_ANGLE && speed > 0) || (getTopMotorAngle() >= RobotMap.ARM_MAX_ANGLE && speed < 0))
            stopTopMotor();
        else
            armTopMotor.set(speed);
    }

    public void moveBottomArm(double speed) {
        if ((getBottomMotorAngle() <= RobotMap.ARM_MIN_ANGLE && speed > 0) || (getBottomMotorAngle() >= RobotMap.ARM_MAX_ANGLE && speed < 0))
            stopBottomMotor();
        else {
            //System.out.println(speed);
            armBottomMotor.set(speed);
        }
    }

    public void stopBottomMotor() {
        armBottomMotor.stopMotor();
    }

    public void stopTopMotor() {
        armTopMotor.stopMotor();
    }

    public void stopArm() {
        stopBottomMotor();
        stopTopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Top Motor Angle:", getTopMotorAngle());
        SmartDashboard.putNumber("Bottom Motor Angle:", getBottomMotorAngle());

        sim.update();
    }
}