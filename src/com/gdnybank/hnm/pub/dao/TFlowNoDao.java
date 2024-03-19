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
 * 表名:T_FLOW_NO
 * 主键:
 * account_id
 **/
@Repository
public class TFlowNoDao extends TXBaseDao {

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"account_id","trandate","busi_seq_no"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"account_id"};
	
	@Override
	public int save(Map<String,Object> p) {			
		String sql= Sql.create("insert into T_FLOW_NO (")
				.append(field("account_id "))
				.append(field("trandate ",hasKey(p,"trandate")))
				.append(field("busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(") values (")
				.append(field(":account_id "))
				.append(field(":trandate ",hasKey(p,"trandate")))
				.append(field(":busi_seq_no ",hasKey(p,"busi_seq_no")))
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
		String sql= Sql.create("delete from T_FLOW_NO where 1=1 ")
				.append(and("account_id = :account_id"))
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
		String sql= Sql.create("delete from T_FLOW_NO where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}	

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update T_FLOW_NO set ")
				.append(field("trandate = :trandate",hasKey(p,"trandate")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);	
		String sql= Sql.create("update T_FLOW_NO set ")
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
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
		String sql= Sql.create("select * from T_FLOW_NO where 1=1")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {			
		Sql sql= Sql.create("select * from T_FLOW_NO where 1=1")
			.append(and("account_id = :account_id",hasKey(p,"account_id")))
						.append(and("trandate = :trandate",hasKey(p,"trandate")))
						.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
			;
		
		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
		PageInfo pageInf = MfpContextHolder.getPageInfo();
		pageInf.setITotalDisplayRecords(count);
		MfpContextHolder.setPageInfo(pageInf);
		sql.append(orderBySql()).append(pageSql());
		sqlStr=sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
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
		String sql= Sql.create("select * from T_FLOW_NO where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from T_FLOW_NO where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}

	
}
