/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.doorfile.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.doorfile.dao.DoorFileDao;
import com.willow.door.admin.doorfile.domain.DoorFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
<pre>
 * 文件信息业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class DoorFileService extends BaseService<DoorFile> {
    @Autowired
    private DoorFileDao doorFileDao;
    @Override
    public BaseDao<DoorFile> getDao() {
        return doorFileDao;
    }

    public void setDoorFileDao(DoorFileDao doorFileDao) {
        this.doorFileDao = doorFileDao;
    }
}
