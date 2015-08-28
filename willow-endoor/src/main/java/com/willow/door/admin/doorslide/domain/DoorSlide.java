/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-12
*/
package com.willow.door.admin.doorslide.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   幻灯片领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class DoorSlide extends BaseObject{
    //中文图片ID
    private String fileId;
    //英文图片ID
    private String fileIdEn;

    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileIdEn() {
        return fileIdEn;
    }
    public void setFileIdEn(String fileIdEn) {
        this.fileIdEn = fileIdEn;
    }
}
