package com.mulesoft.connector.agentforce.internal.botapi.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mulesoft.connector.agentforce.api.metadata.AgentResponseMetadata;
import com.mulesoft.connector.agentforce.api.metadata.InvokeAgentResponseAttributes;
import com.mulesoft.connector.agentforce.api.metadata.AgentBusinessDataResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentConversationResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentMetadataResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.BotContinueSessionRequestDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.BotRecord;
import com.mulesoft.connector.agentforce.internal.botapi.dto.BotSessionRequestDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentApiResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.dto.InstanceConfigDTO;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType;
import com.mulesoft.connector.agentforce.internal.params.ReadTimeoutParams;
import org.json.JSONObject;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.core.api.util.IOUtils;
import org.mule.runtime.extension.api.connectivity.oauth.AccessTokenExpiredException;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.http.api.client.HttpRequestOptions;
import org.mule.runtime.http.api.domain.entity.EmptyHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.entity.InputStreamHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.CONTINUE_SESSION_MESSAGE_TYPE_TEXT;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.END_SESSION_REASON_USERREQUEST;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.MESSAGE;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.MESSAGES;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.SESSION_ENDED;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.SESSION_ID;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.SLASH;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.URI_BOT_API_AGENTS;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.URI_BOT_API_MESSAGES;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.URI_BOT_API_METADATA_AGENTLIST;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.URI_BOT_API_METADATA_SERVICES_V_62;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.URI_BOT_API_SESSIONS;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.V6_URI_BOT_API_BOTS;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.X_SESSION_END_REASON;
import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.AGENT_OPERATIONS_FAILURE;
import static org.apache.commons.io.IOUtils.toInputStream;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.ACCEPT_TYPE_STRING;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.AUTHORIZATION;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.CONTENT_TYPE_APPLICATION_JSON;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.CONTENT_TYPE_STRING;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.HTTP_METHOD_DELETE;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.HTTP_METHOD_GET;
import static com.mulesoft.connector.agentforce.internal.botapi.helpers.BotConstantUtil.HTTP_METHOD_POST;

public class BotRequestHelper {

  private static final Logger log = LoggerFactory.getLogger(BotRequestHelper.class);

  private final AgentforceConnection agentforceConnection;
  private final ObjectMapper objectMapper;

  public BotRequestHelper(AgentforceConnection agentforceConnection) {
    this.agentforceConnection = agentforceConnection;
    objectMapper = new ObjectMapper();
  }

  public List<BotRecord> getAgentList() throws IOException, TimeoutException {

    String metadataUrl = agentforceConnection.getSalesforceOrgUrl()
        + URI_BOT_API_METADATA_SERVICES_V_62 + URI_BOT_API_METADATA_AGENTLIST;

    log.info("Executing getAgentList request with URL: {} ", metadataUrl);

    HttpResponse httpResponse = agentforceConnection.getHttpClient().send(buildRequest(metadataUrl, agentforceConnection
        .getAccessToken(), HTTP_METHOD_GET, new EmptyHttpEntity()));

    InputStream inputStream = parseHttpResponse(httpResponse);

    AgentMetadataResponseDTO agentMetadataResponse =
        objectMapper.readValue(inputStream, AgentMetadataResponseDTO.class);

    return agentMetadataResponse.getRecords();
  }


  public void startSession(String agentId, boolean byPassUser, ReadTimeoutParams readTimeout,
                           CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback)
      throws IOException {

    String startSessionUrl = agentforceConnection.getApiInstanceUrl() + V6_URI_BOT_API_BOTS + URI_BOT_API_AGENTS
        + agentId + URI_BOT_API_SESSIONS;
    String externalSessionKey = UUID.randomUUID().toString();
    String endpoint = agentforceConnection.getSalesforceOrgUrl();
    BotSessionRequestDTO payload = createStartSessionRequestPayload(externalSessionKey, endpoint, byPassUser);

    log.debug("Agentforce start session details. Request URL: {}, external Session Key:{}," +
        " endpoint: {}", startSessionUrl, externalSessionKey, endpoint);

    InputStream payloadStream = new ByteArrayInputStream(objectMapper.writeValueAsString(payload)
        .getBytes(StandardCharsets.UTF_8));

    sendRequest(startSessionUrl, HTTP_METHOD_POST, payloadStream, readTimeout, callback, this::parseResponseForStartSession);
  }

