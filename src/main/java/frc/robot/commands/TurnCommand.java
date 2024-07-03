package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DuoWheelDrivetrainSubsystem;
import org.opencv.core.Mat;


public class TurnCommand extends Command {
    private final DuoWheelDrivetrainSubsystem duoWheelDrivetrainSubsystem;
    private double prevDegree;
    private double degree;
    public TurnCommand(DuoWheelDrivetrainSubsystem duoWheelDrivetrainSubsystem, double turnDegree) {
        this.duoWheelDrivetrainSubsystem = duoWheelDrivetrainSubsystem;
        addRequirements(this.duoWheelDrivetrainSubsystem);
        this.prevDegree = this.duoWheelDrivetrainSubsystem.getSpin();
        this.degree = turnDegree;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double speed = Math.max(Math.toRadians(duoWheelDrivetrainSubsystem.getSpin()), 0.05);
        duoWheelDrivetrainSubsystem.Power(-speed, speed);

    }

    @Override
    public boolean isFinished() {
        return MathUtil.isNear(this.degree, this.duoWheelDrivetrainSubsystem.getSpin(), 0.1);
    }

    @Override
    public void end(boolean interrupted) {

    }
}
