package org.sciborgs1155.robot.drive;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RealDrive implements DriveIO {
  //  left and right side motors of drive
  private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kLeftMotor1Port,MotorType.kBrushed);
  private final CANSparkMax leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port,MotorType.kBrushed);
  private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.kRightMotor1Port,MotorType.kBrushed);
  private final CANSparkMax rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port,MotorType.kBrushed);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);
  
  private final RelativeEncoder leftEncoder= leftLeader.getEncoder();
  private final RelativeEncoder rightEncoder= rightLeader.getEncoder();

  private final ADIS16448_IMU gyro = new ADIS16448_IMU();
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(gyro.getAngle()), leftEncoder.getPosition(), rightEncoder.getPosition(), new Pose2d(5, 13.5, new Rotation2d()));
  private Pose2d pose = new Pose2d();

  public RealDrive() {
    leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);

    leftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderVelocityConversionFactor);
    leftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderVelocityConversionFactor);

    //idk if this is actually needed
    rightLeader.setInverted(true);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader, true);
  }

  @Override public void setVoltages(double leftVoltage, double rightVoltage) {
    leftLeader.setVoltage(leftVoltage);
    rightLeader.setVoltage(rightVoltage);
  }
  @Override
  public Pose2d getPose() {
      return odometry.getPoseMeters();
  }

  @Override public double getLVelocity() {
    return leftEncoder.getVelocity();
  }
  @Override public double getRVelocity() {
    return rightEncoder.getVelocity();
  }
  
  @Override
  public void update() {
    Rotation2d gyroAngle = Rotation2d.fromDegrees(gyro.getGyroAngleX());
    pose = odometry.update(gyroAngle, leftEncoder.getPosition(), rightEncoder.getPosition());
  }

  @Override public void close() throws Exception {leftLeader.close(); leftFollower.close(); rightLeader.close(); rightFollower.close();}
  
}


//First, inspires by https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/rapidreactcommandbot/subsystems/Drive.java
//a bit less now...