package com.zhangxuerong.o2o.enums;

public enum ShopSateEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),NULL_SHOPID(-1002,"ShopId胃为空"),NULL_SHOP(-1003,"Shop信息为空");
    private int state;
    private String stateInfo;


    private ShopSateEnum(int state, String sateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopSateEnum stateOf(int state){
        for(ShopSateEnum stateEnum:values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
