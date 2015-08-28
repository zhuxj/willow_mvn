/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-12
*/
package com.willow.platform.module.basic.sysfile.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   附件领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class SysFile extends BaseObject{
    //文件所有者
    private String ownerId;
    //文件ID
    private String fileId;
    //文件类型
    private String fileType;
    //文件名称
    private String fileName;
    //文件大小
    private Integer fileSize;

    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Integer getFileSize() {
        return fileSize;
    }
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}
