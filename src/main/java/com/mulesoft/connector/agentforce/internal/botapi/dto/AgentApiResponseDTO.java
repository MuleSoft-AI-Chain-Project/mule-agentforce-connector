package com.mulesoft.connector.agentforce.internal.botapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mulesoft.connector.agentforce.api.metadata.Collect;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Internal class for deserializing complete API responses from Agentforce API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentApiResponseDTO implements Serializable {

  private List<Message> messages;

  @JsonProperty("_links")
  private Links links;

  public List<Message> getMessages() {
    return messages;
  }

  public Links getLinks() {
    return links;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Message implements Serializable {

    // Core metadata fields
    private String type;
    private String id;
    private String feedbackId;
    private String planId;

    @JsonProperty("isContentSafe")
    private Boolean isContentSafe;
    private Map<String, Object> metrics;

    // Common business field
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

    // Chunk fields
    private Integer offset;
    private Object value;
    private String lightningType;

    // Escalate fields
    private List<Map<String, Object>> targets;

    // EscalateToConversationMessage fields
    private String conversationId;
    private String accessToken;

    // Error fields
    private Integer httpStatus;
    private String error;
    private Long timestamp;
    private Boolean expected;
    private String traceId;
    private String botMode;

    // Getters only (no setters - Jackson deserializes via fields)

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

    public Boolean getIsContentSafe() {
      return isContentSafe;
    }

    public Map<String, Object> getMetrics() {
      return metrics;
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

    public List<Map<String, Object>> getErrors() {
      return errors;
    }

    public String getReason() {
      return reason;
    }

    public String getIndicatorType() {
      return indicatorType;
    }

    public Integer getOffset() {
      return offset;
    }

    public Object getValue() {
      return value;
    }

    public String getLightningType() {
      return lightningType;
    }

    public List<Map<String, Object>> getTargets() {
      return targets;
    }

    public String getConversationId() {
      return conversationId;
    }

    public String getAccessToken() {
      return accessToken;
    }

    public Integer getHttpStatus() {
      return httpStatus;
    }

    public String getError() {
      return error;
    }

    public Long getTimestamp() {
      return timestamp;
    }

    public Boolean getExpected() {
      return expected;
    }

    public String getTraceId() {
      return traceId;
    }

    public String getBotMode() {
      return botMode;
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

    public Link getMessages() {
      return messages;
    }

    public Link getMessagesStream() {
      return messagesStream;
    }

    public Link getSession() {
      return session;
    }

    public Link getEnd() {
      return end;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link implements Serializable {

      private String href;

      public String getHref() {
        return href;
      }
    }
  }
}
