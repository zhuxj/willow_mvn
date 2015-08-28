/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-02-02
*/
package com.willow.door.admin.doorquality.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.door.admin.doorquality.domain.DoorQuality;
import com.willow.door.admin.doorquality.mapper.DoorQualityMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   质检中心持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class DoorQualityDao extends BaseDao<DoorQuality> {
    @Override
    public Class getMapperClass() {
    return DoorQualityMapper.class;
    }
}
