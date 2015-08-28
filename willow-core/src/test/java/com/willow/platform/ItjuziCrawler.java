/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2015 
 * 日    期：15-7-21
 */
package com.willow.platform;

import com.willow.platform.utils.*;
import com.willow.platform.utils.Json.JsonParser;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.oro.text.regex.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class ItjuziCrawler {
    /**
     * 日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(ItjuziCrawler.class);
    private final HttpClient httpClient;

    public static final String WEB_SITE = "http://itjuzi.com/";
    public static final String WEB_LOGON_SITE = "http://i.xizhi.com/e/";

    public static final String userName = "zhuxj";
    public static final String passwd = "123456";

    public ItjuziCrawler() {
        httpClient = new HttpClient();
        httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
    }

    /**
     * 模拟登录
     */
    public Boolean logonWeb() {
        Boolean boolResult = true;
        String logonUrl = WEB_SITE + "enews/index.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName); //用户名
        params.put("password", passwd);//密码
        params.put("enews", "login");//密码
        params.put("ts", new Date().getTime() + "");//时间戳
        String logonContext = HttpClientUtils.postHtmlContent(this.httpClient, logonUrl, params, "UTF-8");
        System.out.println(logonContext);
        List<JSONObject> list = JsonParser.parseJsonArray("[" + logonContext + "]");
        if (list.size() == 0) {
            logger.info("返回结果list为0，即登录失败");
            boolResult = false;
            return boolResult;
        }
        JSONObject jsonObject = list.get(0);
        Integer result = (Integer) jsonObject.get("status");
        if (result == 1) {
            boolResult = true;
            logger.info("模拟登录成功");
        } else {
            boolResult = false;
            logger.info("模拟登录失败");
        }
        return boolResult;
    }


    public List<Map<String, String>> listCrawl(String scope, String prov, Integer pageNo) {
        List<Map<String, String>> tlist = new ArrayList<Map<String, String>>();
        StringBuffer paramSb = new StringBuffer();
        paramSb.append("scope=").append(scope)
                .append("&page=").append(pageNo)
                .append("&ts=").append(new Date().getTime())
        ;
        if (LocalStringUtils.isNotEmpty(prov)) {
            paramSb.append("&prov=").append(prov);
        }


        String listUrl = WEB_SITE + "/company?" + paramSb.toString();
        String listContext = HttpClientUtils.getHtmlContent(this.httpClient, listUrl, "UTF-8");
//        System.out.println(listContext);

        String regexStr = "<p class=\"infotext\">([^>]+?)</p>\n" +
                "                                <a href=\"([^>]+?)\"><img width=\"160\" height=\"120\" class=\"media-object\" src=\"([^>]+?)\" alt=\"([^>]+?)\" title=\"([^>]+?)\"></a> ";
        tlist = RegexUtils.getObjects(listContext, regexStr, new MatcherCollector() {
            @Override
            public Map<String, String> match(MatchResult matchResult) {
                Map<String, String> map = new HashMap<String, String>();
                matchResult.groups();
                map.put("comid", matchResult.group(2));
                return map;
            }
        });

        for (Map<String, String> map : tlist) {
            String comid = map.get("comid");
//            System.out.println(comid);
            String detailUrl = comid + "?ts=" + new Date().getTime();
            String detailContext = HttpClientUtils.getHtmlContent(this.httpClient, detailUrl, "UTF-8");
//            System.out.println(detailContext);
            String cname = RegexUtils.getString(detailContext, "<li>公司:  <em>([^>]+?)</em></li>", 1);
            String hy = RegexUtils.getString(detailContext, "<li>行业: \n" +
                    "\t\t\t\t<a href=\"([^>]+?)\" scope_id=\"([^>]+?)\" id=\"scope_id_value\">([^>]+?)</a>\t\t\t</li>", 3);
            String site = RegexUtils.getString(detailContext, "<li>网址:  <a target=\"_blank\" href=\"([^>]+?)\">([^>]+?)</a></li>", 1);
            String remark = RegexUtils.getString(detailContext, "<li>简介:  <em>([^>]+?)</em></li>", 1);
            System.out.println(DateUtils.getCurrDateStr("yyyy-MM-dd") + "\t" + cname + "\t" + hy + "\t\t\t\t\t\t" + remark + "\t" + site);
//            return null;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return tlist;
    }


    public static void main(String[] args) {
        System.out.println("日期\t公司名称\t行业\t联系人\t职位\t电话\tQQ\t邮箱\t备注\t网址");
        ItjuziCrawler crawler = new ItjuziCrawler();
//        xizhiCrawler.logonXiZhi();
        String prov = "";
//        String scope = "1"; //教育
//        String scope = "12"; //金融
//        String scope = "28"; //汽车交通
        String scope = "38"; //房产
//        String scope = "145"; //145为电子商务

//        每页显示30家
        int start = 1;
        int craPage = 4;
        for (int i = start; i < start + craPage; i++) {
            crawler.listCrawl(scope, prov, i);
        }

    }
}
