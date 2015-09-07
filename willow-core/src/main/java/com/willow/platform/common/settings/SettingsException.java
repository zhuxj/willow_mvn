package com.willow.platform.common.settings;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class SettingsException extends RuntimeException {

    public SettingsException() {
    }

    public SettingsException(String message) {
        super(message);
    }

    public SettingsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettingsException(Throwable cause) {
        super(cause);
    }
}
