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

import java.util.List;

/**
 * 多图文消息，
 * 单图文的时候 Articles 只放一个就行了
 *
 * @author 朱贤俊
 */
public class NewsMessage extends BaseMessage {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}