package com.ogy.weixin.pojo;

public abstract class AbstractButton {
    private String name;

    public AbstractButton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
