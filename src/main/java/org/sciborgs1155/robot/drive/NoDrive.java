package org.sciborgs1155.robot.drive;

public class NoDrive implements DriveIO {

    @Override public void setLVoltage(double voltage) {}
    @Override public void setRVoltage(double voltage) {}
    @Override public double getLDistanceTraveled() {return 0.0;}
    @Override public double getRDistanceTraveled() {return 0.0;}
    @Override public double getRVelocity() {return 0;}
    @Override public double getLVelocity() {return 0;}
    @Override public void update() {}
    
    //do nothing? 
    //You are being asked to drive a certain distance when you can't move.
    //You are being asked to run when you can't move.

}
