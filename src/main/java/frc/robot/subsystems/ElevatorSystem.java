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

    private final ElevatorSim sim;
    private CANSparkMax motor;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;
    private RelativeEncoder encoder;


    public ElevatorSystem() {
        motor = new CANSparkMax(RobotMap.ELEVATOR_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        sim = new ElevatorSim(motor);

        topLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_TOP_SWITCH);
        bottomLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_SWITCH);

        encoder = motor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.NEO_ENCODER_PPR);
    }

    public double getCurrentHeight(){
        double circumference = 2 * Math.PI * RobotMap.ELEVATOR_DRUM_RADIUS_M;
        return circumference * encoder.getPosition() /  RobotMap.ELEVATOR_GEAR_RATIO;
    }

    public boolean getTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean getBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }

    public void move(double speed){
        motor.set(speed);
    }

    public void stop(){
        motor.stopMotor();
    }

    @Override
    public void periodic() {
        sim.update();
        SmartDashboard.putNumber("current height: ", getCurrentHeight());
        SmartDashboard.putBoolean("top switch: ", getTopLimitSwitch());
        SmartDashboard.putBoolean("bottom switch: ", getBottomLimitSwitch());
    }
}
