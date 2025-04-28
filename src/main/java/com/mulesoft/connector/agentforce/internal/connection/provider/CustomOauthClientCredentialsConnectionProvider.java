package com.mulesoft.connector.agentforce.internal.connection.provider;

import com.mulesoft.connector.agentforce.internal.connection.provider.param.AgentforceConnectionParameterGroup;
import com.mulesoft.connector.agentforce.internal.connection.AgentforceConnection;
import com.mulesoft.connector.agentforce.internal.connection.CustomOAuthClientCredentialsConnection;
import com.mulesoft.connector.agentforce.internal.proxy.HttpProxyConfig;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.api.lifecycle.Startable;
import org.mule.runtime.api.lifecycle.Stoppable;
import org.mule.runtime.core.api.lifecycle.LifecycleUtils;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.connectivity.oauth.ClientCredentialsState;
import org.mule.runtime.extension.api.annotation.connectivity.oauth.ClientCredentials;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.connectivity.oauth.OAuthCallbackValue;
import org.mule.runtime.extension.api.annotation.Alias;

import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.ParameterGroup.CONNECTION;

@Alias("oauth-client-credentials")
@DisplayName("OAuth Client Credentials")
@ClientCredentials(tokenUrl = "https://{salesforceorg}/services/oauth2/token")
public class CustomOauthClientCredentialsConnectionProvider implements AgentforceConnectionProvider,
    CachedConnectionProvider<AgentforceConnection>, Startable, Stoppable {

  private static final Logger log = LoggerFactory.getLogger(CustomOauthClientCredentialsConnectionProvider.class);
  private ClientCredentialsState clientCredentialsState;

  /**
   * Proxy Configuration
   */
  @Parameter
  @Optional
  @Placement(tab = "Proxy", order = 3)
  @Expression(NOT_SUPPORTED)
  @DisplayName("Proxy Configuration")
  private HttpProxyConfig proxyConfig;

  @ParameterGroup(name = CONNECTION)
  @Placement(order = 1)
  private AgentforceConnectionParameterGroup agentforceConnectionParameterGroup;


  @Inject
  private HttpService httpService;

  private HttpClient httpClient;

  @OAuthCallbackValue(expression = "#[payload.instance_url]")
  private String salesforceOrgUrl;

  @OAuthCallbackValue(expression = "#[payload.api_instance_url]")
  private String apiInstanceUrl;

  @Override
  public void start() throws InitialisationException {
    LifecycleUtils.initialiseIfNeeded(agentforceConnectionParameterGroup.getTlsContextFactory());
    HttpClientConfiguration.Builder baseClientConfigBuilder = httpClientConfigBuilder();
    this.httpClient = httpService.getClientFactory().create(baseClientConfigBuilder.build());
    log.info("Starting httpclient");
    this.httpClient.start();

    log.debug("Mule HTTP client started.");
  }

  @Override
  public AgentforceConnection connect() {
    log.info("Inside CustomOauthClientCredentialsConnectionProvider connect, salesforceOrg {}, apiInstanceUrl = {}",
             salesforceOrgUrl, apiInstanceUrl);
    return new CustomOAuthClientCredentialsConnection(salesforceOrgUrl, clientCredentialsState, apiInstanceUrl, httpClient);
  }

  @Override
  public void stop() {
    if (httpClient != null) {
      httpClient.stop();
    }
  }

  private HttpClientConfiguration.Builder httpClientConfigBuilder() {
    return new HttpClientConfiguration.Builder().setName("http-client")
        .setProxyConfig(proxyConfig).setTlsContextFactory(agentforceConnectionParameterGroup.getTlsContextFactory())
        .setClientSocketProperties(TcpClientSocketProperties.builder()
            .connectionTimeout(agentforceConnectionParameterGroup.getConnectionTimeoutInMillis()).build())
        .setMaxConnections(agentforceConnectionParameterGroup.getMaxConnections())
        .setConnectionIdleTimeout(agentforceConnectionParameterGroup.getConnectionIdleTimeoutInMillis());
  }
}
