package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSystem;

public class ArmCommand extends Command {

    private static final double VALUE_STOP = Double.MAX_VALUE;

    private static final boolean TUNING_MODE = false;
    private static final double KP = 0.21;
    private static final double KI = 0.03;
    private static final double KD = 0.012;
    private static final double IZONE = 2;
    private static final double TOLERANCE_POSITION = 1;
    private static final double TOLERANCE_VELOCITY = 0.1;

    private final ArmSystem armSystem;

    private final PIDController controllerBottom;
    private final PIDController controllerTop;

    private double currentTargetAngleBottom;
    private double currentTargetAngleTop;
    private boolean shouldMoveToTarget;

    private double targetAngleBottom;
    private double targetAngleTop;
    private boolean newTarget;

    public ArmCommand(ArmSystem armSystem) {
        this.armSystem = armSystem;

        controllerBottom = new PIDController(KP, KI, KD);
        controllerBottom.setIZone(IZONE);
        controllerBottom.setTolerance(TOLERANCE_POSITION, TOLERANCE_VELOCITY);

        controllerTop = new PIDController(KP, KI, KD);
        controllerTop.setIZone(IZONE);
        controllerTop.setTolerance(TOLERANCE_POSITION, TOLERANCE_VELOCITY);

        addRequirements(armSystem);

        if (TUNING_MODE) {
            SmartDashboard.putNumber("kp", 0);
            SmartDashboard.putNumber("ki", 0);
            SmartDashboard.putNumber("kd", 0);
            SmartDashboard.putNumber("izone", 0);
        }
    }

    @Override
    public void initialize() {
        currentTargetAngleBottom = VALUE_STOP;
        currentTargetAngleTop = VALUE_STOP;
        shouldMoveToTarget = false;
        newTarget = true;
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("ArmPosTop", armSystem.getAngleTop());
        SmartDashboard.putNumber("ArmPosBottom", armSystem.getAngleBottom());

        if (newTarget) {
            newTarget = false;
            currentTargetAngleBottom = targetAngleBottom;
            currentTargetAngleTop = targetAngleTop;
            shouldMoveToTarget = currentTargetAngleBottom != VALUE_STOP && currentTargetAngleTop != VALUE_STOP;

            SmartDashboard.putNumber("ArmTargetPosBottom", currentTargetAngleBottom);
            SmartDashboard.putNumber("ArmTargetPosTop", currentTargetAngleTop);

            controllerBottom.reset();
            controllerTop.reset();

            if (!shouldMoveToTarget) {
                SmartDashboard.putNumber("ArmOutputBottom", 0);
                SmartDashboard.putNumber("ArmOutputTop", 0);

                armSystem.stop();
            }
        }

        if (shouldMoveToTarget) {
            if (TUNING_MODE) {
                double kp = SmartDashboard.getNumber("kp", 0);
                double ki = SmartDashboard.getNumber("ki", 0);
                double kd = SmartDashboard.getNumber("kd", 0);
                double izone = SmartDashboard.getNumber("izone", 0);

                controllerBottom.setPID(kp, ki, kd);
                controllerBottom.setIZone(izone);
                controllerTop.setPID(kp, ki, kd);
                controllerTop.setIZone(izone);
            }

            double outputBottom = controllerBottom.calculate(armSystem.getAngleBottom(), currentTargetAngleBottom);
            double outputTop = controllerTop.calculate(armSystem.getAngleTop(), currentTargetAngleTop);

            SmartDashboard.putNumber("ArmOutputBottom", outputBottom);
            SmartDashboard.putNumber("ArmOutputTop", outputTop);

            SmartDashboard.putBoolean("ArmAtSetpointBottom", controllerBottom.atSetpoint());
            SmartDashboard.putBoolean("ArmAtSetpointTop", controllerTop.atSetpoint());

            armSystem.move(outputBottom, outputTop);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        armSystem.stop();
    }

    public void setTarget(double targetAngleBottom, double targetAngleTop) {
        this.targetAngleBottom = targetAngleBottom;
        this.targetAngleTop = targetAngleTop;
        newTarget = true;
    }

    public void stop() {
        setTarget(targetAngleBottom, targetAngleTop);
    }

    public boolean didReachTarget() {
        if (!shouldMoveToTarget) {
            return true;
        }

        return controllerBottom.atSetpoint() && controllerTop.atSetpoint();
    }
}
