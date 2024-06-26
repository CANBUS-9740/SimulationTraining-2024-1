package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MoveToHeightCommand;
import frc.robot.commands.StayAtHeightCommand;
import frc.robot.subsystems.ElevatorSystem;

import javax.swing.*;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;
    private MoveToHeightCommand moveToHeightCommand;
    private StayAtHeightCommand stayAtHeightCommand;

    @Override
    public void robotInit() {
        elevatorSystem = new ElevatorSystem();
        moveToHeightCommand = new MoveToHeightCommand(elevatorSystem, 1);
        stayAtHeightCommand = new StayAtHeightCommand(elevatorSystem);
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
        SequentialCommandGroup sequentialCommandGroup = new SequentialCommandGroup(
                moveToHeightCommand,
                stayAtHeightCommand
                );
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
