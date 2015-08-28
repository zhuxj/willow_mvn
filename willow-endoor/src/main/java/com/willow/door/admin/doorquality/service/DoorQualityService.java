/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-02-02
*/
package com.willow.door.admin.doorquality.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.doorquality.dao.DoorQualityDao;
import com.willow.door.admin.doorquality.domain.DoorQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
<pre>
 * 质检中心业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class DoorQualityService extends BaseService<DoorQuality> {
    @Autowired
    private DoorQualityDao doorQualityDao;
    @Override
    public BaseDao<DoorQuality> getDao() {
        return doorQualityDao;
    }

    public void setDoorQualityDao(DoorQualityDao doorQualityDao) {
        this.doorQualityDao = doorQualityDao;
    }
}
