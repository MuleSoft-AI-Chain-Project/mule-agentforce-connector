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

  public void setMessages(List<Message> messages) {
    this.messages = messages;
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

  @Override
  public String toString() {
    return "InvokeAgentResponseAttributes{" +
        "messages=" + messages +
        '}';
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

    public void setCollect(List<Collect> collect) {
      this.collect = collect;
    }

    public void setType(String type) {
      this.type = type;
    }

    public void setId(String id) {
      this.id = id;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public void setContentSafe(boolean contentSafe) {
      this.isContentSafe = contentSafe;
    }

    @Override
    public String toString() {
      return "Message{" +
          "type='" + type + '\'' +
          ", id='" + id + '\'' +
          ", feedbackId='" + feedbackId + '\'' +
          ", planId='" + planId + '\'' +
          ", isContentSafe=" + isContentSafe +
          ", message='" + message + '\'' +
          ", reason='" + reason + '\'' +
          ", collect=" + collect +
          '}';
    }
  }
}
