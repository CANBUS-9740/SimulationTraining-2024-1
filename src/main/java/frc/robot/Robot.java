package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.ArmCommandForever;
import frc.robot.subsystems.ArmSubsystem;

public class Robot extends TimedRobot {

    private ArmSubsystem armSubsystem;
    private ArmCommandForever armCommandForever;

    @Override
    public void robotInit() {
        armSubsystem = new ArmSubsystem();
        armCommandForever = new ArmCommandForever(armSubsystem);
        armSubsystem.setDefaultCommand(armCommandForever);

        SmartDashboard.putNumber("targetangle", -1);
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
        double target = SmartDashboard.getNumber("targetangle", -1);
        armCommandForever.targetAngle = target;
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
