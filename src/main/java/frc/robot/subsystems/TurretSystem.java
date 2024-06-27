package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.TurretSim;

public class TurretSystem extends SubsystemBase {
    private final CANSparkMax turretMotor;
    private final RelativeEncoder turretMotorEncoder;

    private final DigitalInput leftLimitSwitch;
    private final DigitalInput rightLimitSwitch;
    private final DigitalInput centerLimitSwitch;

    private final TurretSim sim;

    public TurretSystem() {
        turretMotor = new CANSparkMax(RobotMap.TURRET_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        turretMotorEncoder = turretMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);

        leftLimitSwitch = new DigitalInput(RobotMap.TURRET_LEFT_SWITCH);
        rightLimitSwitch = new DigitalInput(RobotMap.TURRET_RIGHT_SWITCH);
        centerLimitSwitch = new DigitalInput(RobotMap.TURRET_CENTER_SWITCH);

        sim = new TurretSim(turretMotor);
    }

    public double getTurretAngle() {
        return turretMotorEncoder.getPosition() / RobotMap.TURRET_GEAR_RATIO * 360;
    }

    public boolean getLeftLimitSwitchValue() {
        return !leftLimitSwitch.get();
    }

    public boolean getRightLimitSwitchValue() {
        return !rightLimitSwitch.get();
    }

    public boolean getCenterLimitSwitchValue() {
        return !centerLimitSwitch.get();
    }

    public void moveTurret(double turretSpeed) {
        // Positive speed - up
        // Negative speed - down

        if ((getRightLimitSwitchValue() && turretSpeed > 0) || (getLeftLimitSwitchValue() && turretSpeed < 0))
            turretMotor.stopMotor();
        else
            turretMotor.set(turretSpeed);
    }

    public void stopTurret() {
        turretMotor.stopMotor();
    }

    public void resetTurretMotorEncoder() {
        turretMotorEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Angle", getTurretAngle());

        SmartDashboard.putBoolean("Left LS", getLeftLimitSwitchValue());
        SmartDashboard.putBoolean("Center LS", getCenterLimitSwitchValue());
        SmartDashboard.putBoolean("Right LS", getRightLimitSwitchValue());

        sim.update();
    }
}
