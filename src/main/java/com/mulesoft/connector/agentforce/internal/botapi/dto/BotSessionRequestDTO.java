package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BotSessionRequestDTO {

  private final String externalSessionKey;
  private final InstanceConfigDTO instanceConfig;
  private final boolean bypassUser;

  public BotSessionRequestDTO(String externalSessionKey, InstanceConfigDTO instanceConfig, Boolean byPassUser) {

    this.externalSessionKey = externalSessionKey;
    this.instanceConfig = instanceConfig;
    this.bypassUser = byPassUser;
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
}
