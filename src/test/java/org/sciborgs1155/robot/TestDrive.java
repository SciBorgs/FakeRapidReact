package org.sciborgs1155.robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sciborgs1155.robot.drive.Drive;
import static org.sciborgs1155.lib.TestingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestDrive {
    Drive drive;
    final double delta = 0.5;
    
    @BeforeEach
    public void doBefore() {
        setupHAL();
        drive = Drive.create();
    }

    @ParameterizedTest
    @ValueSource(doubles = {1})
    public void testVelocity(double v) {
        run(drive.tank(() -> v, () -> v));
        fastForward(2500);
        assertEquals(drive.sumPositionError(), 0, delta);
    }

}
