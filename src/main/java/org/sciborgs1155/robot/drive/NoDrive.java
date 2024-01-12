package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NoDrive extends SubsystemBase implements DriveIO {
    //do nothing? 
    //You are being asked to drive a certain distance when you can't move.
    //You are being asked to run when you can't move.

    public Command driveDistance(double distance, double speed) {
        return run(()->{}); 
    }

    public Command setSpeeds(double lspeed, double rspeed) {
        return run(()->{});
    }
}
