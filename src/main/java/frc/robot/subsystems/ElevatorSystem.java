package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ElevatorSim;

public class ElevatorSystem extends SubsystemBase {
    private final CANSparkMax elevatorMotor;
    private final RelativeEncoder elevatorMotorEncoder;

    private final DigitalInput topLimitSwitch; // Normally Open
    private final DigitalInput bottomLimitSwitch; // Normally Open

    private final ElevatorSim sim;

    public ElevatorSystem() {
        elevatorMotor = new CANSparkMax(RobotMap.ELEVATOR_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        elevatorMotorEncoder = elevatorMotor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);

        topLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_TOP_SWITCH);
        bottomLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_SWITCH);

        sim = new ElevatorSim(elevatorMotor);
    }

    public boolean getTopLimitSwitchValue() {
        return !topLimitSwitch.get();
    }

    public boolean getBottomLimitSwitchValue() {
        return !bottomLimitSwitch.get();
    }

    public double getElevatorHeight() {
        return elevatorMotorEncoder.getPosition() / RobotMap.ELEVATOR_GEAR_RATIO * (2 * RobotMap.ELEVATOR_DRUM_RADIUS_M * Math.PI);
    }

    public void moveElevator(double elevatorSpeed) {
        // Positive speed - up
        // Negative speed - down

        if ((getBottomLimitSwitchValue() && elevatorSpeed < 0) || (getTopLimitSwitchValue() && elevatorSpeed > 0))
            elevatorMotor.stopMotor();
        else
            elevatorMotor.set(elevatorSpeed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Bottom LS:", getBottomLimitSwitchValue());
        SmartDashboard.putBoolean("Top LS:", getTopLimitSwitchValue());
        SmartDashboard.putNumber("Elevator Height:", getElevatorHeight());

        sim.update();
    }
}
