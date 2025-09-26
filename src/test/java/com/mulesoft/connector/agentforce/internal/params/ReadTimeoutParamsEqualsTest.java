package com.mulesoft.connector.agentforce.internal.params;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for equals() and hashCode() methods in ReadTimeoutParams class using parameterized constructor
 */
class ReadTimeoutParamsEqualsTest {

  // ==================== Equals Tests ====================

  @Test
  void testEquals_SameInstance_ShouldReturnTrue() {
    // Arrange
    ReadTimeoutParams params = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params.equals(params), "Same instance should be equal to itself");
  }

  @Test
  void testEquals_SameValues_ShouldReturnTrue() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with same values should be equal");
    assertTrue(params2.equals(params1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentReadTimeout_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(60, TimeUnit.SECONDS);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with different readTimeout should not be equal");
    assertFalse(params2.equals(params1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentReadTimeoutUnit_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.MINUTES);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with different readTimeoutUnit should not be equal");
    assertFalse(params2.equals(params1), "Equals should be symmetric");
  }

  @Test
  void testEquals_BothValuesDifferent_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(60, TimeUnit.MINUTES);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with both values different should not be equal");
  }

  @Test
  void testEquals_NullValues_ShouldReturnTrue() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(null, null);
    ReadTimeoutParams params2 = new ReadTimeoutParams(null, null);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with null values should be equal");
  }

  @Test
  void testEquals_OneNullReadTimeout_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(null, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with one null readTimeout should not be equal");
    assertFalse(params2.equals(params1), "Equals should be symmetric");
  }

  @Test
  void testEquals_OneNullReadTimeoutUnit_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, null);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with one null readTimeoutUnit should not be equal");
    assertFalse(params2.equals(params1), "Equals should be symmetric");
  }

  @Test
  void testEquals_NullObject_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertFalse(params.equals(null), "ReadTimeoutParams should not be equal to null");
  }

  @Test
  void testEquals_DifferentClass_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    String differentClass = "not a ReadTimeoutParams";

    // Act & Assert
    assertFalse(params.equals(differentClass), "ReadTimeoutParams should not be equal to different class");
  }

  @Test
  void testEquals_Transitivity() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params3 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "params1 should equal params2");
    assertTrue(params2.equals(params3), "params2 should equal params3");
    assertTrue(params1.equals(params3), "params1 should equal params3 (transitivity)");
  }

  // ==================== HashCode Tests ====================

  @Test
  void testHashCode_SameValues_ShouldReturnSameHashCode() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with same values should have same hashCode");
  }

  @Test
  void testHashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(60, TimeUnit.MINUTES);

    // Act & Assert
    assertNotEquals(params1.hashCode(), params2.hashCode(),
                    "ReadTimeoutParams with different values should have different hashCode");
  }

  @Test
  void testHashCode_NullValues_ShouldReturnSameHashCode() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(null, null);
    ReadTimeoutParams params2 = new ReadTimeoutParams(null, null);

    // Act & Assert
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with null values should have same hashCode");
  }

  // ==================== TimeUnit Tests ====================

  @Test
  void testEquals_DifferentTimeUnits_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.MINUTES);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with different TimeUnit should not be equal");
    assertNotEquals(params1.hashCode(), params2.hashCode(),
                    "ReadTimeoutParams with different TimeUnit should have different hashCode");
  }

  @Test
  void testEquals_SameTimeUnits_ShouldReturnTrue() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with same TimeUnit should be equal");
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with same TimeUnit should have same hashCode");
  }

  // ==================== Timeout Value Tests ====================

  @Test
  void testEquals_DifferentTimeoutValues_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(60, TimeUnit.SECONDS);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with different timeout values should not be equal");
    assertNotEquals(params1.hashCode(), params2.hashCode(),
                    "ReadTimeoutParams with different timeout values should have different hashCode");
  }

  @Test
  void testEquals_SameTimeoutValues_ShouldReturnTrue() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with same timeout values should be equal");
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with same timeout values should have same hashCode");
  }

  // ==================== Edge Cases ====================

  @Test
  void testEquals_ZeroTimeout_ShouldWorkCorrectly() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(0, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(0, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with zero timeout should be equal");
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with zero timeout should have same hashCode");
  }

  @Test
  void testEquals_NegativeTimeout_ShouldWorkCorrectly() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(-1, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(-1, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with negative timeout should be equal");
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with negative timeout should have same hashCode");
  }

  @Test
  void testEquals_LargeTimeout_ShouldWorkCorrectly() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(Integer.MAX_VALUE, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(Integer.MAX_VALUE, TimeUnit.SECONDS);

    // Act & Assert
    assertTrue(params1.equals(params2), "ReadTimeoutParams with large timeout should be equal");
    assertEquals(params1.hashCode(), params2.hashCode(), "ReadTimeoutParams with large timeout should have same hashCode");
  }

  @Test
  void testEquals_MixedNullAndNonNull_ShouldReturnFalse() {
    // Arrange
    ReadTimeoutParams params1 = new ReadTimeoutParams(null, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, null);

    // Act & Assert
    assertFalse(params1.equals(params2), "ReadTimeoutParams with mixed null and non-null values should not be equal");
    assertFalse(params2.equals(params1), "Equals should be symmetric");
  }

  // ==================== Additional TimeUnit Combinations ====================

  @Test
  void testEquals_AllTimeUnits_ShouldWorkCorrectly() {
    // Test various TimeUnit combinations
    TimeUnit[] timeUnits = {TimeUnit.NANOSECONDS, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS,
        TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS};

    for (TimeUnit unit : timeUnits) {
      ReadTimeoutParams params1 = new ReadTimeoutParams(30, unit);
      ReadTimeoutParams params2 = new ReadTimeoutParams(30, unit);

      assertTrue(params1.equals(params2), "ReadTimeoutParams with same TimeUnit " + unit + " should be equal");
      assertEquals(params1.hashCode(), params2.hashCode(),
                   "ReadTimeoutParams with same TimeUnit " + unit + " should have same hashCode");
    }
  }

  @Test
  void testEquals_MultipleDifferentTimeUnits_ShouldReturnFalse() {
    // Test different TimeUnit combinations
    ReadTimeoutParams params1 = new ReadTimeoutParams(30, TimeUnit.SECONDS);
    ReadTimeoutParams params2 = new ReadTimeoutParams(30, TimeUnit.MINUTES);
    ReadTimeoutParams params3 = new ReadTimeoutParams(30, TimeUnit.HOURS);
    ReadTimeoutParams params4 = new ReadTimeoutParams(30, TimeUnit.DAYS);

    assertFalse(params1.equals(params2), "SECONDS should not equal MINUTES");
    assertFalse(params1.equals(params3), "SECONDS should not equal HOURS");
    assertFalse(params1.equals(params4), "SECONDS should not equal DAYS");
    assertFalse(params2.equals(params3), "MINUTES should not equal HOURS");
  }

  @Test
  void testEquals_VariousTimeoutValues_ShouldWorkCorrectly() {
    // Test various timeout values
    Integer[] timeouts = {null, 0, 1, 30, 60, 3600, Integer.MAX_VALUE, Integer.MIN_VALUE, -1};

    for (Integer timeout : timeouts) {
      ReadTimeoutParams params1 = new ReadTimeoutParams(timeout, TimeUnit.SECONDS);
      ReadTimeoutParams params2 = new ReadTimeoutParams(timeout, TimeUnit.SECONDS);

      assertTrue(params1.equals(params2), "ReadTimeoutParams with timeout " + timeout + " should be equal");
      assertEquals(params1.hashCode(), params2.hashCode(),
                   "ReadTimeoutParams with timeout " + timeout + " should have same hashCode");
    }
  }
}
