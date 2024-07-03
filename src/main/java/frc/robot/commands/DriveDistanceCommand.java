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
        double encoderAverage = (sub.getEncoderL() + sub.getEncoderR()) / 2;
        double output = (targetPosition - encoderAverage) / (2 * targetPosition);

        sub.move(output, output);
    }

    @Override
    public boolean isFinished() {
        double encoderAverage = (sub.getEncoderL() + sub.getEncoderR()) / 2;
        System.out.println(encoderAverage);
        return MathUtil.isNear(targetPosition, encoderAverage, 0.02);
    }

    @Override
    public void end(boolean interrupted) {
        sub.stop();
        System.out.println("stopped");
    }
}
