package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站长候选人信息情况审批条件项值
 */
public enum OccupationEnum {
	ITEM_0("0","企业主或个体户"),
	ITEM_1("1","村委人员或家属"),
    ITEM_2("2","企事单位职员"),
    ITEM_3("3","打工返乡人员(现无业)"),
    ITEM_4("4","毕业返乡人员(现无业)"),
    ITEM_5("5","自由职业"),
	ITEM_10("5","其他"),
    ;

	private String item;
	private String value;

	OccupationEnum(String item, String value){
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

	public static OccupationEnum findByItem(String item) {
		for (OccupationEnum node : values()) {
			if (node.getItem().equals(item)) {
				return node;
			}
		}
		return null;
	}

	public static OccupationEnum findByValue(String value) {
		for (OccupationEnum node : values()) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (OccupationEnum node : OccupationEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("item", node.getItem());
			map.put("value", node.getValue());
			list.add(map);
		}
		return list;
	}
}
