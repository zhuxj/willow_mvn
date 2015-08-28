/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-9
 */
package com.willow.weixin.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * <pre>
 *  证书信任管理器（用于https请求）
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class MyX509TrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
