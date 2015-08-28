/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-27
 */
package com.willow.platform.module.basic.sysfile.web;

import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.module.basic.sysfile.domain.SysFile;
import com.willow.platform.module.basic.sysfile.service.SysFileService;
import com.willow.platform.module.basic.sysimage.domain.ImageFile;
import com.willow.platform.module.basic.sysimage.service.ImageService;
import com.willow.platform.utils.CodeGenerator;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/file")
public class SysFileController extends BaseController {
    @Autowired
    private SysFileService sysFileService;

    private static final long MAX_SIZE = 1024 * 1024 * 200;//200M
    private static final Set<String> validFileTypeSet;

    static {
        validFileTypeSet = new HashSet<String>();
        validFileTypeSet.add("png");
        validFileTypeSet.add("jpg");
        validFileTypeSet.add("pdf");
        validFileTypeSet.add("zip");
        validFileTypeSet.add("rar");
        validFileTypeSet.add("doc");
        validFileTypeSet.add("docx");
    }


    private void setCacheFile(HttpServletResponse response) {
        final int CACHE_DURATION_IN_SECOND = 60 * 60 * 24 * 2;
        final long CACHE_DURATION_IN_MS = CACHE_DURATION_IN_SECOND * 1000;
        long now = System.currentTimeMillis();
        response.addHeader("Cache-Control", "max-age=" + CACHE_DURATION_IN_SECOND);
        response.setDateHeader("Expires", now + CACHE_DURATION_IN_MS);
    }

    /**
     * 下载文件
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Assert.notNull(id);
        SysFile sysFile = sysFileService.querySysFileByFileId(id);
        response.reset(); // 非常重要
        response.setContentType("application/" + sysFile.getFileType() + ";charset=utf-8");
        response.setHeader("Content-Length", String.valueOf(sysFile.getFileSize()));
        String fileName = new String((sysFile.getFileName() + "." + sysFile.getFileType()).getBytes("UTF-8"), "ISO-8859-1");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream output = response.getOutputStream();
        byte[] file = sysFileService.getFile(id);
        FileCopyUtils.copy(file, output);
    }


    /**
     * 上传文件
     *
     * @param fileSort
     * @param confirm
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void uploadImage(String fileSort, String confirm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFileMap().values().toArray()[0];
        Assert.notNull(fileSort);
        Assert.notNull(fileData);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.equals("3", fileSort) && fileData.getSize() >= MAX_SIZE) {
            jsonObject.put("result", "exceed");
            jsonObject.put("maxSize", MAX_SIZE / (1024 * 1024) + "M");
        } else {
            SysFile sysFile = new SysFile();
            sysFile.setFileId(CodeGenerator.getUUID());
            sysFile.setFileSize(Integer.parseInt(fileData.getSize() + ""));
            String fullFileName = fileData.getFileItem().getName();
            String fileType = StringUtils.substring(fullFileName, fullFileName.lastIndexOf(".") + 1);
            if (!validFileTypeSet.contains(fileType.toLowerCase())) {
                jsonObject.put("result", "invalidType");
            } else {
                sysFile.setFileName(StringUtils.substring(fullFileName, fullFileName.lastIndexOf("\\") + 1, fullFileName.lastIndexOf(".")));
                sysFile.setFileType(fileType);
                boolean result = sysFileService.saveSysFile(sysFile, fileData.getBytes());
                jsonObject.put("result", "success");
                jsonObject.put("fileId", sysFile.getFileId());
                jsonObject.put("fileType", fileType);
                jsonObject.put("fileSize", sysFile.getFileSize());
                jsonObject.put("fileName", sysFile.getFileName());
            }
        }
        this.flushResponse(response, "<script>window.parent.$.fileUploadCallBack('" + jsonObject.toString() + "')</script>");

    }


}
