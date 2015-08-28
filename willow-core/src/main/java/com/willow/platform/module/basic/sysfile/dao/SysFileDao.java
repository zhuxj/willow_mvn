/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.platform.module.basic.sysfile.dao;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.module.basic.sysfile.domain.SysFile;
import com.willow.platform.module.basic.sysfile.mapper.SysFileMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *   附件持久层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Repository
public class SysFileDao extends BaseDao<SysFile> {
    @Override
    public Class getMapperClass() {
    return SysFileMapper.class;
    }
}
