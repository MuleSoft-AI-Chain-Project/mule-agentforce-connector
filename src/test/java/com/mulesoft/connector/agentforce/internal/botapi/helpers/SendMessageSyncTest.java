package com.mulesoft.connector.agentforce.internal.botapi.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mulesoft.connector.agentforce.api.metadata.AgentBusinessDataResponseDTO;
import com.mulesoft.connector.agentforce.api.metadata.AgentResponseMetadata;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentApiResponseDTO;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SendMessageSyncTest {

  @Mock
  private AgentforceConnection connection;

  private BotRequestHelper botRequestHelper;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    botRequestHelper = new BotRequestHelper(connection);
    objectMapper = new ObjectMapper();
  }

  private InputStream loadMockResponse(String fileName) {
    return getClass().getResourceAsStream("/mock-responses/sendMessageSync/" + fileName);
  }

  private AgentApiResponseDTO loadApiResponse(String fileName) throws Exception {
    InputStream stream = loadMockResponse(fileName);
    return objectMapper.readValue(stream, AgentApiResponseDTO.class);
  }

  @Test
  void testBuildBusinessDataPayload_InformMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("inform-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size(), "Should have 1 message");
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("Inform", msg.getType());
    assertTrue(msg.getMessage().contains("Energy Efficiency Rewards Programme"));
    assertNotNull(msg.getResult(), "Result should not be null");
    assertNotNull(msg.getCitedReferences(), "Cited references should not be null");
    assertEquals(1, msg.getResult().size());
  }

  @Test
  void testBuildMetadata_InformMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("inform-response.json");

    AgentResponseMetadata metadata = botRequestHelper.buildMetadata(apiResponse);

    assertNotNull(metadata, "Metadata should not be null");
    assertEquals(1, metadata.getMessageMetadata().size());
    AgentResponseMetadata.MessageMetadata msgMeta = metadata.getMessageMetadata().get(0);
    assertEquals("Inform", msgMeta.getType());
    assertEquals("f8308c08-5c63-479f-af71-c1e80ccdb166", msgMeta.getFeedbackId());
    assertEquals("f8308c08-5c63-479f-af71-c1e80ccdb166", msgMeta.getPlanId());
    assertTrue(msgMeta.getIsContentSafe());

    assertNotNull(metadata.getLinks());
    assertNotNull(metadata.getLinks().getMessages());
    assertNotNull(metadata.getLinks().getSession());
  }

  @Test
  void testBuildBusinessDataPayload_InquireMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("inquire-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("Inquire", msg.getType());
    assertTrue(msg.getMessage().contains("creating a new account"));
    assertNotNull(msg.getCollect(), "Collect should not be null");
    assertEquals(5, msg.getCollect().size());
    assertEquals("copilotActionInput/CreateAccount_179840627f1c603", msg.getCollect().get(0).getTargetType());
  }

  @Test
  void testBuildBusinessDataPayload_ConfirmMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("confirm-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("Confirm", msg.getType());
    assertTrue(msg.getMessage().contains("Energy Efficiency Rewards Programme"));
    assertNotNull(msg.getConfirm());
    assertEquals(1, msg.getConfirm().size());
  }

  @Test
  void testBuildBusinessDataPayload_FailureMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("failure-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("Failure", msg.getType());
    assertEquals("ACTION_FAILED", msg.getCode());
    assertNotNull(msg.getErrors());
    assertEquals(1, msg.getErrors().size());
    assertTrue(msg.getErrors().get(0) instanceof String);
    assertEquals("Unfortunately a system error occurred. Please try again.", msg.getErrors().get(0));
  }

  @Test
  void testBuildBusinessDataPayload_SessionEndedMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("session-ended-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("SessionEnded", msg.getType());
    assertEquals("ClientRequest", msg.getReason());
  }

  @Test
  void testBuildBusinessDataPayload_ErrorMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("error-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("Error", msg.getType());
    assertEquals("INTERNAL_SERVER_ERROR", msg.getError());
  }

  @Test
  void testBuildMetadata_ErrorMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("error-response.json");

    AgentResponseMetadata metadata = botRequestHelper.buildMetadata(apiResponse);

    AgentResponseMetadata.MessageMetadata msgMeta = metadata.getMessageMetadata().get(0);
    assertEquals(500, msgMeta.getHttpStatus());
    assertEquals(1773998000000L, msgMeta.getTimestamp());
    assertFalse(msgMeta.getExpected());
    assertEquals("trace-123-456-789", msgMeta.getTraceId());
  }

  @Test
  void testBuildBusinessDataPayload_ProgressIndicatorMessage() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("progress-indicator-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(1, payload.getMessages().size());
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = payload.getMessages().get(0);
    assertEquals("ProgressIndicator", msg.getType());
    assertEquals("Processing", msg.getIndicatorType());
  }

  @Test
  void testBuildBusinessDataPayload_MultipleMessages_WithEscalate() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("escalate-response.json");

    AgentBusinessDataResponseDTO payload = botRequestHelper.buildBusinessDataPayload(apiResponse);

    assertEquals(2, payload.getMessages().size(), "Should have 2 messages");
    assertEquals("Inform", payload.getMessages().get(0).getType());
    assertEquals("Escalate", payload.getMessages().get(1).getType());

    assertNotNull(payload.getMessages().get(1).getTargets());
    assertEquals(1, payload.getMessages().get(1).getTargets().size());
  }

  @Test
  void testBuildMetadata_MultipleMessages_WithEscalate() throws Exception {
    AgentApiResponseDTO apiResponse = loadApiResponse("escalate-response.json");

    AgentResponseMetadata metadata = botRequestHelper.buildMetadata(apiResponse);

    assertEquals(2, metadata.getMessageMetadata().size(), "Should have 2 message metadata");
    assertEquals("3c907722-710d-403d-9d7a-7d43f07a5183", metadata.getMessageMetadata().get(0).getId());
    assertEquals("a133c185-73a7-4adf-b6d9-b7fd62babb4e", metadata.getMessageMetadata().get(1).getId());
  }
}
