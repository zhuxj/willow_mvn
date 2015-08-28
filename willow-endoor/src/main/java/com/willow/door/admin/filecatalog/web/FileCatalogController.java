/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-11
*/
package com.willow.door.admin.filecatalog.web;

import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.filecatalog.domain.FileCatalog;
import com.willow.door.admin.filecatalog.service.FileCatalogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
<pre>
 * 订单信息控制层
 * </pre>
*
* @author 朱贤俊
* @version 1.0
*/
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/filecatalog")
public class FileCatalogController extends BaseController {
@Autowired
private FileCatalogService fileCatalogService;

    /**
     * 显示新增页面
     *
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(FileCatalog fileCatalog) {
        ModelAndView view = new ModelAndView("/door/admin/filecatalog/add");
        return view;
    }

    /**
     * 保存记录
     *
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(FileCatalog fileCatalog) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = fileCatalogService.save(fileCatalog);
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
        int affectCount = fileCatalogService.deleteByObjId(objId);
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
        fileCatalogService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(FileCatalog fileCatalog) {
        ModelAndView view = new ModelAndView("/door/admin/filecatalog/update");
        FileCatalog domain = fileCatalogService.selectByObjId(fileCatalog.getObjId());
        view.addObject("fileCatalog", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(FileCatalog fileCatalog) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = fileCatalogService.update(fileCatalog);
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
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(FileCatalog fileCatalog) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = fileCatalogService.updateByIdSelective(fileCatalog);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param fileCatalog
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(FileCatalog fileCatalog) {
        ModelAndView view = new ModelAndView("/door/admin/filecatalog/list");
        return view;
    }

    /**
     * 查询记录
     *
     * @param  fileCatalog
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(FileCatalog fileCatalog, PageParam pageParam) {
        Page<FileCatalog> page = fileCatalogService.queryPageList(fileCatalog, pageParam);
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
        ModelAndView view = new ModelAndView("/door/admin/filecatalog/detail");
        FileCatalog fileCatalog = fileCatalogService.selectByObjId(objId);
        view.addObject("fileCatalog", fileCatalog);
        return view;
    }


}
