/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-12
*/
package com.willow.door.admin.doorslide.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.doorslide.dao.DoorSlideDao;
import com.willow.door.admin.doorslide.domain.DoorSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
<pre>
 * 幻灯片业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class DoorSlideService extends BaseService<DoorSlide> {
    @Autowired
    private DoorSlideDao doorSlideDao;
    @Override
    public BaseDao<DoorSlide> getDao() {
        return doorSlideDao;
    }

    public void setDoorSlideDao(DoorSlideDao doorSlideDao) {
        this.doorSlideDao = doorSlideDao;
    }
}
