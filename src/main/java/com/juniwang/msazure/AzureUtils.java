/*
 Copyright 2017 Microsoft Open Technologies, Inc.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.juniwang.msazure;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.credentials.AzureTokenCredentials;
import com.microsoft.azure.management.Azure;
import com.microsoft.rest.LogLevel;

public class AzureUtils {
    public static final String JW_TEST_AZURE_CLIENT_ID = "JW_TEST_AZURE_CLIENT_ID";
    public static final String JW_TEST_AZURE_CLIENT_SECRET = "JW_TEST_AZURE_CLIENT_SECRET";
    public static final String JW_TEST_AZURE_CLIENT_TENANT = "JW_TEST_AZURE_TENANT";
    public static final String JW_TEST_AZURE_SUBSCRIPTION_ID = "JW_TEST_AZURE_SUBSCRIPTION_ID";

    public static ApplicationTokenCredentials getAzureCredential(final String clientId,
                                                                 final String tenant,
                                                                 final String clientSecret,
                                                                 final AzureEnvironment environment) {
        return new ApplicationTokenCredentials(clientId, tenant, clientSecret, environment);
    }

    public static ApplicationTokenCredentials getDefaultAzureCredentials() {
        String clientId = System.getenv(JW_TEST_AZURE_CLIENT_ID);
        String clientSecret = System.getenv(JW_TEST_AZURE_CLIENT_SECRET);
        String tenant = System.getenv(JW_TEST_AZURE_CLIENT_TENANT);

        return getAzureCredential(clientId, tenant, clientSecret, AzureEnvironment.AZURE);
    }

    public static String getDefaultSubscriptionId() {
        return System.getenv(JW_TEST_AZURE_SUBSCRIPTION_ID);
    }

    public static Azure getAzureClient() {
        return getAzureClient(getDefaultSubscriptionId(), getDefaultAzureCredentials());
    }

    public static Azure getAzureClient(String subscriptionId, AzureTokenCredentials credentials) {
        return Azure.configure()
                .withLogLevel(LogLevel.BASIC)
                .authenticate(credentials)
                .withSubscription(subscriptionId);
    }
}
