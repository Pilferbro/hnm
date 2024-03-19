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
 * 表名:ALL_TRADE_INFO
 * 主键:
 **/
@Repository
public class AllTradeInfoDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"flow_num", "trans_dt", "trans_tm", "tran_amt", "tran_addr", "terminal_id", "site_no", "acct_num", "tran_type", "open_bank", "cust_name", "src_sys_cd", "creat_data"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into ALL_TRADE_INFO (")
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
        String sql = Sql.create("delete from ALL_TRADE_INFO where 1=1 ")
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
        String sql = Sql.create("delete from ALL_TRADE_INFO where 1=1 ")
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
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update ALL_TRADE_INFO set ")
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
                .append(" where 1=1 ")
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update ALL_TRADE_INFO set ")
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
        String sql = Sql.create("select * from ALL_TRADE_INFO where 1=1")
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
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from ALL_TRADE_INFO where 1=1")
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
        String sql = Sql.create("select * from ALL_TRADE_INFO where 1=1 ")
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
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from ALL_TRADE_INFO where 1=1 ")
                .append(and("flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_dt >= :start_date", hasKey(p, "start_date")))
                .append(and("trans_dt <= :end_date", hasKey(p, "end_date")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(and("acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))

                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public int[] saveList(List<Map<String, Object>> params) {
        String sql = Sql.create("insert into ALL_TRADE_INFO (")
                .append(field("flow_num "))
                .append(field("trans_dt "))
                .append(field("trans_tm "))
                .append(field("tran_amt "))
                .append(field("tran_addr "))
                .append(field("terminal_id "))
                .append(field("site_no "))
                .append(field("acct_num "))
                .append(field("tran_type "))
                .append(field("open_bank "))
                .append(field("cust_name "))
                .append(field("src_sys_cd "))
                .append(field("creat_data "))
                .append(") values (")
                .append(field(":flow_num "))
                .append(field(":trans_dt "))
                .append(field(":trans_tm "))
                .append(field(":tran_amt "))
                .append(field(":tran_addr "))
                .append(field(":terminal_id "))
                .append(field(":site_no "))
                .append(field(":acct_num "))
                .append(field(":tran_type "))
                .append(field(":open_bank "))
                .append(field(":cust_name "))
                .append(field(":src_sys_cd "))
                .append(field(":creat_data "))
                .append(")")
                .toString();
        return batchUpdate(sql, params);
    }

    public List<Map<String, Object>> countTrades(Map<String, Object> p) {
        Sql sql = Sql.create("select a.acct_num,count(a.acct_num)countNum,sum(tran_amt)tran_amt from ALL_TRADE_INFO a where 1=1 ")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_dt >= :start_date", hasKey(p, "start_date")))
                .append(and("a.trans_dt <= :end_date", hasKey(p, "end_date")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by a.acct_num ");
        if (p.containsKey("num")) {
            sql.append("having count (a.acct_num) > " + p.get("num"));
        }

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryTrade(Map<String, Object> p) {
        Sql sql = Sql.create("select a.*,b.branch_name,b.branch_id from ALL_TRADE_INFO a " +
                "left join sys_branch b on a.open_bank = b.branch_id where 1=1 ")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_dt >= :start_date", hasKey(p, "start_date")))
                .append(and("a.trans_dt <= :end_date", hasKey(p, "end_date")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryQuotaTrade(Map<String, Object> p) {
        Sql sql = Sql.create("select a.acct_num,count(a.acct_num)countNum,sum(tran_amt)tran_amt from ALL_TRADE_INFO a where 1=1")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_types1", hasKey(p, "tran_types1")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by a.acct_num ");
        if (p.containsKey("num1")) {
            sql.append("having sum (a.tran_amt) > " + p.get("num1"));
        }
        sql.append("union all " +
                "select b.acct_num,count(b.acct_num)countNum,sum(b.tran_amt)tran_amt from ALL_TRADE_INFO b where 1=1 ")
                .append(and("b.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("b.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("b.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("b.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("b.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("b.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("b.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("b.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("b.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("b.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("b.tran_type = :tran_types2", hasKey(p, "tran_types2")))
                .append(and("b.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("b.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("b.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("b.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("b.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("b.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by b.acct_num ");
        if (p.containsKey("num2")) {
            sql.append("having sum (b.tran_amt) > " + p.get("num2"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> countTradesByTranAddr(Map<String, Object> p) {
        Sql sql = Sql.create("select a.tran_addr,count(a.tran_addr)countNum from ALL_TRADE_INFO a where 1=1 ")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_dt >= :start_date", hasKey(p, "start_date")))
                .append(and("a.trans_dt <= :end_date", hasKey(p, "end_date")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by a.tran_addr ");

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> countTradesByTerminalId(Map<String, Object> p) {
        Sql sql = Sql.create("select a.terminal_id,count(a.terminal_id)countNum from ALL_TRADE_INFO a where 1=1 ")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_dt >= :start_date", hasKey(p, "start_date")))
                .append(and("a.trans_dt <= :end_date", hasKey(p, "end_date")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by terminal_id ");

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> sumTransTm(Map<String, Object> p) {
        Sql sql = Sql.create("select a.acct_num,count(a.acct_num)countNum,sum(tran_amt)tran_amt from ALL_TRADE_INFO a where 1=1")
                .append(and("a.flow_num = :flow_num", hasKey(p, "flow_num")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.trans_tm >= :start_time", hasKey(p, "start_time")))
                .append(and("a.trans_tm <= :end_time", hasKey(p, "end_time")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.tran_addr = :tran_addr", hasKey(p, "tran_addr")))
                .append(and("a.terminal_id = :terminal_id", hasKey(p, "terminal_id")))
                .append(and("a.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("a.acct_num = :acct_num", hasKey(p, "acct_num")))
                .append(and("a.tran_type = :tran_type", hasKey(p, "tran_type")))
                .append(and("a.tran_type in (" + p.get("tran_types") + ")", hasKey(p, "tran_types")))
                .append(and("a.open_bank = :open_bank", hasKey(p, "open_bank")))
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.risk_id = :risk_id", hasKey(p, "risk_id")))
                .append("group by a.acct_num ");
        if (p.containsKey("num")) {
            sql.append("having sum (a.tran_amt) > " + p.get("num"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }
}