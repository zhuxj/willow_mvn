/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-09
*/
package com.willow.door.admin.doororder.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.door.admin.doororder.domain.DoorOrder;
import com.willow.door.admin.doororder.mapper.DoorOrderMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   订单信息持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class DoorOrderDao extends BaseDao<DoorOrder> {
    @Override
    public Class getMapperClass() {
    return DoorOrderMapper.class;
    }
}
