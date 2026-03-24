package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentResponseMetadataTest {

  @Test
  void testEquals_SameObject() {
    AgentResponseMetadata metadata = new AgentResponseMetadata();
    assertEquals(metadata, metadata);
  }

  @Test
  void testEquals_NullObject() {
    AgentResponseMetadata metadata = new AgentResponseMetadata();
    assertNotEquals(null, metadata);
  }

  @Test
  void testEquals_DifferentClass() {
    AgentResponseMetadata metadata = new AgentResponseMetadata();
    assertNotEquals(new Object(), metadata);
  }

  @Test
  void testEquals_EqualObjects() {
    AgentResponseMetadata metadata1 = new AgentResponseMetadata();
    AgentResponseMetadata metadata2 = new AgentResponseMetadata();
    assertEquals(metadata1, metadata2);
  }

  @Test
  void testHashCode_EqualObjects() {
    AgentResponseMetadata metadata1 = new AgentResponseMetadata();
    AgentResponseMetadata metadata2 = new AgentResponseMetadata();
    assertEquals(metadata1.hashCode(), metadata2.hashCode());
  }

  @Test
  void testMessageMetadataEquals_SameObject() {
    AgentResponseMetadata.MessageMetadata messageMetadata = new AgentResponseMetadata.MessageMetadata();
    assertEquals(messageMetadata, messageMetadata);
  }

  @Test
  void testMessageMetadataEquals_NullObject() {
    AgentResponseMetadata.MessageMetadata messageMetadata = new AgentResponseMetadata.MessageMetadata();
    assertNotEquals(null, messageMetadata);
  }

  @Test
  void testMessageMetadataEquals_DifferentClass() {
    AgentResponseMetadata.MessageMetadata messageMetadata = new AgentResponseMetadata.MessageMetadata();
    assertNotEquals(messageMetadata, new Object());
  }

  @Test
  void testMessageMetadataEquals_EqualObjects() {
    AgentResponseMetadata.MessageMetadata messageMetadata1 = new AgentResponseMetadata.MessageMetadata();
    AgentResponseMetadata.MessageMetadata messageMetadata2 = new AgentResponseMetadata.MessageMetadata();
    assertEquals(messageMetadata1, messageMetadata2);
  }

  @Test
  void testMessageMetadataHashCode_EqualObjects() {
    AgentResponseMetadata.MessageMetadata messageMetadata1 = new AgentResponseMetadata.MessageMetadata();
    AgentResponseMetadata.MessageMetadata messageMetadata2 = new AgentResponseMetadata.MessageMetadata();
    assertEquals(messageMetadata1.hashCode(), messageMetadata2.hashCode());
  }

  @Test
  void testLinksEquals_SameObject() {
    AgentResponseMetadata.Links links = new AgentResponseMetadata.Links();
    assertEquals(links, links);
  }

  @Test
  void testLinksEquals_NullObject() {
    AgentResponseMetadata.Links links = new AgentResponseMetadata.Links();
    assertNotEquals(null, links);
  }

  @Test
  void testLinksEquals_DifferentClass() {
    AgentResponseMetadata.Links links = new AgentResponseMetadata.Links();
    assertNotEquals(links, new Object());
  }

  @Test
  void testLinksEquals_EqualObjects() {
    AgentResponseMetadata.Links links1 = new AgentResponseMetadata.Links();
    AgentResponseMetadata.Links links2 = new AgentResponseMetadata.Links();
    assertEquals(links1, links2);
  }

  @Test
  void testLinksHashCode_EqualObjects() {
    AgentResponseMetadata.Links links1 = new AgentResponseMetadata.Links();
    AgentResponseMetadata.Links links2 = new AgentResponseMetadata.Links();
    assertEquals(links1.hashCode(), links2.hashCode());
  }

  @Test
  void testLinkEquals_SameObject() {
    AgentResponseMetadata.Links.Link link = new AgentResponseMetadata.Links.Link();
    assertEquals(link, link);
  }

  @Test
  void testLinkEquals_NullObject() {
    AgentResponseMetadata.Links.Link link = new AgentResponseMetadata.Links.Link();
    assertNotEquals(null, link);
  }

  @Test
  void testLinkEquals_DifferentClass() {
    AgentResponseMetadata.Links.Link link = new AgentResponseMetadata.Links.Link();
    assertNotEquals(link, new Object());
  }

  @Test
  void testLinkEquals_EqualObjects() {
    AgentResponseMetadata.Links.Link link1 = new AgentResponseMetadata.Links.Link();
    AgentResponseMetadata.Links.Link link2 = new AgentResponseMetadata.Links.Link();
    assertEquals(link1, link2);
  }

  @Test
  void testLinkHashCode_EqualObjects() {
    AgentResponseMetadata.Links.Link link1 = new AgentResponseMetadata.Links.Link();
    AgentResponseMetadata.Links.Link link2 = new AgentResponseMetadata.Links.Link();
    assertEquals(link1.hashCode(), link2.hashCode());
  }
}
