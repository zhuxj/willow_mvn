/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-01-09
 */
package com.willow.door.admin.doororder.domain;

import com.willow.door.admin.product.domain.Product;
import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   订单信息领域对象
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
public class DoorOrder extends BaseObject {
    //产品ID
    private String productId;
    private String productName;
    private Product product;

    //订单编号
    private String orderNo;
    //订单数量
    private Integer orderNum;
    //联系人
    private String contactName;
    //联系地址
    private String contactAddress;
    //联系手机
    private String contactPhone;
    //联系电话
    private String contactTel;
    //电子邮箱
    private String contactEmail;
    //QQ
    private String contactQq;
    //订单状态
    private String orderStatus;
    //备注
    private String contactMemo;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactQq() {
        return contactQq;
    }

    public void setContactQq(String contactQq) {
        this.contactQq = contactQq;
    }

    public String getDisplayOrderStatus() {
        if (DoorOrderUtil.ORDER_STATUS_SUBMIT.equals(orderStatus)) {
            return "未处理";
        }

        if (DoorOrderUtil.ORDER_STATUS_FINISH.equals(orderStatus)) {
            return "已处理";
        }

        return "";
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getContactMemo() {
        return contactMemo;
    }

    public void setContactMemo(String contactMemo) {
        this.contactMemo = contactMemo;
    }
}
