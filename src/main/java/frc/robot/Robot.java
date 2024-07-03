package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.MoveToHeight;
import frc.robot.commands.StayAtHeight;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ElevatorSystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;
    private MoveToHeight moveToHeight;
    private StayAtHeight stayAtHeight;
    private DriveSystem driveSystem;
    private DriveCommand driveCommand;
    private TurnToAngle turnToAngle;

    @Override
    public void robotInit() {
        elevatorSystem = new ElevatorSystem();
        moveToHeight = new MoveToHeight(elevatorSystem, 1);
        stayAtHeight = new StayAtHeight(elevatorSystem);
        driveSystem = new DriveSystem();
        driveCommand = new DriveCommand(driveSystem,1);
        turnToAngle = new TurnToAngle(driveSystem, 1);
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
        //SequentialCommandGroup elevator = new SequentialCommandGroup(
        //        moveToHeight,
        //        stayAtHeight)
        new DriveCommand(driveSystem,1).andThen(
                new TurnToAngle(driveSystem,1)
        ).schedule();
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
