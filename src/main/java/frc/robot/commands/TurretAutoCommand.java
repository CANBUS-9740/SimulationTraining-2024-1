package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSystem;


public class TurretAutoCommand extends Command {
    private final TurretSystem turretSystem;
    private double speed;
    private double pos;

    public TurretAutoCommand(TurretSystem turretSystem, double speed, double setPos) {
        this.turretSystem = turretSystem;
        addRequirements(this.turretSystem);
        this.speed = speed;
        if(setPos < 0){
            this.speed *= -1;
        }
        this.pos = setPos;
    }
    @Override
    public void initialize() {

    }
    @Override
    public void execute() {
        this.turretSystem.powerMotor(this.speed);
    }
    @Override
    public boolean isFinished() {
        return ((Math.abs((this.turretSystem.getSpins()*360) - this.pos) < 0.5) || this.turretSystem.negstivePressed() || this.turretSystem.positivePressed());
    }
    @Override
    public void end(boolean interrupted) {
    }
}
