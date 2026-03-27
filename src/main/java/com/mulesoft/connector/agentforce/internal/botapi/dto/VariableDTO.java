package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariableDTO {

  private String name;
  private Object value;
  private String type;

  public VariableDTO() {}

  public String getName() {
    return name;
  }

  public Object getValue() {
    return value;
  }

  public String getType() {
    return type;
  }
}
