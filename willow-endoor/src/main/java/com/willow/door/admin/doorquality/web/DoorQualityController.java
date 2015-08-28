/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-02-02
*/
package com.willow.door.admin.doorquality.web;

import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.doorquality.domain.DoorQuality;
import com.willow.door.admin.doorquality.service.DoorQualityService;
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
 * 质检中心控制层
 * </pre>
*
* @author 朱贤俊
* @version 1.0
*/
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/doorquality")
public class DoorQualityController extends BaseController {
    @Autowired
    private DoorQualityService doorQualityService;

    /**
     * 显示新增页面
     *
     * @param doorQuality
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(DoorQuality doorQuality) {
        ModelAndView view = new ModelAndView("/door/admin/doorquality/doorqualityadd");
        return view;
    }

    /**
     * 保存记录
     *
     * @param doorQuality
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(DoorQuality doorQuality) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorQualityService.save(doorQuality);
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
        int affectCount = doorQualityService.deleteByObjId(objId);
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
        doorQualityService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param doorQuality
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(DoorQuality doorQuality) {
        ModelAndView view = new ModelAndView("/door/admin/doorquality/doorqualityupdate");
        DoorQuality domain = doorQualityService.selectByObjId(doorQuality.getObjId());
        view.addObject("doorQuality", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param doorQuality
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(DoorQuality doorQuality) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorQualityService.update(doorQuality);
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
     * @param doorQuality
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(DoorQuality doorQuality) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorQualityService.updateByIdSelective(doorQuality);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param doorQuality
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(DoorQuality doorQuality) {
        ModelAndView view = new ModelAndView("/door/admin/doorquality/doorqualitylist");
        return view;
    }

    /**
     * 查询记录
     *
     * @param  doorQuality
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(DoorQuality doorQuality, PageParam pageParam) {
        Page<DoorQuality> page = doorQualityService.queryPageList(doorQuality, pageParam);
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
        ModelAndView view = new ModelAndView("/door/admin/doorquality/doorqualitydetail");
        DoorQuality doorQuality = doorQualityService.selectByObjId(objId);
        view.addObject("doorQuality", doorQuality);
        return view;
    }


}
