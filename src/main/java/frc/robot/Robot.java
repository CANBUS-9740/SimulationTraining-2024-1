package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.arm.MoveArmToAngle;
import frc.robot.commands.elevator.MoveToHeight;
import frc.robot.commands.elevator.StayAtHeight;
import frc.robot.subsystems.ArmSystem;
import frc.robot.subsystems.ElevatorSystem;
import frc.robot.subsystems.TurretSystem;

public class Robot extends TimedRobot {
    private ElevatorSystem elevatorSystem;
    private ArmSystem armSystem;

    @Override
    public void robotInit() {
        //elevatorSystem = new ElevatorSystem();
        armSystem = new ArmSystem();
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void teleopInit() {
        //new MoveToHeight(elevatorSystem, 1.5).andThen(
        //        new StayAtHeight(elevatorSystem)
        //).schedule();

        new MoveArmToAngle(armSystem, 90, 45).schedule();
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
