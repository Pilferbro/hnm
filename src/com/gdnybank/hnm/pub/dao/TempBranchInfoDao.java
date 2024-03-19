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
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;

/**
 * 自动化工具生成数据库操作类
 * 表名:TEMP_BRANCH_INFO
 * 主键:
 **/
@Repository
public class TempBranchInfoDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"acct_class","bran_code","bran_name","city_code","oth_addr","post_code","bran_type","sub_grp_no","quota_grp_no","bran_status","last_trans_date","par_mana_flag","act_flag","ret_bran_code","count_bran_code","adm_bran_code","bran_level","set_bran_code","for_set_bran_code","card_bran_code","report_bran_code","country_draft","city_draft","draft_flag","swift_no","area_code_array","set_bank_no","set_clear_bank_no","ctrl_bit"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{};

	@Override
	public int save(Map<String,Object> p) {
		String sql=Sql.create("insert into TEMP_BRANCH_INFO (")
				.append(field("acct_class ",hasKey(p,"acct_class")))
				.append(field("bran_code ",hasKey(p,"bran_code")))
				.append(field("bran_name ",hasKey(p,"bran_name")))
				.append(field("city_code ",hasKey(p,"city_code")))
				.append(field("oth_addr ",hasKey(p,"oth_addr")))
				.append(field("post_code ",hasKey(p,"post_code")))
				.append(field("bran_type ",hasKey(p,"bran_type")))
				.append(field("sub_grp_no ",hasKey(p,"sub_grp_no")))
				.append(field("quota_grp_no ",hasKey(p,"quota_grp_no")))
				.append(field("bran_status ",hasKey(p,"bran_status")))
				.append(field("last_trans_date ",hasKey(p,"last_trans_date")))
				.append(field("par_mana_flag ",hasKey(p,"par_mana_flag")))
				.append(field("act_flag ",hasKey(p,"act_flag")))
				.append(field("ret_bran_code ",hasKey(p,"ret_bran_code")))
				.append(field("count_bran_code ",hasKey(p,"count_bran_code")))
				.append(field("adm_bran_code ",hasKey(p,"adm_bran_code")))
				.append(field("bran_level ",hasKey(p,"bran_level")))
				.append(field("set_bran_code ",hasKey(p,"set_bran_code")))
				.append(field("for_set_bran_code ",hasKey(p,"for_set_bran_code")))
				.append(field("card_bran_code ",hasKey(p,"card_bran_code")))
				.append(field("report_bran_code ",hasKey(p,"report_bran_code")))
				.append(field("country_draft ",hasKey(p,"country_draft")))
				.append(field("city_draft ",hasKey(p,"city_draft")))
				.append(field("draft_flag ",hasKey(p,"draft_flag")))
				.append(field("swift_no ",hasKey(p,"swift_no")))
				.append(field("area_code_array ",hasKey(p,"area_code_array")))
				.append(field("set_bank_no ",hasKey(p,"set_bank_no")))
				.append(field("set_clear_bank_no ",hasKey(p,"set_clear_bank_no")))
				.append(field("ctrl_bit ",hasKey(p,"ctrl_bit")))
				.append(") values (")
				.append(field(":acct_class ",hasKey(p,"acct_class")))
				.append(field(":bran_code ",hasKey(p,"bran_code")))
				.append(field(":bran_name ",hasKey(p,"bran_name")))
				.append(field(":city_code ",hasKey(p,"city_code")))
				.append(field(":oth_addr ",hasKey(p,"oth_addr")))
				.append(field(":post_code ",hasKey(p,"post_code")))
				.append(field(":bran_type ",hasKey(p,"bran_type")))
				.append(field(":sub_grp_no ",hasKey(p,"sub_grp_no")))
				.append(field(":quota_grp_no ",hasKey(p,"quota_grp_no")))
				.append(field(":bran_status ",hasKey(p,"bran_status")))
				.append(field(":last_trans_date ",hasKey(p,"last_trans_date")))
				.append(field(":par_mana_flag ",hasKey(p,"par_mana_flag")))
				.append(field(":act_flag ",hasKey(p,"act_flag")))
				.append(field(":ret_bran_code ",hasKey(p,"ret_bran_code")))
				.append(field(":count_bran_code ",hasKey(p,"count_bran_code")))
				.append(field(":adm_bran_code ",hasKey(p,"adm_bran_code")))
				.append(field(":bran_level ",hasKey(p,"bran_level")))
				.append(field(":set_bran_code ",hasKey(p,"set_bran_code")))
				.append(field(":for_set_bran_code ",hasKey(p,"for_set_bran_code")))
				.append(field(":card_bran_code ",hasKey(p,"card_bran_code")))
				.append(field(":report_bran_code ",hasKey(p,"report_bran_code")))
				.append(field(":country_draft ",hasKey(p,"country_draft")))
				.append(field(":city_draft ",hasKey(p,"city_draft")))
				.append(field(":draft_flag ",hasKey(p,"draft_flag")))
				.append(field(":swift_no ",hasKey(p,"swift_no")))
				.append(field(":area_code_array ",hasKey(p,"area_code_array")))
				.append(field(":set_bank_no ",hasKey(p,"set_bank_no")))
				.append(field(":set_clear_bank_no ",hasKey(p,"set_clear_bank_no")))
				.append(field(":ctrl_bit ",hasKey(p,"ctrl_bit")))
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
		String sql=Sql.create("delete from TEMP_BRANCH_INFO where 1=1 ")
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
		String sql=Sql.create("delete from TEMP_BRANCH_INFO where 1=1 ")
				.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(and("city_code = :city_code",hasKey(p,"city_code")))
				.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(and("post_code = :post_code",hasKey(p,"post_code")))
				.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update TEMP_BRANCH_INFO set ")
				.append(field("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(field("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(field("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(field("city_code = :city_code",hasKey(p,"city_code")))
				.append(field("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(field("post_code = :post_code",hasKey(p,"post_code")))
				.append(field("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(field("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(field("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(field("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(field("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(field("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(field("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(field("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(field("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(field("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(field("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(field("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(field("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(field("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(field("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(field("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(field("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(field("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(field("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(field("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(field("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(field("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(field("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.append(" where 1=1 ")
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update TEMP_BRANCH_INFO set ")
				.append(field("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(field("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(field("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(field("city_code = :city_code",hasKey(p,"city_code")))
				.append(field("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(field("post_code = :post_code",hasKey(p,"post_code")))
				.append(field("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(field("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(field("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(field("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(field("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(field("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(field("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(field("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(field("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(field("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(field("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(field("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(field("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(field("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(field("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(field("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(field("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(field("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(field("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(field("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(field("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(field("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(field("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.append(" where 1=1 ")
				.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(and("city_code = :city_code",hasKey(p,"city_code")))
				.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(and("post_code = :post_code",hasKey(p,"post_code")))
				.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
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
		String sql=Sql.create("select * from TEMP_BRANCH_INFO where 1=1")
				.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(and("city_code = :city_code",hasKey(p,"city_code")))
				.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(and("post_code = :post_code",hasKey(p,"post_code")))
				.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from TEMP_BRANCH_INFO where 1=1")
			.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
						.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
						.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
						.append(and("city_code = :city_code",hasKey(p,"city_code")))
						.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
						.append(and("post_code = :post_code",hasKey(p,"post_code")))
						.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
						.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
						.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
						.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
						.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
						.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
						.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
						.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
						.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
						.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
						.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
						.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
						.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
						.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
						.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
						.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
						.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
						.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
						.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
						.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
						.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
						.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
						.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
			;

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
		PageInfo  pageInf = MfpContextHolder.getPageInfo();
		pageInf.setITotalDisplayRecords(count);
		MfpContextHolder.setPageInfo(pageInf);
		sql.append(orderBySql()).append(pageSql());
		sqlStr=sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
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
		String sql=Sql.create("select * from TEMP_BRANCH_INFO where 1=1 ")
				.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(and("city_code = :city_code",hasKey(p,"city_code")))
				.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(and("post_code = :post_code",hasKey(p,"post_code")))
				.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from TEMP_BRANCH_INFO where 1=1 ")
				.append(and("acct_class = :acct_class",hasKey(p,"acct_class")))
				.append(and("bran_code = :bran_code",hasKey(p,"bran_code")))
				.append(and("bran_name = :bran_name",hasKey(p,"bran_name")))
				.append(and("city_code = :city_code",hasKey(p,"city_code")))
				.append(and("oth_addr = :oth_addr",hasKey(p,"oth_addr")))
				.append(and("post_code = :post_code",hasKey(p,"post_code")))
				.append(and("bran_type = :bran_type",hasKey(p,"bran_type")))
				.append(and("sub_grp_no = :sub_grp_no",hasKey(p,"sub_grp_no")))
				.append(and("quota_grp_no = :quota_grp_no",hasKey(p,"quota_grp_no")))
				.append(and("bran_status = :bran_status",hasKey(p,"bran_status")))
				.append(and("last_trans_date = :last_trans_date",hasKey(p,"last_trans_date")))
				.append(and("par_mana_flag = :par_mana_flag",hasKey(p,"par_mana_flag")))
				.append(and("act_flag = :act_flag",hasKey(p,"act_flag")))
				.append(and("ret_bran_code = :ret_bran_code",hasKey(p,"ret_bran_code")))
				.append(and("count_bran_code = :count_bran_code",hasKey(p,"count_bran_code")))
				.append(and("adm_bran_code = :adm_bran_code",hasKey(p,"adm_bran_code")))
				.append(and("bran_level = :bran_level",hasKey(p,"bran_level")))
				.append(and("set_bran_code = :set_bran_code",hasKey(p,"set_bran_code")))
				.append(and("for_set_bran_code = :for_set_bran_code",hasKey(p,"for_set_bran_code")))
				.append(and("card_bran_code = :card_bran_code",hasKey(p,"card_bran_code")))
				.append(and("report_bran_code = :report_bran_code",hasKey(p,"report_bran_code")))
				.append(and("country_draft = :country_draft",hasKey(p,"country_draft")))
				.append(and("city_draft = :city_draft",hasKey(p,"city_draft")))
				.append(and("draft_flag = :draft_flag",hasKey(p,"draft_flag")))
				.append(and("swift_no = :swift_no",hasKey(p,"swift_no")))
				.append(and("area_code_array = :area_code_array",hasKey(p,"area_code_array")))
				.append(and("set_bank_no = :set_bank_no",hasKey(p,"set_bank_no")))
				.append(and("set_clear_bank_no = :set_clear_bank_no",hasKey(p,"set_clear_bank_no")))
				.append(and("ctrl_bit = :ctrl_bit",hasKey(p,"ctrl_bit")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


	/**
	 * 一次性删除全表数据
	 * @author chenhao
	 */
	public int truncate(){
		return super.update("truncate table TEMP_BRANCH_INFO", BaseUtils.map());
	}

}
