package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;

public interface DriveIO extends AutoCloseable {
    void setVoltages(double leftVoltage, double rightVoltage);
    Pose2d getPose();
    double getRVelocity();
    double getLVelocity();
    void update();
}
