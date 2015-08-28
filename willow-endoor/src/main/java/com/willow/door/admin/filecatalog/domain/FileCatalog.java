/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.filecatalog.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   订单信息领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class FileCatalog extends BaseObject{
    //分类名称
    private String catalogName;
    //分类英文名称
    private String catalogNameEn;

    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }
    public String getCatalogNameEn() {
        return catalogNameEn;
    }
    public void setCatalogNameEn(String catalogNameEn) {
        this.catalogNameEn = catalogNameEn;
    }
}
