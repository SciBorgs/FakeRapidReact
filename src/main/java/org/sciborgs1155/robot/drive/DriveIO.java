package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;

public interface DriveIO {
    void setVoltages(double leftVoltage, double rightVoltage);
    double getLDistanceTraveled();
    double getRDistanceTraveled();
    double getRVelocity();
    double getLVelocity();
    void update();
}
