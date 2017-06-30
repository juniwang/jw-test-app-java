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
import com.microsoft.azure.management.appservice.*;
import com.microsoft.azure.management.appservice.implementation.SiteConfigResourceInner;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;

import java.util.ArrayList;
import java.util.List;

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

        DeploymentSlot deploymentSlot = webApp.deploymentSlots().define("staging")
                .withConfigurationFromWebApp(webApp)
                .create();
        System.out.println(deploymentSlot.id());

        azure.resourceGroups().deleteByName(resourceGroupName);
    }

    public static void getDeploymentSiteInner() {
        final Azure azure = AzureUtils.getAzureClient();
        WebApp webApp = azure.webApps().getByResourceGroup("jw-webapp-linux-tomcat-05", "jw-webapp-linux-tomcat-05");
        Utils.checkNotNull(webApp, "");

        DeploymentSlot slot = webApp.deploymentSlots().getByName("testing");
        Utils.checkNotNull(slot, "");

        SiteConfigResourceInner siteConfigResourceInner = azure.webApps().inner().getConfigurationSlot(slot.resourceGroupName(), webApp.name(), slot.name());
        Utils.checkNotNull(siteConfigResourceInner, "");

        List<NameValuePair> appSettings = new ArrayList<NameValuePair>();
        appSettings.add(new NameValuePair().withName("DOCKER_CUSTOM_IMAGE_NAME").withValue("jwregistry001.azurecr.io/nginx"));
        appSettings.add(new NameValuePair().withName("DOCKER_REGISTRY_SERVER_URL").withValue("http://jwregistry001.azurecr.io"));
        appSettings.add(new NameValuePair().withName("DOCKER_REGISTRY_SERVER_USERNAME").withValue("jwregistry001"));
        appSettings.add(new NameValuePair().withName("DOCKER_REGISTRY_SERVER_PASSWORD").withValue(""));
        siteConfigResourceInner.withLinuxFxVersion("DOCKER|jwregistry001.azurecr.io/nginx");
        siteConfigResourceInner.withAppSettings(appSettings);
        azure.webApps().inner().updateConfigurationSlot(webApp.resourceGroupName(), webApp.name(), slot.name(), siteConfigResourceInner);

//        DeploymentSlot.Update update = slot.update();
//        SiteConfig siteConfig = new SiteConfig().withLinuxFxVersion("DOCKER|jwregistry001.azurecr.io/jwregistry001/tomcat8-sshd:5");
//        slot.inner().withSiteConfig(siteConfig).withKind("app");
//        update.withAppSetting("DOCKER_CUSTOM_IMAGE_NAME", "jwregistry001.azurecr.io/jwregistry001/tomcat8-sshd:5");
//        update.withAppSetting("DOCKER_REGISTRY_SERVER_URL", "http://jwregistry001.azurecr.io");
//        update.withAppSetting("DOCKER_REGISTRY_SERVER_USERNAME", "jwregistry001");
//        update.withAppSetting("DOCKER_REGISTRY_SERVER_PASSWORD", "/F/t7lRxG+=VlcSsil+pgoC1w9Sc5hIe");
//        slot.inner().siteConfig();
//        update.apply();
    }
}
