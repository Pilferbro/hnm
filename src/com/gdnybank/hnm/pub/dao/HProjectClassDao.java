package com.gdnybank.hnm.pub.dao;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:H_PROJECT_CLASS
 * 主键:
 * id
 **/
@Repository
public class HProjectClassDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","project_name","pj_classify","created","updatetime","source","isdeleted"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};
	
	@Override
	public int save(Map<String,Object> p) {			
				p.put("id",sequenceService.getTableFlowNo("H_PROJECT_CLASS", "id"));
		String sql=Sql.create("insert into H_PROJECT_CLASS (")
				.append(field("id "))
				.append(field("project_name ",hasKey(p,"project_name")))
				.append(field("pj_classify ",hasKey(p,"pj_classify")))
				.append(field("created ",hasKey(p,"created")))
				.append(field("updatetime ",hasKey(p,"updatetime")))
				.append(field("source ",hasKey(p,"source")))
				.append(field("isdeleted ",hasKey(p,"isdeleted")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":project_name ",hasKey(p,"project_name")))
				.append(field(":pj_classify ",hasKey(p,"pj_classify")))
				.append(field(":created ",hasKey(p,"created")))
				.append(field(":updatetime ",hasKey(p,"updatetime")))
				.append(field(":source ",hasKey(p,"source")))
				.append(field(":isdeleted ",hasKey(p,"isdeleted")))
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
		String sql=Sql.create("delete from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id"))
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
		String sql=Sql.create("delete from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}	

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_PROJECT_CLASS set ")
				.append(field("project_name = :project_name",hasKey(p,"project_name")))
				.append(field("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(field("created = :created",hasKey(p,"created")))
				.append(field("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(field("source = :source",hasKey(p,"source")))
				.append(field("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);	
		String sql=Sql.create("update H_PROJECT_CLASS set ")
				.append(field("project_name = :project_name",hasKey(p,"project_name")))
				.append(field("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(field("created = :created",hasKey(p,"created")))
				.append(field("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(field("source = :source",hasKey(p,"source")))
				.append(field("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
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
		String sql=Sql.create("select project_name,pj_classify from H_PROJECT_CLASS where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = 0 "))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {			
		Sql sql=Sql.create("select * from H_PROJECT_CLASS where 1=1")
			.append(and("id = :id",hasKey(p,"id")))
						.append(and("project_name = :project_name",hasKey(p,"project_name")))
						.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
						.append(and("created = :created",hasKey(p,"created")))
						.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
						.append(and("source = :source",hasKey(p,"source")))
						.append(and("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
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
		String sql=Sql.create("select * from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = 0",hasKey(p,"isdeleted")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = :isdeleted",hasKey(p,"isdeleted")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}


	public int updateByPJ(Map<String, Object> p) {

		String sql=Sql.create("update H_PROJECT_CLASS set ")
				.append(field("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(field("source = :source",hasKey(p,"source")))
				.append(field("isdeleted = 1"))
				.append(" where 1=1 ")
				.append(and("pj_classify = :pj_classify"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	public long countByNmae(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = 0"))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public List<Map<String, Object>> queryByname(Map<String, Object> p) {
		String sql = Sql.create("select pj_classify from H_PROJECT_CLASS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("project_name = :project_name",hasKey(p,"project_name")))
				.append(and("pj_classify = :pj_classify",hasKey(p,"pj_classify")))
				.append(and("created = :created",hasKey(p,"created")))
				.append(and("updatetime = :updatetime",hasKey(p,"updatetime")))
				.append(and("source = :source",hasKey(p,"source")))
				.append(and("isdeleted = 0"))
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}
}
