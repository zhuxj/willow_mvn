package com.willow.platform.common.settings;

import com.google.common.collect.ImmutableMap;

public interface Settings {

    ImmutableMap<String, String> getAsMap();

    Settings getByPrefix(String prefix);

    ClassLoader getClassLoader();

    ClassLoader getClassLoaderIfSet();

    String get(String setting);

    String get(String setting, String defaultValue);

    Float getAsFloat(String setting, Float defaultValue) throws SettingsException;

    Double getAsDouble(String setting, Double defaultValue) throws SettingsException;

    Integer getAsInt(String setting, Integer defaultValue) throws SettingsException;

    Long getAsLong(String setting, Long defaultValue) throws SettingsException;

    Boolean getAsBoolean(String setting, Boolean defaultValue) throws SettingsException;


    interface Builder {

        /**
         * Builds the settings.
         */
        Settings build();
    }
}
