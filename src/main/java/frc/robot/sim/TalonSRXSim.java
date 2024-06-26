package frc.robot.sim;

import com.ctre.phoenix.motorcontrol.TalonSRXSimCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.RobotController;

public class TalonSRXSim extends BaseSimMotor {

    private final WPI_TalonSRX motor;
    private final TalonSRXSimCollection motorSim;

    public TalonSRXSim(NetworkTable rootTable, String name,
                       WPI_TalonSRX motor, MotorShaft motorShaft,
                       boolean inverted, boolean updateEncoder) {
        super(rootTable, name, motorShaft, inverted, updateEncoder);
        this.motor = motor;
        motorSim = motor.getSimCollection();
    }

    public TalonSRXSim(NetworkTable rootTable, String name,
                       WPI_TalonSRX motor, MotorShaft motorShaft,
                       boolean inverted) {
        this(rootTable, name, motor, motorShaft, inverted, false);
    }

    @Override
    protected double getMotorOutputVoltage() {
        return motorSim.getMotorOutputLeadVoltage();
    }

    @Override
    protected boolean isMotorInverted() {
        return false;
    }

    @Override
    protected void setAppliedOutput(double voltage) {

    }

    @Override
    protected void setEncoderData(double position, double positionChanged, double velocityRpm) {
        motorSim.setQuadratureRawPosition((int) (position * motorShaft.getEncoderPpr()));
        motorSim.setQuadratureVelocity((int) (velocityRpm * motorShaft.getEncoderPpr() / 600));
    }

    @Override
    public void update() {
        motorSim.setBusVoltage(RobotController.getBatteryVoltage());
    }
}