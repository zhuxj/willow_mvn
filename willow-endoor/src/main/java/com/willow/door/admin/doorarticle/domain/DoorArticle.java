/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-02-02
 */
package com.willow.door.admin.doorarticle.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   文章管理领域对象
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
public class DoorArticle extends BaseObject {
    public static final String ARTICLETYPE_ABOUT = "about";
    public static final String ARTICLETYPE_OEM = "oem";

    //文章编码
    private String articleCode;
    //文章中文标题
    private String articleTitle;
    //文章英文标题
    private String articleTitleSn;
    //文章中文内容
    private String articleContent;
    //文章英文内容
    private String articleContentEn;
    //序号
    private Integer orderNo;
    //类型
    private String articleType;

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitleSn() {
        return articleTitleSn;
    }

    public void setArticleTitleSn(String articleTitleSn) {
        this.articleTitleSn = articleTitleSn;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleContentEn() {
        return articleContentEn;
    }

    public void setArticleContentEn(String articleContentEn) {
        this.articleContentEn = articleContentEn;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }
}
