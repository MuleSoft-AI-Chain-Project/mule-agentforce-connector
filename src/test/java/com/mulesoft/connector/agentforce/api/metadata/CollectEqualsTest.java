package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for equals() and hashCode() methods in Collect.RecordData, Collect.RecordField, and InvokeAgentResponseAttributes
 * classes
 */
class CollectEqualsTest {

  // ==================== RecordField Tests ====================

  @Test
  void testRecordField_Equals_SameInstance_ShouldReturnTrue() {
    // Arrange
    Collect.RecordField field = createRecordField("display1", "value1");

    // Act & Assert
    assertTrue(field.equals(field), "Same instance should be equal to itself");
  }

  @Test
  void testRecordField_Equals_SameValues_ShouldReturnTrue() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display1", "value1");

    // Act & Assert
    assertTrue(field1.equals(field2), "RecordFields with same values should be equal");
    assertTrue(field2.equals(field1), "Equals should be symmetric");
  }

  @Test
  void testRecordField_Equals_DifferentDisplayValue_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display2", "value1");

    // Act & Assert
    assertFalse(field1.equals(field2), "RecordFields with different displayValue should not be equal");
    assertFalse(field2.equals(field1), "Equals should be symmetric");
  }

  @Test
  void testRecordField_Equals_DifferentValue_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display1", "value2");

    // Act & Assert
    assertFalse(field1.equals(field2), "RecordFields with different value should not be equal");
    assertFalse(field2.equals(field1), "Equals should be symmetric");
  }

  @Test
  void testRecordField_Equals_BothValuesDifferent_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display2", "value2");

    // Act & Assert
    assertFalse(field1.equals(field2), "RecordFields with both values different should not be equal");
  }

  @Test
  void testRecordField_Equals_NullDisplayValue_ShouldReturnTrue() {
    // Arrange
    Collect.RecordField field1 = createRecordField(null, "value1");
    Collect.RecordField field2 = createRecordField(null, "value1");

    // Act & Assert
    assertTrue(field1.equals(field2), "RecordFields with null displayValue should be equal");
  }

  @Test
  void testRecordField_Equals_NullValue_ShouldReturnTrue() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", null);
    Collect.RecordField field2 = createRecordField("display1", null);

    // Act & Assert
    assertTrue(field1.equals(field2), "RecordFields with null value should be equal");
  }

  @Test
  void testRecordField_Equals_BothNull_ShouldReturnTrue() {
    // Arrange
    Collect.RecordField field1 = createRecordField(null, null);
    Collect.RecordField field2 = createRecordField(null, null);

    // Act & Assert
    assertTrue(field1.equals(field2), "RecordFields with both null values should be equal");
  }

  @Test
  void testRecordField_Equals_OneNullDisplayValue_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field1 = createRecordField(null, "value1");
    Collect.RecordField field2 = createRecordField("display1", "value1");

    // Act & Assert
    assertFalse(field1.equals(field2), "RecordFields with one null displayValue should not be equal");
  }

  @Test
  void testRecordField_Equals_OneNullValue_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", null);
    Collect.RecordField field2 = createRecordField("display1", "value1");

    // Act & Assert
    assertFalse(field1.equals(field2), "RecordFields with one null value should not be equal");
  }

  @Test
  void testRecordField_Equals_NullObject_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field = createRecordField("display1", "value1");

    // Act & Assert
    assertFalse(field.equals(null), "RecordField should not be equal to null");
  }

  @Test
  void testRecordField_Equals_DifferentClass_ShouldReturnFalse() {
    // Arrange
    Collect.RecordField field = createRecordField("display1", "value1");
    String differentClass = "not a RecordField";

    // Act & Assert
    assertFalse(field.equals(differentClass), "RecordField should not be equal to different class");
  }

  @Test
  void testRecordField_HashCode_SameValues_ShouldReturnSameHashCode() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display1", "value1");

    // Act & Assert
    assertEquals(field1.hashCode(), field2.hashCode(), "RecordFields with same values should have same hashCode");
  }

  @Test
  void testRecordField_HashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    // Arrange
    Collect.RecordField field1 = createRecordField("display1", "value1");
    Collect.RecordField field2 = createRecordField("display2", "value2");

    // Act & Assert
    assertNotEquals(field1.hashCode(), field2.hashCode(), "RecordFields with different values should have different hashCode");
  }

  // ==================== RecordData Tests ====================

  @Test
  void testRecordData_Equals_SameInstance_ShouldReturnTrue() {
    // Arrange
    Collect.RecordData recordData = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertTrue(recordData.equals(recordData), "Same instance should be equal to itself");
  }

  @Test
  void testRecordData_Equals_SameValues_ShouldReturnTrue() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertTrue(recordData1.equals(recordData2), "RecordData with same values should be equal");
    assertTrue(recordData2.equals(recordData1), "Equals should be symmetric");
  }

  @Test
  void testRecordData_Equals_DifferentType_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type2", "id1", "name1");

    // Act & Assert
    assertFalse(recordData1.equals(recordData2), "RecordData with different Type should not be equal");
    assertFalse(recordData2.equals(recordData1), "Equals should be symmetric");
  }

  @Test
  void testRecordData_Equals_DifferentId_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id2", "name1");

    // Act & Assert
    assertFalse(recordData1.equals(recordData2), "RecordData with different Id should not be equal");
    assertFalse(recordData2.equals(recordData1), "Equals should be symmetric");
  }

  @Test
  void testRecordData_Equals_DifferentName_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id1", "name2");

    // Act & Assert
    assertFalse(recordData1.equals(recordData2), "RecordData with different Name should not be equal");
    assertFalse(recordData2.equals(recordData1), "Equals should be symmetric");
  }

  @Test
  void testRecordData_Equals_AllDifferent_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type2", "id2", "name2");

    // Act & Assert
    assertFalse(recordData1.equals(recordData2), "RecordData with all different values should not be equal");
  }

  @Test
  void testRecordData_Equals_NullFields_ShouldReturnTrue() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData(null, null, null);
    Collect.RecordData recordData2 = createRecordData(null, null, null);

    // Act & Assert
    assertTrue(recordData1.equals(recordData2), "RecordData with null fields should be equal");
  }

  @Test
  void testRecordData_Equals_OneNullField_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData(null, "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertFalse(recordData1.equals(recordData2), "RecordData with one null field should not be equal");
  }

  @Test
  void testRecordData_Equals_NullObject_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertFalse(recordData.equals(null), "RecordData should not be equal to null");
  }

  @Test
  void testRecordData_Equals_DifferentClass_ShouldReturnFalse() {
    // Arrange
    Collect.RecordData recordData = createRecordData("type1", "id1", "name1");
    String differentClass = "not a RecordData";

    // Act & Assert
    assertFalse(recordData.equals(differentClass), "RecordData should not be equal to different class");
  }

  @Test
  void testRecordData_HashCode_SameValues_ShouldReturnSameHashCode() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertEquals(recordData1.hashCode(), recordData2.hashCode(), "RecordData with same values should have same hashCode");
  }

  @Test
  void testRecordData_HashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type2", "id2", "name2");

    // Act & Assert
    assertNotEquals(recordData1.hashCode(), recordData2.hashCode(),
                    "RecordData with different values should have different hashCode");
  }

  @Test
  void testRecordData_Equals_Transitivity() {
    // Arrange
    Collect.RecordData recordData1 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData2 = createRecordData("type1", "id1", "name1");
    Collect.RecordData recordData3 = createRecordData("type1", "id1", "name1");

    // Act & Assert
    assertTrue(recordData1.equals(recordData2), "recordData1 should equal recordData2");
    assertTrue(recordData2.equals(recordData3), "recordData2 should equal recordData3");
    assertTrue(recordData1.equals(recordData3), "recordData1 should equal recordData3 (transitivity)");
  }

  // ==================== Helper Methods ====================

  private Collect.RecordField createRecordField(String displayValue, String value) {
    Collect.RecordField field = new Collect.RecordField();
    // Use reflection to set private fields since there are no setters
    try {
      java.lang.reflect.Field displayValueField = Collect.RecordField.class.getDeclaredField("displayValue");
      displayValueField.setAccessible(true);
      displayValueField.set(field, displayValue);

      java.lang.reflect.Field valueField = Collect.RecordField.class.getDeclaredField("value");
      valueField.setAccessible(true);
      valueField.set(field, value);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create RecordField", e);
    }
    return field;
  }

  private Collect.RecordData createRecordData(String typeValue, String idValue, String nameValue) {
    Collect.RecordData recordData = new Collect.RecordData();
    // Use reflection to set private fields since there are no setters
    try {
      java.lang.reflect.Field typeField = Collect.RecordData.class.getDeclaredField("Type");
      typeField.setAccessible(true);
      typeField.set(recordData, createRecordField("", typeValue));

      java.lang.reflect.Field idField = Collect.RecordData.class.getDeclaredField("Id");
      idField.setAccessible(true);
      idField.set(recordData, createRecordField("", idValue));

      java.lang.reflect.Field nameField = Collect.RecordData.class.getDeclaredField("Name");
      nameField.setAccessible(true);
      nameField.set(recordData, createRecordField("", nameValue));
    } catch (Exception e) {
      throw new RuntimeException("Failed to create RecordData", e);
    }
    return recordData;
  }

  // ==================== InvokeAgentResponseAttributes Tests ====================

  @Test
  void testInvokeAgentResponseAttributes_Equals_SameInstance_ShouldReturnTrue() {
    // Arrange
    InvokeAgentResponseAttributes attributes = createInvokeAgentResponseAttributes();

    // Act & Assert
    assertTrue(attributes.equals(attributes), "Same instance should be equal to itself");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_SameMessages_ShouldReturnTrue() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributes();

    // Act & Assert
    assertTrue(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with same messages should be equal");
    assertTrue(attributes2.equals(attributes1), "Equals should be symmetric");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_DifferentMessages_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithDifferentMessages();

    // Act & Assert
    assertFalse(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with different messages should not be equal");
    assertFalse(attributes2.equals(attributes1), "Equals should be symmetric");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_OneNullMessages_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithNullMessages();

    // Act & Assert
    assertFalse(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with one null messages should not be equal");
    assertFalse(attributes2.equals(attributes1), "Equals should be symmetric");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_BothNullMessages_ShouldReturnTrue() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributesWithNullMessages();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithNullMessages();

    // Act & Assert
    assertTrue(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with both null messages should be equal");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_EmptyMessages_ShouldReturnTrue() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributesWithEmptyMessages();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithEmptyMessages();

    // Act & Assert
    assertTrue(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with empty messages should be equal");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_OneEmptyMessages_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithEmptyMessages();

    // Act & Assert
    assertFalse(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with one empty messages should not be equal");
    assertFalse(attributes2.equals(attributes1), "Equals should be symmetric");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_MultipleMessages_ShouldReturnTrue() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributesWithMultipleMessages();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithMultipleMessages();

    // Act & Assert
    assertTrue(attributes1.equals(attributes2), "InvokeAgentResponseAttributes with multiple messages should be equal");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_DifferentMessageCount_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithMultipleMessages();

    // Act & Assert
    assertFalse(attributes1.equals(attributes2),
                "InvokeAgentResponseAttributes with different message count should not be equal");
    assertFalse(attributes2.equals(attributes1), "Equals should be symmetric");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_NullObject_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes = createInvokeAgentResponseAttributes();

    // Act & Assert
    assertFalse(attributes.equals(null), "InvokeAgentResponseAttributes should not be equal to null");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_DifferentClass_ShouldReturnFalse() {
    // Arrange
    InvokeAgentResponseAttributes attributes = createInvokeAgentResponseAttributes();
    String differentClass = "not an InvokeAgentResponseAttributes";

    // Act & Assert
    assertFalse(attributes.equals(differentClass), "InvokeAgentResponseAttributes should not be equal to different class");
  }

  @Test
  void testInvokeAgentResponseAttributes_HashCode_SameMessages_ShouldReturnSameHashCode() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributes();

    // Act & Assert
    assertEquals(attributes1.hashCode(), attributes2.hashCode(),
                 "InvokeAgentResponseAttributes with same messages should have same hashCode");
  }

  @Test
  void testInvokeAgentResponseAttributes_HashCode_DifferentMessages_ShouldReturnDifferentHashCode() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributesWithDifferentMessages();

    // Act & Assert
    assertNotEquals(attributes1.hashCode(), attributes2.hashCode(),
                    "InvokeAgentResponseAttributes with different messages should have different hashCode");
  }

  @Test
  void testInvokeAgentResponseAttributes_Equals_Transitivity() {
    // Arrange
    InvokeAgentResponseAttributes attributes1 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes2 = createInvokeAgentResponseAttributes();
    InvokeAgentResponseAttributes attributes3 = createInvokeAgentResponseAttributes();

    // Act & Assert
    assertTrue(attributes1.equals(attributes2), "attributes1 should equal attributes2");
    assertTrue(attributes2.equals(attributes3), "attributes2 should equal attributes3");
    assertTrue(attributes1.equals(attributes3), "attributes1 should equal attributes3 (transitivity)");
  }

  // ==================== Helper Methods for InvokeAgentResponseAttributes ====================

  private InvokeAgentResponseAttributes createInvokeAgentResponseAttributes() {
    InvokeAgentResponseAttributes attributes = new InvokeAgentResponseAttributes();
    // Use reflection to set private field since there are no setters
    try {
      java.lang.reflect.Field messagesField = InvokeAgentResponseAttributes.class.getDeclaredField("messages");
      messagesField.setAccessible(true);
      messagesField
          .set(attributes,
               Arrays.asList(createMessage("Inquire", "msg1", "feedback1", "plan1", true, "Test message", "reason1", null)));
    } catch (Exception e) {
      throw new RuntimeException("Failed to create InvokeAgentResponseAttributes", e);
    }
    return attributes;
  }

  private InvokeAgentResponseAttributes createInvokeAgentResponseAttributesWithDifferentMessages() {
    InvokeAgentResponseAttributes attributes = new InvokeAgentResponseAttributes();
    try {
      java.lang.reflect.Field messagesField = InvokeAgentResponseAttributes.class.getDeclaredField("messages");
      messagesField.setAccessible(true);
      messagesField
          .set(attributes,
               Arrays.asList(createMessage("Answer", "msg2", "feedback2", "plan2", false, "Different message", "reason2", null)));
    } catch (Exception e) {
      throw new RuntimeException("Failed to create InvokeAgentResponseAttributes", e);
    }
    return attributes;
  }

  private InvokeAgentResponseAttributes createInvokeAgentResponseAttributesWithNullMessages() {
    InvokeAgentResponseAttributes attributes = new InvokeAgentResponseAttributes();
    try {
      java.lang.reflect.Field messagesField = InvokeAgentResponseAttributes.class.getDeclaredField("messages");
      messagesField.setAccessible(true);
      messagesField.set(attributes, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create InvokeAgentResponseAttributes", e);
    }
    return attributes;
  }

  private InvokeAgentResponseAttributes createInvokeAgentResponseAttributesWithEmptyMessages() {
    InvokeAgentResponseAttributes attributes = new InvokeAgentResponseAttributes();
    try {
      java.lang.reflect.Field messagesField = InvokeAgentResponseAttributes.class.getDeclaredField("messages");
      messagesField.setAccessible(true);
      messagesField.set(attributes, Collections.emptyList());
    } catch (Exception e) {
      throw new RuntimeException("Failed to create InvokeAgentResponseAttributes", e);
    }
    return attributes;
  }

  private InvokeAgentResponseAttributes createInvokeAgentResponseAttributesWithMultipleMessages() {
    InvokeAgentResponseAttributes attributes = new InvokeAgentResponseAttributes();
    try {
      java.lang.reflect.Field messagesField = InvokeAgentResponseAttributes.class.getDeclaredField("messages");
      messagesField.setAccessible(true);
      messagesField.set(attributes, Arrays.asList(
                                                  createMessage("Inquire", "msg1", "feedback1", "plan1", true, "First message",
                                                                "reason1", null),
                                                  createMessage("Answer", "msg2", "feedback2", "plan2", false, "Second message",
                                                                "reason2", null)));
    } catch (Exception e) {
      throw new RuntimeException("Failed to create InvokeAgentResponseAttributes", e);
    }
    return attributes;
  }

  private InvokeAgentResponseAttributes.Message createMessage(String type, String id, String feedbackId, String planId,
                                                              boolean isContentSafe, String message, String reason,
                                                              List<Collect> collect) {
    InvokeAgentResponseAttributes.Message msg = new InvokeAgentResponseAttributes.Message();
    // Use reflection to set private fields since there are no setters
    try {
      java.lang.reflect.Field typeField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("type");
      typeField.setAccessible(true);
      typeField.set(msg, type);

      java.lang.reflect.Field idField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("id");
      idField.setAccessible(true);
      idField.set(msg, id);

      java.lang.reflect.Field feedbackIdField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("feedbackId");
      feedbackIdField.setAccessible(true);
      feedbackIdField.set(msg, feedbackId);

      java.lang.reflect.Field planIdField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("planId");
      planIdField.setAccessible(true);
      planIdField.set(msg, planId);

      java.lang.reflect.Field isContentSafeField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("isContentSafe");
      isContentSafeField.setAccessible(true);
      isContentSafeField.set(msg, isContentSafe);

      java.lang.reflect.Field messageField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("message");
      messageField.setAccessible(true);
      messageField.set(msg, message);

      java.lang.reflect.Field reasonField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("reason");
      reasonField.setAccessible(true);
      reasonField.set(msg, reason);

      java.lang.reflect.Field collectField = InvokeAgentResponseAttributes.Message.class.getDeclaredField("collect");
      collectField.setAccessible(true);
      collectField.set(msg, collect);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create Message", e);
    }
    return msg;
  }
}
