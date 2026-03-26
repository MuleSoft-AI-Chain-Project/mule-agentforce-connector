package com.mulesoft.connector.agentforce.internal.botapi.helpers;

import com.mulesoft.connector.agentforce.api.metadata.AgentBusinessDataResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentApiResponseDTO;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public enum MessageTypeHandler {

  INFORM("Inform", (source, target) -> {
    target.setMessage(source.getMessage());
    target.setResult(source.getResult());
    target.setCitedReferences(source.getCitedReferences());
  }),

  INQUIRE("Inquire", (source, target) -> {
    target.setMessage(source.getMessage());
    target.setCollect(source.getCollect());
  }),

  CONFIRM("Confirm", (source, target) -> {
    target.setMessage(source.getMessage());
    target.setConfirm(source.getConfirm());
  }),

  FAILURE("Failure", (source, target) -> {
    target.setCode(source.getCode());
    target.setMessage(source.getMessage());
    target.setErrors(source.getErrors());
  }),

  SESSION_ENDED("SessionEnded", (source, target) -> {
    target.setReason(source.getReason());
  }),

  // Escalation types
  ESCALATE("Escalate", (source, target) -> {
    target.setTargets(source.getTargets());
  }),

  // Error type
  ERROR("Error", (source, target) -> {
    target.setError(source.getError());
    target.setMessage(source.getMessage());
  }),

  // Progress indicator type
  PROGRESS_INDICATOR("ProgressIndicator", (source, target) -> {
    target.setIndicatorType(source.getIndicatorType());
    target.setMessage(source.getMessage());
  });

  private final String typeName;
  private final BiConsumer<AgentApiResponseDTO.Message, AgentBusinessDataResponseDTO.BusinessDataMessage> handler;

  private static final Map<String, MessageTypeHandler> TYPE_MAP = Arrays.stream(values())
      .collect(Collectors.toMap(MessageTypeHandler::getTypeName, h -> h));

  MessageTypeHandler(String typeName,
                     BiConsumer<AgentApiResponseDTO.Message, AgentBusinessDataResponseDTO.BusinessDataMessage> handler) {
    this.typeName = typeName;
    this.handler = handler;
  }

  public String getTypeName() {
    return typeName;
  }

  public static MessageTypeHandler fromTypeName(String typeName) {
    return TYPE_MAP.get(typeName);
  }

  public void apply(AgentApiResponseDTO.Message source,
                    AgentBusinessDataResponseDTO.BusinessDataMessage target) {
    handler.accept(source, target);
  }
}
