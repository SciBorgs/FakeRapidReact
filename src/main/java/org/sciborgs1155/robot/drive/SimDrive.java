package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj2.command.Command;

public class SimDrive implements DriveIO { 
    private final DifferentialDrivetrainSim simDriver;
    private final EncoderSim leftEncoderSim;
    private final EncoderSim rightEncoderSim;
    private final ADXRS450_GryoSim gyroSim;
    
    public SimDrive() {
        simDriver = new DifferentialDrivetrainSim(
            DriveConstants.kDrivetrainPlant,
            DriveConstants.kDriveGearbox,
            DriveConstants.kDriveGearing,
            DriveConstants.kTrackwidthMeters,
            DriveConstants.kWheelDiameterMeters / 2.0,
            VecBuilder.fill(0, 0, 0.0001, 0.1, 0.1, 0.005, 0.005));\
        
            leftEncoderSim = new EncoderSim(m_leftEncoder);
            rightEncoderSim = new EncoderSim(m_rightEncoder);
            gyroSim = new ADXRS450_GyroSim(m_gyro);
    }
}
