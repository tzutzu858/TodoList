package com.macroviz.todolist;

public class ItemData {
    String name;
    String code;

    public ItemData(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
