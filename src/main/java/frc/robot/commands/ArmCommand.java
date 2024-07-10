package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;


public class ArmCommand extends Command {
    private final ArmSubsystem armSubsystem;
    private final double angleDegrees;

    public ArmCommand(ArmSubsystem armSubsystem, double angleDegrees) {
        this.armSubsystem = armSubsystem;
        this.angleDegrees = angleDegrees;

        addRequirements(this.armSubsystem);
    }

    @Override
    public void initialize() {
        this.armSubsystem.moveToSetPoint(angleDegrees);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return this.armSubsystem.isFinished(angleDegrees);
    }

    @Override
    public void end(boolean interrupted) {
        this.armSubsystem.stop();
    }

}
