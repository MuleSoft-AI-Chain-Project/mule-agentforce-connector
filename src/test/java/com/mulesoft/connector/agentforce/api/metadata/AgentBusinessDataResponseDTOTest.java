package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AgentBusinessDataResponseDTOTest {

  @Test
  void testEquals_SameObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Collections.singletonList(msg));
    assertEquals(dto, dto);
  }

  @Test
  void testEquals_NullObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Collections.singletonList(msg));
    assertNotEquals(null, dto);
  }

  @Test
  void testEquals_DifferentClass() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(Collections.singletonList(msg));
    assertNotEquals(new Object(), dto);
  }

  @Test
  void testEquals_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Hello")
        .build();
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Hello")
        .build();
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertEquals(dto1, dto2);
  }

  @Test
  void testEquals_DifferentMessages() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inquire").build();
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testHashCode_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Test")
        .build();
    AgentBusinessDataResponseDTO dto1 = new AgentBusinessDataResponseDTO(Arrays.asList(msg1));

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Test")
        .build();
    AgentBusinessDataResponseDTO dto2 = new AgentBusinessDataResponseDTO(Arrays.asList(msg2));

    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testBusinessDataMessageEquals_SameObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    assertEquals(msg, msg);
  }

  @Test
  void testBusinessDataMessageEquals_NullObject() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    assertNotEquals(null, msg);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentClass() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    assertNotEquals(new Object(), msg);
  }

  @Test
  void testBusinessDataMessageEquals_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Hello")
        .build();

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Hello")
        .build();

    assertEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentType() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform").build();
    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inquire").build();

    assertNotEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageEquals_DifferentMessage() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Hello")
        .build();

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Goodbye")
        .build();

    assertNotEquals(msg1, msg2);
  }

  @Test
  void testBusinessDataMessageHashCode_EqualObjects() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg1 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Test")
        .build();

    AgentBusinessDataResponseDTO.BusinessDataMessage msg2 = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Test")
        .build();

    assertEquals(msg1.hashCode(), msg2.hashCode());
  }

  @Test
  void testBusinessDataMessage_InformFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inform")
        .message("Information message")
        .result(Collections.emptyList())
        .citedReferences(Collections.emptyList())
        .build();

    assertEquals("Inform", msg.getType());
    assertEquals("Information message", msg.getMessage());
    assertNotNull(msg.getResult());
    assertNotNull(msg.getCitedReferences());
  }

  @Test
  void testBusinessDataMessage_InquireFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Inquire")
        .message("Question?")
        .collect(Collections.emptyList())
        .build();

    assertEquals("Inquire", msg.getType());
    assertEquals("Question?", msg.getMessage());
    assertNotNull(msg.getCollect());
  }

  @Test
  void testBusinessDataMessage_ConfirmFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Confirm")
        .confirm(Collections.emptyList())
        .build();

    assertEquals("Confirm", msg.getType());
    assertNotNull(msg.getConfirm());
  }

  @Test
  void testBusinessDataMessage_FailureFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Failure")
        .code("ERROR_CODE")
        .errors(Arrays.asList("Error 1", "Error 2"))
        .build();

    assertEquals("Failure", msg.getType());
    assertEquals("ERROR_CODE", msg.getCode());
    assertNotNull(msg.getErrors());
    assertEquals(2, msg.getErrors().size());
  }

  @Test
  void testBusinessDataMessage_SessionEndedFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("SessionEnded")
            .reason("ClientRequest")
            .build();

    assertEquals("SessionEnded", msg.getType());
    assertEquals("ClientRequest", msg.getReason());
  }

  @Test
  void testBusinessDataMessage_ProgressIndicatorFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("ProgressIndicator")
            .indicatorType("Processing")
            .build();

    assertEquals("ProgressIndicator", msg.getType());
    assertEquals("Processing", msg.getIndicatorType());
  }

  @Test
  void testBusinessDataMessage_EscalateFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Escalate")
            .targets(Collections.emptyList())
            .build();

    assertEquals("Escalate", msg.getType());
    assertNotNull(msg.getTargets());
  }

  @Test
  void testBusinessDataMessage_ErrorFields() {
    AgentBusinessDataResponseDTO.BusinessDataMessage msg = new AgentBusinessDataResponseDTO.BusinessDataMessage.Builder("Error")
        .error("INTERNAL_ERROR")
        .build();

    assertEquals("Error", msg.getType());
    assertEquals("INTERNAL_ERROR", msg.getError());
  }
}
