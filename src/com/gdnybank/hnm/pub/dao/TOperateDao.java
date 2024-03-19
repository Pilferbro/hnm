package com.gdnybank.hnm.pub.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:T_OPERATE
 * 主键:
 * id
 **/
@Repository
public class TOperateDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "operate_no", "operate_name", "operate_day", "operate_rate", "operate_area", "operate_risk", "operate_content", "operate_requirement", "operate_operno", "use_up", "remarks", "spare1", "spare2", "spare3", "operate_time", "operate_condition", "create_time", "update_time", "creator","operate_range"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("id", sequenceService.getTableFlowNo("T_OPERATE", "id"));
        String sql = Sql.create("insert into T_OPERATE (")
                .append(field("id "))
                .append(field("operate_no ", hasKey(p, "operate_no")))
                .append(field("operate_name ", hasKey(p, "operate_name")))
                .append(field("operate_day ", hasKey(p, "operate_day")))
                .append(field("operate_rate ", hasKey(p, "operate_rate")))
                .append(field("operate_area ", hasKey(p, "operate_area")))
                .append(field("operate_risk ", hasKey(p, "operate_risk")))
                .append(field("operate_content ", hasKey(p, "operate_content")))
                .append(field("operate_requirement ", hasKey(p, "operate_requirement")))
                .append(field("operate_operno ", hasKey(p, "operate_operno")))
                .append(field("use_up ", hasKey(p, "use_up")))
                .append(field("remarks ", hasKey(p, "remarks")))
                .append(field("spare1 ", hasKey(p, "spare1")))
                .append(field("spare2 ", hasKey(p, "spare2")))
                .append(field("spare3 ", hasKey(p, "spare3")))
                .append(field("operate_time ", hasKey(p, "operate_time")))
                .append(field("operate_condition ", hasKey(p, "operate_condition")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("update_time ", hasKey(p, "update_time")))
                .append(field("creator ", hasKey(p, "creator")))
                .append(field("operate_range ", hasKey(p, "operate_range")))
                .append(") values (")
                .append(field(":id "))
                .append(field(":operate_no ", hasKey(p, "operate_no")))
                .append(field(":operate_name ", hasKey(p, "operate_name")))
                .append(field(":operate_day ", hasKey(p, "operate_day")))
                .append(field(":operate_rate ", hasKey(p, "operate_rate")))
                .append(field(":operate_area ", hasKey(p, "operate_area")))
                .append(field(":operate_risk ", hasKey(p, "operate_risk")))
                .append(field(":operate_content ", hasKey(p, "operate_content")))
                .append(field(":operate_requirement ", hasKey(p, "operate_requirement")))
                .append(field(":operate_operno ", hasKey(p, "operate_operno")))
                .append(field(":use_up ", hasKey(p, "use_up")))
                .append(field(":remarks ", hasKey(p, "remarks")))
                .append(field(":spare1 ", hasKey(p, "spare1")))
                .append(field(":spare2 ", hasKey(p, "spare2")))
                .append(field(":spare3 ", hasKey(p, "spare3")))
                .append(field(":operate_time ", hasKey(p, "operate_time")))
                .append(field(":operate_condition ", hasKey(p, "operate_condition")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":update_time ", hasKey(p, "update_time")))
                .append(field(":creator ", hasKey(p, "creator")))
                .append(field(":operate_range ", hasKey(p, "operate_range")))
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
        String sql = Sql.create("delete from T_OPERATE where 1=1 ")
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
        String sql = Sql.create("delete from T_OPERATE where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update T_OPERATE set ")
                .append(field("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(field("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(field("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(field("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(field("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(field("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(field("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(field("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(field("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(field("use_up = :use_up", hasKey(p, "use_up")))
                .append(field("remarks = :remarks", hasKey(p, "remarks")))
                .append(field("spare1 = :spare1", hasKey(p, "spare1")))
                .append(field("spare2 = :spare2", hasKey(p, "spare2")))
                .append(field("spare3 = :spare3", hasKey(p, "spare3")))
                .append(field("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(field("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("update_time = :update_time", hasKey(p, "update_time")))
                .append(field("creator = :creator", hasKey(p, "creator")))
                .append(field("operate_range = :operate_range", hasKey(p, "operate_range")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update T_OPERATE set ")
                .append(field("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(field("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(field("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(field("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(field("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(field("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(field("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(field("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(field("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(field("use_up = :use_up", hasKey(p, "use_up")))
                .append(field("remarks = :remarks", hasKey(p, "remarks")))
                .append(field("spare1 = :spare1", hasKey(p, "spare1")))
                .append(field("spare2 = :spare2", hasKey(p, "spare2")))
                .append(field("spare3 = :spare3", hasKey(p, "spare3")))
                .append(field("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(field("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("update_time = :update_time", hasKey(p, "update_time")))
                .append(field("creator = :creator", hasKey(p, "creator")))
                .append(field("operate_range = :operate_range", hasKey(p, "operate_range")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
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
        String sql = Sql.create("select * from T_OPERATE where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
                .append(" order by operate_no ")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from T_OPERATE where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name like :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
                ;

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = Sql.pageSqlInOracle(sql.append(" order by create_time ").toString());
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        } else {
            long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sql.append(" order by create_time ").append(pageSql());
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
        String sql = Sql.create("select * from T_OPERATE where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from T_OPERATE where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("operate_no = :operate_no", hasKey(p, "operate_no")))
                .append(and("operate_name = :operate_name", hasKey(p, "operate_name")))
                .append(and("operate_day = :operate_day", hasKey(p, "operate_day")))
                .append(and("operate_rate = :operate_rate", hasKey(p, "operate_rate")))
                .append(and("operate_area = :operate_area", hasKey(p, "operate_area")))
                .append(and("operate_risk = :operate_risk", hasKey(p, "operate_risk")))
                .append(and("operate_content = :operate_content", hasKey(p, "operate_content")))
                .append(and("operate_requirement = :operate_requirement", hasKey(p, "operate_requirement")))
                .append(and("operate_operno = :operate_operno", hasKey(p, "operate_operno")))
                .append(and("use_up = :use_up", hasKey(p, "use_up")))
                .append(and("remarks = :remarks", hasKey(p, "remarks")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("spare3 = :spare3", hasKey(p, "spare3")))
                .append(and("operate_time = :operate_time", hasKey(p, "operate_time")))
                .append(and("operate_condition = :operate_condition", hasKey(p, "operate_condition")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("operate_range = :operate_range", hasKey(p, "operate_range")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }


}
