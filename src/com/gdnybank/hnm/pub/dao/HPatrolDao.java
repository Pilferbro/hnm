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
 * 表名:H_PATROL
 * 主键:
 * patrol_id
 **/
@Repository
public class HPatrolDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"patrol_id","site_id","account_id","inspect_time","patrol_remark","clock_in","category"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"patrol_id"};

	@Override
	public int save(Map<String,Object> p) {
		String tableFlowNo = sequenceService.getTableFlowNo("H_PATROL", "patrol_id");
		p.put("patrol_id",tableFlowNo);
		String sql=Sql.create("insert into H_PATROL (")
				.append(field("patrol_id "))
				.append(field("site_id ",hasKey(p,"site_id")))
				.append(field("account_id ",hasKey(p,"account_id")))
				.append(field("inspect_time ",hasKey(p,"inspect_time")))
				.append(field("patrol_remark ",hasKey(p,"patrol_remark")))
				.append(field("clock_in ",hasKey(p,"clock_in")))
				.append(field("patrol_problem ",hasKey(p,"patrol_problem")))
				.append(field("category ",hasKey(p,"category")))
				.append(") values (")
				.append(field(":patrol_id "))
				.append(field(":site_id ",hasKey(p,"site_id")))
				.append(field(":account_id ",hasKey(p,"account_id")))
				.append(field(":inspect_time ",hasKey(p,"inspect_time")))
				.append(field(":patrol_remark ",hasKey(p,"patrol_remark")))
				.append(field(":clock_in ",hasKey(p,"clock_in")))
				.append(field(":patrol_problem ",hasKey(p,"patrol_problem")))
				.append(field(":category ",hasKey(p,"category")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}
	public String saveAndReturnID(Map<String,Object> p) {
		String tableFlowNo = sequenceService.getTableFlowNo("H_PATROL", "patrol_id");
		p.put("patrol_id",tableFlowNo);
		String sql=Sql.create("insert into H_PATROL (")
				.append(field("patrol_id "))
				.append(field("site_id ",hasKey(p,"site_id")))
				.append(field("account_id ",hasKey(p,"account_id")))
				.append(field("inspect_time ",hasKey(p,"inspect_time")))
				.append(field("patrol_remark ",hasKey(p,"patrol_remark")))
				.append(field("clock_in ",hasKey(p,"clock_in")))
				.append(field("patrol_problem ",hasKey(p,"patrol_problem")))
				.append(field("category ",hasKey(p,"category")))
				.append(") values (")
				.append(field(":patrol_id "))
				.append(field(":site_id ",hasKey(p,"site_id")))
				.append(field(":account_id ",hasKey(p,"account_id")))
				.append(field(":inspect_time ",hasKey(p,"inspect_time")))
				.append(field(":patrol_remark ",hasKey(p,"patrol_remark")))
				.append(field(":clock_in ",hasKey(p,"clock_in")))
				.append(field(":patrol_problem ",hasKey(p,"patrol_problem")))
				.append(field(":category ",hasKey(p,"category")))
				.append(")")
				.toString();
		printSql(sql,p);
		save(sql, p);
		return tableFlowNo;
	}
	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String,Object> p) {
		String sql=Sql.create("delete from H_PATROL where 1=1 ")
				.append(and("patrol_id = :patrol_id"))
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
		String sql=Sql.create("delete from H_PATROL where 1=1 ")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("category = :category",hasKey(p,"category")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_PATROL set ")
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("account_id = :account_id",hasKey(p,"account_id")))
				.append(field("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(field("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(field("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(field("patrol_problem = :patrol_problem",hasKey(p,"patrol_problem")))
				.append(field("category = :category",hasKey(p,"category")))
				.append(" where 1=1 ")
				.append(and("patrol_id = :patrol_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_PATROL set ")
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("account_id = :account_id",hasKey(p,"account_id")))
				.append(field("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(field("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(field("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(field("patrol_problem = :patrol_problem",hasKey(p,"patrol_problem")))
				.append(field("category = :category",hasKey(p,"category")))
				.append(" where 1=1 ")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("patrol_problem = :patrol_problem",hasKey(p,"patrol_problem")))
				.append(and("category = :category",hasKey(p,"category")))
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
		String sql=Sql.create("select patrol_id,site_id,account_id,inspect_time,patrol_remark,clock_in," +
				"nvl(category,0)category from H_PATROL where 1=1")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("category = :category",hasKey(p,"category")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from (select p.patrol_id,p.site_id,p.account_id,p.inspect_time,p.patrol_remark,p.clock_in,nvl(p.category,0)category,a.name,s.site_name , " +
				"(SELECT l.PATROL_RESULT FROM H_PATROL_LOG l " +
				"LEFT JOIN H_MARK_SHEEL m on m.MARK_SHEEL_ID=l.PATROL_CONTENT " +
				"WHERE l.PATROL_ID=p.PATROL_ID and m.MARK_SHEEL_ID = 80101 ) patrol_remarks, " +
				"(SELECT listagg(h.content_text,',') FROM H_PATROL_LOG_CONTENT h " +
				"where p.patrol_id = h.patrol_id and h.isdeleted = 0 group by h.patrol_id) patrol_problem " +
				"from H_PATROL p " +
				"left join SYS_ACCOUNT a on p.account_id=a.account_id " +
				"left join H_SITE s on s.site_no=p.site_id " +
				"left join  T_APPROVAL_APPLY ap on ap.relation_id=s.id " +
				"where 1=1 and s.is_delete=0 and ap.approval_status=2 ")
			.append(and("p.patrol_id = :patrol_id",hasKey(p,"patrol_id")))
						.append(and("p.site_id = :site_id",hasKey(p,"site_id")))
						.append(and("p.account_id = :account_id",hasKey(p,"account_id")))
						.append(and("p.inspect_time = :inspect_time",hasKey(p,"inspect_time")))
						.append(and("p.inspect_time >= :inspect_start_time",hasKey(p,"inspect_start_time")))
						.append(and("p.inspect_time <= :inspect_end_time",hasKey(p,"inspect_end_time")))
						.append(and("p.patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
						.append(and("p.clock_in = :clock_in",hasKey(p,"clock_in")))
			;

		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("orgids")+")"));
		}
        sql.append(")t1 where 1=1")
                .append(and("t1.category = :category",hasKey(p,"category")));
		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.append(" order by inspect_time desc ").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by p.inspect_time desc ").append(pageSql());
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
		String sql=Sql.create("select * from H_PATROL where 1=1 ")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("category = :category",hasKey(p,"category")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_PATROL where 1=1 ")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("category = :category",hasKey(p,"category")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


    public List<Map<String, Object>> queryByPatrolId(Map<String, Object> p) {
		String sql=Sql.create("select distinct t1.site_id,t2.mgr_id from H_PATROL t1 " +
				"left join H_SITE t2 on t1.site_id = t2.site_no " +
				" where 1=1 ")
				.append(and("patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);

    }

	public List<Map<String, Object>> queryForListToInspection1(Map<String,Object> p) {
		String sql=Sql.create("select t1.account_id,t1.patrol_id,t1.inspect_time,t1.site_id,nvl(t1.category,0) category," +
				"t1.patrol_remark,t1.clock_in,t2.patrol_content,t2.patrol_result " +
				"from H_PATROL t1 left join H_PATROL_LOG t2 on t1.patrol_id = t2.patrol_id where 1=1")
				.append(and("t1.patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("t1.site_id = :site_id",hasKey(p,"site_id")))
				.append(and("t1.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("t1.category = :category",hasKey(p,"category")))
				.append(and("t1.inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("t1.inspect_time >= :inspect_start",hasKey(p,"inspect_start")))
				.append(and("t1.inspect_time <= :inspect_end",hasKey(p,"inspect_end")))
				.append(and("t1.patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("t1.clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("t2.patrol_content = :patrol_content",hasKey(p,"patrol_content")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryforExport(Map<String, Object> p) {
		Sql sql = Sql.create("select t1.*,t2.site_name,t2.master_name,t3.name from H_PATROL t1 " +
				"left join h_site t2 on t2.site_no = t1.site_id  " +
				"left join sys_account t3 on t3.account_id = t1.account_id " +
				"left join  t_approval_apply t4 on t4.relation_id=t2.id " +
				"where 1 = 1 and t1.inspect_time >= substr(t4.update_time,0,10) and t4.approval_status=2 ")
				.append(and("t1.patrol_id = :patrol_id",hasKey(p,"patrol_id")))
				.append(and("t1.site_id = :site_id",hasKey(p,"site_id")))
				.append(and("t1.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("t1.inspect_time = :inspect_time",hasKey(p,"inspect_time")))
				.append(and("t1.inspect_time >= :inspect_start",hasKey(p,"inspect_start")))
				.append(and("t1.inspect_time <= :inspect_end",hasKey(p,"inspect_end")))
				.append(and("t1.patrol_remark = :patrol_remark",hasKey(p,"patrol_remark")))
				.append(and("t1.clock_in = :clock_in",hasKey(p,"clock_in")))
				.append(and("t1.category = :category",hasKey(p,"category")))
				.append(and("t2.patrol_content = :patrol_content",hasKey(p,"patrol_content")))
				.append("order by t4.update_time desc");

		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}
}
