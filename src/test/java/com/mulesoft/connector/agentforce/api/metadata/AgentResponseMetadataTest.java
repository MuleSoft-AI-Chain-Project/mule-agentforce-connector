package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentResponseMetadataTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testEquals_SameObject() throws Exception {
    String json = "{\"_links\":{},\"messagesMetadata\":[]}";
    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);
    assertEquals(metadata, metadata);
  }

  @Test
  void testEquals_NullObject() throws Exception {
    String json = "{\"messagesMetadata\":[]}";
    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);
    assertNotEquals(null, metadata);
  }

  @Test
  void testEquals_DifferentClass() throws Exception {
    String json = "{\"messagesMetadata\":[]}";
    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);
    assertNotEquals(metadata, new Object());
  }

  @Test
  void testEquals_EqualObjects_WithData() throws Exception {
    String json = "{\"_links\":{\"session\":{\"href\":\"https://test.com/session\"}}," +
        "\"messagesMetadata\":[{\"id\":\"msg-123\",\"type\":\"Inform\"}]}";

    AgentResponseMetadata metadata1 = objectMapper.readValue(json, AgentResponseMetadata.class);
    AgentResponseMetadata metadata2 = objectMapper.readValue(json, AgentResponseMetadata.class);

    assertEquals(metadata1, metadata2);
  }

  @Test
  void testEquals_DifferentLinks() throws Exception {
    String json1 = "{\"_links\":{\"session\":{\"href\":\"https://test.com/session1\"}},\"messagesMetadata\":[]}";
    String json2 = "{\"_links\":{\"session\":{\"href\":\"https://test.com/session2\"}},\"messagesMetadata\":[]}";

    AgentResponseMetadata metadata1 = objectMapper.readValue(json1, AgentResponseMetadata.class);
    AgentResponseMetadata metadata2 = objectMapper.readValue(json2, AgentResponseMetadata.class);

    assertNotEquals(metadata1, metadata2);
  }

  @Test
  void testEquals_DifferentMessagesMetadata() throws Exception {
    String json1 = "{\"messagesMetadata\":[{\"id\":\"msg-123\"}]}";
    String json2 = "{\"messagesMetadata\":[{\"id\":\"msg-456\"}]}";

    AgentResponseMetadata metadata1 = objectMapper.readValue(json1, AgentResponseMetadata.class);
    AgentResponseMetadata metadata2 = objectMapper.readValue(json2, AgentResponseMetadata.class);

    assertNotEquals(metadata1, metadata2);
  }

  @Test
  void testHashCode_EqualObjects_WithData() throws Exception {
    String json = "{\"_links\":{\"session\":{\"href\":\"https://test.com/session\"}}," +
        "\"messagesMetadata\":[{\"id\":\"msg-123\"}]}";

    AgentResponseMetadata metadata1 = objectMapper.readValue(json, AgentResponseMetadata.class);
    AgentResponseMetadata metadata2 = objectMapper.readValue(json, AgentResponseMetadata.class);

    assertEquals(metadata1.hashCode(), metadata2.hashCode());
  }

  @Test
  void testDeserialization_AllMessageMetadataFields() throws Exception {
    String json = "{\"messagesMetadata\":[{" +
        "\"id\":\"msg-123\"," +
        "\"type\":\"Inform\"," +
        "\"feedbackId\":\"feedback-456\"," +
        "\"planId\":\"plan-789\"," +
        "\"isContentSafe\":true," +
        "\"metrics\":{\"duration\":100}," +
        "\"httpStatus\":200," +
        "\"timestamp\":1234567890," +
        "\"expected\":true," +
        "\"traceId\":\"trace-abc\"" +
        "}]}";

    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);

    assertNotNull(metadata.getMessagesMetadata());
    assertEquals(1, metadata.getMessagesMetadata().size());

    AgentResponseMetadata.MessageMetadata msgMeta = metadata.getMessagesMetadata().get(0);
    assertEquals("msg-123", msgMeta.getId());
    assertEquals("Inform", msgMeta.getType());
    assertEquals("feedback-456", msgMeta.getFeedbackId());
    assertEquals("plan-789", msgMeta.getPlanId());
    assertTrue(msgMeta.getIsContentSafe());
    assertNotNull(msgMeta.getMetrics());
    assertEquals(200, msgMeta.getHttpStatus());
    assertEquals(1234567890L, msgMeta.getTimestamp());
    assertTrue(msgMeta.getExpected());
    assertEquals("trace-abc", msgMeta.getTraceId());
  }

  @Test
  void testDeserialization_AllLinksFields() throws Exception {
    String json = "{\"_links\":{" +
        "\"self\":{\"href\":\"https://test.com/self\"}," +
        "\"messages\":{\"href\":\"https://test.com/messages\"}," +
        "\"session\":{\"href\":\"https://test.com/session\"}," +
        "\"end\":{\"href\":\"https://test.com/end\"}" +
        "},\"messagesMetadata\":[]}";

    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);

    assertNotNull(metadata.getLinks());
    assertNotNull(metadata.getLinks().getSelf());
    assertEquals("https://test.com/self", metadata.getLinks().getSelf().getHref());
    assertNotNull(metadata.getLinks().getMessages());
    assertEquals("https://test.com/messages", metadata.getLinks().getMessages().getHref());
    assertNotNull(metadata.getLinks().getSession());
    assertEquals("https://test.com/session", metadata.getLinks().getSession().getHref());
    assertNotNull(metadata.getLinks().getEnd());
    assertEquals("https://test.com/end", metadata.getLinks().getEnd().getHref());
  }

  @Test
  void testDeserialization_MultipleMessagesMetadata() throws Exception {
    String json = "{\"messagesMetadata\":[" +
        "{\"id\":\"msg-001\",\"type\":\"Inform\"}," +
        "{\"id\":\"msg-002\",\"type\":\"Escalate\"}" +
        "]}";

    AgentResponseMetadata metadata = objectMapper.readValue(json, AgentResponseMetadata.class);

    assertNotNull(metadata.getMessagesMetadata());
    assertEquals(2, metadata.getMessagesMetadata().size());
    assertEquals("msg-001", metadata.getMessagesMetadata().get(0).getId());
    assertEquals("msg-002", metadata.getMessagesMetadata().get(1).getId());
  }

  @Test
  void testMessageMetadataEquals_DifferentId() throws Exception {
    String json1 = "{\"id\":\"msg-123\",\"type\":\"Inform\"}";
    String json2 = "{\"id\":\"msg-456\",\"type\":\"Inform\"}";

    AgentResponseMetadata.MessageMetadata msgMeta1 = objectMapper.readValue(json1,
                                                                            AgentResponseMetadata.MessageMetadata.class);
    AgentResponseMetadata.MessageMetadata msgMeta2 = objectMapper.readValue(json2,
                                                                            AgentResponseMetadata.MessageMetadata.class);

    assertNotEquals(msgMeta1, msgMeta2);
  }

  @Test
  void testMessageMetadataEquals_DifferentMetrics() throws Exception {
    String json1 = "{\"metrics\":{\"duration\":100}}";
    String json2 = "{\"metrics\":{\"duration\":200}}";

    AgentResponseMetadata.MessageMetadata msgMeta1 = objectMapper.readValue(json1,
                                                                            AgentResponseMetadata.MessageMetadata.class);
    AgentResponseMetadata.MessageMetadata msgMeta2 = objectMapper.readValue(json2,
                                                                            AgentResponseMetadata.MessageMetadata.class);

    assertNotEquals(msgMeta1, msgMeta2);
  }

  @Test
  void testMessageMetadataHashCode_EqualObjects() throws Exception {
    String json = "{\"id\":\"msg-123\",\"type\":\"Inform\",\"feedbackId\":\"feedback-456\"}";

    AgentResponseMetadata.MessageMetadata msgMeta1 = objectMapper.readValue(json,
                                                                            AgentResponseMetadata.MessageMetadata.class);
    AgentResponseMetadata.MessageMetadata msgMeta2 = objectMapper.readValue(json,
                                                                            AgentResponseMetadata.MessageMetadata.class);

    assertEquals(msgMeta1.hashCode(), msgMeta2.hashCode());
  }

  @Test
  void testLinksEquals_DifferentSessionLink() throws Exception {
    String json1 = "{\"session\":{\"href\":\"https://test.com/session1\"}}";
    String json2 = "{\"session\":{\"href\":\"https://test.com/session2\"}}";

    AgentResponseMetadata.Links links1 = objectMapper.readValue(json1, AgentResponseMetadata.Links.class);
    AgentResponseMetadata.Links links2 = objectMapper.readValue(json2, AgentResponseMetadata.Links.class);

    assertNotEquals(links1, links2);
  }

  @Test
  void testLinksHashCode_EqualObjects() throws Exception {
    String json = "{\"self\":{\"href\":\"https://test.com/self\"}," +
        "\"messages\":{\"href\":\"https://test.com/messages\"}}";

    AgentResponseMetadata.Links links1 = objectMapper.readValue(json, AgentResponseMetadata.Links.class);
    AgentResponseMetadata.Links links2 = objectMapper.readValue(json, AgentResponseMetadata.Links.class);

    assertEquals(links1.hashCode(), links2.hashCode());
  }

  @Test
  void testLinkEquals_EqualObjects_WithHref() throws Exception {
    String json = "{\"href\":\"https://test.com/api/session/123\"}";

    AgentResponseMetadata.Links.Link link1 = objectMapper.readValue(json, AgentResponseMetadata.Links.Link.class);
    AgentResponseMetadata.Links.Link link2 = objectMapper.readValue(json, AgentResponseMetadata.Links.Link.class);

    assertEquals(link1, link2);
  }

  @Test
  void testLinkEquals_DifferentHref() throws Exception {
    String json1 = "{\"href\":\"https://test.com/api/session/123\"}";
    String json2 = "{\"href\":\"https://test.com/api/session/456\"}";

    AgentResponseMetadata.Links.Link link1 = objectMapper.readValue(json1, AgentResponseMetadata.Links.Link.class);
    AgentResponseMetadata.Links.Link link2 = objectMapper.readValue(json2, AgentResponseMetadata.Links.Link.class);

    assertNotEquals(link1, link2);
  }

  @Test
  void testLinkHashCode_EqualObjects_WithHref() throws Exception {
    String json = "{\"href\":\"https://test.com/api/session/123\"}";

    AgentResponseMetadata.Links.Link link1 = objectMapper.readValue(json, AgentResponseMetadata.Links.Link.class);
    AgentResponseMetadata.Links.Link link2 = objectMapper.readValue(json, AgentResponseMetadata.Links.Link.class);

    assertEquals(link1.hashCode(), link2.hashCode());
  }
}
