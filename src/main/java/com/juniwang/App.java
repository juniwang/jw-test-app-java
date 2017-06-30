package com.juniwang;

import com.juniwang.msazure.JAppService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        testAppServiceCreate();
    }

    static void testAppServiceCreate() {
        JAppService.getDeploymentSiteInner();
    }
}
