package com.mulesoft.connector.agentforce.internal.connection.provider.param;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.api.tls.TlsContextFactory;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.ConfigOverride;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.concurrent.TimeUnit;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;

public class AgentforceConnectionParameterGroup {

  private static final String SECURITY_TAB = "Security";

  /**
   * The maximum number of outbound connections that will be kept open at the same time. By default the number of connections is
   * unlimited. The value -1 means unlimited
   */
  @Parameter
  @Optional(defaultValue = "-1")
  @Placement(tab = ADVANCED_TAB, order = 1)
  @Summary("The maximum number of outbound connections that will be kept open at the same time")
  private Integer maxConnections;

  /**
   * The amount of time to wait when initially establishing the TCP connection between the connector and Agent before throwing an
   * exception if the connection fails.
   */
  @Parameter
  @Optional(defaultValue = "30")
  @DisplayName("Connection Timeout")
  @Placement(tab = Placement.ADVANCED_TAB, order = 2)
  @Summary("The amount of time to wait when initially establishing the TCP connection between the connector " +
      "and Salesforce Agent before throwing an exception if the connection fails.")
  @ConfigOverride
  private int connectionTimeout;

  /**
   * The time unit for the Connection Timeout value.
   */
  @Parameter
  @Optional(defaultValue = "SECONDS")
  @DisplayName("Connection Timeout Unit")
  @Expression(NOT_SUPPORTED)
  @Placement(tab = Placement.ADVANCED_TAB, order = 3)
  @Summary("The time unit for Connection Timeout value.")
  @ConfigOverride
  private TimeUnit connectionTimeoutUnit;

  /**
   * The number of units that a connection can remain idle before it is closed.
   */
  @Parameter
  @DisplayName("Connection Idle Timeout")
  @Optional(defaultValue = "30")
  @Placement(tab = ADVANCED_TAB, order = 4)
  @Summary("The number of units that a connection can remain idle before it is closed")
  @ConfigOverride
  private Integer connectionIdleTimeout;

  /**
   * The unit for Connection idle timeout
   */
  @Parameter
  @DisplayName("Connection Idle Timeout Unit")
  @Optional(defaultValue = "SECONDS")
  @Expression(NOT_SUPPORTED)
  @Placement(tab = ADVANCED_TAB, order = 5)
  @ConfigOverride
  private TimeUnit connectionIdleTimeoutUnit;

  @Parameter
  @DisplayName("TLS Configuration")
  @Optional
  @Expression(NOT_SUPPORTED)
  @Placement(tab = SECURITY_TAB)
  @Summary("Protocol to use for communication")
  private TlsContextFactory tlsContextFactory;

  public Integer getMaxConnections() {
    return maxConnections;
  }

  public int getConnectionTimeout() {
    return connectionTimeout;
  }

  public TimeUnit getConnectionTimeoutUnit() {
    return connectionTimeoutUnit;
  }

  public Integer getConnectionIdleTimeout() {
    return connectionIdleTimeout;
  }

  public TimeUnit getConnectionIdleTimeoutUnit() {
    return connectionIdleTimeoutUnit;
  }

  public TlsContextFactory getTlsContextFactory() {
    return tlsContextFactory;
  }

  public Integer getConnectionTimeoutInMillis() {
    return Math.toIntExact(connectionTimeoutUnit.toMillis(connectionTimeout));
  }

  public Integer getConnectionIdleTimeoutInMillis() {
    return Math.toIntExact(connectionIdleTimeoutUnit.toMillis(connectionIdleTimeout));
  }
}
