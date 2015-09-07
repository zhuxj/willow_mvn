package com.willow.platform.common;

/**
 * @author 朱贤俊
 *         功能说明:
 */
public class Classes {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = Classes.class.getClassLoader();
        }
        return cl;
    }
}
