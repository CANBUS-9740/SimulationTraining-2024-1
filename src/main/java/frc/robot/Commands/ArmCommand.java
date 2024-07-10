package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSystem;

public class ArmCommand extends Command {
    private final ArmSystem armSystem;

    private double targetAngleTop;
    private double targetAngleBottom;

    private final PIDController pidTop;
    private final PIDController pidBottom;

    public ArmCommand(ArmSystem armSystem, double targetAngleBottom, double targetAngleTop){
        this.armSystem = armSystem;

        this.targetAngleBottom = targetAngleBottom;
        this.targetAngleTop = targetAngleTop;

        this.pidTop = new PIDController(0.12,0.002,0.01);
        this.pidBottom = new PIDController(0.12,0.002,0.01);

        addRequirements(armSystem);
    }

    @Override
    public void initialize() {
    }

    public void execute(){
        double speedBottom = pidBottom.calculate(armSystem.getBottomAngle(), targetAngleBottom);
        armSystem.bottomMotorMoveToAngle(speedBottom);

        double speedTop = pidTop.calculate(armSystem.getTopAngle(), targetAngleTop);
        armSystem.topMotorMoveToAngle(speedTop);

        SmartDashboard.putData("PIDTop", pidTop);
        SmartDashboard.putData("PIDBottom", pidBottom);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        armSystem.stopBottomMotor();
        armSystem.stopTopMotor();
    }
}
