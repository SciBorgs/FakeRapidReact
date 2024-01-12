package org.sciborgs1155.robot.drive;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RealDrive extends SubsystemBase implements DriveIO {
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

  public Command driveDistance(double distance, double speed){
    double leftstart=leftEncoder.getPosition();
    double rightstart=rightEncoder.getPosition();
    return run(()->diffDrive.arcadeDrive(speed, 0))
    .until(()-> Math.max(leftEncoder.getPosition()-leftstart, rightEncoder.getPosition()-rightstart) >= distance)
    .finallyDo(()-> diffDrive.stopMotor());
    //This code has no PID controller, controlled only with distance and speed. 
    //That means that if this robot was a fast moving car, it will drive onto the crosswalk and hit an unsuspecting pedistrian. 
  }

  @Override
  public Command setSpeeds(double lspeed, double rspeed){
    return run(()->diffDrive.tankDrive(lspeed, rspeed));
  }
}


//First, inspires by https://github.com/wpilibsuite/allwpilib/blob/main/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/rapidreactcommandbot/subsystems/Drive.java