package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class NoDrive implements DriveIO {
    @Override
    public Command arcadeDrive() {
        return Commands.run(() -> {});
    }
    @Override
    public Command driveDistance() {
        return Commands.run(() -> {});
    }
}
