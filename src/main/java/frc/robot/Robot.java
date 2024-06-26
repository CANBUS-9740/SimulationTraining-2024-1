package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.MoveToHeight;
import frc.robot.commands.StayAtHeight;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ElevatorSystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;
    private MoveToHeight moveToHeight;
    private StayAtHeight stayAtHeight;

    @Override
    public void robotInit() {
        elevatorSystem = new ElevatorSystem();
        moveToHeight = new MoveToHeight(elevatorSystem, 1);
        stayAtHeight = new StayAtHeight(elevatorSystem);


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
        SequentialCommandGroup elevator = new SequentialCommandGroup(
                moveToHeight,
                stayAtHeight);
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
