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
 * 表名:H_MANAGER
 * 主键:
 * mgr_id
 **/
@Repository
public class HManagerDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","mgr_id","branch_id","mgr_name","phone","e_mail","age","sex","birthday","employment_date","education","create_time","update_time","is_delete","team_id","creator","remarks","temp_role_id","temp_save"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
		String sql=Sql.create("insert into H_MANAGER (")
				.append(field("id "))
				.append(field("mgr_id ",hasKey(p,"mgr_id")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
				.append(field("mgr_name ",hasKey(p,"mgr_name")))
				.append(field("phone ",hasKey(p,"phone")))
				.append(field("e_mail ",hasKey(p,"e_mail")))
				.append(field("age ",hasKey(p,"age")))
				.append(field("sex ",hasKey(p,"sex")))
				.append(field("birthday ",hasKey(p,"birthday")))
				.append(field("employment_date ",hasKey(p,"employment_date")))
				.append(field("education ",hasKey(p,"education")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("update_time ",hasKey(p,"update_time")))
				.append(field("is_delete ",hasKey(p,"is_delete")))
				.append(field("team_id ",hasKey(p,"team_id")))
				.append(field("creator ",hasKey(p,"creator")))
				.append(field("remarks ",hasKey(p,"remarks")))
				.append(field("temp_role_id ",hasKey(p,"temp_role_id")))
				.append(field("temp_save ",hasKey(p,"temp_save")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":mgr_id ",hasKey(p,"mgr_id")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
				.append(field(":mgr_name ",hasKey(p,"mgr_name")))
				.append(field(":phone ",hasKey(p,"phone")))
				.append(field(":e_mail ",hasKey(p,"e_mail")))
				.append(field(":age ",hasKey(p,"age")))
				.append(field(":sex ",hasKey(p,"sex")))
				.append(field(":birthday ",hasKey(p,"birthday")))
				.append(field(":employment_date ",hasKey(p,"employment_date")))
				.append(field(":education ",hasKey(p,"education")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":update_time ",hasKey(p,"update_time")))
				.append(field(":is_delete ",hasKey(p,"is_delete")))
				.append(field(":team_id ",hasKey(p,"team_id")))
				.append(field(":creator ",hasKey(p,"creator")))
				.append(field(":remarks ",hasKey(p,"remarks")))
				.append(field(":temp_role_id ",hasKey(p,"temp_role_id")))
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
		String sql=Sql.create("delete from H_MANAGER where 1=1 ")
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
		String sql=Sql.create("delete from H_MANAGER where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("age = :age",hasKey(p,"age")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("birthday = :birthday",hasKey(p,"birthday")))
				.append(and("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete =:is_delete",hasKey(p,"is_delete")))
				.append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("creator =:creator",hasKey(p,"creator")))
				.append(and("remarks =:remarks",hasKey(p,"remarks")))
				.append(and("temp_role_id =:temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("temp_save =:temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_MANAGER set ")
				.append(field("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(field("age = :age",hasKey(p,"age")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("birthday = :birthday",hasKey(p,"birthday")))
				.append(field("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(field("education = :education",hasKey(p,"education")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("team_id = :team_id",hasKey(p,"team_id")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("remarks = :remarks",hasKey(p,"remarks")))
				.append(field("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(field("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_MANAGER set ")
				.append(field("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(field("age = :age",hasKey(p,"age")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("birthday = :birthday",hasKey(p,"birthday")))
				.append(field("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(field("education = :education",hasKey(p,"education")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("team_id = :team_id",hasKey(p,"team_id")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("remarks = :remarks",hasKey(p,"remarks")))
				.append(field("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(field("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("age = :age",hasKey(p,"age")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("birthday = :birthday",hasKey(p,"birthday")))
				.append(and("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
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
		String sql=Sql.create("select t1.*,t2.branch_name from H_MANAGER t1 left join SYS_BRANCH t2 on t1.branch_id = t2.branch_id where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from H_MANAGER where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("age = :age",hasKey(p,"age")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("birthday = :birthday",hasKey(p,"birthday")))
				.append(and("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
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
		String sql=Sql.create("select * from H_MANAGER where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("age = :age",hasKey(p,"age")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("birthday = :birthday",hasKey(p,"birthday")))
				.append(and("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_MANAGER where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("age = :age",hasKey(p,"age")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("birthday = :birthday",hasKey(p,"birthday")))
				.append(and("employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public List<Map<String, Object>> queryForListByPageForManageQuery(Map<String, Object> p) {
		Sql sql= Sql.create("select t1.*,b.role_id,b.role_name,t2.BRANCH_NAME,t3.APPROVAL_STATUS_NAME,t3.APPROVAL_STATUS " +
				" from H_MANAGER t1 left join SYS_BRANCH t2 on t1.BRANCH_ID=t2.BRANCH_ID  left join T_APPROVAL_APPLY t3 on t1.id=t3.RELATION_ID" +
				" left join SYS_ACCOUNT_ROLE a on t1.mgr_id=a.account_id left join sys_role b on a.role_id=b.role_id where 1=1 ")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t3.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				;

		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}

		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)) {
			long count = count("select count(*) from (" + sqlStr + ")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr = pageSqlInOracle(sql.append(" order by t1.create_time desc").toString());
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		} else {
			long count = count("select count(*) from (" + sqlStr
					+ ")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by t1.create_time desc").append(pageSql());
			sqlStr = sql.toString();
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		}
	}

	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql= Sql.create("select t1.*,b.role_id,b.role_name,CASE t1.SEX WHEN '0' THEN'男' WHEN '1' THEN'女'END SEX_NAME,CASE t1.EDUCATION WHEN '0' THEN'初中及以下' WHEN '1' THEN'高中（含中专等）' WHEN '2' THEN'大专'WHEN '3' THEN'本科'WHEN '4' THEN'研究生及以上'END EDUCATION_NAME, t2.BRANCH_NAME,t3.APPROVAL_STATUS_NAME " +
				" from H_MANAGER t1 left join SYS_BRANCH t2 on t1.BRANCH_ID=t2.BRANCH_ID  left join T_APPROVAL_APPLY t3 on t1.id=t3.RELATION_ID " +
				" left join SYS_ACCOUNT_ROLE a on t1.mgr_id=a.account_id left join sys_role b on a.role_id=b.role_id " +
				" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t3.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}
		sql.append(" order by t1.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	/*public List<Map<String, Object>> queryForListByPageForManageQuery(Map<String, Object> p) {
		Sql sql= Sql.create("select t1.*,b.role_id,b.role_name,t2.BRANCH_NAME,t3.APPROVAL_STATUS_NAME,t3.APPROVAL_STATUS, " +
				" (select count(t4.id) from H_SITE t4 left join T_APPROVAL_APPLY t5 on t4.id = t5.RELATION_ID where t4.mgr_id = t1.mgr_id and t4.status in ('0','1') and t4.is_delete = '0' and t5.approval_status = '2') site_num1, " +
				" (select count(t6.id) from H_SITE t6 left join T_APPROVAL_APPLY t7 on t6.id = t7.RELATION_ID where t6.mgr_id = t1.mgr_id and t6.status = '2' and t6.is_delete = '0' and t7.approval_status = '2') site_num2, " +
				" (select count(t8.id) from H_SITE t8 left join T_APPROVAL_APPLY t9 on t8.id = t9.RELATION_ID where t8.mgr_id = t1.mgr_id and t8.is_delete = '0' and t9.approval_status = '2') site_num3 " +
				" from H_MANAGER t1 left join SYS_BRANCH t2 on t1.BRANCH_ID=t2.BRANCH_ID  left join T_APPROVAL_APPLY t3 on t1.id=t3.RELATION_ID" +
				" left join SYS_ACCOUNT_ROLE a on t1.mgr_id=a.account_id left join sys_role b on a.role_id=b.role_id where 1=1 ")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t3.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				;

		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}

		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType)) {
			long count = count("select count(*) from (" + sqlStr + ")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr = pageSqlInOracle(sql.append(" order by t1.create_time desc").toString());
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		} else {
			long count = count("select count(*) from (" + sqlStr
					+ ")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by t1.create_time desc").append(pageSql());
			sqlStr = sql.toString();
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		}
	}

	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql= Sql.create("select t1.*,b.role_id,b.role_name,CASE t1.SEX WHEN '0' THEN'男' WHEN '1' THEN'女'END SEX_NAME,CASE t1.EDUCATION WHEN '0' THEN'初中及以下' WHEN '1' THEN'高中（含中专等）' WHEN '2' THEN'大专'WHEN '3' THEN'本科'WHEN '4' THEN'研究生及以上'END EDUCATION_NAME, t2.BRANCH_NAME,t3.APPROVAL_STATUS_NAME," +
				" (select count(t4.id) from H_SITE t4 left join T_APPROVAL_APPLY t5 on t4.id = t5.RELATION_ID where t4.mgr_id = t1.mgr_id and t4.status in ('0','1') and t4.is_delete = '0' and t5.approval_status = '2') site_num1, " +
				" (select count(t6.id) from H_SITE t6 left join T_APPROVAL_APPLY t7 on t6.id = t7.RELATION_ID where t6.mgr_id = t1.mgr_id and t6.status = '2' and t6.is_delete = '0' and t7.approval_status = '2') site_num2, " +
				" (select count(t8.id) from H_SITE t8 left join T_APPROVAL_APPLY t9 on t8.id = t9.RELATION_ID where t8.mgr_id = t1.mgr_id and t8.is_delete = '0' and t9.approval_status = '2') site_num3 " +
				" from H_MANAGER t1 left join SYS_BRANCH t2 on t1.BRANCH_ID=t2.BRANCH_ID  left join T_APPROVAL_APPLY t3 on t1.id=t3.RELATION_ID " +
				" left join SYS_ACCOUNT_ROLE a on t1.mgr_id=a.account_id left join sys_role b on a.role_id=b.role_id " +
				" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t3.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}
		sql.append(" order by t1.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}*/

	public List<Map<String, Object>> queryList(Map<String,Object> p) {
		String sql=Sql.create("select t1.*,t2.APPROVAL_STATUS_NAME from H_MANAGER t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID  where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForListBycondition(Map<String,Object> p) {
		Sql sql=Sql.create("select t1.*,t3.branch_name from H_MANAGER t1 left join T_APPROVAL_APPLY t2 on t1.id = t2.RELATION_ID left join SYS_BRANCH t3 on t1.branch_id = t3.branch_id where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("t1.mgr_name = :mgr_name",hasKey(p,"mgr_name")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.e_mail = :e_mail",hasKey(p,"e_mail")))
				.append(and("t1.age = :age",hasKey(p,"age")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.birthday = :birthday",hasKey(p,"birthday")))
				.append(and("t1.employment_date = :employment_date",hasKey(p,"employment_date")))
				.append(and("t1.education = :education",hasKey(p,"education")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.remarks = :remarks",hasKey(p,"remarks")))
				.append(and("t1.temp_role_id = :temp_role_id",hasKey(p,"temp_role_id")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t2.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				;
				if(p.containsKey("branchids")&&p.get("branchids")!=null) {
					sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
				}
				sql.append(orderBySql());
				String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	public int updateStatus(String status,String ids){
		String sql="update H_MANAGER set is_delete = "+status+" where mgr_id in("+ids+")";
		printSql(sql,null);
		return delete(sql,null);
	}

	public List<Map<String, Object>> queryForHisList(Map<String, Object> p) {
		String sql = Sql.create("select t2.*,t3.approval_type_name from H_MANAGER t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID left join T_APPROVAL_TYPE t3 on t2.approval_type = t3.approval_type where 1=1")
				.append(and("t1.mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(" order by t2.create_time desc")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}
}
