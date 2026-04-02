package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BotSessionRequestDTO {

  private final String externalSessionKey;
  private final InstanceConfigDTO instanceConfig;
  private final boolean bypassUser;
  private final List<VariableDTO> variables;

  public BotSessionRequestDTO(String externalSessionKey, InstanceConfigDTO instanceConfig, Boolean byPassUser,
                              List<VariableDTO> variables) {

    this.externalSessionKey = externalSessionKey;
    this.instanceConfig = instanceConfig;
    this.bypassUser = byPassUser;
    this.variables = variables;
  }

  public String getExternalSessionKey() {
    return externalSessionKey;
  }

  public InstanceConfigDTO getInstanceConfig() {
    return instanceConfig;
  }

  public boolean getBypassUser() {
    return bypassUser;
  }

  public List<VariableDTO> getVariables() {
    return variables;
  }
}
