package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Serializable {

  private String type;
  private Value value;
  private String property;

  public String getType() {
    return type;
  }

  public Value getValue() {
    return value;
  }

  public String getProperty() {
    return property;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Value implements Serializable {

    private String promptResponse;

    public String getPromptResponse() {
      return promptResponse;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass())
        return false;
      Value value = (Value) o;
      return Objects.equals(promptResponse, value.promptResponse);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(promptResponse);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    Result result = (Result) o;
    return Objects.equals(type, result.type) && Objects.equals(value, result.value) && Objects.equals(property, result.property);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value, property);
  }
}
