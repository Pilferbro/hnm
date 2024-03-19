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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:H_SITE_WARN_RECORD
 * 主键:
 * id
 **/
@Repository
public class HSiteWarnRecordDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","site_id","site_no","site_name","site_status","site_step","branch_id","mgr_id","phone","send_msg","push_msg","create_time"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
				p.put("id",sequenceService.getTableFlowNo("H_SITE_WARN_RECORD", "id"));
		String sql=Sql.create("insert into H_SITE_WARN_RECORD (")
				.append(field("id "))
				.append(field("site_id ",hasKey(p,"site_id")))
				.append(field("site_no ",hasKey(p,"site_no")))
				.append(field("site_name ",hasKey(p,"site_name")))
				.append(field("site_status ",hasKey(p,"site_status")))
				.append(field("site_step ",hasKey(p,"site_step")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
				.append(field("mgr_id ",hasKey(p,"mgr_id")))
				.append(field("phone ",hasKey(p,"phone")))
				.append(field("send_msg ",hasKey(p,"send_msg")))
				.append(field("push_msg ",hasKey(p,"push_msg")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":site_id ",hasKey(p,"site_id")))
				.append(field(":site_no ",hasKey(p,"site_no")))
				.append(field(":site_name ",hasKey(p,"site_name")))
				.append(field(":site_status ",hasKey(p,"site_status")))
				.append(field(":site_step ",hasKey(p,"site_step")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
				.append(field(":mgr_id ",hasKey(p,"mgr_id")))
				.append(field(":phone ",hasKey(p,"phone")))
				.append(field(":send_msg ",hasKey(p,"send_msg")))
				.append(field("push_msg ",hasKey(p,"push_msg")))
				.append(field(":create_time ",hasKey(p,"create_time")))
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
		String sql=Sql.create("delete from H_SITE_WARN_RECORD where 1=1 ")
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
		String sql=Sql.create("delete from H_SITE_WARN_RECORD where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_SITE_WARN_RECORD set ")
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("site_no = :site_no",hasKey(p,"site_no")))
				.append(field("site_name = :site_name",hasKey(p,"site_name")))
				.append(field("site_status = :site_status",hasKey(p,"site_status")))
				.append(field("site_step = :site_step",hasKey(p,"site_step")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(field("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_SITE_WARN_RECORD set ")
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("site_no = :site_no",hasKey(p,"site_no")))
				.append(field("site_name = :site_name",hasKey(p,"site_name")))
				.append(field("site_status = :site_status",hasKey(p,"site_status")))
				.append(field("site_step = :site_step",hasKey(p,"site_step")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(field("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
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
		String sql=Sql.create("select * from H_SITE_WARN_RECORD where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from H_SITE_WARN_RECORD where 1=1")
			.append(and("id = :id",hasKey(p,"id")))
						.append(and("site_id = :site_id",hasKey(p,"site_id")))
						.append(and("site_no = :site_no",hasKey(p,"site_no")))
						.append(and("site_name = :site_name",hasKey(p,"site_name")))
						.append(and("site_status = :site_status",hasKey(p,"site_status")))
						.append(and("site_step = :site_step",hasKey(p,"site_step")))
						.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
						.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
						.append(and("phone = :phone",hasKey(p,"phone")))
						.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
						.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
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
		String sql=Sql.create("select * from H_SITE_WARN_RECORD where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_SITE_WARN_RECORD where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	//批量插入方法
	public int[] saveList(List<Map<String,Object>> paramlist) {

		Sql sql= Sql.create("");
		sql.append("insert into H_SITE_WARN_RECORD (")
				.append(field("id "))
				.append(field("site_id "))
				.append(field("site_no "))
				.append(field("site_name "))
				.append(field("site_status "))
				.append(field("site_step "))
				.append(field("branch_id "))
				.append(field("mgr_id "))
				.append(field("phone "))
				.append(field("send_msg "))
				.append(field("push_msg "))
				.append(field("create_time "))
				.append(") values (")
				.append(field(":id "))
				.append(field(":site_id "))
				.append(field(":site_no "))
				.append(field(":site_name "))
				.append(field(":site_status "))
				.append(field(":site_step "))
				.append(field(":branch_id "))
				.append(field(":mgr_id "))
				.append(field(":phone "))
				.append(field(":send_msg "))
				.append(field(":push_msg "))
				.append(field(":create_time "))
				.append(")");

		return batchUpdate(sql.toString(), paramlist);
	}

	public List<Map<String, Object>> queryForListByconditionByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select t1.*,t3.branch_name,t4.branch_name org_name from H_SITE_WARN_RECORD t1 left join sys_account t2 on t1.mgr_id = t2.account_id " +
				" left join SYS_BRANCH t3 on t1.branch_id = t3.branch_id left join SYS_BRANCH t4 on t2.branch_id = t4.branch_id " +
				" where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.site_id = :site_id",hasKey(p,"site_id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name like :site_name",hasKey(p,"site_name")))
				.append(and("t1.site_status = :site_status",hasKey(p,"site_status")))
				.append(and("t1.site_step = :site_step",hasKey(p,"site_step")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("t1.push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("t1.create_time <= :end_date",hasKey(p,"end_date")))
				;

		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("t2.branch_id in ("+ p.get("orgids")+")"));
		}

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.append(" order by t1.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by t1.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 最近的提醒时间记录
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListByMax(Map<String,Object> p) {
		String sql=Sql.create("select * from H_SITE_WARN_RECORD where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				//.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(" and create_time = (select max(CREATE_TIME) from H_SITE_WARN_RECORD where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("site_status = :site_status",hasKey(p,"site_status")))
				.append(and("site_step = :site_step",hasKey(p,"site_step")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("send_msg = :send_msg",hasKey(p,"send_msg")))
				.append(and("push_msg = :push_msg",hasKey(p,"push_msg")))
				.append(")")
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

}
