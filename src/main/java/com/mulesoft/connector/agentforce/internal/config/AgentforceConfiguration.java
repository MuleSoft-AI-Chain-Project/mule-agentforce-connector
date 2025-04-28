package com.mulesoft.connector.agentforce.internal.config;

import com.mulesoft.connector.agentforce.internal.connection.provider.CustomOauthClientCredentialsConnectionProvider;
import com.mulesoft.connector.agentforce.internal.operation.AgentforceBotOperations;
import com.mulesoft.connectors.commons.template.config.ConnectorConfig;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.concurrent.TimeUnit;

import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;
import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;

@Configuration(name = "config")
@DisplayName("Configuration")
@Operations({AgentforceBotOperations.class})
@ConnectionProviders(CustomOauthClientCredentialsConnectionProvider.class)
public class AgentforceConfiguration implements ConnectorConfig {

  /**
   * Specifies the amount of time, in the unit defined in {@link #readTimeoutUnit}, that the consumer will wait for a response
   * before it times out.
   */
  @Parameter
  @Optional(defaultValue = "30")
  @DisplayName("Read Timeout")
  @Placement(tab = ADVANCED_TAB, order = 1)
  @Summary("Read timeout value")
  private Integer readTimeout;

  /**
   * A {@link TimeUnit} which qualifies the {@link #readTimeout}
   */
  @Parameter
  @Optional(defaultValue = "SECONDS")
  @DisplayName("Read Timeout Unit")
  @Placement(tab = ADVANCED_TAB, order = 2)
  @Summary("Time unit to be used in the Timeout configurations")
  @Expression(NOT_SUPPORTED)
  private TimeUnit readTimeoutUnit;

  public Integer getReadTimeout() {
    return readTimeout;
  }

  public TimeUnit getReadTimeoutUnit() {
    return readTimeoutUnit;
  }
}
