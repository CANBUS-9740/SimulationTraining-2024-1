package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorSystem;

public class StayAtHeight extends Command {
    private final ElevatorSystem elevatorSystem;

    private double targetHeight;
    private double currentHeight;

    public StayAtHeight(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;

        addRequirements(elevatorSystem);
    }

    @Override
    public void initialize() {
        currentHeight = elevatorSystem.getElevatorHeight();
        targetHeight = elevatorSystem.getElevatorHeight();

        System.out.println(targetHeight);
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
        return false;
    }
}