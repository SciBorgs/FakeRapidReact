package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;

public class RealDrive implements DriveIO {
  //  left and right side motors of drive
  private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kLeftMotor1Port,MotorType.kBrushed);
  private final CANSparkMax leftFollower = new CANSparkMax(DriveConstants.kLeftMotor2Port,MotorType.kBrushed);
  private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.kRightMotor1Port,MotorType.kBrushed);
  private final CANSparkMax rightFollower = new CANSparkMax(DriveConstants.kRightMotor2Port,MotorType.kBrushed);

  private final DifferentialDrive motorDrive = new DifferentialDrive(leftLeader, rightLeader);
  
  private final RelativeEncoder leftEncoder = leftLeader.getEncoder();
  private final RelativeEncoder rightEncoder = rightLeader.getEncoder();

  leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
  rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
  

  
  @Override
  public Command arcadeDrive(){
    return ;
  }
  @Override
  public Command driveDistance(){
    return ;
  }

}


//First, inspires by https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/rapidreactcommandbot/subsystems/Drive.java