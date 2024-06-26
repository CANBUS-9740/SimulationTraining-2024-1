package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class StayAtHeight extends Command {
    private ElevatorSystem elevatorSystem;
    private double targetHeight;

    public StayAtHeight(ElevatorSystem elevatorSystem){
        this.elevatorSystem = elevatorSystem;


        addRequirements(elevatorSystem);
    }

    public void initialize() {
        targetHeight = elevatorSystem.getHeight();
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
