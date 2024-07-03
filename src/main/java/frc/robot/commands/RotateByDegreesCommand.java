package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class RotateByDegreesCommand extends Command {
    private DriveSystem sub;
    private double targetAngle;

    public RotateByDegreesCommand(DriveSystem sub, double targetAngle) {
        this.sub = sub;
        this.targetAngle = targetAngle;

        addRequirements(sub);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double output = (targetAngle - sub.getYaw()) * 0.1;

        if (targetAngle - sub.getYaw() < 180) {
            sub.move(-output, output);
        } else {
            sub.move(output, -output);
        }
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isNear(targetAngle, sub.getYaw(), 0.5);
    }

    @Override
    public void end(boolean interrupted) {
        sub.stop();
    }
}
