package com.alex.java.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {

    UP(1, "上架的商品"),
    DOWN(0, "下架的商品")
    ;

    private Integer productCode;
    private String message;

    ProductStatus(Integer productCode, String message) {
        this.productCode = productCode;
        this.message = message;
    }


}
