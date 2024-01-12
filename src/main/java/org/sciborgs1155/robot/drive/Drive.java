package org.sciborgs1155.robot.drive;

import org.sciborgs1155.robot.Robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class Drive extends SubsystemBase implements Logged {
  private final PIDController pid = new PIDController(0, 0, 0);

  private final DriveIO driver;
  public Drive(DriveIO driverIO){
    driver=driverIO;
  }
  public static Drive createFromConfigure() {
    return Robot.isReal() ? new Drive(new RealDrive()) : new Drive(new SimDrive()); // see if you are real
  }

  public Command driveDistance(double distance,double speed) {
    return runOnce(()->driver.dr(distance,speed));
  }

}