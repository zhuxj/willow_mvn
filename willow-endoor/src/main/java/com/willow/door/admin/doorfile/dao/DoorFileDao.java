/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.doorfile.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.door.admin.doorfile.domain.DoorFile;
import com.willow.door.admin.doorfile.mapper.DoorFileMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   文件信息持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class DoorFileDao extends BaseDao<DoorFile> {
    @Override
    public Class getMapperClass() {
    return DoorFileMapper.class;
    }
}
