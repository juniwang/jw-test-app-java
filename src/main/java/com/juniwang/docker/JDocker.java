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

package com.juniwang.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.apache.commons.lang3.SystemUtils;

public class JDocker {

    private DockerClient getDockerClient() {
        final DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:2375")
//            .withDockerTlsVerify(true)
//            .withDockerCertPath("/home/user/.docker/certs")
//            .withDockerConfig("/home/user/.docker")
//            .withApiVersion("1.23")
//            .withRegistryUrl("https://index.docker.io/v1/")
//            .withRegistryUsername("dockeruser")
//            .withRegistryPassword("ilovedocker")
//            .withRegistryEmail("dockeruser@github.com")
                .build();
        final DockerClient docker = DockerClientBuilder.getInstance(config).build();
        return docker;
    }

    public void ping() {
        final DockerClient dockerClient = getDockerClient();
        dockerClient.pingCmd().exec();
        final String os = System.getProperty("os.name");
        System.out.println("ping successfully on " + os);
        System.out.println(SystemUtils.IS_OS_WINDOWS);
    }
}
