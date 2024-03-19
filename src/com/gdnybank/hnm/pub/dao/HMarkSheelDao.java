package com.gdnybank.hnm.pub.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:H_MARK_SHEEL
 * 主键:
 * mark_sheet_id
 **/
@Repository
public class HMarkSheelDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"mark_sheet_id","content","seq_num","parent_id","mlevel","score"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"mark_sheet_id"};
	
	@Override
	public int save(Map<String,Object> p) {			
				p.put("mark_sheet_id",sequenceService.getTableFlowNo("H_MARK_SHEEL", "mark_sheet_id"));
		String sql=Sql.create("insert into H_MARK_SHEEL (")
				.append(field("mark_sheet_id "))
				.append(field("content ",hasKey(p,"content")))
				.append(field("seq_num ",hasKey(p,"seq_num")))
				.append(field("parent_id ",hasKey(p,"parent_id")))
				.append(field("mlevel ",hasKey(p,"mlevel")))
				.append(field("score ",hasKey(p,"score")))
				.append(") values (")
				.append(field(":mark_sheet_id "))
				.append(field(":content ",hasKey(p,"content")))
				.append(field(":seq_num ",hasKey(p,"seq_num")))
				.append(field(":parent_id ",hasKey(p,"parent_id")))
				.append(field(":mlevel ",hasKey(p,"mlevel")))
				.append(field(":score ",hasKey(p,"score")))
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
		String sql=Sql.create("delete from H_MARK_SHEEL where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id"))
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
		String sql=Sql.create("delete from H_MARK_SHEEL where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
				.append(and("content = :content",hasKey(p,"content")))
				.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(and("score = :score",hasKey(p,"score")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}	

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_MARK_SHEEL set ")
				.append(field("content = :content",hasKey(p,"content")))
				.append(field("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(field("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(field("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(field("score = :score",hasKey(p,"score")))
				.append(" where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);	
		String sql=Sql.create("update H_MARK_SHEEL set ")
				.append(field("content = :content",hasKey(p,"content")))
				.append(field("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(field("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(field("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(field("score = :score",hasKey(p,"score")))
				.append(" where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
				.append(and("content = :content",hasKey(p,"content")))
				.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(and("score = :score",hasKey(p,"score")))
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
		String sql=Sql.create("select * from H_MARK_SHEEL where 1=1")
				.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
				.append(and("content = :content",hasKey(p,"content")))
				.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(and("score = :score",hasKey(p,"score")))
				.append(" order by mark_sheel_id")
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {			
		Sql sql=Sql.create("select * from H_MARK_SHEEL where 1=1")
			.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
						.append(and("content = :content",hasKey(p,"content")))
						.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
						.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
						.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
						.append(and("score = :score",hasKey(p,"score")))
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
		String sql=Sql.create("select * from H_MARK_SHEEL where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
				.append(and("content = :content",hasKey(p,"content")))
				.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(and("score = :score",hasKey(p,"score")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_MARK_SHEEL where 1=1 ")
				.append(and("mark_sheet_id = :mark_sheet_id",hasKey(p,"mark_sheet_id")))
				.append(and("content = :content",hasKey(p,"content")))
				.append(and("seq_num = :seq_num",hasKey(p,"seq_num")))
				.append(and("parent_id = :parent_id",hasKey(p,"parent_id")))
				.append(and("mlevel = :mlevel",hasKey(p,"mlevel")))
				.append(and("score = :score",hasKey(p,"score")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}

	
}
