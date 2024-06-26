package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class StayAtHeight extends Command {
    private ElevatorSystem elevatorSystem;
    private double startHeight;
    private double targetHeight;

    public StayAtHeight(ElevatorSystem elevatorSystem, double startHeight, double targetHeight){
        this.elevatorSystem = elevatorSystem;
        this.startHeight = startHeight;
        this.targetHeight = targetHeight;

        addRequirements(elevatorSystem);
    }

    public void initialize() {
        startHeight = elevatorSystem.getHeight();
    }

    public void execute() {
        elevatorSystem.move(targetHeight - elevatorSystem.getHeight() * 0.1);

    }

    public void end(boolean interrupted) {
    }

    public boolean isFinished() {
        return false;
    }
}
