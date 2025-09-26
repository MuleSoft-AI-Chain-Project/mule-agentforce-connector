package com.mulesoft.connector.agentforce.internal.connection;

import com.mulesoft.connector.agentforce.internal.botapi.helpers.BotRequestHelper;
import com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mule.runtime.extension.api.connectivity.oauth.ClientCredentialsState;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.http.api.client.HttpClient;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for CustomOAuthClientCredentialsConnection.validate() method
 */
@ExtendWith(MockitoExtension.class)
class CustomOAuthClientCredentialsConnectionTest {

  @Mock
  private ClientCredentialsState clientCredentialsState;

  @Mock
  private HttpClient httpClient;

  @Mock
  private BotRequestHelper botRequestHelper;

  private CustomOAuthClientCredentialsConnection connection;
  private final String validSalesforceOrgUrl = "https://test.salesforce.com";
  private final String validApiInstanceUrl = "https://api.salesforce.com";

  @BeforeEach
  void setUp() {
    // Create connection with valid URLs
    connection = new CustomOAuthClientCredentialsConnection(
                                                            validSalesforceOrgUrl,
                                                            clientCredentialsState,
                                                            validApiInstanceUrl,
                                                            httpClient);
  }

  @Test
  void testValidate_WithValidConfiguration_ShouldSucceed() throws IOException, TimeoutException {
    // Arrange
    // Use reflection to inject the mocked BotRequestHelper
    injectMockedBotRequestHelper();

    // Mock successful getAgentList call
    when(botRequestHelper.getAgentList()).thenReturn(java.util.Collections.emptyList());

    // Act & Assert
    assertDoesNotThrow(() -> connection.validate(),
                       "Validate should succeed with valid configuration");

    // Verify that getAgentList was called
    verify(botRequestHelper, times(1)).getAgentList();
  }

  @Test
  void testValidate_WithNullApiInstanceUrl_ShouldThrowModuleException() {
    // Arrange
    CustomOAuthClientCredentialsConnection connectionWithNullUrl =
        new CustomOAuthClientCredentialsConnection(
                                                   validSalesforceOrgUrl,
                                                   clientCredentialsState,
                                                   null,
                                                   httpClient);

    // Act & Assert
    ModuleException exception = assertThrows(ModuleException.class,
                                             () -> connectionWithNullUrl.validate(),
                                             "Validate should throw ModuleException when apiInstanceUrl is null");

    assertEquals(AgentforceErrorType.INVALID_CONNECTION, exception.getType(),
                 "Exception should have INVALID_CONNECTION error type");
    assertTrue(exception.getMessage().contains("Empty API Instance URL"),
               "Exception message should mention empty API Instance URL");
  }

  @Test
  void testValidate_WithEmptyApiInstanceUrl_ShouldThrowModuleException() {
    // Arrange
    CustomOAuthClientCredentialsConnection connectionWithEmptyUrl =
        new CustomOAuthClientCredentialsConnection(
                                                   validSalesforceOrgUrl,
                                                   clientCredentialsState,
                                                   "",
                                                   httpClient);

    // Act & Assert
    ModuleException exception = assertThrows(ModuleException.class,
                                             () -> connectionWithEmptyUrl.validate(),
                                             "Validate should throw ModuleException when apiInstanceUrl is empty");

    assertEquals(AgentforceErrorType.INVALID_CONNECTION, exception.getType(),
                 "Exception should have INVALID_CONNECTION error type");
    assertTrue(exception.getMessage().contains("Empty API Instance URL"),
               "Exception message should mention empty API Instance URL");
  }

  @Test
  void testValidate_WithWhitespaceOnlyApiInstanceUrl_ShouldThrowModuleException() {
    // Arrange
    CustomOAuthClientCredentialsConnection connectionWithWhitespaceUrl =
        new CustomOAuthClientCredentialsConnection(
                                                   validSalesforceOrgUrl,
                                                   clientCredentialsState,
                                                   "   ",
                                                   httpClient);

    // Act & Assert
    ModuleException exception = assertThrows(ModuleException.class,
                                             () -> connectionWithWhitespaceUrl.validate(),
                                             "Validate should throw ModuleException when apiInstanceUrl is whitespace only");

    assertEquals(AgentforceErrorType.INVALID_CONNECTION, exception.getType(),
                 "Exception should have INVALID_CONNECTION error type");
    assertTrue(exception.getMessage().contains("Empty API Instance URL"),
               "Exception message should mention empty API Instance URL");
  }

  @Test
  void testValidate_WithIOExceptionFromGetAgentList_ShouldThrowModuleException() throws IOException, TimeoutException {
    // Arrange
    injectMockedBotRequestHelper();

    // Mock IOException from getAgentList
    when(botRequestHelper.getAgentList()).thenThrow(new IOException("Network error"));

    // Act & Assert
    ModuleException exception = assertThrows(ModuleException.class,
                                             () -> connection.validate(),
                                             "Validate should throw ModuleException when getAgentList throws IOException");

    assertEquals(AgentforceErrorType.INVALID_CONNECTION, exception.getType(),
                 "Exception should have INVALID_CONNECTION error type");
    assertTrue(exception.getMessage().contains("Unable to validate credentials"),
               "Exception message should mention unable to validate credentials");
    assertNotNull(exception.getCause(),
                  "Exception should have IOException as cause");
    assertTrue(exception.getCause() instanceof IOException,
               "Exception cause should be IOException");
  }

  @Test
  void testValidate_WithTimeoutExceptionFromGetAgentList_ShouldThrowModuleException() throws IOException, TimeoutException {
    // Arrange
    injectMockedBotRequestHelper();

    // Mock TimeoutException from getAgentList
    when(botRequestHelper.getAgentList()).thenThrow(new TimeoutException("Request timeout"));

    // Act & Assert
    ModuleException exception = assertThrows(ModuleException.class,
                                             () -> connection.validate(),
                                             "Validate should throw ModuleException when getAgentList throws TimeoutException");

    assertEquals(AgentforceErrorType.INVALID_CONNECTION, exception.getType(),
                 "Exception should have INVALID_CONNECTION error type");
    assertTrue(exception.getMessage().contains("Unable to validate credentials"),
               "Exception message should mention unable to validate credentials");
    assertNotNull(exception.getCause(),
                  "Exception should have TimeoutException as cause");
    assertTrue(exception.getCause() instanceof TimeoutException,
               "Exception cause should be TimeoutException");
  }

  /**
   * Helper method to inject the mocked BotRequestHelper using reflection
   */
  private void injectMockedBotRequestHelper() {
    try {
      java.lang.reflect.Field botRequestHelperField =
          CustomOAuthClientCredentialsConnection.class.getDeclaredField("botRequestHelper");
      botRequestHelperField.setAccessible(true);
      botRequestHelperField.set(connection, botRequestHelper);
    } catch (Exception e) {
      throw new RuntimeException("Failed to inject mocked BotRequestHelper", e);
    }
  }
}
