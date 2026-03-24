package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentBusinessDataResponseDTO {

  private List<BusinessDataMessage> messages;

  public AgentBusinessDataResponseDTO(List<BusinessDataMessage> messages) {
    this.messages = messages;
  }

  public List<BusinessDataMessage> getMessages() {
    return messages;
  }

  public void setMessages(List<BusinessDataMessage> messages) {
    this.messages = messages;
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class BusinessDataMessage {

    private String type;
    private String message;

    // Inform fields
    private List<Map<String, Object>> result;
    private List<Map<String, Object>> citedReferences;

    // Inquire fields
    private List<Collect> collect;

    // Confirm fields
    private List<Map<String, Object>> confirm;

    // Failure fields
    private String code;
    private List<Map<String, Object>> errors;

    // SessionEnded fields
    private String reason;

    // ProgressIndicator fields
    private String indicatorType;

    // Escalate fields
    private List<Map<String, Object>> targets;

    // Error fields
    private String error;

    public BusinessDataMessage(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public List<Map<String, Object>> getResult() {
      return result;
    }

    public void setResult(List<Map<String, Object>> result) {
      this.result = result;
    }

    public List<Map<String, Object>> getCitedReferences() {
      return citedReferences;
    }

    public void setCitedReferences(List<Map<String, Object>> citedReferences) {
      this.citedReferences = citedReferences;
    }

    public List<Collect> getCollect() {
      return collect;
    }

    public void setCollect(List<Collect> collect) {
      this.collect = collect;
    }

    public List<Map<String, Object>> getConfirm() {
      return confirm;
    }

    public void setConfirm(List<Map<String, Object>> confirm) {
      this.confirm = confirm;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public List<Map<String, Object>> getErrors() {
      return errors;
    }

    public void setErrors(List<Map<String, Object>> errors) {
      this.errors = errors;
    }

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public String getIndicatorType() {
      return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
      this.indicatorType = indicatorType;
    }

    public List<Map<String, Object>> getTargets() {
      return targets;
    }

    public void setTargets(List<Map<String, Object>> targets) {
      this.targets = targets;
    }

    public String getError() {
      return error;
    }

    public void setError(String error) {
      this.error = error;
    }
  }
}
