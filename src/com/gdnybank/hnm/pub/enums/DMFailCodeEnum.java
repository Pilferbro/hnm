package com.gdnybank.hnm.pub.enums;


import cn.hutool.core.util.StrUtil;

/**
 * desc:DM 服务端定义的状态码 (文件系统返回的错误码)
 * @author pzz
 * @date 2016-11-25
 */
public enum DMFailCodeEnum {
	FAIL_700("700","未知的服务端异常"),
	FAIL_701("701","找不到业务处理时间"),
	FAIL_702("702","创建文件夹失败"),
	FAIL_703("703","内容存储服务器不支持该内容模型"),
	FAIL_704("704","服务端接收文件失败"),
	FAIL_705("705","服务器连接失败"),
	FAIL_706("706","会话异常"),
	FAIL_707("707","找不到文件"),
	FAIL_708("708","数据库操作失败"),
	FAIL_709("709","获取console配置失败"),
	FAIL_710("710","MD5 码校验失败"),
	FAIL_711("711","批次文件没有提交 MD5码,服务器配置需要校验MD5"),
	FAIL_712("712","找不到分表"),
	FAIL_713("713","找不到分表时间"),
	FAIL_714("714","LOG4J配置修改失败"),
	FAIL_715("715","url加密失败"),
	FAIL_716("716","url解密失败"),
	FAIL_717("717","权限校验出错"),
	FAIL_718("718","批次文档部件名有误或未关联"),
	FAIL_719("719","该属性未关联该内容模型"),
	FAIL_720("720","IO 设备出错"),
	FAIL_721("721","找不到所配置的系统卷"),
	FAIL_722("722","转换对象失败"),
	FAIL_723("723","找不到 IP"),
	FAIL_724("724","找不到检入检出标识"),
	FAIL_725("725","不支持的数据类型代码"),
	FAIL_726("726","内容模型没有该属性字段"),
	FAIL_727("727","同一批次中文件重复"),
	FAIL_728("728","内容模型获取失败，或没有与存储服务器关联"),
	FAIL_729("729","令牌校验参数不正确"),
	FAIL_730("730","用户未登陆"),
	FAIL_731("731","用户没有对该内容模型的权限"),
	FAIL_732("732","令牌校验失败"),
	FAIL_733("733","数据库操作失败,原因:批次未检出或文件 FILENO 错误"),
	FAIL_734("734","批次已被锁定，已被检出"),
	FAIL_735("735","批注信息添加失败"),
	FAIL_736("736","客户端连接服务器失败"),
	FAIL_737("737","批次不存在或最新版本不在当前服务器组"),
	FAIL_738("738","文档对象一次只能上传一个"),
	FAIL_739("739","文档对象模型的批次中没有文档信息"),
	FAIL_740("740","文档对象未关联索引对象"),
	FAIL_741("741","服务器已停用"),
	FAIL_742("742","上传报文错误"),
	FAIL_743("743","服务器接收的文件与批次上传的文件数不一致"),
	FAIL_744("744","服务器组名称不正确,统一接入未从 Console 接收到该服务器组的配置信息,或所查詢的內容存儲服务器组所有服务器均已停用"),
	FAIL_745("745","更新时有版本控制的内容模型必需要设置版本号"),
	FAIL_746("746","更新不支持单独文档对象"),
	FAIL_750("750","spring 获取 Bean 信息失败"),
	FAIL_751("751","WebService 异常"),
	FAIL_752("752","服务端 socket 读取一行信息出错"),
	FAIL_753("753","迁移失败"),
	FAIL_754("754","迁移服务端发生错误"),
	FAIL_755("755","参数缺少文件修改类型"),
	FAIL_756("756","UA 无法根据 contentID 找到对应的 DM，在上传，跟新时会产生此错误"),
	FAIL_757("757","不支持的操作"),
	FAIL_758("758","第三方存储平台操作失败");
	
	private String code;
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    DMFailCodeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
    
    public static String getDescByCode(String code){
    	if(StrUtil.isBlank(code)){
    		return code+"未定义";
    	}
    	for(DMFailCodeEnum dmFailCodeEnum:DMFailCodeEnum.values()){
    		if(code.trim().equals(dmFailCodeEnum.getCode())){
    			return dmFailCodeEnum.getDesc();
    		}
    	}
    	return code+"未定义";
    }
}
