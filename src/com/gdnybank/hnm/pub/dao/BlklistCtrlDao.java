package com.gdnybank.hnm.pub.dao;

import java.util.List;
import java.util.Map;

import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.DateUtils;
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
 * 表名:BLKLIST_CTRL
 * 主键:
 * id
 **/
@Repository
public class BlklistCtrlDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "lp_cd", "blklist_type_cd", "ctrl_id", "cust_type_cd", "cust_name", "cert_type_cd", "cert_num", "cust_id", "acct_id", "valid_flg", "closing_dt", "src", "set_ctrl_dt", "set_ctrl_tm", "remit_ctrl_dt", "remit_ctrl_tm", "anti_mon_lau_cust_risk_level_cd", "create_time", "update_time", "risk_level", "is_dep_acct_basic"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {

        String sql = Sql.create("insert into BLKLIST_CTRL (")
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
                .append(field("risk_level ", hasKey(p, "risk_level")))
                .append(field("is_dep_acct_basic ", hasKey(p, "is_dep_acct_basic")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("update_time ", hasKey(p, "update_time")))
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
                .append(field(":risk_level ", hasKey(p, "risk_level")))
                .append(field(":is_dep_acct_basic ", hasKey(p, "is_dep_acct_basic")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":update_time ", hasKey(p, "update_time")))
                .append(")")
                .toString();
        printSql(sql, p);
        return save(sql, p);
    }

