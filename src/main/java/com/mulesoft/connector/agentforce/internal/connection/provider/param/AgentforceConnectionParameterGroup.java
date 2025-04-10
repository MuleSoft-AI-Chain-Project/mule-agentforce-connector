package com.mulesoft.connector.agentforce.internal.connection.provider.param;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.sdk.api.annotation.semantics.connectivity.ExcludeFromConnectivitySchema;

import java.util.concurrent.TimeUnit;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;

public class AgentforceConnectionParameterGroup {

  /**
   * The maximum number of outbound connections that will be kept open at the same time. By default the number of connections is
   * unlimited.
   */
  @Parameter
  @Optional(defaultValue = "-1")
  @Expression(NOT_SUPPORTED)
  @Placement(tab = ADVANCED_TAB, order = 1)
  @Summary("The maximum number of outbound connections that will be kept open at the same time")
  @ExcludeFromConnectivitySchema
  private Integer maxConnections;

  /**
   * The amount of time to wait when initially establishing the TCP connection between the connector and Agent before throwing an
   * exception if the connection fails.
   */
  @Parameter
  @Optional(defaultValue = "30")
  @DisplayName("Connection Timeout")
  @Expression(ExpressionSupport.SUPPORTED)
  @Placement(tab = Placement.ADVANCED_TAB, order = 2)
  @Summary("The amount of time to wait when initially establishing the TCP connection between the connector " +
      "and Amazon S3 server before throwing an exception if the connection fails.")
  private int connectionTimeout;

  /**
   * The time unit for the Connection Timeout value.
   */
  @Parameter
  @Optional(defaultValue = "SECONDS")
  @DisplayName("Connection Timeout Time Unit")
  @Expression(ExpressionSupport.SUPPORTED)
  @Placement(tab = Placement.ADVANCED_TAB, order = 3)
  @Summary("The time unit for Connection Timeout value.")
  private TimeUnit connectionTimeoutUnit;

  /**
   * The number of units that a connection can remain idle before it is closed.
   */
  @Parameter
  @DisplayName("Connection Idle Timeout")
  @Optional(defaultValue = "30")
  @Expression(NOT_SUPPORTED)
  @Placement(tab = ADVANCED_TAB, order = 4)
  @Summary("The number of units that a connection can remain idle before it is closed")
  @ExcludeFromConnectivitySchema
  private Integer connectionIdleTimeout;

  /**
   * The unit for Connection idle timeout
   */
  @Parameter
  @DisplayName("Connection Idle Timeout Unit")
  @Optional(defaultValue = "SECONDS")
  @Expression(NOT_SUPPORTED)
  @Placement(tab = ADVANCED_TAB, order = 5)
  @ExcludeFromConnectivitySchema
  private TimeUnit connectionIdleTimeoutUnit;

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

  public Integer getMaxConnections() {
    return maxConnections;
  }

  public Integer getConnectionTimeoutInMillis() {
    return Math.toIntExact(connectionTimeoutUnit.toMillis(connectionTimeout));
  }

  public Integer getConnectionIdleTimeoutInMillis() {
    return Math.toIntExact(connectionIdleTimeoutUnit.toMillis(connectionIdleTimeout));
  }
}
