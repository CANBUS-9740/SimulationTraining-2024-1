package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.ElevatorSim;

public class ElevatorSystem extends SubsystemBase {

    // uncomment this
    private final ElevatorSim sim;
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    private DigitalInput limitSwitchTop;
    private DigitalInput limitSwitchBottom;

    public ElevatorSystem() {
        // initialize system components and uncomment this, passing these components to the sim
        motor = new CANSparkMax(8, CANSparkLowLevel.MotorType.kBrushless);
        sim = new ElevatorSim(motor);

        encoder = motor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);
        limitSwitchTop = new DigitalInput(5);
        limitSwitchBottom = new DigitalInput(6);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("get height", getHeight());
        SmartDashboard.putBoolean("get limitSwitchTop", isLimitSwitchTopPressed());
        SmartDashboard.putBoolean("get limitSwitchBottom", isLimitSwitchBottomPressed());
        sim.update();
    }
    public void move(double speed) {
        motor.set(speed);
    }
    public void stop(){
        motor.stopMotor();
    }

    public boolean isLimitSwitchTopPressed(){
        return limitSwitchTop.get();
    }
    public boolean isLimitSwitchBottomPressed(){
        return limitSwitchBottom.get();
    }
    public double getHeight(){
        double circle = Math.PI * 2 * RobotMap.ELEVATOR_DRUM_RADIUS_M;
        return circle * encoder.getPosition() / RobotMap.ELEVATOR_GEAR_RATIO;

    }
}
