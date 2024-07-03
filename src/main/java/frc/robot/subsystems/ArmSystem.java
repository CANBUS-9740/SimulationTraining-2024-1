package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sim.ArmSim;

public class ArmSystem extends SubsystemBase {
    //private final CANSparkMax armBottomMotor;
    //private final CANSparkMax armTopMotor;

    //private final ArmSim sim;

    public ArmSystem() {

        // initialize system components and uncomment this, passing these components to the sim
        // sim = new ArmSim(motorBottom, motorTop);
    }

    @Override
    public void periodic() {
        // uncomment this
        // sim.update();
    }
}