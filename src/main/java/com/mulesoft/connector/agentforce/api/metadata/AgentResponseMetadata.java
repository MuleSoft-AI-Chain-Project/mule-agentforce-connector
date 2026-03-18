package com.mulesoft.connector.agentforce.api.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentResponseMetadata implements Serializable {

  @JsonProperty("_links")
  private Links links;

  private List<MessageMetadata> messageMetadata;

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public List<MessageMetadata> getMessageMetadata() {
    return messageMetadata;
  }

  public void setMessageMetadata(List<MessageMetadata> messageMetadata) {
    this.messageMetadata = messageMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof AgentResponseMetadata))
      return false;
    AgentResponseMetadata that = (AgentResponseMetadata) o;
    return Objects.equals(getLinks(), that.getLinks()) &&
        Objects.equals(getMessageMetadata(), that.getMessageMetadata());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLinks(), getMessageMetadata());
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class MessageMetadata implements Serializable {

    private String id;
    private String type;
    private String feedbackId;
    private String planId;
    private Boolean isContentSafe;
    private Map<String, Object> metrics;

    // Streaming metadata
    private Integer offset;
    private String lightningType;

    // Error metadata
    private Integer httpStatus;
    private Long timestamp;
    private Boolean expected;
    private String traceId;
    private String botMode;

    // Session management metadata
    private String conversationId;
    private String accessToken;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getFeedbackId() {
      return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
      this.feedbackId = feedbackId;
    }

    public String getPlanId() {
      return planId;
    }

    public void setPlanId(String planId) {
      this.planId = planId;
    }

    public Boolean getIsContentSafe() {
      return isContentSafe;
    }

    public void setIsContentSafe(Boolean isContentSafe) {
      this.isContentSafe = isContentSafe;
    }

    public Map<String, Object> getMetrics() {
      return metrics;
    }

    public void setMetrics(Map<String, Object> metrics) {
      this.metrics = metrics;
    }

    public Integer getOffset() {
      return offset;
    }

    public void setOffset(Integer offset) {
      this.offset = offset;
    }

    public String getLightningType() {
      return lightningType;
    }

    public void setLightningType(String lightningType) {
      this.lightningType = lightningType;
    }

    public Integer getHttpStatus() {
      return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
      this.httpStatus = httpStatus;
    }

    public Long getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
    }

    public Boolean getExpected() {
      return expected;
    }

    public void setExpected(Boolean expected) {
      this.expected = expected;
    }

    public String getTraceId() {
      return traceId;
    }

    public void setTraceId(String traceId) {
      this.traceId = traceId;
    }

    public String getBotMode() {
      return botMode;
    }

    public void setBotMode(String botMode) {
      this.botMode = botMode;
    }

    public String getConversationId() {
      return conversationId;
    }

    public void setConversationId(String conversationId) {
      this.conversationId = conversationId;
    }

    public String getAccessToken() {
      return accessToken;
    }

    public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof MessageMetadata))
        return false;
      MessageMetadata that = (MessageMetadata) o;
      return Objects.equals(getId(), that.getId()) &&
          Objects.equals(getType(), that.getType()) &&
          Objects.equals(getFeedbackId(), that.getFeedbackId()) &&
          Objects.equals(getPlanId(), that.getPlanId()) &&
          Objects.equals(getIsContentSafe(), that.getIsContentSafe()) &&
          Objects.equals(getMetrics(), that.getMetrics());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId(), getType(), getFeedbackId(), getPlanId(), getIsContentSafe(), getMetrics());
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Links implements Serializable {

    private Link self;
    private Link messages;
    private Link messagesStream;
    private Link session;
    private Link end;

    public Link getSelf() {
      return self;
    }

    public void setSelf(Link self) {
      this.self = self;
    }

    public Link getMessages() {
      return messages;
    }

    public void setMessages(Link messages) {
      this.messages = messages;
    }

    public Link getMessagesStream() {
      return messagesStream;
    }

    public void setMessagesStream(Link messagesStream) {
      this.messagesStream = messagesStream;
    }

    public Link getSession() {
      return session;
    }

    public void setSession(Link session) {
      this.session = session;
    }

    public Link getEnd() {
      return end;
    }

    public void setEnd(Link end) {
      this.end = end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Links))
        return false;
      Links links = (Links) o;
      return Objects.equals(getSelf(), links.getSelf()) &&
          Objects.equals(getMessages(), links.getMessages()) &&
          Objects.equals(getMessagesStream(), links.getMessagesStream()) &&
          Objects.equals(getSession(), links.getSession()) &&
          Objects.equals(getEnd(), links.getEnd());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getSelf(), getMessages(), getMessagesStream(), getSession(), getEnd());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link implements Serializable {

      private String href;

      public String getHref() {
        return href;
      }

      public void setHref(String href) {
        this.href = href;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o)
          return true;
        if (!(o instanceof Link))
          return false;
        Link link = (Link) o;
        return Objects.equals(getHref(), link.getHref());
      }

      @Override
      public int hashCode() {
        return Objects.hash(getHref());
      }
    }
  }
}
