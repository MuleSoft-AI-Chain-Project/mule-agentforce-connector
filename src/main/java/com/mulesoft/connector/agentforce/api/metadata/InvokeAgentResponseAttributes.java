package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvokeAgentResponseAttributes implements Serializable {

  private List<Message> messages;

  public List<Message> getMessages() {
    return messages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof InvokeAgentResponseAttributes))
      return false;
    InvokeAgentResponseAttributes that = (InvokeAgentResponseAttributes) o;
    return Objects.equals(getMessages(), that.getMessages());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMessages());
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Message implements Serializable {

    private String type;
    private String id;
    private String feedbackId;
    private String planId;
    @JsonProperty("isContentSafe")
    private boolean isContentSafe;
    private String message;
    private String reason;
    private List<Collect> collect;

    public String getType() {
      return type;
    }

    public String getId() {
      return id;
    }

    public String getFeedbackId() {
      return feedbackId;
    }

    public String getPlanId() {
      return planId;
    }

    public boolean isContentSafe() {
      return isContentSafe;
    }

    public String getMessage() {
      return message;
    }

    public String getReason() {
      return reason;
    }

    public List<Collect> getCollect() {
      return collect;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Message))
        return false;
      Message message1 = (Message) o;
      return isContentSafe == message1.isContentSafe &&
          Objects.equals(type, message1.type) &&
          Objects.equals(id, message1.id) &&
          Objects.equals(feedbackId, message1.feedbackId) &&
          Objects.equals(planId, message1.planId) &&
          Objects.equals(message, message1.message) &&
          Objects.equals(reason, message1.reason) &&
          Objects.equals(collect, message1.collect);
    }

    @Override
    public int hashCode() {
      return Objects.hash(type, id, feedbackId, planId, isContentSafe, message, reason, collect);
    }
  }
}
