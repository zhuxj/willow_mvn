
package com.willow.platform.common.settings.loader;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class SettingsLoaderFactory {

    private SettingsLoaderFactory() {

    }

    public static SettingsLoader loaderFromResource(String resourceName) {
        return new YamlSettingsLoader();
    }
}
