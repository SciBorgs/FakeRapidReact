package org.sciborgs1155.robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sciborgs1155.robot.drive.Drive;
import static org.sciborgs1155.lib.TestingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestDrive {
    Drive drive;
    final double DELTA = 1; 
    
    @BeforeEach
    public void doBefore() {
        setupHAL();
        drive = new Drive();
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        1, 1
        -1, -1
        0, 0
        1, -1
        -1, 1  
    """)
    public void testVelocity(double Lv, double Rv) {
        run(drive.tank(() -> Lv, () -> Rv));
        fastForward(1000); // /tick warp??
        assertEquals(Lv, drive.getLVelocity(), DELTA);
        assertEquals(Rv, drive.getRVelocity(), DELTA);
    }

}
