package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.ArmSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ElevatorSystem;

import javax.swing.*;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;
    private MoveToHeightCommand moveToHeightCommand;
    private StayAtHeightCommand stayAtHeightCommand;
    private DriveSystem driveSystem;
    private DriveDistanceCommand driveDistanceCommand;
    private RotateByDegreesCommand rotateByDegreesCommand;
    private ArmSystem armSystem;
    private ArmCommand armCommand;

    @Override
    public void robotInit() {
//        elevatorSystem = new ElevatorSystem();
//        moveToHeightCommand = new MoveToHeightCommand(elevatorSystem, 1);
//        stayAtHeightCommand = new StayAtHeightCommand(elevatorSystem);
//        driveSystem = new DriveSystem();
//        driveDistanceCommand = new DriveDistanceCommand(driveSystem, 4);
//        rotateByDegreesCommand = new RotateByDegreesCommand(driveSystem, 270);
        armSystem = new ArmSystem();
        armCommand = new ArmCommand(armSystem, 45, 10);
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
        //rotateByDegreesCommand.schedule();
        armCommand.schedule();
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void autonomousInit() {
        //driveDistanceCommand.schedule();
        //moveToHeightCommand.andThen
                //(stayAtHeightCommand).schedule();
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
