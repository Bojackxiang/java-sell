package com.alex.java.Utils;
import lombok.Data;

@Data
public class ResponseObj<T> {
    /*
     * 返回给前端的对象
     */
    private Integer code;
    private String message;
    private T data;
}

// ArrayList<ProductDataObj> productDataObjs = new ArrayList<ProductDataObj>();
