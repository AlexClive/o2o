package com.zhangxuerong.o2o.exceptions;

import java.io.Serial;

public class ShopOperationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2740673872882170564L;

    public ShopOperationException(String msg) {
        super(msg);
    }
}
