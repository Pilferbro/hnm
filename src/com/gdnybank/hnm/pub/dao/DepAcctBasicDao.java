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
 * 表名:DEP_ACCT_BASIC
 * 主键:
 **/
@Repository
public class DepAcctBasicDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"id", "lp_cd", "acct_id", "card_no", "cust_name", "cust_id", "acct_char_cd", "acct_status_cd", "acct_grad_flg_bit"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"id"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("id", sequenceService.getTableFlowNo("BLKLIST_CTRL", "id"));
        String sql = Sql.create("insert into DEP_ACCT_BASIC (")
                .append(field("id "))
                .append(field("lp_cd ", hasKey(p, "lp_cd")))
                .append(field("acct_id ", hasKey(p, "acct_id")))
                .append(field("card_no ", hasKey(p, "card_no")))
                .append(field("cust_name ", hasKey(p, "cust_name")))
                .append(field("cust_id ", hasKey(p, "cust_id")))
                .append(field("acct_char_cd ", hasKey(p, "acct_char_cd")))
                .append(field("acct_status_cd ", hasKey(p, "acct_status_cd")))
                .append(field("acct_status_cd ", hasKey(p, "acct_status_cd")))
                .append(field("acct_grad_flg_bit ", hasKey(p, "acct_grad_flg_bit")))
                .append(") values (")
                .append(field(":id"))
                .append(field(":lp_cd ", hasKey(p, "lp_cd")))
                .append(field(":acct_id ", hasKey(p, "acct_id")))
                .append(field(":card_no ", hasKey(p, "card_no")))
                .append(field(":cust_name ", hasKey(p, "cust_name")))
                .append(field(":cust_id ", hasKey(p, "cust_id")))
                .append(field(":acct_char_cd ", hasKey(p, "acct_char_cd")))
                .append(field(":acct_status_cd ", hasKey(p, "acct_status_cd")))
                .append(field(":acct_grad_flg_bit ", hasKey(p, "acct_grad_flg_bit")))
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
        String sql = Sql.create("delete from DEP_ACCT_BASIC where 1=1 ")
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
        String sql = Sql.create("delete from DEP_ACCT_BASIC where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update DEP_ACCT_BASIC set ")
                .append(field("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(field("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(field("card_no = :card_no", hasKey(p, "card_no")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(field("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(field("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(field("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .append(" where 1=1 ")
                .append(and("id = :id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update DEP_ACCT_BASIC set ")
                .append(field("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(field("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(field("card_no = :card_no", hasKey(p, "card_no")))
                .append(field("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(field("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(field("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(field("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(field("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .append(" where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
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
        String sql = Sql.create("select * from DEP_ACCT_BASIC where 1=1")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from DEP_ACCT_BASIC where 1=1 and substr(acct_grad_flg_bit, 7, 1) in ('7','8') ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")));

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
        String sql = Sql.create("select * from DEP_ACCT_BASIC where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from DEP_ACCT_BASIC where 1=1 ")
                .append(and("id = :id",hasKey(p, "id")))
                .append(and("lp_cd = :lp_cd", hasKey(p, "lp_cd")))
                .append(and("acct_id = :acct_id", hasKey(p, "acct_id")))
                .append(and("card_no = :card_no", hasKey(p, "card_no")))
                .append(and("cust_name = :cust_name", hasKey(p, "cust_name")))
                .append(and("cust_id = :cust_id", hasKey(p, "cust_id")))
                .append(and("acct_char_cd = :acct_char_cd", hasKey(p, "acct_char_cd")))
                .append(and("acct_status_cd = :acct_status_cd", hasKey(p, "acct_status_cd")))
                .append(and("acct_grad_flg_bit = :acct_grad_flg_bit", hasKey(p, "acct_grad_flg_bit")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public void synByTempData() {
        String sql = "merge into dep_acct_basic t1 using dep_acct_basic_temp t2 on (t1.acct_id = t2.acct_id) " +
                "when matched then update set " +
                "t1.lp_cd = t2.lp_cd,t1.card_no = t2.card_no," +
                "t1.cust_name = t2.cust_name, t1.cust_id = t2.cust_id," +
                "t1.acct_char_cd = t2.acct_char_cd, t1.acct_status_cd = t2.acct_status_cd," +
                "t1.acct_grad_flg_bit = t2.acct_grad_flg_bit " +
                "when not matched then insert (id,lp_cd,acct_id,card_no,cust_name,cust_id,acct_char_cd,acct_status_cd," +
                "acct_grad_flg_bit) values (t2.id,t2.lp_cd,t2.acct_id,t2.card_no,t2.cust_name,t2.cust_id,t2.acct_char_cd," +
                "t2.acct_status_cd,t2.acct_grad_flg_bit)";
        printSql(sql, BaseUtils.map());
        super.update(sql, BaseUtils.map());
    }

    public int truncate() {
        return super.update("truncate table DEP_ACCT_BASIC", BaseUtils.map());
    }
}