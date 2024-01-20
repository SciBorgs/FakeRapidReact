package org.sciborgs1155.robot;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sciborgs1155.lib.TestingUtil.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sciborgs1155.robot.shooter.Shooter;
import org.sciborgs1155.robot.shooter.ShooterConstants;

public class TestShooter {
  Shooter shooter;
  double DELTA = 5e-1;

  @BeforeEach
  public void
      executeMethodPreliminaryToAnyAndAllRelevantTestMethodsWithTheAimOfNotOnlySettingUpHalAsAPreresiquiteToTestingButAlsoResettingTheShooterInstanceAsToPreventStatespaceBetweenTestsInterferingWithAndConfoundingExperimentalData() {
    setupHAL();
    shooter = new Shooter();
  }

  @Test
  public void testVelocity() {
    run(shooter.shoot());
    shooter.periodic();
    fastForward();
    assertEquals(ShooterConstants.CONSTANT_TARGET_RPS, shooter.getVelocity(), DELTA);
  }
}
