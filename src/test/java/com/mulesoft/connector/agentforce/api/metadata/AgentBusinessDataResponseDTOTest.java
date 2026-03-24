package com.mulesoft.connector.agentforce.api.metadata;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgentBusinessDataResponseDTOTest {

  @Test
  void testConstructorAndGetMessages() {
    List<AgentBusinessDataResponseDTO.BusinessDataMessage> messages = new ArrayList<>();
    AgentBusinessDataResponseDTO.BusinessDataMessage message =
        new AgentBusinessDataResponseDTO.BusinessDataMessage("Inform");
    messages.add(message);

    AgentBusinessDataResponseDTO dto = new AgentBusinessDataResponseDTO(messages);
    assertEquals(1, dto.getMessages().size());
    assertEquals("Inform", dto.getMessages().get(0).getType());
  }

  @Test
  void testBusinessDataMessageConstructor() {
    AgentBusinessDataResponseDTO.BusinessDataMessage message =
        new AgentBusinessDataResponseDTO.BusinessDataMessage("Inquire");
    assertEquals("Inquire", message.getType());
  }
}
