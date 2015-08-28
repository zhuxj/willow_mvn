/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.doorfile.domain;

import com.willow.platform.core.base.domian.BaseObject;

/**
 * <pre>
 *   文件信息领域对象
 * </pre>
 * @author 朱贤俊
 * @version 1.0
 */
public class DoorFile extends BaseObject{
    //中文文件名称
    private String fileName;
    //中文文件ID
    private String fileId;
    //中文文件类型
    private String fileType;
    //中文文件大小
    private Integer fileSize;
    //英文文件名称
    private String fileNameEn;
    //英文文件ID
    private String fileIdEn;
    //英文文件类型
    private String fileTypeEn;
    //英文文件大小
    private Integer fileSizeEn;
    //产品分类
    private String catalogId;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
    public Integer getFileSize() {
        return fileSize;
    }
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileNameEn() {
        return fileNameEn;
    }
    public void setFileNameEn(String fileNameEn) {
        this.fileNameEn = fileNameEn;
    }
    public String getFileIdEn() {
        return fileIdEn;
    }
    public void setFileIdEn(String fileIdEn) {
        this.fileIdEn = fileIdEn;
    }
    public String getFileTypeEn() {
        return fileTypeEn;
    }
    public void setFileTypeEn(String fileTypeEn) {
        this.fileTypeEn = fileTypeEn;
    }
    public Integer getFileSizeEn() {
        return fileSizeEn;
    }
    public void setFileSizeEn(Integer fileSizeEn) {
        this.fileSizeEn = fileSizeEn;
    }
    public String getCatalogId() {
        return catalogId;
    }
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
