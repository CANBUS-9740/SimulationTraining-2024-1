package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.elevator.MoveToHeight;
import frc.robot.commands.elevator.StayAtHeight;
import frc.robot.subsystems.ElevatorSystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;

    @Override
    public void robotInit() {
        elevatorSystem = new ElevatorSystem();
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
        new MoveToHeight(elevatorSystem, 1.5).andThen(
                new StayAtHeight(elevatorSystem)
        ).schedule();
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void autonomousInit() {

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
