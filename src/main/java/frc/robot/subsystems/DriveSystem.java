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

import java.beans.Encoder;

public class DriveSystem extends SubsystemBase {

    private final DriveSim sim;

    private CANSparkMax motorLeftFront;
    private CANSparkMax motorRightFront;
    private CANSparkMax motorLeftBack;
    private CANSparkMax motorRightBack;

    private WPI_Pigeon2 pigeon;

    private RelativeEncoder encoderLeft;
    private RelativeEncoder encoderRight;

    public DriveSystem() {
        motorLeftFront = new CANSparkMax(RobotMap.DRIVE_FRONT_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        motorLeftBack = new CANSparkMax(RobotMap.DRIVE_BACK_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        motorRightFront = new CANSparkMax(RobotMap.DRIVE_FRONT_RIGHT, CANSparkLowLevel.MotorType.kBrushless);
        motorRightBack = new CANSparkMax(RobotMap.DRIVE_BACK_RIGHT, CANSparkLowLevel.MotorType.kBrushless);

        motorRightFront.setInverted(true);
        motorRightBack.setInverted(true);

        encoderLeft = motorLeftFront.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);
        encoderRight = motorRightFront.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);

        pigeon = new WPI_Pigeon2(5);

        sim = new DriveSim(motorLeftFront, motorLeftBack, motorRightFront, motorRightBack, pigeon);
    }


    public void drive(double speedR, double speedL){
        motorLeftFront.set(speedL);
        motorRightFront.set(speedR);
        motorLeftBack.set(speedL);
        motorRightBack.set(speedR);
    }

    public void stop(){
        motorLeftFront.stopMotor();
        motorRightFront.stopMotor();
        motorLeftBack.stopMotor();
        motorRightBack.stopMotor();
    }

    public double getAngle(){
        return pigeon.getYaw() % 360;
    }

    public double getDistancePassed(){
        double leftDistance = encoderLeft.getPosition() / RobotMap.DRIVE_GEAR_RATIO * (RobotMap.DRIVE_WHEEL_RADIUS_M * 2 * Math.PI);
        double rightDistance = (-encoderRight.getPosition()) / RobotMap.DRIVE_GEAR_RATIO * (RobotMap.DRIVE_WHEEL_RADIUS_M * 2 * Math.PI);

        return (leftDistance + rightDistance) / 2;
    }

    @Override
    public void periodic() {
        sim.update();
        SmartDashboard.putNumber("passed distance: ", getDistancePassed());
        SmartDashboard.putNumber("current angle: ", getAngle());
    }
}
