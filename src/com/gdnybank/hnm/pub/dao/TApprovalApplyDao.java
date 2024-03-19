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
 * 表名:T_APPROVAL_APPLY
 * 主键:
 * id
 **/
@Repository
public class TApprovalApplyDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "approval_type", "approval_status", "approval_status_name", "operator", "relation_id", "create_time", "update_time", "update_items"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("id", sequenceService.getTableFlowNo("T_APPROVAL_APPLY", "id"));
        String sql = Sql.create("insert into T_APPROVAL_APPLY (")
                .append(field("id "))
                .append(field("approval_type ", hasKey(p, "approval_type")))
                .append(field("approval_status ", hasKey(p, "approval_status")))
                .append(field("approval_status_name ", hasKey(p, "approval_status_name")))
                .append(field("operator ", hasKey(p, "operator")))
                .append(field("relation_id ", hasKey(p, "relation_id")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("update_time ", hasKey(p, "update_time")))
                .append(field("update_items ", hasKey(p, "update_items")))
                .append(") values (")
                .append(field(":id "))
                .append(field(":approval_type ", hasKey(p, "approval_type")))
                .append(field(":approval_status ", hasKey(p, "approval_status")))
                .append(field(":approval_status_name ", hasKey(p, "approval_status_name")))
                .append(field(":operator ", hasKey(p, "operator")))
                .append(field(":relation_id ", hasKey(p, "relation_id")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":update_time ", hasKey(p, "update_time")))
                .append(field(":update_items ", hasKey(p, "update_items")))
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
        String sql = Sql.create("delete from T_APPROVAL_APPLY where 1=1 ")
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
        String sql = Sql.create("delete from T_APPROVAL_APPLY where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update T_APPROVAL_APPLY set ")
                .append(field("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(field("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(field("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(field("operator = :operator", hasKey(p, "operator")))
                .append(field("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("update_time = :update_time", hasKey(p, "update_time")))
                .append(field("update_items = :update_items", hasKey(p, "update_items")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update T_APPROVAL_APPLY set ")
                .append(field("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(field("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(field("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(field("operator = :operator", hasKey(p, "operator")))
                .append(field("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("update_time = :update_time", hasKey(p, "update_time")))
                .append(field("update_items = :update_items", hasKey(p, "update_items")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int saveOrUpdate(Map<String, Object> p) {

        return 0;
    }

    @Override
    public List<Map<String, Object>> queryForList(Map<String, Object> p) {
        String sql = Sql.create("select * from T_APPROVAL_APPLY where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from T_APPROVAL_APPLY where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")));

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = pageSqlInOracle(sql.append(orderBySql()).toString());
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
        String sql = Sql.create("select * from T_APPROVAL_APPLY where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from T_APPROVAL_APPLY where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> queryForFlow(Map<String, Object> p) {
        Sql sql = Sql.create("select a.*,b.approval_type_name,c.gt_approval_user,c.approval_user from T_APPROVAL_APPLY a left join T_APPROVAL_TYPE b on a.approval_type = b.approval_type " +
                " left join T_APPROVAL c on a.id = c.apply_id where 1=1")
                .append(and("a.id = :id", hasKey(p, "id")))
                .append(and("a.approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("a.approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("a.approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("a.operator = :operator", hasKey(p, "operator")))
                .append(and("a.relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("a.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("a.create_time >= :start_date", hasKey(p, "start_date")))
                .append(and("a.create_time <= :end_date", hasKey(p, "end_date")))
                .append(and("a.update_time = :update_time", hasKey(p, "update_time")))
                .append(and("a.update_items = :update_items", hasKey(p, "update_items")));

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
        //获取数据库类型
    }

    public List<Map<String, Object>> queryForListBycondition(Map<String, Object> p) {
        Sql sql = Sql.create("select a.*,b.approval_type_name,c.gt_approval_user,c.approval_user,d.name from T_APPROVAL_APPLY a left join T_APPROVAL_TYPE b on a.approval_type = b.approval_type " +
                " left join T_APPROVAL c on a.id = c.apply_id left join SYS_ACCOUNT d on d.account_id = c.approval_user where 1=1")
                .append(and("a.id = :id", hasKey(p, "id")))
                .append(and("a.approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("a.approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("a.approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("a.operator = :operator", hasKey(p, "operator")))
                .append(and("a.relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("a.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("a.create_time >= :start_date", hasKey(p, "start_date")))
                .append(and("a.create_time <= :end_date", hasKey(p, "end_date")))
                .append(and("a.update_time = :update_time", hasKey(p, "update_time")))
                .append(and("a.update_items = :update_items", hasKey(p, "update_items")));

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = pageSqlInOracle(sql.append(" order by a.create_time desc").toString());
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        } else {
            long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sql.append(" order by a.create_time desc").append(pageSql());
            sqlStr = sql.toString();
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        }
    }

    public int updateStatus(Map<String, Object> p) {
        String sql = Sql.create("update T_APPROVAL_APPLY set ")
                .append(field("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(field("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    public List<Map<String, Object>> queryForListForHis(Map<String, Object> p) {
        String sql = Sql.create("select t1.*,t2.name,t3.approval_type_name from T_APPROVAL_APPLY t1 left join SYS_ACCOUNT t2 on t1.operator = t2.account_id left join T_APPROVAL_TYPE t3 on t1.approval_type = t3.approval_type " +
                " where 1=1 ")
                .append(and("t1.id = :id", hasKey(p, "id")))
                .append(and("t1.approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("t1.approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("t1.approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("t1.operator = :operator", hasKey(p, "operator")))
                .append(and("t1.relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
                .append(and("t1.update_items = :update_items", hasKey(p, "update_items")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryForUnload(Map<String, Object> p) {
        String sql = Sql.create("select * from T_APPROVAL_APPLY where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type = :approval_type", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryForRoleList(Map<String, Object> p) {
        String sql = Sql.create("select * from T_APPROVAL_APPLY where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("approval_type in (" + p.get("approval_type") + ")", hasKey(p, "approval_type")))
                .append(and("approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("approval_status_name = :approval_status_name", hasKey(p, "approval_status_name")))
                .append(and("operator = :operator", hasKey(p, "operator")))
                .append(and("relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("update_items = :update_items", hasKey(p, "update_items")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}
