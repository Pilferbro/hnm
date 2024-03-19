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
 * 表名:TCLIENT
 * 主键:
 * cltnbr
 **/
@Repository
public class TclientDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"cltnbr", "cltname", "nameeng", "spellname", "othname", "datasource", "clttag", "citycode", "tlrclt", "tlrclt2", "trusttlr", "chrexpdate", "enteftdate", "entexpdate", "clttype", "chrstatus", "cltprop", "cltlevel", "applyway", "crdtag", "partnertag", "banktag", "evatag", "crtcrddate", "entrusttag", "creattag", "status", "relaship", "sharescope", "valuetype", "valuedata", "connfreq", "maintbbk", "maintbrn", "manbbk", "manbrn", "deputiden", "deputnbr", "salestype", "sales", "opendate", "opentime", "closedate", "regdate", "specialdate", "rlsseq", "contactseq", "remark", "operclt", "autclt", "lastviewdate", "mstcon", "reserv10", "reserv20", "reserv30", "changedate", "changetime", "reserv50", "rcdver", "rcdstatus"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"cltnbr"};

    @Override
    public int save(Map<String, Object> p) {
        p.put("cltnbr", sequenceService.getTableFlowNo("TCLIENT", "cltnbr"));
        String sql = Sql.create("insert into TCLIENT (")
                .append(field("cltnbr "))
                .append(field("cltname ", hasKey(p, "cltname")))
                .append(field("nameeng ", hasKey(p, "nameeng")))
                .append(field("spellname ", hasKey(p, "spellname")))
                .append(field("othname ", hasKey(p, "othname")))
                .append(field("datasource ", hasKey(p, "datasource")))
                .append(field("clttag ", hasKey(p, "clttag")))
                .append(field("citycode ", hasKey(p, "citycode")))
                .append(field("tlrclt ", hasKey(p, "tlrclt")))
                .append(field("tlrclt2 ", hasKey(p, "tlrclt2")))
                .append(field("trusttlr ", hasKey(p, "trusttlr")))
                .append(field("chrexpdate ", hasKey(p, "chrexpdate")))
                .append(field("enteftdate ", hasKey(p, "enteftdate")))
                .append(field("entexpdate ", hasKey(p, "entexpdate")))
                .append(field("clttype ", hasKey(p, "clttype")))
                .append(field("chrstatus ", hasKey(p, "chrstatus")))
                .append(field("cltprop ", hasKey(p, "cltprop")))
                .append(field("cltlevel ", hasKey(p, "cltlevel")))
                .append(field("applyway ", hasKey(p, "applyway")))
                .append(field("crdtag ", hasKey(p, "crdtag")))
                .append(field("partnertag ", hasKey(p, "partnertag")))
                .append(field("banktag ", hasKey(p, "banktag")))
                .append(field("evatag ", hasKey(p, "evatag")))
                .append(field("crtcrddate ", hasKey(p, "crtcrddate")))
                .append(field("entrusttag ", hasKey(p, "entrusttag")))
                .append(field("creattag ", hasKey(p, "creattag")))
                .append(field("status ", hasKey(p, "status")))
                .append(field("relaship ", hasKey(p, "relaship")))
                .append(field("sharescope ", hasKey(p, "sharescope")))
                .append(field("valuetype ", hasKey(p, "valuetype")))
                .append(field("valuedata ", hasKey(p, "valuedata")))
                .append(field("connfreq ", hasKey(p, "connfreq")))
                .append(field("maintbbk ", hasKey(p, "maintbbk")))
                .append(field("maintbrn ", hasKey(p, "maintbrn")))
                .append(field("manbbk ", hasKey(p, "manbbk")))
                .append(field("manbrn ", hasKey(p, "manbrn")))
                .append(field("deputiden ", hasKey(p, "deputiden")))
                .append(field("deputnbr ", hasKey(p, "deputnbr")))
                .append(field("salestype ", hasKey(p, "salestype")))
                .append(field("sales ", hasKey(p, "sales")))
                .append(field("opendate ", hasKey(p, "opendate")))
                .append(field("opentime ", hasKey(p, "opentime")))
                .append(field("closedate ", hasKey(p, "closedate")))
                .append(field("regdate ", hasKey(p, "regdate")))
                .append(field("specialdate ", hasKey(p, "specialdate")))
                .append(field("rlsseq ", hasKey(p, "rlsseq")))
                .append(field("contactseq ", hasKey(p, "contactseq")))
                .append(field("remark ", hasKey(p, "remark")))
                .append(field("operclt ", hasKey(p, "operclt")))
                .append(field("autclt ", hasKey(p, "autclt")))
                .append(field("lastviewdate ", hasKey(p, "lastviewdate")))
                .append(field("mstcon ", hasKey(p, "mstcon")))
                .append(field("reserv10 ", hasKey(p, "reserv10")))
                .append(field("reserv20 ", hasKey(p, "reserv20")))
                .append(field("reserv30 ", hasKey(p, "reserv30")))
                .append(field("changedate ", hasKey(p, "changedate")))
                .append(field("changetime ", hasKey(p, "changetime")))
                .append(field("reserv50 ", hasKey(p, "reserv50")))
                .append(field("rcdver ", hasKey(p, "rcdver")))
                .append(field("rcdstatus ", hasKey(p, "rcdstatus")))
                .append(") values (")
                .append(field(":cltnbr "))
                .append(field(":cltname ", hasKey(p, "cltname")))
                .append(field(":nameeng ", hasKey(p, "nameeng")))
                .append(field(":spellname ", hasKey(p, "spellname")))
                .append(field(":othname ", hasKey(p, "othname")))
                .append(field(":datasource ", hasKey(p, "datasource")))
                .append(field(":clttag ", hasKey(p, "clttag")))
                .append(field(":citycode ", hasKey(p, "citycode")))
                .append(field(":tlrclt ", hasKey(p, "tlrclt")))
                .append(field(":tlrclt2 ", hasKey(p, "tlrclt2")))
                .append(field(":trusttlr ", hasKey(p, "trusttlr")))
                .append(field(":chrexpdate ", hasKey(p, "chrexpdate")))
                .append(field(":enteftdate ", hasKey(p, "enteftdate")))
                .append(field(":entexpdate ", hasKey(p, "entexpdate")))
                .append(field(":clttype ", hasKey(p, "clttype")))
                .append(field(":chrstatus ", hasKey(p, "chrstatus")))
                .append(field(":cltprop ", hasKey(p, "cltprop")))
                .append(field(":cltlevel ", hasKey(p, "cltlevel")))
                .append(field(":applyway ", hasKey(p, "applyway")))
                .append(field(":crdtag ", hasKey(p, "crdtag")))
                .append(field(":partnertag ", hasKey(p, "partnertag")))
                .append(field(":banktag ", hasKey(p, "banktag")))
                .append(field(":evatag ", hasKey(p, "evatag")))
                .append(field(":crtcrddate ", hasKey(p, "crtcrddate")))
                .append(field(":entrusttag ", hasKey(p, "entrusttag")))
                .append(field(":creattag ", hasKey(p, "creattag")))
                .append(field(":status ", hasKey(p, "status")))
                .append(field(":relaship ", hasKey(p, "relaship")))
                .append(field(":sharescope ", hasKey(p, "sharescope")))
                .append(field(":valuetype ", hasKey(p, "valuetype")))
                .append(field(":valuedata ", hasKey(p, "valuedata")))
                .append(field(":connfreq ", hasKey(p, "connfreq")))
                .append(field(":maintbbk ", hasKey(p, "maintbbk")))
                .append(field(":maintbrn ", hasKey(p, "maintbrn")))
                .append(field(":manbbk ", hasKey(p, "manbbk")))
                .append(field(":manbrn ", hasKey(p, "manbrn")))
                .append(field(":deputiden ", hasKey(p, "deputiden")))
                .append(field(":deputnbr ", hasKey(p, "deputnbr")))
                .append(field(":salestype ", hasKey(p, "salestype")))
                .append(field(":sales ", hasKey(p, "sales")))
                .append(field(":opendate ", hasKey(p, "opendate")))
                .append(field(":opentime ", hasKey(p, "opentime")))
                .append(field(":closedate ", hasKey(p, "closedate")))
                .append(field(":regdate ", hasKey(p, "regdate")))
                .append(field(":specialdate ", hasKey(p, "specialdate")))
                .append(field(":rlsseq ", hasKey(p, "rlsseq")))
                .append(field(":contactseq ", hasKey(p, "contactseq")))
                .append(field(":remark ", hasKey(p, "remark")))
                .append(field(":operclt ", hasKey(p, "operclt")))
                .append(field(":autclt ", hasKey(p, "autclt")))
                .append(field(":lastviewdate ", hasKey(p, "lastviewdate")))
                .append(field(":mstcon ", hasKey(p, "mstcon")))
                .append(field(":reserv10 ", hasKey(p, "reserv10")))
                .append(field(":reserv20 ", hasKey(p, "reserv20")))
                .append(field(":reserv30 ", hasKey(p, "reserv30")))
                .append(field(":changedate ", hasKey(p, "changedate")))
                .append(field(":changetime ", hasKey(p, "changetime")))
                .append(field(":reserv50 ", hasKey(p, "reserv50")))
                .append(field(":rcdver ", hasKey(p, "rcdver")))
                .append(field(":rcdstatus ", hasKey(p, "rcdstatus")))
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
        String sql = Sql.create("delete from TCLIENT where 1=1 ")
                .append(and("cltnbr = :cltnbr"))
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
        String sql = Sql.create("delete from TCLIENT where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update TCLIENT set ")
                .append(field("cltname = :cltname", hasKey(p, "cltname")))
                .append(field("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(field("spellname = :spellname", hasKey(p, "spellname")))
                .append(field("othname = :othname", hasKey(p, "othname")))
                .append(field("datasource = :datasource", hasKey(p, "datasource")))
                .append(field("clttag = :clttag", hasKey(p, "clttag")))
                .append(field("citycode = :citycode", hasKey(p, "citycode")))
                .append(field("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(field("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(field("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(field("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(field("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(field("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(field("clttype = :clttype", hasKey(p, "clttype")))
                .append(field("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(field("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(field("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(field("applyway = :applyway", hasKey(p, "applyway")))
                .append(field("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(field("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(field("banktag = :banktag", hasKey(p, "banktag")))
                .append(field("evatag = :evatag", hasKey(p, "evatag")))
                .append(field("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(field("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(field("creattag = :creattag", hasKey(p, "creattag")))
                .append(field("status = :status", hasKey(p, "status")))
                .append(field("relaship = :relaship", hasKey(p, "relaship")))
                .append(field("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(field("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(field("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(field("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(field("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(field("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(field("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(field("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(field("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(field("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(field("salestype = :salestype", hasKey(p, "salestype")))
                .append(field("sales = :sales", hasKey(p, "sales")))
                .append(field("opendate = :opendate", hasKey(p, "opendate")))
                .append(field("opentime = :opentime", hasKey(p, "opentime")))
                .append(field("closedate = :closedate", hasKey(p, "closedate")))
                .append(field("regdate = :regdate", hasKey(p, "regdate")))
                .append(field("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(field("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(field("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(field("remark = :remark", hasKey(p, "remark")))
                .append(field("operclt = :operclt", hasKey(p, "operclt")))
                .append(field("autclt = :autclt", hasKey(p, "autclt")))
                .append(field("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(field("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(field("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(field("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(field("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(field("changedate = :changedate", hasKey(p, "changedate")))
                .append(field("changetime = :changetime", hasKey(p, "changetime")))
                .append(field("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(field("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(field("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .append(" where 1=1 ")
                .append(and("cltnbr = :cltnbr"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update TCLIENT set ")
                .append(field("cltname = :cltname", hasKey(p, "cltname")))
                .append(field("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(field("spellname = :spellname", hasKey(p, "spellname")))
                .append(field("othname = :othname", hasKey(p, "othname")))
                .append(field("datasource = :datasource", hasKey(p, "datasource")))
                .append(field("clttag = :clttag", hasKey(p, "clttag")))
                .append(field("citycode = :citycode", hasKey(p, "citycode")))
                .append(field("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(field("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(field("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(field("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(field("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(field("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(field("clttype = :clttype", hasKey(p, "clttype")))
                .append(field("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(field("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(field("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(field("applyway = :applyway", hasKey(p, "applyway")))
                .append(field("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(field("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(field("banktag = :banktag", hasKey(p, "banktag")))
                .append(field("evatag = :evatag", hasKey(p, "evatag")))
                .append(field("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(field("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(field("creattag = :creattag", hasKey(p, "creattag")))
                .append(field("status = :status", hasKey(p, "status")))
                .append(field("relaship = :relaship", hasKey(p, "relaship")))
                .append(field("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(field("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(field("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(field("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(field("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(field("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(field("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(field("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(field("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(field("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(field("salestype = :salestype", hasKey(p, "salestype")))
                .append(field("sales = :sales", hasKey(p, "sales")))
                .append(field("opendate = :opendate", hasKey(p, "opendate")))
                .append(field("opentime = :opentime", hasKey(p, "opentime")))
                .append(field("closedate = :closedate", hasKey(p, "closedate")))
                .append(field("regdate = :regdate", hasKey(p, "regdate")))
                .append(field("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(field("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(field("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(field("remark = :remark", hasKey(p, "remark")))
                .append(field("operclt = :operclt", hasKey(p, "operclt")))
                .append(field("autclt = :autclt", hasKey(p, "autclt")))
                .append(field("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(field("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(field("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(field("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(field("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(field("changedate = :changedate", hasKey(p, "changedate")))
                .append(field("changetime = :changetime", hasKey(p, "changetime")))
                .append(field("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(field("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(field("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .append(" where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
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
        String sql = Sql.create("select * from TCLIENT where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from TCLIENT where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")));

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
        String sql = Sql.create("select * from TCLIENT where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from TCLIENT where 1=1 ")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }

    public List<Map<String, Object>> query(Map<String, Object> p) {
        Sql sql = Sql.create("select * from TCLIENT where 1=1")
                .append(and("cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("cltname = :cltname", hasKey(p, "cltname")))
                .append(and("nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("spellname = :spellname", hasKey(p, "spellname")))
                .append(and("othname = :othname", hasKey(p, "othname")))
                .append(and("datasource = :datasource", hasKey(p, "datasource")))
                .append(and("clttag = :clttag", hasKey(p, "clttag")))
                .append(and("citycode = :citycode", hasKey(p, "citycode")))
                .append(and("tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("clttype = :clttype", hasKey(p, "clttype")))
                .append(and("chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("applyway = :applyway", hasKey(p, "applyway")))
                .append(and("crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("banktag = :banktag", hasKey(p, "banktag")))
                .append(and("evatag = :evatag", hasKey(p, "evatag")))
                .append(and("crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("creattag = :creattag", hasKey(p, "creattag")))
                .append(and("status = :status", hasKey(p, "status")))
                .append(and("relaship = :relaship", hasKey(p, "relaship")))
                .append(and("sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("salestype = :salestype", hasKey(p, "salestype")))
                .append(and("sales = :sales", hasKey(p, "sales")))
                .append(and("opendate = :opendate", hasKey(p, "opendate")))
                .append(and("opentime = :opentime", hasKey(p, "opentime")))
                .append(and("closedate = :closedate", hasKey(p, "closedate")))
                .append(and("regdate = :regdate", hasKey(p, "regdate")))
                .append(and("regdate <= :nowdate", hasKey(p, "nowdate")))
                .append(and("specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("remark = :remark", hasKey(p, "remark")))
                .append(and("operclt = :operclt", hasKey(p, "operclt")))
                .append(and("autclt = :autclt", hasKey(p, "autclt")))
                .append(and("lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("changedate = :changedate", hasKey(p, "changedate")))
                .append(and("changetime = :changetime", hasKey(p, "changetime")))
                .append(and("reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")));
        if (ObjectUtil.isNotEmpty(p.get("siteIds"))) {
            sql.append(and("tlrclt in (" + p.get("siteIds") + ")"));
        }
        if (ObjectUtil.isNotEmpty(p.get("cltname"))) {
            sql.append(and("cltname like '%" + p.get("cltname") + "%'"));
        }
        sql.append(orderBySql());
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    public List<Map<String, Object>> queryClients(Map<String, Object> p) {
        Sql sql = Sql.create("select distinct t1.*,t2.branch_name,nvl(c.currents,0) as currents,NVL(m.termly,0) as termly,NVL(a.AUM,0) as AUM from TCLIENT t1 " +
                "left join ( select "+p.get("onlbal") + " as currents, CLTNBR from TPERPRP where proptype = '1' and acctday = " + p.get("yyyyMM") + ") c on t1.CLTNBR = c.CLTNBR " +
                "LEFT JOIN ( SELECT  "+p.get("onlbal") + " AS termly, CLTNBR FROM TPERPRP WHERE PROPTYPE = '2' and acctday = " + p.get("yyyyMM") + ") m ON t1.CLTNBR = m.CLTNBR "+
                "LEFT JOIN ( SELECT CLTNBR, SUM( "+p.get("onlbal") + " ) AS AUM FROM TPERPRP WHERE 1 = 1 and acctday = " + p.get("yyyyMM") + " GROUP BY CLTNBR ) a ON a.CLTNBR = t1.CLTNBR "+
                "left join SYS_BRANCH t2 on t2.branch_id = substr(t1.maintbrn, 3, 4) " +
                "left join TPERPRP t3 on t1.cltnbr = t3.cltnbr ")

                .append(" where 1=1")
                .append(and("t1.cltnbr = :cltnbr", hasKey(p, "cltnbr")))
                .append(and("t1.nameeng = :nameeng", hasKey(p, "nameeng")))
                .append(and("t1.cltname = :cltname", hasKey(p, "cltname")))
                .append(and("t1.spellname = :spellname", hasKey(p, "spellname")))
                .append(and("t1.othname = :othname", hasKey(p, "othname")))
                .append(and("t1.datasource = :datasource", hasKey(p, "datasource")))
                .append(and("t1.clttag = :clttag", hasKey(p, "clttag")))
                .append(and("t1.citycode = :citycode", hasKey(p, "citycode")))
                .append(and("t1.tlrclt = :tlrclt", hasKey(p, "tlrclt")))
                .append(and("t1.tlrclt2 = :tlrclt2", hasKey(p, "tlrclt2")))
                .append(and("t1.trusttlr = :trusttlr", hasKey(p, "trusttlr")))
                .append(and("t1.chrexpdate = :chrexpdate", hasKey(p, "chrexpdate")))
                .append(and("t1.enteftdate = :enteftdate", hasKey(p, "enteftdate")))
                .append(and("t1.entexpdate = :entexpdate", hasKey(p, "entexpdate")))
                .append(and("t1.clttype = :clttype", hasKey(p, "clttype")))
                .append(and("t1.chrstatus = :chrstatus", hasKey(p, "chrstatus")))
                .append(and("t1.cltprop = :cltprop", hasKey(p, "cltprop")))
                .append(and("t1.cltlevel = :cltlevel", hasKey(p, "cltlevel")))
                .append(and("t1.applyway = :applyway", hasKey(p, "applyway")))
                .append(and("t1.crdtag = :crdtag", hasKey(p, "crdtag")))
                .append(and("t1.partnertag = :partnertag", hasKey(p, "partnertag")))
                .append(and("t1.banktag = :banktag", hasKey(p, "banktag")))
                .append(and("t1.evatag = :evatag", hasKey(p, "evatag")))
                .append(and("t1.crtcrddate = :crtcrddate", hasKey(p, "crtcrddate")))
                .append(and("t1.entrusttag = :entrusttag", hasKey(p, "entrusttag")))
                .append(and("t1.creattag = :creattag", hasKey(p, "creattag")))
                .append(and("t1.status = :status", hasKey(p, "status")))
                .append(and("t1.relaship = :relaship", hasKey(p, "relaship")))
                .append(and("t1.sharescope = :sharescope", hasKey(p, "sharescope")))
                .append(and("t1.valuetype = :valuetype", hasKey(p, "valuetype")))
                .append(and("t1.valuedata = :valuedata", hasKey(p, "valuedata")))
                .append(and("t1.connfreq = :connfreq", hasKey(p, "connfreq")))
                .append(and("t1.maintbbk = :maintbbk", hasKey(p, "maintbbk")))
                .append(and("t1.maintbrn = :maintbrn", hasKey(p, "maintbrn")))
                .append(and("t1.manbbk = :manbbk", hasKey(p, "manbbk")))
                .append(and("t1.manbrn = :manbrn", hasKey(p, "manbrn")))
                .append(and("t1.deputiden = :deputiden", hasKey(p, "deputiden")))
                .append(and("t1.deputnbr = :deputnbr", hasKey(p, "deputnbr")))
                .append(and("t1.salestype = :salestype", hasKey(p, "salestype")))
                .append(and("t1.sales = :sales", hasKey(p, "sales")))
                .append(and("t1.opendate = :opendate", hasKey(p, "opendate")))
                .append(and("t1.opentime = :opentime", hasKey(p, "opentime")))
                .append(and("t1.closedate = :closedate", hasKey(p, "closedate")))
                .append(and("t1.regdate = :regdate", hasKey(p, "regdate")))
                .append(and("t1.regdate <= :nowdate", hasKey(p, "nowdate")))
                .append(and("t1.specialdate = :specialdate", hasKey(p, "specialdate")))
                .append(and("t1.rlsseq = :rlsseq", hasKey(p, "rlsseq")))
                .append(and("t1.contactseq = :contactseq", hasKey(p, "contactseq")))
                .append(and("t1.remark = :remark", hasKey(p, "remark")))
                .append(and("t1.operclt = :operclt", hasKey(p, "operclt")))
                .append(and("t1.autclt = :autclt", hasKey(p, "autclt")))
                .append(and("t1.lastviewdate = :lastviewdate", hasKey(p, "lastviewdate")))
                .append(and("t1.mstcon = :mstcon", hasKey(p, "mstcon")))
                .append(and("t1.reserv10 = :reserv10", hasKey(p, "reserv10")))
                .append(and("t1.reserv20 = :reserv20", hasKey(p, "reserv20")))
                .append(and("t1.reserv30 = :reserv30", hasKey(p, "reserv30")))
                .append(and("t1.changedate = :changedate", hasKey(p, "changedate")))
                .append(and("t1.changetime = :changetime", hasKey(p, "changetime")))
                .append(and("t1.reserv50 = :reserv50", hasKey(p, "reserv50")))
                .append(and("t1.rcdver = :rcdver", hasKey(p, "rcdver")))
                .append(and("t1.rcdstatus = :rcdstatus", hasKey(p, "rcdstatus")))
                .append(and("t1.tlrclt = :tlrclt",hasKey(p,"tlrclt")))
                ;
        if (ObjectUtil.isNotEmpty(p.get("siteIds"))) {
            sql.append(and("t1.tlrclt in (" + p.get("siteIds") + ")"));
        }

        if (ObjectUtil.isNotEmpty(p.get("minTermly")) && ObjectUtil.isNotEmpty(p.get("maxTermly"))) {
            sql.append(and("termly between " + p.get("minTermly") + " and " + p.get("maxTermly")));
        }

        if ( ObjectUtil.isNotEmpty(p.get("minCurrent")) && ObjectUtil.isNotEmpty(p.get("maxCurrent"))) {
            sql.append(and("currents between " + p.get("minCurrent") + " and " + p.get("maxCurrent")));
        }

        if (ObjectUtil.isNotEmpty(p.get("minAUM")) && ObjectUtil.isNotEmpty(p.get("maxAUM"))) {
            sql.append(and("AUM between " + p.get("minAUM") + " and " + p.get("maxAUM")));
        }
        sql.append(orderBySql());
        String sqlStr = sql.toString();
        if (ObjectUtil.isEmpty(p.get("export"))) {
            //获取数据库类型
            String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
            if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
                long count = count("select count(*) from (" + sqlStr + ")  ", p);
                PageInfo pageInf = MfpContextHolder.getPageInfo();
                pageInf.setITotalDisplayRecords(count);
                MfpContextHolder.setPageInfo(pageInf);
                sqlStr = Sql.pageSqlInOracle(sql.append(" order by t1.opendate desc").toString());
                printSql(sqlStr, p);
                return queryForList(sqlStr, p);
            } else {
                long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
                PageInfo pageInf = MfpContextHolder.getPageInfo();
                pageInf.setITotalDisplayRecords(count);
                MfpContextHolder.setPageInfo(pageInf);
                sql.append(" order by t1.opendate desc").append(pageSql());
                sqlStr = sql.toString();
                printSql(sqlStr, p);
                return queryForList(sqlStr, p);
            }

        }
        return queryForList(sqlStr, p);
    }

    /**
     * 一次性删除全表数据
     *
     * @author chenhao
     */
    public int truncate() {
        return super.update("truncate table TCLIENT", BaseUtils.map());
    }

}
