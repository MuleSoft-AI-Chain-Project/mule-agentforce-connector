package com.mulesoft.connector.agentforce.internal.operation;

import com.mulesoft.connector.agentforce.api.metadata.AgentResponseMetadata;
import com.mulesoft.connector.agentforce.api.metadata.InvokeAgentResponseAttributes;
import com.mulesoft.connector.agentforce.api.request.Variable;
import com.mulesoft.connector.agentforce.internal.error.provider.BotErrorTypeProvider;
import com.mulesoft.connector.agentforce.internal.botapi.group.BotAgentParameterGroup;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import com.mulesoft.connector.agentforce.internal.metadata.AgentConversationResponseMetadataResolver;
import com.mulesoft.connector.agentforce.internal.metadata.SendMessageSyncResponseMetadataResolver;
import com.mulesoft.connector.agentforce.internal.params.ReadTimeoutParams;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;

import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.AGENT_OPERATIONS_FAILURE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;
import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

public class AgentforceBotOperations {

  private static final Logger log = LoggerFactory.getLogger(AgentforceBotOperations.class);

  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Start-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/StartAgentConversationResponse.json")
  public void startAgentConversation(@Connection AgentforceConnection connection,
                                     @Placement(order = 1) @ParameterGroup(
                                         name = "Agent") @MetadataKeyId BotAgentParameterGroup parameterGroup,
                                     @ParameterGroup(
                                         name = ReadTimeoutParams.READ_TIMEOUT_LABEL) @Summary("If defined, it overwrites values in configuration.") ReadTimeoutParams readTimeout,
                                     CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback) {

    log.info("Executing start agent conversation operation, agent = {}", parameterGroup.getAgent());
    try {
      connection.getBotRequestHelper().startSession(parameterGroup.getAgent(), parameterGroup.getByPassUser(),
                                                    parameterGroup.getVariables(), readTimeout, callback);
    } catch (Exception e) {
      callback.error(new ModuleException("Error while starting agent conversation for agent: " + parameterGroup.getAgent(),
                                         AGENT_OPERATIONS_FAILURE, e));
    }
  }

  @Deprecated
  @MediaType(value = TEXT_PLAIN, strict = false)
  @Alias("Continue-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  @OutputResolver(output = AgentConversationResponseMetadataResolver.class)
  @Summary("DEPRECATED: Use Send-message-sync for structured JSON response. Continue an agent conversation (returns plain text)")
  public void continueConversation(@Connection AgentforceConnection connection,
                                   @Content(primary = true) InputStream message,
                                   @Content @Example("3bb7c7e8-1234-5678-9abc-def012345678") String sessionId,
                                   @Summary("Increase this number for each subsequent message in a session") @DisplayName("Message Sequence Number") @Example("1") int messageSequenceNumber,
                                   @ParameterGroup(
                                       name = ReadTimeoutParams.READ_TIMEOUT_LABEL) @Summary("If defined, it overwrites values in configuration.") ReadTimeoutParams readTimeout,
                                   CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback) {

    log.info("Executing continue agent conversation operation (DEPRECATED), sessionId = {}", sessionId);

    try {
      connection.getBotRequestHelper().continueSession(message, sessionId, messageSequenceNumber, readTimeout, callback);
    } catch (Exception e) {
      callback.error(new ModuleException("Error in continue agent conversation for session id: " + sessionId,
                                         AGENT_OPERATIONS_FAILURE, e));
    }
  }

  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Send-message-sync")
  @Throws(BotErrorTypeProvider.class)
  @OutputResolver(output = SendMessageSyncResponseMetadataResolver.class,
      attributes = SendMessageSyncResponseMetadataResolver.class)
  @Summary("Send a message to the agent and receive structured JSON response with clean payload/attributes separation")
  @DisplayName("Send Message (Sync)")
  public void sendMessageSync(@Connection AgentforceConnection connection,
                              @Content(primary = true) InputStream message,
                              @Content @Example("3bb7c7e8-1234-5678-9abc-def012345678") String sessionId,
                              @Summary("Increase this number for each subsequent message in a session") @DisplayName("Message Sequence Number") @Example("1") int messageSequenceNumber,
                              @Optional @NullSafe @Expression(ExpressionSupport.SUPPORTED) @Summary("Array of custom and context agent variables ") @DisplayName("Agent Variables") List<Variable> variables,
                              @ParameterGroup(
                                  name = ReadTimeoutParams.READ_TIMEOUT_LABEL) @Summary("If defined, it overwrites values in configuration.") ReadTimeoutParams readTimeout,
                              CompletionCallback<InputStream, AgentResponseMetadata> callback) {

    log.debug("Executing send message sync operation, sessionId = {}", sessionId);

    try {
      connection.getBotRequestHelper().sendMessageSync(message, sessionId, messageSequenceNumber, variables, readTimeout,
                                                       callback);
    } catch (Exception e) {
      callback.error(new ModuleException("Error in send message sync for session id: " + sessionId,
                                         AGENT_OPERATIONS_FAILURE, e));
    }
  }

  @MediaType(value = TEXT_PLAIN, strict = false)
  @Alias("End-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  @OutputResolver(output = AgentConversationResponseMetadataResolver.class)
  public void endConversation(@Connection AgentforceConnection connection,
                              @Content String sessionId,
                              @ParameterGroup(
                                  name = ReadTimeoutParams.READ_TIMEOUT_LABEL) @Summary("If defined, it overwrites values in configuration.") ReadTimeoutParams readTimeout,
                              CompletionCallback<InputStream, InvokeAgentResponseAttributes> callback) {

    log.info("Executing end agent conversation operation. sessionId = {}", sessionId);

    try {
      connection.getBotRequestHelper().endSession(sessionId, readTimeout, callback);
    } catch (Exception e) {
      callback.error(new ModuleException("Error in end agent conversation for session id: " + sessionId,
                                         AGENT_OPERATIONS_FAILURE, e));
    }
  }
}
