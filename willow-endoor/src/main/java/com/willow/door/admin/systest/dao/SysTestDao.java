/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-03-10
*/
package com.willow.door.admin.systest.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.door.admin.systest.domain.SysTest;
import com.willow.door.admin.systest.mapper.SysTestMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   测试持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class SysTestDao extends BaseDao<SysTest> {
    @Override
    public Class getMapperClass() {
    return SysTestMapper.class;
    }
}
