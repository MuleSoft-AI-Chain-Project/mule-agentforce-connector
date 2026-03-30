package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BotContinueSessionRequestDTO {

  private final Message message;
  private final List<VariableDTO> variables;

  public BotContinueSessionRequestDTO(Message message, List<VariableDTO> variables) {
    this.message = message;
    this.variables = variables;
  }

  public Message getMessage() {
    return message;
  }

  public List<VariableDTO> getVariables() {
    return variables;
  }

  @JsonInclude(JsonInclude.Include.NON_DEFAULT)
  public static class Message {

    private String type;
    private int sequenceId;
    private String text;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public int getSequenceId() {
      return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
      this.sequenceId = sequenceId;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }
}
