package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurretSystem;


public class TurretInitCommand extends Command {
    private final TurretSystem turretSystem;
    private double speed;

    public TurretInitCommand(TurretSystem turretSystem) {
        this.turretSystem = turretSystem;
        addRequirements(this.turretSystem);
        this.speed = 0.001;
    }
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
       if(turretSystem.negstivePressed()){
          speed = this.speed;
       }
       if(turretSystem.positivePressed()){
          speed = -this.speed;
       }
       this.turretSystem.powerMotor(speed);

    }

    @Override
    public boolean isFinished() {
        return (this.turretSystem.neutralPressed());
    }

    @Override
    public void end(boolean interrupted) {

    }
}
