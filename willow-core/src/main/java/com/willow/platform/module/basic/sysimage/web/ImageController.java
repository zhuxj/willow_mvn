/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-27
 */
package com.willow.platform.module.basic.sysimage.web;

import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.module.basic.sysimage.domain.ImageFile;
import com.willow.platform.module.basic.sysimage.service.ImageService;
import com.willow.platform.utils.CodeGenerator;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
@Controller
@RequestMapping("image")
public class ImageController extends BaseController {
    @Autowired
    private ImageService imageService;

    private static final long MAX_SIZE = 1024 * 1024 * 2;//2M
    private static final Set<String> validFileTypeSet;

    static {
        validFileTypeSet = new HashSet<String>();
        validFileTypeSet.add("png");
        validFileTypeSet.add("jpg");
    }


    private void setCacheImage(HttpServletResponse response) {
        final int CACHE_DURATION_IN_SECOND = 60 * 60 * 24 * 2;
        final long CACHE_DURATION_IN_MS = CACHE_DURATION_IN_SECOND * 1000;
        long now = System.currentTimeMillis();
        response.addHeader("Cache-Control", "max-age=" + CACHE_DURATION_IN_SECOND);
        response.setDateHeader("Expires", now + CACHE_DURATION_IN_MS);
    }

    @RequestMapping(value = "/{id}.jpg", method = RequestMethod.GET)
    public ModelAndView getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Assert.notNull(id);
        response.setContentType("image/jpeg");
        setCacheImage(response);
        ServletOutputStream output = response.getOutputStream();

        byte[] image = imageService.getImage(id);

        if (image != null) {
            IOUtils.write(image, output);
        } else {
            IOUtils.write(imageService.getImage("blank"), output);
        }

        return null;
    }


    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void uploadImage(String fileSort, String confirm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFileMap().values().toArray()[0];
        Assert.notNull(fileSort);
        Assert.notNull(fileData);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.equals("3", fileSort) && fileData.getSize() >= MAX_SIZE) {
            jsonObject.put("result", "exceed");
        } else {
            ImageFile imageFile = new ImageFile(CodeGenerator.getUUID(), fileSort);
            imageFile.setFileSize(Integer.parseInt(fileData.getSize() + ""));
            String fullFileName = fileData.getFileItem().getName();
            String fileType = StringUtils.substring(fullFileName, fullFileName.lastIndexOf(".") + 1);
            if (!validFileTypeSet.contains(fileType.toLowerCase())) {
                jsonObject.put("result", "invalidType");
            } else {
                imageFile.setSrcFileName(StringUtils.substring(fullFileName, fullFileName.lastIndexOf("\\") + 1, fullFileName.lastIndexOf(".")));
                imageFile.setFileType(fileType);
                imageFile.setFileData(fileData.getBytes());
                boolean result = imageService.saveImage(imageFile);
                jsonObject.put("result", "success");
                jsonObject.put("imageId", imageFile.getImageId());
                jsonObject.put("fileType", fileType);
                jsonObject.put("fileSize", imageFile.getFileSize());
            }
        }
        this.flushResponse(response, "<script>window.parent.$.fileUploadCallBack('" + jsonObject.toString() + "')</script>");

    }


}
