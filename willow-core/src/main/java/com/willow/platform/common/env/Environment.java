package com.willow.platform.common.env;

import com.willow.platform.common.settings.FailedToResolveConfigException;
import com.willow.platform.common.settings.Settings;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static com.willow.platform.common.Strings.cleanPath;
import static com.willow.platform.common.settings.ImmutableSettings.Builder.EMPTY_SETTINGS;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class Environment {
    private final Settings settings;
    private final File homeFile;
    private final File configFile;

    public Environment() {
        this(EMPTY_SETTINGS);
    }

    public Environment(Settings settings) {
        this.settings = settings;
        if (settings.get("path.home") != null) {
            homeFile = new File(cleanPath(settings.get("path.home")));
        } else {
            homeFile = new File(System.getProperty("user.dir"));
        }
        if (settings.get("path.conf") != null) {
            configFile = new File(cleanPath(settings.get("path.conf")));
        } else {
            configFile = new File(homeFile, "config");
        }
    }

    public URL resolveConfig(String path) throws FailedToResolveConfigException {
        String origPath = path;
        // first, try it as a path on the file system
        File f1 = new File(path);
        if (f1.exists()) {
            try {
                return f1.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new FailedToResolveConfigException("Failed to resolve path [" + f1 + "]", e);
            }
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        // next, try it relative to the config location
        File f2 = new File(configFile, path);
        if (f2.exists()) {
            try {
                return f2.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new FailedToResolveConfigException("Failed to resolve path [" + f2 + "]", e);
            }
        }
        // try and load it from the classpath directly
        URL resource = settings.getClassLoader().getResource(path);
        if (resource != null) {
            return resource;
        }
        // try and load it from the classpath with config/ prefix
        if (!path.startsWith("conf/")) {
            resource = settings.getClassLoader().getResource("conf/" + path);
            if (resource != null) {
                return resource;
            }
        }

        if (!path.startsWith("config/")) {
            resource = settings.getClassLoader().getResource("config/" + path);
            if (resource != null) {
                return resource;
            }
        }
        throw new FailedToResolveConfigException("Failed to resolve config path [" + origPath + "], tried file path [" + f1 + "], path file [" + f2 + "], and classpath");
    }
}
