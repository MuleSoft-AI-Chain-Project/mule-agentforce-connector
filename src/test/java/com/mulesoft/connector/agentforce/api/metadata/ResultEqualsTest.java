package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for equals() and hashCode() methods in Result and Result.Value classes
 */
class ResultEqualsTest {

  // ==================== Result.Value Tests ====================

  @Test
  void testValue_Equals_SameInstance_ShouldReturnTrue() {
    Result.Value value = createValue("response1");

    assertTrue(value.equals(value), "Same instance should be equal to itself");
  }

  @Test
  void testValue_Equals_SameValues_ShouldReturnTrue() {
    Result.Value value1 = createValue("response1");
    Result.Value value2 = createValue("response1");

    assertTrue(value1.equals(value2), "Values with same promptResponse should be equal");
    assertTrue(value2.equals(value1), "Equals should be symmetric");
  }

  @Test
  void testValue_Equals_DifferentPromptResponse_ShouldReturnFalse() {
    Result.Value value1 = createValue("response1");
    Result.Value value2 = createValue("response2");

    assertFalse(value1.equals(value2), "Values with different promptResponse should not be equal");
    assertFalse(value2.equals(value1), "Equals should be symmetric");
  }

  @Test
  void testValue_Equals_NullPromptResponse_ShouldReturnTrue() {
    Result.Value value1 = createValue(null);
    Result.Value value2 = createValue(null);

    assertTrue(value1.equals(value2), "Values with null promptResponse should be equal");
  }

  @Test
  void testValue_Equals_OneNullPromptResponse_ShouldReturnFalse() {
    Result.Value value1 = createValue(null);
    Result.Value value2 = createValue("response1");

    assertFalse(value1.equals(value2), "Values with one null promptResponse should not be equal");
    assertFalse(value2.equals(value1), "Equals should be symmetric");
  }

  @Test
  void testValue_Equals_NullObject_ShouldReturnFalse() {
    Result.Value value = createValue("response1");

    assertFalse(value.equals(null), "Value should not be equal to null");
  }

  @Test
  void testValue_Equals_DifferentClass_ShouldReturnFalse() {
    Result.Value value = createValue("response1");
    String differentClass = "response1";

    assertFalse(value.equals(differentClass), "Value should not be equal to different class");
  }

  @Test
  void testValue_HashCode_SameValues_ShouldReturnSameHashCode() {
    Result.Value value1 = createValue("response1");
    Result.Value value2 = createValue("response1");

    assertEquals(value1.hashCode(), value2.hashCode(), "Values with same promptResponse should have same hashCode");
  }

  @Test
  void testValue_HashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    Result.Value value1 = createValue("response1");
    Result.Value value2 = createValue("response2");

