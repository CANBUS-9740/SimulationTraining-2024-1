package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSystem;

public class ArmCommand extends Command {
    private ArmSystem armSystem;
    private double targetAngleTop;
    private double targetAngleBottom;
    private PIDController pidControllerTop;
    private PIDController pidControllerBottom;
    public static final double VALUE_STOP_TOP = -91;
    public static final double VALUE_STOP_BOTTOM = -1;


    public ArmCommand(ArmSystem armSystem, double targetAngleBottom,double targetAngleTop){
        this.armSystem = armSystem;
        this.targetAngleBottom = targetAngleBottom;
        this.targetAngleTop = targetAngleTop;

        pidControllerTop = new PIDController(0,0,0);
        pidControllerBottom = new PIDController(0,0,0);

        addRequirements(armSystem);
    }
    public void initialize() {
        pidControllerTop.reset();
        pidControllerBottom.reset();
    }

    public void execute() {
        double outputTop = pidControllerTop.calculate(armSystem.getPositionTop(), targetAngleTop);
        double outputBottom = pidControllerBottom.calculate(armSystem.getPositionBottom(), targetAngleBottom);

        armSystem.setMotorBottom(outputBottom);
        armSystem.setMotorTop(outputTop);
    }

    public void end(boolean interrupted) {
        armSystem.stopTop();
        armSystem.stopBottom();
    }

    public boolean isFinished() {

        return armSystem.reachAngleBottom() && armSystem.reachAngleTop();
    }
}
