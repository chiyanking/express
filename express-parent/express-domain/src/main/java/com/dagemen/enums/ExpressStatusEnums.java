package com.dagemen.enums;

public enum ExpressStatusEnums {


    WAITE(3, "等待打印"),
    PRINTED(5,"已经打印"),
    Delete(9,"已经删除");
    private String name;
    private Integer value;

    ExpressStatusEnums(Integer value, String name) {
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
