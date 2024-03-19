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
 * 表名:TRADE_INFO
 * 主键:
 **/
@Repository
public class TradeInfoDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"cust_name", "open_acct_org_id", "core_flow_num", "valid_cd", "trans_dt", "trans_tm", "tran_amt", "recv_side_name_addr", "recv_side_termn_id", "agent_org_id", "main_acct_num", "cap_tran_out_acct", "tran_in_acct", "mercht_cate_cd", "info_type_cd", "proc_cd", "src_sys_cd", "creat_data", "mask_from_v2"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into TRADE_INFO (")
                .append(field("cust_name ", hasKey(p, "cust_name")))
                .append(field("open_acct_org_id ", hasKey(p, "open_acct_org_id")))
                .append(field("core_flow_num ", hasKey(p, "core_flow_num")))
                .append(field("valid_cd ", hasKey(p, "valid_cd")))
                .append(field("trans_dt ", hasKey(p, "trans_dt")))
                .append(field("trans_tm ", hasKey(p, "trans_tm")))
                .append(field("tran_amt ", hasKey(p, "tran_amt")))
                .append(field("recv_side_name_addr ", hasKey(p, "recv_side_name_addr")))
                .append(field("recv_side_termn_id ", hasKey(p, "recv_side_termn_id")))
                .append(field("agent_org_id ", hasKey(p, "agent_org_id")))
                .append(field("main_acct_num ", hasKey(p, "main_acct_num")))
                .append(field("cap_tran_out_acct ", hasKey(p, "cap_tran_out_acct")))
                .append(field("tran_in_acct ", hasKey(p, "tran_in_acct")))
                .append(field("mercht_cate_cd ", hasKey(p, "mercht_cate_cd")))
                .append(field("info_type_cd ", hasKey(p, "info_type_cd")))
                .append(field("proc_cd ", hasKey(p, "proc_cd")))
                .append(field("src_sys_cd ", hasKey(p, "src_sys_cd")))
                .append(field("creat_data ", hasKey(p, "creat_data")))
                .append(field("mask_from_v2 ", hasKey(p, "mask_from_v2")))
                .append(") values (")
                .append(field(":cust_name ", hasKey(p, "cust_name")))
                .append(field(":open_acct_org_id ", hasKey(p, "open_acct_org_id")))
                .append(field(":core_flow_num ", hasKey(p, "core_flow_num")))
                .append(field(":valid_cd ", hasKey(p, "valid_cd")))
                .append(field(":trans_dt ", hasKey(p, "trans_dt")))
                .append(field(":trans_tm ", hasKey(p, "trans_tm")))
                .append(field(":tran_amt ", hasKey(p, "tran_amt")))
                .append(field(":recv_side_name_addr ", hasKey(p, "recv_side_name_addr")))
                .append(field(":recv_side_termn_id ", hasKey(p, "recv_side_termn_id")))
                .append(field(":agent_org_id ", hasKey(p, "agent_org_id")))
                .append(field(":main_acct_num ", hasKey(p, "main_acct_num")))
                .append(field(":cap_tran_out_acct ", hasKey(p, "cap_tran_out_acct")))
                .append(field(":tran_in_acct ", hasKey(p, "tran_in_acct")))
                .append(field(":mercht_cate_cd ", hasKey(p, "mercht_cate_cd")))
                .append(field(":info_type_cd ", hasKey(p, "info_type_cd")))
                .append(field(":proc_cd ", hasKey(p, "proc_cd")))
                .append(field(":src_sys_cd ", hasKey(p, "src_sys_cd")))
                .append(field(":creat_data ", hasKey(p, "creat_data")))
                .append(field(":mask_from_v2 ", hasKey(p, "mask_from_v2")))
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
        String sql = Sql.create("delete from TRADE_INFO where 1=1 ")
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
        String sql = Sql.create("delete from TRADE_INFO where 1=1 ")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update TRADE_INFO set ")
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(field("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(field("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(field("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(field("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(field("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(field("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(field("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(field("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(field("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(field("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(field("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(field("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(field("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(field("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(field("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(field("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(field("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(" where 1=1 ")
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update TRADE_INFO set ")
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(field("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(field("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(field("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(field("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(field("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(field("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(field("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(field("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(field("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(field("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(field("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(field("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(field("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(field("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(field("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(field("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(field("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(" where 1=1 ")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
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
        String sql = Sql.create("select * from TRADE_INFO where 1=1")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from TRADE_INFO where 1=1")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")));

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
        String sql = Sql.create("select * from TRADE_INFO where 1=1 ")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from TRADE_INFO where 1=1 ")
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> queryDetailsForList(Map<String, Object> p) {
        Sql sql = Sql.create("select b.main_acct_num cardNum,b.cust_name,b.open_acct_org_id branch_id from trade_info b where 1=1")
                .append(and("b.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("b.open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("b.core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("b.valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("b.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("b.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("b.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("b.recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("b.recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("b.agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("b.main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("b.cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("b.tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("b.mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("b.info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("b.proc_cd like :proc_cd1", hasKey(p, "proc_cd1")))
                .append(and("b.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("b.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("b.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                .append(and("b.mask_from_v2 <= :end_time", hasKey(p, "end_time")));

        if (p.containsKey("proc_cd2")) {
            sql.append("union all ")
                    .append("select c.main_acct_num cardNum,b.cust_name,b.open_acct_org_id branch_id from trade_info c where 1=1")
                    .append(and("c.cust_name = :cust_name", hasKey(p, "cust_name")))
                    .append(and("c.open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                    .append(and("c.core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                    .append(and("c.valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                    .append(and("c.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                    .append(and("c.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                    .append(and("c.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                    .append(and("c.recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                    .append(and("c.recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                    .append(and("c.agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                    .append(and("c.main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                    .append(and("c.cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                    .append(and("c.tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                    .append(and("c.mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                    .append(and("c.info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                    .append(and("c.proc_cd like :proc_cd2", hasKey(p, "proc_cd2")))
                    .append(and("c.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                    .append(and("c.creat_data = :creat_data", hasKey(p, "creat_data")))
                    .append(and("c.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                    .append(and("c.mask_from_v2 <= :end_time", hasKey(p, "end_time")))
            ;
        }
        if (p.containsKey("proc_cd3")) {
            sql.append("union all ")
                    .append("select c.main_acct_num cardNum from trade_info c where 1=1")
                    .append(and("c.core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                    .append(and("c.valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                    .append(and("c.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                    .append(and("c.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                    .append(and("c.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                    .append(and("c.recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                    .append(and("c.recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                    .append(and("c.agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                    .append(and("c.main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                    .append(and("c.cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                    .append(and("c.tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                    .append(and("c.mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                    .append(and("c.info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                    .append(and("c.proc_cd like :proc_cd3", hasKey(p, "proc_cd3")))
                    .append(and("c.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                    .append(and("c.creat_data = :creat_data", hasKey(p, "creat_data")))
                    .append(and("c.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                    .append(and("c.mask_from_v2 <= :end_time", hasKey(p, "end_time")))
            ;
        }

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryToMergerData(Map<String, Object> p) {
        String sql = Sql.create("select a.core_flow_num flow_num," +
                "a.trans_dt,a.mask_from_v2 trans_tm,a.tran_amt,a.recv_side_name_addr tran_addr," +
                "a.agent_org_id site_no,a.recv_side_termn_id terminal_id,a.main_acct_num acct_num," +
                "a.src_sys_cd,a.cust_name,a.open_acct_org_id open_bank,a.proc_cd from TRADE_INFO a " +
                "where 1=1 ")
                .append(and("a.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("a.open_acct_org_id = :open_acct_org_id", hasKey(p, "open_acct_org_id")))
                .append(and("a.core_flow_num = :core_flow_num", hasKey(p, "core_flow_num")))
                .append(and("a.valid_cd = :valid_cd", hasKey(p, "valid_cd")))
                .append(and("a.trans_dt = :trans_dt", hasKey(p, "trans_dt")))
                .append(and("a.trans_tm = :trans_tm", hasKey(p, "trans_tm")))
                .append(and("a.tran_amt = :tran_amt", hasKey(p, "tran_amt")))
                .append(and("a.recv_side_name_addr = :recv_side_name_addr", hasKey(p, "recv_side_name_addr")))
                .append(and("a.recv_side_termn_id = :recv_side_termn_id", hasKey(p, "recv_side_termn_id")))
                .append(and("a.agent_org_id = :agent_org_id", hasKey(p, "agent_org_id")))
                .append(and("a.main_acct_num = :main_acct_num", hasKey(p, "main_acct_num")))
                .append(and("a.cap_tran_out_acct = :cap_tran_out_acct", hasKey(p, "cap_tran_out_acct")))
                .append(and("a.tran_in_acct = :tran_in_acct", hasKey(p, "tran_in_acct")))
                .append(and("a.mercht_cate_cd = :mercht_cate_cd", hasKey(p, "mercht_cate_cd")))
                .append(and("a.info_type_cd = :info_type_cd", hasKey(p, "info_type_cd")))
                .append(and("a.proc_cd = :proc_cd", hasKey(p, "proc_cd")))
                .append(and("a.src_sys_cd = :src_sys_cd", hasKey(p, "src_sys_cd")))
                .append(and("a.creat_data = :creat_data", hasKey(p, "creat_data")))
                .append(and("a.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}