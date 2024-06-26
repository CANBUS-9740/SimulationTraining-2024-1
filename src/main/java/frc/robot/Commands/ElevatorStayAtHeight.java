package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class ElevatorStayAtHeight extends Command {
    private final ElevatorSystem elevatorSystem;
    private double target;

    public ElevatorStayAtHeight(ElevatorSystem elevatorSystem){
        this.elevatorSystem = elevatorSystem;

        addRequirements(elevatorSystem);
    }

    @Override
    public void initialize() {
        target = elevatorSystem.getCurrentHeight();
    }

    @Override
    public void execute() {
        elevatorSystem.move((target - elevatorSystem.getCurrentHeight()) * 0.01 );
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
