/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2012-12-21
 */
package com.willow.door.admin.doorarticle.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.doorarticle.dao.DoorArticleDao;
import com.willow.door.admin.doorarticle.domain.DoorArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 产品信息业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class DoorArticleService extends BaseService<DoorArticle> {
    public static final String ABOUT = "about";

    @Autowired
    private DoorArticleDao doorArticleDao;

    /**
     * 根据文章编码查询文章
     *
     * @param articleCode
     * @return
     */
    public DoorArticle queryDoorArticleByCode(String articleCode) {
        DoorArticle filter = new DoorArticle();
        filter.setArticleCode(articleCode);
        return doorArticleDao.selectByCondition(filter);
    }


    @Override
    public BaseDao<DoorArticle> getDao() {
        return doorArticleDao;
    }

    public void setDoorArticleDao(DoorArticleDao doorArticleDao) {
        this.doorArticleDao = doorArticleDao;
    }
}
