package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DuoWheelDrivetrainSubsystem;


public class DriveAmountCommand extends Command {
    private final DuoWheelDrivetrainSubsystem duoWheelDrivetrainSubsystem;
    private double Distance;
    private double speed;
    private double previous;

    public DriveAmountCommand(DuoWheelDrivetrainSubsystem duoWheelDrivetrainSubsystem, double distance, double speed) {
        this.duoWheelDrivetrainSubsystem = duoWheelDrivetrainSubsystem;
        this.Distance = distance;
        addRequirements(this.duoWheelDrivetrainSubsystem);
        this.previous = (this.duoWheelDrivetrainSubsystem.getLeftMoved() + this.duoWheelDrivetrainSubsystem.getRightMoved()) / 2;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double sped = this.speed* ((this.duoWheelDrivetrainSubsystem.getLeftMoved() + this.duoWheelDrivetrainSubsystem.getRightMoved()) / 2);
        this.duoWheelDrivetrainSubsystem.Power(this.speed, this.speed);

    }

    @Override
    public boolean isFinished() {
        return (MathUtil.isNear(this.Distance, ((this.duoWheelDrivetrainSubsystem.getLeftMoved() + this.duoWheelDrivetrainSubsystem.getRightMoved()) / 2), 0.03));
    }

    @Override
    public void end(boolean interrupted) {

    }
}
