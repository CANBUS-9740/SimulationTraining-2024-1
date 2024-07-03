package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.TurretSim;

public class TurretSystem extends SubsystemBase {
    private final CANSparkMax turretMotor;
    private final RelativeEncoder enc;
    private final TurretSim sim;
    private final DigitalInput negative ;
    private final DigitalInput positive;
    private final DigitalInput neutral;

    public TurretSystem() {
        turretMotor = new CANSparkMax(7, CANSparkLowLevel.MotorType.kBrushless);
        sim = new TurretSim(turretMotor);
        enc = turretMotor.getEncoder();
        negative = new DigitalInput(0);
        neutral = new DigitalInput(1);
        positive = new DigitalInput(2);
    }
    public void powerMotor(double pow){
        turretMotor.set(pow);
    }
    public void stopMotor(){
        turretMotor.stopMotor();
    }
    public double getSpins(){
        return (enc.getPosition()/ RobotMap.NEO_ENCODER_PPR)*360;
    }
    public boolean negstivePressed(){
        return negative.get();
    }
    public boolean positivePressed(){
        return positive.get();
    }
    public boolean neutralPressed(){
        return neutral.get();
    }

    @Override
    public void periodic() {
        sim.update();
    }
}
