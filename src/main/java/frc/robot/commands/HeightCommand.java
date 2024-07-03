package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;


public class HeightCommand extends Command {
    private final ElevatorSubsystem elevatorSubsystem;
    private double speed;
    private double loc;
    public HeightCommand(ElevatorSubsystem elevatorSubsystem, double a, double location) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(this.elevatorSubsystem);
        this.speed = a;
        loc = location;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        this.elevatorSubsystem.powerMotor(this.speed);
    }

    @Override
    public boolean isFinished() {
        return MathUtil.isNear(this.loc, this.elevatorSubsystem.getDistance(), 0.11)|| this.elevatorSubsystem.getDownPressed() || this.elevatorSubsystem.getUpPressed();
    }

    @Override
    public void end(boolean interrupted) {

    }
}
