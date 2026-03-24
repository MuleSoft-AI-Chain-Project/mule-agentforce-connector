package com.mulesoft.connector.agentforce.internal.metadata;

import com.mulesoft.connector.agentforce.api.metadata.AgentBusinessDataResponseDTO;
import com.mulesoft.connector.agentforce.api.metadata.AgentResponseMetadata;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.resolving.AttributesTypeResolver;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

/**
 * Metadata resolver for Send-message-sync operation. Resolves both output (payload) and attributes metadata by loading from DTO
 * classes.
 */
public class SendMessageSyncResponseMetadataResolver implements OutputTypeResolver<String>, AttributesTypeResolver<String> {

  @Override
  public String getResolverName() {
    return OutputTypeResolver.super.getResolverName();
  }

  @Override
  public MetadataType getOutputType(MetadataContext context, String key) {
    return context.getTypeLoader().load(AgentBusinessDataResponseDTO.class);
  }

  @Override
  public MetadataType getAttributesType(MetadataContext context, String key) {
    return context.getTypeLoader().load(AgentResponseMetadata.class);
  }

  @Override
  public String getCategoryName() {
    return "SendMessageSyncResponseResolver";
  }
}
