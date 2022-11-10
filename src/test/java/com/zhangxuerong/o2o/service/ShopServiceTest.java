package com.zhangxuerong.o2o.service;

import com.zhangxuerong.o2o.BaseTest;
import com.zhangxuerong.o2o.dto.ShopExecution;
import com.zhangxuerong.o2o.entity.Area;
import com.zhangxuerong.o2o.entity.PersonInfo;
import com.zhangxuerong.o2o.entity.Shop;
import com.zhangxuerong.o2o.entity.ShopCategory;
import com.zhangxuerong.o2o.enums.ShopSateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
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
        shop.setShopName("测试店铺2");
        shop.setShopDesc("测试1");
        shop.setShopAddr("测试1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopSateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("F:/lear/lanhua.png");
        ShopExecution se = shopService.addShop(shop,shopImg);
        assertEquals(ShopSateEnum.CHECK.getState(),se.getState());
    }
}
