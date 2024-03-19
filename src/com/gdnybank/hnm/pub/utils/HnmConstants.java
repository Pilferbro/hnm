package com.gdnybank.hnm.pub.utils;

/**
 * desc:hnm 常量类
 */
public class HnmConstants {

	/**操作类型 新增或修改**/
	public static final String OPER_TYPE_SAVE_UPDATE = "01";
	/**操作类型 删除**/
	public static final String OPER_TYPE_DELETE = "02";
	/**时间格式**/
	public static final String DATE_YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	public static final String DATE_YYYYMMDD="yyyyMMdd";
	/**影像资料存储位置 LOCAL 在本地**/
	public static final String MEDIA_STATUS_LOCAL = "LOCAL";
	/**影像资料存储位置  REMOTE 在远程文件系统**/
	public static final String MEDIA_STATUS_REMOTE = "REMOTE";


	public static final String TABLE_NAME="T_BANK_CODE"; //银行编码表名称
	public static final String TABLE_TEMP_NAME="T_BANK_CODE_TEMP"; //银行编码临时表名称
	public static final String SUPER_TABLE_NAME="T_BANK_CODE_SUPER"; //超级网银银行编码表名称
	public static final String SUPER_TABLE_TEMP_NAME="T_BANK_CODE_SUPER_TEMP"; //超级网银银行编码临时表名称
}
