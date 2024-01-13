package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.geometry.Pose2d;

public class NoDrive implements DriveIO {

    @Override public void setVoltages(double leftVoltage, double rightVoltage) {}
    @Override
    public Pose2d getPose() {
        return null;
    }
    @Override public double getRVelocity() {return 0;}
    @Override public double getLVelocity() {return 0;}
    @Override public void update() {}
    
    //do nothing? 
    //You are being asked to drive a certain distance when you can't move.
    //You are being asked to run when you can't move.

}
