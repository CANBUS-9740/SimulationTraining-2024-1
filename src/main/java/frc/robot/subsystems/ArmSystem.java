package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {

    // uncomment this
    private final ArmSim sim;
    private CANSparkMax motorBottom;
    private CANSparkMax motorTop;
    private RelativeEncoder topEncoder;
    private RelativeEncoder bottomEncoder;
    private PIDController pidControllerBottom;
    private PIDController pidControllerTop;
    private double targetAngleTop;
    private double targetAngleBottom;

    public ArmSystem(double targetAngleTop, double targetAngleBottom) {
        // initialize system components and uncomment this, passing these components to the sim
        motorBottom = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
        motorTop = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);

        topEncoder = motorTop.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);
        bottomEncoder = motorBottom.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);

        pidControllerTop = new PIDController(0,0,0);
        pidControllerBottom = new PIDController(0,0,0);

        this.targetAngleTop = targetAngleTop;
        this.targetAngleBottom = targetAngleBottom;

        motorTop.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 90);
        motorTop.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -90);

        motorTop.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motorTop.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        motorBottom.setSoftLimit(CANSparkBase.SoftLimitDirection.kForward, 180);
        motorBottom.setSoftLimit(CANSparkBase.SoftLimitDirection.kReverse, 0);

        motorBottom.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        motorBottom.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        sim = new ArmSim(motorBottom, motorTop,topEncoder, bottomEncoder, pidControllerTop, pidControllerBottom);
    }

    public double getBottomEncoder() {
        return getBottomEncoder() ;
    }

    public double getTopEncoder(){
        return getTopEncoder();
    }

    public double getPositionTop(){
        return getTopEncoder() / RobotMap.ARM_GEAR_RATIO * 360;
    }
    public double getPositionBottom(){
        return getBottomEncoder() / RobotMap.ARM_GEAR_RATIO * 360;
    }
    public double getVelocityTop(){
        return getVelocityTop();
    }
    public double getVelocityBottom(){
        return getVelocityBottom();
    }

    public void setMotorTop(double speedTop){
        motorTop.set(speedTop);
    }

    public void setMotorBottom(double speedBottom){
        motorBottom.set(speedBottom);
    }

    public void stopTop(){
        motorTop.stopMotor();
    }

    public void stopBottom(){
        motorBottom.stopMotor();
    }

    public boolean reachAngleBottom(){
        return MathUtil.isNear(targetAngleBottom, getBottomEncoder(), 0.1) &&
                Math.abs(getVelocityBottom() ) < 0.5;
    }
    public boolean reachAngleTop(){
        return MathUtil.isNear(targetAngleTop, getTopEncoder(), 0.1) &&
                Math.abs(getVelocityTop() ) < 0.5;
    }

    @Override
    public void periodic() {
        // uncomment this
        sim.update();
        SmartDashboard.putNumber("bottom angle: ",getPositionBottom());
        SmartDashboard.putNumber("top angle: ", getPositionTop());
    }
}
