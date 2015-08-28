package com.willow.platform.module.basic.sysimage.domain;

public class ImageFile {
    public static String IMAGE_CONFIRM_STATUS_CONFIRM = "1";
    public static String IMAGE_CONFIRM_STATUS_UNCONFIRM = "0";
    private String imageId;
    private String srcFileName;
    private Integer fileSize;
    private String uploadType;
    private String fileType;
    private String confirm;
    private String needLog="1";
    private byte[] fileData;
    public ImageFile(String imageId, String fileSort){
        this.imageId = imageId;
        this.uploadType = fileSort;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getSrcFileName() {
        return srcFileName;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public String getNeedLog() {
        return needLog;
    }

    public void setNeedLog(String needLog) {
        this.needLog = needLog;
    }
}
