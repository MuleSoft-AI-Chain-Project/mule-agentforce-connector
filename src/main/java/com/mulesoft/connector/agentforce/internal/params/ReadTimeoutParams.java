package com.mulesoft.connector.agentforce.internal.params;

import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;

import org.mule.runtime.extension.api.annotation.param.ConfigOverride;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ReadTimeoutParams {

  public ReadTimeoutParams() {}

  public ReadTimeoutParams(Integer readTimeout, TimeUnit readTimeoutUnit) {
    this.readTimeout = readTimeout;
    this.readTimeoutUnit = readTimeoutUnit;
  }

  public static final String READ_TIMEOUT_LABEL = "Read timeout";

  /**
   * Specifies the amount of time, in the unit defined in {@link #readTimeoutUnit}, that the consumer will wait for a response
   * before it times out.
   */
  @Parameter
  @Optional
  @Placement(tab = ADVANCED_TAB, order = 1)
  @Summary("Read timeout value")
  @DisplayName("Read Timeout")
  @ConfigOverride
  private Integer readTimeout;

  /**
   * A {@link TimeUnit} which qualifies the {@link #readTimeout}
   */
  @Parameter
  @Placement(tab = ADVANCED_TAB, order = 2)
  @Optional
  @Summary("Time unit to be used in the Timeout configurations")
  @DisplayName("Read Timeout Unit")
  @ConfigOverride
  private TimeUnit readTimeoutUnit;

  public Integer getReadTimeout() {
    return readTimeout;
  }

  public TimeUnit getReadTimeoutUnit() {
    return readTimeoutUnit;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ReadTimeoutParams))
      return false;
    ReadTimeoutParams that = (ReadTimeoutParams) o;
    return Objects.equals(getReadTimeout(), that.getReadTimeout()) && getReadTimeoutUnit() == that.getReadTimeoutUnit();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReadTimeout(), getReadTimeoutUnit());
  }
}
