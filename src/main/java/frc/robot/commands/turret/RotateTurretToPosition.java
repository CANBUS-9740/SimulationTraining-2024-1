package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSystem;

public class RotateTurretToPosition extends Command {
    private final TurretSystem turretSystem;

    private final double targetAngle;
    private double startAngle;
    private double currentAngle;

    public RotateTurretToPosition(TurretSystem turretSystem, double targetAngle) {
        this.turretSystem = turretSystem;
        this.targetAngle = targetAngle;

        addRequirements(turretSystem);
    }

    @Override
    public void initialize() {
        startAngle = turretSystem.getTurretAngle();
    }

    @Override
    public void execute() {
        currentAngle = turretSystem.getTurretAngle();
        double motorPower = (targetAngle - currentAngle) / 45;

        turretSystem.moveTurret(motorPower);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Stopped Turret");
        turretSystem.stopTurret();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(targetAngle - currentAngle) < 1 ||
                (turretSystem.getLeftLimitSwitchValue() && startAngle > targetAngle) ||
                (turretSystem.getRightLimitSwitchValue() && startAngle < targetAngle);
    }
}