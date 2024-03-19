package com.gdnybank.hnm.pub.dao;

import static com.nantian.mfp.framework.dao.sql.Sql.and;
import static com.nantian.mfp.framework.dao.sql.Sql.field;
import static com.nantian.mfp.framework.dao.sql.Sql.orderBySql;
import static com.nantian.mfp.framework.dao.sql.Sql.pageSql;
import static com.nantian.mfp.framework.dao.sql.Sql.printSql;
import static com.nantian.mfp.framework.dao.sql.Sql.hasKey;
import java.util.List;
import java.util.Map;

import com.nantian.mfp.framework.utils.BaseUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:SMS_SETTING
 * 主键:
 * id
 **/
@Repository
public class SmsSettingDao extends TXBaseDao {

	/**
	 * 当前所有字段名
	 **/
	String[] fieldNames = new String[]{"id", "use_up", "trigger_condition", "sms_pro", "sms_content", "send_role", "create_time", "creater", "update_time", "updater"};
	/**
	 * 当前主键(包括多主键)
	 **/
	String[] primaryKeys = new String[]{"id"};

	@Override
	public int save(Map<String, Object> p) {
		p.put("id", sequenceService.getTableFlowNo("SMS_SETTING", "id"));
		String sql = Sql.create("insert into SMS_SETTING (")
				.append(field("id "))
				.append(field("use_up ", hasKey(p, "use_up")))
				.append(field("trigger_condition ", hasKey(p, "trigger_condition")))
				.append(field("sms_pro ", hasKey(p, "sms_pro")))
				.append(field("sms_content ", hasKey(p, "sms_content")))
				.append(field("send_role ", hasKey(p, "send_role")))
				.append(field("create_time ", hasKey(p, "create_time")))
				.append(field("creater ", hasKey(p, "creater")))
				.append(field("update_time ", hasKey(p, "update_time")))
				.append(field("updater ", hasKey(p, "updater")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":use_up ", hasKey(p, "use_up")))
				.append(field(":trigger_condition ", hasKey(p, "trigger_condition")))
				.append(field(":sms_pro ", hasKey(p, "sms_pro")))
				.append(field(":sms_content ", hasKey(p, "sms_content")))
				.append(field(":send_role ", hasKey(p, "send_role")))
				.append(field(":create_time ", hasKey(p, "create_time")))
				.append(field(":creater ", hasKey(p, "creater")))
				.append(field(":update_time ", hasKey(p, "update_time")))
				.append(field(":updater ", hasKey(p, "updater")))
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
		String sql = Sql.create("delete from SMS_SETTING where 1=1 ")
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
		String sql = Sql.create("delete from SMS_SETTING where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.toString();
		printSql(sql, p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String, Object> p) {
		String sql = Sql.create("update SMS_SETTING set ")
				.append(field("use_up = :use_up", hasKey(p, "use_up")))
				.append(field("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(field("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(field("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(field("send_role = :send_role", hasKey(p, "send_role")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("creater = :creater", hasKey(p, "creater")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("updater = :updater", hasKey(p, "updater")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String, Object> p) {
		checkParameter(p, primaryKeys, fieldNames);
		String sql = Sql.create("update SMS_SETTING set ")
				.append(field("use_up = :use_up", hasKey(p, "use_up")))
				.append(field("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(field("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(field("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(field("send_role = :send_role", hasKey(p, "send_role")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("creater = :creater", hasKey(p, "creater")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("updater = :updater", hasKey(p, "updater")))
				.append(" where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String, Object> p) {

		if (count(BaseUtils.map("id",p.get("id"))) > 0) {
			return updateByPk(p);
		} else {
			return save(p);
		}
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String, Object> p) {
		String sql = Sql.create("select t1.*,t2.role_name from sms_setting t1 " +
				"left join sys_role t2 on t1.send_role = t2.role_id where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.append(orderBySql())
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
		Sql sql = Sql.create("select * from SMS_SETTING where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")));

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
		String sql = Sql.create("select * from SMS_SETTING where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.toString();
		printSql(sql, p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from SMS_SETTING where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("use_up = :use_up", hasKey(p, "use_up")))
				.append(and("trigger_condition = :trigger_condition", hasKey(p, "trigger_condition")))
				.append(and("sms_pro = :sms_pro", hasKey(p, "sms_pro")))
				.append(and("sms_content = :sms_content", hasKey(p, "sms_content")))
				.append(and("send_role = :send_role", hasKey(p, "send_role")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("creater = :creater", hasKey(p, "creater")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("updater = :updater", hasKey(p, "updater")))
				.toString();
		printSql(sql, p);
		return count(sql, p);
	}

}