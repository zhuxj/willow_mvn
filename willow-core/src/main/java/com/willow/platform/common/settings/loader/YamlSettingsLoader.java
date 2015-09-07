package com.willow.platform.common.settings.loader;

import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class YamlSettingsLoader implements SettingsLoader {
    @Override
    public Map<String, String> load(String source) throws IOException {
        // replace tabs with whitespace (yaml does not accept tabs, but many users might use it still...)
        source = source.replace("\t", "  ");
        Yaml yaml = new Yaml();
        Map<Object, Object> yamlMap = (Map<Object, Object>) yaml.load(source);
        return Helper.loadNestedFromMap(yamlMap);
    }

    @Override
    public Map<String, String> load(byte[] source) throws IOException {
        Yaml yaml = new Yaml();
        Map<Object, Object> yamlMap = (Map<Object, Object>) yaml.load(new ByteArrayInputStream(source));
        return Helper.loadNestedFromMap(yamlMap);
    }
}
