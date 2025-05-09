package com.mulesoft.connector.agentforce.internal.extension;

import com.mulesoft.connector.agentforce.internal.proxy.DefaultHttpProxyConfig;
import com.mulesoft.connector.agentforce.internal.proxy.HttpProxyConfig;
import com.mulesoft.connector.agentforce.internal.config.AgentforceConfiguration;
import com.mulesoft.connector.agentforce.internal.error.AgentforceErrorType;
import org.mule.runtime.api.meta.Category;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;
import org.mule.sdk.api.annotation.JavaVersionSupport;

import static org.mule.sdk.api.meta.JavaVersion.JAVA_11;
import static org.mule.sdk.api.meta.JavaVersion.JAVA_17;
import static org.mule.sdk.api.meta.JavaVersion.JAVA_8;

/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations and
 * sources are going to be declared.
 */
@Xml(prefix = "ms-agentforce")
@Extension(name = "Agentforce", category = Category.SELECT)
@ErrorTypes(AgentforceErrorType.class)
@SubTypeMapping(baseType = HttpProxyConfig.class, subTypes = {DefaultHttpProxyConfig.class})
@RequiresEnterpriseLicense(allowEvaluationLicense = true)
@JavaVersionSupport({JAVA_8, JAVA_11, JAVA_17})
@Configurations(AgentforceConfiguration.class)
public class AgentforceConnector {
}
