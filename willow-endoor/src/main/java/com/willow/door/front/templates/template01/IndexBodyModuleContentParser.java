/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-23
 */
package com.willow.door.front.templates.template01;

import com.willow.door.admin.doorarticle.domain.DoorArticle;
import com.willow.door.admin.doorarticle.service.DoorArticleService;
import com.willow.door.admin.doorfile.domain.DoorFile;
import com.willow.door.admin.doorfile.service.DoorFileService;
import com.willow.door.admin.doorquality.domain.DoorQuality;
import com.willow.door.admin.doorquality.service.DoorQualityService;
import com.willow.door.admin.product.domain.Product;
import com.willow.door.admin.product.service.ProductService;
import com.willow.door.admin.productcatalog.domain.ProductCatalog;
import com.willow.door.admin.productcatalog.service.ProductCatalogService;
import com.willow.door.front.templates.Channel;
import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.freemarker.ClientLanguage;
import com.willow.platform.core.base.freemarker.ModuleContentParserAdapter;
import com.willow.platform.utils.CookieUtil;
import com.willow.platform.utils.LocalStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
@Component("indexBody")
public class IndexBodyModuleContentParser extends ModuleContentParserAdapter {
    @Autowired
    private DoorQualityService doorQualityService;
    @Autowired
    private DoorFileService doorFileService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCatalogService productCatalogService;

    @Autowired
    private DoorArticleService doorArticleService;

    @Override
    public Map<String, Object> loadModuleData(String moduleCod, Map<String, Object> extParamMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        DoorArticle about = doorArticleService.queryDoorArticleByCode(DoorArticleService.ABOUT);
        HttpServletRequest request = (HttpServletRequest) extParamMap.get("request");
        String clientLanguage = CookieUtil.getCookieValue(request, ClientLanguage.CLIENT_LANGUAGE);

        //关于我们
        String content = ClientLanguage.EN_US.equals(clientLanguage) ? LocalStringUtils.getPlanString(about.getArticleContentEn(), 155, "...")
                : LocalStringUtils.getPlanString(about.getArticleContent(), 95, "...");
        map.put("content", content);
        map.put("about", about);

        //分类商品
        Page<ProductCatalog> productCatalogPage = productCatalogService.queryPageList(new ProductCatalog(), new PageParam());
        List<ProductCatalog> productCatalogList = productCatalogPage.getResult();
        for (ProductCatalog productCatalog : productCatalogList) {
            Product filter = new Product();
            filter.setCatalogId(productCatalog.getObjId());
            PageParam pageParam = new PageParam();
            pageParam.setPageSize(12);
            Page<Product> productPage = productService.queryPageList(filter, pageParam);
            List<Product> result = productPage.getResult();
            productCatalog.setProductList(result);
        }

        map.put("productCatalogList", productCatalogList);


        //下载中心
        PageParam pageParam = new PageParam();
        pageParam.setPageSize(9);
        pageParam.setSortFieldName("create_time");
        pageParam.setSortType("desc");
        Page<DoorFile> doorFilePage = doorFileService.queryPageList(new DoorFile(), pageParam);
        map.put("doorFileList", doorFilePage.getResult());

        List<DoorQuality> doorQualities = doorQualityService.queryList(new DoorQuality());
        map.put("doorQualities", doorQualities);

        return map;
    }

    @Override
    public String getModuleCode() {
        return "indexBody";
    }

    @Override
    public String getModuleFtlBaseName() {
        return "/door/front/template_01/indexBody";
    }
}
