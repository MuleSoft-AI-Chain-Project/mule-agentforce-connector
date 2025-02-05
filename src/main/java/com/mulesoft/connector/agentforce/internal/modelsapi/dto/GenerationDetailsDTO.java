package com.mulesoft.connector.agentforce.internal.modelsapi.dto;

import com.mulesoft.connector.agentforce.api.metadata.ResponseParameters;

import java.util.List;

public class GenerationDetailsDTO {

  private ResponseParameters parameters;
  private List<GenerationsChatFromMessagesDTO> generations;

  public ResponseParameters getParameters() {
    return parameters;
  }

  public List<GenerationsChatFromMessagesDTO> getGenerations() {
    return generations;
  }
}
