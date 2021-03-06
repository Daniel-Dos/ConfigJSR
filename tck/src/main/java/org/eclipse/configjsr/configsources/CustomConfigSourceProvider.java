/*
 * Copyright (c) 2016-2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.eclipse.configjsr.configsources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.config.spi.ConfigSource;
import javax.config.spi.ConfigSourceProvider;


/**
 * @author <a href="mailto:struberg@apache.org">Mark Struberg</a>
 */
public class CustomConfigSourceProvider implements ConfigSourceProvider {

    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        List<ConfigSource> detectedConfigSources = new ArrayList<>();

        Enumeration<URL> yamlFiles = null;
        try {
            yamlFiles = forClassLoader.getResources("sampleconfig.yaml");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (yamlFiles.hasMoreElements()) {
            detectedConfigSources.add(new SampleYamlConfigSource(yamlFiles.nextElement()));
        }
        return detectedConfigSources;
    }
}
