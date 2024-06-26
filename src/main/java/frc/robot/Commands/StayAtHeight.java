package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class StayAtHeight extends Command {
    private ElevatorSystem elevatorSystem;
    private double startHeight;

    public StayAtHeight(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;

        addRequirements(elevatorSystem);
    }

    public void initialize() {
        startHeight = elevatorSystem.getHeight();
    }

    public void execute() {
        elevatorSystem.move(startHeight - elevatorSystem.getHeight() * 0.01);
    }

    public void end(boolean interrupted) {
    }

    public boolean isFinished() {
        return false;
    }

}
