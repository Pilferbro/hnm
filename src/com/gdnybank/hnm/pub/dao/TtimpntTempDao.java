package com.gdnybank.hnm.pub.dao;

import cn.hutool.core.util.ObjectUtil;
import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:TTIMPNT_TEMP
 * 主键:
 * acctdate
 * acctseq
 * accttype
 * cltnbr
 * itemcode
 * richnbr
 **/
@Repository
public class TtimpntTempDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"cltnbr","richnbr","acctseq","accttype","brnnbr","tlrnbr","itemcode","ccynbr","incode","intrate","intrate2","startamt","intchgmode","lastchgdate","intchgcnt","onlbal01","onlbal02","onlbal03","onlbal04","onlbal05","onlbal06","onlbal07","onlbal08","onlbal09","onlbal10","onlbal11","onlbal12","onlbal13","onlbal14","onlbal15","onlbal16","onlbal17","onlbal18","onlbal19","onlbal20","onlbal21","onlbal22","onlbal23","onlbal24","onlbal25","onlbal26","onlbal27","onlbal28","onlbal29","onlbal30","onlbal31","lastmonthbal","lastyearbal","monthdays","yeardays","monthaccum","lastmonthaccum","lastyearaccum","lastmonthdays","lastyeardays","yearaccum","acctdate","updatedate","rcdver","rcdstatus"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"acctdate","acctseq","accttype","cltnbr","itemcode","richnbr"};

	@Override
	public int save(Map<String,Object> p) {
				//p.put("acctdate",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "acctdate"));
				//p.put("acctseq",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "acctseq"));
				//p.put("accttype",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "accttype"));
				//p.put("cltnbr",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "cltnbr"));
				//p.put("itemcode",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "itemcode"));
				//p.put("richnbr",sequenceService.getTableFlowNo("TTIMPNT_TEMP", "richnbr"));
		String sql=Sql.create("insert into TTIMPNT_TEMP (")
				.append(field("acctdate "))
				.append(field("acctseq "))
				.append(field("accttype "))
				.append(field("cltnbr "))
				.append(field("itemcode "))
				.append(field("richnbr "))
				.append(field("brnnbr ",hasKey(p,"brnnbr")))
				.append(field("tlrnbr ",hasKey(p,"tlrnbr")))
				.append(field("ccynbr ",hasKey(p,"ccynbr")))
				.append(field("incode ",hasKey(p,"incode")))
				.append(field("intrate ",hasKey(p,"intrate")))
				.append(field("intrate2 ",hasKey(p,"intrate2")))
				.append(field("startamt ",hasKey(p,"startamt")))
				.append(field("intchgmode ",hasKey(p,"intchgmode")))
				.append(field("lastchgdate ",hasKey(p,"lastchgdate")))
				.append(field("intchgcnt ",hasKey(p,"intchgcnt")))
				.append(field("onlbal01 ",hasKey(p,"onlbal01")))
				.append(field("onlbal02 ",hasKey(p,"onlbal02")))
				.append(field("onlbal03 ",hasKey(p,"onlbal03")))
				.append(field("onlbal04 ",hasKey(p,"onlbal04")))
				.append(field("onlbal05 ",hasKey(p,"onlbal05")))
				.append(field("onlbal06 ",hasKey(p,"onlbal06")))
				.append(field("onlbal07 ",hasKey(p,"onlbal07")))
				.append(field("onlbal08 ",hasKey(p,"onlbal08")))
				.append(field("onlbal09 ",hasKey(p,"onlbal09")))
				.append(field("onlbal10 ",hasKey(p,"onlbal10")))
				.append(field("onlbal11 ",hasKey(p,"onlbal11")))
				.append(field("onlbal12 ",hasKey(p,"onlbal12")))
				.append(field("onlbal13 ",hasKey(p,"onlbal13")))
				.append(field("onlbal14 ",hasKey(p,"onlbal14")))
				.append(field("onlbal15 ",hasKey(p,"onlbal15")))
				.append(field("onlbal16 ",hasKey(p,"onlbal16")))
				.append(field("onlbal17 ",hasKey(p,"onlbal17")))
				.append(field("onlbal18 ",hasKey(p,"onlbal18")))
				.append(field("onlbal19 ",hasKey(p,"onlbal19")))
				.append(field("onlbal20 ",hasKey(p,"onlbal20")))
				.append(field("onlbal21 ",hasKey(p,"onlbal21")))
				.append(field("onlbal22 ",hasKey(p,"onlbal22")))
				.append(field("onlbal23 ",hasKey(p,"onlbal23")))
				.append(field("onlbal24 ",hasKey(p,"onlbal24")))
				.append(field("onlbal25 ",hasKey(p,"onlbal25")))
				.append(field("onlbal26 ",hasKey(p,"onlbal26")))
				.append(field("onlbal27 ",hasKey(p,"onlbal27")))
				.append(field("onlbal28 ",hasKey(p,"onlbal28")))
				.append(field("onlbal29 ",hasKey(p,"onlbal29")))
				.append(field("onlbal30 ",hasKey(p,"onlbal30")))
				.append(field("onlbal31 ",hasKey(p,"onlbal31")))
				.append(field("lastmonthbal ",hasKey(p,"lastmonthbal")))
				.append(field("lastyearbal ",hasKey(p,"lastyearbal")))
				.append(field("monthdays ",hasKey(p,"monthdays")))
				.append(field("yeardays ",hasKey(p,"yeardays")))
				.append(field("monthaccum ",hasKey(p,"monthaccum")))
				.append(field("lastmonthaccum ",hasKey(p,"lastmonthaccum")))
				.append(field("lastyearaccum ",hasKey(p,"lastyearaccum")))
				.append(field("lastmonthdays ",hasKey(p,"lastmonthdays")))
				.append(field("lastyeardays ",hasKey(p,"lastyeardays")))
				.append(field("yearaccum ",hasKey(p,"yearaccum")))
				.append(field("updatedate ",hasKey(p,"updatedate")))
				.append(field("rcdver ",hasKey(p,"rcdver")))
				.append(field("rcdstatus ",hasKey(p,"rcdstatus")))
				.append(") values (")
				.append(field(":acctdate "))
				.append(field(":acctseq "))
				.append(field(":accttype "))
				.append(field(":cltnbr "))
				.append(field(":itemcode "))
				.append(field(":richnbr "))
				.append(field(":brnnbr ",hasKey(p,"brnnbr")))
				.append(field(":tlrnbr ",hasKey(p,"tlrnbr")))
				.append(field(":ccynbr ",hasKey(p,"ccynbr")))
				.append(field(":incode ",hasKey(p,"incode")))
				.append(field(":intrate ",hasKey(p,"intrate")))
				.append(field(":intrate2 ",hasKey(p,"intrate2")))
				.append(field(":startamt ",hasKey(p,"startamt")))
				.append(field(":intchgmode ",hasKey(p,"intchgmode")))
				.append(field(":lastchgdate ",hasKey(p,"lastchgdate")))
				.append(field(":intchgcnt ",hasKey(p,"intchgcnt")))
				.append(field(":onlbal01 ",hasKey(p,"onlbal01")))
				.append(field(":onlbal02 ",hasKey(p,"onlbal02")))
				.append(field(":onlbal03 ",hasKey(p,"onlbal03")))
				.append(field(":onlbal04 ",hasKey(p,"onlbal04")))
				.append(field(":onlbal05 ",hasKey(p,"onlbal05")))
				.append(field(":onlbal06 ",hasKey(p,"onlbal06")))
				.append(field(":onlbal07 ",hasKey(p,"onlbal07")))
				.append(field(":onlbal08 ",hasKey(p,"onlbal08")))
				.append(field(":onlbal09 ",hasKey(p,"onlbal09")))
				.append(field(":onlbal10 ",hasKey(p,"onlbal10")))
				.append(field(":onlbal11 ",hasKey(p,"onlbal11")))
				.append(field(":onlbal12 ",hasKey(p,"onlbal12")))
				.append(field(":onlbal13 ",hasKey(p,"onlbal13")))
				.append(field(":onlbal14 ",hasKey(p,"onlbal14")))
				.append(field(":onlbal15 ",hasKey(p,"onlbal15")))
				.append(field(":onlbal16 ",hasKey(p,"onlbal16")))
				.append(field(":onlbal17 ",hasKey(p,"onlbal17")))
				.append(field(":onlbal18 ",hasKey(p,"onlbal18")))
				.append(field(":onlbal19 ",hasKey(p,"onlbal19")))
				.append(field(":onlbal20 ",hasKey(p,"onlbal20")))
				.append(field(":onlbal21 ",hasKey(p,"onlbal21")))
				.append(field(":onlbal22 ",hasKey(p,"onlbal22")))
				.append(field(":onlbal23 ",hasKey(p,"onlbal23")))
				.append(field(":onlbal24 ",hasKey(p,"onlbal24")))
				.append(field(":onlbal25 ",hasKey(p,"onlbal25")))
				.append(field(":onlbal26 ",hasKey(p,"onlbal26")))
				.append(field(":onlbal27 ",hasKey(p,"onlbal27")))
				.append(field(":onlbal28 ",hasKey(p,"onlbal28")))
				.append(field(":onlbal29 ",hasKey(p,"onlbal29")))
				.append(field(":onlbal30 ",hasKey(p,"onlbal30")))
				.append(field(":onlbal31 ",hasKey(p,"onlbal31")))
				.append(field(":lastmonthbal ",hasKey(p,"lastmonthbal")))
				.append(field(":lastyearbal ",hasKey(p,"lastyearbal")))
				.append(field(":monthdays ",hasKey(p,"monthdays")))
				.append(field(":yeardays ",hasKey(p,"yeardays")))
				.append(field(":monthaccum ",hasKey(p,"monthaccum")))
				.append(field(":lastmonthaccum ",hasKey(p,"lastmonthaccum")))
				.append(field(":lastyearaccum ",hasKey(p,"lastyearaccum")))
				.append(field(":lastmonthdays ",hasKey(p,"lastmonthdays")))
				.append(field(":lastyeardays ",hasKey(p,"lastyeardays")))
				.append(field(":yearaccum ",hasKey(p,"yearaccum")))
				.append(field(":updatedate ",hasKey(p,"updatedate")))
				.append(field(":rcdver ",hasKey(p,"rcdver")))
				.append(field(":rcdstatus ",hasKey(p,"rcdstatus")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}
	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String,Object> p) {
		String sql=Sql.create("delete from TTIMPNT_TEMP where 1=1 ")
				.append(and("acctdate = :acctdate"))
				.append(and("acctseq = :acctseq"))
				.append(and("accttype = :accttype"))
				.append(and("cltnbr = :cltnbr"))
				.append(and("itemcode = :itemcode"))
				.append(and("richnbr = :richnbr"))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	/***
	 * 删除一条或者多条数据,慎用此函数.
	 * 此函数当传入的条件为空的时候,有可能会导致整张数据表被删除,因此,在执行此函数前,请一定对传入的参数进行非空检验,以防数据被误删.
	 * 项目组务必对使用此函数的代码进行代码走查,防止漏洞发生,防止被攻击.
	 **/
	@Override
	public int delete(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("delete from TTIMPNT_TEMP where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(and("incode = :incode",hasKey(p,"incode")))
				.append(and("intrate = :intrate",hasKey(p,"intrate")))
				.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(and("startamt = :startamt",hasKey(p,"startamt")))
				.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update TTIMPNT_TEMP set ")
				.append(field("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(field("accttype = :accttype",hasKey(p,"accttype")))
				.append(field("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(field("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(field("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(field("incode = :incode",hasKey(p,"incode")))
				.append(field("intrate = :intrate",hasKey(p,"intrate")))
				.append(field("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(field("startamt = :startamt",hasKey(p,"startamt")))
				.append(field("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(field("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(field("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(field("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(field("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(field("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(field("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(field("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(field("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(field("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(field("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(field("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(field("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(field("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(field("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(field("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(field("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(field("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(field("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(field("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(field("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(field("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(field("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(field("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(field("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(field("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(field("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(field("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(field("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(field("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(field("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(field("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(field("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(field("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(field("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(field("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(field("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(field("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(field("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(field("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(field("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(field("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(field("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(field("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(field("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("acctdate = :acctdate"))
				.append(and("acctseq = :acctseq"))
				.append(and("accttype = :accttype"))
				.append(and("cltnbr = :cltnbr"))
				.append(and("itemcode = :itemcode"))
				.append(and("richnbr = :richnbr"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update TTIMPNT_TEMP set ")
				.append(field("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(field("accttype = :accttype",hasKey(p,"accttype")))
				.append(field("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(field("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(field("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(field("incode = :incode",hasKey(p,"incode")))
				.append(field("intrate = :intrate",hasKey(p,"intrate")))
				.append(field("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(field("startamt = :startamt",hasKey(p,"startamt")))
				.append(field("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(field("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(field("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(field("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(field("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(field("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(field("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(field("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(field("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(field("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(field("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(field("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(field("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(field("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(field("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(field("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(field("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(field("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(field("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(field("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(field("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(field("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(field("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(field("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(field("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(field("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(field("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(field("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(field("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(field("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(field("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(field("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(field("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(field("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(field("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(field("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(field("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(field("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(field("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(field("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(field("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(field("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(field("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(field("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(field("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(field("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(field("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(" where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(and("incode = :incode",hasKey(p,"incode")))
				.append(and("intrate = :intrate",hasKey(p,"intrate")))
				.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(and("startamt = :startamt",hasKey(p,"startamt")))
				.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String,Object> p) {

		return 0;
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select * from TTIMPNT_TEMP where 1=1")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(and("incode = :incode",hasKey(p,"incode")))
				.append(and("intrate = :intrate",hasKey(p,"intrate")))
				.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(and("startamt = :startamt",hasKey(p,"startamt")))
				.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from TTIMPNT_TEMP where 1=1")
			.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
						.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
						.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
						.append(and("accttype = :accttype",hasKey(p,"accttype")))
						.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
						.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
						.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
						.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
						.append(and("incode = :incode",hasKey(p,"incode")))
						.append(and("intrate = :intrate",hasKey(p,"intrate")))
						.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
						.append(and("startamt = :startamt",hasKey(p,"startamt")))
						.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
						.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
						.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
						.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
						.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
						.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
						.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
						.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
						.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
						.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
						.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
						.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
						.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
						.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
						.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
						.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
						.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
						.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
						.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
						.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
						.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
						.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
						.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
						.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
						.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
						.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
						.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
						.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
						.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
						.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
						.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
						.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
						.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
						.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
						.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
						.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
						.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
						.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
						.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
						.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
						.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
						.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
						.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
						.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
						.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
						.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
						.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
						.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.append(orderBySql()).toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(orderBySql()).append(pageSql());
			sqlStr=sql.toString();
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}
	}

	/**
	 * 查询一条唯一记录,如果没有查到或者查询到两条以上记录会报异常,服务层应该扑捉此类异常进行特别处理.
	 * @see EmptyResultDataAccessException
	 * @see IncorrectResultSizeDataAccessException
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 ***/
	@Override
	public Map<String, Object> queryForMap(Map<String,Object> p) {
		String sql=Sql.create("select * from TTIMPNT_TEMP where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(and("incode = :incode",hasKey(p,"incode")))
				.append(and("intrate = :intrate",hasKey(p,"intrate")))
				.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(and("startamt = :startamt",hasKey(p,"startamt")))
				.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from TTIMPNT_TEMP where 1=1 ")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("brnnbr = :brnnbr",hasKey(p,"brnnbr")))
				.append(and("tlrnbr = :tlrnbr",hasKey(p,"tlrnbr")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("ccynbr = :ccynbr",hasKey(p,"ccynbr")))
				.append(and("incode = :incode",hasKey(p,"incode")))
				.append(and("intrate = :intrate",hasKey(p,"intrate")))
				.append(and("intrate2 = :intrate2",hasKey(p,"intrate2")))
				.append(and("startamt = :startamt",hasKey(p,"startamt")))
				.append(and("intchgmode = :intchgmode",hasKey(p,"intchgmode")))
				.append(and("lastchgdate = :lastchgdate",hasKey(p,"lastchgdate")))
				.append(and("intchgcnt = :intchgcnt",hasKey(p,"intchgcnt")))
				.append(and("onlbal01 = :onlbal01",hasKey(p,"onlbal01")))
				.append(and("onlbal02 = :onlbal02",hasKey(p,"onlbal02")))
				.append(and("onlbal03 = :onlbal03",hasKey(p,"onlbal03")))
				.append(and("onlbal04 = :onlbal04",hasKey(p,"onlbal04")))
				.append(and("onlbal05 = :onlbal05",hasKey(p,"onlbal05")))
				.append(and("onlbal06 = :onlbal06",hasKey(p,"onlbal06")))
				.append(and("onlbal07 = :onlbal07",hasKey(p,"onlbal07")))
				.append(and("onlbal08 = :onlbal08",hasKey(p,"onlbal08")))
				.append(and("onlbal09 = :onlbal09",hasKey(p,"onlbal09")))
				.append(and("onlbal10 = :onlbal10",hasKey(p,"onlbal10")))
				.append(and("onlbal11 = :onlbal11",hasKey(p,"onlbal11")))
				.append(and("onlbal12 = :onlbal12",hasKey(p,"onlbal12")))
				.append(and("onlbal13 = :onlbal13",hasKey(p,"onlbal13")))
				.append(and("onlbal14 = :onlbal14",hasKey(p,"onlbal14")))
				.append(and("onlbal15 = :onlbal15",hasKey(p,"onlbal15")))
				.append(and("onlbal16 = :onlbal16",hasKey(p,"onlbal16")))
				.append(and("onlbal17 = :onlbal17",hasKey(p,"onlbal17")))
				.append(and("onlbal18 = :onlbal18",hasKey(p,"onlbal18")))
				.append(and("onlbal19 = :onlbal19",hasKey(p,"onlbal19")))
				.append(and("onlbal20 = :onlbal20",hasKey(p,"onlbal20")))
				.append(and("onlbal21 = :onlbal21",hasKey(p,"onlbal21")))
				.append(and("onlbal22 = :onlbal22",hasKey(p,"onlbal22")))
				.append(and("onlbal23 = :onlbal23",hasKey(p,"onlbal23")))
				.append(and("onlbal24 = :onlbal24",hasKey(p,"onlbal24")))
				.append(and("onlbal25 = :onlbal25",hasKey(p,"onlbal25")))
				.append(and("onlbal26 = :onlbal26",hasKey(p,"onlbal26")))
				.append(and("onlbal27 = :onlbal27",hasKey(p,"onlbal27")))
				.append(and("onlbal28 = :onlbal28",hasKey(p,"onlbal28")))
				.append(and("onlbal29 = :onlbal29",hasKey(p,"onlbal29")))
				.append(and("onlbal30 = :onlbal30",hasKey(p,"onlbal30")))
				.append(and("onlbal31 = :onlbal31",hasKey(p,"onlbal31")))
				.append(and("lastmonthbal = :lastmonthbal",hasKey(p,"lastmonthbal")))
				.append(and("lastyearbal = :lastyearbal",hasKey(p,"lastyearbal")))
				.append(and("monthdays = :monthdays",hasKey(p,"monthdays")))
				.append(and("yeardays = :yeardays",hasKey(p,"yeardays")))
				.append(and("monthaccum = :monthaccum",hasKey(p,"monthaccum")))
				.append(and("lastmonthaccum = :lastmonthaccum",hasKey(p,"lastmonthaccum")))
				.append(and("lastyearaccum = :lastyearaccum",hasKey(p,"lastyearaccum")))
				.append(and("lastmonthdays = :lastmonthdays",hasKey(p,"lastmonthdays")))
				.append(and("lastyeardays = :lastyeardays",hasKey(p,"lastyeardays")))
				.append(and("yearaccum = :yearaccum",hasKey(p,"yearaccum")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(and("updatedate = :updatedate",hasKey(p,"updatedate")))
				.append(and("rcdver = :rcdver",hasKey(p,"rcdver")))
				.append(and("rcdstatus = :rcdstatus",hasKey(p,"rcdstatus")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public List<Map<String, Object>> queryForListById(Map<String,Object> p) {
		String sql=Sql.create("select * from TTIMPNT_TEMP where 1=1")
				.append(and("cltnbr = :cltnbr",hasKey(p,"cltnbr")))
				.append(and("richnbr = :richnbr",hasKey(p,"richnbr")))
				.append(and("acctseq = :acctseq",hasKey(p,"acctseq")))
				.append(and("accttype = :accttype",hasKey(p,"accttype")))
				.append(and("itemcode = :itemcode",hasKey(p,"itemcode")))
				.append(and("acctdate = :acctdate",hasKey(p,"acctdate")))
				.append(orderBySql())
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}


	/**
	 * 一次性删除全表数据
	 * @author chenhao
	 */
	public int truncate(){
		return super.update("truncate table TTIMPNT_TEMP", BaseUtils.map());
	}
}
