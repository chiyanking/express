package com.dagemen.enums;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class LabelValue {

    @JsonSerialize(using=ToStringSerializer.class)
    private Object value;
    private Object label;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }
}