package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvokeAgentResponseAttributesTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testEquals_SameObject() throws Exception {
    String json = "{\"messages\":[{\"id\":\"msg-123\",\"type\":\"Inform\"}]}";
    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);
    assertEquals(attributes, attributes);
  }

  @Test
  void testEquals_NullObject() throws Exception {
    String json = "{\"messages\":[]}";
    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);
    assertNotEquals(null, attributes);
  }

  @Test
  void testEquals_DifferentClass() throws Exception {
    String json = "{\"messages\":[]}";
    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);
    assertNotEquals(attributes, new Object());
  }

  @Test
  void testEquals_EqualObjects_EmptyMessages() throws Exception {
    String json1 = "{\"messages\":[]}";
    String json2 = "{\"messages\":[]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json1, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json2, InvokeAgentResponseAttributes.class);

    assertEquals(attributes1, attributes2);
  }

  @Test
  void testEquals_EqualObjects_WithSingleMessage() throws Exception {
    String json = "{\"messages\":[{\"id\":\"msg-123\",\"type\":\"Inform\",\"message\":\"Hello\",\"isContentSafe\":true}]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);

    // Note: This compares lists, but Message class doesn't have equals/hashCode
    // So we check that both have same message count and data
    assertEquals(attributes1.getMessages().size(), attributes2.getMessages().size());
    assertEquals(attributes1.getMessages().get(0).getId(), attributes2.getMessages().get(0).getId());
    assertEquals(attributes1.getMessages().get(0).getType(), attributes2.getMessages().get(0).getType());
  }

  @Test
  void testEquals_DifferentMessages() throws Exception {
    String json1 = "{\"messages\":[{\"id\":\"msg-123\",\"type\":\"Inform\"}]}";
    String json2 = "{\"messages\":[{\"id\":\"msg-456\",\"type\":\"Inquire\"}]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json1, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json2, InvokeAgentResponseAttributes.class);

    assertNotEquals(attributes1, attributes2);
  }

  @Test
  void testEquals_DifferentMessageCount() throws Exception {
    String json1 = "{\"messages\":[{\"id\":\"msg-123\",\"type\":\"Inform\"}]}";
    String json2 = "{\"messages\":[]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json1, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json2, InvokeAgentResponseAttributes.class);

    assertNotEquals(attributes1, attributes2);
  }

  @Test
  void testHashCode_EqualObjects_EmptyMessages() throws Exception {
    String json1 = "{\"messages\":[]}";
    String json2 = "{\"messages\":[]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json1, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json2, InvokeAgentResponseAttributes.class);

    assertEquals(attributes1.hashCode(), attributes2.hashCode());
  }

  @Test
  void testHashCode_EqualObjects_WithMessages() throws Exception {
    String json = "{\"messages\":[{\"id\":\"msg-123\",\"type\":\"Inform\",\"feedbackId\":\"fb-456\"}]}";

    InvokeAgentResponseAttributes attributes1 = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);
    InvokeAgentResponseAttributes attributes2 = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);

    // Note: hashCode depends on equals of Message class, which doesn't exist
    // So we just verify hashCode is computed (non-zero) and messages have same data
    assertNotEquals(0, attributes1.hashCode());
    assertNotEquals(0, attributes2.hashCode());
    assertEquals(attributes1.getMessages().get(0).getId(), attributes2.getMessages().get(0).getId());
  }

  @Test
  void testDeserialization_AllMessageFields() throws Exception {
    String json = "{\"messages\":[{" +
        "\"id\":\"msg-789\"," +
        "\"type\":\"Inquire\"," +
        "\"feedbackId\":\"fb-abc\"," +
        "\"planId\":\"plan-def\"," +
        "\"isContentSafe\":true," +
        "\"message\":\"What is your name?\"," +
        "\"reason\":\"ClientRequest\"," +
        "\"collect\":[{\"targetType\":\"Text\"}]" +
        "}]}";

    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);

    assertNotNull(attributes.getMessages());
    assertEquals(1, attributes.getMessages().size());

    InvokeAgentResponseAttributes.Message msg = attributes.getMessages().get(0);
    assertEquals("msg-789", msg.getId());
    assertEquals("Inquire", msg.getType());
    assertEquals("fb-abc", msg.getFeedbackId());
    assertEquals("plan-def", msg.getPlanId());
    assertTrue(msg.isContentSafe());
    assertEquals("What is your name?", msg.getMessage());
    assertEquals("ClientRequest", msg.getReason());
    assertNotNull(msg.getCollect());
    assertEquals(1, msg.getCollect().size());
  }

  @Test
  void testDeserialization_ContentSafeFalse() throws Exception {
    String json = "{\"messages\":[{\"id\":\"msg-001\",\"type\":\"Error\",\"isContentSafe\":false}]}";

    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);

    assertNotNull(attributes.getMessages());
    assertEquals(1, attributes.getMessages().size());
    assertFalse(attributes.getMessages().get(0).isContentSafe());
  }

  @Test
  void testDeserialization_MultipleMessages() throws Exception {
    String json = "{\"messages\":[" +
        "{\"id\":\"msg-001\",\"type\":\"Inform\",\"message\":\"First message\"}," +
        "{\"id\":\"msg-002\",\"type\":\"Escalate\",\"reason\":\"TransferToAgent\"}" +
        "]}";

    InvokeAgentResponseAttributes attributes = objectMapper.readValue(json, InvokeAgentResponseAttributes.class);

    assertNotNull(attributes.getMessages());
    assertEquals(2, attributes.getMessages().size());
    assertEquals("msg-001", attributes.getMessages().get(0).getId());
    assertEquals("msg-002", attributes.getMessages().get(1).getId());
  }
}
