package com.gdnybank.hnm.pub.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审批状态
 */
public enum ApprovalStatusEnum {
	//审批状态枚举
	STATUS_1("1","审批中"),
	STATUS_2("2","审批通过"),
	STATUS_3("3","审批未通过"),
	STATUS_4("4","驳回"),
	STATUS_9("9","废弃");

	private String approvalStatus;
	private String approvalStatusName;

	ApprovalStatusEnum(String approvalStatus, String approvalStatusName){
		this.approvalStatus = approvalStatus;
		this.approvalStatusName = approvalStatusName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatusName() { return approvalStatusName; }

	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}

	public static ApprovalStatusEnum findByApprovalStatus(String approvalStatus) {
		for (ApprovalStatusEnum status : values()) {
			if (status.getApprovalStatus().equals(approvalStatus)) {
				return status;
			}
		}
		return null;
	}

	public static ApprovalStatusEnum findByApprovalStatusName(String approvalStatusName) {
		for (ApprovalStatusEnum status : values()) {
			if (status.getApprovalStatusName().equals(approvalStatusName)) {
				return status;
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (ApprovalStatusEnum item : ApprovalStatusEnum.values()) {
			Map<String, Object> map = new HashMap<>();
			map.put("approvalStatus", item.getApprovalStatus());
			map.put("approvalStatusName", item.getApprovalStatusName());
			list.add(map);
		}
		return list;
	}
}
