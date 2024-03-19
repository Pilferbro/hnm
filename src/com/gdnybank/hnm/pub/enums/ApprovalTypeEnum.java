package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ApprovalTypeEnum {
	TYPE002("002","新增人员"),
	TYPE003("003","人员基本信息调整"),
	TYPE004("004","新增站点(试营业)"),
	TYPE005("005","站点基本信息调整"),
	TYPE006("006","助农POS申请"),
	TYPE007("007","助农POS申请和申请开业"),
	TYPE008("008","退出申请"),
	TYPE009("009","新增团队"),
	TYPE010("010","团队基本信息调整"),
	TYPE011("011","团队类型调整"),
	TYPE012("012","团队所属机构调整"),
	TYPE013("013","团队负责人调整"),
	TYPE014("014","人员角色调整"),
	TYPE015("015","人员所属机构调整"),
	TYPE016("016","站点站长信息调整"),
	TYPE017("017","站点POS信息调整"),
	TYPE018("018","站点管辖客户经理调整"),
	TYPE019("019","站点落地支行调整"),
	TYPE020("020","删除团队"),
	TYPE021("021","删除人员"),
	TYPE022("022","转试营业申请"),
	TYPE023("023","风险处置整改"),
	TYPE024("024","新增角色"),
	TYPE025("025","修改角色"),
	TYPE026("026","删除角色");

	private String approvalType;
	private String approvalTypeName;

	ApprovalTypeEnum(String approvalType,String approvalTypeName){
		this.approvalType = approvalType;
		this.approvalTypeName = approvalTypeName;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalTypeName() {
		return approvalTypeName;
	}

	public void setApprovalTypeName(String approvalTypeName) {
		this.approvalTypeName = approvalTypeName;
	}

	public static ApprovalTypeEnum findByApprovalType(String approvalType) {
		for (ApprovalTypeEnum type : values()) {
			if (type.getApprovalType().equals(approvalType)) {
				return type;
			}
		}
		return null;
	}

	public static ApprovalTypeEnum findByApprovalTypeName(String approvalTypeName) {
		for (ApprovalTypeEnum type : values()) {
			if (type.getApprovalTypeName().equals(approvalTypeName)) {
				return type;
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (ApprovalTypeEnum item : ApprovalTypeEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("approvalType", item.getApprovalType());
			map.put("approvalTypeName", item.getApprovalTypeName());
			list.add(map);
		}
		return list;
	}

}
