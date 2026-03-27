package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testCollectEquals_WithData() throws Exception {
    String json =
        "{\"targetType\":\"Account\",\"targetProperty\":\"Name\",\"data\":{\"type\":\"search\",\"property\":\"query\"}}";

    Collect collect1 = objectMapper.readValue(json, Collect.class);
    Collect collect2 = objectMapper.readValue(json, Collect.class);

    assertEquals(collect1, collect2);
  }

  @Test
  void testCollectEquals_DifferentTargetType() throws Exception {
    String json1 = "{\"targetType\":\"Account\"}";
    String json2 = "{\"targetType\":\"Contact\"}";

    Collect collect1 = objectMapper.readValue(json1, Collect.class);
    Collect collect2 = objectMapper.readValue(json2, Collect.class);

    assertNotEquals(collect1, collect2);
  }

  @Test
  void testCollectHashCode() throws Exception {
    String json = "{\"targetType\":\"Account\",\"targetProperty\":\"Name\"}";

    Collect collect1 = objectMapper.readValue(json, Collect.class);
    Collect collect2 = objectMapper.readValue(json, Collect.class);

    assertEquals(collect1.hashCode(), collect2.hashCode());
  }

  @Test
  void testRecordFieldEquals_WithData() throws Exception {
    String json = "{\"displayValue\":\"John Doe\",\"value\":\"123456\"}";

    Collect.RecordField field1 = objectMapper.readValue(json, Collect.RecordField.class);
    Collect.RecordField field2 = objectMapper.readValue(json, Collect.RecordField.class);

    assertEquals(field1, field2);
  }

  @Test
  void testRecordFieldEquals_DifferentValues() throws Exception {
    String json1 = "{\"value\":\"123\"}";
    String json2 = "{\"value\":\"456\"}";

    Collect.RecordField field1 = objectMapper.readValue(json1, Collect.RecordField.class);
    Collect.RecordField field2 = objectMapper.readValue(json2, Collect.RecordField.class);

    assertNotEquals(field1, field2);
  }

  @Test
  void testRecordFieldHashCode() throws Exception {
    String json = "{\"displayValue\":\"Test\",\"value\":\"789\"}";

    Collect.RecordField field1 = objectMapper.readValue(json, Collect.RecordField.class);
    Collect.RecordField field2 = objectMapper.readValue(json, Collect.RecordField.class);

    assertEquals(field1.hashCode(), field2.hashCode());
  }

  @Test
  void testRecordDataEquals_WithFields() throws Exception {
    String json = "{\"Type\":{\"value\":\"Account\"},\"Id\":{\"value\":\"123\"},\"Name\":{\"value\":\"Acme\"}}";

    Collect.RecordData data1 = objectMapper.readValue(json, Collect.RecordData.class);
    Collect.RecordData data2 = objectMapper.readValue(json, Collect.RecordData.class);

    assertEquals(data1, data2);
  }

  @Test
  void testRecordDataEquals_DifferentFields() throws Exception {
    String json1 = "{\"Name\":{\"value\":\"Acme\"}}";
    String json2 = "{\"Name\":{\"value\":\"Globex\"}}";

    Collect.RecordData data1 = objectMapper.readValue(json1, Collect.RecordData.class);
    Collect.RecordData data2 = objectMapper.readValue(json2, Collect.RecordData.class);

    assertNotEquals(data1, data2);
  }

  @Test
  void testRecordDataHashCode() throws Exception {
    String json = "{\"Type\":{\"value\":\"Contact\"},\"Id\":{\"value\":\"456\"}}";

    Collect.RecordData data1 = objectMapper.readValue(json, Collect.RecordData.class);
    Collect.RecordData data2 = objectMapper.readValue(json, Collect.RecordData.class);

    assertEquals(data1.hashCode(), data2.hashCode());
  }

  @Test
  void testSObjectInfoEquals_WithData() throws Exception {
    String json = "{\"apiName\":\"Account\",\"label\":\"Account\"}";

    Collect.SObjectInfo info1 = objectMapper.readValue(json, Collect.SObjectInfo.class);
    Collect.SObjectInfo info2 = objectMapper.readValue(json, Collect.SObjectInfo.class);

    assertEquals(info1, info2);
  }

  @Test
  void testSObjectInfoEquals_DifferentApiName() throws Exception {
    String json1 = "{\"apiName\":\"Account\"}";
    String json2 = "{\"apiName\":\"Contact\"}";

    Collect.SObjectInfo info1 = objectMapper.readValue(json1, Collect.SObjectInfo.class);
    Collect.SObjectInfo info2 = objectMapper.readValue(json2, Collect.SObjectInfo.class);

    assertNotEquals(info1, info2);
  }

  @Test
  void testSObjectInfoHashCode() throws Exception {
    String json = "{\"apiName\":\"Account\",\"label\":\"Account\"}";

    Collect.SObjectInfo info1 = objectMapper.readValue(json, Collect.SObjectInfo.class);
    Collect.SObjectInfo info2 = objectMapper.readValue(json, Collect.SObjectInfo.class);

    assertEquals(info1.hashCode(), info2.hashCode());
  }

  @Test
  void testSearchResultEquals_WithData() throws Exception {
    String json = "{\"id\":\"001\",\"title\":\"Test Account\",\"recordTypeId\":\"rt-123\"," +
        "\"sObjectInfo\":{\"apiName\":\"Account\"},\"data\":{\"Name\":{\"value\":\"Acme\"}}}";

    Collect.SearchResult result1 = objectMapper.readValue(json, Collect.SearchResult.class);
    Collect.SearchResult result2 = objectMapper.readValue(json, Collect.SearchResult.class);

    assertEquals(result1, result2);
  }

  @Test
  void testSearchResultEquals_DifferentId() throws Exception {
    String json1 = "{\"id\":\"001\"}";
    String json2 = "{\"id\":\"002\"}";

    Collect.SearchResult result1 = objectMapper.readValue(json1, Collect.SearchResult.class);
    Collect.SearchResult result2 = objectMapper.readValue(json2, Collect.SearchResult.class);

    assertNotEquals(result1, result2);
  }

  @Test
  void testSearchResultHashCode() throws Exception {
    String json = "{\"id\":\"001\",\"title\":\"Test\"}";

    Collect.SearchResult result1 = objectMapper.readValue(json, Collect.SearchResult.class);
    Collect.SearchResult result2 = objectMapper.readValue(json, Collect.SearchResult.class);

    assertEquals(result1.hashCode(), result2.hashCode());
  }
}
