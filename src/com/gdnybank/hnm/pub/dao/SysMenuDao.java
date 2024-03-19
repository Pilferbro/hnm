package com.gdnybank.hnm.pub.dao;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:SYS_MENU
 * 主键:menu_code menuid
 **/
@Repository
public class SysMenuDao extends TXBaseDao {

	//修改
	/**当前所有字段名**/
	String[] fieldNames=new String[]{"menu_code","menu_name","super_code","menu_path","creator","create_time","icon","menu_type"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"menu_code"};


	/**
	 * 查询当前待删除的菜单是否存在子菜单
	 * @param p
	 * @return
	 */
	public List<Map<String,Object>> selectIsExistSubMenu(Map<String,Object> p){
		String sql = "select * from SYS_MENU where super_code = :menu_code";
		printSql(sql,p);
		return queryForList(sql,p);
	}

	@Override
	public int save(Map<String,Object> p) {
				p.put("menu_code",sequenceService.getTableFlowNo("SYS_MENU", "menu_code"));
		String sql= Sql.create("insert into SYS_MENU (")
				.append(field("menu_code "))
				.append(field("menu_name ",hasKey(p,"menu_name")))
				.append(field("super_code ",hasKey(p,"super_code")))
				.append(field("menu_path ",hasKey(p,"menu_path")))
				.append(field("icon ",hasKey(p,"icon")))
				.append(field("creator ",hasKey(p,"creator")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("menu_type ",hasKey(p,"menu_type")))
				.append(") values (")
				.append(field(":menu_code "))
				.append(field(":menu_name ",hasKey(p,"menu_name")))
				.append(field(":super_code ",hasKey(p,"super_code")))
				.append(field(":menu_path ",hasKey(p,"menu_path")))
				.append(field(":icon ",hasKey(p,"icon")))
				.append(field(":creator ",hasKey(p,"creator")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":menu_type ",hasKey(p,"menu_type")))
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
		String sql= Sql.create("delete from SYS_MENU where 1=1 ")
				.append(and("menu_code = :menu_code"))
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
		String sql= Sql.create("delete from SYS_MENU where 1=1 ")
				.append(and("menu_code = :menu_code",hasKey(p,"menu_code")))
				.append(and("menu_name = :menu_name",hasKey(p,"menu_name")))
				.append(and("super_code = :super_code",hasKey(p,"super_code")))
				.append(and("menu_path = :menu_path",hasKey(p,"menu_path")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("icon = :icon",hasKey(p,"icon")))
				.append(and("menu_type = :menu_type",hasKey(p,"menu_type")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update SYS_MENU set ")
				.append(field("menu_name = :menu_name",hasKey(p,"menu_name")))
				.append(field("super_code = :super_code",hasKey(p,"super_code")))
				.append(field("menu_path = :menu_path",hasKey(p,"menu_path")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("menu_type = :create_time",hasKey(p,"menu_type")))
				.append(" where 1=1 ")
				.append(and("menu_code = :menu_code"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql= Sql.create("update SYS_MENU set ")
				.append(field("menu_name = :menu_name",hasKey(p,"menu_name")))
				.append(field("super_code = :super_code",hasKey(p,"super_code")))
				.append(field("menu_path = :menu_path",hasKey(p,"menu_path")))
				.append(field("icon = :icon",hasKey(p,"icon")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("menu_type = :menu_type",hasKey(p,"menu_type")))
				.append(" where 1=1 ")
				.append(and("menu_code = :menu_code",hasKey(p,"menu_code")))
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
		String sql= Sql.create("select * from SYS_MENU where 1=1")
				.append(and("menu_code = :menu_code",hasKey(p,"menu_code")))
				.append(and("menu_name = :menu_name",hasKey(p,"menu_name")))
				.append(and("super_code = :super_code",hasKey(p,"super_code")))
				.append(and("menu_path = :menu_path",hasKey(p,"menu_path")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("icon = :icon",hasKey(p,"icon")))
				.append(and("menu_type = :menu_type",hasKey(p,"menu_type")))
				.append(" order by create_time asc")
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql= Sql.create("select * from SYS_MENU where 1=1")
			.append(and("menu_code = :menu_code",hasKey(p,"menu_code")))
						.append(and("menu_name = :menu_name",hasKey(p,"menu_name")))
						.append(and("super_code = :super_code",hasKey(p,"super_code")))
						.append(and("menu_path = :menu_path",hasKey(p,"menu_path")))
						.append(and("creator = :creator",hasKey(p,"creator")))
						.append(and("create_time = :create_time",hasKey(p,"create_time")))
						.append(and("icon = :icon",hasKey(p,"icon")))
						.append(and("menu_type = :menu_type",hasKey(p,"menu_type")))
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



	public List<Map<String, Object>> queryMenuInfo(Map<String,Object> p) {
		String sql = "select menu_code,menu_name,super_code,menu_path,icon,menu_type from SYS_MENU where 1 = 1 ";

		if ( !StringUtils.isEmpty(p.get("menu_code"))){
			sql += " and menu_code like " + "'" +  "%" + p.get("menu_code") + "%" + "'";
		}

		if ( !StringUtils.isEmpty(p.get("menu_name"))){
			sql += " and menu_name like " + "'" +  "%" + p.get("menu_name") + "%" + "'";
		}

		sql += "order by create_time";
		printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from SYS_MENU where 1=1 ")
				.append(and("menu_code = :menu_code",hasKey(p,"menu_code")))
				.append(and("menu_name = :menu_name",hasKey(p,"menu_name")))
				.append(and("super_code = :super_code",hasKey(p,"super_code")))
				.append(and("menu_path = :menu_path",hasKey(p,"menu_path")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("icon = :icon",hasKey(p,"icon")))
				.append(and("menu_type = :menu_type",hasKey(p,"menu_type")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


	//批量插入方法
	public int[] saveList(List<Map<String,Object>> paramlist) {

			Sql sql= Sql.create("");
			sql.append("insert into SYS_MENU (")
				.append(field("menu_code "))
				.append(field("menuid "))
				.append(field("menu_name "))
				.append(field("super_code "))
				.append(field("menu_path "))
				.append(field("creator "))
				.append(field("create_time "))
				.append(field("icon "))
				.append(field("menu_type "))
			.append(") values (")
				.append(field(":menu_code "))
				.append(field(":menuid "))
				.append(field(":menu_name "))
				.append(field(":super_code "))
				.append(field(":menu_path "))
				.append(field(":creator "))
				.append(field(":create_time "))
				.append(field(":icon "))
				.append(field(":menu_type "))
			.append(")");

		return batchUpdate(sql.toString(), paramlist);
	}

	public List<Map<String,Object>> queryMenuByRole(Map<String,Object> p){
		String sql="select n.* from (select distinct m.menu_code from (select * from sys_account_role where account_id=:account_id) a,sys_role_menu m where a.role_id=m.role_id) d,sys_menu n where d.menu_code=n.menu_code and n.menu_type = :menu_type order by n.create_time asc";
		printSql(sql,p);
		return queryForList(sql,p);
	}

	public List<Map<String,Object>> queryMobileMenuByRole(Map<String,Object> p){
		String sql="select n.* from (select distinct m.menu_code from (select * from sys_account_role where account_id=:account_id) a,sys_role_mobile_menu m where a.role_id=m.role_id) d,sys_menu n where d.menu_code=n.menu_code and n.menu_type = :menu_type order by n.create_time asc";
		printSql(sql,p);
		return queryForList(sql,p);
	}

	public List<Map<String,Object>> queryShoperMenu(Map<String,Object> p){
		String sql="select m.* from (select * from sys_role_menu where role_id=:role_id) a left join sys_menu m on a.menu_code=m.menu_code order by m.create_time asc";
		printSql(sql,p);
		return queryForList(sql,p);
	}

}
