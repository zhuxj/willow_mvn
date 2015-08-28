/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-9
 */
package com.willow.weixin.menu.domain;

/**
 * <pre>
 *   view类型的菜单（子按钮）
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class ViewButton extends Button {
    private String type;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}