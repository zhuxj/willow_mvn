/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-27
 */
package com.willow.platform.module.basic.sysimage.service;

import com.willow.platform.module.basic.sysimage.domain.ImageFile;
import com.willow.platform.module.basic.sysimage.domain.SysImage;
import com.willow.platform.utils.HttpClientBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
@Component
public class ImageService {
    @Autowired
    private SysImageService sysImageService;

    private static final Logger LOG = LoggerFactory.getLogger(ImageService.class);

    public static final String SRC_IMAGE = "image";

    private String imageDir = "/attachment/";

    /**
     * 查询图片
     * @param imageId
     * @return
     */
    public byte[] getImage(String imageId) {
        //检查文件
        SysImage sysImage = sysImageService.querySysImageByImageId(imageId);
        String imageType = sysImage.getImageType();
        String srcImageDir = FilenameUtils.concat(imageDir, SRC_IMAGE);
        String srcImagePath = FilenameUtils.concat(srcImageDir, imageId + "." + imageType);
        File imageFile = new File(srcImagePath);
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    /**
     * 保存图片信息到数据库
     *
     * @param sysImage
     * @return
     */
    public int saveDbImage(SysImage sysImage) {
        return sysImageService.save(sysImage);
    }

    /**
     * 保存图片文件
     *
     * @param imageFile
     * @return
     */
    public boolean saveImage(ImageFile imageFile) {
        boolean result = saveImageFile(imageFile.getImageId(), imageFile.getFileType(), imageFile.getFileData());
        if (result) {
            SysImage sysImage = new SysImage();
            sysImage.setImageId(imageFile.getImageId());
            sysImage.setImageName(imageFile.getSrcFileName());
            sysImage.setImageSize(imageFile.getFileSize());
            sysImage.setImageType(imageFile.getFileType());
            saveDbImage(sysImage);
        }
        return true;
    }


    /**
     * 保存文件
     *
     * @param id
     * @param imageType
     * @param image
     * @return
     */
    public boolean saveImageFile(String id, String imageType, byte[] image) {
        Assert.notNull(id);
        Assert.notNull(image);

        String srcImageDir = FilenameUtils.concat(imageDir, SRC_IMAGE);
        String srcImagePath = FilenameUtils.concat(srcImageDir, id + "." + imageType);

        File srcImageFile = new File(srcImagePath);
        try {
            FileUtils.writeByteArrayToFile(srcImageFile, image);
        } catch (IOException e) {
            throw new RuntimeException("写图片文件" + srcImagePath + "失败!");
        }
        return true;
    }

}
