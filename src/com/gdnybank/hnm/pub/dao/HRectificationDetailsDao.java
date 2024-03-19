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
 * 表名:H_RECTIFICATION_DETAILS
 * 主键:
 **/
@Repository
public class HRectificationDetailsDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "patrol_lc_id", "status", "schedule", "source", "written", "updatetime", "risk_photo", "tmp_status", "deleted"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into H_RECTIFICATION_DETAILS (")
                .append(field("id ", hasKey(p, "id")))
                .append(field("patrol_lc_id ", hasKey(p, "patrol_lc_id")))
                .append(field("status ", hasKey(p, "status")))
                .append(field("schedule ", hasKey(p, "schedule")))
                .append(field("source ", hasKey(p, "source")))
                .append(field("written ", hasKey(p, "written")))
                .append(field("risk_photo ", hasKey(p, "risk_photo")))
                .append(field("updatetime ", hasKey(p, "updatetime")))
                .append(field("tmp_status ", hasKey(p, "tmp_status")))
                .append(field("deleted ", hasKey(p, "deleted")))
                .append(") values (")
                .append(field(":id ", hasKey(p, "id")))
                .append(field(":patrol_lc_id ", hasKey(p, "patrol_lc_id")))
                .append(field(":status ", hasKey(p, "status")))
                .append(field(":schedule ", hasKey(p, "schedule")))
                .append(field(":source ", hasKey(p, "source")))
                .append(field(":written ", hasKey(p, "written")))
                .append(field(":risk_photo ", hasKey(p, "risk_photo")))
                .append(field(":updatetime ", hasKey(p, "updatetime")))
                .append(field(":tmp_status ", hasKey(p, "tmp_status")))
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
        String sql = Sql.create("delete from H_RECTIFICATION_DETAILS where 1=1 ")
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
        String sql = Sql.create("delete from H_RECTIFICATION_DETAILS where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update H_RECTIFICATION_DETAILS set ")
                .append(field("id = :id", hasKey(p, "id")))
                .append(field("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(field("status = :status", hasKey(p, "status")))
                .append(field("schedule = :schedule", hasKey(p, "schedule")))
                .append(field("source = :source", hasKey(p, "source")))
                .append(field("written = :written", hasKey(p, "written")))
                .append(field("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(field("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(field("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(field("deleted = :deleted", hasKey(p, "deleted")))
                .append(" where 1=1 ")
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update H_RECTIFICATION_DETAILS set ")
                .append(field("id = :id", hasKey(p, "id")))
                .append(field("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(field("status = :status", hasKey(p, "status")))
                .append(field("schedule = :schedule", hasKey(p, "schedule")))
                .append(field("source = :source", hasKey(p, "source")))
                .append(field("written = :written", hasKey(p, "written")))
                .append(field("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(field("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(field("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(field("deleted = :deleted", hasKey(p, "deleted")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
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
        String sql = Sql.create("select * from H_RECTIFICATION_DETAILS where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from H_RECTIFICATION_DETAILS where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")));

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
        String sql = Sql.create("select * from H_RECTIFICATION_DETAILS where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);

        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from H_RECTIFICATION_DETAILS where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("schedule = :schedule", hasKey(p, "schedule")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("written = :written", hasKey(p, "written")))
                .append(and("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public int updateById(Map<String, Object> p) {
        String sql = Sql.create("update H_RECTIFICATION_DETAILS set ")
                .append(field("id = :id", hasKey(p, "id")))
                .append(field("patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(field("status = :status", hasKey(p, "status")))
                .append(field("schedule = :schedule", hasKey(p, "schedule")))
                .append(field("source = :source", hasKey(p, "source")))
                .append(field("written = :written", hasKey(p, "written")))
                .append(field("risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(field("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(field("tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(field("deleted = :deleted", hasKey(p, "deleted")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    public List<Map<String, Object>> queryByApplyId(Map<String, Object> p) {
        String sql = Sql.create("select t1.*,t2.approval_status_name from H_RECTIFICATION_DETAILS t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id = t2.relation_id where 1=1 ")
                .append(and("t2.id = :id", hasKey(p, "id")))
                .append(and("t2.relation_id = :relation_id", hasKey(p, "relation_id")))
                .append(and("t1.patrol_lc_id = :patrol_lc_id", hasKey(p, "patrol_lc_id")))
                .append(and("t1.status = :status", hasKey(p, "status")))
                .append(and("t1.schedule = :schedule", hasKey(p, "schedule")))
                .append(and("t1.source = :source", hasKey(p, "source")))
                .append(and("t1.written = :written", hasKey(p, "written")))
                .append(and("t1.risk_photo = :risk_photo", hasKey(p, "risk_photo")))
                .append(and("t1.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("t1.tmp_status = :tmp_status", hasKey(p, "tmp_status")))
                .append(and("t1.deleted = :deleted", hasKey(p, "deleted")))
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}
