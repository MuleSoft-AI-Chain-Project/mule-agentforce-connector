package com.mulesoft.connector.agentforce.internal.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for equals() and hashCode() methods in DefaultHttpProxyConfig class
 */
class DefaultHttpProxyConfigEqualsTest {

  // ==================== Helper Methods ====================

  private DefaultHttpProxyConfig createProxyConfig(String host, int port, String username, String password,
                                                   String nonProxyHosts) {
    DefaultHttpProxyConfig config = new DefaultHttpProxyConfig();
    // Use reflection to set private fields since there are no setters
    try {
      java.lang.reflect.Field hostField = DefaultHttpProxyConfig.class.getDeclaredField("host");
      hostField.setAccessible(true);
      hostField.set(config, host);

      java.lang.reflect.Field portField = DefaultHttpProxyConfig.class.getDeclaredField("port");
      portField.setAccessible(true);
      portField.set(config, port);

      java.lang.reflect.Field usernameField = DefaultHttpProxyConfig.class.getDeclaredField("username");
      usernameField.setAccessible(true);
      usernameField.set(config, username);

      java.lang.reflect.Field passwordField = DefaultHttpProxyConfig.class.getDeclaredField("password");
      passwordField.setAccessible(true);
      passwordField.set(config, password);

      java.lang.reflect.Field nonProxyHostsField = DefaultHttpProxyConfig.class.getDeclaredField("nonProxyHosts");
      nonProxyHostsField.setAccessible(true);
      nonProxyHostsField.set(config, nonProxyHosts);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create DefaultHttpProxyConfig", e);
    }
    return config;
  }

  // ==================== Equals Tests ====================

  @Test
  void testEquals_SameInstance_ShouldReturnTrue() {
    // Arrange
    DefaultHttpProxyConfig config = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertTrue(config.equals(config), "Same instance should be equal to itself");
  }

  @Test
  void testEquals_SameValues_ShouldReturnTrue() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertTrue(config1.equals(config2), "DefaultHttpProxyConfig with same values should be equal");
    assertTrue(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentHost_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy1.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy2.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with different host should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentPort_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 9090, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with different port should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentUsername_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user1", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user2", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with different username should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentPassword_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass1", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass2", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with different password should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_DifferentNonProxyHosts_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "*.example.com");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with different nonProxyHosts should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_AllFieldsDifferent_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy1.example.com", 8080, "user1", "pass1", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy2.example.com", 9090, "user2", "pass2", "*.example.com");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with all different fields should not be equal");
  }

  @Test
  void testEquals_NullFields_ShouldReturnTrue() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig(null, 8080, null, null, null);
    DefaultHttpProxyConfig config2 = createProxyConfig(null, 8080, null, null, null);

    // Act & Assert
    assertTrue(config1.equals(config2), "DefaultHttpProxyConfig with null fields should be equal");
  }

  @Test
  void testEquals_OneNullHost_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig(null, 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with one null host should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_OneNullUsername_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, null, "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with one null username should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_OneNullPassword_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", null, "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with one null password should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_OneNullNonProxyHosts_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", null);
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with one null nonProxyHosts should not be equal");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }

  @Test
  void testEquals_NullObject_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertFalse(config.equals(null), "DefaultHttpProxyConfig should not be equal to null");
  }

  @Test
  void testEquals_DifferentClass_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    String differentClass = "not a DefaultHttpProxyConfig";

    // Act & Assert
    assertFalse(config.equals(differentClass), "DefaultHttpProxyConfig should not be equal to different class");
  }

  @Test
  void testEquals_Transitivity() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config3 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertTrue(config1.equals(config2), "config1 should equal config2");
    assertTrue(config2.equals(config3), "config2 should equal config3");
    assertTrue(config1.equals(config3), "config1 should equal config3 (transitivity)");
  }

  // ==================== HashCode Tests ====================

  @Test
  void testHashCode_SameValues_ShouldReturnSameHashCode() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 8080, "user", "pass", "localhost");

    // Act & Assert
    assertEquals(config1.hashCode(), config2.hashCode(), "DefaultHttpProxyConfig with same values should have same hashCode");
  }

  @Test
  void testHashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy1.example.com", 8080, "user1", "pass1", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy2.example.com", 9090, "user2", "pass2", "*.example.com");

    // Act & Assert
    assertNotEquals(config1.hashCode(), config2.hashCode(),
                    "DefaultHttpProxyConfig with different values should have different hashCode");
  }

  @Test
  void testHashCode_NullFields_ShouldReturnSameHashCode() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig(null, 8080, null, null, null);
    DefaultHttpProxyConfig config2 = createProxyConfig(null, 8080, null, null, null);

    // Act & Assert
    assertEquals(config1.hashCode(), config2.hashCode(), "DefaultHttpProxyConfig with null fields should have same hashCode");
  }

  // ==================== Edge Cases ====================

  @Test
  void testEquals_DefaultPortValue_ShouldWorkCorrectly() {
    // Arrange - Test with the default port value (2147483647)
    DefaultHttpProxyConfig config1 = createProxyConfig("proxy.example.com", 2147483647, "user", "pass", "localhost");
    DefaultHttpProxyConfig config2 = createProxyConfig("proxy.example.com", 2147483647, "user", "pass", "localhost");

    // Act & Assert
    assertTrue(config1.equals(config2), "DefaultHttpProxyConfig with default port value should be equal");
    assertEquals(config1.hashCode(), config2.hashCode(),
                 "DefaultHttpProxyConfig with default port value should have same hashCode");
  }

  @Test
  void testEquals_EmptyStrings_ShouldWorkCorrectly() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("", 8080, "", "", "");
    DefaultHttpProxyConfig config2 = createProxyConfig("", 8080, "", "", "");

    // Act & Assert
    assertTrue(config1.equals(config2), "DefaultHttpProxyConfig with empty strings should be equal");
    assertEquals(config1.hashCode(), config2.hashCode(), "DefaultHttpProxyConfig with empty strings should have same hashCode");
  }

  @Test
  void testEquals_EmptyStringVsNull_ShouldReturnFalse() {
    // Arrange
    DefaultHttpProxyConfig config1 = createProxyConfig("", 8080, "", "", "");
    DefaultHttpProxyConfig config2 = createProxyConfig(null, 8080, null, null, null);

    // Act & Assert
    assertFalse(config1.equals(config2), "DefaultHttpProxyConfig with empty strings should not equal null fields");
    assertFalse(config2.equals(config1), "Equals should be symmetric");
  }
}
