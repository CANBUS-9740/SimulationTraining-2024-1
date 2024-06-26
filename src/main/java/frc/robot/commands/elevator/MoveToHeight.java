package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorSystem;

public class MoveToHeight extends Command {
    private final ElevatorSystem elevatorSystem;

    private final double targetHeight;
    private double startHeight;
    private double currentHeight;

    public MoveToHeight(ElevatorSystem elevatorSystem, double targetHeight) {
        this.elevatorSystem = elevatorSystem;
        this.targetHeight = targetHeight;

        addRequirements(elevatorSystem);
    }

    @Override
    public void initialize() {
        currentHeight = elevatorSystem.getElevatorHeight();
        startHeight = elevatorSystem.getElevatorHeight();
    }

    @Override
    public void execute() {
        currentHeight = elevatorSystem.getElevatorHeight();
        double motorPower = (targetHeight - currentHeight) / (RobotMap.ELEVATOR_MAX_HEIGHT - RobotMap.ELEVATOR_MIN_HEIGHT);

        elevatorSystem.moveElevator(motorPower + RobotMap.ELEVATOR_FEEDFORWARD_POW);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSystem.moveElevator(0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(targetHeight - currentHeight) < 0.01 ||
                (elevatorSystem.getBottomLimitSwitchValue() && (startHeight > targetHeight)) ||
                (elevatorSystem.getTopLimitSwitchValue() && (startHeight < targetHeight));
    }
}
