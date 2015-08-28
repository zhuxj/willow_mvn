/**
* 版权声明：贤俊工作室 版权所有 违者必究
* 日    期：2012-12-21
*/
package com.willow.door.admin.doorarticle.web;

import com.willow.door.admin.doorarticle.domain.DoorArticle;
import com.willow.door.admin.doorarticle.service.DoorArticleService;
import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.core.context.WebSiteContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
*
<pre>
 * 产品信息控制层
 * </pre>
*
* @author 朱贤俊
* @version 1.0
*/
@Controller
@RequestMapping(WebSiteContext.MANAGER + "admin/doorabout")
public class DoorAboutController extends BaseController {
@Autowired
private DoorArticleService doorArticleService;


    /**
     * 显示查询页面
     *
     * @param doorArticle
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(DoorArticle doorArticle) {
        ModelAndView view = new ModelAndView("/door/admin/doorarticle/list");
        doorArticle.setArticleType(DoorArticle.ARTICLETYPE_ABOUT);
        view.addObject("doorArticle",doorArticle);
        return view;
    }


}
