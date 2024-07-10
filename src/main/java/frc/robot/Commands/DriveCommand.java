package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends Command {
    private DriveSystem driveSystem;
    private double targetDistanceDrive;

    private double startDistance;

    public DriveCommand(DriveSystem driveSystem, double targetDistanceDrive){
        this.driveSystem = driveSystem;
        this.targetDistanceDrive = targetDistanceDrive;

        addRequirements(driveSystem);
    }

    @Override
    public void initialize() {
        startDistance = driveSystem.getDistancePassed();
    }

    @Override
    public void execute() {
        double speed = (targetDistanceDrive - driveSystem.getDistancePassed()) / (targetDistanceDrive - startDistance);
        driveSystem.drive(speed, speed);
    }

    @Override
    public boolean isFinished() {
        return (driveSystem.getDistancePassed() > (targetDistanceDrive - 0.5)) && (driveSystem.getDistancePassed() < (targetDistanceDrive + 0.5)) ;
    }

    @Override
    public void end(boolean interrupted) {
        driveSystem.stop();
    }
}
