/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-12
*/
package com.willow.door.admin.doorslide.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.door.admin.doorslide.domain.DoorSlide;
import com.willow.door.admin.doorslide.mapper.DoorSlideMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   幻灯片持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class DoorSlideDao extends BaseDao<DoorSlide> {
    @Override
    public Class getMapperClass() {
    return DoorSlideMapper.class;
    }
}
