package org.sciborgs1155.robot.Drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import org.sciborgs1155.robot.Constants;

public class SimDrive implements DriveIO {

  DifferentialDrivetrainSim sim =
      new DifferentialDrivetrainSim(
          DriveConstants.DRIVEMOTOR,
          DriveConstants.GEARING,
          DriveConstants.JKGMETERSSQUARED,
          DriveConstants.MASSKG,
          DriveConstants.WHEELRADIUSMETERS,
          DriveConstants.TRACKWIDTHMETERS,
          DriveConstants.MEASUREMENTSSTDDEVS);

  public SimDrive() {
    sim.setPose(DriveConstants.STARTINGPOSE);
  }

  public double getLeftSpeed() {
    return sim.getLeftVelocityMetersPerSecond();
  }

  public double getRightSpeed() {
    return sim.getRightVelocityMetersPerSecond();
  }

  @Override
  public void setVoltage(double leftSpeed, double rightSpeed) {
    sim.setInputs(leftSpeed, rightSpeed);
  }

  @Override
  public void updateState() {
    sim.update(Constants.PERIOD);
  }

  @Override
  public Pose2d getPose() {
    return sim.getPose();
  }
}
