package com.gdnybank.hnm.pub.enums;


import cn.hutool.core.util.StrUtil;

/**
 * desc:图片枚举（暂用于站点管理内图片命名）
 * @author:pzz 
 * date:2015年11月5日
 */
public enum PicTypeEnum {
	A("A","《广东南粤银行银行卡助农取款业务申请表》"), // 《广东南粤银行银行卡助农取款业务申请表》
	B("B","站长身份证正面"), //站长身份证正面
	C("C","站长身份证反面"), //站长身份证反面
	D("D","主卡正面照片"), //主卡正面照片
	E("E","合作协议书"), //合作协议书
	F("F","站长个人征询的查询授权"), //站长个人征询的查询授权
	G("G","站长征信报告"), //站长征信报告
	H("H","站长无犯罪证明"), //站长无犯罪证明
	I("I","拟定服务点环境照片"), //拟定服务点环境照片
	J("J","其他材料"), //其他材料
	K("K","《普惠金融支付服务点手里银联卡业务协议书》"), //《普惠金融支付服务点手里银联卡业务协议书》;
	L("L","服务点工商营业执照"),//服务点工商营业执照
	M("M","服务点装修照片"),//服务点装修照片
	N("N","其他资料"),//其他资料
	O("O","《普惠金融支付服务点助农终端撤销申请表》"),//《普惠金融支付服务点助农终端撤销申请表》
	P("P","站长退出协议");//站长退出协议




	public String key;
	public String value;

	PicTypeEnum(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public static String getFinTypeByCoreType(String picTypeEnum){
		if(StrUtil.isEmpty(picTypeEnum)){
			return null;
		}
		for(PicTypeEnum type:PicTypeEnum.values()){
			if(type.getKey().equals(picTypeEnum.trim())){
				return type.getValue();
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
