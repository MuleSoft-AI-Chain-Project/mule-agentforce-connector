package com.mulesoft.connector.agentforce.internal.error.provider;

import com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType;
import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

public class BotErrorTypeProvider implements ErrorTypeProvider {

  @SuppressWarnings("rawtypes")
  @Override
  public Set<ErrorTypeDefinition> getErrorTypes() {
    return unmodifiableSet(new HashSet<>(asList(AgentforceErrorType.values())));
  }
}
