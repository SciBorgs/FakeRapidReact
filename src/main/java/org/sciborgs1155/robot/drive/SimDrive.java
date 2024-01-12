package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;

public class SimDrive implements DriveIO { 
    private final DifferentialDrivetrainSim simDriver;
    private final Field2d field  = new Field2d();
    private final Pose2d pose = new Pose2d();
    
    public SimDrive() {
        simDriver = new DifferentialDrivetrainSim(
            DriveConstants.kDrivetrainPlant,
            DriveConstants.kDriveGearbox,
            DriveConstants.kDriveGearing,
            DriveConstants.kTrackwidthMeters,
            DriveConstants.kWheelDiameterMeters / 2.0,
            VecBuilder.fill(0, 0, 0.0001, 0.1, 0.1, 0.005, 0.005));
    }

}
