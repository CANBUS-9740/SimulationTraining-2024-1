package frc.robot.sim;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.jni.CANSparkMaxJNI;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotState;

import java.lang.reflect.Field;

public class SparkMaxSim {

    private final CANSparkMax motor;
    private final MotorShaft motorShaft;
    private final boolean inverted;
    private final boolean updateEncoder;

    private final long handle;
    private final RelativeEncoder encoder;

    private final NetworkTableEntry entryOutput;
    private final NetworkTableEntry entryOutputVoltage;
    private final NetworkTableEntry entryLastRevolutions;
    private final NetworkTableEntry entryPosition;
    private final NetworkTableEntry entryRevolutions;
    private final NetworkTableEntry entryVelocity;
    private final NetworkTableEntry entryRpm;

    public SparkMaxSim(NetworkTable rootTable, String name,
                       CANSparkMax motor, MotorShaft motorShaft,
                       boolean inverted, boolean updateEncoder) {
        this.motor = motor;
        this.motorShaft = motorShaft;
        this.inverted = inverted;
        this.updateEncoder = updateEncoder;

        handle = getHandle(motor);

        if (updateEncoder) {
            // will through if user misconfigured the encoder
            encoder = motor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, motorShaft.getEncoderPpr());
        } else {
            encoder = null;
        }

        NetworkTable table = rootTable.getSubTable(name);
        entryOutput = table.getEntry("Output");
        entryOutputVoltage = table.getEntry("OutputVoltage");
        entryLastRevolutions = table.getEntry("LastRevolutions");
        entryPosition = table.getEntry("Position");
        entryRevolutions = table.getEntry("Revolutions");
        entryVelocity = table.getEntry("Velocity");
        entryRpm = table.getEntry("RPM");

        if (updateEncoder) {
            entryLastRevolutions.setDouble(encoder.getPosition());
        }
    }

    public SparkMaxSim(NetworkTable rootTable, String name,
                       CANSparkMax motor, MotorShaft motorShaft,
                       boolean inverted) {
        this(rootTable, name, motor, motorShaft, inverted, false);
    }

    public double updateOutput(double busVoltage) {
        double value = motor.get();

        if (inverted) {
            value = -value;
        }
        if (motor.getInverted()) {
            value = -value;
        }

        if (RobotState.isDisabled()) {
            value = 0;
        }

        double voltage = value * busVoltage;
        setAppliedOutput(voltage);

        entryOutput.setDouble(value);
        entryOutputVoltage.setDouble(voltage);

        return voltage;
    }

    public void updateOdometry(double position, double velocity) {
        if (!updateEncoder) {
            return;
        }

        if (inverted) {
            position = -position;
            velocity = -velocity;
        }

        double revs = motorShaft.distanceToMotorRotations(position);
        double rpm = motorShaft.velocityToMotorRotations(velocity);

        double positionDifference = revs - entryLastRevolutions.getDouble(0);
        double encoderPos = encoder.getPosition();

        encoder.setPosition(encoderPos + positionDifference);
        setSimVelocity(rpm);

        entryLastRevolutions.setDouble(revs);
        entryPosition.setDouble(position);
        entryRevolutions.setDouble(revs);
        entryVelocity.setDouble(velocity);
        entryRpm.setDouble(rpm);
    }

    private void setAppliedOutput(double voltage) {
        CANSparkMaxJNI.c_SparkMax_SetSimAppliedOutput(handle, (float) voltage);
    }

    private void setSimVelocity(double velocityRpm) {
        CANSparkMaxJNI.c_SparkMax_SetSimAltEncoderVelocity(handle, (float) velocityRpm);
    }

    private static long getHandle(CANSparkMax motor) {
        try {
            Field field = ReflectionHelper.getField(motor.getClass(), "sparkMaxHandle");
            field.setAccessible(true);
            return (long) field.get(motor);
        } catch (IllegalAccessException | NumberFormatException e) {
            throw new Error("unable to access spark handle", e);
        }
    }
}
