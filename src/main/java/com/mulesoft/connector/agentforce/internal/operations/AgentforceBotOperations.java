package com.mulesoft.connector.agentforce.internal.operations;

import com.mulesoft.connector.agentforce.api.metadata.InvokeAgentResponseAttributes;
import com.mulesoft.connector.agentforce.internal.botapi.dto.AgentConversationResponseDTO;
import com.mulesoft.connector.agentforce.internal.botapi.error.provider.BotErrorTypeProvider;
import com.mulesoft.connector.agentforce.internal.botapi.group.BotMessageParameterGroup;
import com.mulesoft.connector.agentforce.internal.botapi.helpers.BotRequestHelper;
import com.mulesoft.connector.agentforce.internal.botapi.group.BotAgentParameterGroup;
import com.mulesoft.connector.agentforce.internal.botapi.metadata.AgentConversationResponseMetadataResolver;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.AGENT_OPERATIONS_FAILURE;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;
import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

public class AgentforceBotOperations {

  BotRequestHelper requestHelper = new BotRequestHelper();

  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Start-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/StartAgentConversationResponse.json")
  public Result<InputStream, InvokeAgentResponseAttributes> startAgentConversation(@Connection AgentforceConnection connection,
                                                                                   @ParameterGroup(
                                                                                       name = "Agent") @MetadataKeyId BotAgentParameterGroup parameterGroup) {
    try {
      AgentConversationResponseDTO responseDTO = requestHelper.startSession(connection, parameterGroup.getAgent());

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("sessionId", responseDTO.getSessionId());

      return Result.<InputStream, InvokeAgentResponseAttributes>builder()
          .output(toInputStream(jsonObject.toString(), StandardCharsets.UTF_8))
          .attributes(responseDTO.getResponseAttributes())
          .attributesMediaType(org.mule.runtime.api.metadata.MediaType.APPLICATION_JAVA)
          .mediaType(org.mule.runtime.api.metadata.MediaType.APPLICATION_JSON)
          .build();
    } catch (Exception e) {

      throw new ModuleException("Error while starting agent conversation for agent: " + parameterGroup.getAgent(),
                                AGENT_OPERATIONS_FAILURE, e);
    }
  }

  @MediaType(value = TEXT_PLAIN, strict = false)
  @Alias("Continue-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  @OutputResolver(output = AgentConversationResponseMetadataResolver.class)
  public Result<InputStream, InvokeAgentResponseAttributes> continueConversation(@Content(primary = true) InputStream message,
                                                                                 @Content String sessionId,
                                                                                 @ParameterGroup(
                                                                                     name = "Additional Details") BotMessageParameterGroup messageParameterGroup,
                                                                                 @Connection AgentforceConnection connection) {
    try {
      AgentConversationResponseDTO responseDTO = requestHelper.continueSession(message,
                                                                               sessionId,
                                                                               messageParameterGroup.getMessageSequenceNumber(),
                                                                               messageParameterGroup.getInReplyToMessageId(),
                                                                               connection);
      return Result.<InputStream, InvokeAgentResponseAttributes>builder()
          .output(toInputStream(responseDTO.getText(), StandardCharsets.UTF_8))
          .attributes(responseDTO.getResponseAttributes())
          .attributesMediaType(org.mule.runtime.api.metadata.MediaType.APPLICATION_JAVA)
          .mediaType(org.mule.runtime.api.metadata.MediaType.TEXT)
          .build();
    } catch (Exception e) {

      throw new ModuleException("Error in continue agent conversation for session id: " + sessionId,
                                AGENT_OPERATIONS_FAILURE, e);
    }
  }

  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("End-agent-conversation")
  @Throws(BotErrorTypeProvider.class)
  public Result<Void, InvokeAgentResponseAttributes> endConversation(@Content String sessionId,
                                                                     @Connection AgentforceConnection connection) {

    try {
      AgentConversationResponseDTO responseDTO = requestHelper.endSession(sessionId, connection);

      return Result.<Void, InvokeAgentResponseAttributes>builder()
          .attributes(responseDTO.getResponseAttributes())
          .attributesMediaType(org.mule.runtime.api.metadata.MediaType.APPLICATION_JAVA)
          .build();
    } catch (Exception e) {

      throw new ModuleException("Error in end agent conversation for session id: " + sessionId,
                                AGENT_OPERATIONS_FAILURE, e);
    }
  }
}
