package com.dagemen.enums;

public enum ExpModelStatusEnum {
    Vaild(0, "有效"),
    unValid(1,"无效");
    private String name;
    private Integer value;
    ExpModelStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
