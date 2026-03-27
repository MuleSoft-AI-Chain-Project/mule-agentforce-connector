package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AgentBusinessDataResponseDTOTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testEquals_SameObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Arrays.asList(msg));
    assertEquals(dto, dto);
  }

  @Test
  void testEquals_NullObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Arrays.asList(msg));
    assertNotEquals(null, dto);
  }

  @Test
  void testEquals_DifferentClass() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Arrays.asList(msg));
    assertNotEquals(dto, new Object());
  }

  @Test
  void testEquals_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg1.setMessage("Hello");
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg2.setMessage("Hello");
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertEquals(dto1, dto2);
  }

  @Test
  void testEquals_DifferentMessages() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inquire");
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testHashCode_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg1.setMessage("Test");
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg2.setMessage("Test");
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testBusinessDataMessageEquals_SameObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    assertEquals(msg, msg);
  }

  @Test
  void testBusinessDataMessageEquals_NullObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    assertNotEquals(null, msg);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentClass() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    assertNotEquals(msg, new Object());
  }

  @Test
  void testBusinessDataMessageEquals_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg1.setMessage("Hello");

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg2.setMessage("Hello");

    assertEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentType() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inquire");

    assertNotEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentMessage() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg1.setMessage("Hello");

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg2.setMessage("Goodbye");

    assertNotEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageHashCode_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg1.setMessage("Test");

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg2.setMessage("Test");

    assertEquals(msg1.hashCode(), msg2.hashCode());
  }

  @Test
  void testBusinessDataMessage_InformFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    msg.setMessage("Information message");
    msg.setResult(Arrays.asList());
    msg.setCitedReferences(Arrays.asList());

    assertEquals("Inform", msg.getType());
    assertEquals("Information message", msg.getMessage());
    assertNotNull(msg.getResult());
    assertNotNull(msg.getCitedReferences());
  }

  @Test
  void testBusinessDataMessage_InquireFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Inquire");
    msg.setMessage("Question?");
    msg.setCollect(Arrays.asList());

    assertEquals("Inquire", msg.getType());
    assertEquals("Question?", msg.getMessage());
    assertNotNull(msg.getCollect());
  }

  @Test
  void testBusinessDataMessage_ConfirmFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Confirm");
    msg.setConfirm(Arrays.asList());

    assertEquals("Confirm", msg.getType());
    assertNotNull(msg.getConfirm());
  }

  @Test
  void testBusinessDataMessage_FailureFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Failure");
    msg.setCode("ERROR_CODE");
    msg.setErrors(Arrays.asList("Error 1", "Error 2"));

    assertEquals("Failure", msg.getType());
    assertEquals("ERROR_CODE", msg.getCode());
    assertNotNull(msg.getErrors());
    assertEquals(2, msg.getErrors().size());
  }

  @Test
  void testBusinessDataMessage_SessionEndedFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("SessionEnded");
    msg.setReason("ClientRequest");

    assertEquals("SessionEnded", msg.getType());
    assertEquals("ClientRequest", msg.getReason());
  }

  @Test
  void testBusinessDataMessage_ProgressIndicatorFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage("ProgressIndicator");
    msg.setIndicatorType("Processing");

    assertEquals("ProgressIndicator", msg.getType());
    assertEquals("Processing", msg.getIndicatorType());
  }

  @Test
  void testBusinessDataMessage_EscalateFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Escalate");
    msg.setTargets(Arrays.asList());

    assertEquals("Escalate", msg.getType());
    assertNotNull(msg.getTargets());
  }

  @Test
  void testBusinessDataMessage_ErrorFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage("Error");
    msg.setError("INTERNAL_ERROR");

    assertEquals("Error", msg.getType());
    assertEquals("INTERNAL_ERROR", msg.getError());
  }
}
