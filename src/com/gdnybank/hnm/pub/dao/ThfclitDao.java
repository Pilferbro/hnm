package com.gdnybank.hnm.pub.dao;

import static com.nantian.mfp.framework.dao.sql.Sql.and;
import static com.nantian.mfp.framework.dao.sql.Sql.field;
import static com.nantian.mfp.framework.dao.sql.Sql.orderBySql;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSqlInOracle;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSql;
import static com.nantian.mfp.framework.dao.sql.Sql.printSql;
import static com.nantian.mfp.framework.dao.sql.Sql.hasKey;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.nantian.mfp.framework.utils.BaseUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:THFCLIT
 * 主键:
 * cltnbr
 **/
@Repository
public class ThfclitDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"cltnbr","flag","branno","crmflag","isstock","iscancel","disttype","feedtype","proeftdate","proexpdate","cardno","cardopendate","quaraccum","quardate","ismeetcon","clttag","opendate","clttype","chrstatus","entrusttag","fbackid","fircrtdate","changedate","changetime","remark","reserv20","reserv30","reserv40","remark1","remark2","remark3","rcdver","rcdstatus"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"cltnbr"};

	@Override
	public int save(Map<String,Object> p) {
				//p.put("cltnbr",sequenceService.getTableFlowNo("THFCLIT", "cltnbr"));
		String sql=Sql.create("insert into THFCLIT (")
				.append(field("cltnbr "))
				.append(field("flag ",hasKey(p,"flag")))
				.append(field("branno ",hasKey(p,"branno")))
				.append(field("crmflag ",hasKey(p,"crmflag")))
				.append(field("isstock ",hasKey(p,"isstock")))
				.append(field("iscancel ",hasKey(p,"iscancel")))
				.append(field("disttype ",hasKey(p,"disttype")))
				.append(field("feedtype ",hasKey(p,"feedtype")))
				.append(field("proeftdate ",hasKey(p,"proeftdate")))
				.append(field("proexpdate ",hasKey(p,"proexpdate")))
				.append(field("cardno ",hasKey(p,"cardno")))
				.append(field("cardopendate ",hasKey(p,"cardopendate")))
				.append(field("quaraccum ",hasKey(p,"quaraccum")))
				.append(field("quardate ",hasKey(p,"quardate")))
				.append(field("ismeetcon ",hasKey(p,"ismeetcon")))
				.append(field("clttag ",hasKey(p,"clttag")))
				.append(field("opendate ",hasKey(p,"opendate")))
				.append(field("clttype ",hasKey(p,"clttype")))
				.append(field("chrstatus ",hasKey(p,"chrstatus")))
				.append(field("entrusttag ",hasKey(p,"entrusttag")))
				.append(field("fbackid ",hasKey(p,"fbackid")))
				.append(field("fircrtdate ",hasKey(p,"fircrtdate")))
				.append(field("changedate ",hasKey(p,"changedate")))
				.append(field("changetime ",hasKey(p,"changetime")))
				.append(field("remark ",hasKey(p,"remark")))
				.append(field("reserv20 ",hasKey(p,"reserv20")))
				.append(field("reserv30 ",hasKey(p,"reserv30")))
				.append(field("reserv40 ",hasKey(p,"reserv40")))
				.append(field("remark1 ",hasKey(p,"remark1")))
				.append(field("remark2 ",hasKey(p,"remark2")))
				.append(field("remark3 ",hasKey(p,"remark3")))
				.append(field("rcdver ",hasKey(p,"rcdver")))
				.append(field("rcdstatus ",hasKey(p,"rcdstatus")))
				.append(") values (")
				.append(field(":cltnbr "))
				.append(field(":flag ",hasKey(p,"flag")))
				.append(field(":branno ",hasKey(p,"branno")))
				.append(field(":crmflag ",hasKey(p,"crmflag")))
				.append(field(":isstock ",hasKey(p,"isstock")))
				.append(field(":iscancel ",hasKey(p,"iscancel")))
				.append(field(":disttype ",hasKey(p,"disttype")))
				.append(field(":feedtype ",hasKey(p,"feedtype")))
				.append(field(":proeftdate ",hasKey(p,"proeftdate")))
				.append(field(":proexpdate ",hasKey(p,"proexpdate")))
				.append(field(":cardno ",hasKey(p,"cardno")))
				.append(field(":cardopendate ",hasKey(p,"cardopendate")))
				.append(field(":quaraccum ",hasKey(p,"quaraccum")))
				.append(field(":quardate ",hasKey(p,"quardate")))
				.append(field(":ismeetcon ",hasKey(p,"ismeetcon")))
				.append(field(":clttag ",hasKey(p,"clttag")))
				.append(field(":opendate ",hasKey(p,"opendate")))
				.append(field(":clttype ",hasKey(p,"clttype")))
				.append(field(":chrstatus ",hasKey(p,"chrstatus")))
				.append(field(":entrusttag ",hasKey(p,"entrusttag")))
				.append(field(":fbackid ",hasKey(p,"fbackid")))
				.append(field(":fircrtdate ",hasKey(p,"fircrtdate")))
				.append(field(":changedate ",hasKey(p,"changedate")))
				.append(field(":changetime ",hasKey(p,"changetime")))
				.append(field(":remark ",hasKey(p,"remark")))
				.append(field(":reserv20 ",hasKey(p,"reserv20")))
				.append(field(":reserv30 ",hasKey(p,"reserv30")))
				.append(field(":reserv40 ",hasKey(p,"reserv40")))
				.append(field(":remark1 ",hasKey(p,"remark1")))
				.append(field(":remark2 ",hasKey(p,"remark2")))
				.append(field(":remark3 ",hasKey(p,"remark3")))
				.append(field(":rcdver ",hasKey(p,"rcdver")))
				.append(field(":rcdstatus ",hasKey(p,"rcdstatus")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}
	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String,Object> p) {
		String sql=Sql.create("delete from THFCLIT where 1=1 ")
				.append(and("cltnbr = :cltnbr"))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	/***
	 * 删除一条或者多条数据,慎用此函数.
	 * 此函数当传入的条件为空的时候,有可能会导致整张数据表被删除,因此,在执行此函数前,请一定对传入的参数进行非空检验,以防数据被误删.
	 * 项目组务必对使用此函数的代码进行代码走查,防止漏洞发生,防止被攻击.
	 **/
	@Override
	public int delete(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("delete from THFCLIT where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update THFCLIT set ")
				.append(field("flag = :flag",hasKey(p,"flag")))
				.append(field("branno = :branno",hasKey(p,"branno")))
				.append(field("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(field("isstock = :isstock",hasKey(p,"isstock")))
				.append(field("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(field("disttype = :disttype",hasKey(p,"disttype")))
				.append(field("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(field("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(field("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(field("cardno = :cardno",hasKey(p,"cardno")))
				.append(field("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(field("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(field("quardate = :quardate",hasKey(p,"quardate")))
				.append(field("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(field("clttag = :clttag",hasKey(p,"clttag")))
				.append(field("opendate = :opendate",hasKey(p,"opendate")))
				.append(field("clttype = :clttype",hasKey(p,"clttype")))
				.append(field("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(field("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(field("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(field("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(field("changedate = :changedate",hasKey(p,"changedate")))
				.append(field("changetime = :changetime",hasKey(p,"changetime")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(field("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(field("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(field("remark1 = :remark1",hasKey(p,"remark1")))
				.append(field("remark2 = :remark2",hasKey(p,"remark2")))
				.append(field("remark3 = :remark3",hasKey(p,"remark3")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("cltnbr = :cltnbr"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update THFCLIT set ")
				.append(field("flag = :flag",hasKey(p,"flag")))
				.append(field("branno = :branno",hasKey(p,"branno")))
				.append(field("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(field("isstock = :isstock",hasKey(p,"isstock")))
				.append(field("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(field("disttype = :disttype",hasKey(p,"disttype")))
				.append(field("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(field("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(field("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(field("cardno = :cardno",hasKey(p,"cardno")))
				.append(field("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(field("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(field("quardate = :quardate",hasKey(p,"quardate")))
				.append(field("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(field("clttag = :clttag",hasKey(p,"clttag")))
				.append(field("opendate = :opendate",hasKey(p,"opendate")))
				.append(field("clttype = :clttype",hasKey(p,"clttype")))
				.append(field("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(field("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(field("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(field("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(field("changedate = :changedate",hasKey(p,"changedate")))
				.append(field("changetime = :changetime",hasKey(p,"changetime")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(field("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(field("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(field("remark1 = :remark1",hasKey(p,"remark1")))
				.append(field("remark2 = :remark2",hasKey(p,"remark2")))
				.append(field("remark3 = :remark3",hasKey(p,"remark3")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String,Object> p) {

		return 0;
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select * from THFCLIT where 1=1")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from THFCLIT where 1=1")
			.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
						.append(and("flag = :flag",hasKey(p,"flag")))
						.append(and("branno = :branno",hasKey(p,"branno")))
						.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
						.append(and("isstock = :isstock",hasKey(p,"isstock")))
						.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
						.append(and("disttype = :disttype",hasKey(p,"disttype")))
						.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
						.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
						.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
						.append(and("cardno = :cardno",hasKey(p,"cardno")))
						.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
						.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
						.append(and("quardate = :quardate",hasKey(p,"quardate")))
						.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
						.append(and("clttag = :clttag",hasKey(p,"clttag")))
						.append(and("opendate = :opendate",hasKey(p,"opendate")))
						.append(and("clttype = :clttype",hasKey(p,"clttype")))
						.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
						.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
						.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
						.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
						.append(and("changedate = :changedate",hasKey(p,"changedate")))
						.append(and("changetime = :changetime",hasKey(p,"changetime")))
						.append(and("remark = :remark",hasKey(p,"remark")))
						.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
						.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
						.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
						.append(and("remark1 = :remark1",hasKey(p,"remark1")))
						.append(and("remark2 = :remark2",hasKey(p,"remark2")))
						.append(and("remark3 = :remark3",hasKey(p,"remark3")))
						.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
						.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.append(orderBySql()).toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(orderBySql()).append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 查询一条唯一记录,如果没有查到或者查询到两条以上记录会报异常,服务层应该扑捉此类异常进行特别处理.
	 * @see EmptyResultDataAccessException
	 * @see IncorrectResultSizeDataAccessException
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 ***/
	@Override
	public Map<String, Object> queryForMap(Map<String,Object> p) {
		String sql=Sql.create("select * from THFCLIT where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from THFCLIT where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}
	public List<Map<String, Object>> queryForListById(Map<String,Object> p) {
		String sql=Sql.create("select * from THFCLIT where 1=1")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql=Sql.create("select * from THFCLIT where 1=1")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("crmflag = :crmflag",hasKey(p,"crmflag")))
				.append(and("isstock = :isstock",hasKey(p,"isstock")))
				.append(and("iscancel = :iscancel",hasKey(p,"iscancel")))
				.append(and("disttype = :disttype",hasKey(p,"disttype")))
				.append(and("feedtype = :feedtype",hasKey(p,"feedtype")))
				.append(and("proeftdate = :proeftdate",hasKey(p,"proeftdate")))
				.append(and("proexpdate = :proexpdate",hasKey(p,"proexpdate")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("cardopendate = :cardopendate",hasKey(p,"cardopendate")))
				.append(and("quaraccum = :quaraccum",hasKey(p,"quaraccum")))
				.append(and("quardate = :quardate",hasKey(p,"quardate")))
				.append(and("ismeetcon = :ismeetcon",hasKey(p,"ismeetcon")))
				.append(and("clttag = :clttag",hasKey(p,"clttag")))
				.append(and("opendate = :opendate",hasKey(p,"opendate")))
				.append(and("clttype = :clttype",hasKey(p,"clttype")))
				.append(and("chrstatus = :chrstatus",hasKey(p,"chrstatus")))
				.append(and("entrusttag = :entrusttag",hasKey(p,"entrusttag")))
				.append(and("fbackid = :fbackid",hasKey(p,"fbackid")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("fircrtdate <= :nowdate",hasKey(p,"nowdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				;
				if(ObjectUtil.isNotEmpty(p.get("siteIds"))) {
					sql.append(and("branno in ("+ p.get("siteIds")+")"));
				}
				sql.append(orderBySql());
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	/**
	 * 一次性删除全表数据
	 * @author chenhao
	 */
	public int truncate(){
		return super.update("truncate table THFCLIT", BaseUtils.map());
	}

}
