package com.gdnybank.hnm.pub.dao;

import static com.nantian.mfp.framework.dao.sql.Sql.and;
import static com.nantian.mfp.framework.dao.sql.Sql.field;
import static com.nantian.mfp.framework.dao.sql.Sql.orderBySql;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSql;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSqlInOracle;
import static com.nantian.mfp.framework.dao.sql.Sql.printSql;
import static com.nantian.mfp.framework.dao.sql.Sql.hasKey;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:sys_datadictionary
 * 主键:
 * dd_dictname
 * dd_id
 **/
@Repository
public class SysDatadictionaryDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"dd_dictname","dd_id","dd_text","dd_desc","dd_order","dd_enabled"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"dd_dictname","dd_id"};

	@Override
	public int save(Map<String,Object> p) {
				//p.put("dd_dictname",sequenceService.getTableFlowNo("sys_datadictionary", "dd_dictname"));
				//p.put("dd_id",sequenceService.getTableFlowNo("sys_datadictionary", "dd_id"));
		String sql=Sql.create("insert into sys_datadictionary (")
				.append(field("dd_dictname "))
				.append(field("dd_id ",hasKey(p,"dd_id")))
				.append(field("dd_text ",hasKey(p,"dd_text")))
				.append(field("dd_desc ",hasKey(p,"dd_desc")))
				.append(field("dd_order ",hasKey(p,"dd_order")))
				.append(field("dd_enabled ",hasKey(p,"dd_enabled")))
				.append(") values (")
				.append(field(":dd_dictname "))
				.append(field(":dd_id ",hasKey(p,"dd_id")))
				.append(field(":dd_text ",hasKey(p,"dd_text")))
				.append(field(":dd_desc ",hasKey(p,"dd_desc")))
				.append(field(":dd_order ",hasKey(p,"dd_order")))
				.append(field(":dd_enabled ",hasKey(p,"dd_enabled")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}
	public List<Map<String,Object>> queryForMax(Map<String,Object> p){
		String sql="select max(dd_id) as maxdd_id,max(dd_flag) from sys_datadictionary where dd_dictname=:dd_dictname";
		List<Map<String, Object>> list = queryForList(sql, p);
		return list;
	}
	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String,Object> p) {
		String sql=Sql.create("delete from sys_datadictionary where 1=1 ")
				.append(and("dd_dictname = :dd_dictname"))
				.append(and("dd_id = :dd_id"))
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
		String sql=Sql.create("delete from sys_datadictionary where 1=1 ")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update sys_datadictionary set ")
				.append(field("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(field("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(field("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(field("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(field("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(" where 1=1 ")
				.append(and("dd_dictname = :dd_dictname"))
				.append(and("dd_id = :dd_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update sys_datadictionary set ")
				.append(field("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(field("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(field("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(field("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(field("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(" where 1=1 ")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
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
		String sql=Sql.create("select * from sys_datadictionary where 1=1")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForListWithoutorderby(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_datadictionary where 1=1")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryCusCategary(Map<String,Object> p) {
		String sql=Sql.create("select dd_id as appclassid,dd_text as typename from sys_datadictionary where 1=1")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForId(Map<String,Object> p) {
		String sql=Sql.create("select dd_id from sys_datadictionary where 1=1")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForNameList(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_datadictionary where 1=1")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from sys_datadictionary where 1=1")
			.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
						.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
						.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
						.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
						.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
						.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.toString());sqlStr=sqlStr +" " +orderBySql();
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

	public List<Map<String, Object>> queryDataByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select dd_dictname,dd_id,dd_text,dd_desc,dd_enabled from sys_datadictionary where 1=1")
			.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
						.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
						.append(and("dd_text like :dd_text",hasKey(p,"dd_text")))
						.append(and("dd_desc like :dd_desc",hasKey(p,"dd_desc")))
						.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
						.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)){
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
		String sql=Sql.create("select * from sys_datadictionary where 1=1 ")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from sys_datadictionary where 1=1 ")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public int updatebyname(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update sys_datadictionary set ")
				.append(field("dd_id = :dd_id",hasKey(p,"dd_id")))
				.append(field("dd_text = :dd_text",hasKey(p,"dd_text")))
				.append(field("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(field("dd_order = :dd_order",hasKey(p,"dd_order")))
				.append(field("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.append(" where 1=1 ")
				.append(and("dd_dictname = :dd_dictname",hasKey(p,"dd_dictname")))
//				.append(and("dd_id = :dd_id",hasKey(p,"dd_id")))
//				.append(and("dd_text = :dd_text",hasKey(p,"dd_text")))
//				.append(and("dd_desc = :dd_desc",hasKey(p,"dd_desc")))
				.append(and("dd_flag = :dd_flag",hasKey(p,"dd_flag")))
//				.append(and("dd_order = :dd_order",hasKey(p,"dd_order")))
//				.append(and("dd_enabled = :dd_enabled",hasKey(p,"dd_enabled")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

}
