package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSystem;

public class ResetTurretToZero extends Command {
    private final TurretSystem turretSystem;

    private boolean reachedLeft;

    public ResetTurretToZero(TurretSystem turretSystem) {
        this.turretSystem = turretSystem;

        addRequirements(turretSystem);
    }

    @Override
    public void initialize() {
        reachedLeft = turretSystem.getLeftLimitSwitchValue();
    }

    @Override
    public void execute() {
        if (reachedLeft)
            turretSystem.moveTurret(0.2);
        else
            turretSystem.moveTurret(-0.2);
    }

    @Override
    public void end(boolean interrupted) {
        turretSystem.stopTurret();
        turretSystem.resetTurretMotorEncoder();
    }

    @Override
    public boolean isFinished() {
        return turretSystem.getCenterLimitSwitchValue();
    }
}
