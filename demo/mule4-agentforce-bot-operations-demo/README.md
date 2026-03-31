MuleSoft Agentforce Connector Demo - Bot Operations
====================================================
Anypoint Studio demo for MuleSoft Agentforce Connector Bot Operations.


Prerequisites
-------------

* Anypoint Studio 7 with Mule ESB 4.6.9 Runtime.
* MuleSoft Agentforce Connector v1.3.0+


How to Run Sample
-----------------

1. Import the project folder demo in Studio.
2. Update the Client Id, Client Secret and Salesforce Org URL (token URL) in the mule-artifact.properties
3. Save the configuration & run the application


About the Sample
----------------

This demo showcases the bot operations of the Agentforce connector including:
* Starting agent conversations
* Continuing conversations with message exchanges
* Ending agent sessions

You can use Postman or similar tools to trigger requests to the configured endpoints at http://localhost:8081


Configuration
-------------

Ensure you have valid Salesforce credentials with access to Agentforce API:
* **Client ID**: OAuth Connected App Client ID
* **Client Secret**: OAuth Connected App Client Secret
* **Salesforce Org URL**: Your Salesforce instance URL (e.g., https://login.salesforce.com)
