package com.willow.platform.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtils {

    private static Logger log = Logger.getLogger(HttpClientUtils.class);

    public static HttpClient createHttpClient() {
        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        return httpClient;
    }

    public static Reader getHTMlReader(HttpClient httpClient, String url, String encoding) throws IOException {
        url = encoding(url, encoding);

        GetMethod method = makeGetMethod(url);
        method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows xp)");
        method.setRequestHeader("Accept-Language", "zh-cn");
        method.setRequestHeader("Connection", "Keep-Alive");
        int statusCode = httpClient.executeMethod(method);
        if (statusCode != HttpStatus.SC_OK) {
            log.info("Method failed: " + method.getStatusLine());
            return null;
        }
        return getInputStream(method, encoding);
    }

    /**
     * 获得Method返回的字节流
     *
     * @param method
     * @param encoding
     * @return
     * @throws java.io.IOException
     */
    private static Reader getInputStream(HttpMethod method, String encoding) throws IOException {
        InputStream is = method.getResponseBodyAsStream();
        BufferedReader br = null;
        String newEncoding = getHTMLEncoding(method);
        if (StringUtils.isNotBlank(newEncoding)) {
            encoding = newEncoding;
        }
        if (StringUtils.isNotBlank(encoding))
            br = new BufferedReader(new InputStreamReader(is, encoding));
        else
            br = new BufferedReader(new InputStreamReader(is));

        return br;
    }

    public static String getHtmlContent(HttpClient httpClient, String url, String encoding) {
        String returnHtml = "";               

        url = encoding(url, encoding);

        GetMethod method = makeGetMethod(url);

        String htmlEncoding = getHTMLEncoding(method);
        if (StringUtils.isNotBlank(htmlEncoding)) {
            encoding = htmlEncoding;
        }

        try {
            method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows xp)");
            method.setRequestHeader("Accept-Language", "zh-cn");
            method.setRequestHeader("Connection", "Keep-Alive");
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("Method failed: " + method.getStatusLine());
                returnHtml = "";
            }
            returnHtml = getResponseText(method, encoding);

        } catch (Exception e) {
            log.error("获取HTML失败：" + e);
            returnHtml = "";
            //TODO
            //ErrorReportService.saveErrorMessage("获取HTML失败：", e);
        } finally {
            method.releaseConnection();
        }
        return returnHtml;
    }

    public static String postHtmlContent(HttpClient httpClient, String url, Map params, String encoding) {
        String returnHtml = "";
        PostMethod method = makePostMethod(url, params, encoding);
        try {
            method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows xp)");
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Method failed: " + method.getStatusLine());
                returnHtml = "";
            }

            returnHtml = getResponseText(method, encoding);
        } catch (IOException e) {
            log.error("获取HTML失败：" + e);
            returnHtml = "";
        } finally {
            method.releaseConnection();
        }
        return returnHtml;
    }


    public static String encoding(String url, String encoding) {
        if (url.indexOf("?") != -1) {
            String[] urls = url.split("\\?");
            String[] params = urls[1].split("&");
            for (int i = 0; i < params.length; i++) {
                String[] param = params[i].split("=");
                try {
                    if (param.length == 2)
                        params[i] = param[0] + "=" + URLEncoder.encode(param[1], encoding);
                } catch (UnsupportedEncodingException e) {
                    return url;
                }
            }
            return urls[0] + "?" + StringUtils.join(params, "&");
        }

        return url;
    }

    /**
     * 创建Post方法
     *
     * @param url
     * @param params
     * @return
     */
    public static PostMethod makePostMethod(String url, Map params, String encoding) {
        PostMethod method = new PostMethod(url);
        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = (String) params.get(key);
            try {
                NameValuePair param = new NameValuePair(new String(key.getBytes(), encoding)
                        , new String(value.getBytes(), encoding));
                method.addParameter(param);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=" + encoding);

        for (int i = 0; i < method.getRequestHeaders().length; i++) {
            Header header = method.getRequestHeaders()[i];
            System.out.println(header.getName() + ":" + header.getValue());
        }
        return method;
    }

    /**
     * 获得Method返回的文本信息
     *
     * @param method
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public static String getResponseText(HttpMethod method, String encoding) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream is = method.getResponseBodyAsStream();
        BufferedReader br = null;
        try {
            if (StringUtils.isNotBlank(encoding))
                br = new BufferedReader(new InputStreamReader(is, encoding));
            else
                br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
        } finally {
            if (br != null) {
                br.close();
            }

            if (is != null) {
                is.close();
            }
        }
        return sb.toString();
    }

    private static String getHTMLEncoding(HttpMethod method) {
        String encoding = null;
        Header responseHeader = method.getResponseHeader("Content-Type");
        if (responseHeader != null) {
            String contentType = responseHeader.getValue();
            if (StringUtils.isNotBlank(contentType)) {
                encoding = contentType.replace("text/html; charset=","");
            }
        }
        return encoding;
    }

    /**
     * 创建Get方法
     *
     * @param url
     * @return
     */
    private static GetMethod makeGetMethod(String url) {
        GetMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
        return method;
    }

    /**
     * 保存附件信息  add by zhuxj 2011-09-07
     *
     * @param url
     * @param savePath
     */
    public static void saveFileContent(HttpClient httpClient, String url, String savePath) {
        GetMethod method = makeGetMethod(url);
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Method failed: " + method.getStatusLine());
                return;
            }
            saveFile(savePath, method);
        } catch (IOException e) {
            log.error("保存文件" + url + "失败：" + e);
        } finally {
            method.releaseConnection();
        }
    }

    /**
     * 保存附件   add by zhuxj 2011-09-07
     *
     * @param savePath
     * @param method
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    private static void saveFile(String savePath, GetMethod method) throws IOException, FileNotFoundException {
        byte[] buffer = new byte[1024];
        InputStream is = method.getResponseBodyAsStream();
        FileOutputStream fos = new FileOutputStream(savePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int length = is.read(buffer);
        while (length > 0) {
            bos.write(buffer, 0, length);
            length = is.read(buffer);
        }
        bos.flush();
        bos.close();
        fos.close();
    }

}
