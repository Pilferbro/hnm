package com.gdnybank.hnm.pub.enums;

/**
 * desc:交易状态
 * @author:pzz
 * date:2016年1月13日
 */
public enum TransStatusEnum {

	EXECUTORY("0","执行中"),
	SUCCESS("1","成功"),
	PART_SUCCESS("2","部分成功"),
	NET_FAIL("3","通讯异常 "),
	TRAN_FAIL("4","交易失败");

	private String status;
	private String statusName;

	TransStatusEnum(String status,String statusName) {
		this.status = status;
		this.statusName = statusName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


}
