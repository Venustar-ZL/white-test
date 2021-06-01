package com.learn.white_test.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * @Author: ZhangLei
 * @Date: 2021/06/01/22:32
 * @Description:
 * @Version: 1.0
 */
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {

        if (resource == null) {
            return super.createPropertySource(name, resource);
        }

        return new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource()).get(0);

    }
}
