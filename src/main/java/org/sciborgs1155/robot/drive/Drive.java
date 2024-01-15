package org.sciborgs1155.robot.drive;

import java.util.function.DoubleSupplier;

import org.sciborgs1155.robot.Robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;
import monologue.Monologue.LogBoth;
import monologue.Monologue.LogFile;

public class Drive extends SubsystemBase implements Logged {
  @LogBoth
  private final PIDController lpid = new PIDController(DriveConstants.kp, DriveConstants.ki, DriveConstants.kd);
  @LogBoth
  private final PIDController rpid = new PIDController(DriveConstants.kp, DriveConstants.ki, DriveConstants.kd);

  private final SimpleMotorFeedforward lfeedforward = new SimpleMotorFeedforward(DriveConstants.kSVolts, DriveConstants.kVVoltSecondsPerRotation);
  private final SimpleMotorFeedforward rfeedforward = new SimpleMotorFeedforward(DriveConstants.kSVolts, DriveConstants.kVVoltSecondsPerRotation);

  @LogBoth
  private final Field2d field = new Field2d();

  @LogFile private final DriveIO drive = Robot.isReal() ? new RealDrive() : new SimDrive();

  @LogBoth
  public boolean isAtGoal() {
    return lpid.atSetpoint() && rpid.atSetpoint();
  }
  
  @LogBoth public double getLVelocity(){return drive.getLVelocity();}
  @LogBoth public double getRVelocity(){return drive.getRVelocity();}
  
  public Command tank(DoubleSupplier l, DoubleSupplier r) {
    return run(() -> 
      drive.setVoltages(
        lpid.calculate(drive.getLVelocity(), l.getAsDouble()) + lfeedforward.calculate(l.getAsDouble())
        ,
        rpid.calculate(drive.getRVelocity(), r.getAsDouble()) + rfeedforward.calculate(r.getAsDouble())
      ));
    }

    @Override
    public void periodic() {
      drive.update(); 
      field.setRobotPose(drive.getPose());
    }
  }