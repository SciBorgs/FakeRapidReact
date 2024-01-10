package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;

public interface DriveIO {
    Command arcadeDrive();
    Command driveDistance();
}