    assertNotEquals(value1.hashCode(), value2.hashCode(),
                    "Values with different promptResponse should have different hashCode");
  }

  @Test
  void testValue_HashCode_NullValues_ShouldReturnSameHashCode() {
    Result.Value value1 = createValue(null);
    Result.Value value2 = createValue(null);

    assertEquals(value1.hashCode(), value2.hashCode(), "Values with null promptResponse should have same hashCode");
  }

  @Test
  void testValue_Equals_Transitivity() {
    Result.Value value1 = createValue("response1");
    Result.Value value2 = createValue("response1");
    Result.Value value3 = createValue("response1");

    assertTrue(value1.equals(value2), "value1 should equal value2");
    assertTrue(value2.equals(value3), "value2 should equal value3");
    assertTrue(value1.equals(value3), "value1 should equal value3 (transitivity)");
  }

  @Test
  void testValue_GetPromptResponse_ShouldReturnCorrectValue() {
    Result.Value value = createValue("response1");

    assertEquals("response1", value.getPromptResponse(), "getPromptResponse should return correct value");
  }

  @Test
  void testValue_GetPromptResponse_NullValue_ShouldReturnNull() {
    Result.Value value = createValue(null);

    assertNull(value.getPromptResponse(), "getPromptResponse should return null");
  }

  // ==================== Result Tests ====================

  @Test
  void testResult_Equals_SameInstance_ShouldReturnTrue() {
    Result result = createResult("type1", createValue("response1"), "property1");

    assertTrue(result.equals(result), "Same instance should be equal to itself");
  }

  @Test
  void testResult_Equals_SameValues_ShouldReturnTrue() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response1"), "property1");

    assertTrue(result1.equals(result2), "Results with same values should be equal");
    assertTrue(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_DifferentType_ShouldReturnFalse() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type2", createValue("response1"), "property1");

    assertFalse(result1.equals(result2), "Results with different type should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_DifferentValue_ShouldReturnFalse() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response2"), "property1");

    assertFalse(result1.equals(result2), "Results with different value should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_DifferentProperty_ShouldReturnFalse() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response1"), "property2");

    assertFalse(result1.equals(result2), "Results with different property should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_AllDifferent_ShouldReturnFalse() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type2", createValue("response2"), "property2");

    assertFalse(result1.equals(result2), "Results with all different values should not be equal");
  }

  @Test
  void testResult_Equals_NullFields_ShouldReturnTrue() {
    Result result1 = createResult(null, null, null);
    Result result2 = createResult(null, null, null);

    assertTrue(result1.equals(result2), "Results with null fields should be equal");
  }

  @Test
  void testResult_Equals_OneNullType_ShouldReturnFalse() {
    Result result1 = createResult(null, createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response1"), "property1");

    assertFalse(result1.equals(result2), "Results with one null type should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_OneNullValue_ShouldReturnFalse() {
    Result result1 = createResult("type1", null, "property1");
    Result result2 = createResult("type1", createValue("response1"), "property1");

    assertFalse(result1.equals(result2), "Results with one null value should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_OneNullProperty_ShouldReturnFalse() {
    Result result1 = createResult("type1", createValue("response1"), null);
    Result result2 = createResult("type1", createValue("response1"), "property1");

    assertFalse(result1.equals(result2), "Results with one null property should not be equal");
    assertFalse(result2.equals(result1), "Equals should be symmetric");
  }

  @Test
  void testResult_Equals_NullObject_ShouldReturnFalse() {
    Result result = createResult("type1", createValue("response1"), "property1");

    assertFalse(result.equals(null), "Result should not be equal to null");
  }

  @Test
  void testResult_Equals_DifferentClass_ShouldReturnFalse() {
    Result result = createResult("type1", createValue("response1"), "property1");
    String differentClass = "type1";

    assertFalse(result.equals(differentClass), "Result should not be equal to different class");
  }

  @Test
  void testResult_HashCode_SameValues_ShouldReturnSameHashCode() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response1"), "property1");

    assertEquals(result1.hashCode(), result2.hashCode(), "Results with same values should have same hashCode");
  }

  @Test
  void testResult_HashCode_DifferentValues_ShouldReturnDifferentHashCode() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type2", createValue("response2"), "property2");

    assertNotEquals(result1.hashCode(), result2.hashCode(),
                    "Results with different values should have different hashCode");
  }

  @Test
  void testResult_HashCode_NullFields_ShouldReturnSameHashCode() {
    Result result1 = createResult(null, null, null);
    Result result2 = createResult(null, null, null);

    assertEquals(result1.hashCode(), result2.hashCode(), "Results with null fields should have same hashCode");
  }

  @Test
  void testResult_Equals_Transitivity() {
    Result result1 = createResult("type1", createValue("response1"), "property1");
    Result result2 = createResult("type1", createValue("response1"), "property1");
    Result result3 = createResult("type1", createValue("response1"), "property1");

    assertTrue(result1.equals(result2), "result1 should equal result2");
    assertTrue(result2.equals(result3), "result2 should equal result3");
    assertTrue(result1.equals(result3), "result1 should equal result3 (transitivity)");
  }

  @Test
  void testResult_GetType_ShouldReturnCorrectValue() {
    Result result = createResult("type1", createValue("response1"), "property1");

    assertEquals("type1", result.getType(), "getType should return correct value");
  }

  @Test
  void testResult_GetValue_ShouldReturnCorrectValue() {
    Result.Value value = createValue("response1");
    Result result = createResult("type1", value, "property1");

    assertEquals(value, result.getValue(), "getValue should return correct value");
  }

  @Test
  void testResult_GetProperty_ShouldReturnCorrectValue() {
    Result result = createResult("type1", createValue("response1"), "property1");

    assertEquals("property1", result.getProperty(), "getProperty should return correct value");
  }

  @Test
  void testResult_GetType_NullValue_ShouldReturnNull() {
    Result result = createResult(null, createValue("response1"), "property1");

    assertNull(result.getType(), "getType should return null");
  }

  @Test
  void testResult_GetValue_NullValue_ShouldReturnNull() {
    Result result = createResult("type1", null, "property1");

    assertNull(result.getValue(), "getValue should return null");
  }

  @Test
  void testResult_GetProperty_NullValue_ShouldReturnNull() {
    Result result = createResult("type1", createValue("response1"), null);

    assertNull(result.getProperty(), "getProperty should return null");
  }

  // ==================== Helper Methods ====================

  private Result.Value createValue(String promptResponse) {
    Result.Value value = new Result.Value();
    // Use reflection to set private field since there is no setter
    try {
      java.lang.reflect.Field promptResponseField = Result.Value.class.getDeclaredField("promptResponse");
      promptResponseField.setAccessible(true);
      promptResponseField.set(value, promptResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create Value", e);
    }
    return value;
  }

  private Result createResult(String type, Result.Value value, String property) {
    Result result = new Result();
    // Use reflection to set private fields since there are no setters
    try {
      java.lang.reflect.Field typeField = Result.class.getDeclaredField("type");
      typeField.setAccessible(true);
      typeField.set(result, type);

      java.lang.reflect.Field valueField = Result.class.getDeclaredField("value");
      valueField.setAccessible(true);
      valueField.set(result, value);

      java.lang.reflect.Field propertyField = Result.class.getDeclaredField("property");
      propertyField.setAccessible(true);
      propertyField.set(result, property);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create Result", e);
    }
    return result;
  }
}