  public void continueSession(InputStream message, String sessionId, int messageSequenceNumber,
                              ReadTimeoutParams readTimeout,
                              CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback)
      throws IOException {

    String continueSessionUrl =
        agentforceConnection.getApiInstanceUrl() + V6_URI_BOT_API_BOTS + URI_BOT_API_SESSIONS + SLASH + sessionId
            + URI_BOT_API_MESSAGES;

    BotContinueSessionRequestDTO payload =
        createContinueSessionRequestPayload(IOUtils.toString(message), messageSequenceNumber);

    log.info("Agentforce continue session details. Request URL: {}, Session ID:{}", continueSessionUrl, sessionId);

    InputStream payloadStream = new ByteArrayInputStream(objectMapper.writeValueAsString(payload)
        .getBytes(StandardCharsets.UTF_8));

    sendRequest(continueSessionUrl, HTTP_METHOD_POST, payloadStream, readTimeout, callback,
                this::parseResponseForContinueSession);
  }

  public void sendMessageSync(InputStream message, String sessionId, int messageSequenceNumber,
                              ReadTimeoutParams readTimeout,
                              CompletionCallback<InputStream, AgentResponseMetadata> callback)
      throws IOException {

    String continueSessionUrl =
        agentforceConnection.getApiInstanceUrl() + V6_URI_BOT_API_BOTS + URI_BOT_API_SESSIONS + SLASH + sessionId
            + URI_BOT_API_MESSAGES;

    BotContinueSessionRequestDTO payload =
        createContinueSessionRequestPayload(IOUtils.toString(message), messageSequenceNumber);

    log.info("Agentforce send message sync details. Request URL: {}, Session ID:{}", continueSessionUrl, sessionId);

    InputStream payloadStream = new ByteArrayInputStream(objectMapper.writeValueAsString(payload)
        .getBytes(StandardCharsets.UTF_8));

    sendRequest(continueSessionUrl, HTTP_METHOD_POST, payloadStream, readTimeout, callback,
                this::parseResponseForSendMessageSync);
  }

  public void endSession(String sessionId, ReadTimeoutParams readTimeout,
                         CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback) {

    String endSessionUrl =
        agentforceConnection.getApiInstanceUrl() + V6_URI_BOT_API_BOTS + URI_BOT_API_SESSIONS + SLASH + sessionId;

    log.debug("Agentforce end session details. Request URL: {}, Session ID:{}", endSessionUrl, sessionId);

    sendRequest(endSessionUrl, HTTP_METHOD_DELETE, null, readTimeout, callback, this::parseResponseForDeleteSession);
  }

  private BotSessionRequestDTO createStartSessionRequestPayload(String externalSessionKey,
                                                                String endpoint,
                                                                boolean byPassUser) {

    InstanceConfigDTO instanceConfigDTO = new InstanceConfigDTO();
    instanceConfigDTO.setEndpoint(endpoint);
    return new BotSessionRequestDTO(externalSessionKey, instanceConfigDTO, byPassUser);
  }

  private BotContinueSessionRequestDTO createContinueSessionRequestPayload(String message,
                                                                           int messageSequenceNumber) {

    BotContinueSessionRequestDTO.Message messageDTO = new BotContinueSessionRequestDTO.Message();
    messageDTO.setText(message);
    messageDTO.setSequenceId(messageSequenceNumber);
    messageDTO.setType(CONTINUE_SESSION_MESSAGE_TYPE_TEXT);

    return new BotContinueSessionRequestDTO(messageDTO);
  }

  private MultiMap<String, String> addConnectionHeaders(String accessToken) {
    MultiMap<String, String> multiMap = new MultiMap<>();
    multiMap.put(AUTHORIZATION, "Bearer " + accessToken);
    multiMap.put(CONTENT_TYPE_STRING, CONTENT_TYPE_APPLICATION_JSON);
    multiMap.put(ACCEPT_TYPE_STRING, CONTENT_TYPE_APPLICATION_JSON);
    return multiMap;
  }

