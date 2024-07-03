package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSystem;

public class MoveArmToAngle extends Command {
    private final ArmSystem armSystem;
    private final double targetBottomAngle;
    private final double targetTopAngle;

    private double currentBottomAngle;
    private double currentTopAngle;

    public MoveArmToAngle(ArmSystem armSystem, double targetBottomAngle, double targetTopAngle) {
        this.armSystem = armSystem;
        this.targetBottomAngle = targetBottomAngle;
        this.targetTopAngle = targetTopAngle;

        addRequirements(armSystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        currentBottomAngle = armSystem.getBottomMotorAngle();
        currentTopAngle = armSystem.getTopMotorAngle();

        double bottomMotorPower = (targetBottomAngle - currentBottomAngle) / (RobotMap.ARM_MAX_ANGLE - RobotMap.ARM_MIN_ANGLE);
        armSystem.moveBottomArm(bottomMotorPower);


        double topMotorPower = (targetTopAngle - currentTopAngle) / (0.5 * (RobotMap.ARM_MAX_ANGLE - RobotMap.ARM_MIN_ANGLE));
        armSystem.moveTopArm(topMotorPower + 0.22);
    }

    @Override
    public void end(boolean interrupted) {
        armSystem.stopArm();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
