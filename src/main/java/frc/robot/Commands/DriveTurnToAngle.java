package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class DriveTurnToAngle extends Command {
    private DriveSystem driveSystem;
    private double targetAngle;
    private double startAngle;

    public DriveTurnToAngle(DriveSystem driveSystem, double targetAngle){
        this.driveSystem = driveSystem;
        this.targetAngle = targetAngle;

        addRequirements(driveSystem);
    }

    @Override
    public void initialize() {
        startAngle = driveSystem.getAngle();
    }

    @Override
    public void execute() {
        double speed = (targetAngle - driveSystem.getAngle()) / (targetAngle - startAngle);
        if (targetAngle > driveSystem.getAngle()){
            driveSystem.drive(-speed, speed);
        } else {
            driveSystem.drive(speed, -speed);
        }
    }

    @Override
    public boolean isFinished() {
        return targetAngle - 0.5 < driveSystem.getAngle() && driveSystem.getAngle() < targetAngle + 0.5;
    }

    @Override
    public void end(boolean interrupted) {
        driveSystem.stop();
    }
}
