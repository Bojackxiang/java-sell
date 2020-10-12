package com.alex.java.exception;

import com.alex.java.enums.ResultEnum;

public class OrderException extends RuntimeException{

    private Integer code;
    private String message;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public OrderException(
        ResultEnum resultEnum,
        String message){
        this.code = resultEnum.getCode();
        this.message =message;
    }
}
