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

public class Drive extends SubsystemBase implements Logged {
  @LogBoth
  private final PIDController lpid = new PIDController(DriveConstants.kp, DriveConstants.ki, DriveConstants.kd);
  @LogBoth
  private final PIDController rpid = new PIDController(DriveConstants.kp, DriveConstants.ki, DriveConstants.kd);

  private final SimpleMotorFeedforward lfeedforward = new SimpleMotorFeedforward(DriveConstants.kSVolts, DriveConstants.kVVoltSecondsPerRotation);
  private final SimpleMotorFeedforward rfeedforward = new SimpleMotorFeedforward(DriveConstants.kSVolts, DriveConstants.kVVoltSecondsPerRotation);

  @LogBoth
  private final Field2d field = new Field2d();

  private final DriveIO drive;
  public Drive(DriveIO driverIO) {
    drive = driverIO;
  }
  public static Drive create() {
    return Robot.isReal() ? new Drive(new RealDrive()) : new Drive(new SimDrive()); // see if you are real
  }

  public boolean isAtGoal() {
    return lpid.atSetpoint() && rpid.atSetpoint();
  }
  
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
      SmartDashboard.putNumber("L V", drive.getLVelocity());
      SmartDashboard.putNumber("R V", drive.getRVelocity());
    }

  }