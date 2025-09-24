package com.mulesoft.connector.agentforce.internal.operation;

import com.mulesoft.connector.agentforce.api.metadata.Collect;
import com.mulesoft.connector.agentforce.api.metadata.InvokeAgentResponseAttributes;
import com.mulesoft.connector.agentforce.internal.botapi.helpers.BotRequestHelper;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import com.mulesoft.connector.agentforce.internal.params.ReadTimeoutParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgentforceBotOperationsTest {

  @Mock
  private AgentforceConnection connection;

  @Mock
  private BotRequestHelper botRequestHelper;

  @Mock
  private CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback;

  private AgentforceBotOperations operations;

  @BeforeEach
  void setUp() {
    operations = new AgentforceBotOperations();
    when(connection.getBotRequestHelper()).thenReturn(botRequestHelper);
  }

  @Test
  void testContinueConversation_WithValidResponse_ShouldReturnExpectedJsonStructure() throws Exception {
    // Arrange
    String message = "恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？";
    String sessionId = "c7765dd2-1ec1-4431-a507-13eb705fa01c";
    int messageSequenceNumber = 1;
    ReadTimeoutParams readTimeout = new ReadTimeoutParams();

    // Mock the BotRequestHelper to call the callback with success
    doAnswer(invocation -> {
      CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback = invocation.getArgument(4);

      // Create the expected response structure
      String jsonResponse = "{\n" +
          "  \"messages\": [\n" +
          "    {\n" +
          "      \"type\": \"Inquire\",\n" +
          "      \"id\": \"6109116f-f841-43ed-b86b-a6755a3c6039\",\n" +
          "      \"metrics\": {},\n" +
          "      \"feedbackId\": \"ab28251f-3afc-4df4-bca3-6cbe1ae2e069\",\n" +
          "      \"planId\": \"ab28251f-3afc-4df4-bca3-6cbe1ae2e069\",\n" +
          "      \"isContentSafe\": true,\n" +
          "      \"message\": \"恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？\",\n"
          +
          "      \"collect\": [\n" +
          "        {\n" +
          "          \"targetType\": \"lightning__recordInfoType\",\n" +
          "          \"targetProperty\": \"\",\n" +
          "          \"data\": {\n" +
          "            \"type\": \"copilotActionOutput/EmployeeCopilot__IdentifyRecordByName\",\n" +
          "            \"property\": \"searchResults\",\n" +
          "            \"value\": [\n" +
          "              {\n" +
          "                \"id\": \"001IT00002z3kr7YAA\",\n" +
          "                \"sObjectInfo\": {\n" +
          "                  \"apiName\": \"Account\",\n" +
          "                  \"label\": \"顧客\"\n" +
          "                },\n" +
          "                \"recordTypeId\": \"012000000000000AAA\",\n" +
          "                \"title\": \"\",\n" +
          "                \"data\": {\n" +
          "                  \"Type\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"Account\"\n" +
          "                  },\n" +
          "                  \"Id\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"001IT00002z3kr7YAA\"\n" +
          "                  },\n" +
          "                  \"Name\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"※恵比寿株式会社\"\n" +
          "                  }\n" +
          "                }\n" +
          "              },\n" +
          "              {\n" +
          "                \"id\": \"006IT00000oXIMOYA4\",\n" +
          "                \"sObjectInfo\": {\n" +
          "                  \"apiName\": \"Opportunity\",\n" +
          "                  \"label\": \"商談\"\n" +
          "                },\n" +
          "                \"recordTypeId\": \"012000000000000AAA\",\n" +
          "                \"title\": \"\",\n" +
          "                \"data\": {\n" +
          "                  \"Type\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"Opportunity\"\n" +
          "                  },\n" +
          "                  \"Id\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"006IT00000oXIMOYA4\"\n" +
          "                  },\n" +
          "                  \"Name\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"恵比寿株式会社 タブレット導入 200台\"\n" +
          "                  }\n" +
          "                }\n" +
          "              }\n" +
          "            ]\n" +
          "          }\n" +
          "        }\n" +
          "      ]\n" +
          "    }\n" +
          "  ],\n" +
          "  \"_links\": {\n" +
          "    \"self\": null,\n" +
          "    \"messages\": {\n" +
          "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/c7765dd2-1ec1-4431-a507-13eb705fa01c/messages\"\n"
          +
          "    },\n" +
          "    \"messagesStream\": {\n" +
          "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/c7765dd2-1ec1-4431-a507-13eb705fa01c/messages/stream\"\n"
          +
          "    },\n" +
          "    \"session\": {\n" +
          "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/agents/0XxIT00000002X20AI/sessions\"\n" +
          "    },\n" +
          "    \"end\": {\n" +
          "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/c7765dd2-1ec1-4431-a507-13eb705fa01c\"\n" +
          "    }\n" +
          "  }\n" +
          "}";

      InputStream responseStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));

      // Create a mock result with the expected structure
      Result<InputStream, InvokeAgentResponseAttributes> result = Result.<InputStream, InvokeAgentResponseAttributes>builder()
          .output(responseStream)
          .attributes(createMockResponseAttributes())
          .build();

      callback.success(result);
      return null;
    }).when(botRequestHelper).continueSession(any(InputStream.class), anyString(), anyInt(), any(ReadTimeoutParams.class),
                                              any(CompletionCallback.class));

    // Act
    InputStream messageStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
    operations.continueConversation(connection, messageStream, sessionId, messageSequenceNumber, readTimeout, callback);

    // Assert
    verify(botRequestHelper, times(1)).continueSession(any(InputStream.class), eq(sessionId), eq(messageSequenceNumber),
                                                       any(ReadTimeoutParams.class), any(CompletionCallback.class));
    verify(callback, times(1)).success(any(Result.class));
    verify(callback, never()).error(any(Exception.class));
  }

  @Test
  void testContinueConversation_WithError_ShouldCallErrorCallback() throws Exception {
    // Arrange
    String message = "Test message";
    String sessionId = "test-session-id";
    int messageSequenceNumber = 1;
    ReadTimeoutParams readTimeout = new ReadTimeoutParams();

    // Mock the BotRequestHelper to throw an exception
    doThrow(new RuntimeException("Connection error")).when(botRequestHelper)
        .continueSession(any(InputStream.class), anyString(), anyInt(), any(ReadTimeoutParams.class),
                         any(CompletionCallback.class));

    // Act
    InputStream messageStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
    operations.continueConversation(connection, messageStream, sessionId, messageSequenceNumber, readTimeout, callback);

    // Assert
    verify(botRequestHelper, times(1)).continueSession(any(InputStream.class), eq(sessionId), eq(messageSequenceNumber),
                                                       any(ReadTimeoutParams.class), any(CompletionCallback.class));
    verify(callback, times(1)).error(any(Exception.class));
    verify(callback, never()).success(any(Result.class));
  }

  private InvokeAgentResponseAttributes createMockResponseAttributes() {
    try {
      String jsonResponse = "{\n" +
          "  \"messages\": [\n" +
          "    {\n" +
          "      \"type\": \"Inquire\",\n" +
          "      \"id\": \"6109116f-f841-43ed-b86b-a6755a3c6039\",\n" +
          "      \"metrics\": {},\n" +
          "      \"feedbackId\": \"ab28251f-3afc-4df4-bca3-6cbe1ae2e069\",\n" +
          "      \"planId\": \"ab28251f-3afc-4df4-bca3-6cbe1ae2e069\",\n" +
          "      \"isContentSafe\": true,\n" +
          "      \"message\": \"恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？\",\n"
          +
          "      \"collect\": [\n" +
          "        {\n" +
          "          \"targetType\": \"lightning__recordInfoType\",\n" +
          "          \"targetProperty\": \"\",\n" +
          "          \"data\": {\n" +
          "            \"type\": \"copilotActionOutput/EmployeeCopilot__IdentifyRecordByName\",\n" +
          "            \"property\": \"searchResults\",\n" +
          "            \"value\": [\n" +
          "              {\n" +
          "                \"id\": \"001IT00002z3kr7YAA\",\n" +
          "                \"sObjectInfo\": {\n" +
          "                  \"apiName\": \"Account\",\n" +
          "                  \"label\": \"顧客\"\n" +
          "                },\n" +
          "                \"recordTypeId\": \"012000000000000AAA\",\n" +
          "                \"title\": \"\",\n" +
          "                \"data\": {\n" +
          "                  \"Type\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"Account\"\n" +
          "                  },\n" +
          "                  \"Id\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"001IT00002z3kr7YAA\"\n" +
          "                  },\n" +
          "                  \"Name\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"※恵比寿株式会社\"\n" +
          "                  }\n" +
          "                }\n" +
          "              },\n" +
          "              {\n" +
          "                \"id\": \"006IT00000oXIMOYA4\",\n" +
          "                \"sObjectInfo\": {\n" +
          "                  \"apiName\": \"Opportunity\",\n" +
          "                  \"label\": \"商談\"\n" +
          "                },\n" +
          "                \"recordTypeId\": \"012000000000000AAA\",\n" +
          "                \"title\": \"\",\n" +
          "                \"data\": {\n" +
          "                  \"Type\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"Opportunity\"\n" +
          "                  },\n" +
          "                  \"Id\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"006IT00000oXIMOYA4\"\n" +
          "                  },\n" +
          "                  \"Name\": {\n" +
          "                    \"displayValue\": \"\",\n" +
          "                    \"value\": \"恵比寿株式会社 タブレット導入 200台\"\n" +
          "                  }\n" +
          "                }\n" +
          "              }\n" +
          "            ]\n" +
          "          }\n" +
          "        }\n" +
          "      ]\n" +
          "    }\n" +
          "  ]\n" +
          "}";

      com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
      return objectMapper.readValue(jsonResponse, InvokeAgentResponseAttributes.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create mock response attributes", e);
    }
  }
}
