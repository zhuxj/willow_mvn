/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.filecatalog.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.door.admin.filecatalog.dao.FileCatalogDao;
import com.willow.door.admin.filecatalog.domain.FileCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
<pre>
 * 订单信息业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class FileCatalogService extends BaseService<FileCatalog> {
    @Autowired
    private FileCatalogDao fileCatalogDao;
    @Override
    public BaseDao<FileCatalog> getDao() {
        return fileCatalogDao;
    }

    public void setFileCatalogDao(FileCatalogDao fileCatalogDao) {
        this.fileCatalogDao = fileCatalogDao;
    }
}
