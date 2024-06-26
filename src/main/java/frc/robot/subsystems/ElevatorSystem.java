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
    private RelativeEncoder encoder;
    private DigitalInput bottomSwitch;
    private DigitalInput topSwitch;
    private double PPR = RobotMap.NEO_ENCODER_PPR;

    public ElevatorSystem() {
        motor = new CANSparkMax(RobotMap.ELEVATOR_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        bottomSwitch = new DigitalInput(RobotMap.ELEVATOR_BOTTOM_SWITCH);
        topSwitch = new DigitalInput(RobotMap.ELEVATOR_TOP_SWITCH);
        encoder = motor.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature,RobotMap.NEO_ENCODER_PPR);
        sim = new ElevatorSim(motor);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("elevator bottom: ", getBottomSwitch());
        SmartDashboard.putBoolean("elevator top: ", getTopSwitch());
        sim.update();
    }

    public double getHeight(){
        double circle = Math.PI * 2 * RobotMap.ELEVATOR_DRUM_RADIUS_M;
        return circle * encoder.getPosition() / RobotMap.ELEVATOR_GEAR_RATIO ;
    }

    public boolean getBottomSwitch(){
        return bottomSwitch.get();
    }

    public boolean getTopSwitch(){
        return topSwitch.get();
    }

    public void stop(){
        motor.stopMotor();
    }

    public void move(double speed){
            motor.set(speed);
    }
}
