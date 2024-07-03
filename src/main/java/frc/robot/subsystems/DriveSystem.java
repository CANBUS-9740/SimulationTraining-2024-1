package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.*;
import edu.wpi.first.hal.simulation.AddressableLEDDataJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.sim.DriveSim;

public class DriveSystem extends SubsystemBase {
    private final DriveSim sim;
    private CANSparkMax motorFR;
    private CANSparkMax motorBR;
    private CANSparkMax motorFL;
    private CANSparkMax motorBL;
    private WPI_Pigeon2 pigeon;
    private RelativeEncoder encoderL;
    private RelativeEncoder encoderR;


    public DriveSystem() {

        motorFR =  new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
        motorBR =  new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);
        motorFL =  new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
        motorBL =  new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);

        motorFR.setInverted(true);
        motorBR.setInverted(true);

        encoderL = motorBL.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature ,RobotMap.SRX_ENCODER_PPR);
        encoderR = motorFL.getAlternateEncoder(SparkMaxAlternateEncoder.Type.kQuadrature, RobotMap.SRX_ENCODER_PPR);
        pigeon = new WPI_Pigeon2(5);
        sim = new DriveSim( motorFL ,motorBL , motorFR, motorBR, pigeon);

    }

    public void drive(double speed){

        motorFR.set(speed);
        motorBR.set(speed);
        motorFL.set(speed);
        motorBL.set(speed);
    }

    public void drive(double speedR, double speedL){
        motorFR.set(speedR);
        motorBR.set(speedR);
        motorFL.set(speedL);
        motorBL.set(speedL);
    }

    public void stop(){
        motorFR.stopMotor();
        motorBR.stopMotor();
        motorFL.stopMotor();
        motorBL.stopMotor();
    }

    public double getAngle(){
        return pigeon.getYaw();
    }

    public double getEncoderL() {
        return encoderL.getPosition() / RobotMap.DRIVE_GEAR_RATIO * RobotMap.ELEVATOR_DRUM_RADIUS_M * 2 * Math.PI ;
    }
    public double getEncoderR() {
        return encoderR.getPosition() / RobotMap.DRIVE_GEAR_RATIO * RobotMap.ELEVATOR_DRUM_RADIUS_M * 2 * Math.PI ;
    }
    public double getDistance(){
        return (getEncoderL() + getEncoderR())/2;

    }

    @Override
    public void periodic(){
        sim.update();
        SmartDashboard.putNumber("distance", getDistance());
        SmartDashboard.putNumber("angle", getAngle());
    }
}
