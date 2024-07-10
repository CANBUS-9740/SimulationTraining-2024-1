package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ArmSystem;

public class ArmCommand extends Command {
    private final ArmSystem sub;
    private PIDController PIDBottom;
    private PIDController PIDTop;
    private final double topTarget;
    private final double bottomTarget;

    public ArmCommand(ArmSystem sub, double topTarget, double bottomTarget) {
        this.sub = sub;
        this.bottomTarget = bottomTarget;
        this.topTarget = topTarget;

        PIDBottom = new PIDController(0.05, 0.001, 0.01);
        PIDTop = new PIDController(0.3, 0.005, 0.015);

        addRequirements(sub);
    }

    @Override
    public void initialize() {
        PIDTop.reset();
        PIDBottom.reset();
    }

    @Override
    public void execute() {
        double topOutput = PIDTop.calculate(sub.getEncoderTop(), topTarget);
        double bottomOutput = PIDBottom.calculate(sub.getEncoderBottom(), bottomTarget);

        sub.moveToTarget(bottomOutput, topOutput);

        SmartDashboard.putData("PIDTop", PIDTop);
        SmartDashboard.putData("PIDBottom", PIDBottom);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        sub.stop();
    }


}
