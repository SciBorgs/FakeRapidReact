package org.sciborgs1155.robot.drive;

import com.revrobotics.CANSparkMax;

public class RealDrive implements DriveIO {
     // The motors on the left side of the drive.
  private final CANSparkMax m_leftLeader = new CANSparkMax(DriveConstants.kLeftMotor1Port);
  private final CANSparkMax m_leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port);

  // The motors on the right side of the drive.
  private final CANSparkMax m_rightLeader = new CANSparkMax(DriveConstants.kRightMotor1Port);
  private final CANSparkMax m_rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port);
}