    /**
     * 批量新增
     *
     * @param paramMap
     * @param p
     * @return
     */
    public int[] batchUpdate(Map<String, Object>[] paramMap, Map<String, Object> p) {

        String sql = Sql.create("insert into BLKLIST_CTRL (")
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
                .append(field("risk_level ", hasKey(p, "risk_level")))
                .append(field("is_dep_acct_basic ", hasKey(p, "is_dep_acct_basic")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("update_time ", hasKey(p, "update_time")))
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
                .append(field(":risk_level ", hasKey(p, "risk_level")))
                .append(field(":is_dep_acct_basic ", hasKey(p, "is_dep_acct_basic")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":update_time ", hasKey(p, "update_time")))
                .append(")")
                .toString();
        printSql(sql, p);
        return super.getJdbcTemplateForMap().batchUpdate(sql, paramMap);
    }

    /***
     * 删除一条或者多条数据,慎用此函数.
     * 此函数当传入的条件为空的时候,有可能会导致整张数据表被删除,因此,在执行此函数前,请一定对传入的参数进行非空检验,以防数据被误删.
     * 项目组务必对使用此函数的代码进行代码走查,防止漏洞发生,防止被攻击.
     **/
    @Override
    public int delete(Map<String, Object> p) {

        String sql = Sql.create("delete from BLKLIST_CTRL where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
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
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int deleteByPk(Map<String, Object> p) {
        String sql = Sql.create("delete from  blklist_ctrl where 1=1 ")
                .append(and("id = :id")).toString();
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update BLKLIST_CTRL set ")
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
                .append(field("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(field("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
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
        String sql = Sql.create("update BLKLIST_CTRL set ")
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
                .append(field("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(field("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("update_time = :update_time", hasKey(p, "update_time")))
                .append(" where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
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
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
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
        String sql = Sql.create("select id,cust_name,cert_num,acct_id,closing_dt,src,set_ctrl_dt,risk_level," +
                "create_time from blklist_ctrl where 1=1")
                .append(and("id = :id", hasKey(p, "id")))
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
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .append(" order by create_time desc ")
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    /**
     * src枚举植：999999-柜员录入  100001-惠农系统录入  200501 - 公安 其他如P、U等均是反洗钱黑名单数据，均默认为999999
     */
    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        String date = DateUtils.getDate();
        Sql sql = Sql.create("select t1.id,case t1.blklist_type_cd when '1' then '电信诈骗（黑名单）' when '2' then '电信诈骗（灰名单）' when '7' then '7、8类账户' " +
                "when '6' then '反洗钱黑名单' else blklist_type_cd end blklistType,t4.site_name,t1.is_dep_acct_basic common," +
                "t1.cust_name,t1.cert_num,t1.cert_type_cd,t1.acct_id,t1.closing_dt,case t1.src when '999999' then '999999' " +
                "when '200501' then '200501' when '100001' then '100001' else '999999' end src," +
                "t1.set_ctrl_dt,t1.risk_level,t1.create_time " +
                "from BLKLIST_CTRL t1 left join thfacct t2 on t1.acct_id = t2.richnbr " +
                "left join tclient t3 on t3.cltnbr = t2.cltnbr " +
                "left join (select a.SITE_NO,a.site_name from H_SITE a group BY a.SITE_NO ,a.site_name) t4 on t4.site_no = t3.tlrclt " +
                "where 1=1 ")
                .append(and("t1.id = :id", hasKey(p, "id")))
                .append(and("t1.lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("t1.blklist_type_cd = :blklist_type_cd", hasKey(p, "blklist_type_cd")))
                .append(and("t1.ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("t1.cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("t1.cust_name like :cust_name", hasKey(p, "cust_name")))
                .append(and("t1.cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("t1.cert_num like :cert_num", hasKey(p, "cert_num")))
                .append(and("t1.cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("t1.acct_id like :acct_id", hasKey(p, "acct_id")))
                .append(and("t1.valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("t1.closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("t1.closing_dt >= :endDate", hasKey(p, "endDate")))
                .append(and("t1.src = :src", hasKey(p, "src")))
                .append(and("t1.set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("t1.set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("t1.remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("t1.remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("t1.anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(and("t1.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("t1.is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.update_time = :update_time", hasKey(p, "update_time")));

        if (p.containsKey("type_cd")) {
            sql.append(and("t1.blklist_type_cd not in (" + p.get("type_cd") + ") "));
        }else{
            if (!p.containsKey("blklist_type_cd")){
                sql.append(and("t1.blklist_type_cd not in ('3','4','5') "));
            }
        }
        if (p.containsKey("site_no")) {
            sql.append(and("t3.tlrclt in (" + p.get("site_no") + ")"));
        }
        sql.append(" order by t1.create_time desc ");

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
        String sql = Sql.create("select * from BLKLIST_CTRL where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
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
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from BLKLIST_CTRL where 1=1 ")
                .append(and("id = :id", hasKey(p, "id")))
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
                .append(and("risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("update_time = :update_time", hasKey(p, "update_time")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public int synByTempData() {
        String date = DateUtils.getDate();
        String sql = "merge into blklist_ctrl T1 using (select id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd," +
                "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,anti_mon_lau_cust_risk_level_cd " +
                "from blklist_ctrl_temp A where A.acct_id is not null) T2 on (T1.acct_id = T2.acct_id) "
                + "when matched then update set T1.cert_num = t2.cert_num,T1.blklist_type_cd = T2.blklist_type_cd,T1.valid_flg = T2.valid_flg "
                + "when not matched then insert (id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd,"
                + "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,"
                + "anti_mon_lau_cust_risk_level_cd,risk_level,is_dep_acct_basic,create_time)"
                + "VALUES (T2.id,T2.lp_cd,T2.blklist_type_cd,T2.ctrl_id,T2.cust_type_cd,T2.cust_name,T2.cert_type_cd,T2.cert_num,T2.cust_id,T2.acct_id,"
                + "T2.valid_flg,T2.closing_dt,T2.src,T2.set_ctrl_dt,T2.set_ctrl_tm,T2.remit_ctrl_dt,T2.remit_ctrl_tm,"
                + "T2.anti_mon_lau_cust_risk_level_cd,'0','0','" + date + "')";
        printSql(sql, BaseUtils.map());
        super.update(sql, BaseUtils.map());
        String sql1 = "merge into blklist_ctrl T1 using (select id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd," +
                "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,anti_mon_lau_cust_risk_level_cd " +
                "from blklist_ctrl_temp A where A.acct_id is not null and A.cert_num is not null) T2 on (T1.acct_id = T2.acct_id and T1.cert_num = t2.cert_num) "
                + "when matched then update set "
                + "T1.lp_cd = T2.lp_cd,"
                + "T1.blklist_type_cd = T2.blklist_type_cd,"
                + "T1.cust_type_cd = T2.cust_type_cd,"
                + "T1.cust_name = T2.cust_name,"
                + "T1.cert_type_cd = T2.cert_type_cd,"
                + "T1.cust_id = T2.cust_id,"
                + "T1.ctrl_id = T2.ctrl_id,"
                + "T1.valid_flg = T2.valid_flg,"
                + "T1.closing_dt = T2.closing_dt,"
                + "T1.src = T2.src,"
                + "T1.set_ctrl_dt = T2.set_ctrl_dt,"
                + "T1.set_ctrl_tm = T2.set_ctrl_tm,"
                + "T1.remit_ctrl_dt = T2.remit_ctrl_dt,"
                + "T1.remit_ctrl_tm = T2.remit_ctrl_tm,"
                + "T1.anti_mon_lau_cust_risk_level_cd = T2.anti_mon_lau_cust_risk_level_cd,"
                + "T1.risk_level = '0',"
                + "T1.update_time = '" + date + "' "
                + "when not matched then insert (id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd,"
                + "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,"
                + "anti_mon_lau_cust_risk_level_cd,risk_level,is_dep_acct_basic,create_time)"
                + "VALUES (T2.id,T2.lp_cd,T2.blklist_type_cd,T2.ctrl_id,T2.cust_type_cd,T2.cust_name,T2.cert_type_cd,T2.cert_num,T2.cust_id,T2.acct_id,"
                + "T2.valid_flg,T2.closing_dt,T2.src,T2.set_ctrl_dt,T2.set_ctrl_tm,T2.remit_ctrl_dt,T2.remit_ctrl_tm,"
                + "T2.anti_mon_lau_cust_risk_level_cd,'0','0','" + date + "')";
        printSql(sql1, BaseUtils.map());
        super.update(sql1, BaseUtils.map());
        String sql2 = "merge into blklist_ctrl T1 using (select id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd," +
                "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,anti_mon_lau_cust_risk_level_cd " +
                "from blklist_ctrl_temp A where A.acct_id is null and A.cert_num is not null) T2 on (T1.cert_num = T2.cert_num) "
                + "when matched then update set T1.remit_ctrl_dt = T2.remit_ctrl_dt,T1.blklist_type_cd = T2.blklist_type_cd,T1.valid_flg = T2.valid_flg "
                + "when not matched then insert (id,lp_cd,blklist_type_cd,ctrl_id,cust_type_cd,cust_name,cert_type_cd,"
                + "cert_num,cust_id,acct_id,valid_flg,closing_dt,src,set_ctrl_dt,set_ctrl_tm,remit_ctrl_dt,remit_ctrl_tm,"
                + "anti_mon_lau_cust_risk_level_cd,risk_level,is_dep_acct_basic,create_time)"
                + "VALUES (T2.id,T2.lp_cd,T2.blklist_type_cd,T2.ctrl_id,T2.cust_type_cd,T2.cust_name,T2.cert_type_cd,T2.cert_num,T2.cust_id,T2.acct_id,"
                + "T2.valid_flg,T2.closing_dt,T2.src,T2.set_ctrl_dt,T2.set_ctrl_tm,T2.remit_ctrl_dt,T2.remit_ctrl_tm,"
                + "T2.anti_mon_lau_cust_risk_level_cd,'0','0','" + date + "')";
        printSql(sql2, BaseUtils.map());

        return super.update(sql2, BaseUtils.map());
    }

    public void synByDepAcctBasicData() {
        String date = DateUtils.getDate();
        String sql = "merge into blklist_ctrl t1 using (select * from dep_acct_basic where 1=1 and substr(acct_grad_flg_bit, 7, 1) in ('7','8'))t2 " +
                "on (t1.acct_id = t2.acct_id ) when matched then update set t1.is_dep_acct_basic = '1' " +
                "when not matched then insert (id,lp_cd,blklist_type_cd," +
                "cust_name,cert_type_cd,cust_id,acct_id,valid_flg,src,risk_level,create_time,set_ctrl_dt,closing_dt,is_dep_acct_basic) values " +
                "(t2.id,t2.lp_cd,'7',t2.cust_name,'000000',T2.cust_id,T2.acct_id,'1','999999','1','" + date + "','" + date + "','2199-12-31','1')";

        printSql(sql, BaseUtils.map());
        super.update(sql, BaseUtils.map());
    }

    public List<Map<String, Object>> queryByConditionToInspection(Map<String, Object> p) {
        String sql = Sql.create("select t3.tlrclt,count(distinct t1.acct_id)count from blklist_ctrl t1 " +
                "left join thfacct t2 on t1.acct_id = t2.richnbr " +
                "left join tclient t3 on t3.cltnbr = t2.cltnbr where 1=1 ")
                .append(and("t1.lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("t1.blklist_type_cd not in ('3','4','5')"))
                .append(and("t1.ctrl_id = :ctrl_id", hasKey(p, "ctrl_id")))
                .append(and("t1.cust_type_cd = :cust_type_cd", hasKey(p, "cust_type_cd")))
                .append(and("t1.cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("t1.cert_type_cd = :cert_type_cd", hasKey(p, "cert_type_cd")))
                .append(and("t1.cert_num = :cert_num", hasKey(p, "cert_num")))
                .append(and("t1.cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("t1.acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("t1.valid_flg = :valid_flg", hasKey(p, "valid_flg")))
                .append(and("t1.closing_dt = :closing_dt", hasKey(p, "closing_dt")))
                .append(and("t1.src = :src", hasKey(p, "src")))
                .append(and("t1.set_ctrl_dt = :set_ctrl_dt", hasKey(p, "set_ctrl_dt")))
                .append(and("t1.set_ctrl_tm = :set_ctrl_tm", hasKey(p, "set_ctrl_tm")))
                .append(and("t1.remit_ctrl_dt = :remit_ctrl_dt", hasKey(p, "remit_ctrl_dt")))
                .append(and("t1.remit_ctrl_tm = :remit_ctrl_tm", hasKey(p, "remit_ctrl_tm")))
                .append(and("t1.anti_mon_lau_cust_risk_level_cd = :anti_mon_lau_cust_risk_level_cd", hasKey(p, "anti_mon_lau_cust_risk_level_cd")))
                .append(and("t1.risk_level = :risk_level", hasKey(p, "risk_level")))
                .append(and("t1.is_dep_acct_basic = :is_dep_acct_basic", hasKey(p, "is_dep_acct_basic")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
                .append("group by t3.tlrclt")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryOfConjunctive(Map<String, Object> p) {
        String date = DateUtils.getDate();
        String sql = Sql.create("select * from (select acct_id,risk_level,closing_dt,set_ctrl_dt,valid_flg,blklist_type_cd from blklist_ctrl where cert_num = :cert_num " +
                "union select acct_id,risk_level,closing_dt,set_ctrl_dt,valid_flg,blklist_type_cd from blklist_ctrl where acct_id = :acct_id " +
                "union select acct_id,risk_level,closing_dt,set_ctrl_dt,valid_flg,blklist_type_cd from blklist_ctrl where acct_id = :card_no " +
                "union select a.acct_id,a.risk_level,a.closing_dt,a.set_ctrl_dt,a.valid_flg,a.blklist_type_cd from blklist_ctrl a " +
                "left join thfacct b on b.cltnbr = a.cust_id " + "where b.cardno = :card_no ) t where " +
                "t.closing_dt >= :endDate and t.set_ctrl_dt <= :endDate and t.valid_flg = '1' and t.blklist_type_cd not in ('3','4','5')").toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> isExist(Map<String, Object> p) {
        String sql = Sql.create("select * from BLKLIST_CTRL where ")
                .append("cert_num = :cert_num and ")
                .append("valid_flg = '1' ")
                .append("union select * from BLKLIST_CTRL where ")
                .append("acct_id = :acct_id and ")
                .append("valid_flg = '1'")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }
}
