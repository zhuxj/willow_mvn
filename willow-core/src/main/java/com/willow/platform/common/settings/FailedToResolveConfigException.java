package com.willow.platform.common.settings;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class FailedToResolveConfigException extends RuntimeException {

    public FailedToResolveConfigException() {
    }

    public FailedToResolveConfigException(String message) {
        super(message);
    }

    public FailedToResolveConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToResolveConfigException(Throwable cause) {
        super(cause);
    }
}
