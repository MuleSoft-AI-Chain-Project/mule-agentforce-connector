package com.mulesoft.connector.agentforce.internal.botapi.helpers;

import com.mulesoft.connector.agentforce.api.metadata.Collect;
import com.mulesoft.connector.agentforce.api.metadata.InvokeAgentResponseAttributes;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BotRequestHelperTest {

  @Mock
  private AgentforceConnection connection;

  private BotRequestHelper botRequestHelper;

  @BeforeEach
  void setUp() {
    botRequestHelper = new BotRequestHelper(connection);
  }

  @Test
  void testParseResponseForContinueSession_WithValidJsonResponse() throws Exception {
    // Arrange
    String jsonResponse = "{\n" +
        "  \"messages\": [\n" +
        "    {\n" +
        "      \"type\": \"Inquire\",\n" +
        "      \"message\": \"恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？\"\n"
        +
        "    }\n" +
        "  ]\n" +
        "}";
    InputStream responseStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));

    // Act
    Result<InputStream, InvokeAgentResponseAttributes> result =
        botRequestHelper.parseResponseForContinueSession(responseStream);

    // Assert
    assertNotNull(result, "Result should not be null");
    assertNotNull(result.getOutput(), "Output stream should not be null");
    assertNotNull(result.getAttributes(), "Attributes should not be null");

    String outputContent = new String(org.apache.commons.io.IOUtils.toByteArray(result.getOutput()), StandardCharsets.UTF_8);
    assertTrue(outputContent.contains("恵比寿株式会社"), "Should contain Japanese company name");
    assertTrue(outputContent.contains("商談"), "Should contain Japanese word for deal");
  }

  @Test
  void testParseResponseForContinueSession_WithMultipleMessages() throws Exception {
    // Arrange
    String jsonResponse = "{\n" +
        "  \"messages\": [\n" +
        "    {\n" +
        "      \"type\": \"Inquire\",\n" +
        "      \"message\": \"First message\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"type\": \"Response\",\n" +
        "      \"message\": \"Second message\"\n" +
        "    }\n" +
        "  ]\n" +
        "}";
    InputStream responseStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));

    // Act
    Result<InputStream, InvokeAgentResponseAttributes> result =
        botRequestHelper.parseResponseForContinueSession(responseStream);

    // Assert
    assertNotNull(result, "Result should not be null");
    String outputContent = new String(org.apache.commons.io.IOUtils.toByteArray(result.getOutput()), StandardCharsets.UTF_8);
    assertEquals("First message Second message", outputContent, "Should concatenate all messages");
  }

  @Test
  void testParseResponseForContinueSession_WithCompleteJsonStructure() throws Exception {
    // Arrange
    String jsonResponse = "{\n" +
        "  \"sessionId\": \"0198f4f8-1d57-716e-9c6d-a774f16169bf\",\n" +
        "  \"_links\": {\n" +
        "    \"self\": null,\n" +
        "    \"messages\": {\n" +
        "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/0198f4f8-1d57-716e-9c6d-a774f16169bf/messages\"\n"
        +
        "    },\n" +
        "    \"messagesStream\": {\n" +
        "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/0198f4f8-1d57-716e-9c6d-a774f16169bf/messages/stream\"\n"
        +
        "    },\n" +
        "    \"session\": {\n" +
        "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/agents/0XxWt00000059vVKAQ/sessions\"\n" +
        "    },\n" +
        "    \"end\": {\n" +
        "      \"href\": \"https://api.salesforce.com/einstein/ai-agent/v1/sessions/0198f4f8-1d57-716e-9c6d-a774f16169bf\"\n" +
        "    }\n" +
        "  },\n" +
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
    InputStream responseStream = new ByteArrayInputStream(jsonResponse.getBytes(StandardCharsets.UTF_8));

    // Act
    Result<InputStream, InvokeAgentResponseAttributes> result =
        botRequestHelper.parseResponseForContinueSession(responseStream);
    System.out.println(result);
    System.out.println(result.getAttributes().get());

    // Assert
    assertNotNull(result, "Result should not be null");
    assertNotNull(result.getOutput(), "Output stream should not be null");
    assertNotNull(result.getAttributes(), "Attributes should not be null");

    String outputContent = new String(org.apache.commons.io.IOUtils.toByteArray(result.getOutput()), StandardCharsets.UTF_8);
    assertEquals("恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？",
                 outputContent, "Should contain the exact Japanese message");

    // Verify that the response attributes contain the collect data and sessionId
    assertTrue(result.getAttributes().isPresent(), "Response attributes should be present");
    InvokeAgentResponseAttributes attributes = result.getAttributes().get();
    assertNotNull(attributes, "Response attributes should not be null");

    // Verify messages are parsed correctly
    assertNotNull(attributes.getMessages(), "Messages should not be null");
    assertEquals(1, attributes.getMessages().size(), "Should have exactly one message");

    InvokeAgentResponseAttributes.Message message = attributes.getMessages().get(0);
    assertEquals("Inquire", message.getType(), "Message type should be Inquire");
    assertEquals("6109116f-f841-43ed-b86b-a6755a3c6039", message.getId(), "Message ID should match");
    assertEquals("恵比寿株式会社に関連する商談が見つかりました。どれを選択しますか？",
                 message.getMessage(), "Message text should match");
    assertTrue(message.isContentSafe(), "true");

    // Verify collect data is parsed correctly
    assertNotNull(message.getCollect(), "Collect data should not be null");
    assertEquals(1, message.getCollect().size(), "Should have exactly one collect item");

    Collect collect = message.getCollect().get(0);
    assertEquals("lightning__recordInfoType", collect.getTargetType(), "Target type should match");
    assertEquals("", collect.getTargetProperty(), "Target property should be empty");

    // Verify collect data structure
    assertNotNull(collect.getData(), "Collect data should not be null");
    assertEquals("copilotActionOutput/EmployeeCopilot__IdentifyRecordByName", collect.getData().getType(),
                 "Data type should match");
    assertEquals("searchResults", collect.getData().getProperty(), "Data property should match");

    // Verify search results
    assertNotNull(collect.getData().getValue(), "Search results should not be null");
    assertEquals(2, collect.getData().getValue().size(), "Should have exactly 2 search results");

    // Verify first search result (Account)
    Collect.SearchResult firstResult = collect.getData().getValue().get(0);
    assertEquals("001IT00002z3kr7YAA", firstResult.getId(), "First result ID should match");
    assertEquals("012000000000000AAA", firstResult.getRecordTypeId(), "Record type ID should match");
    assertEquals("", firstResult.getTitle(), "Title should be empty");

    // Verify SObjectInfo for Account
    assertNotNull(firstResult.getSObjectInfo(), "SObjectInfo should not be null");
    assertEquals("Account", firstResult.getSObjectInfo().getApiName(), "API name should be Account");
    assertEquals("顧客", firstResult.getSObjectInfo().getLabel(), "Label should be 顧客");

    // Verify RecordData for Account
    assertNotNull(firstResult.getData(), "Record data should not be null");
    assertEquals("Account", firstResult.getData().getType().getValue(), "Type value should be Account");
    assertEquals("001IT00002z3kr7YAA", firstResult.getData().getId().getValue(), "ID value should match");
    assertEquals("※恵比寿株式会社", firstResult.getData().getName().getValue(), "Name value should match");

    // Verify second search result (Opportunity)
    Collect.SearchResult secondResult = collect.getData().getValue().get(1);
    assertEquals("006IT00000oXIMOYA4", secondResult.getId(), "Second result ID should match");

    // Verify SObjectInfo for Opportunity
    assertNotNull(secondResult.getSObjectInfo(), "SObjectInfo should not be null");
    assertEquals("Opportunity", secondResult.getSObjectInfo().getApiName(), "API name should be Opportunity");
    assertEquals("商談", secondResult.getSObjectInfo().getLabel(), "Label should be 商談");

    // Verify RecordData for Opportunity
    assertNotNull(secondResult.getData(), "Record data should not be null");
    assertEquals("Opportunity", secondResult.getData().getType().getValue(), "Type value should be Opportunity");
    assertEquals("006IT00000oXIMOYA4", secondResult.getData().getId().getValue(), "ID value should match");
    assertEquals("恵比寿株式会社 タブレット導入 200台", secondResult.getData().getName().getValue(),
                 "Name value should match");
  }
}
