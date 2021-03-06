/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-23
 */
package com.willow.door.front.templates.template01;

import com.willow.door.admin.doorslide.domain.DoorSlide;
import com.willow.door.admin.doorslide.service.DoorSlideService;
import com.willow.platform.core.base.freemarker.ModuleContentParserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
@Component("slide")
public class SlideModuleContentParser extends ModuleContentParserAdapter {
    @Autowired
    private DoorSlideService doorSlideService;

    @Override
    public Map<String, Object> loadModuleData(String moduleCod, Map<String, Object> extParamMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DoorSlide> doorSlides = doorSlideService.queryList(new DoorSlide());
        map.put("doorSlides", doorSlides);
        return map;
    }

    @Override
    public String getModuleCode() {
        return "top";
    }

    @Override
    public String getModuleFtlBaseName() {
        return "/door/front/template_01/module/slide";
    }
}
