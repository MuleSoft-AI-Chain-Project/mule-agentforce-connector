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

  private List<MessageMetadata> messagesMetadata;

  public AgentResponseMetadata() {}

  public AgentResponseMetadata(Links links, List<MessageMetadata> messagesMetadata) {
    this.links = links;
    this.messagesMetadata = messagesMetadata;
  }

  public Links getLinks() {
    return links;
  }

  public List<MessageMetadata> getMessagesMetadata() {
    return messagesMetadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof AgentResponseMetadata))
      return false;
    AgentResponseMetadata that = (AgentResponseMetadata) o;
    return Objects.equals(getLinks(), that.getLinks()) &&
        Objects.equals(getMessagesMetadata(), that.getMessagesMetadata());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLinks(), getMessagesMetadata());
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class MessageMetadata implements Serializable {

    private String id;
    private String type;
    private String feedbackId;
    private String planId;
    private boolean isContentSafe;
    private Map<String, Object> metrics;

    // Error metadata
    private Integer httpStatus;
    private Long timestamp;
    private boolean expected;
    private String traceId;

    public MessageMetadata() {}

    public MessageMetadata(String id, String type, String feedbackId, String planId, boolean isContentSafe,
                           Map<String, Object> metrics, Integer httpStatus, Long timestamp, boolean expected,
                           String traceId) {
      this.id = id;
      this.type = type;
      this.feedbackId = feedbackId;
      this.planId = planId;
      this.isContentSafe = isContentSafe;
      this.metrics = metrics;
      this.httpStatus = httpStatus;
      this.timestamp = timestamp;
      this.expected = expected;
      this.traceId = traceId;
    }

    public String getId() {
      return id;
    }

    public String getType() {
      return type;
    }

    public String getFeedbackId() {
      return feedbackId;
    }

    public String getPlanId() {
      return planId;
    }

    public boolean getIsContentSafe() {
      return isContentSafe;
    }

    public Map<String, Object> getMetrics() {
      return metrics;
    }

    public Integer getHttpStatus() {
      return httpStatus;
    }

    public Long getTimestamp() {
      return timestamp;
    }

    public boolean getExpected() {
      return expected;
    }

    public String getTraceId() {
      return traceId;
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
          Objects.equals(getMetrics(), that.getMetrics()) &&
          Objects.equals(getHttpStatus(), that.getHttpStatus()) &&
          Objects.equals(getTimestamp(), that.getTimestamp()) &&
          Objects.equals(getExpected(), that.getExpected()) &&
          Objects.equals(getTraceId(), that.getTraceId());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId(), getType(), getFeedbackId(), getPlanId(), getIsContentSafe(), getMetrics(),
                          getHttpStatus(), getTimestamp(), getExpected(), getTraceId());
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Links implements Serializable {

    private Link self;
    private Link messages;
    private Link session;
    private Link end;

    public Links() {}

    public Links(Link self, Link messages, Link session, Link end) {
      this.self = self;
      this.messages = messages;
      this.session = session;
      this.end = end;
    }

    public Link getSelf() {
      return self;
    }

    public Link getMessages() {
      return messages;
    }

    public Link getSession() {
      return session;
    }

    public Link getEnd() {
      return end;
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
          Objects.equals(getSession(), links.getSession()) &&
          Objects.equals(getEnd(), links.getEnd());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getSelf(), getMessages(), getSession(), getEnd());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link implements Serializable {

      private String href;

      public Link() {}

      public Link(String href) {
        this.href = href;
      }

      public String getHref() {
        return href;
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