  private MultiMap<String, String> addConnectionHeadersForDelete(String accessToken) {
    MultiMap<String, String> multiMap = addConnectionHeaders(accessToken);
    multiMap.put(X_SESSION_END_REASON, END_SESSION_REASON_USERREQUEST);
    return multiMap;
  }

  private Result<InputStream, InvokeAgentResponseAttributes> parseResponseForStartSession(InputStream responseStream) {

    AgentConversationResponseDTO responseDTO = parseResponse(responseStream);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("sessionId", responseDTO.getSessionId());
    return Result.<InputStream, InvokeAgentResponseAttributes>builder()
        .output(toInputStream(jsonObject.toString(), StandardCharsets.UTF_8))
        .attributes(responseDTO.getResponseAttributes())
        .attributesMediaType(MediaType.APPLICATION_JAVA)
        .mediaType(MediaType.APPLICATION_JSON)
        .build();
  }

  Result<InputStream, InvokeAgentResponseAttributes> parseResponseForContinueSession(InputStream responseStream) {

    AgentConversationResponseDTO responseDTO = parseResponse(responseStream);

    return Result.<InputStream, InvokeAgentResponseAttributes>builder()
        .output(toInputStream(responseDTO.getText(), StandardCharsets.UTF_8))
        .attributes(responseDTO.getResponseAttributes())
        .attributesMediaType(MediaType.APPLICATION_JAVA)
        .mediaType(MediaType.TEXT)
        .build();
  }

  private Result<InputStream, InvokeAgentResponseAttributes> parseResponseForDeleteSession(InputStream responseStream) {

    AgentConversationResponseDTO responseDTO = parseResponse(responseStream);

    return Result.<InputStream, InvokeAgentResponseAttributes>builder()
        .output(toInputStream(SESSION_ENDED, StandardCharsets.UTF_8))
        .attributes(responseDTO.getResponseAttributes())
        .attributesMediaType(MediaType.APPLICATION_JAVA)
        .mediaType(MediaType.TEXT)
        .build();
  }

  private AgentConversationResponseDTO parseResponse(InputStream responseStream) {
    AgentConversationResponseDTO responseDTO = new AgentConversationResponseDTO();

    try {
      JsonNode rootNode = objectMapper.readTree(responseStream);
      responseDTO.setResponseAttributes(
                                        objectMapper.treeToValue(
                                                                 rootNode, InvokeAgentResponseAttributes.class));
      responseDTO.setSessionId(getTextValue(rootNode, SESSION_ID));
      responseDTO.setText(getMessageText(rootNode));
    } catch (IOException e) {
      throw new ModuleException("Error in parsing response ", AGENT_OPERATIONS_FAILURE, e);
    }
    return responseDTO;
  }

