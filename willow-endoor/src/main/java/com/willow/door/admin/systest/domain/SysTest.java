/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-03-10
*/
package com.willow.door.admin.systest.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   测试领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class SysTest extends BaseObject{
    //菜单名称
    private String menuName;
    //菜单编码
    private String menuCode;
    //父菜单
    private String parentMenuId;
    //排序号
    private Integer orderNo;
    //菜单类型
    private String menuType;
    //节点类型
    private String nodeType;
    //菜单链接
    private String url;
    //节点图标
    private String icon;

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getParentMenuId() {
        return parentMenuId;
    }
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    public String getMenuType() {
        return menuType;
    }
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    public String getNodeType() {
        return nodeType;
    }
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
