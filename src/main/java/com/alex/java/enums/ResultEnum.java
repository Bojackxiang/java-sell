package com.alex.java.enums;


public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(-1, "库存不足"),
    NO_ORDER_MASTER(-1, "没有找到 orderMaster"),
    NO_ORDER_DETAIL_FOUND(-1, "没有找到对应的 order detail list"),
    FAILED_VALIDATION(-1, "穿件表单的时候，校验失败"),
    TRANSFER_ERROR(-1, "Order form -> order DTO 转换失败哦"),
    NO_CARD(-1, "前端传进来没有东西"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
