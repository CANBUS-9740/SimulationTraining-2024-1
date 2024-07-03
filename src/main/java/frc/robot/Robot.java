package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveAmountCommand;
import frc.robot.commands.TurnCommand;
import frc.robot.commands.TurretAutoCommand;
import frc.robot.commands.TurretInitCommand;
import frc.robot.subsystems.DuoWheelDrivetrainSubsystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {
DuoWheelDrivetrainSubsystem drivetrainSubsystem;
DriveAmountCommand driveAmountCommand;
TurnCommand turnCommand;

    @Override
    public void robotInit() {
        drivetrainSubsystem = new DuoWheelDrivetrainSubsystem();
        driveAmountCommand = new DriveAmountCommand(drivetrainSubsystem, 1.5, 0.9);
        turnCommand = new TurnCommand(drivetrainSubsystem, 90);
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void autonomousInit() {
        driveAmountCommand.andThen(turnCommand).schedule();
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void simulationPeriodic() {

    }
}
