package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class MoveElevatorToHeight extends Command {
    private double targetHeight;
    private ElevatorSystem elevatorSystem;
    private double startHeight;

    public MoveElevatorToHeight(double targetHeight, ElevatorSystem elevatorSystem) {
        this.targetHeight = targetHeight;
        this.elevatorSystem = elevatorSystem;

        addRequirements(elevatorSystem);
    }

    public void initialize() {
        startHeight = elevatorSystem.getHeight();
    }

    public void execute() {
        elevatorSystem.move((targetHeight - elevatorSystem.getHeight()) / (targetHeight - startHeight));
    }

    public void end(boolean interrupted) {

    }

    public boolean isFinished() {
        return (elevatorSystem.getHeight() > targetHeight - 0.02) && (elevatorSystem.getHeight() < targetHeight + 0.02);
    }

}
