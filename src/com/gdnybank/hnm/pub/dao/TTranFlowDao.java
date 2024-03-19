package com.gdnybank.hnm.pub.dao;

import static com.nantian.mfp.framework.dao.sql.Sql.and;
import static com.nantian.mfp.framework.dao.sql.Sql.field;
import static com.nantian.mfp.framework.dao.sql.Sql.orderBySql;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSql;
import static com.nantian.mfp.framework.dao.sql.Sql.printSql;
import static com.nantian.mfp.framework.dao.sql.Sql.hasKey;

import java.util.List;
import java.util.Map;

import com.nantian.mfp.framework.context.MfpContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:T_TRAN_FLOW
 * 主键:
 * seq_id
 **/
@Repository
public class   TTranFlowDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"seq_id","busi_seq_no","esb_seq_no","txcode","esb_service_code","esb_service_scene","serv_seq_no","user_id","auth_user_id","branch_id","trandate","trantime","status","errmsg"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"seq_id"};

	@Override
	public int save(Map<String,Object> p) {
//				p.put("seq_id",sequenceService.getTableFlowNo("T_TRAN_FLOW", "seq_id"));
		String sql=Sql.create("insert into T_TRAN_FLOW (")
				.append(field("seq_id "))
				.append(field("busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(field("esb_seq_no ",hasKey(p,"esb_seq_no")))
				.append(field("txcode ",hasKey(p,"txcode")))
				.append(field("esb_service_code ",hasKey(p,"esb_service_code")))
				.append(field("esb_service_scene ",hasKey(p,"esb_service_scene")))
				.append(field("serv_seq_no ",hasKey(p,"serv_seq_no")))
				.append(field("user_id ",hasKey(p,"user_id")))
				.append(field("auth_user_id ",hasKey(p,"auth_user_id")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
				.append(field("trandate ",hasKey(p,"trandate")))
				.append(field("trantime ",hasKey(p,"trantime")))
				.append(field("status ",hasKey(p,"status")))
				.append(field("errmsg ",hasKey(p,"errmsg")))
				.append(") values (")
				.append(field(":seq_id "))
				.append(field(":busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(field(":esb_seq_no ",hasKey(p,"esb_seq_no")))
				.append(field(":txcode ",hasKey(p,"txcode")))
				.append(field(":esb_service_code ",hasKey(p,"esb_service_code")))
				.append(field(":esb_service_scene ",hasKey(p,"esb_service_scene")))
				.append(field(":serv_seq_no ",hasKey(p,"serv_seq_no")))
				.append(field(":user_id ",hasKey(p,"user_id")))
				.append(field(":auth_user_id ",hasKey(p,"auth_user_id")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
				.append(field(":trandate ",hasKey(p,"trandate")))
				.append(field(":trantime ",hasKey(p,"trantime")))
				.append(field(":status ",hasKey(p,"status")))
				.append(field(":errmsg ",hasKey(p,"errmsg")))
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
		String sql=Sql.create("delete from T_TRAN_FLOW where 1=1 ")
				.append(and("seq_id = :seq_id"))
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
		String sql=Sql.create("delete from T_TRAN_FLOW where 1=1 ")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(and("user_id = :user_id",hasKey(p,"user_id")))
				.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("trantime = :trantime",hasKey(p,"trantime")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update T_TRAN_FLOW set ")
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(field("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(field("txcode = :txcode",hasKey(p,"txcode")))
				.append(field("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(field("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(field("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(field("user_id = :user_id",hasKey(p,"user_id")))
				.append(field("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("trandate = :trandate",hasKey(p,"trandate")))
				.append(field("trantime = :trantime",hasKey(p,"trantime")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("errmsg = :errmsg",hasKey(p,"errmsg")))
				.append(" where 1=1 ")
				.append(and("seq_id = :seq_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update T_TRAN_FLOW set ")
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(field("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(field("txcode = :txcode",hasKey(p,"txcode")))
				.append(field("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(field("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(field("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(field("user_id = :user_id",hasKey(p,"user_id")))
				.append(field("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("trandate = :trandate",hasKey(p,"trandate")))
				.append(field("trantime = :trantime",hasKey(p,"trantime")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("errmsg = :errmsg",hasKey(p,"errmsg")))
				.append(" where 1=1 ")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(and("user_id = :user_id",hasKey(p,"user_id")))
				.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("trantime = :trantime",hasKey(p,"trantime")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}


	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select * from T_TRAN_FLOW where 1=1")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(and("user_id = :user_id",hasKey(p,"user_id")))
				.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("trantime = :trantime",hasKey(p,"trantime")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from T_TRAN_FLOW where 1=1")
			.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
						.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
						.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
						.append(and("txcode = :txcode",hasKey(p,"txcode")))
						.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
						.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
						.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
						.append(and("user_id = :user_id",hasKey(p,"user_id")))
						.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
						.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
						.append(and("trandate = :trandate",hasKey(p,"trandate")))
						.append(and("trantime = :trantime",hasKey(p,"trantime")))
						.append(and("status = :status",hasKey(p,"status")))
						.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
		PageInfo  pageInf = MfpContextHolder.getPageInfo();
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
		String sql=Sql.create("select * from T_TRAN_FLOW where 1=1 ")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(and("user_id = :user_id",hasKey(p,"user_id")))
				.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("trantime = :trantime",hasKey(p,"trantime")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from T_TRAN_FLOW where 1=1 ")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("esb_seq_no = :esb_seq_no",hasKey(p,"esb_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("esb_service_code = :esb_service_code",hasKey(p,"esb_service_code")))
				.append(and("esb_service_scene = :esb_service_scene",hasKey(p,"esb_service_scene")))
				.append(and("serv_seq_no = :serv_seq_no",hasKey(p,"serv_seq_no")))
				.append(and("user_id = :user_id",hasKey(p,"user_id")))
				.append(and("auth_user_id = :auth_user_id",hasKey(p,"auth_user_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("trandate = :trandate",hasKey(p,"trandate")))
				.append(and("trantime = :trantime",hasKey(p,"trantime")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("errmsg = :errmsg",hasKey(p,"errmsg")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}
	@Override
	public int saveOrUpdate(Map<String, Object> p) {
		if(countByPk(p)>0){
			return this.updateByPk(p);
		}else{
			return this.save(p);
		}
	}
	private long countByPk(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from T_TRAN_FLOW where 1=1 ")
				.append(and("seq_id = :seq_id",hasKey(p,"seq_id")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}



}
