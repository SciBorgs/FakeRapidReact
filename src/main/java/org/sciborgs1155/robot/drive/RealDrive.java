package org.sciborgs1155.robot.drive;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

  @Override public void setLVoltage(double voltage) {
    leftLeader.setVoltage(voltage);
  }
  @Override public void setRVoltage(double voltage) {
    rightLeader.setVoltage(voltage);
  }
  @Override public double getLDistanceTraveled() {
    return leftEncoder.getPosition();
  }
  @Override public double getRDistanceTraveled() {
    return rightEncoder.getPosition();
  }
  @Override public double getRVelocity() {
    return rightEncoder.getVelocity();
  }
  @Override public double getLVelocity() {
    return leftEncoder.getVelocity();
  }

}


//First, inspires by https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/rapidreactcommandbot/subsystems/Drive.java
//a bit less now...