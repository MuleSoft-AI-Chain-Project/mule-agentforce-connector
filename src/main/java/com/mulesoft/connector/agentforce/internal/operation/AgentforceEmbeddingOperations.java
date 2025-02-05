package com.mulesoft.connector.agentforce.internal.operation;

import com.mulesoft.connector.agentforce.api.metadata.AgentforceResponseAttributes;
import com.mulesoft.connector.agentforce.api.metadata.ResponseParameters;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import com.mulesoft.connector.agentforce.internal.error.provider.EmbeddingErrorTypeProvider;
import com.mulesoft.connector.agentforce.internal.modelsapi.helpers.ResponseHelper;
import com.mulesoft.connector.agentforce.internal.modelsapi.models.ParamsEmbeddingDocumentDetails;
import com.mulesoft.connector.agentforce.internal.modelsapi.models.ParamsEmbeddingModelDetails;
import com.mulesoft.connector.agentforce.internal.modelsapi.models.ParamsModelDetails;
import com.mulesoft.connector.agentforce.internal.modelsapi.models.RAGParamsModelDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.EMBEDDING_OPERATIONS_FAILURE;
import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.RAG_FAILURE;
import static com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType.TOOLS_OPERATION_FAILURE;
import static com.mulesoft.connector.agentforce.internal.modelsapi.helpers.ConstantUtil.MODELAPI_OPENAI_ADA_002;
import static java.lang.String.format;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class AgentforceEmbeddingOperations {

  private static final Logger log = LoggerFactory.getLogger(AgentforceEmbeddingOperations.class);

  /**
   * Create an embedding vector representing the input text.
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("EMBEDDING-generate-from-text")
  @Throws(EmbeddingErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/AgentForceEmbeddingResponse.json")
  public Result<InputStream, ResponseParameters> generateEmbeddingFromText(
                                                                           @Connection AgentforceConnection connection,
                                                                           @Content String text,
                                                                           @ParameterGroup(
                                                                               name = "Additional properties") ParamsEmbeddingModelDetails paramDetails) {
    log.info("Executing generate embedding from text operation.");
    try {
      InputStream responseStream = connection.getRequestHelper().generateEmbeddingFromText(text, paramDetails);

      return ResponseHelper.createAgentforceEmbeddingResponse(responseStream);
    } catch (Exception e) {
      throw new ModuleException("Error while executing embedding generate from text operation",
                                EMBEDDING_OPERATIONS_FAILURE, e);
    }
  }

  /**
   * Create an embedding vector representing the input file .
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("EMBEDDING-generate-from-file")
  @Throws(EmbeddingErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/AgentForceFileEmbeddingResponse.json")
  public Result<InputStream, Void> generateEmbeddingFromFile(@Connection AgentforceConnection connection,
                                                             @Content InputStream inputStream,
                                                             @ParameterGroup(
                                                                 name = "Additional properties") ParamsEmbeddingDocumentDetails paramDetails) {
    log.info("Executing generate embedding from file operation.");
    try {
      JSONArray response = connection.getRequestHelper().generateEmbeddingFromFileInputStream(inputStream, paramDetails);

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("result", response);

      return ResponseHelper.createAgentforceDefaultResponse(jsonObject.toString());
    } catch (Exception e) {
      throw new ModuleException("Error while executing embedding generate from file operation",
                                EMBEDDING_OPERATIONS_FAILURE, e);
    }
  }

  /**
   * Generate a response based on a file embedding.
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("EMBEDDING-adhoc-file-query")
  @Throws(EmbeddingErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/AgentForceFileEmbeddingResponse.json")
  public Result<InputStream, Void> queryEmbeddingOnFiles(@Connection AgentforceConnection connection, @Content String prompt,
                                                         @Content InputStream inputStream,
                                                         @ParameterGroup(
                                                             name = "Additional properties") ParamsEmbeddingDocumentDetails paramDetails) {
    log.info("Executing embedding adhoc file query operation.");

    try {
      JSONArray response = connection.getRequestHelper().embeddingFileQuery(prompt, inputStream, paramDetails.getModelApiName(),
                                                                            paramDetails.getFileType(),
                                                                            paramDetails.getOptionType());

      JSONObject jsonObject = new JSONObject();
      jsonObject.put("result", response);
      return ResponseHelper.createAgentforceDefaultResponse(jsonObject.toString());
    } catch (Exception e) {
      throw new ModuleException("Error while generating the chat, for prompt " + prompt, EMBEDDING_OPERATIONS_FAILURE, e);
    }
  }

  /**
   * Generate a response based on a plain text prompt and file from embedding and LLM.
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("RAG-adhoc-load-document")
  @Throws(EmbeddingErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/AgentForceOperationResponse.json")
  public Result<InputStream, AgentforceResponseAttributes> ragOnFiles(@Connection AgentforceConnection connection,
                                                                      @Content String prompt,
                                                                      @Content InputStream inputStream,
                                                                      @ParameterGroup(
                                                                          name = "Additional properties") RAGParamsModelDetails paramDetails) {
    log.info("Executing rag adhoc load document.");
    try {
      String content = connection.getRequestHelper().embeddingFileQuery(prompt, inputStream, paramDetails.getEmbeddingName(),
                                                                        paramDetails.getFileType(), paramDetails.getOptionType())
          .toString();
      InputStream responseStream = connection.getRequestHelper().executeRAG("data: " + content + ", question: " + prompt,
                                                                            paramDetails);

      return ResponseHelper.createAgentforceFormattedResponse(responseStream);
    } catch (Exception e) {
      throw new ModuleException("Error while doing rag adhoc load document for prompt " + prompt, RAG_FAILURE, e);
    }
  }

  /**
   * Generate a response based on a plain text prompt and tools config.
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Tools-use-ai-service")
  @Throws(EmbeddingErrorTypeProvider.class)
  @OutputJsonType(schema = "api/response/AgentForceOperationResponse.json")
  public Result<InputStream, AgentforceResponseAttributes> executeTools(@Connection AgentforceConnection connection,
                                                                        @Content String prompt,
                                                                        @Content InputStream inputStream,
                                                                        @ParameterGroup(
                                                                            name = "Additional properties") ParamsModelDetails paramDetails) {
    log.info("Executing AI service tools operation.");
    try {

      String content =
          connection.getRequestHelper().embeddingFileQuery(prompt, inputStream, MODELAPI_OPENAI_ADA_002, "text", "FULL")
              .toString();
      inputStream.reset();
      InputStream responseStream =
          connection.getRequestHelper().executeTools(prompt, "data: " + content + ", question: " + prompt,
                                                     inputStream, paramDetails);

      return ResponseHelper.createAgentforceFormattedResponse(responseStream);
    } catch (Exception e) {
      throw new ModuleException("Error while executing AI service tools" + ", for prompt "
          + prompt, TOOLS_OPERATION_FAILURE, e);
    }
  }
}
