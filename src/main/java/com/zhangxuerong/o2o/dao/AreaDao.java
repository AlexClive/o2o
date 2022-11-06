package com.zhangxuerong.o2o.dao;

import com.zhangxuerong.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return AreaList
     */
    List<Area> queryArea();
}
