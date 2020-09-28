package com.alex.java.enums;

public enum TestEnum {

    ACTIVE(0, "激活"),
    DEAVTIVE(1, "已经关闭")
    ;
    private Integer activeCode;
    private String message;

    TestEnum(Integer activeCode, String message) {
        this.activeCode = activeCode;
        this.message = message;
    }
}
