package frc.robot.subsystems;


import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.DriveSim;

public class DuoWheelDrivetrainSubsystem extends SubsystemBase {
    private CANSparkMax RightTopMotor;
    private CANSparkMax LeftTopMotor;
    private CANSparkMax RightBottomMotor;
    private CANSparkMax LeftBottomMotor;
    private CANSparkMax RightMotor;
    private CANSparkMax LeftMotor;

    private RelativeEncoder RightEncoder;
    private RelativeEncoder LeftEncoder;

    private WPI_Pigeon2 gyro;

    private final DriveSim sim;

    public DuoWheelDrivetrainSubsystem() {
        RightTopMotor = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
        RightBottomMotor = new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);
        LeftTopMotor = new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
        LeftBottomMotor = new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);
        LeftMotor = LeftBottomMotor;
        RightMotor = RightBottomMotor;
        LeftTopMotor.follow(LeftMotor);
        RightTopMotor.follow(RightMotor);



        RightEncoder = RightMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);
        LeftEncoder = LeftMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);

        RightMotor.setInverted(true);
        RightEncoder.setInverted(true);
        sim = new DriveSim(LeftTopMotor, LeftBottomMotor, RightTopMotor, RightBottomMotor, gyro);

    }
    public void Power(double leftPow, double rightPow){
        SmartDashboard.setDefaultNumber("rightPow: ", rightPow);
        SmartDashboard.setDefaultNumber("LeftPow: ", leftPow);
        RightMotor.set(rightPow);
        LeftMotor.set(leftPow);
    }
    public void Stop(){
        RightMotor.stopMotor();
        LeftMotor.stopMotor();
    }

    public double getRightMoved(){
        double Circumference = Math.PI * 2* RobotMap.DRIVE_WHEEL_RADIUS_M;
        SmartDashboard.setDefaultNumber("rightDistance: ", RightEncoder.getPosition() / RobotMap.DRIVE_GEAR_RATIO * Circumference);
        return RightEncoder.getPosition() / RobotMap.DRIVE_GEAR_RATIO * Circumference;
    }
    public double getLeftMoved(){
        double Circumference = Math.PI * 2* RobotMap.DRIVE_WHEEL_RADIUS_M;
        SmartDashboard.setDefaultNumber("leftDistance: ", LeftEncoder.getPosition() / RobotMap.DRIVE_GEAR_RATIO * Circumference);
        return LeftEncoder.getPosition() / RobotMap.DRIVE_GEAR_RATIO * Circumference;
    }

    public double getSpin(){
        SmartDashboard.setDefaultNumber("dgree: ", gyro.getYaw());
        return gyro.getYaw();
    }
    public void periodic() {
        sim.update();
    }
}

