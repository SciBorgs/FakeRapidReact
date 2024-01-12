package org.sciborgs1155.robot.drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NoDrive extends SubsystemBase implements DriveIO {

    public Command driveDistance(double distance, double speed) {
        return run(()->{}); //do nothing? 
        //You are being asked to drive a certain distance when you can't move.
    }
}
