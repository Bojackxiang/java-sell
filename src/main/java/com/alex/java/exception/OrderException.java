package com.alex.java.exception;

import com.alex.java.enums.ResultEnum;

public class OrderException extends RuntimeException{

    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
