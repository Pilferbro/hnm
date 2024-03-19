package com.gdnybank.hnm.pub.dao;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:SYS_ACCOUNT
 * 主键:account_id
 **/
@Repository
public class SysAccountDao extends TXBaseDao {

	@Autowired
    PasswordEncoder passwordEncoder;

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"account_id","name","identify_no","branch_id","phone","login_pwd","business_pwd","creator","create_time","email","status","error_count","team_id","deleted"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"account_id"};



	public int batchInsertAccountInfo(final List<Map<String, Object>> batchInfoList) {
		String sql = "insert into SYS_ACCOUNT (account_id,name,branch_id,phone,login_pwd,business_pwd,CREATOR,create_time,identify_no,email,status,error_count,deleted) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		printSql(sql, null);
		int[] count = this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public int getBatchSize() {
				return batchInfoList.size();
			}
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Map<String, Object> map = batchInfoList.get(i);
				ps.setString(1, (String) map.get("account_id"));
				ps.setString(2, (String) map.get("name"));
				ps.setString(3, (String) map.get("branch_id"));
				ps.setString(4, (String) map.get("phone"));
				ps.setString(5, (String) map.get("login_pwd"));
				ps.setString(6, (String) map.get("business_pwd"));
				ps.setString(7, (String) map.get("creator"));
				ps.setString(8, (String) map.get("create_time"));
				ps.setString(9, (String) map.get("identify_no"));
				ps.setString(10, (String) map.get("email"));
				ps.setString(11, (String) map.get("status"));
				ps.setString(12, (String) map.get("error_count"));
				ps.setString(13, "0");
			}
		});

		return count.length;
	}

	/**
	 * 查询已经存在的用户信息
	 * @return
	 */
	public List<Map<String,Object>> selectExistAccountIdInfo(String sb) {
		String sql = "select account_id,name from SYS_ACCOUNT where account_id in " + "(" + sb + ")";
		printSql(sql,null);
		return queryForList(sql,null);
	}

	@Override
	public int save(Map<String,Object> p) {
		String sql= Sql.create("insert into SYS_ACCOUNT (")
				.append(field("account_id "))
				.append(field("name ",hasKey(p,"name")))
				.append(field("identify_no ",hasKey(p,"identify_no")))
				.append(field("branch_id ",hasKey(p,"branch_id")))
                .append(field("team_id ",hasKey(p,"team_id")))
				.append(field("phone ",hasKey(p,"phone")))
				.append(field("login_pwd ",hasKey(p,"login_pwd")))
				.append(field("business_pwd ",hasKey(p,"business_pwd")))
				.append(field("creator ",hasKey(p,"creator")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("email ",hasKey(p,"email")))
				.append(field("status ",hasKey(p,"status")))
				.append(field("error_count ",hasKey(p,"error_count")))
				.append(field("deleted ",hasKey(p,"deleted")))
				.append(") values (")
				.append(field(":account_id "))
				.append(field(":name ",hasKey(p,"name")))
				.append(field(":identify_no ",hasKey(p,"identify_no")))
				.append(field(":branch_id ",hasKey(p,"branch_id")))
                .append(field(":team_id ",hasKey(p,"team_id")))
				.append(field(":phone ",hasKey(p,"phone")))
				.append(field(":login_pwd ",hasKey(p,"login_pwd")))
				.append(field(":business_pwd ",hasKey(p,"business_pwd")))
				.append(field(":creator ",hasKey(p,"creator")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":email ",hasKey(p,"email")))
				.append(field(":status ",hasKey(p,"status")))
				.append(field(":error_count ",hasKey(p,"error_count")))
				.append(field(":deleted ",hasKey(p,"deleted")))
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
		String sql= Sql.create("update SYS_ACCOUNT set deleted = 1,name = CONCAT(name, '（停用）') where 1=1 ")
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
		String sql= Sql.create("delete from SYS_ACCOUNT where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("error_count = :error_count",hasKey(p,"error_count")))
				.append(and("deleted = :deleted",hasKey(p,"deleted")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update SYS_ACCOUNT set ")
				.append(field("name = :name",hasKey(p,"name")))
				.append(field("identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(field("team_id = :team_id",hasKey(p,"team_id")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(field("business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("email = :email",hasKey(p,"email")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("error_count = :error_count",hasKey(p,"error_count")))
				.append(field("deleted = :deleted",hasKey(p,"deleted")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql= Sql.create("update SYS_ACCOUNT set ")
				.append(field("name = :name",hasKey(p,"name")))
				.append(field("identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(field("team_id = :team_id",hasKey(p,"team_id")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(field("business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("email = :email",hasKey(p,"email")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("error_count = :error_count",hasKey(p,"error_count")))
				.append(field("deleted = :deleted",hasKey(p,"deleted")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("error_count = :error_count",hasKey(p,"error_count")))
				.append(and("deleted = :deleted",hasKey(p,"deleted")))
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
		String sql= Sql.create("select a.*,b.branch_name from SYS_ACCOUNT a left join SYS_BRANCH b on a.branch_id = b.branch_id where 1=1")
				.append(and("a.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("a.name = :name",hasKey(p,"name")))
				.append(and("a.identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("a.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("a.phone = :phone",hasKey(p,"phone")))
				.append(and("a.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("a.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.email = :email",hasKey(p,"email")))
				.append(and("a.status = :status",hasKey(p,"status")))
				.append(and("a.error_count = :error_count",hasKey(p,"error_count")))
				.append(and("a.deleted = 0",hasKey(p,"deleted")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryPersonal(Map<String,Object> p) {
		String sql= Sql.create("select a.account_id, a.name,a.branch_id,a.phone, r.role_name, b.branch_name from sys_account a left join sys_account_role ar on a.account_id=ar.account_id left join sys_role r on ar.role_id = r.role_id left join sys_branch b on b.branch_id = a.branch_id  where 1=1")
				.append(and("a.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("a.name = :name",hasKey(p,"name")))
				.append(and("a.identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("a.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("a.phone = :phone",hasKey(p,"phone")))
				.append(and("a.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("a.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.email = :email",hasKey(p,"email")))
				.append(and("a.status = :status",hasKey(p,"status")))
				.append(and("a.error_count = :error_count",hasKey(p,"error_count")))
				.append(and("a.deleted = :deleted",hasKey(p,"deleted")))
				.append(and("r.role_id = :role_id",hasKey(p,"role_id")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	/**
	 * 根据用户id查询用户对应机构编号
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> selectBranchIdByAccountId(Map<String,Object> p){
		String sql = "select branch_id from SYS_ACCOUNT where account_id = :account_id ";
		printSql(sql,p);
		return queryForList(sql,p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.branch_name,c.role_id,d.role_name from SYS_ACCOUNT a left join SYS_BRANCH b on a.branch_id=b.branch_id left join SYS_ACCOUNT_ROLE  c on a.account_id=c.account_id left join sys_role d on c.role_id=d.role_id where 1=1")
			.append(and("a.account_id like :account_id",hasKey(p,"account_id")))
						.append(and("a.name like :name",hasKey(p,"name")))
						.append(and("a.identify_no = :identify_no",hasKey(p,"identify_no")))
						.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
                        .append(and("team_id = :team_id",hasKey(p,"team_id")))
						.append(and("a.phone like :phone",hasKey(p,"phone")))
						.append(and("a.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
						.append(and("a.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
						.append(and("a.creator = :creator",hasKey(p,"creator")))
						.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
						.append(and("email = :email",hasKey(p,"email")))
						.append(and("status = :status",hasKey(p,"status")))
						.append(and("error_count = :error_count",hasKey(p,"error_count")))
						.append(and("deleted = :deleted",hasKey(p,"deleted")))
						.append(" order by a.create_time desc")
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
		String sql= Sql.create("select t1.*,t2.branch_name,t4.role_id,t4.role_name from SYS_ACCOUNT t1 left join SYS_BRANCH t2 on t1.branch_id = t2.branch_id " +
				" left join SYS_ACCOUNT_ROLE t3 on t1.account_id = t3.account_id left join SYS_ROLE t4 on t3.role_id = t4.role_id where 1=1 ")
				.append(and("t1.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("t1.name = :name",hasKey(p,"name")))
				.append(and("t1.identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("t1.branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("t1.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("t1.phone = :phone",hasKey(p,"phone")))
				.append(and("t1.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("t1.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.email = :email",hasKey(p,"email")))
				.append(and("t1.status = :status",hasKey(p,"status")))
				.append(and("t1.error_count = :error_count",hasKey(p,"error_count")))
				.append(and("t1.deleted = :deleted",hasKey(p,"deleted")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from SYS_ACCOUNT where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("team_id = :team_id",hasKey(p,"team_id")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("error_count = :error_count",hasKey(p,"error_count")))
				.append(and("deleted = :deleted",hasKey(p,"deleted")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


	//批量插入方法
	public int[] saveList(List<Map<String,Object>> paramlist) {


		Sql sql= Sql.create("");
		sql.append("insert into SYS_ACCOUNT (")
				.append(field("account_id "))
				.append(field("name "))
				.append(field("identify_no "))
				.append(field("branch_id "))
                .append(field("team_id "))
				.append(field("phone "))
				.append(field("login_pwd "))
				.append(field("business_pwd "))
				.append(field("creator "))
				.append(field("create_time "))
				.append(field("email "))
				.append(field("status "))
				.append(field("error_count "))
				.append(field("deleted "))
				.append(") values (")
				.append(field(":account_id "))
				.append(field(":name "))
				.append(field(":identify_no "))
				.append(field(":branch_id "))
                .append(field(":team_id "))
				.append(field(":phone "))
				.append(field(":login_pwd "))
				.append(field(":business_pwd "))
				.append(field(":creator "))
				.append(field(":create_time "))
				.append(field(":email "))
				.append(field(":status "))
				.append(field(":error_count "))
				.append(field(":deleted "))
				.append(")");


		return batchUpdate(sql.toString(), paramlist);
	}

	public int deleteByIds(String ids){
		String sql="update SYS_ACCOUNT set deleted = 1,name = CONCAT(name, '（停用）') where account_id in("+ids+")";
		printSql(sql,null);
		return update(sql,null);

	}

	public int addErrorCount(Map<String,Object> p){
		String sql="update SYS_ACCOUNT set error_count = error_count+1 where account_id =:account_id";
		printSql(sql,p);
		return update(sql,p);
	}

	public int clearErrorCount(Map<String,Object> p){
		String sql="update SYS_ACCOUNT set error_count = 0 where account_id =:account_id";
		printSql(sql,p);
		return update(sql,p);
	}

	/**
	 * 获取支行复核员
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> queryBranchCheckor(Map<String,Object> p){
		String sql="select a.account_id,a.name from (select * from sys_account  where branch_id =:branch_id ) a ,sys_account_role r where a.account_id=r.account_id and r.role_id=:role_id and a.account_id !=:account_id";
		printSql(sql,p);
		return queryForList(sql,p);
	}

	public List<Map<String, Object>> queryForListByconditionBypage(Map<String,Object> p) {
		Sql sql= Sql.create("select a.*,b.branch_name,c.role_id,d.role_name from SYS_ACCOUNT a left join SYS_BRANCH b on a.branch_id=b.branch_id left join SYS_ACCOUNT_ROLE  c on a.account_id=c.account_id left join sys_role d on c.role_id=d.role_id where 1=1")
				.append(and("a.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("a.name like :name",hasKey(p,"name")))
				.append(and("a.identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("a.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("a.phone like :phone",hasKey(p,"phone")))
				.append(and("a.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("a.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.email = :email",hasKey(p,"email")))
				.append(and("a.status = :status",hasKey(p,"status")))
				.append(and("a.error_count = :error_count",hasKey(p,"error_count")))
				.append(and("a.deleted = :deleted",hasKey(p,"deleted")))
				.append(and("c.role_id = :role_id",hasKey(p,"role_id")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		sql.append(and("c.role_id != '2021061500000001'"));

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr= Sql.pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by a.create_time desc").append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 根据角色查询用户
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryByRole(Map<String,Object> p) {
		String sql= Sql.create("select a.account_id,a.name,a.branch_id,a.team_id," +
				"CASE NVL(TO_CHAR(c.branch_name),'0') WHEN '0' THEN a.name ELSE concat(concat(a.name,'-'),c.branch_name) END approval_name from SYS_ACCOUNT a " +
				"left join SYS_ACCOUNT_ROLE b on a.account_id = b.account_id " +
				"left join SYS_BRANCH c on a.branch_id = c.branch_id where 1=1 and a.deleted = 0 ")
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				.append("  order by a.branch_id desc")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	/**
	 * 查询根据角色查询
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListBycondition(Map<String,Object> p) {
		Sql sql= Sql.create("select a.account_id,a.name,a.branch_id from SYS_ACCOUNT a inner join SYS_ACCOUNT_ROLE b on a.account_id=b.account_id " +
				"left join SYS_ROLE c on b.role_id = c.role_id where 1=1 and b.role_id != '2021061500000001' ")
				.append(and("a.account_id like :account_id",hasKey(p,"account_id")))
				.append(and("a.name like :name",hasKey(p,"name")))
				.append(and("a.identify_no = :identify_no",hasKey(p,"identify_no")))
				.append(and("a.branch_id = :branch_id",hasKey(p,"branch_id")))
                .append(and("a.team_id = :team_id",hasKey(p,"team_id")))
				.append(and("a.phone like :phone",hasKey(p,"phone")))
				.append(and("a.login_pwd = :login_pwd",hasKey(p,"login_pwd")))
				.append(and("a.business_pwd = :business_pwd",hasKey(p,"business_pwd")))
				.append(and("a.creator = :creator",hasKey(p,"creator")))
				.append(and("a.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("a.email = :email",hasKey(p,"email")))
				.append(and("a.status = :status",hasKey(p,"status")))
				.append(and("a.error_count = :error_count",hasKey(p,"error_count")))
				.append(and("a.deleted = :deleted",hasKey(p,"deleted")))
				.append(and("b.role_id = :role_id",hasKey(p,"role_id")))
				.append(and("c.role_level = :role_level",hasKey(p,"role_level")))
				;
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("branchids")+")"));
		}
		if(p.containsKey("roleids")&&p.get("roleids")!=null) {
			sql.append(and("b.role_id in ("+ p.get("roleids")+")"));
		}
		if(p.containsKey("rolelevels")&&p.get("rolelevels")!=null) {
			sql.append(and("c.role_level in ("+ p.get("rolelevels")+")"));
		}
        sql.append(" order by a.create_time desc");
        String sqlStr = sql.toString();
        printSql(sqlStr,p);
        return queryForList(sqlStr, p);
	}


}
