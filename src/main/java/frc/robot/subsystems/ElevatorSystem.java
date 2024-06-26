package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ElevatorSim;

public class ElevatorSystem extends SubsystemBase {

    private final ElevatorSim sim;
    private CANSparkMax sparkMax;
    private RelativeEncoder encoder;
    private DigitalInput switchMax;
    private DigitalInput switchMin;

    public ElevatorSystem() {


        sparkMax = new CANSparkMax(RobotMap.ELEVATOR_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        encoder = sparkMax.getAlternateEncoder(RobotMap.NEO_ENCODER_PPR);
        switchMax = new DigitalInput(RobotMap.ELEVATOR_TOP_SWITCH);
        switchMin = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_SWITCH);

        sim = new ElevatorSim(sparkMax);
    }

    public void move(double speed) {
        if ((!switchMax.get() && speed > 0) || (!switchMin.get() && speed < 0)) {
            stop();
        } else {
            sparkMax.set(speed);
        }
    }

    public void stop() {
        sparkMax.stopMotor();
    }

    public boolean getTopSwitch() {
        return !switchMax.get();
    }

    public boolean getBottomSwitch() {
        return !switchMin.get();
    }

    public double getHeight() {
        return (Math.PI * 2 * RobotMap.ELEVATOR_DRUM_RADIUS_M) * encoder.getPosition() / RobotMap.ELEVATOR_GEAR_RATIO;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("getHeight", getHeight());
        SmartDashboard.putBoolean("getTopSwitch", getTopSwitch());
        SmartDashboard.putBoolean("getBottomSwitch", getBottomSwitch());

        sim.update();
    }
}
