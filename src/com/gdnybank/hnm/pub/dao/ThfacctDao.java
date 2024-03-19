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
 * 表名:THFACCT
 * 主键:
 * richnbr
 **/
@Repository
public class ThfacctDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"richnbr","cardno","richclass","flag","branno","lastdate","channelno","esbsysno","esbtrack","regbrn","regtlr","cltnbr","fircrtdate","changedate","changetime","remark","reserv20","reserv30","reserv40","rcdver","rcdstatus"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"richnbr"};

	@Override
	public int save(Map<String,Object> p) {
				//p.put("richnbr",sequenceService.getTableFlowNo("THFACCT", "richnbr"));
		String sql=Sql.create("insert into THFACCT (")
				.append(field("richnbr "))
				.append(field("cardno ",hasKey(p,"cardno")))
				.append(field("richclass ",hasKey(p,"richclass")))
				.append(field("flag ",hasKey(p,"flag")))
				.append(field("branno ",hasKey(p,"branno")))
				.append(field("lastdate ",hasKey(p,"lastdate")))
				.append(field("channelno ",hasKey(p,"channelno")))
				.append(field("esbsysno ",hasKey(p,"esbsysno")))
				.append(field("esbtrack ",hasKey(p,"esbtrack")))
				.append(field("regbrn ",hasKey(p,"regbrn")))
				.append(field("regtlr ",hasKey(p,"regtlr")))
				.append(field("cltnbr ",hasKey(p,"cltnbr")))
				.append(field("fircrtdate ",hasKey(p,"fircrtdate")))
				.append(field("changedate ",hasKey(p,"changedate")))
				.append(field("changetime ",hasKey(p,"changetime")))
				.append(field("remark ",hasKey(p,"remark")))
				.append(field("reserv20 ",hasKey(p,"reserv20")))
				.append(field("reserv30 ",hasKey(p,"reserv30")))
				.append(field("reserv40 ",hasKey(p,"reserv40")))
				.append(field("rcdver ",hasKey(p,"rcdver")))
				.append(field("rcdstatus ",hasKey(p,"rcdstatus")))
				.append(") values (")
				.append(field(":richnbr "))
				.append(field(":cardno ",hasKey(p,"cardno")))
				.append(field(":richclass ",hasKey(p,"richclass")))
				.append(field(":flag ",hasKey(p,"flag")))
				.append(field(":branno ",hasKey(p,"branno")))
				.append(field(":lastdate ",hasKey(p,"lastdate")))
				.append(field(":channelno ",hasKey(p,"channelno")))
				.append(field(":esbsysno ",hasKey(p,"esbsysno")))
				.append(field(":esbtrack ",hasKey(p,"esbtrack")))
				.append(field(":regbrn ",hasKey(p,"regbrn")))
				.append(field(":regtlr ",hasKey(p,"regtlr")))
				.append(field(":cltnbr ",hasKey(p,"cltnbr")))
				.append(field(":fircrtdate ",hasKey(p,"fircrtdate")))
				.append(field(":changedate ",hasKey(p,"changedate")))
				.append(field(":changetime ",hasKey(p,"changetime")))
				.append(field(":remark ",hasKey(p,"remark")))
				.append(field(":reserv20 ",hasKey(p,"reserv20")))
				.append(field(":reserv30 ",hasKey(p,"reserv30")))
				.append(field(":reserv40 ",hasKey(p,"reserv40")))
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
		String sql=Sql.create("delete from THFACCT where 1=1 ")
				.append(and("richnbr = :richnbr"))
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
		String sql=Sql.create("delete from THFACCT where 1=1 ")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("richclass = :richclass",hasKey(p,"richclass")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("channelno = :channelno",hasKey(p,"channelno")))
				.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update THFACCT set ")
				.append(field("cardno = :cardno",hasKey(p,"cardno")))
				.append(field("richclass = :richclass",hasKey(p,"richclass")))
				.append(field("flag = :flag",hasKey(p,"flag")))
				.append(field("branno = :branno",hasKey(p,"branno")))
				.append(field("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(field("channelno = :channelno",hasKey(p,"channelno")))
				.append(field("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(field("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(field("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(field("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(field("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(field("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(field("changedate = :changedate",hasKey(p,"changedate")))
				.append(field("changetime = :changetime",hasKey(p,"changetime")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(field("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(field("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("richnbr = :richnbr"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update THFACCT set ")
				.append(field("cardno = :cardno",hasKey(p,"cardno")))
				.append(field("richclass = :richclass",hasKey(p,"richclass")))
				.append(field("flag = :flag",hasKey(p,"flag")))
				.append(field("branno = :branno",hasKey(p,"branno")))
				.append(field("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(field("channelno = :channelno",hasKey(p,"channelno")))
				.append(field("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(field("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(field("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(field("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(field("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(field("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(field("changedate = :changedate",hasKey(p,"changedate")))
				.append(field("changetime = :changetime",hasKey(p,"changetime")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(field("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(field("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("richclass = :richclass",hasKey(p,"richclass")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("channelno = :channelno",hasKey(p,"channelno")))
				.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
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
		String sql=Sql.create("select * from THFACCT where 1=1")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("richclass = :richclass",hasKey(p,"richclass")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("channelno = :channelno",hasKey(p,"channelno")))
				.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from THFACCT where 1=1")
			.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
						.append(and("cardno = :cardno",hasKey(p,"cardno")))
						.append(and("richclass = :richclass",hasKey(p,"richclass")))
						.append(and("flag = :flag",hasKey(p,"flag")))
						.append(and("branno = :branno",hasKey(p,"branno")))
						.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
						.append(and("channelno = :channelno",hasKey(p,"channelno")))
						.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
						.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
						.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
						.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
						.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
						.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
						.append(and("changedate = :changedate",hasKey(p,"changedate")))
						.append(and("changetime = :changetime",hasKey(p,"changetime")))
						.append(and("remark = :remark",hasKey(p,"remark")))
						.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
						.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
						.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
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
		String sql=Sql.create("select * from THFACCT where 1=1 ")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("richclass = :richclass",hasKey(p,"richclass")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("channelno = :channelno",hasKey(p,"channelno")))
				.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from THFACCT where 1=1 ")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("cardno = :cardno",hasKey(p,"cardno")))
				.append(and("richclass = :richclass",hasKey(p,"richclass")))
				.append(and("flag = :flag",hasKey(p,"flag")))
				.append(and("branno = :branno",hasKey(p,"branno")))
				.append(and("lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("channelno = :channelno",hasKey(p,"channelno")))
				.append(and("esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("changedate = :changedate",hasKey(p,"changedate")))
				.append(and("changetime = :changetime",hasKey(p,"changetime")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}
	public List<Map<String, Object>> queryForListById(Map<String,Object> p) {
		String sql=Sql.create("select * from THFACCT where 1=1")
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql=Sql.create("select t1.* from THFACCT t1 left join TCLIENT t2 on t1.cltnbr = t2.cltnbr where 1=1")
				.append(and("t1.richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("t1.cardno = :cardno",hasKey(p,"cardno")))
				.append(and("t1.richclass = :richclass",hasKey(p,"richclass")))
				.append(and("t1.flag = :flag",hasKey(p,"flag")))
				.append(and("t1.branno = :branno",hasKey(p,"branno")))
				.append(and("t1.lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("t1.channelno = :channelno",hasKey(p,"channelno")))
				.append(and("t1.esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("t1.esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("t1.regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("t1.regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("t1.cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("t1.fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("t1.changedate = :changedate",hasKey(p,"changedate")))
				.append(and("t1.changetime = :changetime",hasKey(p,"changetime")))
				.append(and("t1.remark = :remark",hasKey(p,"remark")))
				.append(and("t1.reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("t1.reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("t1.reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("t1.rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("t1.rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				;
		if(ObjectUtil.isNotEmpty(p.get("siteIds"))) {
			sql.append(and("t2.tlrclt in ("+ p.get("siteIds")+")"));
		}
		sql.append(orderBySql());
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	public List<Map<String, Object>> queryByclientIds(Map<String,Object> p) {
		Sql sql=Sql.create("select t1.richnbr,nvl(a.AUM,0) as AUM,t1.fircrtdate as opendate,t3.site_name,t4.brnnbr,t5.branch_name from THFACCT t1 " +
				"left join TCLIENT t2 on t1.cltnbr = t2.cltnbr " +
				"left join H_SITE t3 on t2.tlrclt = t3.site_no " +
				"left join (select sum(" + p.get("onlbal") + ") AUM,richnbr from TTIMPNT where 1=1 " +
				"and acctdate = "+p.get("yyyyMM")+"00 and accttype = 'A' group by richnbr) a on a.richnbr = t1.richnbr " +
				"left join TTIMPNT t4 on t1.richnbr = t4.richnbr and t4.accttype = 'A' "+
				"left join SYS_BRANCH t5 on t5.branch_id = substr(t4.brnnbr,3,4) where 1=1")
				.append(and("t1.richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("t1.cardno = :cardno",hasKey(p,"cardno")))
				.append(and("t1.richclass = :richclass",hasKey(p,"richclass")))
				.append(and("t1.flag = :flag",hasKey(p,"flag")))
				.append(and("t1.branno = :branno",hasKey(p,"branno")))
				.append(and("t1.lastdate = :lastdate",hasKey(p,"lastdate")))
				.append(and("t1.channelno = :channelno",hasKey(p,"channelno")))
				.append(and("t1.esbsysno = :esbsysno",hasKey(p,"esbsysno")))
				.append(and("t1.esbtrack = :esbtrack",hasKey(p,"esbtrack")))
				.append(and("t1.regbrn = :regbrn",hasKey(p,"regbrn")))
				.append(and("t1.regtlr = :regtlr",hasKey(p,"regtlr")))
				.append(and("t1.cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("t1.fircrtdate = :fircrtdate",hasKey(p,"fircrtdate")))
				.append(and("t1.changedate = :changedate",hasKey(p,"changedate")))
				.append(and("t1.changetime = :changetime",hasKey(p,"changetime")))
				.append(and("t1.remark = :remark",hasKey(p,"remark")))
				.append(and("t1.reserv20 = :reserv20",hasKey(p,"reserv20")))
				.append(and("t1.reserv30 = :reserv30",hasKey(p,"reserv30")))
				.append(and("t1.reserv40 = :reserv40",hasKey(p,"reserv40")))
				.append(and("t1.rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("t1.rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(and("t2.tlrclt = :tlrclt",hasKey(p,"tlrclt")));
		if(ObjectUtil.isNotEmpty(p.get("siteIds"))) {
			sql.append(and("t2.tlrclt in ("+ p.get("siteIds")+")"));
		}

		if (ObjectUtil.isNotEmpty(p.get("minDate")) && ObjectUtil.isNotEmpty(p.get("maxDate"))){
			sql.append(and("t1.fircrtdate between "+ p.get("minDate")+" and "+p.get("maxDate")));
		}
		sql.append(orderBySql());
		String sqlStr = sql.toString();
		if (ObjectUtil.isEmpty(p.get("export"))) {
		    String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
		    if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
			    long count = count("select count(*) from (" + sqlStr + ")  ", p);
			    PageInfo pageInf = MfpContextHolder.getPageInfo();
			    pageInf.setITotalDisplayRecords(count);
			    MfpContextHolder.setPageInfo(pageInf);
			    sqlStr = Sql.pageSqlInOracle(sql.append(" order by opendate desc").toString());
			    printSql(sqlStr, p);
			    return queryForList(sqlStr, p);
		    }else {
			    long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
			    PageInfo pageInf = MfpContextHolder.getPageInfo();
			    pageInf.setITotalDisplayRecords(count);
			    MfpContextHolder.setPageInfo(pageInf);
			    sql.append(" order by opendate desc").append(pageSql());
			    sqlStr = sql.toString();
			    printSql(sqlStr, p);
			    return queryForList(sqlStr, p);
		    }
		}
		return queryForList(sqlStr, p);
	}
	/**
	 * 一次性删除全表数据
	 * @author chenhao
	 */
	public int truncate(){
		return super.update("truncate table THFACCT", BaseUtils.map());
	}

	public List<Map<String, Object>> queryCltnbrByRichnbr(Map<String, Object> p) {
		Sql sql=Sql.create("select cltnbr from thfacct where 1=1 ")
				.append(and("richnbr = :richnbr"));
		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		return queryForList(sqlStr, p);
	}
}
