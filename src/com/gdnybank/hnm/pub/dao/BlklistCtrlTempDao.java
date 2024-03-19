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
 * 表名:BLKLIST_CTRL_TEMP
 * 主键:
 **/
@Repository
public class BlklistCtrlTempDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "lp_cd", "blklist_type_cd", "ctrl_id", "cust_type_cd", "cust_name", "cert_type_cd", "cert_num", "cust_id", "acct_id", "valid_flg", "closing_dt", "src", "set_ctrl_dt", "set_ctrl_tm", "remit_ctrl_dt", "remit_ctrl_tm", "anti_mon_lau_cust_risk_level_cd"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("id", sequenceService.getTableFlowNo("BLKLIST_CTRL", "id"));
        String sql = Sql.create("insert into BLKLIST_CTRL_TEMP (")
                .append(field("id "))
                .append(field("lp_cd ", hasKey(p, "lp_cd")))
                .append(field("blklist_type_cd ", hasKey(p, "blklist_type_cd")))
                .append(field("ctrl_id ", hasKey(p, "ctrl_id")))
                .append(field("cust_type_cd ", hasKey(p, "cust_type_cd")))
                .append(field("cust_name ", hasKey(p, "cust_name")))
                .append(field("cert_type_cd ", hasKey(p, "cert_type_cd")))
                .append(field("cert_num ", hasKey(p, "cert_num")))
                .append(field("cust_id ", hasKey(p, "cust_id")))
                .append(field("acct_id ", hasKey(p, "acct_id")))
                .append(field("valid_flg ", hasKey(p, "valid_flg")))
                .append(field("closing_dt ", hasKey(p, "closing_dt")))
                .append(field("src ", hasKey(p, "src")))
                .append(field("set_ctrl_dt ", hasKey(p, "set_ctrl_dt")))
                .append(field("set_ctrl_tm ", hasKey(p, "set_ctrl_tm")))
                .append(field("remit_ctrl_dt ", hasKey(p, "remit_ctrl_dt")))
                .append(field("remit_ctrl_tm ", hasKey(p, "remit_ctrl_tm")))
                .append(field("anti_mon_lau_cust_risk_level_cd ", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(") values (")
                .append(field(":id "))
                .append(field(":lp_cd ", hasKey(p, "lp_cd")))
                .append(field(":blklist_type_cd ", hasKey(p, "blklist_type_cd")))
                .append(field(":ctrl_id ", hasKey(p, "ctrl_id")))
                .append(field(":cust_type_cd ", hasKey(p, "cust_type_cd")))
                .append(field(":cust_name ", hasKey(p, "cust_name")))
                .append(field(":cert_type_cd ", hasKey(p, "cert_type_cd")))
                .append(field(":cert_num ", hasKey(p, "cert_num")))
                .append(field(":cust_id ", hasKey(p, "cust_id")))
                .append(field(":acct_id ", hasKey(p, "acct_id")))
                .append(field(":valid_flg ", hasKey(p, "valid_flg")))
                .append(field(":closing_dt ", hasKey(p, "closing_dt")))
                .append(field(":src ", hasKey(p, "src")))
                .append(field(":set_ctrl_dt ", hasKey(p, "set_ctrl_dt")))
                .append(field(":set_ctrl_tm ", hasKey(p, "set_ctrl_tm")))
                .append(field(":remit_ctrl_dt ", hasKey(p, "remit_ctrl_dt")))
                .append(field(":remit_ctrl_tm ", hasKey(p, "remit_ctrl_tm")))
                .append(field(":anti_mon_lau_cust_risk_level_cd ", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
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
        String sql = Sql.create("delete from BLKLIST_CTRL_TEMP where 1=1 ").append(and("id = :id"))
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
        String sql = Sql.create("delete from BLKLIST_CTRL_TEMP where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update BLKLIST_CTRL_TEMP set ")
                .append(field("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(field("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(field("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(field("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(field("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(field("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(field("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(field("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(field("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(field("src = :src", hasKey(p, "src")))
                .append(field("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(field("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(field("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(field("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(field("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update BLKLIST_CTRL_TEMP set ")
                .append(field("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(field("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(field("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(field("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(field("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(field("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(field("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(field("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(field("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(field("src = :src", hasKey(p, "src")))
                .append(field("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(field("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(field("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(field("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(field("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(" where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
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
        String sql = Sql.create("select * from BLKLIST_CTRL_TEMP where 1=1")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from BLKLIST_CTRL_TEMP where 1=1")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")));

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
        String sql = Sql.create("select * from BLKLIST_CTRL_TEMP where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from BLKLIST_CTRL_TEMP where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("src = :src", hasKey(p, "src")))
                .append(and("set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public int truncate() {
        return super.update("truncate table BLKLIST_CTRL_TEMP", BaseUtils.map());
    }

    public int deleteRepData() {
        String sql = "DELETE FROM BLKLIST_CTRL_TEMP T1 WHERE T1.CTRL_ID IN( SELECT T2.CTRL_ID FROM(SELECT A.*, ROW_NUMBER() " +
                "OVER(PARTITION BY A.CERT_NUM ORDER BY A.SET_CTRL_DT DESC) RN FROM BLKLIST_CTRL_TEMP A WHERE A.CERT_NUM " +
                "IS NOT NULL AND A.ACCT_ID IS NULL )T2 WHERE T2.RN > 1 UNION  SELECT T3.CTRL_ID FROM(SELECT B.*, ROW_NUMBER() " +
                "OVER(PARTITION BY B.ACCT_ID ORDER BY B.SET_CTRL_DT DESC) RN FROM BLKLIST_CTRL_TEMP B WHERE B.ACCT_ID IS NOT " +
                "NULL )T3 WHERE T3.RN > 1)";
        printSql(sql, BaseUtils.map());
        return delete(sql,BaseUtils.map());

    }
}