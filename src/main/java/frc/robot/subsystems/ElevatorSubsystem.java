package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ElevatorSubsystem extends SubsystemBase {
    private CANSparkMax Motor;
    private RelativeEncoder encoder;
    private DigitalInput limitSwitchUp;
    private DigitalInput getLimitSwitchDown;
    public ElevatorSubsystem() {
        Motor = new CANSparkMax(RobotMap.ELEVATOR_MOTOR, CANSparkLowLevel.MotorType.kBrushless);
        encoder = Motor.getAlternateEncoder(RobotMap.NEO_ENCODER_PPR);
        limitSwitchUp = new DigitalInput(RobotMap.ELEVATOR_TOP_SWITCH);

    }

    public void powerMotor(double a){
        Motor.set(a);
    }
    public void StopMotor(){
        Motor.stopMotor();
    }
    public double getDistance(){
        return encoder.getPosition() * RobotMap.ELEVATOR_DRUM_RADIUS_M;
    }
    public boolean getUpPressed(){
        return !limitSwitchUp.get();
    }
    public boolean getDownPressed(){
        return !getLimitSwitchDown.get();
    }
}

