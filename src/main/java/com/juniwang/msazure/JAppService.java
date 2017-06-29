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

import com.juniwang.Utils;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.appservice.AppServicePlan;
import com.microsoft.azure.management.appservice.OperatingSystem;
import com.microsoft.azure.management.appservice.PricingTier;
import com.microsoft.azure.management.appservice.WebApp;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;

public class JAppService {

    public static void createWebApp() {
        final String resourceGroupName = Utils.generateRandomString("jw-rg-", null, 16);
        final String appServicePlanName = Utils.generateRandomString("jw-sp-", null, 16);
        final String appServicename = Utils.generateRandomString("jw-webapp-linux-", null, 8);
        final Region region = Region.AUSTRALIA_EAST;
        final PricingTier pricingTier = PricingTier.STANDARD_S1;

        final Azure azure = AzureUtils.getAzureClient();
        // app servcie plan
        AppServicePlan appServicePlan = azure.appServices().appServicePlans().define(appServicePlanName)
                .withRegion(region)
                .withNewResourceGroup(resourceGroupName)
                .withPricingTier(pricingTier)
                .withOperatingSystem(OperatingSystem.LINUX)
                .create();

        Utils.checkNotNull(appServicePlan, "");
        System.out.println(appServicePlan.name());
        System.out.println(appServicePlan.id());

        WebApp webApp = azure.webApps().define(appServicename)
                .withExistingLinuxPlan(appServicePlan)
                .withExistingResourceGroup(resourceGroupName)
                .withPublicDockerHubImage("tomcat:8")
                .create();
        Utils.checkNotNull(webApp, "");
        System.out.println(webApp.name());
        System.out.println(webApp.id());

        azure.resourceGroups().deleteByName(resourceGroupName);
        // create resource group
//        final ResourceGroup resourceGroup = azure.resourceGroups().define(resourceGroupName)
//                .withRegion(Region.AUSTRALIA_EAST)
//                .create();
    }
}
