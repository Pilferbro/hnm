package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学历审批条件项值
 */
public enum EducationEnum {
	ITEM_0("0","初中及以下"),
	ITEM_1("1","高中(含中专等)"),
    ITEM_2("2","大专"),
    ITEM_3("3","本科"),
    ITEM_4("4","研究生及以上"),
    ;

	private String item;
	private String value;

	EducationEnum(String item, String value){
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

	public static EducationEnum findByItem(String item) {
		for (EducationEnum node : values()) {
			if (node.getItem().equals(item)) {
				return node;
			}
		}
		return null;
	}

	public static EducationEnum findByValue(String value) {
		for (EducationEnum node : values()) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (EducationEnum node : EducationEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("item", node.getItem());
			map.put("value", node.getValue());
			list.add(map);
		}
		return list;
	}
}
