/**
 * 版权声明：贤俊工作室 版权所有 违者必究
 * 日    期：2013-01-09
 */
package com.willow.door.admin.doororder.web;

import com.willow.door.admin.product.domain.Product;
import com.willow.door.admin.product.service.ProductService;
import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import com.willow.door.admin.doororder.domain.DoorOrder;
import com.willow.door.admin.doororder.service.DoorOrderService;
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
 * 订单信息控制层
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.0
 */
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/doororder")
public class DoorOrderController extends BaseController {
    @Autowired
    private ProductService productService;
    @Autowired
    private DoorOrderService doorOrderService;

    /**
     * 显示新增页面
     *
     * @param doorOrder
     * @return
     */
    @RequestMapping("/addPage")
    public ModelAndView addPage(DoorOrder doorOrder) {
        ModelAndView view = new ModelAndView("/door/admin/doororder/add");
        return view;
    }

    /**
     * 保存记录
     *
     * @param doorOrder
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(DoorOrder doorOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorOrderService.save(doorOrder);
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
        int affectCount = doorOrderService.deleteByObjId(objId);
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
        doorOrderService.deleteByObjIds(objIdArr);
        map.put("success", "1");
        return map;
    }


    /**
     * 显示修改页面
     *
     * @param doorOrder
     * @return
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(DoorOrder doorOrder) {
        ModelAndView view = new ModelAndView("/door/admin/doororder/update");
        DoorOrder domain = doorOrderService.selectByObjId(doorOrder.getObjId());
        Product product = productService.selectByObjId(domain.getProductId());
        view.addObject("product", product);
        view.addObject("doorOrder", domain);
        return view;
    }


    /**
     * 更新记录
     *
     * @param doorOrder
     * @return
     */
    @RequestMapping("/update")
    public Map<String, Object> update(DoorOrder doorOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorOrderService.update(doorOrder);
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
     * @param doorOrder
     * @return
     */
    @RequestMapping("/updateByIdSelective")
    public Map<String, Object> updateByIdSelective(DoorOrder doorOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorOrderService.updateByIdSelective(doorOrder);
        return map;
    }


    /**
     * 显示查询页面
     *
     * @param doorOrder
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(DoorOrder doorOrder) {
        ModelAndView view = new ModelAndView("/door/admin/doororder/list");
        return view;
    }

    /**
     * 查询记录
     *
     * @param doorOrder
     * @param pageParam
     * @return
     */
    @RequestMapping("/query")
    public Map<String, Object> query(DoorOrder doorOrder, PageParam pageParam) {
        Page<DoorOrder> page = doorOrderService.queryPageList(doorOrder, pageParam);
        List<DoorOrder> result = page.getResult();
        for (DoorOrder order : result) {
            Product product = productService.selectByObjId(order.getProductId());
            if (product != null) {
                order.setProductName(product.getProductName());
                order.setProduct(product);
            }
        }

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
        ModelAndView view = new ModelAndView("/door/admin/doororder/detail");
        DoorOrder doorOrder = doorOrderService.selectByObjId(objId);
        Product product = productService.selectByObjId(doorOrder.getProductId());
        view.addObject("product", product);
        view.addObject("doorOrder", doorOrder);
        return view;
    }


}
