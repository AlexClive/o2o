package com.zhangxuerong.o2o.service;

import com.zhangxuerong.o2o.dto.ShopExecution;
import com.zhangxuerong.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 通过店铺id 获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName);

    /**
     * 注册店铺信息
     * @param shop
     * @param shopImg
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImg,String fileName);
}
