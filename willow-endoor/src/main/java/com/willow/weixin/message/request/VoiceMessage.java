/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-4
 */
package com.willow.weixin.message.request;

/**
 * <pre>
 * 音频消息
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class VoiceMessage extends BaseMessage {
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
