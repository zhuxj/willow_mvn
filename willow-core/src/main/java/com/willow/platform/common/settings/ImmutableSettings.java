package com.willow.platform.common.settings;

import com.google.common.collect.ImmutableMap;
import com.willow.platform.common.Classes;
import com.willow.platform.common.helper.StreamHelper;
import com.willow.platform.common.settings.loader.SettingsLoader;
import com.willow.platform.common.settings.loader.SettingsLoaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.willow.platform.common.Strings.toCamelCase;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class ImmutableSettings implements Settings {
    private ImmutableMap<String, String> settings;
    private transient ClassLoader classLoader;

    private ImmutableSettings(Map<String, String> settings, ClassLoader classLoader) {
        this.settings = ImmutableMap.copyOf(settings);
        this.classLoader = classLoader;
    }

    @Override
    public ImmutableMap<String, String> getAsMap() {
        return this.settings;
    }

    @Override
    public Settings getByPrefix(String prefix) {
        Builder builder = new Builder();
        for (Map.Entry<String, String> entry : getAsMap().entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                if (entry.getKey().length() < prefix.length()) {
                    // ignore this one
                    continue;
                }
                builder.put(entry.getKey().substring(prefix.length()), entry.getValue());
            }
        }
        builder.classLoader(classLoader);
        return builder.build();
    }

    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader == null ? Classes.getDefaultClassLoader() : classLoader;
    }

    @Override
    public ClassLoader getClassLoaderIfSet() {
        return this.classLoader;
    }

    @Override
    public String get(String setting) {
        String retVal = settings.get(setting);
        if (retVal != null) {
            return retVal;
        }
        return settings.get(toCamelCase(setting));
    }

    @Override
    public String get(String setting, String defaultValue) {
        String retVal = settings.get(setting);
        return retVal == null ? defaultValue : retVal;
    }

    @Override
    public Float getAsFloat(String setting, Float defaultValue) throws SettingsException {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse float setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    @Override
    public Double getAsDouble(String setting, Double defaultValue) throws SettingsException {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse double setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    @Override
    public Integer getAsInt(String setting, Integer defaultValue) throws SettingsException {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse int setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    @Override
    public Long getAsLong(String setting, Long defaultValue) throws SettingsException {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(sValue);
        } catch (NumberFormatException e) {
            throw new SettingsException("Failed to parse long setting [" + setting + "] with value [" + sValue + "]", e);
        }
    }

    @Override
    public Boolean getAsBoolean(String setting, Boolean defaultValue) throws SettingsException {
        String sValue = get(setting);
        if (sValue == null) {
            return defaultValue;
        }
        return !(sValue.equals("false") || sValue.equals("0") || sValue.equals("off") || sValue.equals("no"));
    }

    public static Builder settingsBuilder() {
        return new Builder();
    }

    public static class Builder implements Settings.Builder {
        public static final Settings EMPTY_SETTINGS = new Builder().build();

        private final Map<String, String> map = new LinkedHashMap<String, String>();

        private ClassLoader classLoader;

        public Builder put(Settings settings) {
            map.putAll(settings.getAsMap());
            classLoader = settings.getClassLoaderIfSet();
            return this;
        }

        public Builder loadFromUrl(URL url) throws SettingsException {
            try {
                return loadFromStream(url.toExternalForm(),url.openStream());
            } catch (IOException e) {
                throw new SettingsException("Failed to open stream for url [" + url.toExternalForm() + "]", e);
            }
        }

        public Builder loadFromStream(String resourceName, InputStream is) throws SettingsException {
            SettingsLoader settingsLoader = SettingsLoaderFactory.loaderFromResource(resourceName);
            try {
                Map<String, String> loadedSettings = settingsLoader.load(StreamHelper.copyToString(new InputStreamReader(is, "UTF-8")));
                put(loadedSettings);
            } catch (Exception e) {
                throw new SettingsException("Failed to load settings from [" + resourceName + "]", e);
            }
            return this;
        }

        public Builder put(Map<String, String> settings) {
            map.putAll(settings);
            return this;
        }

        public Builder put(String key, String value) {
            map.put(key, value);
            return this;
        }

        public Builder classLoader(ClassLoader classLoader) {
            this.classLoader = classLoader;
            return this;
        }

        @Override
        public Settings build() {
            return new ImmutableSettings(Collections.unmodifiableMap(map), classLoader);
        }
    }
}
