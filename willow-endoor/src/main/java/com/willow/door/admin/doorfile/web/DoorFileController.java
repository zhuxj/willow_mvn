/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-01-11
 */
package com.willow.door.admin.doorfile.web;

import com.willow.door.admin.filecatalog.domain.FileCatalog;
import com.willow.door.admin.filecatalog.service.FileCatalogService;
import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.doorfile.domain.DoorFile;
import com.willow.door.admin.doorfile.service.DoorFileService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 文件信息控制层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/doorfile")
public class DoorFileController extends BaseController {
    @Autowired
    private FileCatalogService fileCatalogService;
    @Autowired
    private DoorFileService doorFileService;

    /**
     * 显示新增页面
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(DoorFile doorFile) {
        ModelAndView view = new ModelAndView("/door/admin/doorfile/add");
        List<FileCatalog> fileCatalogs = fileCatalogService.queryList(new FileCatalog());
        view.addObject("fileCatalogs", fileCatalogs);
        return view;
    }

    /**
     * 保存记录
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(DoorFile doorFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorFileService.save(doorFile);
        if (affectCount > 0) {
            map.put("success", "1");
        } else {
            map.put("success", "0");
        }
        return map;
    }


    /**
     * 删除记录
     *
     * @param objId
     * @return
     */
    @RequestMapping("/del")
    public Map<String, Object> del(String objId) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorFileService.deleteByObjId(objId);
        return map;
    }

    /**
     * 批量删除
     *
     * @param objIds
     * @return
     */
    @RequestMapping("/batchDel")
    public Map<String, Object> batchDel(String objIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] objIdArr = StringUtils.split(objIds, ",");
        doorFileService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(DoorFile doorFile) {
        ModelAndView view = new ModelAndView("/door/admin/doorfile/update");
        DoorFile domain = doorFileService.selectByObjId(doorFile.getObjId());
        List<FileCatalog> fileCatalogs = fileCatalogService.queryList(new FileCatalog());
        view.addObject("fileCatalogs", fileCatalogs);
        view.addObject("doorFile", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(DoorFile doorFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorFileService.update(doorFile);
        if (affectCount > 0) {
            map.put("success", "1");
        } else {
            map.put("success", "0");
        }
        return map;
    }

    /**
     * 更新记录
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(DoorFile doorFile) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorFileService.updateByIdSelective(doorFile);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param doorFile
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(DoorFile doorFile) {
        ModelAndView view = new ModelAndView("/door/admin/doorfile/list");
        List<FileCatalog> fileCatalogs = fileCatalogService.queryList(new FileCatalog());
        view.addObject("fileCatalogs", fileCatalogs);
        return view;
    }

    /**
     * 查询记录
     *
     * @param doorFile
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(DoorFile doorFile, PageParam pageParam) {
        Page<DoorFile> page = doorFileService.queryPageList(doorFile, pageParam);
        return page.toDataMap(null);
    }

    /**
     * 显示详情页面
     *
     * @param objId
     * @return
     */
    @RequestMapping("/detailPage")
    public ModelAndView detailPage(String objId) {
        ModelAndView view = new ModelAndView("/door/admin/doorfile/detail");
        DoorFile doorFile = doorFileService.selectByObjId(objId);
        FileCatalog fileCatalog=fileCatalogService.selectByObjId(doorFile.getCatalogId());
        view.addObject("fileCatalog", fileCatalog);
        view.addObject("doorFile", doorFile);
        return view;
    }


}
