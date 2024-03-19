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
 * 表名:T_APPROVAL_DETAILS
 * 主键:
 * id
 **/
@Repository
public class TApprovalDetailsDao extends TXBaseDao {

	/**
	 * 当前所有字段名
	 **/
	String[] fieldNames = new String[]{"id", "approval_field", "approval_details", "create_time", "update_time", "approval_type"};
	/**
	 * 当前主键(包括多主键)
	 **/
	String[] primaryKeys = new String[]{"id"};

	@Override
	public int save(Map<String, Object> p) {
		p.put("id", sequenceService.getTableFlowNo("T_APPROVAL_DETAILS", "id"));
		String sql = Sql.create("insert into T_APPROVAL_DETAILS (")
				.append(field("id "))
				.append(field("approval_field ", hasKey(p, "approval_field")))
				.append(field("approval_details ", hasKey(p, "approval_details")))
				.append(field("approval_type ", hasKey(p, "approval_type")))
				.append(field("create_time ", hasKey(p, "create_time")))
				.append(field("update_time ", hasKey(p, "update_time")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":approval_field ", hasKey(p, "approval_field")))
				.append(field(":approval_details ", hasKey(p, "approval_details")))
				.append(field(":approval_type ", hasKey(p, "approval_type")))
				.append(field(":create_time ", hasKey(p, "create_time")))
				.append(field(":update_time ", hasKey(p, "update_time")))
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
		String sql = Sql.create("delete from T_APPROVAL_DETAILS where 1=1 ")
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
		String sql = Sql.create("delete from T_APPROVAL_DETAILS where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.toString();
		printSql(sql, p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String, Object> p) {
		String sql = Sql.create("update T_APPROVAL_DETAILS set ")
				.append(field("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(field("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String, Object> p) {
		checkParameter(p, primaryKeys, fieldNames);
		String sql = Sql.create("update T_APPROVAL_DETAILS set ")
				.append(field("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(field("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(" where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
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
		String sql = Sql.create("select * from T_APPROVAL_DETAILS where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
		Sql sql = Sql.create("select * from T_APPROVAL_DETAILS where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")));

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
		String sql = Sql.create("select * from T_APPROVAL_DETAILS where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.toString();
		printSql(sql, p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from T_APPROVAL_DETAILS where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("approval_field = :approval_field", hasKey(p, "approval_field")))
				.append(and("approval_details = :approval_details", hasKey(p, "approval_details")))
				.append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.toString();
		printSql(sql, p);
		return count(sql, p);
	}

	public int[] saveList(List<Map<String, Object>> paramlist) {

		List<String> sequenceList = sequenceService.getTableBatchSquence("T_APPROVAL_DETAILS", "id", paramlist.size());

		Sql sql = Sql.create("");
		for (int i = 0; i < paramlist.size(); i++) {
			String id = sequenceList.get(i);
			Map<String, Object> p = paramlist.get(i);
			p.put("id", id);
			sql.append("insert into T_APPROVAL_DETAILS (")

                    .append(field("id "))
                    .append(field("approval_field ", hasKey(p, "approval_field")))
                    .append(field("approval_details ", hasKey(p, "approval_details")))
                    .append(field("approval_type ", hasKey(p, "approval_type")))
                    .append(field("create_time ", hasKey(p, "create_time")))
                    .append(field("update_time ", hasKey(p, "update_time")))
                    .append(") values (")
                    .append(field(":id "))
                    .append(field(":approval_field ", hasKey(p, "approval_field")))
                    .append(field(":approval_details ", hasKey(p, "approval_details")))
                    .append(field(":approval_type ", hasKey(p, "approval_type")))
                    .append(field(":create_time ", hasKey(p, "create_time")))
                    .append(field(":update_time ", hasKey(p, "update_time")))
                    .append("),");
			printSql(sql.toString(), p);
		}

		return batchUpdate(sql.toString(), paramlist);
	}
}