package com.zhangxuerong.o2o.dao;

import com.zhangxuerong.o2o.BaseTest;
import com.zhangxuerong.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCateGoryList = shopCategoryDao.queryShopCategory(new ShopCategory());

        assertEquals(1,shopCateGoryList.size());
    }

}
