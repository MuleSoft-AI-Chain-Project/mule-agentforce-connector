package com.mulesoft.connector.agentforce.internal.botapi.group;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

public class BotMessageParameterGroup {

  @Parameter
  @Placement(order = 1)
  @Summary("Message ID of the previous response you are replying to")
  @DisplayName("In Reply to Message Id")
  @Optional
  private String inReplyToMessageId;

  public String getInReplyToMessageId() {
    return inReplyToMessageId;
  }

}
