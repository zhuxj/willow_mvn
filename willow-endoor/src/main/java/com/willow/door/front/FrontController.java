/**
 * 版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2012 
 * 日    期：12-12-22
 */
package com.willow.door.front;

import com.willow.door.admin.doorarticle.domain.DoorArticle;
import com.willow.door.admin.doorarticle.service.DoorArticleService;
import com.willow.door.admin.doorfile.domain.DoorFile;
import com.willow.door.admin.doorfile.service.DoorFileService;
import com.willow.door.admin.doororder.domain.DoorOrder;
import com.willow.door.admin.doororder.service.DoorOrderService;
import com.willow.door.admin.doorquality.domain.DoorQuality;
import com.willow.door.admin.doorquality.service.DoorQualityService;
import com.willow.door.admin.filecatalog.domain.FileCatalog;
import com.willow.door.admin.filecatalog.service.FileCatalogService;
import com.willow.door.admin.product.domain.Product;
import com.willow.door.admin.product.service.ProductService;
import com.willow.door.admin.productcatalog.domain.ProductCatalog;
import com.willow.door.admin.productcatalog.service.ProductCatalogService;
import com.willow.platform.cache.Cacheable;
import com.willow.platform.cache.SimpleCacheable;
import com.willow.platform.cache.WillowCacheClient;
import com.willow.platform.core.AppConst;
import com.willow.platform.core.Page;
import com.willow.platform.core.PageParam;
import com.willow.platform.core.base.web.BaseController;
import com.willow.platform.module.basic.website.domain.WebSite;
import com.willow.platform.module.basic.website.service.WebSiteService;
import com.willow.platform.utils.CodeGenerator;
import com.willow.platform.utils.CookieUtil;
import com.willow.platform.utils.SecurityImgGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 前台页面
 * </pre>
 *
 * @author 朱贤俊
 * @version 1.00
 */
@Controller
public class FrontController extends BaseController {
    @Autowired
    private WillowCacheClient willowCacheClient;
    @Autowired
    private WebSiteService webSiteService;
    @Autowired
    private DoorArticleService doorArticleService;
    @Autowired
    private ProductCatalogService productCatalogService;
    @Autowired
    private DoorOrderService doorOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileCatalogService fileCatalogService;
    @Autowired
    private DoorQualityService doorQualityService;
    @Autowired
    private DoorFileService doorFileService;

