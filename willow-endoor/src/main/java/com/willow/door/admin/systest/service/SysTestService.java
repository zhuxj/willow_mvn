/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-03-10
*/
package com.willow.door.admin.systest.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.systest.dao.SysTestDao;
import com.willow.door.admin.systest.domain.SysTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
<pre>
 * 测试业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class SysTestService extends BaseService<SysTest> {
    @Autowired
    private SysTestDao sysTestDao;
    @Override
    public BaseDao<SysTest> getDao() {
        return sysTestDao;
    }

    public void setSysTestDao(SysTestDao sysTestDao) {
        this.sysTestDao = sysTestDao;
    }
}
