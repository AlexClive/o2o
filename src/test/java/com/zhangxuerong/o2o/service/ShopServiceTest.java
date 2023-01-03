package com.zhangxuerong.o2o.service;

import com.zhangxuerong.o2o.BaseTest;
import com.zhangxuerong.o2o.dto.ShopExecution;
import com.zhangxuerong.o2o.entity.Area;
import com.zhangxuerong.o2o.entity.PersonInfo;
import com.zhangxuerong.o2o.entity.Shop;
import com.zhangxuerong.o2o.entity.ShopCategory;
import com.zhangxuerong.o2o.enums.ShopSateEnum;
import com.zhangxuerong.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void TestModifyShop() throws ShopOperationException,FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(7L);
        shop.setShopName("修改后的店铺名称+1");
        File shopImg = new File("/Users/zhangxuerong/Downloads/bailu.png");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop,is,shopImg.getName());
        System.out.println(shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺3");
        shop.setShopDesc("测试3");
        shop.setShopAddr("测试3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopSateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/zhangxuerong/Downloads/bailu.png");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,is,shopImg.getName());
        assertEquals(ShopSateEnum.CHECK.getState(),se.getState());
    }

}