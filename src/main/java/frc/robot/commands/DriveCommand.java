package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends Command {
    private DriveSystem driveSystem;
    private double targetPosition;
    private double startPoistion;


    public DriveCommand(DriveSystem driveSystem, double targetPosition){
        this.driveSystem = driveSystem;
        this.targetPosition = targetPosition;

        addRequirements(driveSystem);
    }
    public void initialize() {
        startPoistion = driveSystem.getDistance();
    }

    public void execute() {
        double errorDistance = (targetPosition - driveSystem.getDistance())/(targetPosition - startPoistion) * 0.1;
        driveSystem.drive(errorDistance, errorDistance);

    }

    public void end(boolean interrupted) {
        System.out.println("finished DriveCommand");
    }

    public boolean isFinished() {

        return (driveSystem.getDistance() - targetPosition <= 1) || (driveSystem.getDistance() - targetPosition >= -1);
    }
}
