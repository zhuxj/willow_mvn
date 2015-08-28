/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2013-03-10
*/
package com.willow.door.admin.systest.web;

import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.systest.domain.SysTest;
import com.willow.door.admin.systest.service.SysTestService;
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
 * 测试控制层
 * </pre>
*
* @author 朱贤俊
* @version 1.0
*/
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/systest")
public class SysTestController extends BaseController {
    @Autowired
    private SysTestService sysTestService;

    /**
     * 显示新增页面
     *
     * @param sysTest
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(SysTest sysTest) {
        ModelAndView view = new ModelAndView("/door/admin/systest/systestadd");
        return view;
    }

    /**
     * 保存记录
     *
     * @param sysTest
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(SysTest sysTest) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = sysTestService.save(sysTest);
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
        int affectCount = sysTestService.deleteByObjId(objId);
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
        sysTestService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param sysTest
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(SysTest sysTest) {
        ModelAndView view = new ModelAndView("/door/admin/systest/systestupdate");
        SysTest domain = sysTestService.selectByObjId(sysTest.getObjId());
        view.addObject("sysTest", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param sysTest
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(SysTest sysTest) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = sysTestService.update(sysTest);
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
     * @param sysTest
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(SysTest sysTest) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = sysTestService.updateByIdSelective(sysTest);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param sysTest
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(SysTest sysTest) {
        ModelAndView view = new ModelAndView("/door/admin/systest/systestlist");
        return view;
    }

    /**
     * 查询记录
     *
     * @param  sysTest
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(SysTest sysTest, PageParam pageParam) {
        Page<SysTest> page = sysTestService.queryPageList(sysTest, pageParam);
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
        ModelAndView view = new ModelAndView("/door/admin/systest/systestdetail");
        SysTest sysTest = sysTestService.selectByObjId(objId);
        view.addObject("sysTest", sysTest);
        return view;
    }


}
