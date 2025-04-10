package com.mulesoft.connector.agentforce.api.proxy;

import org.mule.runtime.extension.api.annotation.dsl.xml.TypeDsl;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import java.util.Objects;

@TypeDsl(allowTopLevelDefinition = true)
public class DefaultHttpProxyConfig implements HttpProxyConfig {

  @Parameter
  @Placement(order = 1)
  private String host;
  @Parameter
  @Placement(order = 2)
  private int port = 2147483647;
  @Parameter
  @Optional
  private String username;
  @Parameter
  @Optional
  @Password
  private String password;
  @Parameter
  @Optional
  private String nonProxyHosts;

  public DefaultHttpProxyConfig() {
    // Default constructor for DefaultHttpProxyConfig
  }

  public String getHost() {
    return this.host;
  }

  public int getPort() {
    return this.port;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getNonProxyHosts() {
    return this.nonProxyHosts;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setNonProxyHosts(String nonProxyHosts) {
    this.nonProxyHosts = nonProxyHosts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof DefaultHttpProxyConfig))
      return false;
    DefaultHttpProxyConfig that = (DefaultHttpProxyConfig) o;
    return port == that.port &&
        Objects.equals(host, that.host) &&
        Objects.equals(username, that.username) &&
        Objects.equals(password, that.password) &&
        Objects.equals(nonProxyHosts, that.nonProxyHosts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, port, username, password, nonProxyHosts);
  }
}

