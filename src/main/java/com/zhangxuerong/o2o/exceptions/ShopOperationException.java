package com.zhangxuerong.o2o.exceptions;

public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 2740673872882170564L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
