package com.zhangxuerong.o2o.dto;

import com.zhangxuerong.o2o.entity.Shop;
import com.zhangxuerong.o2o.enums.ShopSateEnum;

import java.util.List;

public class ShopExecution {
    // 结果状态
    private int state;

    //状态标识
    private String stateInfo;

    // 店铺数量
    private int count;

    //操作的shop
    private Shop shop;

    // shop列表
    private List<Shop> shopList;

    public ShopExecution(){

    }
    // 店铺操作失败的构造器
    public ShopExecution(ShopSateEnum shopSateEnum){
        this.state = shopSateEnum.getState();
        this.stateInfo = shopSateEnum.getStateInfo();
    }

    // 店铺操作成功的构造器
    public ShopExecution(ShopSateEnum shopSateEnum,Shop shop){
        this.state = shopSateEnum.getState();
        this.stateInfo = shopSateEnum.getStateInfo();
        this.shop = shop;
    }
    // 店铺操作成功的构造器
    public ShopExecution(ShopSateEnum shopSateEnum,List<Shop> shopList){
        this.state = shopSateEnum.getState();
        this.stateInfo = shopSateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
