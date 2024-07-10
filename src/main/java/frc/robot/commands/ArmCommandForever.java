package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommandForever extends Command {

    private final ArmSubsystem armSubsystem;
    private double currentTargetAngle = -1;

    public double targetAngle = -1;

    public ArmCommandForever(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;

        addRequirements(this.armSubsystem);
    }

    @Override
    public void initialize() {
        currentTargetAngle = -1;
    }

    @Override
    public void execute() {
        if (currentTargetAngle != targetAngle) {
            currentTargetAngle = targetAngle;

            if (targetAngle >= 0) {
                this.armSubsystem.moveToSetPoint(currentTargetAngle);
            } else {
                this.armSubsystem.stop();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        armSubsystem.stop();
    }
}
