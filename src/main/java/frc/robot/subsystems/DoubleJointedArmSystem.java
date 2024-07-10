package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DoubleJointedArmSystem extends SubsystemBase {
    private final CANSparkMax armMotor;
    private final CANSparkMax handMotor;

    public DoubleJointedArmSystem(){
        this.armMotor = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
        this.handMotor = new CANSparkMax(0, CANSparkLowLevel.MotorType.kBrushless);
    }
}
