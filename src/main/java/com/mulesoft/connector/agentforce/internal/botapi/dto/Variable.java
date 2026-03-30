package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Variable {

  @Parameter
  private String name;

  @Parameter
  private String value;

  @Parameter
  private String type;

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public String getType() {
    return type;
  }
}
