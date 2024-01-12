package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RealDrive extends SubsystemBase implements DriveIO {
  //  left and right side motors of drive
  private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kLeftMotor1Port,MotorType.kBrushed);
  private final CANSparkMax leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port,MotorType.kBrushed);
  private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.kRightMotor1Port,MotorType.kBrushed);
  private final CANSparkMax rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port,MotorType.kBrushed);

  private final DifferentialDrive diffDrive = new DifferentialDrive(leftLeader, rightLeader);
  
  private final Encoder leftEncoder= new Encoder(DriveConstants.kLeftEncoderPort[0], DriveConstants.kLeftEncoderPort[1], DriveConstants.kLeftReversed);
  private final Encoder rightEncoder= new Encoder(DriveConstants.kLeftEncoderPort[0], DriveConstants.kLeftEncoderPort[1], DriveConstants.kLeftReversed);

  public RealDrive() {
    leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    
    //idk if this is actually needed
    rightLeader.setInverted(true);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader, true);
  }

  public Command driveDistance(double distance, double speed){
    return runOnce(() -> {leftEncoder.reset(); rightEncoder.reset();});
  }
}


//First, inspires by https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/rapidreactcommandbot/subsystems/Drive.java