    @RequestMapping("/product/queryProductsByCatalogId")
    public Map<String, Object> queryProductsByCatalogId(Product product) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Product> products = productService.queryList(product);
        map.put("products", products);
        return map;
    }


    //下载中心
    @RequestMapping("/download")
    public ResponseEntity<String> doorfile(DoorFile doorFile, PageParam pageParam, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<FileCatalog> fileCatalogs = fileCatalogService.queryList(new FileCatalog());
        pageParam.setSortFieldName("create_time");
        pageParam.setSortType("desc");
        map.put("fileCatalogs", fileCatalogs);

        Page<DoorFile> doorFilePage = doorFileService.queryPageList(doorFile, pageParam);
        map.put("doorFilePage", doorFilePage);

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/doorfile", map));
    }

    //创建订单
    @RequestMapping("/order/createOrder")
    public Map<String, Object> createOrder(HttpServletRequest request, HttpServletResponse response, DoorOrder doorOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        int affectCount = doorOrderService.createOrder(doorOrder);
        if (affectCount > 0) {
            map.put("success", "1");
        } else {
            map.put("success", "0");
        }
        return map;
    }

    /**
     * 验证验证码
     *
     * @param request
     * @param response
     * @param verycode
     * @return
     */
    @RequestMapping("/verycode/checkVerycode")
    public Map<String, Object> checkVerycode(HttpServletRequest request, HttpServletResponse response, String tempId, String verycode) {
        Map<String, Object> map = new HashMap<String, Object>();
        String sessionVeryCode = ((Cacheable<String>) willowCacheClient.get(tempId)).getValue();
        if (sessionVeryCode.equals(verycode)) {
            map.put("isTrue", "true");
        } else {
            map.put("isTrue", "false");
        }
        return map;
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @param tempId
     */
    @RequestMapping("/verycode/getImageCode")
    public void generateImageCode(HttpServletRequest request, HttpServletResponse response, String tempId) {
        noCache(response);
        SecurityImgGenerator generator = new SecurityImgGenerator();
        String imgCheckCode = generator.image(response);
        willowCacheClient.put(tempId, new SimpleCacheable<String>(imgCheckCode));
    }

    //显示订单填写页面
    @RequestMapping("/order/{objId}")
    public ResponseEntity<String> showOrderConfirm(@PathVariable String objId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = productService.selectByObjId(objId);
        List<ProductCatalog> productCatalogs = productCatalogService.queryList(new ProductCatalog());
        map.put("productCatalogs", productCatalogs);
        map.put("product", product);
        map.put("tempId", CodeGenerator.getUUID());

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/orderConfirm", map));
    }


    /**
     * 商品详细页
     *
     * @param objId
     * @param request
     * @return
     */
    @RequestMapping("/product/{objId}")
    public ResponseEntity<String> products(@PathVariable String objId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Product product = productService.selectByObjId(objId);
        List<ProductCatalog> productCatalogs = productCatalogService.queryList(new ProductCatalog());
        map.put("productCatalogs", productCatalogs);
        map.put("product", product);

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/productDetail", map));
    }

    /**
     * 产品中心
     *
     * @param product
     * @param pageParam
     * @param request
     * @return
     */
    @RequestMapping("/product")
    public ResponseEntity<String> products(Product product, PageParam pageParam, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ProductCatalog> productCatalogs = productCatalogService.queryList(new ProductCatalog());
        map.put("productCatalogs", productCatalogs);

        Page<Product> productPage = productService.queryPageList(product, pageParam);
        map.put("productPage", productPage);
        map.put("product", product);

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/product", map));
    }


    @RequestMapping("/order")
    public ResponseEntity<String> order(Product product, PageParam pageParam, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ProductCatalog> productCatalogs = productCatalogService.queryList(new ProductCatalog());
        map.put("productCatalogs", productCatalogs);
        map.put("tempId", CodeGenerator.getUUID());

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/order", map));
    }


    /**
     * 显示公司信息
     *
     * @return
     */
    @RequestMapping("/{articleCode}")
    public ResponseEntity<String> showArticle(@PathVariable String articleCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        DoorArticle doorArticle = doorArticleService.queryDoorArticleByCode(articleCode);
        if (doorArticle == null) {
            return null;
        }
        map.put("doorArticle", doorArticle);

        DoorArticle filter = new DoorArticle();
        filter.setArticleType(DoorArticle.ARTICLETYPE_ABOUT);
        List<DoorArticle> doorArticles = doorArticleService.queryList(filter);
        map.put("doorArticles", doorArticles);

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/article", map));
    }

    /**
     * oem显示
     *
     * @param request
     * @return
     */
    @RequestMapping("/oem")
    public ResponseEntity<String> showOem(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String articleCode = "oem";
        DoorArticle doorArticle = doorArticleService.queryDoorArticleByCode(articleCode);
        if (doorArticle == null) {
            return null;
        }
        map.put("doorArticle", doorArticle);

        DoorArticle filter = new DoorArticle();
        filter.setArticleType(DoorArticle.ARTICLETYPE_OEM);
        List<DoorArticle> doorArticles = doorArticleService.queryList(filter);
        map.put("doorArticles", doorArticles);

        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/article", map));
    }

    /**
     * 质检中心
     *
     * @param request
     * @return
     */
    @RequestMapping("/quality")
    public ResponseEntity<String> showquality(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DoorQuality> doorQualities = doorQualityService.queryList(new DoorQuality());
        map.put("doorQualities", doorQualities);
        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/quality", map));
    }


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public ResponseEntity<String> index(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String template = getTemplate();
        setCommon(request, map);
        return makeResponseEntity(formatContent("/door/front/" + template + "/index", map));
    }

    private String getTemplate() {
        return "template_01";
    }

    /**
     * 设置公共信息
     *
     * @param request
     * @param map
     */
    private void setCommon(HttpServletRequest request, Map<String, Object> map) {
        map.put("webSite", getWebSite());
        map.put("request", request);
        map.put("extMap", map);
    }

    private WebSite getWebSite() {
        WebSite webSite = webSiteService.getWebSiteByCompanyId(AppConst.COMPANY_ID);
        return webSite;
    }


}
