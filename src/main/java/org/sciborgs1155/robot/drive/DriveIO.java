package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;

public interface DriveIO {
    void setLVoltage(double voltage);
    void setRVoltage(double voltage);
    double getLDistanceTraveled();
    double getRDistanceTraveled();
    double getRVelocity();
    double getLVelocity();
    void update();
}
