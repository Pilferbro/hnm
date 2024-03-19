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
 * 表名:ALL_TRADE_INFO_RISK
 * 主键:
 **/
@Repository
public class AllTradeInfoRiskDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"flow_num", "trans_dt", "trans_tm", "tran_amt", "tran_addr", "terminal_id", "site_no", "acct_num", "tran_type", "open_bank", "cust_name", "src_sys_cd", "creat_data", "risk_id"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into ALL_TRADE_INFO_RISK (")
                .append(field("flow_num ", hasKey(p, "flow_num")))
                .append(field("trans_dt ", hasKey(p, "trans_dt")))
                .append(field("trans_tm ", hasKey(p, "trans_tm")))
                .append(field("tran_amt ", hasKey(p, "tran_amt")))
                .append(field("tran_addr ", hasKey(p, "tran_addr")))
                .append(field("terminal_id ", hasKey(p, "terminal_id")))
                .append(field("site_no ", hasKey(p, "site_no")))
                .append(field("acct_num ", hasKey(p, "acct_num")))
                .append(field("tran_type ", hasKey(p, "tran_type")))
                .append(field("open_bank ", hasKey(p, "open_bank")))
                .append(field("cust_name ", hasKey(p, "cust_name")))
                .append(field("src_sys_cd ", hasKey(p, "src_sys_cd")))
                .append(field("creat_data ", hasKey(p, "creat_data")))
                .append(field("risk_id ", hasKey(p, "risk_id")))
                .append(") values (")
                .append(field(":flow_num ", hasKey(p, "flow_num")))
                .append(field(":trans_dt ", hasKey(p, "trans_dt")))
                .append(field(":trans_tm ", hasKey(p, "trans_tm")))
                .append(field(":tran_amt ", hasKey(p, "tran_amt")))
                .append(field(":tran_addr ", hasKey(p, "tran_addr")))
                .append(field(":terminal_id ", hasKey(p, "terminal_id")))
                .append(field(":site_no ", hasKey(p, "site_no")))
                .append(field(":acct_num ", hasKey(p, "acct_num")))
                .append(field(":tran_type ", hasKey(p, "tran_type")))
                .append(field(":open_bank ", hasKey(p, "open_bank")))
                .append(field(":cust_name ", hasKey(p, "cust_name")))
                .append(field(":src_sys_cd ", hasKey(p, "src_sys_cd")))
                .append(field(":creat_data ", hasKey(p, "creat_data")))
                .append(field(":risk_id ", hasKey(p, "risk_id")))
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
        String sql = Sql.create("delete from ALL_TRADE_INFO_RISK where 1=1 ")
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
        String sql = Sql.create("delete from ALL_TRADE_INFO_RISK where 1=1 ")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update ALL_TRADE_INFO_RISK set ")
                .append(field("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(field("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(field("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(field("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(field("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(field("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(field("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(field("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(field("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(field("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(field("risk_id = :risk_id", hasKey(p, "risk_id")))
                .append(" where 1=1 ")
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update ALL_TRADE_INFO_RISK set ")
                .append(field("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(field("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(field("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(field("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(field("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(field("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(field("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(field("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(field("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(field("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(field("risk_id = :risk_id", hasKey(p, "risk_id")))
                .append(" where 1=1 ")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
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
        String sql = Sql.create("select * from ALL_TRADE_INFO_RISK where 1=1")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from ALL_TRADE_INFO_RISK where 1=1")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")));

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
        String sql = Sql.create("select * from ALL_TRADE_INFO_RISK where 1=1 ")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from ALL_TRADE_INFO_RISK where 1=1 ")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }
    public List<Map<String, Object>> queryForListByRiskId(Map<String, Object> p) {
        String sql = Sql.create("select trans_tm,tran_amt,tran_addr,decode(tran_type,'56','小额取款','57','现金汇款','58','转账汇款-汇出','111','消费','222','转账汇款-汇入')tran_type from ALL_TRADE_INFO_RISK where 1=1")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("risk_id = :risk_id", hasKey(p, "risk_id")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}