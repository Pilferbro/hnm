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
 * 表名:H_TEAM
 * 主键:id
 **/
@Repository
public class HTeamDao extends TXBaseDao {

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","branch_id","branch_name","porgid","creator","create_time","address","branch_type","lng","lat","rural_branch_id","team_flag","team_leader","generate_date","is_delete","remarks","temp_save"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
		String sql= Sql.create("insert into H_TEAM (")
				.append(field("id ",hasKey(p,"id")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
				.append(field("branch_name ",hasKey(p,"branch_name")))
				.append(field("porgid ",hasKey(p,"porgid")))
				.append(field("creator ",hasKey(p,"creator")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("address ",hasKey(p,"address")))
				.append(field("branch_type ",hasKey(p,"branch_type")))
				.append(field("lng ",hasKey(p,"lng")))
				.append(field("lat ",hasKey(p,"lat")))
				.append(field("rural_branch_id ",hasKey(p,"rural_branch_id")))
				.append(field("team_flag ",hasKey(p,"team_flag")))
				.append(field("team_leader ",hasKey(p,"team_leader")))
				.append(field("generate_date ",hasKey(p,"generate_date")))
				.append(field("is_delete ",hasKey(p,"is_delete")))
				.append(field("remarks ",hasKey(p,"remarks")))
				.append(field("temp_save ",hasKey(p,"temp_save")))
				.append(") values (")
				.append(field(":id ",hasKey(p,"id")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
				.append(field(":branch_name ",hasKey(p,"branch_name")))
				.append(field(":porgid ",hasKey(p,"porgid")))
				.append(field(":creator ",hasKey(p,"creator")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":address ",hasKey(p,"address")))
				.append(field(":branch_type ",hasKey(p,"branch_type")))
				.append(field(":lng ",hasKey(p,"lng")))
				.append(field(":lat ",hasKey(p,"lat")))
				.append(field(":rural_branch_id ",hasKey(p,"rural_branch_id")))
				.append(field(":team_flag ",hasKey(p,"team_flag")))
				.append(field(":team_leader ",hasKey(p,"team_leader")))
				.append(field(":generate_date ",hasKey(p,"generate_date")))
				.append(field(":is_delete ",hasKey(p,"is_delete")))
				.append(field(":remarks ",hasKey(p,"remarks")))
				.append(field(":temp_save ",hasKey(p,"temp_save")))
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
		String sql= Sql.create("delete from H_TEAM where 1=1 ")
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
		String sql= Sql.create("delete from H_TEAM where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update H_TEAM set ")
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(field("porgid = :porgid",hasKey(p,"porgid")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("address =:address",hasKey(p,"address")))
				.append(field("branch_type =:branch_type",hasKey(p,"branch_type")))
				.append(field("lng =:lng",hasKey(p,"lng")))
				.append(field("lat =:lat",hasKey(p,"lat")))
				.append(field("rural_branch_id =:rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(field("team_flag =:team_flag",hasKey(p,"team_flag")))
				.append(field("team_leader =:team_leader",hasKey(p,"team_leader")))
				.append(field("generate_date =:generate_date",hasKey(p,"generate_date")))
				.append(field("is_delete =:is_delete",hasKey(p,"is_delete")))
				.append(field("remarks =:remarks",hasKey(p,"remarks")))
				.append(field("temp_save =:temp_save",hasKey(p,"temp_save")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql= Sql.create("update H_TEAM set ")
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(field("porgid = :porgid",hasKey(p,"porgid")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("address =:address",hasKey(p,"address")))
				.append(field("branch_type =:branch_type",hasKey(p,"branch_type")))
				.append(field("lng =:lng",hasKey(p,"lng")))
				.append(field("lat =:lat",hasKey(p,"lat")))
				.append(field("rural_branch_id =:rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(field("team_flag =:team_flag",hasKey(p,"team_flag")))
				.append(field("team_leader =:team_leader",hasKey(p,"team_leader")))
				.append(field("generate_date =:generate_date",hasKey(p,"generate_date")))
				.append(field("is_delete =:is_delete",hasKey(p,"is_delete")))
				.append(field("remarks =:remarks",hasKey(p,"remarks")))
				.append(field("temp_save =:temp_save",hasKey(p,"temp_save")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks =:remarks",hasKey(p,"remarks")))
				.append(and("temp_save =:temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String,Object> p) {

		if(count(p)>0){
			return update(p);
		}else{
			return save(p);
		}
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql= Sql.create("select * from H_TEAM where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks =:remarks",hasKey(p,"remarks")))
				.append(and("temp_save =:temp_save",hasKey(p,"temp_save")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql= Sql.create("select * from H_TEAM where 1=1")
			.append(and("id = :id",hasKey(p,"id")))
						.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
						.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
						.append(and("porgid = :porgid",hasKey(p,"porgid")))
						.append(and("creator = :creator",hasKey(p,"creator")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
						.append(and("address = :address",hasKey(p,"address")))
						.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
						.append(and("lng = :lng",hasKey(p,"lng")))
						.append(and("lat = :lat",hasKey(p,"lat")))
						.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
						.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
						.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
						.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
						.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
						.append(and("remarks = :remarks",hasKey(p,"remarks")))
						.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr= Sql.pageSqlInOracle(sql.append(orderBySql()).toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
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
		String sql= Sql.create("select * from H_TEAM where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_TEAM where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


	//批量插入方法
	public int[] saveList(List<Map<String,Object>> paramlist) {

		Sql sql= Sql.create("");
		sql.append("insert into H_TEAM (")
				.append(field("id "))
				.append(field("branch_id "))
				.append(field("branch_name "))
				.append(field("porgid "))
				.append(field("creator "))
				.append(field("create_time "))
				.append(field("address "))
				.append(field("branch_type "))
				.append(field("lng "))
				.append(field("lat "))
				.append(field("rural_branch_id "))
				.append(field("team_flag "))
				.append(field("team_leader "))
				.append(field("generate_date "))
				.append(field("is_delete "))
				.append(field("remarks "))
				.append(field("temp_save "))
				.append(") values (")
				.append(field(":id "))
				.append(field(":branch_id "))
				.append(field(":branch_name "))
				.append(field(":porgid "))
				.append(field(":creator "))
				.append(field(":create_time "))
				.append(field(":address "))
				.append(field(":branch_type "))
				.append(field(":lng "))
				.append(field(":lat "))
				.append(field(":rural_branch_id "))
				.append(field(":team_flag "))
				.append(field(":team_leader "))
				.append(field(":generate_date "))
				.append(field(":is_delete "))
				.append(field(":remarks "))
				.append(field(":temp_save "))
				.append(")");


		return batchUpdate(sql.toString(), paramlist);
	}
	/**
	 * 根据上级部门id查询部门id
	 */
	public List<Map<String, Object>> queryBeforeOrgid(Map<String, Object> p) {
		String sql = "select * from H_TEAM where porgid=:branch_id";
		printSql(sql, p);
		return queryForList(sql, p);
	}

	/**
	 * 查询部门
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListBycondition(Map<String,Object> p) {
		Sql sql= Sql.create("select * from H_TEAM where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("porgid = :porgid",hasKey(p,"porgid")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("porgid in ("+ p.get("branchids")+")"));
		}
		//sql.append(orderBySql());
		sql.append(" order by branch_id desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	/**
	 * 查询部门 分页
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListByconditionByPage(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.approval_status,b.approval_status_name,concat(a.porgid,c.branch_name) porg " +
				" from H_TEAM a left join T_APPROVAL_APPLY b on a.id = b.relation_id left join SYS_BRANCH c on a.porgid = c.branch_id where 1=1 ")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.branch_id like :branch_id",hasKey(p,"branch_id")))
				.append(and("a.branch_name like :branch_name",hasKey(p,"branch_name")))
				.append(and("a.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.address = :address",hasKey(p,"address")))
				.append(and("a.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("a.lng = :lng",hasKey(p,"lng")))
				.append(and("a.lat = :lat",hasKey(p,"lat")))
				.append(and("a.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("a.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("a.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("a.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("a.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("a.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)){
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

	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.approval_status,b.approval_status_name,concat(a.porgid,c.branch_name) porg,d.name team_leader_name, " +
				" (select count(m.branch_id) team_count from SYS_BRANCH m where m.porgid = a.branch_id) team_count,(select count(n.account_id) team_num from SYS_ACCOUNT n where n.branch_id = a.branch_id) team_num,(select count(o.account_id) area_num from SYS_ACCOUNT o where o.branch_id in (select p.branch_id from SYS_BRANCH p where a.branch_id = p.porgid or a.branch_id = p.branch_id)) area_num " +
				" from H_TEAM a left join T_APPROVAL_APPLY b on a.id = b.relation_id left join SYS_BRANCH c on a.porgid = c.branch_id left join SYS_ACCOUNT d on a.team_leader = d.account_id where 1=1 ")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.branch_id like :branch_id",hasKey(p,"branch_id")))
				.append(and("a.branch_name like :branch_name",hasKey(p,"branch_name")))
				.append(and("a.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.address = :address",hasKey(p,"address")))
				.append(and("a.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("a.lng = :lng",hasKey(p,"lng")))
				.append(and("a.lat = :lat",hasKey(p,"lat")))
				.append(and("a.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("a.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("a.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("a.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("a.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("a.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		sql.append(" order by a.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	/*public List<Map<String, Object>> queryForListByconditionByPage(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.approval_status,b.approval_status_name,concat(a.porgid,c.branch_name) porg, " +
				" (select count(t4.id) from H_SITE t4 left join T_APPROVAL_APPLY t5 on t4.id = t5.RELATION_ID left join SYS_ACCOUNT m on t4.mgr_id = m.account_id where m.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t4.status in ('0','1') and t4.is_delete = '0' and t5.approval_status = '2') site_num1, " +
				" (select count(t6.id) from H_SITE t6 left join T_APPROVAL_APPLY t7 on t6.id = t7.RELATION_ID left join SYS_ACCOUNT n on t6.mgr_id = n.account_id where n.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t6.status = '2' and t6.is_delete = '0' and t7.approval_status = '2') site_num2, " +
				" (select count(t8.id) from H_SITE t8 left join T_APPROVAL_APPLY t9 on t8.id = t9.RELATION_ID left join SYS_ACCOUNT o on t8.mgr_id = o.account_id where o.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t8.is_delete = '0' and t9.approval_status = '2') site_num3 " +
				" from H_TEAM a left join T_APPROVAL_APPLY b on a.id = b.relation_id left join SYS_BRANCH c on a.porgid = c.branch_id where 1=1 ")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.branch_id like :branch_id",hasKey(p,"branch_id")))
				.append(and("a.branch_name like :branch_name",hasKey(p,"branch_name")))
				.append(and("a.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.address = :address",hasKey(p,"address")))
				.append(and("a.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("a.lng = :lng",hasKey(p,"lng")))
				.append(and("a.lat = :lat",hasKey(p,"lat")))
				.append(and("a.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("a.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("a.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("a.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("a.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)){
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
	}*/


	/*public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.approval_status,b.approval_status_name,concat(a.porgid,c.branch_name) porg,d.name team_leader_name, " +
				" (select count(t4.id) from H_SITE t4 left join T_APPROVAL_APPLY t5 on t4.id = t5.RELATION_ID left join SYS_ACCOUNT m on t4.mgr_id = m.account_id where m.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t4.status in ('0','1') and t4.is_delete = '0' and t5.approval_status = '2') site_num1, " +
				" (select count(t6.id) from H_SITE t6 left join T_APPROVAL_APPLY t7 on t6.id = t7.RELATION_ID left join SYS_ACCOUNT n on t6.mgr_id = n.account_id where n.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t6.status = '2' and t6.is_delete = '0' and t7.approval_status = '2') site_num2, " +
				" (select count(t8.id) from H_SITE t8 left join T_APPROVAL_APPLY t9 on t8.id = t9.RELATION_ID left join SYS_ACCOUNT o on t8.mgr_id = o.account_id where o.branch_id in (SELECT BRANCH_ID FROM SYS_BRANCH START WITH BRANCH_ID = a.branch_id CONNECT BY PRIOR BRANCH_ID = PORGID) and t8.is_delete = '0' and t9.approval_status = '2') site_num3, " +
				" (select count(m.branch_id) team_count from SYS_BRANCH m where m.porgid = a.branch_id) team_count,(select count(n.account_id) team_num from SYS_ACCOUNT n where n.branch_id = a.branch_id) team_num,(select count(o.account_id) area_num from SYS_ACCOUNT o where o.branch_id in (select p.branch_id from SYS_BRANCH p where a.branch_id = p.porgid or a.branch_id = p.branch_id)) area_num " +
				" from H_TEAM a left join T_APPROVAL_APPLY b on a.id = b.relation_id left join SYS_BRANCH c on a.porgid = c.branch_id left join SYS_ACCOUNT d on a.team_leader = d.account_id where 1=1 ")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.branch_id like :branch_id",hasKey(p,"branch_id")))
				.append(and("a.branch_name like :branch_name",hasKey(p,"branch_name")))
				.append(and("a.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.address = :address",hasKey(p,"address")))
				.append(and("a.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("a.lng = :lng",hasKey(p,"lng")))
				.append(and("a.lat = :lat",hasKey(p,"lat")))
				.append(and("a.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("a.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("a.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("a.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("a.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("b.approval_status = :approval_status",hasKey(p,"approval_status")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		sql.append(" order by a.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}*/

	public List<Map<String, Object>> queryList(Map<String,Object> p) {
		String sql= Sql.create("select a.*,b.APPROVAL_STATUS,b.APPROVAL_STATUS_NAME from H_TEAM a LEFT JOIN T_APPROVAL_APPLY b on a.id=b.RELATION_ID where 1=1")
				.append(and("a.id = :id",hasKey(p,"id")))
				.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("a.branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("a.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.address = :address",hasKey(p,"address")))
				.append(and("a.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("a.lng = :lng",hasKey(p,"lng")))
				.append(and("a.lat = :lat",hasKey(p,"lat")))
				.append(and("a.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("a.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("a.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("a.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("a.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("a.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("a.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("b.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	/**
	 * 根据上级部门id查询下级部门的最大id
	 */
	public List<Map<String, Object>> queryMaxOrgid(Map<String, Object> p) {
		String sql = "select max(branch_id) maxid from H_TEAM where porgid=:porgid";
		printSql(sql, p);
		return queryForList(sql, p);
	}

	public int updateStatus(String status,String branch_id){
		String sql="update H_TEAM set is_delete = "+status+" where branch_id = " + branch_id;
		printSql(sql,null);
		return delete(sql,null);
	}

	public List<Map<String, Object>> queryForListDetail(Map<String,Object> p) {
		String sql= Sql.create("select t1.*,t7.name,(select count(t2.branch_id) team_count from SYS_BRANCH t2 where t2.porgid = t1.branch_id) team_count,(select count(t3.account_id) team_num from SYS_ACCOUNT t3 where t3.branch_id = t1.branch_id) team_num, " +
				" (select count(t4.account_id) area_num from SYS_ACCOUNT t4 where t4.branch_id in (select t5.branch_id from SYS_BRANCH t5 where t1.branch_id = t5.porgid or t1.branch_id = t5.branch_id)) area_num,t6.branch_name porgname " +
				" from H_TEAM t1 left join SYS_BRANCH t6 on t1.porgid = t6.branch_id left join SYS_ACCOUNT t7 on t1.team_leader = t7.account_id where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.branch_name = :branch_name",hasKey(p,"branch_name")))
				.append(and("t1.porgid = :porgid",hasKey(p,"porgid")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.address = :address",hasKey(p,"address")))
				.append(and("t1.branch_type = :branch_type",hasKey(p,"branch_type")))
				.append(and("t1.lng = :lng",hasKey(p,"lng")))
				.append(and("t1.lat = :lat",hasKey(p,"lat")))
				.append(and("t1.rural_branch_id = :rural_branch_id",hasKey(p,"rural_branch_id")))
				.append(and("t1.team_flag = :team_flag",hasKey(p,"team_flag")))
				.append(and("t1.team_leader = :team_leader",hasKey(p,"team_leader")))
				.append(and("t1.generate_date = :generate_date",hasKey(p,"generate_date")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.remarks =:remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_save =:temp_save",hasKey(p,"temp_save")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForHisList(Map<String, Object> p) {
		String sql = Sql.create("select t2.*,t3.approval_type_name from H_TEAM t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID left join T_APPROVAL_TYPE t3 on t2.approval_type = t3.approval_type where 1=1")
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(" order by t2.create_time desc")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}
}
