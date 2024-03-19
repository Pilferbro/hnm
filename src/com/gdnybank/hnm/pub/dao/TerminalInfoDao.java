package com.gdnybank.hnm.pub.dao;

import static com.nantian.mfp.framework.dao.sql.Sql.and;
import static com.nantian.mfp.framework.dao.sql.Sql.field;
import static com.nantian.mfp.framework.dao.sql.Sql.orderBySql;
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
 * 表名:TERMINAL_INFO
 * 主键:
 * id
 **/
@Repository
public class TerminalInfoDao extends TXBaseDao {

	/**
	 * 当前所有字段名
	 **/
	String[] fieldNames = new String[]{"id", "use_up", "app_version", "terminal_name", "create_time", "creater", "update_time", "updater", "is_delete","distance","ctrl_no"};
	/**
	 * 当前主键(包括多主键)
	 **/
	String[] primaryKeys = new String[]{"id"};

	@Override
	public int save(Map<String, Object> p) {
		p.put("id", sequenceService.getTableFlowNo("TERMINAL_INFO", "id"));
		String sql = Sql.create("insert into TERMINAL_INFO (")
				.append(field("id "))
				.append(field("use_up ", hasKey(p, "use_up")))
				.append(field("app_version ", hasKey(p, "app_version")))
				.append(field("terminal_name ", hasKey(p, "terminal_name")))
				.append(field("distance ", hasKey(p, "distance")))
				.append(field("create_time ", hasKey(p, "create_time")))
				.append(field("creater ", hasKey(p, "creater")))
				.append(field("update_time ", hasKey(p, "update_time")))
				.append(field("updater ", hasKey(p, "updater")))
				.append(field("is_delete ", hasKey(p, "is_delete")))
				.append(field("ctrl_no ", hasKey(p, "ctrl_no")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":use_up ", hasKey(p, "use_up")))
				.append(field(":app_version ", hasKey(p, "app_version")))
				.append(field(":terminal_name ", hasKey(p, "terminal_name")))
				.append(field(":distance ", hasKey(p, "distance")))
				.append(field(":create_time ", hasKey(p, "create_time")))
				.append(field(":creater ", hasKey(p, "creater")))
				.append(field(":update_time ", hasKey(p, "update_time")))
				.append(field(":updater ", hasKey(p, "updater")))
				.append(field(":is_delete ", hasKey(p, "is_delete")))
				.append(field(":ctrl_no ", hasKey(p, "ctrl_no")))
				.append(")")
				.toString();
		printSql(sql, p);
		return save(sql, p);
	}

	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String, Object> p) {
		String sql = Sql.create("delete from TERMINAL_INFO where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql, p);
		return delete(sql, p);
	}

	/***
	 * 删除一条或者多条数据,慎用此函数.
	 * 此函数当传入的条件为空的时候,有可能会导致整张数据表被删除,因此,在执行此函数前,请一定对传入的参数进行非空检验,以防数据被误删.
	 * 项目组务必对使用此函数的代码进行代码走查,防止漏洞发生,防止被攻击.
	 **/
	@Override
	public int delete(Map<String, Object> p) {
		checkParameter(p, primaryKeys, fieldNames);
		String sql = Sql.create("delete from TERMINAL_INFO where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.toString();
		printSql(sql, p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String, Object> p) {
		String sql = Sql.create("update TERMINAL_INFO set ")
				.append(field("use_up = :use_up", hasKey(p, "use_up")))
				.append(field("app_version = :app_version", hasKey(p, "app_version")))
				.append(field("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(field("distance = :distance", hasKey(p, "distance")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("creater = :creater", hasKey(p, "creater")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("updater = :updater", hasKey(p, "updater")))
				.append(field("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(field("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String, Object> p) {
		checkParameter(p, primaryKeys, fieldNames);
		String sql = Sql.create("update TERMINAL_INFO set ")
				.append(field("use_up = :use_up", hasKey(p, "use_up")))
				.append(field("app_version = :app_version", hasKey(p, "app_version")))
				.append(field("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(field("distance = :distance", hasKey(p, "distance")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("creater = :creater", hasKey(p, "creater")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("updater = :updater", hasKey(p, "updater")))
				.append(field("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(field("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.append(" where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String, Object> p) {

		if (count(p) > 0) {
			return update(p);
		} else {
			return save(p);
		}
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String, Object> p) {
		String sql = Sql.create("select * from TERMINAL_INFO where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.append(orderBySql())
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
		Sql sql = Sql.create("select id,use_up,app_version,terminal_name from TERMINAL_INFO where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")));

		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		//获取数据库类型
		String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
			long count = count("select count(*) from (" + sqlStr + ")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr = Sql.pageSqlInOracle(sql.append(orderBySql()).toString());
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		} else {
			long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(orderBySql()).append(pageSql());
			sqlStr = sql.toString();
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 查询一条唯一记录,如果没有查到或者查询到两条以上记录会报异常,服务层应该扑捉此类异常进行特别处理.
	 *
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 * @see EmptyResultDataAccessException
	 * @see IncorrectResultSizeDataAccessException
	 ***/
	@Override
	public Map<String, Object> queryForMap(Map<String, Object> p) {
		String sql = Sql.create("select * from TERMINAL_INFO where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.toString();
		printSql(sql, p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from TERMINAL_INFO where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("app_version = :app_version", hasKey(p, "app_version")))
				.append(and("terminal_name = :terminal_name", hasKey(p, "terminal_name")))
				.append(and("distance = :distance", hasKey(p, "distance")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(and("is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.toString();
		printSql(sql, p);
		return count(sql, p);
	}

	public int updateDistanc(Map<String, Object> p) {
		String sql = Sql.create("update TERMINAL_INFO set ")
				.append(field("distance = :distance", hasKey(p, "distance")))
				.append(field("ctrl_no = :ctrl_no", hasKey(p, "ctrl_no")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("updater = :updater", hasKey(p, "updater")))

				.toString();
		printSql(sql, p);
		return update(sql, p);
	}
}