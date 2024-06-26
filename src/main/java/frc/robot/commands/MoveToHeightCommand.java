package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ElevatorSystem;

public class MoveToHeightCommand extends Command {
    private ElevatorSystem sub;
    private double targetHeight;

    public MoveToHeightCommand(ElevatorSystem sub, double targetHeight) {
        this.sub = sub;
        this.targetHeight = targetHeight;

        addRequirements(sub);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double speed = ((targetHeight - sub.getHeight()) / targetHeight) * 0.5;
        sub.move(speed + RobotMap.ELEVATOR_STATIC_POW);
    }

    @Override
    public boolean isFinished() {
        return sub.getHeight() > targetHeight - 0.05 && sub.getHeight() < targetHeight + 0.05;
    }

    @Override
    public void end(boolean interrupted) {
        sub.stop();
    }
}
