package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorSystem;

public class StayAtHeightCommand extends Command {
    private ElevatorSystem sub;
    private double targetHeight;

    public StayAtHeightCommand(ElevatorSystem sub) {
        this.sub = sub;

        addRequirements(sub);
    }

    @Override
    public void initialize() {
        targetHeight = sub.getHeight();
    }

    @Override
    public void execute() {
        double speed = ((targetHeight - sub.getHeight()) / (RobotMap.ELEVATOR_MAX_HEIGHT - RobotMap.ELEVATOR_MIN_HEIGHT));
        sub.move(speed + RobotMap.ELEVATOR_STATIC_POW);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
