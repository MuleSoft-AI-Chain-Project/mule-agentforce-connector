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
    * **Body**: `{ "prompt": "What is your return policy?" }`
* **POST** - `/sendMessageSyncTyped` - Advanced type-specific processing with DataWeave pattern matching
    * **Body**: `{ "prompt": "Your question" }`
* **POST** - `/sendMessageSyncWithVariables` - Agent variables support (bypasses verification)
    * **Body**: `{ "prompt": "Show me my cases", "contactId": "003Ig00000DsENIIA3" }`

### Agent Variables Feature

Agent variables allow you to pass context (like verified customer IDs) to bypass agent verification steps:

**Without Variables:**
```
User: "Show me my cases"
Agent: "I need to verify your identity. Please provide your email."
```

**With Variables (verifiedContactID):**
```
User: "Show me my cases"
Agent: "Here are your cases: 00001256 (New), 00001253 (High), 00001234 (Working)"
```

**Supported Variable Types:**
* Text, Boolean, Number, Date, DateTime, Money, Ref, Object, List, Json

**Usage:**
```xml
<ms-agentforce:start-agent-conversation agent="0Xx..." config-ref="config">
    <ms-agentforce:variables>
        <ms-agentforce:variable name="verifiedContactID" value="#['003...']" type="Text"/>
    </ms-agentforce:variables>
</ms-agentforce:start-agent-conversation>
```


## Demo Files

* `mule4-agentforce-bot-operations-demo.xml` - Legacy operation demo
* `send-message-sync-demo.xml` - New operation demos with structured response handling and variables support
