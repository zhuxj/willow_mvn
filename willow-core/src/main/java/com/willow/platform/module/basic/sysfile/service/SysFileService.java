/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-01-11
 */
package com.willow.platform.module.basic.sysfile.service;

import com.willow.platform.core.base.dao.BaseDao;
import com.willow.platform.core.base.service.BaseService;
import com.willow.platform.module.basic.sysfile.dao.SysFileDao;
import com.willow.platform.module.basic.sysfile.domain.SysFile;
import com.willow.platform.module.basic.sysimage.domain.SysImage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 * 附件业务类
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Service
public class SysFileService extends BaseService<SysFile> {
    private static final Logger LOG = LoggerFactory.getLogger(SysFileService.class);
    public static final String SRC_FILE = "file";
    private String fileDir = "/attachment/";
    @Autowired
    private SysFileDao sysFileDao;

    public SysFile querySysFileByFileId(String fileId) {
        SysFile filter = new SysFile();
        filter.setFileId(fileId);
        return sysFileDao.selectByCondition(filter);
    }

    public byte[] getFile(String fileId) {
        //检查文件
        SysFile sysFile = querySysFileByFileId(fileId);
        String fileType = sysFile.getFileType();
        String srcFileDir = FilenameUtils.concat(fileDir, SRC_FILE);
        String srcFilePath = FilenameUtils.concat(srcFileDir, fileId + "." + fileType);

        File file = new File(srcFilePath);
        byte[] fileByte = new byte[0];
        try {
            fileByte = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByte;
    }


    /**
     * 保存附件
     *
     * @param sysFile
     * @return
     */
    public boolean saveSysFile(SysFile sysFile, byte[] fileData) {
        boolean result = saveFile(sysFile.getFileId(), sysFile.getFileType(), fileData);
        if (result) {
            SysFile sysFileDb = new SysFile();
            sysFileDb.setFileId(sysFile.getFileId());
            sysFileDb.setFileName(sysFile.getFileName());
            sysFileDb.setFileType(sysFile.getFileType());
            sysFileDb.setFileSize(sysFile.getFileSize());
            save(sysFileDb);
        }
        return true;
    }


    /**
     * 保存正式的文件
     *
     * @param id
     * @param fileType
     * @param file
     * @return
     */
    public boolean saveFile(String id, String fileType, byte[] file) {
        String srcFileDir = FilenameUtils.concat(fileDir, SRC_FILE);
        String srcFilePath = FilenameUtils.concat(srcFileDir, id + "." + fileType);

        File srcFile = new File(srcFilePath);
        try {
            FileUtils.writeByteArrayToFile(srcFile, file);
        } catch (IOException e) {
            throw new RuntimeException("写文件" + srcFilePath + "失败!");
        }
        return true;
    }


    @Override
    public BaseDao<SysFile> getDao() {
        return sysFileDao;
    }

    public void setSysFileDao(SysFileDao sysFileDao) {
        this.sysFileDao = sysFileDao;
    }
}
