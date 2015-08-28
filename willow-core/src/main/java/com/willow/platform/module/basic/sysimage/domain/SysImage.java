/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2012-12-27
*/
package com.willow.platform.module.basic.sysimage.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   图片附件领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class SysImage extends BaseObject{
    //图片ID
    private String imageId;
    //图片类型
    private String imageType;
    //图片名称
    private String imageName;
    //图片大小
    private Integer imageSize;

    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public Integer getImageSize() {
        return imageSize;
    }
    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }
}
