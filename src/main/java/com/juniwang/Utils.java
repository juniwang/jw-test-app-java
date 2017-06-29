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

package com.juniwang;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Utils {
    public static String generateRandomString(int length) {
        final String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("[^a-z0-9]", "a").substring(0, length);
    }

    public static String generateRandomString(String prefix, String suffix, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(prefix))
            stringBuilder.append(prefix);

        final String uuid = UUID.randomUUID().toString();
        stringBuilder.append(uuid.replaceAll("[^a-z0-9]", "a").substring(0, length));

        if (StringUtils.isNotBlank(suffix))
            stringBuilder.append(suffix);

        return stringBuilder.toString();
    }

    public static <T> void checkNotNull(T reference, String message) {
        if (reference == null)
            throw new NullPointerException(message);
    }
}
