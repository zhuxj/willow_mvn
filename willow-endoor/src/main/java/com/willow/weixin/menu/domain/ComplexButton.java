/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2014 
 * 日    期：14-4-9
 */
package com.willow.weixin.menu.domain;

/**
 * <pre>
 *   复杂按钮（父按钮）
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
