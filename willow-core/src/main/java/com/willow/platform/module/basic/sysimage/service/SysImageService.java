/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2012-12-27
 */
package com.willow.platform.module.basic.sysimage.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.platform.module.basic.sysimage.dao.SysImageDao;
import com.willow.platform.module.basic.sysimage.domain.SysImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 图片附件业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class SysImageService extends BaseService<SysImage> {
    @Autowired
    private SysImageDao sysImageDao;

    public SysImage querySysImageByImageId(String imageId) {
        SysImage filter = new SysImage();
        filter.setImageId(imageId);
        return sysImageDao.selectByCondition(filter);
    }

    @Override
    public BaseDao<SysImage> getDao() {
        return sysImageDao;
    }

    public void setSysImageDao(SysImageDao sysImageDao) {
        this.sysImageDao = sysImageDao;
    }
}
