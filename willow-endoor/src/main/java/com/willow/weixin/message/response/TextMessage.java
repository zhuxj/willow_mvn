/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-4
 */
package com.willow.weixin.message.response;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
/**
 * 文本消息
 */
public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}