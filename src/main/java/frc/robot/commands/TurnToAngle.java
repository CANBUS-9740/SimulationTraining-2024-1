package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class TurnToAngle extends Command {
    private DriveSystem driveSystem;
    private double targetAngle;
    private double startAngle;


    public  TurnToAngle(DriveSystem driveSystem, double targetAngle){
        this.targetAngle = targetAngle;
        addRequirements(driveSystem);
    }
    public void initialize() {
        startAngle = driveSystem.getAngle();
    }

    public void execute() {
        if(driveSystem.getAngle() > targetAngle) {
            driveSystem.drive(0.5, -0.5);
        } else if (driveSystem.getAngle() < targetAngle) {
            driveSystem.drive(-0.5, 0.5);
        }
    }

    public void end(boolean interrupted) {
    }

    public boolean isFinished() {
        return driveSystem.getAngle() <= targetAngle;
    }
}
