/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.juniwang.msazure;

import com.microsoft.applicationinsights.TelemetryClient;

public class JAppInsights {
    public static void testAI() {
        TelemetryClient telemetryClient = new TelemetryClient();

//        telemetryClient.getContext().setInstrumentationKey("712adcab-2593-48c6-8367-8a940f483bc1");
        telemetryClient.trackEvent("Test event 2");
        telemetryClient.flush();

        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(i);
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
