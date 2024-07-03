package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.DriveSim;

public class DriveSystem extends SubsystemBase {
    private CANSparkMax sparkLF;
    private CANSparkMax sparkLB;
    private CANSparkMax sparkRF;
    private CANSparkMax sparkRB;
    private WPI_Pigeon2 pigeon;

    private RelativeEncoder encoderL;
    private RelativeEncoder encoderR;

    private final DriveSim sim;

    public DriveSystem() {
        sparkLB = new CANSparkMax(RobotMap.DRIVE_BACK_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        sparkLF = new CANSparkMax(RobotMap.DRIVE_FRONT_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        sparkRB = new CANSparkMax(RobotMap.DRIVE_BACK_RIGHT, CANSparkLowLevel.MotorType.kBrushless);
        sparkRF = new CANSparkMax(RobotMap.DRIVE_FRONT_RIGHT, CANSparkLowLevel.MotorType.kBrushless);

        sparkRF.setInverted(true);
        sparkRB.setInverted(true);
        sparkLF.setInverted(false);
        sparkLB.setInverted(false);

        pigeon = new WPI_Pigeon2(RobotMap.DRIVE_PIGEON);

        sparkLB.follow(sparkLF);
        sparkRB.follow(sparkRF);

        encoderL = sparkLF.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);
        encoderR = sparkRF.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);

        sim = new DriveSim(sparkLF, sparkLB, sparkRF, sparkRB, pigeon);
    }

    public void move(double speedL, double speedR) {
        sparkLF.set(speedL);
        sparkRF.set(speedR);
    }

    public void stop() {
        sparkLF.stopMotor();
        sparkRF.stopMotor();
    }

    public double getEncoderL() {
        return (encoderL.getPosition() / RobotMap.DRIVE_GEAR_RATIO) * (RobotMap.DRIVE_WHEEL_RADIUS_M * 2 * Math.PI);
    }

    public double getEncoderR() {
        return (-encoderR.getPosition() / RobotMap.DRIVE_GEAR_RATIO) * (RobotMap.DRIVE_WHEEL_RADIUS_M * 2 * Math.PI);
    }

    public double getYaw() {
        if (pigeon.getYaw() < 0) {
            return (pigeon.getYaw() % 360) + 360;
        } else {
            return pigeon.getYaw() % 360;
        }
    }

    @Override
    public void periodic() {
        double encoderAverage = (getEncoderL() + getEncoderR()) / 2;
        SmartDashboard.putNumber("encoderAverage", encoderAverage);
        SmartDashboard.putNumber("Yaw", getYaw());
        SmartDashboard.putNumber("outputLeft", sparkLF.get());
        SmartDashboard.putNumber("outputRight", sparkRF.get());

        sim.update();
    }
}
