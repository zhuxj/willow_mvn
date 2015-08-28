/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-02-02
*/
package com.willow.door.admin.doorquality.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   质检中心领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class DoorQuality extends BaseObject{
    //中文名称
    private String qualityName;
    //中文图片ID
    private String fileId;
    //英文名称
    private String qualityNameEn;
    //英文图片ID
    private String fileIdEn;

    public String getQualityName() {
        return qualityName;
    }
    public void setQualityName(String qualityName) {
        this.qualityName = qualityName;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getQualityNameEn() {
        return qualityNameEn;
    }
    public void setQualityNameEn(String qualityNameEn) {
        this.qualityNameEn = qualityNameEn;
    }
    public String getFileIdEn() {
        return fileIdEn;
    }
    public void setFileIdEn(String fileIdEn) {
        this.fileIdEn = fileIdEn;
    }
}
