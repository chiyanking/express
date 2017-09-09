package com.dagemen.dto;

import java.util.List;

public class PCD {

    private Object  label;

    private Object  value;

    private List<PCD> children;

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<PCD> getChildren() {
        return children;
    }

    public void setChildren(List<PCD> children) {
        this.children = children;
    }
}
