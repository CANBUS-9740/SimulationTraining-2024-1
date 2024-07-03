package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class DriveDistanceCommand extends Command {
    private DriveSystem sub;
    private double targetPosition;

    public DriveDistanceCommand(DriveSystem sub, double targetPosition) {
        this.sub = sub;
        this.targetPosition = targetPosition;

        addRequirements(sub);
    }

    @Override
    public void initialize() {
        targetPosition = targetPosition + sub.getEncoderL();
    }

    @Override
    public void execute() {
        double outputL = (targetPosition - sub.getEncoderL()) / targetPosition;
        double outputR = (targetPosition - sub.getEncoderR()) / targetPosition;

        sub.move(outputL, outputR);
    }

    @Override
    public boolean isFinished() {
        double encoderAverage = (sub.getEncoderL() + sub.getEncoderR()) / 2;
        return MathUtil.isNear(targetPosition, encoderAverage, 0.1);
    }

    @Override
    public void end(boolean interrupted) {
        sub.stop();
    }
}