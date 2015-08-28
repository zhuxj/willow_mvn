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
public class XizhiCrawler {
    /**
     * 日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(XizhiCrawler.class);
    private final HttpClient httpClient;

    public static final String XIZHI_SITE = "http://www.xizhi.com";
    public static final String XIZHI_LOGON_SITE = "http://i.xizhi.com/e/";

    public static final String userName = "zhuxj";
    public static final String passwd = "123456";

    public XizhiCrawler() {
        httpClient = new HttpClient();
        httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
    }

    /**
     * 模拟登录
     */
    public Boolean logonXiZhi() {
        Boolean boolResult = true;
        String logonUrl = XIZHI_LOGON_SITE + "enews/index.php";
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


    public List<Map<String, String>> listCrawl(String wd, Integer pageNo) {
        List<Map<String, String>> tlist = new ArrayList<Map<String, String>>();
        String listUrl = XIZHI_SITE + "/zhi?wd=" + wd + "&p=" + pageNo + "&ts=" + new Date().getTime();
        String listContext = HttpClientUtils.getHtmlContent(this.httpClient, listUrl, "UTF-8");
//        System.out.println(listContext);
        String regexStr = "<span class=\"s-shareBox bdsharebox disnone\" comid=\"([^>]+?)\"><span class=\"s-share bdsharenum\">0</span></span>";
        tlist = RegexUtils.getObjects(listContext, regexStr, new MatcherCollector() {
            @Override
            public Map<String, String> match(MatchResult matchResult) {
                Map<String, String> map = new HashMap<String, String>();
                matchResult.groups();
                map.put("comid", matchResult.group(1));
                return map;
            }
        });

        for (Map<String, String> map : tlist) {
            String comid = map.get("comid");
//            System.out.println(comid);
            String detailUrl = XIZHI_SITE + "/" + comid + "?ts=" + new Date().getTime();
            String detailContext = HttpClientUtils.getHtmlContent(this.httpClient, detailUrl, "UTF-8");
//            System.out.println(detailContext);
            String cname = RegexUtils.getString(detailContext, "                                <li class=\"clearfix\">\n" +
                    "                                    <label>联系人：</label>\n" +
                    "                                    <p class=\"conName\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"addhref\" name=\"([^>]+?)\" rel=\"nofollow\">([^>]+?)</a><span class=\"tosearch\">搜索相同联系人企业</span>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</p>\n" +
                    "                                </li>", 1);
            if (LocalStringUtils.isEmpty(cname)) {
                cname = RegexUtils.getString(detailContext, "                                <li class=\"clearfix\">\n" +
                        "                                    <label>联系人：</label>\n" +
                        "                                    <p class=\"conName\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"addhref\" name=\"([^>]+?)\">([^>]+?)</a><span class=\"tosearch\">搜索相同联系人企业</span>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</p>\n" +
                        "                                </li>", 1);
            }
            String tel = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>电话：</label>\n" +
                    "                                    <p>([^>]+?)</p>\n" +
                    "                                </li>", 1);
            String phone = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>手机：</label>\n" +
                    "                                    <p><span class=\"telNum\">([^>]+?)</span><a href=\"javascript:;\" class=\"viewPhone weixin_phone\" name=\"([^>]+?)\">查看手机号</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:;\" class=\"viewPhone quickphone\" name=\"([^>]+?)\" com=\"([^>]+?)\" conn=\"([^>]+?)\" phone=\"([^>]+?)\">快速查看</a></p>\n" +
                    "                                </li>", 6);
            String com = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>手机：</label>\n" +
                    "                                    <p><span class=\"telNum\">([^>]+?)</span><a href=\"javascript:;\" class=\"viewPhone weixin_phone\" name=\"([^>]+?)\">查看手机号</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:;\" class=\"viewPhone quickphone\" name=\"([^>]+?)\" com=\"([^>]+?)\" conn=\"([^>]+?)\" phone=\"([^>]+?)\">快速查看</a></p>\n" +
                    "                                </li>", 4);
            String cz = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>传真：</label>\n" +
                    "                                    <p>([^>]+?)</p>\n" +
                    "                                </li>", 1);
            String email = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>邮箱：</label>\n" +
                    "                                    <p>([^>]+?)</p>\n" +
                    "                                </li>", 1);
            String qq = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>QQ：</label>\n" +
                    "                                    <p>([^>]+?)</p>\n" +
                    "                                </li>", 1);
            String site = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>网址：</label>\n" +
                    "                                    <p>([^>]+?)</p>\n" +
                    "                                </li>", 1);
            String addr = RegexUtils.getString(detailContext, "<li class=\"clearfix\">\n" +
                    "                                    <label>地址：</label>\n" +
                    "                                    <p>\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"addhref\" name=\"([^>]+?)\" rel=\"nofollow\">([^>]+?)</a>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</p>\n" +
                    "                                </li>", 1);

            System.out.println(DateUtils.getCurrDateStr("yyyy-MM-dd") + "\t" + com + "\t\t" + cname + "\t\t" + tel + "(" + phone + ")" + "\t" + qq + "\t" + email + "\t" + "" + "\t" + site);
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
        XizhiCrawler xizhiCrawler = new XizhiCrawler();
//        xizhiCrawler.logonXiZhi();
        String kw = "电商";

        int start = 1;

        for (int i = start; i < start + 5; i++) {
            xizhiCrawler.listCrawl(kw, i);
        }

    }
}
