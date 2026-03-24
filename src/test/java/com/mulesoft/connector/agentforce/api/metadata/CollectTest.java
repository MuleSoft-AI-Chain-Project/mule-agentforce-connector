package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectTest {

  @Test
  void testRecordDataEquals_SameObject() {
    Collect.RecordData recordData = new Collect.RecordData();
    assertEquals(recordData, recordData);
  }

  @Test
  void testRecordDataEquals_NullObject() {
    Collect.RecordData recordData = new Collect.RecordData();
    assertNotEquals(null, recordData);
  }

  @Test
  void testRecordDataEquals_DifferentClass() {
    Collect.RecordData recordData = new Collect.RecordData();
    assertNotEquals(new Object(), recordData);
  }

  @Test
  void testRecordDataEquals_EqualObjects() {
    Collect.RecordData recordData1 = new Collect.RecordData();
    Collect.RecordData recordData2 = new Collect.RecordData();
    assertEquals(recordData1, recordData2);
  }

  @Test
  void testRecordDataHashCode_EqualObjects() {
    Collect.RecordData recordData1 = new Collect.RecordData();
    Collect.RecordData recordData2 = new Collect.RecordData();
    assertEquals(recordData1.hashCode(), recordData2.hashCode());
  }

  @Test
  void testRecordFieldEquals_SameObject() {
    Collect.RecordField recordField = new Collect.RecordField();
    assertEquals(recordField, recordField);
  }

  @Test
  void testRecordFieldEquals_NullObject() {
    Collect.RecordField recordField = new Collect.RecordField();
    assertNotEquals(null, recordField);
  }

  @Test
  void testRecordFieldEquals_DifferentClass() {
    Collect.RecordField recordField = new Collect.RecordField();
    assertNotEquals(new Object(), recordField);
  }

  @Test
  void testRecordFieldEquals_EqualObjects() {
    Collect.RecordField recordField1 = new Collect.RecordField();
    Collect.RecordField recordField2 = new Collect.RecordField();
    assertEquals(recordField1, recordField2);
  }

  @Test
  void testRecordFieldHashCode_EqualObjects() {
    Collect.RecordField recordField1 = new Collect.RecordField();
    Collect.RecordField recordField2 = new Collect.RecordField();
    assertEquals(recordField1.hashCode(), recordField2.hashCode());
  }
}
