package org.sciborgs1155.robot.drive;

import edu.wpi.first.math.util.Units;

public class DriveConstants {
    static final int kLeftMotor1Port = 0;
    static final int kLeftMotor2Port = 1;
    static final int[] kLeftEncoderPort = {0,1};
    static final boolean kLeftReversed = false;

    static final int kRightMotor1Port = 2;
    static final int kRightMotor2Port = 3;
    static final int[] kRightEncoderPort = {2,3};
    static final boolean kRightReversed = true;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(6);
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

    public static final double kEncoderVelocityConversionFactor = 1; //no clue what this number should be
}
