/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-9
 */
package com.willow.weixin.utils;

import com.willow.weixin.domain.AccessToken;
import com.willow.weixin.menu.domain.Menu;
import com.willow.weixin.message.request.BaseMessage;
import com.willow.weixin.user.domain.User;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * <pre>
 *  公众平台通用接口工具类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class WeixinUtil {
    /**
     * 日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
    public static final String REQUEST_METHOD_DEFAULT = "POST";
    // 获取access_token的接口地址（GET） 限2000（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 菜单创建（POST） 限1000（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    //发送客服消息
    public static String message_send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    //用户分组创建
    public static String groups_create_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    //获取用户基本信息
    public static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 获取用户基本信息
     *
     * @param openId
     * @param accessToken
     * @return
     */
    public User getUserInfo(String openId, String accessToken) {
        User user = null;
        String url = user_info_url.replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openId);
        JSONObject jsonObject = httpRequest(url, "GET", null);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                logger.error("发送客服消息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            } else {
                user = (User) JSONObject.toBean(jsonObject, User.class);
            }
        }
        return user;
    }

    /**
     * 发送客服消息
     *
     * @param message
     * @param accessToken
     * @return
     */
    public static int sendMessage(BaseMessage message, String accessToken) {
        int result = 0;
        // 拼装发送客服消息的url
        String url = message_send_url.replace("ACCESS_TOKEN", accessToken);
        // 将消息对象转换成json字符串
        String jsonMessage = JSONObject.fromObject(message).toString();
        // 调用接口发送客服消息
        JSONObject jsonObject = httpRequest(url, "POST", jsonMessage);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("发送客服消息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }


    /**
     * 查询菜单
     *
     * @param accessToken
     * @return
     */
    public Menu getMenu(String accessToken) {
        Menu result = null;
        // 拼装查询菜单的url
        String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口查询菜单
        JSONObject jsonObject = httpRequest(url, "POST", null);
        if (jsonObject != null) {
            if (0 != jsonObject.getInt("errcode")) {
                logger.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            } else {
                result = (Menu) JSONObject.toBean(jsonObject, Menu.class);
            }
        }
        return result;
    }

    /**
     * 创建菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int deleteMenu(String accessToken) {
        int result = 0;

        // 拼装删除菜单的url
        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", null);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }


    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        requestMethod = (requestMethod == null ? REQUEST_METHOD_DEFAULT : requestMethod);

        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}", e);
        }
        return jsonObject;
    }
}
