package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class VariableDTOTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
  }

  // Helper for round-trip testing
  private VariableDTO roundTrip(String json) throws Exception {
    VariableDTO variable = objectMapper.readValue(json, VariableDTO.class);
    String serialized = objectMapper.writeValueAsString(variable);
    return objectMapper.readValue(serialized, VariableDTO.class);
  }

  @Test
  public void testBooleanVariable_True_RoundTrip() throws Exception {
    String json = "{\"name\":\"isShipped\",\"value\":true,\"type\":\"Boolean\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("isShipped", variable.getName());
    assertEquals(true, variable.getValue());
    assertEquals("Boolean", variable.getType());
  }

  @Test
  public void testBooleanVariable_False_RoundTrip() throws Exception {
    String json = "{\"name\":\"isActive\",\"value\":false,\"type\":\"Boolean\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("isActive", variable.getName());
    assertEquals(false, variable.getValue());
    assertEquals("Boolean", variable.getType());
  }

  @Test
  public void testTextVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"greeting\",\"value\":\"Thanks for your order!\",\"type\":\"Text\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("greeting", variable.getName());
    assertEquals("Thanks for your order!", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  public void testNumberVariable_Integer_RoundTrip() throws Exception {
    String json = "{\"name\":\"quantity\",\"value\":42,\"type\":\"Number\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("quantity", variable.getName());
    assertEquals(42, variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  public void testNumberVariable_Double_RoundTrip() throws Exception {
    String json = "{\"name\":\"price\",\"value\":99.99,\"type\":\"Number\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("price", variable.getName());
    assertEquals(99.99, variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  public void testNumberVariable_Zero_RoundTrip() throws Exception {
    String json = "{\"name\":\"count\",\"value\":0,\"type\":\"Number\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("count", variable.getName());
    assertEquals(0, variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  public void testNumberVariable_Negative_RoundTrip() throws Exception {
    String json = "{\"name\":\"balance\",\"value\":-50.75,\"type\":\"Number\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("balance", variable.getName());
    assertEquals(-50.75, variable.getValue());
    assertEquals("Number", variable.getType());
  }

  @Test
  public void testDateVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"deliveryDate\",\"value\":\"2021-09-21\",\"type\":\"Date\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("deliveryDate", variable.getName());
    assertEquals("2021-09-21", variable.getValue());
    assertEquals("Date", variable.getType());
  }

  @Test
  public void testDateTimeVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"orderTime\",\"value\":\"2018-09-21T14:30:00\",\"type\":\"DateTime\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("orderTime", variable.getName());
    assertEquals("2018-09-21T14:30:00", variable.getValue());
    assertEquals("DateTime", variable.getType());
  }

  @Test
  public void testMoneyVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"totalAmount\",\"value\":\"USD 10.40\",\"type\":\"Money\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("totalAmount", variable.getName());
    assertEquals("USD 10.40", variable.getValue());
    assertEquals("Money", variable.getType());
  }

  @Test
  public void testRefVariable_RoundTrip() throws Exception {
    String json = "{\"name\":\"accountRef\",\"value\":\"1M5xx000000000BCAQ\",\"type\":\"Ref\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("accountRef", variable.getName());
    assertEquals("1M5xx000000000BCAQ", variable.getValue());
    assertEquals("Ref", variable.getType());
  }

  @Test
  public void testObjectVariable_WithNestedData_RoundTrip() throws Exception {
    String json =
        "{\"name\":\"customer\",\"value\":[{\"name\":\"fullName\",\"type\":\"Text\",\"value\":\"Matt Smith\"}],\"type\":\"Object\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("customer", variable.getName());
    assertEquals("Object", variable.getType());
    assertNotNull(variable.getValue());
    assertTrue(variable.getValue() instanceof List);

    @SuppressWarnings("unchecked")
    List<Map<String, Object>> valueList = (List<Map<String, Object>>) variable.getValue();
    assertEquals(1, valueList.size());
    Map<String, Object> nestedVar = valueList.get(0);
    assertEquals("fullName", nestedVar.get("name"));
    assertEquals("Text", nestedVar.get("type"));
    assertEquals("Matt Smith", nestedVar.get("value"));
  }

  @Test
  public void testListVariable_WithMultipleItems_RoundTrip() throws Exception {
    String json =
        "{\"name\":\"accountIds\",\"value\":[{\"type\":\"ref\",\"value\":\"1M5xx000000000BCAQ\"},{\"type\":\"ref\",\"value\":\"1M5xx000000000BCBR\"}],\"type\":\"List\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("accountIds", variable.getName());
    assertEquals("List", variable.getType());
    assertNotNull(variable.getValue());
    assertTrue(variable.getValue() instanceof List);

    @SuppressWarnings("unchecked")
    List<Map<String, Object>> valueList = (List<Map<String, Object>>) variable.getValue();
    assertEquals(2, valueList.size());
    assertEquals("ref", valueList.get(0).get("type"));
    assertEquals("1M5xx000000000BCAQ", valueList.get(0).get("value"));
    assertEquals("ref", valueList.get(1).get("type"));
    assertEquals("1M5xx000000000BCBR", valueList.get(1).get("value"));
  }

  @Test
  public void testJsonVariable_WithComplexObject_RoundTrip() throws Exception {
    String json =
        "{\"name\":\"orderDetails\",\"value\":{\"orderId\":\"ORD-12345\",\"itemCount\":3,\"isPriority\":true},\"type\":\"Json\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("orderDetails", variable.getName());
    assertEquals("Json", variable.getType());
    assertNotNull(variable.getValue());
    assertTrue(variable.getValue() instanceof Map);

    @SuppressWarnings("unchecked")
    Map<String, Object> jsonValue = (Map<String, Object>) variable.getValue();
    assertEquals("ORD-12345", jsonValue.get("orderId"));
    assertEquals(3, jsonValue.get("itemCount"));
    assertEquals(true, jsonValue.get("isPriority"));
  }

  @Test
  public void testEmptyString_RoundTrip() throws Exception {
    String json = "{\"name\":\"emptyText\",\"value\":\"\",\"type\":\"Text\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("emptyText", variable.getName());
    assertEquals("", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  public void testNullValue_ExcludedFromSerialization() throws Exception {
    String json = "{\"name\":\"optionalField\",\"value\":null,\"type\":\"Text\"}";

    VariableDTO variable = objectMapper.readValue(json, VariableDTO.class);
    assertNull(variable.getValue());

    String serialized = objectMapper.writeValueAsString(variable);

    assertFalse(serialized.contains("\"value\""), "Null value should be excluded from JSON due to @JsonInclude(NON_NULL)");
    assertTrue(serialized.contains("\"name\":\"optionalField\""));
    assertTrue(serialized.contains("\"type\":\"Text\""));
  }

  @Test
  public void testNullValue_Deserialization() throws Exception {
    String json = "{\"name\":\"emptyField\",\"type\":\"Text\",\"value\":null}";

    VariableDTO variable = objectMapper.readValue(json, VariableDTO.class);

    assertEquals("emptyField", variable.getName());
    assertEquals("Text", variable.getType());
    assertNull(variable.getValue());
  }

  @Test
  public void testMultipleVariables_ListDeserialization() throws Exception {
    String json =
        "[{\"name\":\"isActive\",\"value\":true,\"type\":\"Boolean\"},{\"name\":\"userName\",\"value\":\"Alice\",\"type\":\"Text\"},{\"name\":\"score\",\"value\":95,\"type\":\"Number\"}]";

    List<VariableDTO> variables = objectMapper.readValue(json, new TypeReference<List<VariableDTO>>() {});

    assertEquals(3, variables.size());

    assertEquals("isActive", variables.get(0).getName());
    assertEquals(true, variables.get(0).getValue());
    assertEquals("Boolean", variables.get(0).getType());

    assertEquals("userName", variables.get(1).getName());
    assertEquals("Alice", variables.get(1).getValue());
    assertEquals("Text", variables.get(1).getType());

    assertEquals("score", variables.get(2).getName());
    assertEquals(95, variables.get(2).getValue());
    assertEquals("Number", variables.get(2).getType());
  }

  @Test
  public void testSpecialCharacters_RoundTrip() throws Exception {
    String json = "{\"name\":\"message\",\"value\":\"Hello \\\"World\\\" \\n\\t Special: <>&\",\"type\":\"Text\"}";

    VariableDTO variable = roundTrip(json);

    assertEquals("message", variable.getName());
    assertEquals("Hello \"World\" \n\t Special: <>&", variable.getValue());
    assertEquals("Text", variable.getType());
  }

  @Test
  public void testSerializationFormat() throws Exception {
    String json = "{\"name\":\"testVar\",\"value\":\"testValue\",\"type\":\"Text\"}";

    VariableDTO variable = objectMapper.readValue(json, VariableDTO.class);
    String serialized = objectMapper.writeValueAsString(variable);

    VariableDTO deserialized = objectMapper.readValue(serialized, VariableDTO.class);

    assertEquals(variable.getName(), deserialized.getName());
    assertEquals(variable.getValue(), deserialized.getValue());
    assertEquals(variable.getType(), deserialized.getType());
  }
}
