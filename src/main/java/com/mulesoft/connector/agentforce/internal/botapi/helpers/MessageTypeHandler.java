package com.mulesoft.connector.agentforce.internal.botapi.helpers;

import com.mulesoft.connector.agentforce.api.metadata.AgentBusinessDataResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentApiResponseDTO;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public enum MessageTypeHandler {

  INFORM("Inform", (source, target) -> {
    target.message(source.getMessage());
    target.result(source.getResult());
    target.citedReferences(source.getCitedReferences());
  }),

  INQUIRE("Inquire", (source, target) -> {
    target.message(source.getMessage());
    target.collect(source.getCollect());
  }),

  CONFIRM("Confirm", (source, target) -> {
    target.message(source.getMessage());
    target.confirm(source.getConfirm());
  }),

  FAILURE("Failure", (source, target) -> {
    target.code(source.getCode());
    target.message(source.getMessage());
    target.errors(source.getErrors());
  }),

  SESSION_ENDED("SessionEnded", (source, target) -> target.reason(source.getReason())),

  // Escalation types
  ESCALATE("Escalate", (source, target) -> target.targets(source.getTargets())),

  // Error type
  ERROR("Error", (source, target) -> {
    target.error(source.getError());
    target.message(source.getMessage());
  }),

  // Progress indicator type
  PROGRESS_INDICATOR("ProgressIndicator", (source, target) -> {
    target.indicatorType(source.getIndicatorType());
    target.message(source.getMessage());
  });

  private final String typeName;
  private final BiConsumer<AgentApiResponseDTO.Message, AgentBusinessDataResponseDTO.BusinessDataMessage.Builder> handler;

  private static final Map<String, MessageTypeHandler> TYPE_MAP = Arrays.stream(values())
      .collect(Collectors.toMap(MessageTypeHandler::getTypeName, h -> h));

  MessageTypeHandler(String typeName,
                     BiConsumer<AgentApiResponseDTO.Message, AgentBusinessDataResponseDTO.BusinessDataMessage.Builder> handler) {
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
                    AgentBusinessDataResponseDTO.BusinessDataMessage.Builder target) {
    handler.accept(source, target);
  }
}
