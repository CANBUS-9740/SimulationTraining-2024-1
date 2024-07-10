package frc.robot.subsystems;


import com.revrobotics.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ArmSubsystem extends SubsystemBase {

    private static final double ABS_OFFSET = 1 - 0.54742;

    private final CANSparkMax motor;
    private final SparkPIDController pid;
    private final RelativeEncoder encoder;
    private final DutyCycleEncoder absEncoder;


    public ArmSubsystem() {
        motor = new CANSparkMax(RobotMap.ARM_ID, CANSparkLowLevel.MotorType.kBrushless);
        pid = motor.getPIDController();
        encoder = motor.getEncoder();
        absEncoder = new DutyCycleEncoder(RobotMap.ABS_ARM_ENCODER_CHANNEL);

        motor.restoreFactoryDefaults();
        motor.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, 49);
        motor.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, 0);
        motor.enableSoftLimit(CANSparkBase.SoftLimitDirection.kForward, true);
        motor.enableSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, true);

        motor.setIdleMode(CANSparkBase.IdleMode.kCoast);

        double absPos = 1 - absEncoder.getAbsolutePosition() - ABS_OFFSET;
        encoder.setPosition(absPos * RobotMap.ARM_GEAR_RATIO);

        pid.setP(0.07);
        pid.setI(0.0001);
        pid.setD(0);
        pid.setFF(0);
        pid.setIZone(5);

    }

    public double getAngleDegrees(){
        return encoder.getPosition() / RobotMap.ARM_GEAR_RATIO*360;
    }

    public double getVelocityRpm(){
        return encoder.getVelocity();
    }

    public void moveToSetPoint(double angle){
        pid.setReference(angle/360*RobotMap.ARM_GEAR_RATIO, CANSparkBase.ControlType.kPosition);
    }

    public void stop(){this.motor.stopMotor();}

    public boolean isFinished(double angleDegrees){
        return MathUtil.isNear(angleDegrees, getAngleDegrees(), 0.5) && Math.abs(getVelocityRpm()) < 5;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Absolute Encoder ", this.absEncoder.getAbsolutePosition());
        SmartDashboard.putNumber("Absolute Encoder deg", this.absEncoder.getAbsolutePosition() * 360);
        SmartDashboard.putNumber("Absolute Encoder fixed", (1 - this.absEncoder.getAbsolutePosition() - ABS_OFFSET));
        SmartDashboard.putNumber("Absolute Encoder deg fixed", (1 - this.absEncoder.getAbsolutePosition() - ABS_OFFSET) * 360);
        SmartDashboard.putNumber("Relative Encoder", getAngleDegrees());
        SmartDashboard.putNumber("Relative Encoder raw", encoder.getPosition());
    }
}

































