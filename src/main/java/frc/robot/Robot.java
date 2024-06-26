package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.ElevatorMoveToTarget;
import frc.robot.Commands.ElevatorStayAtHeight;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ElevatorSystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {

    private ElevatorSystem elevatorSystem;
    private ElevatorMoveToTarget elevatorMoveToTarget;
    private ElevatorStayAtHeight elevatorStayAtHeight;

    @Override
    public void robotInit() {
        elevatorSystem = new ElevatorSystem();

        SequentialCommandGroup elevator = new SequentialCommandGroup(
                elevatorMoveToTarget = new ElevatorMoveToTarget(elevatorSystem, 1.5),
                elevatorStayAtHeight = new ElevatorStayAtHeight(elevatorSystem)
        );

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
