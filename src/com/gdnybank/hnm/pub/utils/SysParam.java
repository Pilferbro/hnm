package com.gdnybank.hnm.pub.utils;

/**
 * SysParam entity. @author MyEclipse Persistence Tools
 * modify by pzz
 * desc:在mfp的mfp-0.0.1-SNAPSHOT版本上重写，将数据库关键字group改成groupid
 */

public class SysParam extends com.nantian.mfp.framework.dao.model.BaseModel
		implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String groupid;
	private String gname;
	private String paramkey;
	private String paramlabel;
	private String defparamvalue;
	private String memo;

	// Constructors

	/** default constructor */
	public SysParam() {
	}

	/** minimal constructor */
	public SysParam(String paramkey) {
		this.paramkey = paramkey;
	}

	/** full constructor */
	public SysParam(String group, String gname, String paramkey, String paramlabel, String defparamvalue,
                    String memo) {
		this.groupid=group;
		this.gname=gname;
		this.paramkey = paramkey;
		this.paramlabel = paramlabel;
		this.defparamvalue = defparamvalue;
		this.memo = memo;
	}

	// Property accessors

	public String getParamkey() {
		return this.paramkey;
	}

	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}

	public String getParamlabel() {
		return this.paramlabel;
	}

	public void setParamlabel(String paramlabel) {
		this.paramlabel = paramlabel;
	}

	public String getDefparamvalue() {
		return this.defparamvalue;
	}

	public void setDefparamvalue(String defparamvalue) {
		this.defparamvalue = defparamvalue;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setGroup(String group) {
		this.groupid = group;
	}
	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGroup() {
		return this.groupid;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
}