/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-01-12
*/
package com.willow.door.admin.doorslide.web;

import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.doorslide.domain.DoorSlide;
import com.willow.door.admin.doorslide.service.DoorSlideService;
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
 * 幻灯片控制层
 * </pre>
*
* @author 朱贤俊
* @version 1.0
*/
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/doorslide")
public class DoorSlideController extends BaseController {
    @Autowired
    private DoorSlideService doorSlideService;

    /**
     * 显示新增页面
     *
     * @param doorSlide
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(DoorSlide doorSlide) {
        ModelAndView view = new ModelAndView("/door/admin/doorslide/add");
        return view;
    }

    /**
     * 保存记录
     *
     * @param doorSlide
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(DoorSlide doorSlide) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorSlideService.save(doorSlide);
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
        int affectCount = doorSlideService.deleteByObjId(objId);
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
        doorSlideService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param doorSlide
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(DoorSlide doorSlide) {
        ModelAndView view = new ModelAndView("/door/admin/doorslide/update");
        DoorSlide domain = doorSlideService.selectByObjId(doorSlide.getObjId());
        view.addObject("doorSlide", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param doorSlide
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(DoorSlide doorSlide) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorSlideService.update(doorSlide);
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
     * @param doorSlide
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(DoorSlide doorSlide) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorSlideService.updateByIdSelective(doorSlide);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param doorSlide
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(DoorSlide doorSlide) {
        ModelAndView view = new ModelAndView("/door/admin/doorslide/list");
        return view;
    }

    /**
     * 查询记录
     *
     * @param  doorSlide
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(DoorSlide doorSlide, PageParam pageParam) {
        Page<DoorSlide> page = doorSlideService.queryPageList(doorSlide, pageParam);
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
        ModelAndView view = new ModelAndView("/door/admin/doorslide/detail");
        DoorSlide doorSlide = doorSlideService.selectByObjId(objId);
        view.addObject("doorSlide", doorSlide);
        return view;
    }


}
