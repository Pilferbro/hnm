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
 * 表名:SYS_ROLE
 * 主键:role_id
 **/
@Repository
public class SysRoleDao extends TXBaseDao {

    //修改
    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"role_id", "role_name", "role_desc", "creator", "create_time", "role_type", "role_level","deleted"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"role_id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("role_id", sequenceService.getTableFlowNo("SYS_ROLE", "role_id"));
        String sql = Sql.create("insert into SYS_ROLE (")
                .append(field("role_id "))
                .append(field("role_name ", hasKey(p, "role_name")))
                .append(field("role_desc ", hasKey(p, "role_desc")))
                .append(field("creator ", hasKey(p, "creator")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("role_type ", hasKey(p, "role_type")))
                .append(field("role_level ", hasKey(p, "role_level")))
                .append(field("deleted ", hasKey(p, "deleted")))
                .append(") values (")
                .append(field(":role_id "))
                .append(field(":role_name ", hasKey(p, "role_name")))
                .append(field(":role_desc ", hasKey(p, "role_desc")))
                .append(field(":creator ", hasKey(p, "creator")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":role_type ", hasKey(p, "role_type")))
                .append(field(":role_level ", hasKey(p, "role_level")))
                .append(field(":deleted ", hasKey(p, "deleted")))
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
        String sql = Sql.create("delete from SYS_ROLE where 1=1 ")
                .append(and("role_id = :role_id"))
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
        String sql = Sql.create("delete from SYS_ROLE where 1=1 ")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type ", hasKey(p, "role_type")))
                .append(and("role_level = :role_level ", hasKey(p, "role_level")))
                .append(and("deleted = :deleted ", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update SYS_ROLE set ")
				.append(field("role_name = :role_name",hasKey(p,"role_name")))
				.append(field("role_desc = :role_desc",hasKey(p,"role_desc")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("role_type = :role_type ",hasKey(p,"role_type")))
				.append(field("role_level = :role_level ",hasKey(p,"role_level")))
				.append(field("deleted = :deleted ",hasKey(p,"deleted")))
				.append(" where 1=1 ")
				.append(and("role_id = :role_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
    public int updateTempByPk(Map<String,Object> p) {
        String sql= Sql.create("update SYS_ROLE_TEMP set ")
                .append(field("role_name = :role_name",hasKey(p,"role_name")))
                .append(field("role_desc = :role_desc",hasKey(p,"role_desc")))
                .append(field("creator = :creator",hasKey(p,"creator")))
                .append(field("create_time = :create_time",hasKey(p,"create_time")))
                .append(field("role_type = :role_type ",hasKey(p,"role_type")))
                .append(field("role_level = :role_level ",hasKey(p,"role_level")))
                .append(field("deleted = :deleted ",hasKey(p,"deleted")))
                .append(field("update_id = :update_id ",hasKey(p,"update_id")))
                .append(" where 1=1 ")
                .append(and("role_id = :role_id"))
                .toString();
        printSql(sql,p);
        return update(sql, p);
    }

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql= Sql.create("update SYS_ROLE set ")
				.append(field("role_name = :role_name",hasKey(p,"role_name")))
				.append(field("role_desc = :role_desc",hasKey(p,"role_desc")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("role_type = :role_type ",hasKey(p,"role_type")))
				.append(field("role_level = :role_level ",hasKey(p,"role_level")))
				.append(field("deleted = :deleted ",hasKey(p,"deleted")))
				.append(" where 1=1 ")
				.append(and("role_id = :role_id",hasKey(p,"role_id")))
				.append(and("role_name = :role_name",hasKey(p,"role_name")))
				.append(and("role_desc = :role_desc",hasKey(p,"role_desc")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("role_type = :role_type ",hasKey(p,"role_type")))
				.append(and("role_level = :role_level ",hasKey(p,"role_level")))
				.append(and("deleted = :deleted ",hasKey(p,"deleted")))
				.toString();
		printSql(sql,p);
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
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql= Sql.create("select * from SYS_ROLE where 1=1")
				.append(and("role_id = :role_id",hasKey(p,"role_id")))
				.append(and("role_name = :role_name",hasKey(p,"role_name")))
				.append(and("role_desc = :role_desc",hasKey(p,"role_desc")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("role_type = :role_type",hasKey(p,"role_type")))
				.append(and("role_level = :role_level",hasKey(p,"role_level")))
				.append(and("deleted = :deleted",hasKey(p,"deleted")))
				.append(" order by create_time asc")
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryAdminRole(Map<String,Object> p) {
		String sql= Sql.create("select * from SYS_ROLE where 1=1")
				.append(and("role_id != :role_id1",hasKey(p,"role_id1")))
				.append(and("role_id != :role_id2",hasKey(p,"role_id2")))
				.append(and("role_name = :role_name",hasKey(p,"role_name")))
				.append(and("role_desc = :role_desc",hasKey(p,"role_desc")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("role_type = :role_type",hasKey(p,"role_type")))
				.append(and("role_level = :role_level",hasKey(p,"role_level")))
				.append(and("deleted = :deleted",hasKey(p,"deleted")))
				.append(" order by create_time asc")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

    public List<Map<String, Object>> queryBranchRole(Map<String, Object> p) {
        String sql = Sql.create("select * from SYS_ROLE where (role_id !=:super_manager and role_id !=:branch_manager) and role_type =:role_type order by create_time asc")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from SYS_ROLE where 1=1 and role_id != '2021061500000001'")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_id != :super_role_id", hasKey(p, "super_role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type", hasKey(p, "role_type")))
                .append(and("role_level = :role_level", hasKey(p, "role_level")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .append(" order by role_id asc");

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
        String sql = Sql.create("select * from SYS_ROLE where 1=1 ")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type", hasKey(p, "role_type")))
                .append(and("role_level = :role_level", hasKey(p, "role_level")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from SYS_ROLE where 1=1 ")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type", hasKey(p, "role_type")))
                .append(and("role_level = :role_level", hasKey(p, "role_level")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> queryForListByLevel(Map<String, Object> p) {
        String sql = Sql.create("select * from SYS_ROLE where 1=1")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type", hasKey(p, "role_type")))
                .append(and("role_level in (" + p.get("role_level") + ")", hasKey(p, "role_level")))
                .append(and("deleted = :deleted",hasKey(p,"deleted")))
                .append(" order by create_time asc")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public int saveForRoleId(Map<String, Object> p) {
        p.put("role_id", sequenceService.getTableSquence("SYS_ROLE_TEMP", "role_id"));
        String sql = Sql.create("insert into SYS_ROLE_TEMP (")
                .append(field("role_id "))
                .append(field("role_name ", hasKey(p, "role_name")))
                .append(field("role_desc ", hasKey(p, "role_desc")))
                .append(field("creator ", hasKey(p, "creator")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("role_type ", hasKey(p, "role_type")))
                .append(field("role_level ", hasKey(p, "role_level")))
                .append(field("update_id ", hasKey(p, "update_id")))
                .append(") values (")
                .append(field(":role_id "))
                .append(field(":role_name ", hasKey(p, "role_name")))
                .append(field(":role_desc ", hasKey(p, "role_desc")))
                .append(field(":creator ", hasKey(p, "creator")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":role_type ", hasKey(p, "role_type")))
                .append(field(":role_level ", hasKey(p, "role_level")))
                .append(field(":update_id ", hasKey(p, "update_id")))
                .append(")")
                .toString();
        printSql(sql, p);
        return save(sql, p);
    }

    public List<Map<String, Object>> queryTemp(Map<String, Object> p) {
        String sql = Sql.create("select * from SYS_ROLE_TEMP where 1=1")
                .append(and("role_id = :role_id", hasKey(p, "role_id")))
                .append(and("role_name = :role_name", hasKey(p, "role_name")))
                .append(and("role_desc = :role_desc", hasKey(p, "role_desc")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("role_type = :role_type", hasKey(p, "role_type")))
                .append(and("role_level = :role_level",hasKey(p,"role_level")))
                .append(and("update_id = :update_id",hasKey(p,"update_id")))
                .append(" order by create_time asc")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}
