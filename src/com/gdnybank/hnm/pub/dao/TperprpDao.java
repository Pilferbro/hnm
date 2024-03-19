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

import cn.hutool.core.util.ObjectUtil;
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
 * 表名:TPERPRP
 * 主键:
 * acctday
 * cltnbr
 * proptype
 **/
@Repository
public class TperprpDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"cltnbr", "ccynbr", "proptype", "onlbal01", "onlbal02", "onlbal03", "onlbal04", "onlbal05", "onlbal06", "onlbal07", "onlbal08", "onlbal09", "onlbal10", "onlbal11", "onlbal12", "onlbal13", "onlbal14", "onlbal15", "onlbal16", "onlbal17", "onlbal18", "onlbal19", "onlbal20", "onlbal21", "onlbal22", "onlbal23", "onlbal24", "onlbal25", "onlbal26", "onlbal27", "onlbal28", "onlbal29", "onlbal30", "onlbal31", "quaraccum01", "quaraccum02", "quaraccum03", "quaraccum04", "quaraccum05", "quaraccum06", "quaraccum07", "quaraccum08", "quaraccum09", "quaraccum10", "quaraccum11", "quaraccum12", "quaraccum13", "quaraccum14", "quaraccum15", "quaraccum16", "quaraccum17", "quaraccum18", "quaraccum19", "quaraccum20", "quaraccum21", "quaraccum22", "quaraccum23", "quaraccum24", "quaraccum25", "quaraccum26", "quaraccum27", "quaraccum28", "quaraccum29", "quaraccum30", "quaraccum31", "monthlybal", "lastmonthbal", "lastquarbal2", "lastyearbal", "lastmonthaccum", "lastmonthaccum2", "lastquaraccum2", "lastyearaccum", "lastmonthquaraccum", "lastquarquaraccum", "lastyearquaraccum", "lastyearmonthaccum", "monthaccum", "quaraccum2", "yearaccum", "acctday", "updatedate"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"acctday", "cltnbr", "proptype"};

    @Override
    public int save(Map<String, Object> p) {
        //p.put("acctday",sequenceService.getTableFlowNo("TPERPRP", "acctday"));
        //p.put("cltnbr",sequenceService.getTableFlowNo("TPERPRP", "cltnbr"));
        //p.put("proptype",sequenceService.getTableFlowNo("TPERPRP", "proptype"));
        String sql = Sql.create("insert into TPERPRP (")
                .append(field("acctday "))
                .append(field("cltnbr "))
                .append(field("proptype "))
                .append(field("ccynbr ", hasKey(p, "ccynbr")))
                .append(field("onlbal01 ", hasKey(p, "onlbal01")))
                .append(field("onlbal02 ", hasKey(p, "onlbal02")))
                .append(field("onlbal03 ", hasKey(p, "onlbal03")))
                .append(field("onlbal04 ", hasKey(p, "onlbal04")))
                .append(field("onlbal05 ", hasKey(p, "onlbal05")))
                .append(field("onlbal06 ", hasKey(p, "onlbal06")))
                .append(field("onlbal07 ", hasKey(p, "onlbal07")))
                .append(field("onlbal08 ", hasKey(p, "onlbal08")))
                .append(field("onlbal09 ", hasKey(p, "onlbal09")))
                .append(field("onlbal10 ", hasKey(p, "onlbal10")))
                .append(field("onlbal11 ", hasKey(p, "onlbal11")))
                .append(field("onlbal12 ", hasKey(p, "onlbal12")))
                .append(field("onlbal13 ", hasKey(p, "onlbal13")))
                .append(field("onlbal14 ", hasKey(p, "onlbal14")))
                .append(field("onlbal15 ", hasKey(p, "onlbal15")))
                .append(field("onlbal16 ", hasKey(p, "onlbal16")))
                .append(field("onlbal17 ", hasKey(p, "onlbal17")))
                .append(field("onlbal18 ", hasKey(p, "onlbal18")))
                .append(field("onlbal19 ", hasKey(p, "onlbal19")))
                .append(field("onlbal20 ", hasKey(p, "onlbal20")))
                .append(field("onlbal21 ", hasKey(p, "onlbal21")))
                .append(field("onlbal22 ", hasKey(p, "onlbal22")))
                .append(field("onlbal23 ", hasKey(p, "onlbal23")))
                .append(field("onlbal24 ", hasKey(p, "onlbal24")))
                .append(field("onlbal25 ", hasKey(p, "onlbal25")))
                .append(field("onlbal26 ", hasKey(p, "onlbal26")))
                .append(field("onlbal27 ", hasKey(p, "onlbal27")))
                .append(field("onlbal28 ", hasKey(p, "onlbal28")))
                .append(field("onlbal29 ", hasKey(p, "onlbal29")))
                .append(field("onlbal30 ", hasKey(p, "onlbal30")))
                .append(field("onlbal31 ", hasKey(p, "onlbal31")))
                .append(field("quaraccum01 ", hasKey(p, "quaraccum01")))
                .append(field("quaraccum02 ", hasKey(p, "quaraccum02")))
                .append(field("quaraccum03 ", hasKey(p, "quaraccum03")))
                .append(field("quaraccum04 ", hasKey(p, "quaraccum04")))
                .append(field("quaraccum05 ", hasKey(p, "quaraccum05")))
                .append(field("quaraccum06 ", hasKey(p, "quaraccum06")))
                .append(field("quaraccum07 ", hasKey(p, "quaraccum07")))
                .append(field("quaraccum08 ", hasKey(p, "quaraccum08")))
                .append(field("quaraccum09 ", hasKey(p, "quaraccum09")))
                .append(field("quaraccum10 ", hasKey(p, "quaraccum10")))
                .append(field("quaraccum11 ", hasKey(p, "quaraccum11")))
                .append(field("quaraccum12 ", hasKey(p, "quaraccum12")))
                .append(field("quaraccum13 ", hasKey(p, "quaraccum13")))
                .append(field("quaraccum14 ", hasKey(p, "quaraccum14")))
                .append(field("quaraccum15 ", hasKey(p, "quaraccum15")))
                .append(field("quaraccum16 ", hasKey(p, "quaraccum16")))
                .append(field("quaraccum17 ", hasKey(p, "quaraccum17")))
                .append(field("quaraccum18 ", hasKey(p, "quaraccum18")))
                .append(field("quaraccum19 ", hasKey(p, "quaraccum19")))
                .append(field("quaraccum20 ", hasKey(p, "quaraccum20")))
                .append(field("quaraccum21 ", hasKey(p, "quaraccum21")))
                .append(field("quaraccum22 ", hasKey(p, "quaraccum22")))
                .append(field("quaraccum23 ", hasKey(p, "quaraccum23")))
                .append(field("quaraccum24 ", hasKey(p, "quaraccum24")))
                .append(field("quaraccum25 ", hasKey(p, "quaraccum25")))
                .append(field("quaraccum26 ", hasKey(p, "quaraccum26")))
                .append(field("quaraccum27 ", hasKey(p, "quaraccum27")))
                .append(field("quaraccum28 ", hasKey(p, "quaraccum28")))
                .append(field("quaraccum29 ", hasKey(p, "quaraccum29")))
                .append(field("quaraccum30 ", hasKey(p, "quaraccum30")))
                .append(field("quaraccum31 ", hasKey(p, "quaraccum31")))
                .append(field("monthlybal ", hasKey(p, "monthlybal")))
                .append(field("lastmonthbal ", hasKey(p, "lastmonthbal")))
                .append(field("lastquarbal2 ", hasKey(p, "lastquarbal2")))
                .append(field("lastyearbal ", hasKey(p, "lastyearbal")))
                .append(field("lastmonthaccum ", hasKey(p, "lastmonthaccum")))
                .append(field("lastmonthaccum2 ", hasKey(p, "lastmonthaccum2")))
                .append(field("lastquaraccum2 ", hasKey(p, "lastquaraccum2")))
                .append(field("lastyearaccum ", hasKey(p, "lastyearaccum")))
                .append(field("lastmonthquaraccum ", hasKey(p, "lastmonthquaraccum")))
                .append(field("lastquarquaraccum ", hasKey(p, "lastquarquaraccum")))
                .append(field("lastyearquaraccum ", hasKey(p, "lastyearquaraccum")))
                .append(field("lastyearmonthaccum ", hasKey(p, "lastyearmonthaccum")))
                .append(field("monthaccum ", hasKey(p, "monthaccum")))
                .append(field("quaraccum2 ", hasKey(p, "quaraccum2")))
                .append(field("yearaccum ", hasKey(p, "yearaccum")))
                .append(field("updatedate ", hasKey(p, "updatedate")))
                .append(") values (")
                .append(field(":acctday "))
                .append(field(":cltnbr "))
                .append(field(":proptype "))
                .append(field(":ccynbr ", hasKey(p, "ccynbr")))
                .append(field(":onlbal01 ", hasKey(p, "onlbal01")))
                .append(field(":onlbal02 ", hasKey(p, "onlbal02")))
                .append(field(":onlbal03 ", hasKey(p, "onlbal03")))
                .append(field(":onlbal04 ", hasKey(p, "onlbal04")))
                .append(field(":onlbal05 ", hasKey(p, "onlbal05")))
                .append(field(":onlbal06 ", hasKey(p, "onlbal06")))
                .append(field(":onlbal07 ", hasKey(p, "onlbal07")))
                .append(field(":onlbal08 ", hasKey(p, "onlbal08")))
                .append(field(":onlbal09 ", hasKey(p, "onlbal09")))
                .append(field(":onlbal10 ", hasKey(p, "onlbal10")))
                .append(field(":onlbal11 ", hasKey(p, "onlbal11")))
                .append(field(":onlbal12 ", hasKey(p, "onlbal12")))
                .append(field(":onlbal13 ", hasKey(p, "onlbal13")))
                .append(field(":onlbal14 ", hasKey(p, "onlbal14")))
                .append(field(":onlbal15 ", hasKey(p, "onlbal15")))
                .append(field(":onlbal16 ", hasKey(p, "onlbal16")))
                .append(field(":onlbal17 ", hasKey(p, "onlbal17")))
                .append(field(":onlbal18 ", hasKey(p, "onlbal18")))
                .append(field(":onlbal19 ", hasKey(p, "onlbal19")))
                .append(field(":onlbal20 ", hasKey(p, "onlbal20")))
                .append(field(":onlbal21 ", hasKey(p, "onlbal21")))
                .append(field(":onlbal22 ", hasKey(p, "onlbal22")))
                .append(field(":onlbal23 ", hasKey(p, "onlbal23")))
                .append(field(":onlbal24 ", hasKey(p, "onlbal24")))
                .append(field(":onlbal25 ", hasKey(p, "onlbal25")))
                .append(field(":onlbal26 ", hasKey(p, "onlbal26")))
                .append(field(":onlbal27 ", hasKey(p, "onlbal27")))
                .append(field(":onlbal28 ", hasKey(p, "onlbal28")))
                .append(field(":onlbal29 ", hasKey(p, "onlbal29")))
                .append(field(":onlbal30 ", hasKey(p, "onlbal30")))
                .append(field(":onlbal31 ", hasKey(p, "onlbal31")))
                .append(field(":quaraccum01 ", hasKey(p, "quaraccum01")))
                .append(field(":quaraccum02 ", hasKey(p, "quaraccum02")))
                .append(field(":quaraccum03 ", hasKey(p, "quaraccum03")))
                .append(field(":quaraccum04 ", hasKey(p, "quaraccum04")))
                .append(field(":quaraccum05 ", hasKey(p, "quaraccum05")))
                .append(field(":quaraccum06 ", hasKey(p, "quaraccum06")))
                .append(field(":quaraccum07 ", hasKey(p, "quaraccum07")))
                .append(field(":quaraccum08 ", hasKey(p, "quaraccum08")))
                .append(field(":quaraccum09 ", hasKey(p, "quaraccum09")))
                .append(field(":quaraccum10 ", hasKey(p, "quaraccum10")))
                .append(field(":quaraccum11 ", hasKey(p, "quaraccum11")))
                .append(field(":quaraccum12 ", hasKey(p, "quaraccum12")))
                .append(field(":quaraccum13 ", hasKey(p, "quaraccum13")))
                .append(field(":quaraccum14 ", hasKey(p, "quaraccum14")))
                .append(field(":quaraccum15 ", hasKey(p, "quaraccum15")))
                .append(field(":quaraccum16 ", hasKey(p, "quaraccum16")))
                .append(field(":quaraccum17 ", hasKey(p, "quaraccum17")))
                .append(field(":quaraccum18 ", hasKey(p, "quaraccum18")))
                .append(field(":quaraccum19 ", hasKey(p, "quaraccum19")))
                .append(field(":quaraccum20 ", hasKey(p, "quaraccum20")))
                .append(field(":quaraccum21 ", hasKey(p, "quaraccum21")))
                .append(field(":quaraccum22 ", hasKey(p, "quaraccum22")))
                .append(field(":quaraccum23 ", hasKey(p, "quaraccum23")))
                .append(field(":quaraccum24 ", hasKey(p, "quaraccum24")))
                .append(field(":quaraccum25 ", hasKey(p, "quaraccum25")))
                .append(field(":quaraccum26 ", hasKey(p, "quaraccum26")))
                .append(field(":quaraccum27 ", hasKey(p, "quaraccum27")))
                .append(field(":quaraccum28 ", hasKey(p, "quaraccum28")))
                .append(field(":quaraccum29 ", hasKey(p, "quaraccum29")))
                .append(field(":quaraccum30 ", hasKey(p, "quaraccum30")))
                .append(field(":quaraccum31 ", hasKey(p, "quaraccum31")))
                .append(field(":monthlybal ", hasKey(p, "monthlybal")))
                .append(field(":lastmonthbal ", hasKey(p, "lastmonthbal")))
                .append(field(":lastquarbal2 ", hasKey(p, "lastquarbal2")))
                .append(field(":lastyearbal ", hasKey(p, "lastyearbal")))
                .append(field(":lastmonthaccum ", hasKey(p, "lastmonthaccum")))
                .append(field(":lastmonthaccum2 ", hasKey(p, "lastmonthaccum2")))
                .append(field(":lastquaraccum2 ", hasKey(p, "lastquaraccum2")))
                .append(field(":lastyearaccum ", hasKey(p, "lastyearaccum")))
                .append(field(":lastmonthquaraccum ", hasKey(p, "lastmonthquaraccum")))
                .append(field(":lastquarquaraccum ", hasKey(p, "lastquarquaraccum")))
                .append(field(":lastyearquaraccum ", hasKey(p, "lastyearquaraccum")))
                .append(field(":lastyearmonthaccum ", hasKey(p, "lastyearmonthaccum")))
                .append(field(":monthaccum ", hasKey(p, "monthaccum")))
                .append(field(":quaraccum2 ", hasKey(p, "quaraccum2")))
                .append(field(":yearaccum ", hasKey(p, "yearaccum")))
                .append(field(":updatedate ", hasKey(p, "updatedate")))
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
        String sql = Sql.create("delete from TPERPRP where 1=1 ")
                .append(and("acctday = :acctday"))
                .append(and("cltnbr = :cltnbr"))
                .append(and("proptype = :proptype"))
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
        String sql = Sql.create("delete from TPERPRP where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update TPERPRP set ")
                .append(field("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(field("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(field("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(field("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(field("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(field("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(field("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(field("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(field("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(field("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(field("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(field("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(field("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(field("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(field("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(field("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(field("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(field("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(field("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(field("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(field("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(field("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(field("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(field("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(field("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(field("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(field("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(field("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(field("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(field("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(field("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(field("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(field("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(field("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(field("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(field("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(field("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(field("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(field("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(field("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(field("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(field("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(field("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(field("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(field("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(field("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(field("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(field("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(field("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(field("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(field("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(field("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(field("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(field("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(field("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(field("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(field("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(field("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(field("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(field("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(field("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(field("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(field("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(field("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(field("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(field("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(field("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(field("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(field("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(field("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(field("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(field("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(field("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(field("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(field("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(field("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(field("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(field("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(field("updatedate = :updatedate", hasKey(p, "updatedate")))
                .append(" where 1=1 ")
                .append(and("acctday = :acctday"))
                .append(and("cltnbr = :cltnbr"))
                .append(and("proptype = :proptype"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update TPERPRP set ")
                .append(field("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(field("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(field("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(field("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(field("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(field("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(field("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(field("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(field("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(field("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(field("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(field("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(field("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(field("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(field("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(field("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(field("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(field("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(field("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(field("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(field("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(field("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(field("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(field("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(field("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(field("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(field("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(field("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(field("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(field("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(field("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(field("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(field("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(field("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(field("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(field("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(field("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(field("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(field("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(field("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(field("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(field("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(field("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(field("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(field("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(field("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(field("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(field("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(field("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(field("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(field("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(field("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(field("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(field("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(field("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(field("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(field("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(field("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(field("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(field("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(field("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(field("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(field("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(field("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(field("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(field("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(field("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(field("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(field("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(field("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(field("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(field("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(field("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(field("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(field("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(field("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(field("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(field("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(field("updatedate = :updatedate", hasKey(p, "updatedate")))
                .append(" where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")))
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
        String sql = Sql.create("select * from TPERPRP where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from TPERPRP where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")));

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
        String sql = Sql.create("select * from TPERPRP where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from TPERPRP where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(and("updatedate = :updatedate", hasKey(p, "updatedate")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> queryForListById(Map<String, Object> p) {
        String sql = Sql.create("select * from TPERPRP where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("proptype = :proptype", hasKey(p, "proptype")))
                .append(and("acctday = :acctday", hasKey(p, "acctday")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryZC(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.cltnbr");
        if (ObjectUtil.isNotEmpty(p.get("onlbal"))) {
            sql.append(",sum(" + p.get("onlbal") + ") amt1");
            sql.append(",sum(" + p.get("onlbal") + " - lastmonthbal) ass_month_bal");
            sql.append(",sum(" + p.get("onlbal") + " - lastyearbal) ass_year_bal");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear"))) {
            sql.append(",sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " amt2");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth"))) {
            sql.append(",sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " amt3");
        }
        if (ObjectUtil.isNotEmpty(p.get("quararg"))) {
            sql.append(",sum(" + p.get("quararg") + ")/90 quararg");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear")) && ObjectUtil.isNotEmpty(p.get("daysOfLastMonth"))) {
            sql.append(",(sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " - sum(yearaccum-monthaccum)/" + p.get("daysOfLastMonth") + ") ave_ass_month_bal");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear")) && ObjectUtil.isNotEmpty(p.get("daysOfLastYear"))) {
            sql.append(",(sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " - sum(lastyearaccum)/" + p.get("daysOfLastYear") + ") ave_ass_year_bal");
        }

        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth")) && ObjectUtil.isNotEmpty(p.get("day_2"))) {
            sql.append(",(sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " - sum(lastmonthaccum)/" + p.get("day_2") + ") ave_ass_month_bal_2");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth")) && ObjectUtil.isNotEmpty(p.get("day_3"))) {
            sql.append(",(sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " - sum(lastyearmonthaccum)) ave_ass_year_bal_2");
        }

        sql.append(" from TPERPRP t1 left join TCLIENT t2 on t1.cltnbr = t2.cltnbr where 1=1")
                .append(and("t1.cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("t1.ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("t1.proptype = :proptype", hasKey(p, "proptype")))
                .append(and("t1.onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("t1.onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("t1.onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("t1.onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("t1.onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("t1.onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("t1.onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("t1.onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("t1.onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("t1.onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("t1.onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("t1.onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("t1.onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("t1.onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("t1.onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("t1.onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("t1.onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("t1.onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("t1.onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("t1.onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("t1.onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("t1.onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("t1.onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("t1.onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("t1.onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("t1.onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("t1.onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("t1.onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("t1.onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("t1.onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("t1.onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("t1.quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("t1.quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("t1.quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("t1.quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("t1.quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("t1.quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("t1.quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("t1.quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("t1.quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("t1.quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("t1.quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("t1.quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("t1.quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("t1.quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("t1.quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("t1.quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("t1.quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("t1.quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("t1.quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("t1.quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("t1.quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("t1.quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("t1.quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("t1.quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("t1.quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("t1.quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("t1.quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("t1.quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("t1.quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("t1.quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("t1.quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("t1.monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("t1.lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("t1.lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("t1.lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("t1.lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("t1.lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("t1.lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("t1.lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("t1.lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("t1.lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("t1.lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("t1.lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("t1.monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("t1.quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("t1.yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("t1.acctday = :acctday", hasKey(p, "acctday")))
                .append(and("t1.updatedate = :updatedate", hasKey(p, "updatedate")))
        ;
        if (ObjectUtil.isNotEmpty(p.get("siteIds"))) {
            sql.append(and("t2.tlrclt in (" + p.get("siteIds") + ")"));
        }
        sql.append("and t1.proptype between 1 and 9 group by t1.cltnbr");
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryCK(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.cltnbr");
        if (ObjectUtil.isNotEmpty(p.get("onlbal"))) {
            sql.append(",sum(" + p.get("onlbal") + ") amt1");
            sql.append(",sum(" + p.get("onlbal") + " - lastmonthbal) ass_month_bal");
            sql.append(",sum(" + p.get("onlbal") + " - lastyearbal) ass_year_bal");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear"))) {
            sql.append(",sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " amt2");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth"))) {
            sql.append(",sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " amt3");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear")) && ObjectUtil.isNotEmpty(p.get("daysOfLastMonth"))) {
            sql.append(",(sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " - sum(yearaccum-monthaccum)/" + p.get("daysOfLastMonth") + ") ave_ass_month_bal");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq1")) && ObjectUtil.isNotEmpty(p.get("dayOfYear")) && ObjectUtil.isNotEmpty(p.get("daysOfLastYear"))) {
            sql.append(",(sum(" + p.get("sq1") + ")/" + p.get("dayOfYear") + " - sum(lastyearaccum)/" + p.get("daysOfLastYear") + ") ave_ass_year_bal");
        }

        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth")) && ObjectUtil.isNotEmpty(p.get("day_2"))) {
            sql.append(",(sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " - sum(lastmonthaccum)/" + p.get("day_2") + ") ave_ass_month_bal_2");
        }
        if (ObjectUtil.isNotEmpty(p.get("sq2")) && ObjectUtil.isNotEmpty(p.get("dayOfMonth")) && ObjectUtil.isNotEmpty(p.get("day_3"))) {
            sql.append(",(sum(" + p.get("sq2") + ")/" + p.get("dayOfMonth") + " - sum(lastyearmonthaccum)) ave_ass_year_bal_2");
        }
        sql.append(" from TPERPRP t1 left join TCLIENT t2 on t1.cltnbr = t2.cltnbr where 1=1")
                .append(and("t1.cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("t1.ccynbr = :ccynbr", hasKey(p, "ccynbr")))
                .append(and("t1.proptype = :proptype", hasKey(p, "proptype")))
                .append(and("t1.onlbal01 = :onlbal01", hasKey(p, "onlbal01")))
                .append(and("t1.onlbal02 = :onlbal02", hasKey(p, "onlbal02")))
                .append(and("t1.onlbal03 = :onlbal03", hasKey(p, "onlbal03")))
                .append(and("t1.onlbal04 = :onlbal04", hasKey(p, "onlbal04")))
                .append(and("t1.onlbal05 = :onlbal05", hasKey(p, "onlbal05")))
                .append(and("t1.onlbal06 = :onlbal06", hasKey(p, "onlbal06")))
                .append(and("t1.onlbal07 = :onlbal07", hasKey(p, "onlbal07")))
                .append(and("t1.onlbal08 = :onlbal08", hasKey(p, "onlbal08")))
                .append(and("t1.onlbal09 = :onlbal09", hasKey(p, "onlbal09")))
                .append(and("t1.onlbal10 = :onlbal10", hasKey(p, "onlbal10")))
                .append(and("t1.onlbal11 = :onlbal11", hasKey(p, "onlbal11")))
                .append(and("t1.onlbal12 = :onlbal12", hasKey(p, "onlbal12")))
                .append(and("t1.onlbal13 = :onlbal13", hasKey(p, "onlbal13")))
                .append(and("t1.onlbal14 = :onlbal14", hasKey(p, "onlbal14")))
                .append(and("t1.onlbal15 = :onlbal15", hasKey(p, "onlbal15")))
                .append(and("t1.onlbal16 = :onlbal16", hasKey(p, "onlbal16")))
                .append(and("t1.onlbal17 = :onlbal17", hasKey(p, "onlbal17")))
                .append(and("t1.onlbal18 = :onlbal18", hasKey(p, "onlbal18")))
                .append(and("t1.onlbal19 = :onlbal19", hasKey(p, "onlbal19")))
                .append(and("t1.onlbal20 = :onlbal20", hasKey(p, "onlbal20")))
                .append(and("t1.onlbal21 = :onlbal21", hasKey(p, "onlbal21")))
                .append(and("t1.onlbal22 = :onlbal22", hasKey(p, "onlbal22")))
                .append(and("t1.onlbal23 = :onlbal23", hasKey(p, "onlbal23")))
                .append(and("t1.onlbal24 = :onlbal24", hasKey(p, "onlbal24")))
                .append(and("t1.onlbal25 = :onlbal25", hasKey(p, "onlbal25")))
                .append(and("t1.onlbal26 = :onlbal26", hasKey(p, "onlbal26")))
                .append(and("t1.onlbal27 = :onlbal27", hasKey(p, "onlbal27")))
                .append(and("t1.onlbal28 = :onlbal28", hasKey(p, "onlbal28")))
                .append(and("t1.onlbal29 = :onlbal29", hasKey(p, "onlbal29")))
                .append(and("t1.onlbal30 = :onlbal30", hasKey(p, "onlbal30")))
                .append(and("t1.onlbal31 = :onlbal31", hasKey(p, "onlbal31")))
                .append(and("t1.quaraccum01 = :quaraccum01", hasKey(p, "quaraccum01")))
                .append(and("t1.quaraccum02 = :quaraccum02", hasKey(p, "quaraccum02")))
                .append(and("t1.quaraccum03 = :quaraccum03", hasKey(p, "quaraccum03")))
                .append(and("t1.quaraccum04 = :quaraccum04", hasKey(p, "quaraccum04")))
                .append(and("t1.quaraccum05 = :quaraccum05", hasKey(p, "quaraccum05")))
                .append(and("t1.quaraccum06 = :quaraccum06", hasKey(p, "quaraccum06")))
                .append(and("t1.quaraccum07 = :quaraccum07", hasKey(p, "quaraccum07")))
                .append(and("t1.quaraccum08 = :quaraccum08", hasKey(p, "quaraccum08")))
                .append(and("t1.quaraccum09 = :quaraccum09", hasKey(p, "quaraccum09")))
                .append(and("t1.quaraccum10 = :quaraccum10", hasKey(p, "quaraccum10")))
                .append(and("t1.quaraccum11 = :quaraccum11", hasKey(p, "quaraccum11")))
                .append(and("t1.quaraccum12 = :quaraccum12", hasKey(p, "quaraccum12")))
                .append(and("t1.quaraccum13 = :quaraccum13", hasKey(p, "quaraccum13")))
                .append(and("t1.quaraccum14 = :quaraccum14", hasKey(p, "quaraccum14")))
                .append(and("t1.quaraccum15 = :quaraccum15", hasKey(p, "quaraccum15")))
                .append(and("t1.quaraccum16 = :quaraccum16", hasKey(p, "quaraccum16")))
                .append(and("t1.quaraccum17 = :quaraccum17", hasKey(p, "quaraccum17")))
                .append(and("t1.quaraccum18 = :quaraccum18", hasKey(p, "quaraccum18")))
                .append(and("t1.quaraccum19 = :quaraccum19", hasKey(p, "quaraccum19")))
                .append(and("t1.quaraccum20 = :quaraccum20", hasKey(p, "quaraccum20")))
                .append(and("t1.quaraccum21 = :quaraccum21", hasKey(p, "quaraccum21")))
                .append(and("t1.quaraccum22 = :quaraccum22", hasKey(p, "quaraccum22")))
                .append(and("t1.quaraccum23 = :quaraccum23", hasKey(p, "quaraccum23")))
                .append(and("t1.quaraccum24 = :quaraccum24", hasKey(p, "quaraccum24")))
                .append(and("t1.quaraccum25 = :quaraccum25", hasKey(p, "quaraccum25")))
                .append(and("t1.quaraccum26 = :quaraccum26", hasKey(p, "quaraccum26")))
                .append(and("t1.quaraccum27 = :quaraccum27", hasKey(p, "quaraccum27")))
                .append(and("t1.quaraccum28 = :quaraccum28", hasKey(p, "quaraccum28")))
                .append(and("t1.quaraccum29 = :quaraccum29", hasKey(p, "quaraccum29")))
                .append(and("t1.quaraccum30 = :quaraccum30", hasKey(p, "quaraccum30")))
                .append(and("t1.quaraccum31 = :quaraccum31", hasKey(p, "quaraccum31")))
                .append(and("t1.monthlybal = :monthlybal", hasKey(p, "monthlybal")))
                .append(and("t1.lastmonthbal = :lastmonthbal", hasKey(p, "lastmonthbal")))
                .append(and("t1.lastquarbal2 = :lastquarbal2", hasKey(p, "lastquarbal2")))
                .append(and("t1.lastyearbal = :lastyearbal", hasKey(p, "lastyearbal")))
                .append(and("t1.lastmonthaccum = :lastmonthaccum", hasKey(p, "lastmonthaccum")))
                .append(and("t1.lastmonthaccum2 = :lastmonthaccum2", hasKey(p, "lastmonthaccum2")))
                .append(and("t1.lastquaraccum2 = :lastquaraccum2", hasKey(p, "lastquaraccum2")))
                .append(and("t1.lastyearaccum = :lastyearaccum", hasKey(p, "lastyearaccum")))
                .append(and("t1.lastmonthquaraccum = :lastmonthquaraccum", hasKey(p, "lastmonthquaraccum")))
                .append(and("t1.lastquarquaraccum = :lastquarquaraccum", hasKey(p, "lastquarquaraccum")))
                .append(and("t1.lastyearquaraccum = :lastyearquaraccum", hasKey(p, "lastyearquaraccum")))
                .append(and("t1.lastyearmonthaccum = :lastyearmonthaccum", hasKey(p, "lastyearmonthaccum")))
                .append(and("t1.monthaccum = :monthaccum", hasKey(p, "monthaccum")))
                .append(and("t1.quaraccum2 = :quaraccum2", hasKey(p, "quaraccum2")))
                .append(and("t1.yearaccum = :yearaccum", hasKey(p, "yearaccum")))
                .append(and("t1.acctday = :acctday", hasKey(p, "acctday")))
                .append(and("t1.updatedate = :updatedate", hasKey(p, "updatedate")))
        ;
        if (ObjectUtil.isNotEmpty(p.get("siteIds"))) {
            sql.append(and("t2.tlrclt in (" + p.get("siteIds") + ")"));
        }
        sql.append("and t1.proptype in (1,2) group by t1.cltnbr");
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    /**
     * 同步“TPERPRP_TEMP”表中的数据，存在则更新，不存在则插入
     *
     * @return
     * @author chenhao
     */
    public int synByTempData() {
        String sql = "MERGE INTO TPERPRP T1 USING TPERPRP_TEMP T2 ON (T1.acctday = T2.acctday and T1.cltnbr = T2.cltnbr and T1.proptype = T2.proptype) "
                + "WHEN MATCHED THEN UPDATE SET "
                + "T1.ccynbr = T2.ccynbr, "
                + "T1.onlbal01 = T2.onlbal01,"
                + "T1.onlbal02 = T2.onlbal02,"
                + "T1.onlbal03 = T2.onlbal03,"
                + "T1.onlbal04 = T2.onlbal04,"
                + "T1.onlbal05 = T2.onlbal05,"
                + "T1.onlbal06 = T2.onlbal06,"
                + "T1.onlbal07 = T2.onlbal07,"
                + "T1.onlbal08 = T2.onlbal08,"
                + "T1.onlbal09 = T2.onlbal09,"
                + "T1.onlbal10 = T2.onlbal10,"
                + "T1.onlbal11 = T2.onlbal11,"
                + "T1.onlbal12 = T2.onlbal12,"
                + "T1.onlbal13 = T2.onlbal13,"
                + "T1.onlbal14 = T2.onlbal14,"
                + "T1.onlbal15 = T2.onlbal15,"
                + "T1.onlbal16 = T2.onlbal16,"
                + "T1.onlbal17 = T2.onlbal17,"
                + "T1.onlbal18 = T2.onlbal18,"
                + "T1.onlbal19 = T2.onlbal19,"
                + "T1.onlbal20 = T2.onlbal20,"
                + "T1.onlbal21 = T2.onlbal21,"
                + "T1.onlbal22 = T2.onlbal22,"
                + "T1.onlbal23 = T2.onlbal23,"
                + "T1.onlbal24 = T2.onlbal24,"
                + "T1.onlbal25 = T2.onlbal25,"
                + "T1.onlbal26 = T2.onlbal26,"
                + "T1.onlbal27 = T2.onlbal27,"
                + "T1.onlbal28 = T2.onlbal28,"
                + "T1.onlbal29 = T2.onlbal29,"
                + "T1.onlbal30 = T2.onlbal30,"
                + "T1.onlbal31 = T2.onlbal31,"
                + "T1.quaraccum01 = T2.quaraccum01,"
                + "T1.quaraccum02 = T2.quaraccum02,"
                + "T1.quaraccum03 = T2.quaraccum03,"
                + "T1.quaraccum04 = T2.quaraccum04,"
                + "T1.quaraccum05 = T2.quaraccum05,"
                + "T1.quaraccum06 = T2.quaraccum06,"
                + "T1.quaraccum07 = T2.quaraccum07,"
                + "T1.quaraccum08 = T2.quaraccum08,"
                + "T1.quaraccum09 = T2.quaraccum09,"
                + "T1.quaraccum10 = T2.quaraccum10,"
                + "T1.quaraccum11 = T2.quaraccum11,"
                + "T1.quaraccum12 = T2.quaraccum12,"
                + "T1.quaraccum13 = T2.quaraccum13,"
                + "T1.quaraccum14 = T2.quaraccum14,"
                + "T1.quaraccum15 = T2.quaraccum15,"
                + "T1.quaraccum16 = T2.quaraccum16,"
                + "T1.quaraccum17 = T2.quaraccum17,"
                + "T1.quaraccum18 = T2.quaraccum18,"
                + "T1.quaraccum19 = T2.quaraccum19,"
                + "T1.quaraccum20 = T2.quaraccum20,"
                + "T1.quaraccum21 = T2.quaraccum21,"
                + "T1.quaraccum22 = T2.quaraccum22,"
                + "T1.quaraccum23 = T2.quaraccum23,"
                + "T1.quaraccum24 = T2.quaraccum24,"
                + "T1.quaraccum25 = T2.quaraccum25,"
                + "T1.quaraccum26 = T2.quaraccum26,"
                + "T1.quaraccum27 = T2.quaraccum27,"
                + "T1.quaraccum28 = T2.quaraccum28,"
                + "T1.quaraccum29 = T2.quaraccum29,"
                + "T1.quaraccum30 = T2.quaraccum30,"
                + "T1.quaraccum31 = T2.quaraccum31,"
                + "T1.monthlybal = T2.monthlybal,"
                + "T1.lastmonthbal = T2.lastmonthbal,"
                + "T1.lastquarbal2 = T2.lastquarbal2,"
                + "T1.lastyearbal = T2.lastyearbal,"
                + "T1.lastmonthaccum = T2.lastmonthaccum,"
                + "T1.lastmonthaccum2 = T2.lastmonthaccum2,"
                + "T1.lastquaraccum2 = T2.lastquaraccum2,"
                + "T1.lastyearaccum = T2.lastyearaccum,"
                + "T1.lastmonthquaraccum = T2.lastmonthquaraccum,"
                + "T1.lastquarquaraccum = T2.lastquarquaraccum,"
                + "T1.lastyearquaraccum = T2.lastyearquaraccum,"
                + "T1.lastyearmonthaccum = T2.lastyearmonthaccum,"
                + "T1.monthaccum = T2.monthaccum,"
                + "T1.quaraccum2 = T2.quaraccum2,"
                + "T1.yearaccum = T2.yearaccum,"
                + "T1.updatedate = T2.updatedate "
                + "WHEN NOT MATCHED THEN INSERT (acctday,cltnbr,proptype,ccynbr,onlbal01,onlbal02,onlbal03,onlbal04,onlbal05,onlbal06,onlbal07,onlbal08,onlbal09,onlbal10,onlbal11,onlbal12,onlbal13,onlbal14,onlbal15,onlbal16,onlbal17,onlbal18,onlbal19,onlbal20,onlbal21,onlbal22,onlbal23,onlbal24,onlbal25,onlbal26,onlbal27,onlbal28,onlbal29,onlbal30,onlbal31,quaraccum01,quaraccum02,quaraccum03,quaraccum04,quaraccum05,quaraccum06,quaraccum07,quaraccum08,quaraccum09,quaraccum10,quaraccum11,quaraccum12,quaraccum13,quaraccum14,quaraccum15,quaraccum16,quaraccum17,quaraccum18,quaraccum19,quaraccum20,quaraccum21,quaraccum22,quaraccum23,quaraccum24,quaraccum25,quaraccum26,quaraccum27,quaraccum28,quaraccum29,quaraccum30,quaraccum31,monthlybal,lastmonthbal,lastquarbal2,lastyearbal,lastmonthaccum,lastmonthaccum2,lastquaraccum2,lastyearaccum,lastmonthquaraccum,lastquarquaraccum,lastyearquaraccum,lastyearmonthaccum,monthaccum,quaraccum2,yearaccum,updatedate)"
                + "VALUES (T2.acctday,T2.cltnbr,T2.proptype,T2.ccynbr,T2.onlbal01,T2.onlbal02,T2.onlbal03,T2.onlbal04,T2.onlbal05,T2.onlbal06,T2.onlbal07,T2.onlbal08,T2.onlbal09,T2.onlbal10,T2.onlbal11,T2.onlbal12,T2.onlbal13,T2.onlbal14,T2.onlbal15,T2.onlbal16,T2.onlbal17,T2.onlbal18,T2.onlbal19,T2.onlbal20,T2.onlbal21,T2.onlbal22,T2.onlbal23,T2.onlbal24,T2.onlbal25,T2.onlbal26,T2.onlbal27,T2.onlbal28,T2.onlbal29,T2.onlbal30,T2.onlbal31,T2.quaraccum01,T2.quaraccum02,T2.quaraccum03,T2.quaraccum04,T2.quaraccum05,T2.quaraccum06,T2.quaraccum07,T2.quaraccum08,T2.quaraccum09,T2.quaraccum10,T2.quaraccum11,T2.quaraccum12,T2.quaraccum13,T2.quaraccum14,T2.quaraccum15,T2.quaraccum16,T2.quaraccum17,T2.quaraccum18,T2.quaraccum19,T2.quaraccum20,T2.quaraccum21,T2.quaraccum22,T2.quaraccum23,T2.quaraccum24,T2.quaraccum25,T2.quaraccum26,T2.quaraccum27,T2.quaraccum28,T2.quaraccum29,T2.quaraccum30,T2.quaraccum31,T2.monthlybal,T2.lastmonthbal,T2.lastquarbal2,T2.lastyearbal,T2.lastmonthaccum,T2.lastmonthaccum2,T2.lastquaraccum2,T2.lastyearaccum,T2.lastmonthquaraccum,T2.lastquarquaraccum,T2.lastyearquaraccum,T2.lastyearmonthaccum,T2.monthaccum,T2.quaraccum2,T2.yearaccum,T2.updatedate)";
        logger.info(sql);
        return super.update(sql, BaseUtils.map());
    }

    public List<Map<String, Object>> queryAsset(Map<String, Object> p) {
        String onlbal = p.get("onlbal").toString();
        String sql = Sql.create("select tb." + onlbal + ", tb.cltnbr from TPERPRP tb where 1=1")
                .append(and("tb.cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("tb.proptype = :proptype", hasKey(p, "proptype")))
                .append(and("tb." + onlbal + " = :" + onlbal, hasKey(p, onlbal)))
                .append(and("tb.acctday = :acctday", hasKey(p, "acctday")))
                .toString();
        return queryForList(sql, p);
    }

    public List<Map<String, Object>> queryAUM(Map<String, Object> p) {
        String onlbal = p.get("onlbal").toString();
        String sql = Sql.create("select sum(" + onlbal + ")AUM from TPERPRP tb where 1=1")
                .append(and("tb.cltnbr = :cltnbr"))
                .append(and("tb." + onlbal + " = :" + onlbal, hasKey(p, onlbal)))
                .append(and("tb.acctday = :acctday", hasKey(p, "acctday")))
                .append("and tb.proptype between 1 and 9 group by tb.cltnbr")
                .toString();
        return queryForList(sql, p);
    }
}
