package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSystem;

public class ElevatorMoveToTarget extends Command {

    private double target;
    public double start;

    private ElevatorSystem elevatorSystem;


    public ElevatorMoveToTarget(ElevatorSystem elevatorSystem, double target){
        this.elevatorSystem = elevatorSystem;
        this.target = target;

        addRequirements(elevatorSystem);
    }

    @Override
    public void initialize() {
        start = elevatorSystem.getCurrentHeight();
    }

    @Override
    public void execute() {
        elevatorSystem.move(target - elevatorSystem.getCurrentHeight() / (target - start));
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSystem.stop();
    }

    @Override
    public boolean isFinished() {
        return elevatorSystem.getCurrentHeight() <  target - 0.01 || elevatorSystem.getCurrentHeight() > target + 0.01;
    }
}
