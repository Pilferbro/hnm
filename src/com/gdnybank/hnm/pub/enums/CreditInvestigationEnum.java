package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 征信情况或犯罪情况审批条件项值
 */
public enum CreditInvestigationEnum {
	ITEM_0("0","当期无逾期"),
	ITEM_1("1","当期有逾期");

	private String item;
	private String value;

	CreditInvestigationEnum(String item, String value){
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

	public static CreditInvestigationEnum findByItem(String item) {
		for (CreditInvestigationEnum node : values()) {
			if (node.getItem().equals(item)) {
				return node;
			}
		}
		return null;
	}

	public static CreditInvestigationEnum findByValue(String value) {
		for (CreditInvestigationEnum node : values()) {
			if (node.getValue().equals(value)) {
				return node;
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (CreditInvestigationEnum node : CreditInvestigationEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("item", node.getItem());
			map.put("value", node.getValue());
			list.add(map);
		}
		return list;
	}
}
