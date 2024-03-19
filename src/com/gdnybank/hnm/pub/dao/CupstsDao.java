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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.pub.dao.TXBaseDao;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:CUPSTS
 * 主键:
 **/
@Repository
public class CupstsDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"bkno", "tsbr", "tsdt", "tsrf", "prid", "stat", "f000", "f002", "f003", "f004", "f005", "f006", "f007", "f009", "f010", "f011", "f012", "f013", "f014", "f015", "f016", "f018", "f019", "f022", "f023", "f025", "f026", "f028", "f032", "f033", "f035", "f036", "f037", "f038", "f039", "f041", "f042", "f043", "f044", "f045", "f046", "f047", "f048", "f049", "f050", "f051", "f052", "f053", "f054", "f055", "f057", "f058", "f059", "f060", "f061", "f062", "f063", "f064", "tsus", "mask_to_v2", "mask_from_v2", "site_no"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into CUPSTS (")
                .append(field("bkno ", hasKey(p, "bkno")))
                .append(field("tsbr ", hasKey(p, "tsbr")))
                .append(field("tsdt ", hasKey(p, "tsdt")))
                .append(field("tsrf ", hasKey(p, "tsrf")))
                .append(field("prid ", hasKey(p, "prid")))
                .append(field("stat ", hasKey(p, "stat")))
                .append(field("f000 ", hasKey(p, "f000")))
                .append(field("f002 ", hasKey(p, "f002")))
                .append(field("f003 ", hasKey(p, "f003")))
                .append(field("f004 ", hasKey(p, "f004")))
                .append(field("f005 ", hasKey(p, "f005")))
                .append(field("f006 ", hasKey(p, "f006")))
                .append(field("f007 ", hasKey(p, "f007")))
                .append(field("f009 ", hasKey(p, "f009")))
                .append(field("f010 ", hasKey(p, "f010")))
                .append(field("f011 ", hasKey(p, "f011")))
                .append(field("f012 ", hasKey(p, "f012")))
                .append(field("f013 ", hasKey(p, "f013")))
                .append(field("f014 ", hasKey(p, "f014")))
                .append(field("f015 ", hasKey(p, "f015")))
                .append(field("f016 ", hasKey(p, "f016")))
                .append(field("f018 ", hasKey(p, "f018")))
                .append(field("f019 ", hasKey(p, "f019")))
                .append(field("f022 ", hasKey(p, "f022")))
                .append(field("f023 ", hasKey(p, "f023")))
                .append(field("f025 ", hasKey(p, "f025")))
                .append(field("f026 ", hasKey(p, "f026")))
                .append(field("f028 ", hasKey(p, "f028")))
                .append(field("f032 ", hasKey(p, "f032")))
                .append(field("f033 ", hasKey(p, "f033")))
                .append(field("f035 ", hasKey(p, "f035")))
                .append(field("f036 ", hasKey(p, "f036")))
                .append(field("f037 ", hasKey(p, "f037")))
                .append(field("f038 ", hasKey(p, "f038")))
                .append(field("f039 ", hasKey(p, "f039")))
                .append(field("f041 ", hasKey(p, "f041")))
                .append(field("f042 ", hasKey(p, "f042")))
                .append(field("f043 ", hasKey(p, "f043")))
                .append(field("f044 ", hasKey(p, "f044")))
                .append(field("f045 ", hasKey(p, "f045")))
                .append(field("f046 ", hasKey(p, "f046")))
                .append(field("f047 ", hasKey(p, "f047")))
                .append(field("f048 ", hasKey(p, "f048")))
                .append(field("f049 ", hasKey(p, "f049")))
                .append(field("f050 ", hasKey(p, "f050")))
                .append(field("f051 ", hasKey(p, "f051")))
                .append(field("f052 ", hasKey(p, "f052")))
                .append(field("f053 ", hasKey(p, "f053")))
                .append(field("f054 ", hasKey(p, "f054")))
                .append(field("f055 ", hasKey(p, "f055")))
                .append(field("f057 ", hasKey(p, "f057")))
                .append(field("f058 ", hasKey(p, "f058")))
                .append(field("f059 ", hasKey(p, "f059")))
                .append(field("f060 ", hasKey(p, "f060")))
                .append(field("f061 ", hasKey(p, "f061")))
                .append(field("f062 ", hasKey(p, "f062")))
                .append(field("f063 ", hasKey(p, "f063")))
                .append(field("f064 ", hasKey(p, "f064")))
                .append(field("tsus ", hasKey(p, "tsus")))
                .append(field("mask_to_v2 ", hasKey(p, "mask_to_v2")))
                .append(field("mask_from_v2 ", hasKey(p, "mask_from_v2")))
                .append(field("site_no ", hasKey(p, "site_no")))
                .append(") values (")
                .append(field(":bkno ", hasKey(p, "bkno")))
                .append(field(":tsbr ", hasKey(p, "tsbr")))
                .append(field(":tsdt ", hasKey(p, "tsdt")))
                .append(field(":tsrf ", hasKey(p, "tsrf")))
                .append(field(":prid ", hasKey(p, "prid")))
                .append(field(":stat ", hasKey(p, "stat")))
                .append(field(":f000 ", hasKey(p, "f000")))
                .append(field(":f002 ", hasKey(p, "f002")))
                .append(field(":f003 ", hasKey(p, "f003")))
                .append(field(":f004 ", hasKey(p, "f004")))
                .append(field(":f005 ", hasKey(p, "f005")))
                .append(field(":f006 ", hasKey(p, "f006")))
                .append(field(":f007 ", hasKey(p, "f007")))
                .append(field(":f009 ", hasKey(p, "f009")))
                .append(field(":f010 ", hasKey(p, "f010")))
                .append(field(":f011 ", hasKey(p, "f011")))
                .append(field(":f012 ", hasKey(p, "f012")))
                .append(field(":f013 ", hasKey(p, "f013")))
                .append(field(":f014 ", hasKey(p, "f014")))
                .append(field(":f015 ", hasKey(p, "f015")))
                .append(field(":f016 ", hasKey(p, "f016")))
                .append(field(":f018 ", hasKey(p, "f018")))
                .append(field(":f019 ", hasKey(p, "f019")))
                .append(field(":f022 ", hasKey(p, "f022")))
                .append(field(":f023 ", hasKey(p, "f023")))
                .append(field(":f025 ", hasKey(p, "f025")))
                .append(field(":f026 ", hasKey(p, "f026")))
                .append(field(":f028 ", hasKey(p, "f028")))
                .append(field(":f032 ", hasKey(p, "f032")))
                .append(field(":f033 ", hasKey(p, "f033")))
                .append(field(":f035 ", hasKey(p, "f035")))
                .append(field(":f036 ", hasKey(p, "f036")))
                .append(field(":f037 ", hasKey(p, "f037")))
                .append(field(":f038 ", hasKey(p, "f038")))
                .append(field(":f039 ", hasKey(p, "f039")))
                .append(field(":f041 ", hasKey(p, "f041")))
                .append(field(":f042 ", hasKey(p, "f042")))
                .append(field(":f043 ", hasKey(p, "f043")))
                .append(field(":f044 ", hasKey(p, "f044")))
                .append(field(":f045 ", hasKey(p, "f045")))
                .append(field(":f046 ", hasKey(p, "f046")))
                .append(field(":f047 ", hasKey(p, "f047")))
                .append(field(":f048 ", hasKey(p, "f048")))
                .append(field(":f049 ", hasKey(p, "f049")))
                .append(field(":f050 ", hasKey(p, "f050")))
                .append(field(":f051 ", hasKey(p, "f051")))
                .append(field(":f052 ", hasKey(p, "f052")))
                .append(field(":f053 ", hasKey(p, "f053")))
                .append(field(":f054 ", hasKey(p, "f054")))
                .append(field(":f055 ", hasKey(p, "f055")))
                .append(field(":f057 ", hasKey(p, "f057")))
                .append(field(":f058 ", hasKey(p, "f058")))
                .append(field(":f059 ", hasKey(p, "f059")))
                .append(field(":f060 ", hasKey(p, "f060")))
                .append(field(":f061 ", hasKey(p, "f061")))
                .append(field(":f062 ", hasKey(p, "f062")))
                .append(field(":f063 ", hasKey(p, "f063")))
                .append(field(":f064 ", hasKey(p, "f064")))
                .append(field(":tsus ", hasKey(p, "tsus")))
                .append(field(":mask_to_v2 ", hasKey(p, "mask_to_v2")))
                .append(field(":mask_from_v2 ", hasKey(p, "mask_from_v2")))
                .append(field(":site_no ", hasKey(p, "site_no")))
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
        String sql = Sql.create("delete from CUPSTS where 1=1 ")
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
        String sql = Sql.create("delete from CUPSTS where 1=1 ")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update CUPSTS set ")
                .append(field("bkno = :bkno", hasKey(p, "bkno")))
                .append(field("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(field("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(field("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(field("prid = :prid", hasKey(p, "prid")))
                .append(field("stat = :stat", hasKey(p, "stat")))
                .append(field("f000 = :f000", hasKey(p, "f000")))
                .append(field("f002 = :f002", hasKey(p, "f002")))
                .append(field("f003 = :f003", hasKey(p, "f003")))
                .append(field("f004 = :f004", hasKey(p, "f004")))
                .append(field("f005 = :f005", hasKey(p, "f005")))
                .append(field("f006 = :f006", hasKey(p, "f006")))
                .append(field("f007 = :f007", hasKey(p, "f007")))
                .append(field("f009 = :f009", hasKey(p, "f009")))
                .append(field("f010 = :f010", hasKey(p, "f010")))
                .append(field("f011 = :f011", hasKey(p, "f011")))
                .append(field("f012 = :f012", hasKey(p, "f012")))
                .append(field("f013 = :f013", hasKey(p, "f013")))
                .append(field("f014 = :f014", hasKey(p, "f014")))
                .append(field("f015 = :f015", hasKey(p, "f015")))
                .append(field("f016 = :f016", hasKey(p, "f016")))
                .append(field("f018 = :f018", hasKey(p, "f018")))
                .append(field("f019 = :f019", hasKey(p, "f019")))
                .append(field("f022 = :f022", hasKey(p, "f022")))
                .append(field("f023 = :f023", hasKey(p, "f023")))
                .append(field("f025 = :f025", hasKey(p, "f025")))
                .append(field("f026 = :f026", hasKey(p, "f026")))
                .append(field("f028 = :f028", hasKey(p, "f028")))
                .append(field("f032 = :f032", hasKey(p, "f032")))
                .append(field("f033 = :f033", hasKey(p, "f033")))
                .append(field("f035 = :f035", hasKey(p, "f035")))
                .append(field("f036 = :f036", hasKey(p, "f036")))
                .append(field("f037 = :f037", hasKey(p, "f037")))
                .append(field("f038 = :f038", hasKey(p, "f038")))
                .append(field("f039 = :f039", hasKey(p, "f039")))
                .append(field("f041 = :f041", hasKey(p, "f041")))
                .append(field("f042 = :f042", hasKey(p, "f042")))
                .append(field("f043 = :f043", hasKey(p, "f043")))
                .append(field("f044 = :f044", hasKey(p, "f044")))
                .append(field("f045 = :f045", hasKey(p, "f045")))
                .append(field("f046 = :f046", hasKey(p, "f046")))
                .append(field("f047 = :f047", hasKey(p, "f047")))
                .append(field("f048 = :f048", hasKey(p, "f048")))
                .append(field("f049 = :f049", hasKey(p, "f049")))
                .append(field("f050 = :f050", hasKey(p, "f050")))
                .append(field("f051 = :f051", hasKey(p, "f051")))
                .append(field("f052 = :f052", hasKey(p, "f052")))
                .append(field("f053 = :f053", hasKey(p, "f053")))
                .append(field("f054 = :f054", hasKey(p, "f054")))
                .append(field("f055 = :f055", hasKey(p, "f055")))
                .append(field("f057 = :f057", hasKey(p, "f057")))
                .append(field("f058 = :f058", hasKey(p, "f058")))
                .append(field("f059 = :f059", hasKey(p, "f059")))
                .append(field("f060 = :f060", hasKey(p, "f060")))
                .append(field("f061 = :f061", hasKey(p, "f061")))
                .append(field("f062 = :f062", hasKey(p, "f062")))
                .append(field("f063 = :f063", hasKey(p, "f063")))
                .append(field("f064 = :f064", hasKey(p, "f064")))
                .append(field("tsus = :tsus", hasKey(p, "tsus")))
                .append(field("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(field("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(" where 1=1 ")
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update CUPSTS set ")
                .append(field("bkno = :bkno", hasKey(p, "bkno")))
                .append(field("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(field("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(field("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(field("prid = :prid", hasKey(p, "prid")))
                .append(field("stat = :stat", hasKey(p, "stat")))
                .append(field("f000 = :f000", hasKey(p, "f000")))
                .append(field("f002 = :f002", hasKey(p, "f002")))
                .append(field("f003 = :f003", hasKey(p, "f003")))
                .append(field("f004 = :f004", hasKey(p, "f004")))
                .append(field("f005 = :f005", hasKey(p, "f005")))
                .append(field("f006 = :f006", hasKey(p, "f006")))
                .append(field("f007 = :f007", hasKey(p, "f007")))
                .append(field("f009 = :f009", hasKey(p, "f009")))
                .append(field("f010 = :f010", hasKey(p, "f010")))
                .append(field("f011 = :f011", hasKey(p, "f011")))
                .append(field("f012 = :f012", hasKey(p, "f012")))
                .append(field("f013 = :f013", hasKey(p, "f013")))
                .append(field("f014 = :f014", hasKey(p, "f014")))
                .append(field("f015 = :f015", hasKey(p, "f015")))
                .append(field("f016 = :f016", hasKey(p, "f016")))
                .append(field("f018 = :f018", hasKey(p, "f018")))
                .append(field("f019 = :f019", hasKey(p, "f019")))
                .append(field("f022 = :f022", hasKey(p, "f022")))
                .append(field("f023 = :f023", hasKey(p, "f023")))
                .append(field("f025 = :f025", hasKey(p, "f025")))
                .append(field("f026 = :f026", hasKey(p, "f026")))
                .append(field("f028 = :f028", hasKey(p, "f028")))
                .append(field("f032 = :f032", hasKey(p, "f032")))
                .append(field("f033 = :f033", hasKey(p, "f033")))
                .append(field("f035 = :f035", hasKey(p, "f035")))
                .append(field("f036 = :f036", hasKey(p, "f036")))
                .append(field("f037 = :f037", hasKey(p, "f037")))
                .append(field("f038 = :f038", hasKey(p, "f038")))
                .append(field("f039 = :f039", hasKey(p, "f039")))
                .append(field("f041 = :f041", hasKey(p, "f041")))
                .append(field("f042 = :f042", hasKey(p, "f042")))
                .append(field("f043 = :f043", hasKey(p, "f043")))
                .append(field("f044 = :f044", hasKey(p, "f044")))
                .append(field("f045 = :f045", hasKey(p, "f045")))
                .append(field("f046 = :f046", hasKey(p, "f046")))
                .append(field("f047 = :f047", hasKey(p, "f047")))
                .append(field("f048 = :f048", hasKey(p, "f048")))
                .append(field("f049 = :f049", hasKey(p, "f049")))
                .append(field("f050 = :f050", hasKey(p, "f050")))
                .append(field("f051 = :f051", hasKey(p, "f051")))
                .append(field("f052 = :f052", hasKey(p, "f052")))
                .append(field("f053 = :f053", hasKey(p, "f053")))
                .append(field("f054 = :f054", hasKey(p, "f054")))
                .append(field("f055 = :f055", hasKey(p, "f055")))
                .append(field("f057 = :f057", hasKey(p, "f057")))
                .append(field("f058 = :f058", hasKey(p, "f058")))
                .append(field("f059 = :f059", hasKey(p, "f059")))
                .append(field("f060 = :f060", hasKey(p, "f060")))
                .append(field("f061 = :f061", hasKey(p, "f061")))
                .append(field("f062 = :f062", hasKey(p, "f062")))
                .append(field("f063 = :f063", hasKey(p, "f063")))
                .append(field("f064 = :f064", hasKey(p, "f064")))
                .append(field("tsus = :tsus", hasKey(p, "tsus")))
                .append(field("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(field("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(field("site_no = :site_no", hasKey(p, "site_no")))
                .append(" where 1=1 ")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
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
        String sql = Sql.create("select * from CUPSTS where 1=1")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from CUPSTS where 1=1")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")));

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
        String sql = Sql.create("select * from CUPSTS where 1=1 ")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from CUPSTS where 1=1 ")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> query(Map<String, Object> p) {
        Sql sql = Sql.create("select * from CUPSTS where 1=1")
                .append(and("bkno = :bkno", hasKey(p, "bkno")))
                .append(and("tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("prid = :prid", hasKey(p, "prid")))
                .append(and("stat = :stat", hasKey(p, "stat")))
                .append(and("f000 = :f000", hasKey(p, "f000")))
                .append(and("f002 = :f002", hasKey(p, "f002")))
                .append(and("f003 = :f003", hasKey(p, "f003")))
                .append(and("f004 = :f004", hasKey(p, "f004")))
                .append(and("f005 = :f005", hasKey(p, "f005")))
                .append(and("f006 = :f006", hasKey(p, "f006")))
                .append(and("f007 = :f007", hasKey(p, "f007")))
                .append(and("f009 = :f009", hasKey(p, "f009")))
                .append(and("f010 = :f010", hasKey(p, "f010")))
                .append(and("f011 = :f011", hasKey(p, "f011")))
                .append(and("f012 = :f012", hasKey(p, "f012")))
                .append(and("f013 = :f013", hasKey(p, "f013")))
                .append(and("f014 = :f014", hasKey(p, "f014")))
                .append(and("f015 = :f015", hasKey(p, "f015")))
                .append(and("f016 = :f016", hasKey(p, "f016")))
                .append(and("f018 = :f018", hasKey(p, "f018")))
                .append(and("f019 = :f019", hasKey(p, "f019")))
                .append(and("f022 = :f022", hasKey(p, "f022")))
                .append(and("f023 = :f023", hasKey(p, "f023")))
                .append(and("f025 = :f025", hasKey(p, "f025")))
                .append(and("f026 = :f026", hasKey(p, "f026")))
                .append(and("f028 = :f028", hasKey(p, "f028")))
                .append(and("f032 = :f032", hasKey(p, "f032")))
                .append(and("f033 = :f033", hasKey(p, "f033")))
                .append(and("f035 = :f035", hasKey(p, "f035")))
                .append(and("f036 = :f036", hasKey(p, "f036")))
                .append(and("f037 = :f037", hasKey(p, "f037")))
                .append(and("f038 = :f038", hasKey(p, "f038")))
                .append(and("f039 = :f039", hasKey(p, "f039")))
                .append(and("f041 = :f041", hasKey(p, "f041")))
                .append(and("f042 = :f042", hasKey(p, "f042")))
                .append(and("f043 = :f043", hasKey(p, "f043")))
                .append(and("f044 = :f044", hasKey(p, "f044")))
                .append(and("f045 = :f045", hasKey(p, "f045")))
                .append(and("f046 = :f046", hasKey(p, "f046")))
                .append(and("f047 = :f047", hasKey(p, "f047")))
                .append(and("f048 = :f048", hasKey(p, "f048")))
                .append(and("f049 = :f049", hasKey(p, "f049")))
                .append(and("f050 = :f050", hasKey(p, "f050")))
                .append(and("f051 = :f051", hasKey(p, "f051")))
                .append(and("f052 = :f052", hasKey(p, "f052")))
                .append(and("f053 = :f053", hasKey(p, "f053")))
                .append(and("f054 = :f054", hasKey(p, "f054")))
                .append(and("f055 = :f055", hasKey(p, "f055")))
                .append(and("f057 = :f057", hasKey(p, "f057")))
                .append(and("f058 = :f058", hasKey(p, "f058")))
                .append(and("f059 = :f059", hasKey(p, "f059")))
                .append(and("f060 = :f060", hasKey(p, "f060")))
                .append(and("f061 = :f061", hasKey(p, "f061")))
                .append(and("f062 = :f062", hasKey(p, "f062")))
                .append(and("f063 = :f063", hasKey(p, "f063")))
                .append(and("f064 = :f064", hasKey(p, "f064")))
                .append(and("tsus = :tsus", hasKey(p, "tsus")))
                .append(and("mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("site_no = :site_no", hasKey(p, "site_no")));
        if (ObjectUtil.isNotEmpty(p.get("siteIds"))) {
            sql.append(and("site_no in (" + p.get("siteIds") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        sql.append(orderBySql());
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryBycondition1(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t5.branch_name,t2.mgr_id,t4.name mgr_name from CUPSTS t1 " +
                "LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " left join SYS_ACCOUNT t4 on t2.mgr_id = t4.account_id " +
                " left join SYS_BRANCH t5 on t2.branch_id = t5.branch_id " +
                " left join T_VOUCHER t6 on t1.tsdt = t6.tsdt and t1.site_no = t6.site_no " +
                " where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (p.containsKey("allOrgids") && p.get("allOrgids") != null) {
            sql.append(and("(t2.branch_id in (" + p.get("allOrgids") + ")" + " or t4.branch_id in (" + p.get("allOrgids") + "))"));
        }
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("t4.branch_id in (" + p.get("orgids") + ")"));
        }
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t2.branch_id in (" + p.get("branchids") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        sql.append(and("t6.id is null "));
        sql.append(" group by t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t5.branch_name,t2.mgr_id,t4.name ");

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = pageSqlInOracle(sql.append(" order by t1.tsdt desc ").toString());
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        } else {
            long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sql.append(" order by t1.tsdt desc ").append(pageSql());
            sqlStr = sql.toString();
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        }
    }

    public List<Map<String, Object>> queryBycondition2(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,t6.creator,t6.gd_result,t7.name creator_name,t1.site_no,t2.site_name,t2.branch_id,t5.branch_name,t2.mgr_id,t4.name mgr_name from CUPSTS t1 LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " left join SYS_ACCOUNT t4 on t2.mgr_id = t4.account_id " +
                " left join SYS_BRANCH t5 on t2.branch_id = t5.branch_id " +
                " left join T_VOUCHER t6 on t1.tsdt = t6.tsdt and t1.site_no = t6.site_no " +
                " left join SYS_ACCOUNT t7 on t6.creator = t7.account_id " +
                " where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (p.containsKey("allOrgids") && p.get("allOrgids") != null) {
            sql.append(and("(t2.branch_id in (" + p.get("allOrgids") + ")" + " or t4.branch_id in (" + p.get("allOrgids") + "))"));
        }
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("t4.branch_id in (" + p.get("orgids") + ")"));
        }
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t2.branch_id in (" + p.get("branchids") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        sql.append(and("t6.id is not null "));
        sql.append(" group by t1.tsdt,t6.creator,t6.gd_result,t7.name,t1.site_no,t2.site_name,t2.branch_id,t5.branch_name,t2.mgr_id,t4.name ");

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = pageSqlInOracle(sql.append(" order by t1.tsdt desc ").toString());
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        } else {
            long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sql.append(" order by t1.tsdt desc ").append(pageSql());
            sqlStr = sql.toString();
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        }
    }

    public List<Map<String, Object>> queryForListBycondition1(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.f002,t1.f004,t1.site_no,t1.f011,t1.tsdt,t1.prid,t1.mask_from_v2,t2.site_name,t4.voucher_result,t4.file_ids from CUPSTS t1 " +
                "LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID" +
                " LEFT JOIN T_VOUCHER_FLOW t4 on t1.tsdt=t4.tsdt and t1.f011=t4.f011 and t1.prid=t4.prid and t1.site_no=t4.site_no where 1=1")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("t1.prid in (" + p.get("prids") + ")"));
        }
        sql.append(" order by t1.f011 desc ");
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryByconditionToInspection1(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t2.mgr_id from CUPSTS t1 LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " left join T_VOUCHER t6 on t1.tsdt = t6.tsdt and t1.site_no = t6.site_no " +
                " where 1=1 and t6.id is null ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        sql.append(" group by t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t2.mgr_id ");

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryByconditionToInspection2(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t2.mgr_id,t1.f062,t1.f002,t1.f004 from CUPSTS t1 LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                .append(and("t1.mask_from_v2 <= :end_time", hasKey(p, "end_time")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public long countByconditionToInspection(Map<String, Object> p) {
        Sql sql = Sql.create("select count(t1.tsdt) from CUPSTS t1 where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                .append(and("t1.mask_from_v2 <= :end_time", hasKey(p, "end_time")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")));

        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return count(sqlStr, p);
    }

    public List<Map<String, Object>> queryForExport1(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,t1.site_no,t2.site_name,t2.branch_id,t1.f002,t1.f004,t1.f011,t1.prid,t1.mask_from_v2," +
                "t5.branch_name,t2.mgr_id,t4.name mgr_name from CUPSTS t1 " +
                "LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " left join SYS_ACCOUNT t4 on t2.mgr_id = t4.account_id " +
                " left join SYS_BRANCH t5 on t2.branch_id = t5.branch_id " +
                " left join T_VOUCHER t6 on t1.tsdt = t6.tsdt and t1.site_no = t6.site_no " +
                " where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (p.containsKey("allOrgids") && p.get("allOrgids") != null) {
            sql.append(and("(t2.branch_id in (" + p.get("allOrgids") + ")" + " or t4.branch_id in (" + p.get("allOrgids") + "))"));
        }
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("t4.branch_id in (" + p.get("orgids") + ")"));
        }
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t2.branch_id in (" + p.get("branchids") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("prid in (" + p.get("prids") + ")"));
        }
        sql.append(and("t6.id is null "));

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryForExport2(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.tsdt,decode(t6.voucher_result,'0','登记正确'，'1','登记不正确')voucher_result," +
                "t7.name creator_name,t2.site_name,t1.f002,t1.f004,t2.site_no," +
                "t1.f011,t1.prid,t1.mask_from_v2,t5.branch_name from CUPSTS t1 " +
                "LEFT JOIN H_SITE t2 on t1.site_no = t2.site_no " +
                " LEFT JOIN T_APPROVAL_APPLY t3 on t2.id=t3.RELATION_ID " +
                " left join SYS_ACCOUNT t4 on t2.mgr_id = t4.account_id " +
                " LEFT JOIN T_VOUCHER_FLOW t6 on t1.tsdt=t6.tsdt and t1.f011=t6.f011 and t1.prid=t6.prid and t1.site_no=t6.site_no " +
                " left join SYS_BRANCH t5 on t2.branch_id = t5.branch_id " +
                " left join SYS_ACCOUNT t7 on t6.creator = t7.account_id " +
                " where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t2.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t2.mgr_id = :account_id", hasKey(p, "account_id")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status  in (" + p.get("approval_status") + ")", hasKey(p, "approval_status")));

        if (p.containsKey("allOrgids") && p.get("allOrgids") != null) {
            sql.append(and("(t2.branch_id in (" + p.get("allOrgids") + ")" + " or t4.branch_id in (" + p.get("allOrgids") + "))"));
        }
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("t4.branch_id in (" + p.get("orgids") + ")"));
        }
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t2.branch_id in (" + p.get("branchids") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("prids"))) {
            sql.append(and("t1.prid in (" + p.get("prids") + ")"));
        }
        sql.append(and("t6.id is not null "));

        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryToMergerData(Map<String,Object> p) {
        Sql sql = Sql.create("select t1.f011 flow_num,t1.tsdt trans_dt,t1.mask_from_v2 trans_tm," +
                "t1.f004,t1.tsus terminal_id,t1.site_no,t1.f002 acct_num,t1.prid tran_type," +
                "substr(b.maintbrn, 3)open_bank,b.cltname cust_name from CUPSTS t1 " +
                "left join thfacct a on a.cardno = t1.f002 " +
                "left join tclient b on a.cltnbr = b.cltnbr " +
                "where 1=1 ")
                .append(and("t1.bkno = :bkno", hasKey(p, "bkno")))
                .append(and("t1.tsbr = :tsbr", hasKey(p, "tsbr")))
                .append(and("t1.tsdt = :tsdt", hasKey(p, "tsdt")))
                .append(and("t1.tsdt >= :start_date", hasKey(p, "start_date")))
                .append(and("t1.tsdt <= :end_date", hasKey(p, "end_date")))
                .append(and("t1.tsrf = :tsrf", hasKey(p, "tsrf")))
                .append(and("t1.prid = :prid", hasKey(p, "prid")))
                .append(and("t1.stat = :stat", hasKey(p, "stat")))
                .append(and("t1.f000 = :f000", hasKey(p, "f000")))
                .append(and("t1.f002 = :f002", hasKey(p, "f002")))
                .append(and("t1.f003 = :f003", hasKey(p, "f003")))
                .append(and("t1.f004 = :f004", hasKey(p, "f004")))
                .append(and("t1.f005 = :f005", hasKey(p, "f005")))
                .append(and("t1.f006 = :f006", hasKey(p, "f006")))
                .append(and("t1.f007 = :f007", hasKey(p, "f007")))
                .append(and("t1.f009 = :f009", hasKey(p, "f009")))
                .append(and("t1.f010 = :f010", hasKey(p, "f010")))
                .append(and("t1.f011 = :f011", hasKey(p, "f011")))
                .append(and("t1.f012 = :f012", hasKey(p, "f012")))
                .append(and("t1.f013 = :f013", hasKey(p, "f013")))
                .append(and("t1.f014 = :f014", hasKey(p, "f014")))
                .append(and("t1.f015 = :f015", hasKey(p, "f015")))
                .append(and("t1.f016 = :f016", hasKey(p, "f016")))
                .append(and("t1.f018 = :f018", hasKey(p, "f018")))
                .append(and("t1.f019 = :f019", hasKey(p, "f019")))
                .append(and("t1.f022 = :f022", hasKey(p, "f022")))
                .append(and("t1.f023 = :f023", hasKey(p, "f023")))
                .append(and("t1.f025 = :f025", hasKey(p, "f025")))
                .append(and("t1.f026 = :f026", hasKey(p, "f026")))
                .append(and("t1.f028 = :f028", hasKey(p, "f028")))
                .append(and("t1.f032 = :f032", hasKey(p, "f032")))
                .append(and("t1.f033 = :f033", hasKey(p, "f033")))
                .append(and("t1.f035 = :f035", hasKey(p, "f035")))
                .append(and("t1.f036 = :f036", hasKey(p, "f036")))
                .append(and("t1.f037 = :f037", hasKey(p, "f037")))
                .append(and("t1.f038 = :f038", hasKey(p, "f038")))
                .append(and("t1.f039 = :f039", hasKey(p, "f039")))
                .append(and("t1.f041 = :f041", hasKey(p, "f041")))
                .append(and("t1.f042 = :f042", hasKey(p, "f042")))
                .append(and("t1.f043 = :f043", hasKey(p, "f043")))
                .append(and("t1.f044 = :f044", hasKey(p, "f044")))
                .append(and("t1.f045 = :f045", hasKey(p, "f045")))
                .append(and("t1.f046 = :f046", hasKey(p, "f046")))
                .append(and("t1.f047 = :f047", hasKey(p, "f047")))
                .append(and("t1.f048 = :f048", hasKey(p, "f048")))
                .append(and("t1.f049 = :f049", hasKey(p, "f049")))
                .append(and("t1.f050 = :f050", hasKey(p, "f050")))
                .append(and("t1.f051 = :f051", hasKey(p, "f051")))
                .append(and("t1.f052 = :f052", hasKey(p, "f052")))
                .append(and("t1.f053 = :f053", hasKey(p, "f053")))
                .append(and("t1.f054 = :f054", hasKey(p, "f054")))
                .append(and("t1.f055 = :f055", hasKey(p, "f055")))
                .append(and("t1.f057 = :f057", hasKey(p, "f057")))
                .append(and("t1.f058 = :f058", hasKey(p, "f058")))
                .append(and("t1.f059 = :f059", hasKey(p, "f059")))
                .append(and("t1.f060 = :f060", hasKey(p, "f060")))
                .append(and("t1.f061 = :f061", hasKey(p, "f061")))
                .append(and("t1.f062 = :f062", hasKey(p, "f062")))
                .append(and("t1.f063 = :f063", hasKey(p, "f063")))
                .append(and("t1.f064 = :f064", hasKey(p, "f064")))
                .append(and("t1.tsus = :tsus", hasKey(p, "tsus")))
                .append(and("t1.mask_to_v2 = :mask_to_v2", hasKey(p, "mask_to_v2")))
                .append(and("t1.mask_from_v2 = :mask_from_v2", hasKey(p, "mask_from_v2")))
                .append(and("t1.mask_from_v2 >= :start_time", hasKey(p, "start_time")))
                .append(and("t1.mask_from_v2 <= :end_time", hasKey(p, "end_time")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")));
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }
}