  private String readErrorStream(InputStream errorStream) {
    if (errorStream == null) {
      return "No error details available.";
    }
    try (BufferedReader br = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
      StringBuilder errorResponse = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        errorResponse.append(line.trim());
      }
      return errorResponse.toString();
    } catch (IOException e) {
      log.debug("Error reading error stream", e);
      return "Unable to get response from Agentforce. Could not read reading error details as well.";
    }
  }

  private <T, A> void sendRequest(String url, String httpMethod, InputStream payloadStream,
                                  ReadTimeoutParams readTimeout,
                                  CompletionCallback<T, A> callback,
                                  Function<InputStream, Result<T, A>> responseParser) {
    log.debug("Agentforce request details. Request URL: {}", url);

    HttpRequestOptions httpRequestOptions = HttpRequestOptions.builder()
        .responseTimeout((int) readTimeout.getReadTimeoutUnit().toMillis(readTimeout.getReadTimeout())).build();

    CompletableFuture<HttpResponse> completableFuture = agentforceConnection.getHttpClient().sendAsync(
                                                                                                       buildRequest(url,
                                                                                                                    agentforceConnection
                                                                                                                        .getAccessToken(),
                                                                                                                    httpMethod,
                                                                                                                    payloadStream != null
                                                                                                                        ? new InputStreamHttpEntity(payloadStream)
                                                                                                                        : new EmptyHttpEntity()),
                                                                                                       httpRequestOptions);

    completableFuture.whenComplete((response, exception) -> handleResponse(response, exception, callback, responseParser));
  }

  private <T, A> void handleResponse(HttpResponse response, Throwable exception,
                                     CompletionCallback<T, A> callback,
                                     Function<InputStream, Result<T, A>> responseParser) {
    if (exception != null) {
      log.debug("Exception in bot api call ", exception);
      callback.error(exception);
      return;
    }
    InputStream contentStream = parseHttpResponse(response, callback);
    if (contentStream == null) {
      return;
    }

    try {
      Result<T, A> result = responseParser.apply(contentStream);
      callback.success(result);
    } catch (Exception e) {
      log.debug("Error while parsing response", e);
      callback.error(e);
    }
  }

  private InputStream parseHttpResponse(HttpResponse httpResponse) {

    int statusCode = httpResponse.getStatusCode();
    log.debug("Parsing Http Response, statusCode = {}", statusCode);

    if (statusCode == HttpURLConnection.HTTP_OK) {
      if (httpResponse.getEntity().getContent() == null) {
        throw new ModuleException(
                                  "Error: No response received from Einstein", AgentforceErrorType.AGENT_METADATA_FAILURE);
      }
      return httpResponse.getEntity().getContent();
    } else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
      throw new AccessTokenExpiredException();
    } else {
      String errorMessage = readErrorStream(httpResponse.getEntity().getContent());
      log.debug("Error in HTTP request. Response code: {}, message: {}", statusCode, errorMessage);
      throw new ModuleException(
                                String.format("Error in HTTP request. ErrorCode: %d, ErrorMessage: %s", statusCode, errorMessage),
                                AgentforceErrorType.AGENT_OPERATIONS_FAILURE);
    }
  }

  private <T, A> InputStream parseHttpResponse(HttpResponse httpResponse, CompletionCallback<T, A> callback) {

    int statusCode = httpResponse.getStatusCode();
    log.debug("Parsing Http Response, statusCode = {}", statusCode);

    if (statusCode == HttpURLConnection.HTTP_OK) {
      if (httpResponse.getEntity().getContent() == null) {
        callback.error(new ModuleException(
                                           "Error: No response received from Einstein", AGENT_OPERATIONS_FAILURE));
      }
      return httpResponse.getEntity().getContent();
    } else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
      callback.error(new AccessTokenExpiredException());
    } else {
      String errorMessage = readErrorStream(httpResponse.getEntity().getContent());
      log.info("Error in HTTP request. Response code: {}, message: {}", statusCode, errorMessage);
      callback.error(new ModuleException(String.format("Error in HTTP request. ErrorCode: %d, ErrorMessage: %s", statusCode,
                                                       errorMessage),
                                         AgentforceErrorType.AGENT_OPERATIONS_FAILURE));
    }
    return null;
  }

  private String getMessageText(JsonNode rootNode) {
    JsonNode messagesNode = rootNode.get(MESSAGES);
    if (messagesNode != null && messagesNode.isArray()) {
      return StreamSupport
          .stream(messagesNode.spliterator(), false)
          .map(x -> getTextValue(x, MESSAGE))
          .collect(Collectors.joining(" "));
    }
    throw new ModuleException(
                              "Invalid response structure. Expected 'Messages'", AgentforceErrorType.AGENT_API_ERROR);
  }

  private String getTextValue(JsonNode node, String keyName) {
    return node != null && node.get(keyName) != null ? node.get(keyName).asText() : null;
  }

  private HttpRequest buildRequest(String url, String accessToken, String httpMethod, HttpEntity httpEntity) {
    return HttpRequest.builder()
        .uri(url)
        .headers(HTTP_METHOD_DELETE.equals(httpMethod) ? addConnectionHeadersForDelete(accessToken)
            : addConnectionHeaders(accessToken))
        .method(httpMethod)
        .entity(httpEntity)
        .build();
  }

  private Result<InputStream, AgentResponseMetadata> parseResponseForSendMessageSync(InputStream responseStream) {
    try {
      AgentApiResponseDTO apiResponse =
          objectMapper.readValue(responseStream, AgentApiResponseDTO.class);

      // Build payload with business data
      String jsonPayload = buildBusinessDataPayload(apiResponse);

      // Build metadata (no business data)
      AgentResponseMetadata metadata = buildMetadata(apiResponse);

      return Result.<InputStream, AgentResponseMetadata>builder()
          .output(toInputStream(jsonPayload, StandardCharsets.UTF_8))
          .attributes(metadata)
          .attributesMediaType(MediaType.APPLICATION_JAVA)
          .mediaType(MediaType.APPLICATION_JSON)
          .build();

    } catch (IOException e) {
      throw new ModuleException("Error parsing send message sync response", AGENT_OPERATIONS_FAILURE, e);
    }
  }

  private String buildBusinessDataPayload(AgentApiResponseDTO apiResponse) {
    try {
      List<AgentBusinessDataResponseDTO.BusinessDataMessage> messages = apiResponse.getMessages().stream()
          .map(this::convertToBusinessDataMessage)
          .collect(Collectors.toList());

      return objectMapper.writeValueAsString(new AgentBusinessDataResponseDTO(messages));

    } catch (JsonProcessingException e) {
      throw new ModuleException("Error building business data payload", AGENT_OPERATIONS_FAILURE, e);
    }
  }

  private AgentBusinessDataResponseDTO.BusinessDataMessage convertToBusinessDataMessage(
                                                                                        AgentApiResponseDTO.Message msg) {
    AgentBusinessDataResponseDTO.BusinessDataMessage businessMsg =
        new AgentBusinessDataResponseDTO.BusinessDataMessage(msg.getType());

    MessageTypeHandler handler = MessageTypeHandler.fromTypeName(msg.getType());

    if (handler != null) {
      handler.apply(msg, businessMsg);
    } else {
      log.warn("Unknown message type: {}", msg.getType());
      businessMsg.setMessage(msg.getMessage());
    }

    return businessMsg;
  }

  private AgentResponseMetadata buildMetadata(AgentApiResponseDTO apiResponse) {
    AgentResponseMetadata metadata = new AgentResponseMetadata();

    if (apiResponse.getLinks() != null) {
      metadata.setLinks(convertLinks(apiResponse.getLinks()));
    }

    List<AgentResponseMetadata.MessageMetadata> msgMetadataList = apiResponse.getMessages().stream()
        .map(this::convertToMessageMetadata)
        .collect(Collectors.toList());

    metadata.setMessageMetadata(msgMetadataList);
    return metadata;
  }

  private AgentResponseMetadata.MessageMetadata convertToMessageMetadata(AgentApiResponseDTO.Message msg) {
    AgentResponseMetadata.MessageMetadata msgMeta = new AgentResponseMetadata.MessageMetadata();

    msgMeta.setId(msg.getId());
    msgMeta.setType(msg.getType());
    msgMeta.setFeedbackId(msg.getFeedbackId());
    msgMeta.setPlanId(msg.getPlanId());
    msgMeta.setIsContentSafe(msg.getIsContentSafe());
    msgMeta.setMetrics(msg.getMetrics());

    // Error metadata
    msgMeta.setHttpStatus(msg.getHttpStatus());
    msgMeta.setTimestamp(msg.getTimestamp());
    msgMeta.setExpected(msg.getExpected());
    msgMeta.setTraceId(msg.getTraceId());

    return msgMeta;
  }

  private AgentResponseMetadata.Links convertLinks(AgentApiResponseDTO.Links source) {
    AgentResponseMetadata.Links links = new AgentResponseMetadata.Links();

    if (source.getSelf() != null) {
      AgentResponseMetadata.Links.Link self = new AgentResponseMetadata.Links.Link();
      self.setHref(source.getSelf().getHref());
      links.setSelf(self);
    }

    if (source.getMessages() != null) {
      AgentResponseMetadata.Links.Link messages = new AgentResponseMetadata.Links.Link();
      messages.setHref(source.getMessages().getHref());
      links.setMessages(messages);
    }

    if (source.getSession() != null) {
      AgentResponseMetadata.Links.Link session = new AgentResponseMetadata.Links.Link();
      session.setHref(source.getSession().getHref());
      links.setSession(session);
    }

    if (source.getEnd() != null) {
      AgentResponseMetadata.Links.Link end = new AgentResponseMetadata.Links.Link();
      end.setHref(source.getEnd().getHref());
      links.setEnd(end);
    }

    return links;
  }
}
