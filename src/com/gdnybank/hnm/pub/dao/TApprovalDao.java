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
 * 表名:T_APPROVAL
 * 主键:
 * id
 **/
@Repository
public class TApprovalDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","approval_type","approval_process_id","approval_process","odd_approval_process","apply_id","next_role","approval_role","approval_user","create_time","update_time","gt_approval_user"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
				p.put("id",sequenceService.getTableFlowNo("T_APPROVAL", "id"));
		String sql=Sql.create("insert into T_APPROVAL (")
				.append(field("id "))
				.append(field("approval_type ",hasKey(p,"approval_type")))
				.append(field("approval_process_id ",hasKey(p,"approval_process_id")))
				.append(field("approval_process ",hasKey(p,"approval_process")))
				.append(field("odd_approval_process ",hasKey(p,"odd_approval_process")))
				.append(field("apply_id ",hasKey(p,"apply_id")))
				.append(field("next_role ",hasKey(p,"next_role")))
				.append(field("approval_role ",hasKey(p,"approval_role")))
				.append(field("approval_user ",hasKey(p,"approval_user")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("update_time ",hasKey(p,"update_time")))
				.append(field("gt_approval_user ",hasKey(p,"gt_approval_user")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":approval_type ",hasKey(p,"approval_type")))
				.append(field(":approval_process_id ",hasKey(p,"approval_process_id")))
				.append(field(":approval_process ",hasKey(p,"approval_process")))
				.append(field(":odd_approval_process ",hasKey(p,"odd_approval_process")))
				.append(field(":apply_id ",hasKey(p,"apply_id")))
				.append(field(":next_role ",hasKey(p,"next_role")))
				.append(field(":approval_role ",hasKey(p,"approval_role")))
				.append(field(":approval_user ",hasKey(p,"approval_user")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":update_time ",hasKey(p,"update_time")))
				.append(field(":gt_approval_user ",hasKey(p,"gt_approval_user")))
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
		String sql=Sql.create("delete from T_APPROVAL where 1=1 ")
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
		String sql=Sql.create("delete from T_APPROVAL where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("next_role = :next_role",hasKey(p,"next_role")))
				.append(and("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update T_APPROVAL set ")
				.append(field("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(field("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(field("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(field("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(field("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(field("next_role = :next_role",hasKey(p,"next_role")))
				.append(field("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(field("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
	public int updateApplyId(Map<String,Object> p) {
		String sql=Sql.create("update T_APPROVAL set ")
				.append(field("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(field("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(field("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(field("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(field("next_role = :next_role",hasKey(p,"next_role")))
				.append(field("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(field("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(" where 1=1 ")
				.append(and("apply_id = :apply_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update T_APPROVAL set ")
				.append(field("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(field("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(field("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(field("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(field("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(field("next_role = :next_role",hasKey(p,"next_role")))
				.append(field("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(field("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("next_role = :next_role",hasKey(p,"next_role")))
				.append(and("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String,Object> p) {

		return count(p) > 0 ? updateApplyId(p) : save(p);
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select t1.*,t2.name approval_user_name from T_APPROVAL t1 left join sys_account t2 on t1.approval_user = t2.account_id where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("t1.approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("t1.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("t1.odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("t1.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("t1.next_role = :next_role",hasKey(p,"next_role")))
				.append(and("t1.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("t1.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from T_APPROVAL where 1=1")
			.append(and("id = :id",hasKey(p,"id")))
						.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
						.append(and("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
						.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
						.append(and("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
						.append(and("apply_id = :apply_id",hasKey(p,"apply_id")))
						.append(and("next_role = :next_role",hasKey(p,"next_role")))
						.append(and("approval_role = :approval_role",hasKey(p,"approval_role")))
						.append(and("approval_user = :approval_user",hasKey(p,"approval_user")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
						.append(and("update_time = :update_time",hasKey(p,"update_time")))
						.append(and("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
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
		String sql=Sql.create("select * from T_APPROVAL where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("next_role = :next_role",hasKey(p,"next_role")))
				.append(and("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from T_APPROVAL where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("next_role = :next_role",hasKey(p,"next_role")))
				.append(and("approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public List<Map<String, Object>> queryForListAndApply(Map<String,Object> p) {
		String sql=Sql.create("select a.*,b.approval_status,b.operator,b.relation_id from T_APPROVAL a left join T_APPROVAL_APPLY b on a.apply_id=b.id where 1=1 ")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("a.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("a.odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.next_role = :next_role",hasKey(p,"next_role")))
				.append(and("a.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("a.update_time like :date",hasKey(p,"date")))
				.append(and("a.gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public int saveById(Map<String,Object> p) {
		//p.put("id",sequenceService.getTableFlowNo("T_APPROVAL", "id"));
		String sql=Sql.create("insert into T_APPROVAL (")
				.append(field("id ",hasKey(p,"id")))
				.append(field("approval_type ",hasKey(p,"approval_type")))
				.append(field("approval_process_id ",hasKey(p,"approval_process_id")))
				.append(field("approval_process ",hasKey(p,"approval_process")))
				.append(field("odd_approval_process ",hasKey(p,"odd_approval_process")))
				.append(field("apply_id ",hasKey(p,"apply_id")))
				.append(field("next_role ",hasKey(p,"next_role")))
				.append(field("approval_role ",hasKey(p,"approval_role")))
				.append(field("approval_user ",hasKey(p,"approval_user")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("update_time ",hasKey(p,"update_time")))
				.append(field("gt_approval_user ",hasKey(p,"gt_approval_user")))
				.append(") values (")
				.append(field(":id ",hasKey(p,"id")))
				.append(field(":approval_type ",hasKey(p,"approval_type")))
				.append(field(":approval_process_id ",hasKey(p,"approval_process_id")))
				.append(field(":approval_process ",hasKey(p,"approval_process")))
				.append(field(":odd_approval_process ",hasKey(p,"odd_approval_process")))
				.append(field(":apply_id ",hasKey(p,"apply_id")))
				.append(field(":next_role ",hasKey(p,"next_role")))
				.append(field(":approval_role ",hasKey(p,"approval_role")))
				.append(field(":approval_user ",hasKey(p,"approval_user")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":update_time ",hasKey(p,"update_time")))
				.append(field(":gt_approval_user ",hasKey(p,"gt_approval_user")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}

	/**
	 * 获取待审批列表
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListBycondition1(Map<String,Object> p) {
		Sql sql=Sql.create("select a.id,a.next_role,a.approval_type,a.apply_id,a.approval_role,a.approval_user,a.gt_approval_user,b.create_time,b.relation_id," +
				" b.operator,b.approval_status,b.approval_status_name,c.approval_type_name,d.branch_id,d.name,e.branch_name from T_APPROVAL a left join T_APPROVAL_APPLY b on a.apply_id = b.id" +
				" left join T_APPROVAL_TYPE c on a.approval_type = c.approval_type left join SYS_ACCOUNT d on b.operator = d.account_id left join SYS_BRANCH e on d.branch_id = e.branch_id where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("a.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("a.odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.next_role = :next_role",hasKey(p,"next_role")))
				.append(and("a.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("b.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("b.create_time <= :end_date",hasKey(p,"end_date")))
				.append(and("a.gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("b.operator = :operator",hasKey(p,"operator")))
				.append(and("a.approval_user is not null"))
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
			sqlStr=pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by a.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 获取已审批列表
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListBycondition2(Map<String,Object> p) {
		Sql sql=Sql.create("select a.apply_id,a.id,a.approval_type,a.approval_role,a.approval_user,b.create_time,b.relation_id," +
				" b.operator,b.approval_status,b.approval_status_name,c.approval_type_name,d.branch_id,d.name,e.branch_name,f.gt_approval_user from T_APPROVAL_HIS a left join T_APPROVAL_APPLY b on a.apply_id = b.id" +
				" left join T_APPROVAL_TYPE c on a.approval_type = c.approval_type left join SYS_ACCOUNT d on b.operator = d.account_id left join SYS_BRANCH e on d.branch_id = e.branch_id " +
				" left join T_APPROVAL f on a.apply_id = f.apply_id where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.approval_opinion = :approval_opinion",hasKey(p,"approval_opinion")))
				.append(and("a.approval_desc = :approval_desc",hasKey(p,"approval_desc")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("b.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("b.create_time <= :end_date",hasKey(p,"end_date")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("b.operator = :operator",hasKey(p,"operator")))
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
			sqlStr=pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by a.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 获取待沟通列表
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForConnectListBycondition1(Map<String,Object> p) {
		Sql sql=Sql.create("select a.id,a.next_role,a.approval_type,a.apply_id,a.approval_role,a.approval_user,b.create_time,b.relation_id," +
				" b.operator,b.approval_status,b.approval_status_name,c.approval_type_name,d.branch_id,d.name,e.branch_name,f.name approval_user_name from T_APPROVAL a left join T_APPROVAL_APPLY b on a.apply_id = b.id" +
				" left join T_APPROVAL_TYPE c on a.approval_type = c.approval_type left join SYS_ACCOUNT d on b.operator = d.account_id left join SYS_BRANCH e on d.branch_id = e.branch_id left join SYS_ACCOUNT f on a.approval_user = f.account_id where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("a.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("a.odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.next_role = :next_role",hasKey(p,"next_role")))
				.append(and("a.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("b.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("b.create_time <= :end_date",hasKey(p,"end_date")))
				.append(and("a.gt_approval_user like :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("b.operator = :operator",hasKey(p,"operator")))
				.append(and("a.approval_user is not null"))
				.append(and("a.gt_approval_user is not null"))
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
			sqlStr=pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by a.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 获取已沟通列表
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForConnectListBycondition2(Map<String,Object> p) {
		Sql sql=Sql.create("select a.apply_id,a.id,a.approval_type,a.approval_user,b.create_time,b.relation_id," +
				" b.operator,b.approval_status,b.approval_status_name,c.approval_type_name,d.branch_id,d.name,e.branch_name,f.name approval_user_name from T_APPROVAL_CONNECT_HIS a left join T_APPROVAL_APPLY b on a.apply_id = b.id" +
				" left join T_APPROVAL_TYPE c on a.approval_type = c.approval_type left join SYS_ACCOUNT d on b.operator = d.account_id left join SYS_BRANCH e on d.branch_id = e.branch_id left join SYS_ACCOUNT f on a.approval_user = f.account_id where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(and("a.approval_opinion = :approval_opinion",hasKey(p,"approval_opinion")))
				.append(and("a.approval_desc = :approval_desc",hasKey(p,"approval_desc")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("b.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("b.create_time <= :end_date",hasKey(p,"end_date")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("b.operator = :operator",hasKey(p,"operator")))
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
			sqlStr=pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by a.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	public List<Map<String, Object>> queryByApprovalUser(Map<String, Object> p) {

		Sql sql=Sql.create("select a.id,a.next_role,a.approval_type,a.apply_id,a.approval_role,a.approval_user,a.gt_approval_user,b.create_time,b.relation_id," +
				" b.operator,b.approval_status,b.approval_status_name,c.approval_type_name,d.branch_id,d.name,e.branch_name from T_APPROVAL a left join T_APPROVAL_APPLY b on a.apply_id = b.id" +
				" left join T_APPROVAL_TYPE c on a.approval_type = c.approval_type left join SYS_ACCOUNT d on b.operator = d.account_id left join SYS_BRANCH e on d.branch_id = e.branch_id where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("a.approval_process_id = :approval_process_id",hasKey(p,"approval_process_id")))
				.append(and("a.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("a.odd_approval_process = :odd_approval_process",hasKey(p,"odd_approval_process")))
				.append(and("a.apply_id = :apply_id",hasKey(p,"apply_id")))
				.append(and("a.next_role = :next_role",hasKey(p,"next_role")))
				.append(and("a.approval_role = :approval_role",hasKey(p,"approval_role")))
				.append(and("a.approval_user = :approval_user",hasKey(p,"approval_user")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("b.create_time >= :start_date",hasKey(p,"start_date")))
				.append(and("b.create_time <= :end_date",hasKey(p,"end_date")))
				.append(and("a.gt_approval_user = :gt_approval_user",hasKey(p,"gt_approval_user")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("b.operator = :operator",hasKey(p,"operator")))
				.append(and("a.approval_user is not null"))
				;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}
}