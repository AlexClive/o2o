package com.zhangxuerong.o2o.service;

import com.zhangxuerong.o2o.dto.ShopExecution;
import com.zhangxuerong.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
