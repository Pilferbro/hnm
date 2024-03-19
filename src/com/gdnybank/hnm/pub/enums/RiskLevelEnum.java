package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RiskLevelEnum {
    ITEM_0("0","高"),
    ITEM_1("1","中"),
    ITEM_2("2","低");

    private String item;
    private String value;

    RiskLevelEnum(String item, String value){
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static RiskLevelEnum findByItem(String item) {
        for (RiskLevelEnum node : values()) {
            if (node.getItem().equals(item)) {
                return node;
            }
        }
        return null;
    }

    public static RiskLevelEnum findByValue(String value) {
        for (RiskLevelEnum node : values()) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }
        return null;
    }

    public static List<Map<String, Object>> toList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TrueOrFalseEnum node : TrueOrFalseEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("item", node.getItem());
            map.put("value", node.getValue());
            list.add(map);
        }
        return list;
    }
}
