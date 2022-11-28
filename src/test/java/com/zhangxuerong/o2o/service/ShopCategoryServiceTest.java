package com.zhangxuerong.o2o.service;

import com.zhangxuerong.o2o.BaseTest;
import com.zhangxuerong.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Test
    public void testShopCategoryService(){
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
        assertEquals("咖啡",shopCategoryList.get(0).getShopCategoryName());
    }
}
