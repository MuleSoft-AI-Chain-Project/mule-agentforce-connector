package com.mulesoft.connector.agentforce.internal.botapi.config;

import com.mulesoft.connector.agentforce.internal.connection.provider.CustomOauthClientCredentialsConnectionProvider;
import com.mulesoft.connector.agentforce.internal.operation.AgentforceBotOperations;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.concurrent.TimeUnit;

import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;

@Configuration(name = "config")
@DisplayName("Configuration")
@Operations({AgentforceBotOperations.class})
@ConnectionProviders(CustomOauthClientCredentialsConnectionProvider.class)
public class AgentforceConfiguration {

  /**
   * Specifies the amount of time, in the unit defined in {@link #readTimeoutUnit}, that the consumer will wait for a response
   * before it times out.
   */
  @Parameter
  @Optional
  @Placement(tab = ADVANCED_TAB, order = 1)
  @Summary("Response timeout value")
  @DisplayName("Read Timeout")
  private Integer readTimeout;

  /**
   * A {@link TimeUnit} which qualifies the {@link #readTimeout}
   */
  @Parameter
  @Placement(tab = ADVANCED_TAB, order = 2)
  @Optional
  @Summary("Time unit to be used in the Timeout configurations")
  @DisplayName("Time unit")
  private TimeUnit readTimeoutUnit;

  public Integer getReadTimeout() {
    return readTimeout;
  }

  public TimeUnit getReadTimeoutUnit() {
    return readTimeoutUnit;
  }

  public Integer getResponseTimeoutInMillis() {
    return Math.toIntExact(readTimeoutUnit.toMillis(readTimeout));
  }
}
