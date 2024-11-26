package com.mule.einstein.internal;

import com.mule.einstein.internal.connection.ConnectionProvider;
import com.mule.einstein.internal.operations.EinsteinOperations;
import com.mule.einstein.internal.error.EinsteinErrorType;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.sdk.api.annotation.JavaVersionSupport;

import static org.mule.sdk.api.meta.JavaVersion.*;

/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "ms-einstein")
@Extension(name = "Einstein AI")
@ErrorTypes(EinsteinErrorType.class)
@Operations(EinsteinOperations.class)
@ConnectionProviders(ConnectionProvider.class)
@JavaVersionSupport({JAVA_8, JAVA_11, JAVA_17})
public class EinsteinConnector {

}
