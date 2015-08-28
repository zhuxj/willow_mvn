/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-01-09
 */
package com.willow.door.admin.doororder.service;

import com.willow.door.admin.doororder.domain.DoorOrderUtil;
import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.doororder.dao.DoorOrderDao;
import com.willow.door.admin.doororder.domain.DoorOrder;
import com.willow.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 订单信息业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class DoorOrderService extends BaseService<DoorOrder> {
    @Autowired
    private DoorOrderDao doorOrderDao;

    /**
     * 提交订单
     *
     * @param doorOrder
     * @return
     */
    public int createOrder(DoorOrder doorOrder) {
        doorOrder.setOrderNo(DateUtils.getCurrTimeStr(DateUtils.DB_STORE_DATE));
        doorOrder.setOrderStatus(DoorOrderUtil.ORDER_STATUS_SUBMIT);
        return doorOrderDao.save(doorOrder);
    }


    @Override
    public BaseDao<DoorOrder> getDao() {
        return doorOrderDao;
    }

    public void setDoorOrderDao(DoorOrderDao doorOrderDao) {
        this.doorOrderDao = doorOrderDao;
    }
}
