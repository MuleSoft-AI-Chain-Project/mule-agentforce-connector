package com.mulesoft.connector.agentforce.api.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  // Helper for round-trip testing
  private Variable roundTrip(String json) throws Exception {
    Variable variable = objectMapper.readValue(json, Variable.class);
    String serialized = objectMapper.writeValueAsString(variable);
    return objectMapper.readValue(serialized, Variable.class);
  }

  @Test
  void testBooleanVariable_True_RoundTrip() throws Exception {
    String json = "{\"name\":\"isShipped\",\"value\":true,\"type\":\"Boolean\"}";

    Variable variable = roundTrip(json);

    assertEquals("isShipped", variable.getName());
    assertEquals("true", variable.getValue());
    assertEquals("Boolean", variable.getType());
  }

  @Test
  void testBooleanVariable_False_RoundTrip() throws Exception {
    String json = "{\"name\":\"isActive\",\"value\":false,\"type\":\"Boolean\"}";

    Variable variable = roundTrip(json);

    assertEquals("isActive", variable.getName());
    assertEquals("false", variable.getValue());
    assertEquals("Boolean", variable.getType());
  }

  @Test
  void testTextVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"greeting\",\"value\":\"Thanks for your order!\",\"type\":\"Text\"}";

    Variable variable = roundTrip(json);

    assertEquals("greeting", variable.getName());
    assertEquals("Thanks for your order!", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  void testNumberVariable_Integer_RoundTrip() throws Exception {
    String json = "{\"name\":\"quantity\",\"value\":42,\"type\":\"Number\"}";

    Variable variable = roundTrip(json);

    assertEquals("quantity", variable.getName());
    assertEquals("42", variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  void testNumberVariable_Double_RoundTrip() throws Exception {
    String json = "{\"name\":\"price\",\"value\":99.99,\"type\":\"Number\"}";

    Variable variable = roundTrip(json);

    assertEquals("price", variable.getName());
    assertEquals("99.99", variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  void testNumberVariable_Zero_RoundTrip() throws Exception {
    String json = "{\"name\":\"count\",\"value\":0,\"type\":\"Number\"}";

    Variable variable = roundTrip(json);

    assertEquals("count", variable.getName());
    assertEquals("0", variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  void testNumberVariable_Negative_RoundTrip() throws Exception {
    String json = "{\"name\":\"balance\",\"value\":-50.75,\"type\":\"Number\"}";

    Variable variable = roundTrip(json);

    assertEquals("balance", variable.getName());
    assertEquals("-50.75", variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  void testDateVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"deliveryDate\",\"value\":\"2021-09-21\",\"type\":\"Date\"}";

    Variable variable = roundTrip(json);

    assertEquals("deliveryDate", variable.getName());
    assertEquals("2021-09-21", variable.getValue());
    assertEquals("Date", variable.getType());
  }

  @Test
  void testDateTimeVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"orderTime\",\"value\":\"2018-09-21T14:30:00\",\"type\":\"DateTime\"}";

    Variable variable = roundTrip(json);

    assertEquals("orderTime", variable.getName());
    assertEquals("2018-09-21T14:30:00", variable.getValue());
    assertEquals("DateTime", variable.getType());
  }

  @Test
  void testMoneyVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"totalAmount\",\"value\":\"USD 10.40\",\"type\":\"Money\"}";

    Variable variable = roundTrip(json);

    assertEquals("totalAmount", variable.getName());
    assertEquals("USD 10.40", variable.getValue());
    assertEquals("Money", variable.getType());
  }

  @Test
  void testRefVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"accountRef\",\"value\":\"1M5xx000000000BCAQ\",\"type\":\"Ref\"}";

    Variable variable = roundTrip(json);

    assertEquals("accountRef", variable.getName());
    assertEquals("1M5xx000000000BCAQ", variable.getValue());
    assertEquals("Ref", variable.getType());
  }


  @Test
  void testEmptyString_RoundTrip() throws Exception {
    String json = "{\"name\":\"emptyText\",\"value\":\"\",\"type\":\"Text\"}";

    Variable variable = roundTrip(json);

    assertEquals("emptyText", variable.getName());
    assertEquals("", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  @Disabled
  void testNullValue_ExcludedFromSerialization() throws Exception {
    String json = "{\"name\":\"optionalField\",\"value\":null,\"type\":\"Text\"}";

    Variable variable = objectMapper.readValue(json, Variable.class);
    assertNull(variable.getValue());

    String serialized = objectMapper.writeValueAsString(variable);

    assertFalse(serialized.contains("\"value\""), "Null value should be excluded from JSON due to @JsonInclude(NON_NULL)");
    assertTrue(serialized.contains("\"name\":\"optionalField\""));
    assertTrue(serialized.contains("\"type\":\"Text\""));
  }

  @Test
  void testNullValue_Deserialization() throws Exception {
    String json = "{\"name\":\"emptyField\",\"type\":\"Text\",\"value\":null}";

    Variable variable = objectMapper.readValue(json, Variable.class);

    assertEquals("emptyField", variable.getName());
    assertEquals("Text", variable.getType());
    assertNull(variable.getValue());
  }

  @Test
  void testMultipleVariables_ListDeserialization() throws Exception {
    String json =
        "[{\"name\":\"isActive\",\"value\":true,\"type\":\"Boolean\"},{\"name\":\"userName\",\"value\":\"Alice\",\"type\":\"Text\"},{\"name\":\"score\",\"value\":95,\"type\":\"Number\"}]";

    List<Variable> variables = objectMapper.readValue(json, new TypeReference<List<Variable>>() {});

    assertEquals(3, variables.size());

    assertEquals("isActive", variables.get(0).getName());
    assertEquals("true", variables.get(0).getValue());
    assertEquals("Boolean", variables.get(0).getType());

    assertEquals("userName", variables.get(1).getName());
    assertEquals("Alice", variables.get(1).getValue());
    assertEquals("Text", variables.get(1).getType());

    assertEquals("score", variables.get(2).getName());
    assertEquals("95", variables.get(2).getValue());
    assertEquals("Number", variables.get(2).getType());
  }

  @Test
  void testSpecialCharacters_RoundTrip() throws Exception {
    String json = "{\"name\":\"message\",\"value\":\"Hello \\\"World\\\" \\n\\t Special: <>&\",\"type\":\"Text\"}";

    Variable variable = roundTrip(json);

    assertEquals("message", variable.getName());
    assertEquals("Hello \"World\" \n\t Special: <>&", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  void testSerializationFormat() throws Exception {
    String json = "{\"name\":\"testVar\",\"value\":\"testValue\",\"type\":\"Text\"}";

    Variable variable = objectMapper.readValue(json, Variable.class);
    String serialized = objectMapper.writeValueAsString(variable);

    Variable deserialized = objectMapper.readValue(serialized, Variable.class);

    assertEquals(variable.getName(), deserialized.getName());
    assertEquals(variable.getValue(), deserialized.getValue());
    assertEquals(variable.getType(), deserialized.getType());
  }
}
