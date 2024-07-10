package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSystem extends SubsystemBase {
    private CANSparkMax motor;

    public ArmSystem(){
        motor = new CANSparkMax()
    }
}
