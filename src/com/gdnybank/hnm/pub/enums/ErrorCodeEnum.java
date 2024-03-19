package com.gdnybank.hnm.pub.enums;

import com.nantian.mfp.framework.err.BusinessException;


/**
 * desc:错误码枚举
 * @author pzz
 * date:2017-03-01 11:04
 *
 */
public enum ErrorCodeEnum {
	/**
	 * "HnmCommService001","从ESB同步 %s失败"
	 */
	HnmCommService001("HnmCommService001","从ESB同步 %s失败"),
	/**
	 * "HnmCommService002","从ESB同步 %s失败"
	 */
	HnmCommService002("HnmCommService002","从ESB同步 %s失败"),
	/**
	 * "HnmCommService004","主管机构信息为空"
	 */
	HnmCommService004("HnmCommService004","主管机构信息为空"),
	/**
	 * "HnmCommService005","主管与柜员不属于同一机构"
	 */
	HnmCommService005("HnmCommService005","主管与柜员不属于同一机构"),
	/**
	 * "HnmCommService006","授权人员%s的级别未达到%s"
	 */
	HnmCommService006("HnmCommService006","授权人员%s的级别未达到%s"),
	/**
	 * "HnmCommService007","主管授权验密失败[%s]"
	 */
	HnmCommService007("HnmCommService007","主管授权验密失败[%s]"),
	/**
	 * "HnmCommService008","主管编号已停用"
	 */
	HnmCommService008("HnmCommService008","主管编号已停用"),
	/**
	 * "HnmCommService009","主管编号不存在"
	 */
	HnmCommService009("HnmCommService009","主管编号不存在"),
	/**
	 * "HnmCommService010","主管密码或指纹不正确"
	 */
	HnmCommService010("HnmCommService010","主管密码或指纹不正确"),
	/**
	 * "HnmCommService011","主管短期离岗"
	 */
	HnmCommService011("HnmCommService011","主管短期离岗"),
	/**
	 * "HnmCommService012","主管已注销"
	 */
	HnmCommService012("HnmCommService012","主管已注销"),
	/**
	 * "HnmCommService013","主管验密查询无返回结果"
	 */
	HnmCommService013("HnmCommService013","主管验密查询无返回结果"),
	/**
	 * "HnmCommService014","主管信息不存在"
	 */
	HnmCommService014("HnmCommService014","主管信息不存在"),
	/**
	 * "HnmCommService015","操作失败[%s]"
	 */
	HnmCommService015("HnmCommService015","操作失败[%s]"),
	/**
	 * "HnmCommService016","获取业务流水号失败"
	 */
	HnmCommService016("HnmCommService016","获取业务流水号失败"),
	/**
	 * "HnmCommService017","获取业务流水号失败"
	 */
	HnmCommService017("HnmCommService017","获取业务流水号失败"),
	/**
	 * "HnmCommService018","获取业务流水号失败"
	 */
	HnmCommService018("HnmCommService018","获取业务流水号失败"),
	/**
	 * "HnmCommService019","获取业务流水号失败"
	 */
	HnmCommService019("HnmCommService019","获取业务流水号失败"),
	/**
	 * "HnmCommService020","获取短信内容失败"
	 */
	HnmCommService020("HnmCommService020","获取短信内容失败"),
	/**
	 * "HnmCommService021","接收短信的手机号不能为空"
	 */
	HnmCommService021("HnmCommService021","接收短信的手机号不能为空"),
	/**
	 * "HnmCommService022","发送短信失败[短信内容加密失败]"
	 */
	HnmCommService022("HnmCommService022","发送短信失败[短信内容加密失败]"),
	/**
	 * "HnmCommService023","通讯异常"
	 */
	HnmCommService023("HnmCommService023","通讯异常"),
	/**
	 * "HnmCommService024","获取短信内容失败，原因：%s"
	 */
	HnmCommService024("HnmCommService024","获取短信内容失败，原因：%s"),
	/**
	 * "HnmCommService025","获取用户信息出错"
	 */
	HnmCommService025("HnmCommService025","获取用户信息出错"),
	/**
	 * "HnmCommService026","本地文件 %s不存在,请查证"
	 */
	HnmCommService026("HnmCommService026","本地文件 %s不存在,请查证"),
	/**
	 * "HnmCommService027","读取本地文件 %s失败"
	 */
	HnmCommService027("HnmCommService027","读取本地文件 %s失败"),
	/**
	 * "HnmCommService028","获取远程数据文件出错"
	 */
	HnmCommService028("HnmCommService028","获取远程数据文件出错"),
	/**
	 * "HnmCommService029","获取远程数据文件失败"
	 */
	HnmCommService029("HnmCommService029","获取远程数据文件失败"),
	/**
	 * "HnmCommService030","获取远程数据文件出错"
	 */
	HnmCommService030("HnmCommService030","获取远程数据文件出错"),
	/**
	 * "HnmCommService031","获取远程数据文件失败"
	 */
	HnmCommService031("HnmCommService031","获取远程数据文件失败"),
	/**
	 * "HnmCommService032","不支持的“pwdType”类型：%s"
	 */
	HnmCommService032("HnmCommService032","不支持的“pwdType”类型：%s"),
	/**
	 * "HnmCommService033","柜员密码RSA解密出错"
	 */
	HnmCommService033("HnmCommService033","柜员密码RSA解密出错"),
	/**
	 * "HnmCommService035","获取“.ok”文件出错"
	 */
	HnmCommService035("HnmCommService035","获取“.ok”文件出错"),
	/**
	 * "HnmCommService036","获取“.ok”文件失败"
	 */
	HnmCommService036("HnmCommService036","获取“.ok”文件失败"),
	/**
	 * "HnmCommService037","获取“.ok”文件出错"
	 */
	HnmCommService037("HnmCommService037","获取“.ok”文件出错"),
	/**
	 * "HnmCommService038","获取“.ok”文件失败"
	 */
	HnmCommService038("HnmCommService038","获取“.ok”文件失败"),
	/**
	 * "HnmCommService039","获取“.ok”文件出错"
	 */
	HnmCommService039("HnmCommService039","获取“.ok”文件出错"),
	/**
	 * "HnmCommService040","获取“.ok”文件失败"
	 */
	HnmCommService040("HnmCommService040","获取“.ok”文件失败"),
	/**
	 * "HnmCommService041","获取远程数据文件出错"
	 */
	HnmCommService041("HnmCommService041","获取远程数据文件出错"),
	/**
	 * "HnmCommService042","获取远程数据文件失败"
	 */
	HnmCommService042("HnmCommService042","获取远程数据文件失败"),
	/**
	 * "HnmCommService043","查询账户基本信息失败：%s"
	 */
	HnmCommService043("HnmCommService043","查询账户基本信息失败：%s"),
	/**
	 * "HnmCommService044","发送短信验证码，key“msg_mobile”不能为空"
	 */
	HnmCommService044("HnmCommService044","发送短信验证码，key“msg_mobile”不能为空"),
	/**
	 * "HnmCommService046","短信验证码已失效，请重新获取"
	 */
	HnmCommService046("HnmCommService046","短信验证码已失效，请重新获取"),
	/**
	 * "HnmCommService047","短信验证码已失效，请重新获取"
	 */
	HnmCommService047("HnmCommService047","短信验证码已失效，请重新获取"),
	/**
	 * "HnmCommService048","短信验证码验证不通过，请重新输入"
	 */
	HnmCommService048("HnmCommService048","短信验证码验证不通过，请重新输入"),
	/**
	 * "HnmCommService049","短信验证码失败次数已超系统允许最大失败次数，请重新获取"
	 */
	HnmCommService049("HnmCommService049","短信验证码失败次数已超系统允许最大失败次数，请重新获取"),
	/**
	 * "HnmCommService050","查询机构详细信息时，执行柜员数据不能为空！"
	 */
	HnmCommService050("HnmCommService050","查询机构详细信息时，执行柜员数据不能为空！"),
	/**
	 * "HnmCommService051","id_check数据格式不正确"
	 */
	HnmCommService051("HnmCommService051","id_check数据格式不正确"),
	/**
	 * "HnmCommService052","%s"
	 * 人脸识别失败
	 */
	HnmCommService052("HnmCommService052","%s"),
	/**
	 * "HnmCommService053","柜面业务流水查询失败"
	 * modified by guohan 20170911
	 */
	HnmCommService053("HnmCommService053","柜面业务流水查询失败[%s]"),
	/**
	 * "HnmCommService054","柜面业务流水查询失败"
	 * modified by guohan 20170911
	 */
	HnmCommService054("HnmCommService054","柜面业务流水查询失败,cur_seqno为空"),
	/**
	 * "HnmCommService055","柜面业务流水查询失败"
	 * modified by guohan 20170911
	 */
	HnmCommService055("HnmCommService055","柜面业务流水查询失败,body部分为空"),
	/**
	 * "HnmCommService056","身份证联网核查失败：%s"
	 * add by chenhao 20171208
	 */
	HnmCommService056("HnmCommService056","身份证联网核查失败：%s"),
	/**
	 * "HnmCommService057","身份联网核查成功[下载图片失败]"
	 * add by chenhao 20171208
	 */
	HnmCommService057("HnmCommService057","身份联网核查成功[下载图片失败]"),
	/**
	 * "HnmCommService058","身份联网核查成功[ftp下载图片通信异常]"
	 * add by chenhao 20171208
	 */
	HnmCommService058("HnmCommService058","身份联网核查成功[ftp下载图片通信异常]"),
	/**
	 * "HnmCommService059","公民身份号码与姓名一致，但不存在照片"
	 * add by chenhao 20171208
	 */
	HnmCommService059("HnmCommService059","公民身份号码与姓名一致，但不存在照片"),
	/**
	 * "HnmCommService060","身份联网核查失败：%s"
	 * add by chenhao 20171208
	 */
	HnmCommService060("HnmCommService060","身份联网核查失败：%s"),
	/**
	 * "HnmCommService061","身份联网核查失败：%s"
	 * add by chenhao 20171208
	 */
	HnmCommService061("HnmCommService061","身份联网核查失败：%s"),
	/**
	 * "HnmCommService062","身份联网核查失败：%s"
	 * add by chenhao 20171208
	 */
	HnmCommService062("HnmCommService062","身份联网核查失败：%s");
	
	
	
	private String code;
	private String desc;
	
	ErrorCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static BusinessException throwBusinessException(ErrorCodeEnum errorCodeEnum, Object...msgObjects){
		return new BusinessException(errorCodeEnum.getCode(), String.format(errorCodeEnum.getDesc(), msgObjects));
	}
	
}
