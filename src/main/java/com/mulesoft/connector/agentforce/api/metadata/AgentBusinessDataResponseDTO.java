package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentBusinessDataResponseDTO {

  private List<BusinessDataMessage> messages;

  public AgentBusinessDataResponseDTO(List<BusinessDataMessage> messages) {
    this.messages = messages;
  }

  public List<BusinessDataMessage> getMessages() {
    return messages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof AgentBusinessDataResponseDTO))
      return false;
    AgentBusinessDataResponseDTO that = (AgentBusinessDataResponseDTO) o;
    return Objects.equals(getMessages(), that.getMessages());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMessages());
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
    private List<Object> errors;

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

    public List<Object> getErrors() {
      return errors;
    }

    public void setErrors(List<Object> errors) {
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

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof BusinessDataMessage))
        return false;
      BusinessDataMessage that = (BusinessDataMessage) o;
      return Objects.equals(getType(), that.getType()) &&
          Objects.equals(getMessage(), that.getMessage()) &&
          Objects.equals(getResult(), that.getResult()) &&
          Objects.equals(getCitedReferences(), that.getCitedReferences()) &&
          Objects.equals(getCollect(), that.getCollect()) &&
          Objects.equals(getConfirm(), that.getConfirm()) &&
          Objects.equals(getCode(), that.getCode()) &&
          Objects.equals(getErrors(), that.getErrors()) &&
          Objects.equals(getReason(), that.getReason()) &&
          Objects.equals(getIndicatorType(), that.getIndicatorType()) &&
          Objects.equals(getTargets(), that.getTargets()) &&
          Objects.equals(getError(), that.getError());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getType(), getMessage(), getResult(), getCitedReferences(),
                          getCollect(), getConfirm(), getCode(), getErrors(), getReason(),
                          getIndicatorType(), getTargets(), getError());
    }
  }
}
