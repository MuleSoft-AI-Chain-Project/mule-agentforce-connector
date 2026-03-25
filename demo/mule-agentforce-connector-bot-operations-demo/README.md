MuleSoft Agentforce Connector Demo
====================================
Anypoint Studio demo for MuleSoft Agentforce Connector Bot Operations.


Prerequisites
---------------

* Anypoint Studio 7 with Mule ESB 4.6.9 Runtime.
* Mulesoft Agentforce Connector v1.3.0+


How to Run Sample
-----------------

1. Import the project folder demo in Studio.
2. Update the Client Id, Client Secret and Salesforce Org URL(token URL) in the mule-artifact.properties
3. Save the configuration & run the application


About the Sample
----------------

You can use Postman to trigger requests to http://localhost:8081

## Endpoints

### Legacy Operation (Deprecated)
* **POST** - `/agentConversation`
    * **Body**: `{ "prompt": "<PromptValue>" }`
    * **Operation**: `Continue-agent-conversation` (returns plain text)
    * **Note**: Deprecated - use Send-message-sync instead

### New Operation (Recommended)
* **Operation**: `Send-message-sync`
* **Returns**: Structured JSON with business data in payload and metadata in attributes
* **Features**:
    - Payload: `result[]`, `citedReferences[]`, `confirm[]`, `collect[]`, `message`
    - Attributes: `messageMetadata`, `_links`, `metrics`

**Demo Endpoints:**
* **POST** - `/sendMessageSync` - Basic structured data extraction
* **POST** - `/sendMessageSyncTyped` - Advanced type-specific processing with DataWeave pattern matching


## Demo Files

* `mule4-agentforce-bot-operations-demo.xml` - Legacy operation demo
* `send-message-sync-demo.xml` - New operation demos with structured response handling
