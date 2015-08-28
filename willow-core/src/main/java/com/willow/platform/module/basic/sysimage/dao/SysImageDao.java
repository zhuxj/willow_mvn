/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2012-12-27
*/
package com.willow.platform.module.basic.sysimage.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.module.basic.sysimage.domain.SysImage;
import com.willow.platform.module.basic.sysimage.mapper.SysImageMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   图片附件持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class SysImageDao extends BaseDao<SysImage> {
    @Override
    public Class getMapperClass() {
    return SysImageMapper.class;
    }
}
