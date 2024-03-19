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
 * 表名:T_APPROVAL_PROCESS
 * 主键:
 * id
 **/
@Repository
public class TApprovalProcessDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","approval_process","approval_process_name","approval_type","remarks","branch_id","create_time","update_time","new_approval_process"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
				p.put("id",sequenceService.getTableFlowNo("T_APPROVAL_PROCESS", "id"));
		String sql=Sql.create("insert into T_APPROVAL_PROCESS (")
				.append(field("id "))
				.append(field("approval_process ",hasKey(p,"approval_process")))
				.append(field("approval_process_name ",hasKey(p,"approval_process_name")))
				.append(field("approval_type ",hasKey(p,"approval_type")))
				.append(field("remarks ",hasKey(p,"remarks")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("update_time ",hasKey(p,"update_time")))
				.append(field("new_approval_process ",hasKey(p,"new_approval_process")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":approval_process ",hasKey(p,"approval_process")))
				.append(field(":approval_process_name ",hasKey(p,"approval_process_name")))
				.append(field(":approval_type ",hasKey(p,"approval_type")))
				.append(field(":remarks ",hasKey(p,"remarks")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":update_time ",hasKey(p,"update_time")))
				.append(field(":new_approval_process ",hasKey(p,"new_approval_process")))
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
		String sql=Sql.create("delete from T_APPROVAL_PROCESS where 1=1 ")
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
		String sql=Sql.create("delete from T_APPROVAL_PROCESS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update T_APPROVAL_PROCESS set ")
				.append(field("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(field("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(field("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(field("remarks = :remarks",hasKey(p,"remarks")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update T_APPROVAL_PROCESS set ")
				.append(field("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(field("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(field("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(field("remarks = :remarks",hasKey(p,"remarks")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
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
		String sql=Sql.create("select t1.*,t2.branch_name from T_APPROVAL_PROCESS t1 left join SYS_BRANCH t2 on t1.branch_id = t2.branch_id where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("t1.approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("t1.approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from T_APPROVAL_PROCESS where 1=1")
			.append(and("id = :id",hasKey(p,"id")))
						.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
						.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
						.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
						.append(and("remarks = :remarks",hasKey(p,"remarks")))
						.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
						.append(and("update_time = :update_time",hasKey(p,"update_time")))
						.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
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
		String sql=Sql.create("select * from T_APPROVAL_PROCESS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from T_APPROVAL_PROCESS where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	//wxm:模糊查询
			public List<Map<String, Object>> queryForListBycondition(Map<String,Object> p) {
				Sql sql=Sql.create("select a.id,a.approval_process,a.new_approval_process,a.approval_process_name,a.approval_type,b.approval_type_name,a.remarks,"
						+ "a.branch_id,a.create_time,a.update_time,concat(c.branch_id,c.branch_name) branch"
						+ " from T_APPROVAL_PROCESS a left join T_APPROVAL_TYPE b on a.approval_type=b.approval_type left join SYS_BRANCH c "
						+ " on a.branch_id = c.branch_id where 1=1 ")
									.append(and("a.id like :id",hasKey(p,"id")))
									.append(and("a.approval_process = :approval_process",hasKey(p,"approval_process")))
									.append(and("a.approval_process_name like :approval_process_name",hasKey(p,"approval_process_name")))
									.append(and("a.approval_type = :approval_type",hasKey(p,"approval_type")))
									.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
									.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
									.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
									.append(and("a.update_time = :update_time",hasKey(p,"update_time")))
									.append(and("a.new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
						;
				if(p.containsKey("branch")&&p.get("branch")!=null) {
					sql.append(and("(c.branch_id like :branch or c.branch_name like :branch)"));
				}
				if(p.containsKey("branchids")&&p.get("branchids")!=null) {
					sql.append(and("(a.branch_id in ("+ p.get("branchids")+") or a.branch_id is null)"));
				}
				String sqlStr=sql.toString();
				printSql(sqlStr,p);
				//获取数据库类型
				String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
				if ("oracle.jdbc.driver.OracleDriver".equals(dbType)){
					long count = count("select count(*) from ("+sqlStr +")  ", p);
					PageInfo  pageInf = MfpContextHolder.getPageInfo();
					pageInf.setITotalDisplayRecords(count);
					MfpContextHolder.setPageInfo(pageInf);
					sqlStr=pageSqlInOracle(sql.append(" order by a.id desc").toString());
					printSql(sqlStr,p);
					return queryForList(sqlStr, p);
				}else{
					long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
					PageInfo  pageInf = MfpContextHolder.getPageInfo();
					pageInf.setITotalDisplayRecords(count);
					MfpContextHolder.setPageInfo(pageInf);
					sql.append(" order by a.id desc").append(pageSql());
					sqlStr=sql.toString();
					printSql(sqlStr,p);
					return queryForList(sqlStr, p);
				}
			}

			public List<Map<String, Object>> queryForListNoBranch(Map<String,Object> p) {
				String sql=Sql.create("select * from T_APPROVAL_PROCESS where 1=1 and branch_id is null")
						.append(and("id = :id",hasKey(p,"id")))
						.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
						.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
						.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
						.append(and("remarks = :remarks",hasKey(p,"remarks")))
						.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
						.append(and("update_time = :update_time",hasKey(p,"update_time")))
						.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
						.append(orderBySql())
						.toString();
				 printSql(sql,p);
				return queryForList(sql, p);
			}

			public List<Map<String, Object>> queryForListByPK(Map<String,Object> p) {
				String sql=Sql.create("select * from T_APPROVAL_PROCESS where 1=1")
						.append(and("id = :id",hasKey(p,"id")))
						.append(orderBySql())
						.toString();
				 printSql(sql,p);
				return queryForList(sql, p);
			}

	public List<Map<String, Object>> queryForListForAllBranch(Map<String,Object> p) {
		String sql=Sql.create("select * from T_APPROVAL_PROCESS where 1=1 and branch_id is null ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("approval_process = :approval_process",hasKey(p,"approval_process")))
				.append(and("approval_process_name = :approval_process_name",hasKey(p,"approval_process_name")))
				.append(and("approval_type = :approval_type",hasKey(p,"approval_type")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("new_approval_process = :new_approval_process",hasKey(p,"new_approval_process")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

}
