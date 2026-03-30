package com.mulesoft.connector.agentforce.internal.botapi.group;

import com.mulesoft.connector.agentforce.internal.botapi.dto.Variable;
import com.mulesoft.connector.agentforce.internal.botapi.metadata.AgentListValueProvider;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;

import java.util.List;

public class BotAgentParameterGroup {

  @Parameter
  @Placement(order = 1)
  @OfValues(AgentListValueProvider.class)
  @Expression(value = ExpressionSupport.NOT_SUPPORTED)
  @DisplayName("Agent List")
  private String agent;

  public String getAgent() {
    return agent;
  }

  @Parameter
  @Placement(order = 2)
  @Expression(value = ExpressionSupport.SUPPORTED)
  @Optional(defaultValue = "#[false]")
  @DisplayName("Bypass User")
  private boolean byPassUser;

  public boolean getByPassUser() {
    return byPassUser;
  }

  @Parameter
  @Placement(order = 3)
  @Optional
  @DisplayName("Agent Variables")
  @Expression(ExpressionSupport.SUPPORTED)
  @Summary("Array of custom and context agent variables ")
  private List<Variable> variables;

  public List<Variable> getVariables() {
    return variables;
  }

}
