package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {

    private final ArmSim sim;
    private final CANSparkMax motorBottom;
    private final CANSparkMax motorTop;

    private final RelativeEncoder bottomEncoder;
    private final RelativeEncoder topEncoder;

    public ArmSystem() {
        motorBottom = new CANSparkMax(RobotMap.ARM_MOTOR_BOTTOM, CANSparkLowLevel.MotorType.kBrushless);
        motorTop = new CANSparkMax(RobotMap.ARM_MOTOR_TOP, CANSparkLowLevel.MotorType.kBrushless);

        bottomEncoder = motorBottom.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);
        topEncoder = motorTop.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);


        motorBottom.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, 180);
        motorBottom.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, 0);
        motorBottom.enableSoftLimit(CANSparkBase.SoftLimitDirection.kForward, true);
        motorBottom.enableSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, true);

        motorTop.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, 90);
        motorTop.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, -90);
        motorTop.enableSoftLimit(CANSparkBase.SoftLimitDirection.kForward, true);
        motorTop.enableSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, true);


        sim = new ArmSim(motorBottom, motorTop);
    }

    public double getBottomAngle(){
        return bottomEncoder.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public double getTopAngle(){
        return topEncoder.getPosition() / RobotMap.ARM_GEAR_RATIO * 360;
    }

    public void bottomMotorMoveToAngle(double speed){
        motorBottom.set(speed);
    }

    public void topMotorMoveToAngle(double speed){
        motorTop.set(speed);
    }

    public void stopBottomMotor(){
        motorBottom.stopMotor();
    }

    public void stopTopMotor(){
        motorTop.stopMotor();
    }
//
//    public boolean bottomReachedTarget(double targetAngle){
//        return MathUtil.isNear(targetAngle, getBottomAngle(), 0.1) &&
//                Math.abs(bottomEncoder.getVelocity()) < 5;
//    }
//
//    public boolean topReachedTarget(double targetAngle){
//        return MathUtil.isNear(targetAngle, getTopAngle(), 0.1) &&
//                Math.abs(topEncoder.getVelocity()) < 5;
//    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("bottom arm angle: ", getBottomAngle());
        SmartDashboard.putNumber("top arm angle: ", getTopAngle());
        sim.update();
    }
}
