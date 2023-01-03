package com.zhangxuerong.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangxuerong.o2o.dto.ShopExecution;
import com.zhangxuerong.o2o.entity.Area;
import com.zhangxuerong.o2o.entity.PersonInfo;
import com.zhangxuerong.o2o.entity.Shop;
import com.zhangxuerong.o2o.entity.ShopCategory;
import com.zhangxuerong.o2o.enums.ShopSateEnum;
import com.zhangxuerong.o2o.service.AreaService;
import com.zhangxuerong.o2o.service.ShopCategoryService;
import com.zhangxuerong.o2o.service.ShopService;
import com.zhangxuerong.o2o.util.CodeUtil;
import com.zhangxuerong.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopById(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId > -1){
           try {
               Shop shop = shopService.getByShopId(shopId);
               List<Area> areaList = areaService.getAreaList();
               modelMap.put("shop",shop);
               modelMap.put("areaList",areaList);
               modelMap.put("success",true);
           }catch (Exception e){
               modelMap.put("success",false);
               modelMap.put("errMsg",e.getMessage());
           }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty ShopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String ,Object> getShopInitInfo(){
        Map<String,Object> modeMap = new HashMap<String,Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modeMap.put("shopCategoryList",shopCategoryList);
            modeMap.put("areaList",areaList);
            modeMap.put("success",true);
        }catch (Exception e){
            modeMap.put("success", false);
            modeMap.put("errMsg",e.getMessage());
        }
        return modeMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入错误的验证码");
            return modelMap;
        }
        // 1.接收参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMag", e.toString());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMag", "上传图片不能为空");
            return modelMap;
        }

        // 2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
                if(se.getState() == ShopSateEnum.CHECK.getState()){
                    modelMap.put("success", true);
                    @SuppressWarnings("unchecked")
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0){
                       shopList = new ArrayList<Shop>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList",shopList);
                }else {
                    modelMap.put("success", false);
                    modelMap.put("errMag", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMag", e.getMessage());
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMag", "请输入店铺信息");
        }
        return  modelMap;
    }


    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyshop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入错误的验证码");
            return modelMap;
        }
        // 1.接收参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMag", e.toString());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        // 2.修改店铺
        if (shop != null && shop.getShopId() != null) {
            ShopExecution se;
            try {
                if(shopImg == null){
                    se = shopService.modifyShop(shop, null,null);
                }else {
                    se = shopService.modifyShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
                }
                if(se.getState() == ShopSateEnum.SUCCESS.getState()){
                    modelMap.put("success", true);
                }else {
                    modelMap.put("success", false);
                    modelMap.put("errMag", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMag", e.getMessage());
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMag", "请输入店铺Id");
        }
        return  modelMap;
    }

}
