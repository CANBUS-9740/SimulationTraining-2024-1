package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;


public class ShooterCommand extends Command {
    private final ShooterSubsystem shooterSubsystem;
    private double speed;

    public ShooterCommand(ShooterSubsystem shooterSubsystem, double speedy) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(this.shooterSubsystem);
        this.speed = speedy;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.shooterSubsystem.powerMotor(this.speed);
    }

    @Override
    public boolean isFinished() {
return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
