package frc.robot.subsystems;


import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * subsystem that shoots
 */
public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private CANSparkMax motor3;
    private CANSparkMax motor4;
    private RelativeEncoder encoder;
    private SparkPIDController pidController;

    public ShooterSubsystem() {
        motor1 = new CANSparkMax(12, CANSparkLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(13, CANSparkLowLevel.MotorType.kBrushless);
        motor3 = new CANSparkMax(14, CANSparkLowLevel.MotorType.kBrushless);
        motor4 = new CANSparkMax(15, CANSparkLowLevel.MotorType.kBrushless);
        motor1.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();
        motor3.restoreFactoryDefaults();
        motor4.restoreFactoryDefaults();
        motor2.follow(motor1);
        motor3.follow(motor1, false);
        motor4.follow(motor1);
        encoder = motor1.getEncoder();
        pidController = motor1.getPIDController();
        pidController.setD(0);
        pidController.setFF(0);
        pidController.setP(0);
        pidController.setI(0);
    }
    public void powerMotor(double velocity){
        pidController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    }
    public void stop(){
        motor1.stopMotor();
    }
}


