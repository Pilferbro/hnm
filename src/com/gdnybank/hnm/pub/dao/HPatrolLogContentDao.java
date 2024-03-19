package com.gdnybank.hnm.pub.dao;

import cn.hutool.core.util.ObjectUtil;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import oracle.net.aso.h;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:H_PATROL_LOG_CONTENT
 * 主键:
 * id
 **/
@Repository
public class HPatrolLogContentDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "patrol_id", "site_no", "indexs", "content_text", "risk_level", "end_date", "requirement", "created", "responsible", "source", "isdeleted", "updatetime", "pj_classify", "discoverer", "spare1", "spare2"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("id", sequenceService.getTableFlowNo("H_PATROL_LOG_CONTENT", "id"));
        String sql = Sql.create("insert into H_PATROL_LOG_CONTENT (")
                .append(field("id "))
                .append(field("patrol_id ", hasKey(p, "patrol_id")))
                .append(field("site_no ", hasKey(p, "site_no")))
                .append(field("indexs ", hasKey(p, "indexs")))
                .append(field("content_text ", hasKey(p, "content_text")))
                .append(field("risk_level ", hasKey(p, "risk_level")))
                .append(field("end_date ", hasKey(p, "end_date")))
                .append(field("requirement ", hasKey(p, "requirement")))
                .append(field("created ", hasKey(p, "created")))
                .append(field("responsible ", hasKey(p, "responsible")))
                .append(field("source ", hasKey(p, "source")))
                .append(field("isdeleted ", hasKey(p, "isdeleted")))
                .append(field("updatetime ", hasKey(p, "updatetime")))
                .append(field("pj_classify ", hasKey(p, "pj_classify")))
                .append(field("discoverer ", hasKey(p, "discoverer")))
                .append(field("spare1 ", hasKey(p, "spare1")))
                .append(field("spare2 ", hasKey(p, "spare2")))
                .append(") values (")
                .append(field(":id "))
                .append(field(":patrol_id ", hasKey(p, "patrol_id")))
                .append(field(":site_no ", hasKey(p, "site_no")))
                .append(field(":indexs ", hasKey(p, "indexs")))
                .append(field(":content_text ", hasKey(p, "content_text")))
                .append(field(":risk_level ", hasKey(p, "risk_level")))
                .append(field(":end_date ", hasKey(p, "end_date")))
                .append(field(":requirement ", hasKey(p, "requirement")))
                .append(field(":created ", hasKey(p, "created")))
                .append(field(":responsible ", hasKey(p, "responsible")))
                .append(field(":source ", hasKey(p, "source")))
                .append(field(":isdeleted ", hasKey(p, "isdeleted")))
                .append(field(":updatetime ", hasKey(p, "updatetime")))
                .append(field(":pj_classify ", hasKey(p, "pj_classify")))
                .append(field(":discoverer ", hasKey(p, "discoverer")))
                .append(field(":spare1 ", hasKey(p, "spare1")))
                .append(field(":spare2 ", hasKey(p, "spare2")))
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
        String sql = Sql.create("delete from H_PATROL_LOG_CONTENT where 1=1 ")
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
        String sql = Sql.create("delete from H_PATROL_LOG_CONTENT where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("indexs = :indexs", hasKey(p, "indexs")))
                .append(and("content_text = :content_text", hasKey(p, "content_text")))
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("end_date = :end_date", hasKey(p, "end_date")))
                .append(and("requirement = :requirement", hasKey(p, "requirement")))
                .append(and("created = :created", hasKey(p, "created")))
                .append(and("responsible = :responsible", hasKey(p, "responsible")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update H_PATROL_LOG_CONTENT set ")
                .append(field("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(field("indexs = :indexs", hasKey(p, "indexs")))
                .append(field("content_text = :content_text", hasKey(p, "content_text")))
                .append(field("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(field("end_date = :end_date", hasKey(p, "end_date")))
                .append(field("requirement = :requirement", hasKey(p, "requirement")))
                .append(field("created = :created", hasKey(p, "created")))
                .append(field("responsible = :responsible", hasKey(p, "responsible")))
                .append(field("source = :source", hasKey(p, "source")))
                .append(field("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(field("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(field("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(field("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(field("spare1 = :spare1", hasKey(p, "spare1")))
                .append(field("spare2 = :spare2", hasKey(p, "spare2")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update H_PATROL_LOG_CONTENT set ")
                .append(field("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(field("indexs = :indexs", hasKey(p, "indexs")))
                .append(field("content_text = :content_text", hasKey(p, "content_text")))
                .append(field("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(field("end_date = :end_date", hasKey(p, "end_date")))
                .append(field("requirement = :requirement", hasKey(p, "requirement")))
                .append(field("created = :created", hasKey(p, "created")))
                .append(field("responsible = :responsible", hasKey(p, "responsible")))
                .append(field("source = :source", hasKey(p, "source")))
                .append(field("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(field("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(field("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(field("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(field("spare1 = :spare1", hasKey(p, "spare1")))
                .append(field("spare2 = :spare2", hasKey(p, "spare2")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("indexs = :indexs", hasKey(p, "indexs")))
                .append(and("content_text = :content_text", hasKey(p, "content_text")))
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("end_date = :end_date", hasKey(p, "end_date")))
                .append(and("requirement = :requirement", hasKey(p, "requirement")))
                .append(and("created = :created", hasKey(p, "created")))
                .append(and("responsible = :responsible", hasKey(p, "responsible")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
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
        String sql = Sql.create("select h.id ids, h.site_no,h.indexs id,h.content_text,h.source,nvl(d.status,0)status,h.risk_level,h.end_date,h.requirement,h.responsible, " +
                "p.project_name,h.pj_classify from H_PATROL_LOG_CONTENT h " +
                "left join H_PROJECT_CLASS p on p.pj_classify = h.pj_classify " +
                "left join H_RECTIFICATION_DETAILS d on d.patrol_lc_id = h.id where 1=1 and p.isdeleted = 0 ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryForListAll(Map<String, Object> p) {
        String sql = Sql.create("select t1.*,t2.name responsible_name,nvl(t3.status,0) status from H_PATROL_LOG_CONTENT t1 left join SYS_ACCOUNT t2 on t1.responsible = t2.account_id " +
                " left join H_RECTIFICATION_DETAILS t3 on t1.id = t3.patrol_lc_id where 1=1")
                .append(and("t1.id = :id", hasKey(p, "id")))
                .append(and("t1.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t1.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("t1.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("t1.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("t1.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("t1.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("t1.created = :created", hasKey(p, "created")))
                .append(and("t1.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("t1.source = :source", hasKey(p, "source")))
                .append(and("t1.isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("t1.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("t1.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("t1.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("t1.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("t1.spare2 = :spare2", hasKey(p, "spare2")))
                .append(" order by t1.indexs ")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from (select nvl(d.status,0)status,nvl(decode( h.site_no, s.site_no, s.site_name )," +
                "( SELECT branch_name FROM SYS_BRANCH f WHERE f.branch_id = h.site_no ) )site_name,h.id,h.risk_level,h.end_date,h.requirement,h.content_text,h.site_no," +
                "a.name, h.responsible, h.pj_classify,created,nvl(d.deleted,0)deleted from (select h1.* from H_PATROL_LOG_CONTENT h1 " +
                "left join H_SITE s1 on h1.site_no = s1.site_no " +
                "left join T_APPROVAL_APPLY t1 ON s1.id = t1.RELATION_ID where 1=1 and s1.is_delete = '0' and t1.approval_status = '2' " + and("h1.responsible = :mrgid", hasKey(p, "mrgid")) +
                "and h1.isdeleted = 0 union select h2.* from  H_PATROL_LOG_CONTENT h2 " +
                "left join H_SITE s2 on h2.site_no = s2.site_no " +
                "left join T_APPROVAL_APPLY t2 ON s2.id = t2.RELATION_ID  " +
                "where 1=1 and s2.is_delete = '0' and t2.approval_status = '2' and h2.isdeleted = 0 " + and("s2.mgr_id = :mrgid", hasKey(p, "mrgid")) + ")  h " +
                "left join SYS_ACCOUNT a on h.responsible = a.account_id " +
                "left join H_RECTIFICATION_DETAILS d on d.patrol_lc_id = h.id " +
                "left join H_SITE s on s.site_no = h.site_no " +
                "left join T_APPROVAL_APPLY t ON s.id = t.RELATION_ID where 1=1 and s.is_delete = '0' and t.approval_status = '2' ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t.account_id = :account_id", hasKey(p, "account_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text like :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.end_date >= :start_time", hasKey(p, "start_time")))
                .append(and("h.end_date <= :end_time", hasKey(p, "end_time")))
                .append(and("h.requirement like :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")));

        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("(a.branch_id in (" + p.get("orgids") + "))"));
//                    .append(or("s.branch_id in (" + p.get("orgids") + "))"));
        }
        String sqlStr = sql.append(" order by h.created desc) where 1=1 ")
                .append(and("status in (" + p.get("status") + ")", hasKey(p, "status")))
                .append(and("deleted = 0"))
                .toString();
        printSql(sqlStr, p);
        if (ObjectUtil.isEmpty(p.get("export"))) {
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
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryForListByPages(Map<String, Object> p) {
        Sql sql = Sql.create("select * from (SELECT nvl( s.status, 0 ) status,nvl((SELECT SITE_NAME FROM H_SITE t " +
                "LEFT JOIN T_APPROVAL_APPLY t1 ON t.id = t1.RELATION_ID " +
                "WHERE 1 = 1 AND t.is_delete = '0' AND t1.approval_status = '2' AND t.SITE_NO = h.SITE_NO )," +
                "( SELECT branch_name FROM SYS_BRANCH f WHERE f.branch_id = h.site_no ) ) site_name," +
                "h.id,h.risk_level,h.end_date,h.requirement,h.content_text,h.site_no,a.name,h.responsible,h.pj_classify,h.created," +
                "nvl( s.deleted, 0 ) deleted FROM H_PATROL_LOG_CONTENT h " +
                "LEFT JOIN H_RECTIFICATION_DETAILS s ON s.patrol_lc_id = h.id " +
                "LEFT JOIN SYS_ACCOUNT a ON h.responsible = a.account_id WHERE 1 = 1 ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t.account_id = :account_id", hasKey(p, "account_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text like :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.end_date >= :start_time", hasKey(p, "start_time")))
                .append(and("h.end_date <= :end_time", hasKey(p, "end_time")))
                .append(and("h.requirement like :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")));

        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("(a.branch_id in (" + p.get("orgids") + "))"));
//                    .append(or("s.branch_id in (" + p.get("orgids") + "))"));
        }
        String sqlStr = sql.append(" order by h.created desc) where 1=1 ")
                .append(and("status in (" + p.get("status") + ")", hasKey(p, "status")))
                .append(and("deleted = 0"))
                .toString();
        printSql(sqlStr, p);
        if (ObjectUtil.isEmpty(p.get("export"))) {
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
        return queryForList(sqlStr, p);
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
        String sql = Sql.create("select * from H_PATROL_LOG_CONTENT where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("indexs = :indexs", hasKey(p, "indexs")))
                .append(and("content_text = :content_text", hasKey(p, "content_text")))
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("end_date = :end_date", hasKey(p, "end_date")))
                .append(and("requirement = :requirement", hasKey(p, "requirement")))
                .append(and("created = :created", hasKey(p, "created")))
                .append(and("responsible = :responsible", hasKey(p, "responsible")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from H_PATROL_LOG_CONTENT where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("indexs = :indexs", hasKey(p, "indexs")))
                .append(and("content_text = :content_text", hasKey(p, "content_text")))
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("end_date = :end_date", hasKey(p, "end_date")))
                .append(and("requirement = :requirement", hasKey(p, "requirement")))
                .append(and("created = :created", hasKey(p, "created")))
                .append(and("responsible = :responsible", hasKey(p, "responsible")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }


    public int deleteByIDs(Map<String, Object> p) {
        String sql = Sql.create("update H_PATROL_LOG_CONTENT set ")
                .append(field("isdeleted = 1 "))
                .append(field("updatetime = :updatetime ", hasKey(p, "updatetime")))
                .append("where id in(" + p.get("ids") + ")").toString();
        printSql(sql, p);
        return update(sql, p);
    }

    public List<Map<String, Object>> queryByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from (select nvl(w.approval_status_name ,'未提交') approval_status_name," +
                "nvl(s.deleted,0)deleted,s.id relation_id,h.id,h.risk_level,h.end_date,h.requirement,h.content_text,h.created,s.risk_photo, " +
                "nvl(decode( h.site_no, b.site_no, b.site_name ),( SELECT branch_name FROM SYS_BRANCH f WHERE f.branch_id =h.site_no ) ) site_no," +
                "a.name,( SELECT name FROM SYS_ACCOUNT g WHERE g.account_id = h.responsible ) responsible,e.project_name,nvl( s.status, 0 ) status,s.tmp_status," +
                "nvl( s.schedule, '' ) schedule,s.written,nvl( decode( c.branch_id, c.branch_id, c.branch_name ), '' ) branch_id," +
                "nvl( ( SELECT branch_name FROM SYS_BRANCH d WHERE d.branch_id = c.porgid ), '' ) porgid, " +
                "nvl( ( SELECT name FROM SYS_ACCOUNT g WHERE g.account_id = h.discoverer ), '系统自动生成' ) discoverer " +
                "from (select h1.* from H_PATROL_LOG_CONTENT h1 " +
                "left join H_SITE s1 on h1.site_no = s1.site_no " +
                "left join T_APPROVAL_APPLY t1 ON s1.id = t1.RELATION_ID where 1=1 and s1.is_delete = '0' and t1.approval_status = '2'" + and("h1.responsible = :mrgid", hasKey(p, "mrgid")) +
                "and h1.isdeleted = 0 union select h2.* from  H_PATROL_LOG_CONTENT h2 " +
                "left join H_SITE s2 on h2.site_no = s2.site_no " +
                "left join T_APPROVAL_APPLY t2 ON s2.id = t2.RELATION_ID where 1=1 and s2.is_delete = '0' and t2.approval_status = '2' and h2.isdeleted = 0 " + and("s2.mgr_id = :mrgid", hasKey(p, "mrgid")) + ") h " +
                "LEFT JOIN SYS_ACCOUNT a ON h.responsible = a.account_id " +
                "LEFT JOIN H_RECTIFICATION_DETAILS s ON s.patrol_lc_id = h.id " +
                "LEFT JOIN H_SITE b ON b.site_no = h.site_no " +
                "LEFT JOIN H_PROJECT_CLASS e ON e.pj_classify = h.pj_classify " +
                "LEFT JOIN SYS_BRANCH c ON c.branch_id = b.branch_id " +
                "LEFT JOIN T_APPROVAL_APPLY w ON s.id = w.relation_id " +
                "left join T_APPROVAL_APPLY t ON b.id = t.relation_id where 1=1 and b.is_delete = '0' and t.approval_status = '2' ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t.account_id = :account_id", hasKey(p, "account_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text like :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.end_date >= :start_time", hasKey(p, "start_time")))
                .append(and("h.end_date <= :end_time", hasKey(p, "end_time")))
                .append(and("h.requirement like :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")));
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("(a.branch_id in (" + p.get("orgids") + ")"));
//                    .append(or("b.branch_id in (" + p.get("orgids") + "))"));
        }
        sql.append(" order by h.created desc ) where 1=1 ")
                .append(and("status in (" + p.get("status") + ")", hasKey(p, "status")))
                .append(and("deleted = 0"));
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

    public List<Map<String, Object>> queryByPages(Map<String, Object> p) {
        Sql sql = Sql.create("select * from (SELECT e.project_name,a.name,nvl( s.schedule, '' ) schedule,s.written," +
                "nvl( s.status, 0 ) status,s.tmp_status,s.risk_photo,s.id relation_id,nvl( s.deleted, 0 ) deleted," +
                "nvl( w.approval_status_name, '未提交' ) approval_status_name,h.id,h.risk_level,h.end_date,h.requirement," +
                "h.content_text,h.created,nvl((select SITE_NAME from H_SITE t LEFT JOIN T_APPROVAL_APPLY t1 ON " +
                "t.id = t1.RELATION_ID WHERE 1 = 1 AND t.is_delete = '0' AND t1.approval_status = '2' and " +
                "t.SITE_NO = h.SITE_NO),( SELECT branch_name FROM SYS_BRANCH f WHERE f.branch_id = h.site_no )) site_no," +
                "nvl( ( SELECT name FROM SYS_ACCOUNT g WHERE g.account_id = h.discoverer ), '系统自动生成' ) discoverer," +
                "nvl((select t2.BRANch_NAME from H_SITE t LEFT JOIN T_APPROVAL_APPLY t1 ON t.id = t1.RELATION_ID " +
                "LEFT JOIN SYS_BRANCH t2 on t.branch_id = t2.branch_id WHERE 1 = 1 AND t.is_delete = '0' AND " +
                "t1.approval_status = '2' and t.SITE_NO = h.SITE_NO ), '' ) branch_id,g.operate_name," +
                "nvl( ( SELECT d.branch_name FROM SYS_BRANCH d LEFT JOIN SYS_BRANCH g on d.branch_id = g.porgid " +
                "LEFT JOIN H_SITE t on t.BRANCH_ID = g.branch_id LEFT JOIN T_APPROVAL_APPLY t1 ON t.id = t1.RELATION_ID" +
                " WHERE t.SITE_NO = h.site_no AND t.is_delete = '0' AND t1.approval_status = '2'), (SELECT d.branch_name " +
                "FROM SYS_BRANCH d LEFT JOIN SYS_BRANCH g ON d.branch_id = g.porgid WHERE g.branch_id = h.site_no and d.BRAN_LEVEL = '1') ) porgid " +
                "FROM H_PATROL_LOG_CONTENT h " +
                "LEFT JOIN H_RECTIFICATION_DETAILS s ON s.patrol_lc_id = h.id " +
                "LEFT JOIN T_APPROVAL_APPLY w ON s.id = w.relation_id " +
                "left join T_OPERATE g on g.operate_no = h.spare1 " +
                "LEFT JOIN SYS_ACCOUNT a ON h.responsible = a.account_id " +
                "LEFT JOIN H_PROJECT_CLASS e ON e.pj_classify = h.pj_classify where 1=1  ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t.account_id = :account_id", hasKey(p, "account_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text like :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.end_date >= :start_time", hasKey(p, "start_time")))
                .append(and("h.end_date <= :end_time", hasKey(p, "end_time")))
                .append(and("h.requirement like :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")));
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("(a.branch_id in (" + p.get("orgids") + "))"));
//                    .append(or("b.branch_id in (" + p.get("orgids") + "))"));
        }
        sql.append(" order by h.created desc ) where 1=1 ")
                .append(and("status in (" + p.get("status") + ")", hasKey(p, "status")))
                .append(and("deleted = 0"));
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

    public int deleteByPatrolId(Map<String, Object> p) {
        Sql sql = Sql.create("update H_PATROL_LOG_CONTENT set ")
                .append(field("isdeleted = 1 "))
                .append(field("updatetime = :updatetime ", hasKey(p, "updatetime")))
                .append("where patrol_id = :patrol_id ")
                .append(and("id not in(" + p.get("ids") + ")", hasKey(p, "ids")));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return update(sqlStr, p);
    }

    public List<Map<String, Object>> queryForExport(Map<String, Object> p) {
        Sql sql = Sql.create("select id,risk_level,end_date,requirement,content_text,site_no,name,responsible,project_name,created," +
                "decode(status,0,'未整改',1,'已整改',2,'无需整改',3,'无法整改')status,schedule,written,branch_id,discoverer,porgid " +
                "from (select h.id,h.risk_level,h.end_date,h.requirement,h.content_text,h.created,nvl(s.deleted,0)deleted," +
                "nvl(decode(h.site_no,b.site_no,b.site_name),(select branch_name FROM SYS_BRANCH f where f.branch_id=h.site_no ))site_no, " +
                "a.name, (select name from SYS_ACCOUNT g where g.account_id = h.responsible)responsible,e.project_name,nvl(s.status,0) status,nvl(s.schedule,' ')schedule," +
                "nvl((select name from SYS_ACCOUNT d where d.account_id = s.written), ' ')written," +
                "nvl(decode(c.branch_id,c.branch_id,c.branch_name),' ')branch_id,nvl((SELECT branch_name FROM SYS_BRANCH d where d.branch_id = c.porgid),' ')porgid," +
                "nvl((select name from SYS_ACCOUNT g where g.account_id = h.discoverer), '系统自动生成')discoverer from " +
                "(select h1.* from H_PATROL_LOG_CONTENT h1 " +
                "left join H_SITE s1 on h1.site_no = s1.site_no " +
                "left join T_APPROVAL_APPLY t1 ON s1.id = t1.RELATION_ID where 1=1 and s1.is_delete = '0' and t1.approval_status = '2' " + and("h1.responsible = :mrgid", hasKey(p, "mrgid")) +
                "and h1.isdeleted = 0 union select h2.* from  H_PATROL_LOG_CONTENT h2 " +
                "left join H_SITE s2 on h2.site_no = s2.site_no " +
                "left join T_APPROVAL_APPLY t2 ON s2.id = t2.RELATION_ID where 1=1 and s2.is_delete = '0' and t2.approval_status = '2' and h2.isdeleted = 0 " + and("s2.mgr_id = :mrgid", hasKey(p, "mrgid")) + ") h " +
                "left join SYS_ACCOUNT a on h.responsible = a.account_id " +
                "left join H_RECTIFICATION_DETAILS s on s.patrol_lc_id = h.id " +
                "left join H_SITE b on b.site_no = h.site_no " +
                "left join H_PROJECT_CLASS e on e.pj_classify = h.pj_classify " +
                "left join SYS_BRANCH c on c.branch_id = b.branch_id " +
                "left join T_APPROVAL_APPLY t ON b.id = t.RELATION_ID where 1=1 and b.is_delete = '0' and t.approval_status = '2' ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t.account_id = :account_id", hasKey(p, "account_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text like :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.end_date >= :start_time", hasKey(p, "start_time")))
                .append(and("h.end_date <= :end_time", hasKey(p, "end_time")))
                .append(and("h.requirement like :requirement", hasKey(p, "requirement")))
                .append(and("h.created = :created", hasKey(p, "created")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = 0"))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")));

        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("(a.branch_id in (" + p.get("orgids") + ")"))
                    .append(or("b.branch_id in (" + p.get("orgids") + "))"));
        }
        sql.append(" order by h.created desc ) where 1=1 ")
                .append(and("status in (" + p.get("status") + ")"))
                .append(and("deleted = 0"));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public Long queryByPjClassify(Map<String, Object> p) {
        Sql sql = create("select count(*) from H_PATROL_LOG_CONTENT where 1=1 ")
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("isdeleted = 0"));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return count(sqlStr, p);
    }

    public List<Map<String, Object>> queryContenst(Map<String, Object> p) {
        Sql sql = Sql.create("select content_text from H_PATROL_LOG_CONTENT t1 where 1 = 1")
                .append(and("id = :id", hasKey(p, "id")))
                .append(and("patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("indexs = :indexs", hasKey(p, "indexs")))
                .append(and("content_text = :content_text", hasKey(p, "content_text")))
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("end_date = :end_date", hasKey(p, "end_date")))
                .append(and("requirement = :requirement", hasKey(p, "requirement")))
                .append(and("created = :created", hasKey(p, "created")))
                .append(and("responsible = :responsible", hasKey(p, "responsible")))
                .append(and("source = :source", hasKey(p, "source")))
                .append(and("isdeleted = 0"))
                .append(and("updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("spare2 = :spare2", hasKey(p, "spare2")));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryForListToJob(Map<String, Object> p) {
        String sql = Sql.create("select h.site_no,h.responsible,p.id from H_PATROL_LOG_CONTENT h " +
                "left join H_RECTIFICATION_DETAILS p on p.patrol_lc_id = h.id where 1=1 and h.isdeleted = 0 and p.id is null ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("h.created >= :created", hasKey(p, "created")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> querySiteName(Map<String, Object> p) {
        Sql sql = Sql.create("select t2.site_name from H_PATROL_LOG_CONTENT t1 " +
                "left join H_SITE t2 on t2.site_no = t1.site_no " +
                "left join T_APPROVAL_APPLY t ON t2.id = t.RELATION_ID " +
                "left join H_RECTIFICATION_DETAILS t3 on t3.patrol_lc_id = t1.id " +
                "where 1 = 1 and t2.is_delete = '0' and t.approval_status = '2'")
                .append(and("t3.id = :id", hasKey(p, "id")))
                .append(and("t1.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t1.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("t1.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("t1.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("t1.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("t1.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("t1.created = :created", hasKey(p, "created")))
                .append(and("t1.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("t1.source = :source", hasKey(p, "source")))
                .append(and("t1.isdeleted = 0"))
                .append(and("t1.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("t1.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("t1.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("t1.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("t1.spare2 = :spare2", hasKey(p, "spare2")));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryAndCountForListToJob(Map<String, Object> p) {
        String sql = Sql.create("select h.responsible,count(h.responsible)count from H_PATROL_LOG_CONTENT h " +
                " where 1=1 and h.isdeleted = 0 ")
                .append(and("h.id = :id", hasKey(p, "id")))
                .append(and("h.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("h.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("h.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("h.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("h.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("h.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("h.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("h.created >= :start_time", hasKey(p, "start_time")))
                .append(and("h.created < :end_time", hasKey(p, "end_time")))
                .append(and("h.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("h.source = :source", hasKey(p, "source")))
                .append(and("h.isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("h.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("h.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("h.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("h.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("h.spare2 = :spare2", hasKey(p, "spare2")))
                .append("group by h.responsible")
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> countForList(Map<String, Object> p) {
        String sql = Sql.create("select count( t1.id ) count," +
                "count( case when t1.risk_level = '高' then '0' end ) high," +
                "count( case when t1.risk_level = '中' then '1' end ) middle," +
                "count( case when t1.risk_level = '低' then '2' end ) low from h_patrol_log_content t1 " +
                "left join h_site t2 on t1.site_no = t2.site_no " +
                "left join sys_branch t3 on t2.branch_id = t3.branch_id " +
                "left join t_approval_apply t4 on t2.id = t4.relation_id where 1=1 " +
                "and t1.isdeleted = '0' and  t2.is_delete = '0' and t4.approval_status = '2' ")
                .append(and("t1.id = :id", hasKey(p, "id")))
                .append(and("t1.patrol_id = :patrol_id", hasKey(p, "patrol_id")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t1.indexs = :indexs", hasKey(p, "indexs")))
                .append(and("t1.content_text = :content_text", hasKey(p, "content_text")))
                .append(and("t1.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("t1.end_date = :end_date", hasKey(p, "end_date")))
                .append(and("t1.requirement = :requirement", hasKey(p, "requirement")))
                .append(and("t1.created >= :start_time", hasKey(p, "start_time")))
                .append(and("t1.created < :end_time", hasKey(p, "end_time")))
                .append(and("t1.responsible = :responsible", hasKey(p, "responsible")))
                .append(and("t1.source = :source", hasKey(p, "source")))
                .append(and("t1.isdeleted = :isdeleted", hasKey(p, "isdeleted")))
                .append(and("t1.updatetime = :updatetime", hasKey(p, "updatetime")))
                .append(and("t1.pj_classify = :pj_classify", hasKey(p, "pj_classify")))
                .append(and("t1.discoverer = :discoverer", hasKey(p, "discoverer")))
                .append(and("t1.spare1 = :spare1", hasKey(p, "spare1")))
                .append(and("t1.spare2 = :spare2", hasKey(p, "spare2")))
                .append(and("t2.branch_id in (" + p.get("branchIds") + ")", hasKey(p, "branchIds")))
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public String saveForId(Map<String, Object> p) {
        String tableFlowNo = sequenceService.getTableFlowNo("H_PATROL_LOG_CONTENT", "id");
        p.put("id", tableFlowNo);
        String sql = Sql.create("insert into H_PATROL_LOG_CONTENT (")
                .append(field("id "))
                .append(field("patrol_id ", hasKey(p, "patrol_id")))
                .append(field("site_no ", hasKey(p, "site_no")))
                .append(field("indexs ", hasKey(p, "indexs")))
                .append(field("content_text ", hasKey(p, "content_text")))
                .append(field("risk_level ", hasKey(p, "risk_level")))
                .append(field("end_date ", hasKey(p, "end_date")))
                .append(field("requirement ", hasKey(p, "requirement")))
                .append(field("created ", hasKey(p, "created")))
                .append(field("responsible ", hasKey(p, "responsible")))
                .append(field("source ", hasKey(p, "source")))
                .append(field("isdeleted ", hasKey(p, "isdeleted")))
                .append(field("updatetime ", hasKey(p, "updatetime")))
                .append(field("pj_classify ", hasKey(p, "pj_classify")))
                .append(field("discoverer ", hasKey(p, "discoverer")))
                .append(field("spare1 ", hasKey(p, "spare1")))
                .append(field("spare2 ", hasKey(p, "spare2")))
                .append(") values (")
                .append(field(":id "))
                .append(field(":patrol_id ", hasKey(p, "patrol_id")))
                .append(field(":site_no ", hasKey(p, "site_no")))
                .append(field(":indexs ", hasKey(p, "indexs")))
                .append(field(":content_text ", hasKey(p, "content_text")))
                .append(field(":risk_level ", hasKey(p, "risk_level")))
                .append(field(":end_date ", hasKey(p, "end_date")))
                .append(field(":requirement ", hasKey(p, "requirement")))
                .append(field(":created ", hasKey(p, "created")))
                .append(field(":responsible ", hasKey(p, "responsible")))
                .append(field(":source ", hasKey(p, "source")))
                .append(field(":isdeleted ", hasKey(p, "isdeleted")))
                .append(field(":updatetime ", hasKey(p, "updatetime")))
                .append(field(":pj_classify ", hasKey(p, "pj_classify")))
                .append(field(":discoverer ", hasKey(p, "discoverer")))
                .append(field(":spare1 ", hasKey(p, "spare1")))
                .append(field(":spare2 ", hasKey(p, "spare2")))
                .append(")")
                .toString();
        printSql(sql, p);
        save(sql, p);
        return tableFlowNo;
    }
}
