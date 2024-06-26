package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class MoveToHeight extends Command {
    private ElevatorSystem elevatorSystem;
    private double targetHeight;
    private double startHeight;


    public MoveToHeight(ElevatorSystem elevatorSystem, double targetHeight){
        this.elevatorSystem = elevatorSystem;
        this.targetHeight = targetHeight;

        addRequirements(elevatorSystem);
    }
    public void initialize() {
        startHeight = elevatorSystem.getHeight();
    }

    public void execute() {

        elevatorSystem.move(targetHeight - elevatorSystem.getHeight() / (targetHeight - startHeight));


    }

    public void end(boolean interrupted) {

    }

    public boolean isFinished() {
        return ( elevatorSystem.getHeight() > targetHeight - 2 && elevatorSystem.getHeight() < targetHeight - 2);
    }

}
