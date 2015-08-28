/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-4
 */
package com.willow.weixin.message.request;

/**
 * <pre>
 *  图片消息
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
