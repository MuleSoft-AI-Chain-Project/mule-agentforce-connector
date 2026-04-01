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

    private final String type;
    private final String message;

    // Inform fields
    private final List<Map<String, Object>> result;
    private final List<Map<String, Object>> citedReferences;

    // Inquire fields
    private final List<Collect> collect;

    // Confirm fields
    private final List<Map<String, Object>> confirm;

    // Failure fields
    private final String code;
    private final List<Object> errors;

    // SessionEnded fields
    private final String reason;

    // ProgressIndicator fields
    private final String indicatorType;

    // Escalate fields
    private final List<Map<String, Object>> targets;

    // Error fields
    private final String error;

    private BusinessDataMessage(Builder builder) {
      this.type = builder.type;
      this.message = builder.message;
      this.result = builder.result;
      this.citedReferences = builder.citedReferences;
      this.collect = builder.collect;
      this.confirm = builder.confirm;
      this.code = builder.code;
      this.errors = builder.errors;
      this.reason = builder.reason;
      this.indicatorType = builder.indicatorType;
      this.targets = builder.targets;
      this.error = builder.error;
    }

    public String getType() {
      return type;
    }

    public String getMessage() {
      return message;
    }

    public List<Map<String, Object>> getResult() {
      return result;
    }

    public List<Map<String, Object>> getCitedReferences() {
      return citedReferences;
    }

    public List<Collect> getCollect() {
      return collect;
    }

    public List<Map<String, Object>> getConfirm() {
      return confirm;
    }

    public String getCode() {
      return code;
    }

    public List<Object> getErrors() {
      return errors;
    }

    public String getReason() {
      return reason;
    }

    public String getIndicatorType() {
      return indicatorType;
    }

    public List<Map<String, Object>> getTargets() {
      return targets;
    }

    public String getError() {
      return error;
    }

    public static class Builder {

      private String type;
      private String message;
      private List<Map<String, Object>> result;
      private List<Map<String, Object>> citedReferences;
      private List<Collect> collect;
      private List<Map<String, Object>> confirm;
      private String code;
      private List<Object> errors;
      private String reason;
      private String indicatorType;
      private List<Map<String, Object>> targets;
      private String error;

      public Builder(String type) {
        this.type = type;
      }

      public Builder message(String message) {
        this.message = message;
        return this;
      }

      public Builder result(List<Map<String, Object>> result) {
        this.result = result;
        return this;
      }

      public Builder citedReferences(List<Map<String, Object>> citedReferences) {
        this.citedReferences = citedReferences;
        return this;
      }

      public Builder collect(List<Collect> collect) {
        this.collect = collect;
        return this;
      }

      public Builder confirm(List<Map<String, Object>> confirm) {
        this.confirm = confirm;
        return this;
      }

      public Builder code(String code) {
        this.code = code;
        return this;
      }

      public Builder errors(List<Object> errors) {
        this.errors = errors;
        return this;
      }

      public Builder reason(String reason) {
        this.reason = reason;
        return this;
      }

      public Builder indicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
        return this;
      }

      public Builder targets(List<Map<String, Object>> targets) {
        this.targets = targets;
        return this;
      }

      public Builder error(String error) {
        this.error = error;
        return this;
      }

      public BusinessDataMessage build() {
        return new BusinessDataMessage(this);
      }
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
