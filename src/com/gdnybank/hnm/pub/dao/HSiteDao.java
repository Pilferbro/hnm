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
 * 表名:H_SITE
 * 主键:
 * id
 **/
@Repository
public class HSiteDao extends TXBaseDao {

	/**
	 * 当前所有字段名
	 **/
	String[] fieldNames=new String[]{"id","site_no","site_name","branch_id","mgr_id","card_no","site_source","recommender","contract_date","master_name","master_tel","master_adr","master_age","master_birthday","cooperate_date","education","relationship","status","remarks","create_time","update_time","site_adr","lng","lat","is_delete","open_date","p_site_adr","p_master_adr","master_identify_no","card_open_bank","card_account_name","virtual_employee","terminal_no","terminal_sn","terminal_type","service_radius","temp_save","creator","grade_score","sex","marriage","emergency_contact","occupation","occupation_explain","retire","credit_investigation","max_due_term","total_due_term","credit_bal","special_staff","over_due_term","stb_id","credit_num","merchant_num","pos_sn"};
	/**
	 * 当前主键(包括多主键)
	 **/
	String[] primaryKeys = new String[]{"id"};

	@Override
	public int save(Map<String, Object> p) {
		String sql = Sql.create("insert into H_SITE (")
				.append(field("id "))
				.append(field("site_no ",hasKey(p,"site_no")))
				.append(field("site_name ", hasKey(p, "site_name")))
				.append(field("branch_id ", hasKey(p, "branch_id")))
				.append(field("mgr_id ", hasKey(p, "mgr_id")))
				.append(field("card_no ", hasKey(p, "card_no")))
				.append(field("site_source ", hasKey(p, "site_source")))
				.append(field("recommender ", hasKey(p, "recommender")))
				.append(field("contract_date ", hasKey(p, "contract_date")))
				.append(field("master_name ", hasKey(p, "master_name")))
				.append(field("master_tel ", hasKey(p, "master_tel")))
				.append(field("master_adr ", hasKey(p, "master_adr")))
				.append(field("master_age ", hasKey(p, "master_age")))
				.append(field("master_birthday ", hasKey(p, "master_birthday")))
				.append(field("cooperate_date ", hasKey(p, "cooperate_date")))
				.append(field("education ", hasKey(p, "education")))
				.append(field("relationship ", hasKey(p, "relationship")))
				.append(field("status ", hasKey(p, "status")))
				.append(field("remarks ", hasKey(p, "remarks")))
				.append(field("create_time ", hasKey(p, "create_time")))
				.append(field("update_time ", hasKey(p, "update_time")))
				.append(field("site_adr ", hasKey(p, "site_adr")))
				.append(field("lng ", hasKey(p, "lng")))
				.append(field("lat ", hasKey(p, "lat")))
				.append(field("is_delete ",hasKey(p,"is_delete")))
				.append(field("open_date ",hasKey(p,"open_date")))
				.append(field("p_site_adr ",hasKey(p,"p_site_adr")))
				.append(field("p_master_adr ",hasKey(p,"p_master_adr")))
				.append(field("master_identify_no ", hasKey(p, "master_identify_no")))
				.append(field("card_open_bank ", hasKey(p, "card_open_bank")))
				.append(field("card_account_name ", hasKey(p, "card_account_name")))
				.append(field("virtual_employee ",hasKey(p,"virtual_employee")))
				.append(field("terminal_no ",hasKey(p,"terminal_no")))
				.append(field("terminal_sn ",hasKey(p,"terminal_sn")))
				.append(field("terminal_type ",hasKey(p,"terminal_type")))
				.append(field("service_radius ",hasKey(p,"service_radius")))
				.append(field("temp_save ",hasKey(p,"temp_save")))
				.append(field("creator ",hasKey(p,"creator")))
				.append(field("grade_score ",hasKey(p,"grade_score")))
				.append(field("sex ",hasKey(p,"sex")))
				.append(field("marriage ",hasKey(p,"marriage")))
				.append(field("emergency_contact ",hasKey(p,"emergency_contact")))
				.append(field("occupation ",hasKey(p,"occupation")))
				.append(field("occupation_explain ",hasKey(p,"occupation_explain")))
				.append(field("retire ",hasKey(p,"retire")))
				.append(field("credit_investigation ",hasKey(p,"credit_investigation")))
				.append(field("max_due_term ",hasKey(p,"max_due_term")))
				.append(field("total_due_term ",hasKey(p,"total_due_term")))
				.append(field("credit_bal ",hasKey(p,"credit_bal")))
				.append(field("special_staff ",hasKey(p,"special_staff")))
				.append(field("over_due_term ",hasKey(p,"over_due_term")))
				.append(field("stb_id ",hasKey(p,"stb_id")))
				.append(field("credit_num ",hasKey(p,"credit_num")))
				.append(field("merchant_num ",hasKey(p,"merchant_num")))
				.append(field("pos_sn ",hasKey(p,"pos_sn")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":site_no ",hasKey(p,"site_no")))
				.append(field(":site_name ", hasKey(p, "site_name")))
				.append(field(":branch_id ", hasKey(p, "branch_id")))
				.append(field(":mgr_id ", hasKey(p, "mgr_id")))
				.append(field(":card_no ", hasKey(p, "card_no")))
				.append(field(":site_source ", hasKey(p, "site_source")))
				.append(field(":recommender ", hasKey(p, "recommender")))
				.append(field(":contract_date ", hasKey(p, "contract_date")))
				.append(field(":master_name ", hasKey(p, "master_name")))
				.append(field(":master_tel ", hasKey(p, "master_tel")))
				.append(field(":master_adr ", hasKey(p, "master_adr")))
				.append(field(":master_age ", hasKey(p, "master_age")))
				.append(field(":master_birthday ", hasKey(p, "master_birthday")))
				.append(field(":cooperate_date ", hasKey(p, "cooperate_date")))
				.append(field(":education ", hasKey(p, "education")))
				.append(field(":relationship ", hasKey(p, "relationship")))
				.append(field(":status ", hasKey(p, "status")))
				.append(field(":remarks ", hasKey(p, "remarks")))
				.append(field(":create_time ", hasKey(p, "create_time")))
				.append(field(":update_time ", hasKey(p, "update_time")))
				.append(field(":site_adr ", hasKey(p, "site_adr")))
				.append(field(":lng ", hasKey(p, "lng")))
				.append(field(":lat ", hasKey(p, "lat")))
				.append(field(":is_delete ",hasKey(p,"is_delete")))
				.append(field(":open_date ",hasKey(p,"open_date")))
				.append(field(":p_site_adr ",hasKey(p,"p_site_adr")))
				.append(field(":p_master_adr ",hasKey(p,"p_master_adr")))
				.append(field(":master_identify_no ", hasKey(p, "master_identify_no")))
				.append(field(":card_open_bank ", hasKey(p, "card_open_bank")))
				.append(field(":card_account_name ", hasKey(p, "card_account_name")))
				.append(field(":virtual_employee ",hasKey(p,"virtual_employee")))
				.append(field(":terminal_no ",hasKey(p,"terminal_no")))
				.append(field(":terminal_sn ",hasKey(p,"terminal_sn")))
				.append(field(":terminal_type ",hasKey(p,"terminal_type")))
				.append(field(":service_radius ",hasKey(p,"service_radius")))
				.append(field(":temp_save ",hasKey(p,"temp_save")))
				.append(field(":creator ",hasKey(p,"creator")))
				.append(field(":grade_score ",hasKey(p,"grade_score")))
				.append(field(":sex ",hasKey(p,"sex")))
				.append(field(":marriage ",hasKey(p,"marriage")))
				.append(field(":emergency_contact ",hasKey(p,"emergency_contact")))
				.append(field(":occupation ",hasKey(p,"occupation")))
				.append(field(":occupation_explain ",hasKey(p,"occupation_explain")))
				.append(field(":retire ",hasKey(p,"retire")))
				.append(field(":credit_investigation ",hasKey(p,"credit_investigation")))
				.append(field(":max_due_term ",hasKey(p,"max_due_term")))
				.append(field(":total_due_term ",hasKey(p,"total_due_term")))
				.append(field(":credit_bal ",hasKey(p,"credit_bal")))
				.append(field(":special_staff ",hasKey(p,"special_staff")))
				.append(field(":over_due_term ",hasKey(p,"over_due_term")))
				.append(field(":stb_id ",hasKey(p,"stb_id")))
				.append(field(":credit_num ",hasKey(p,"credit_num")))
				.append(field(":merchant_num ",hasKey(p,"merchant_num")))
				.append(field(":pos_sn ",hasKey(p,"pos_sn")))
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
		String sql = Sql.create("delete from H_SITE where 1=1 ")
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
		String sql = Sql.create("delete from H_SITE where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("site_no ",hasKey(p,"site_no")))
				.append(and("site_name = :site_name", hasKey(p, "site_name")))
				.append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("card_no = :card_no", hasKey(p, "card_no")))
				.append(and("site_source = :site_source", hasKey(p, "site_source")))
				.append(and("recommender = :recommender", hasKey(p, "recommender")))
				.append(and("contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("master_name = :master_name", hasKey(p, "master_name")))
				.append(and("master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("master_age = :master_age", hasKey(p, "master_age")))
				.append(and("master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("education = :education", hasKey(p, "education")))
				.append(and("relationship = :relationship", hasKey(p, "relationship")))
				.append(and("status = :status", hasKey(p, "status")))
				.append(and("remarks = :remarks", hasKey(p, "remarks")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("lng = :lng", hasKey(p, "lng")))
				.append(and("lat = :lat", hasKey(p, "lat")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("open_date = :open_date",hasKey(p,"open_date")))
				.append(and("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("marriage = :marriage",hasKey(p,"marriage")))
				.append(and("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("occupation = :occupation",hasKey(p,"occupation")))
				.append(and("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("retire = :retire",hasKey(p,"retire")))
				.append(and("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.toString();
		printSql(sql, p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String, Object> p) {
		String sql = Sql.create("update H_SITE set ")
				.append(field("site_no = :site_no",hasKey(p,"site_no")))
				.append(field("site_name = :site_name", hasKey(p, "site_name")))
				.append(field("branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(field("mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(field("card_no = :card_no", hasKey(p, "card_no")))
				.append(field("site_source = :site_source", hasKey(p, "site_source")))
				.append(field("recommender = :recommender", hasKey(p, "recommender")))
				.append(field("contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(field("master_name = :master_name", hasKey(p, "master_name")))
				.append(field("master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(field("master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(field("master_age = :master_age", hasKey(p, "master_age")))
				.append(field("master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(field("cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(field("education = :education", hasKey(p, "education")))
				.append(field("relationship = :relationship", hasKey(p, "relationship")))
				.append(field("status = :status", hasKey(p, "status")))
				.append(field("remarks = :remarks", hasKey(p, "remarks")))
				.append(field("create_time = :create_time", hasKey(p, "create_time")))
				.append(field("update_time = :update_time", hasKey(p, "update_time")))
				.append(field("site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(field("lng = :lng", hasKey(p, "lng")))
				.append(field("lat = :lat", hasKey(p, "lat")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("open_date = :open_date",hasKey(p,"open_date")))
				.append(field("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(field("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(field("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(field("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(field("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(field("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(field("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(field("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(field("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(field("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(field("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("marriage = :marriage",hasKey(p,"marriage")))
				.append(field("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(field("occupation = :occupation",hasKey(p,"occupation")))
				.append(field("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(field("retire = :retire",hasKey(p,"retire")))
				.append(field("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(field("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(field("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(field("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(field("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(field("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(field("stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(field("credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(field("merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(field("pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql, p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_SITE set ")
				.append(field("site_no = :site_no",hasKey(p,"site_no")))
				.append(field("site_name = :site_name",hasKey(p,"site_name")))
				.append(field("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(field("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(field("card_no = :card_no",hasKey(p,"card_no")))
				.append(field("site_source = :site_source",hasKey(p,"site_source")))
				.append(field("recommender = :recommender",hasKey(p,"recommender")))
				.append(field("contract_date = :contract_date",hasKey(p,"contract_date")))
				.append(field("master_name = :master_name",hasKey(p,"master_name")))
				.append(field("master_tel = :master_tel",hasKey(p,"master_tel")))
				.append(field("master_adr = :master_adr",hasKey(p,"master_adr")))
				.append(field("master_age = :master_age",hasKey(p,"master_age")))
				.append(field("master_birthday = :master_birthday",hasKey(p,"master_birthday")))
				.append(field("cooperate_date = :cooperate_date",hasKey(p,"cooperate_date")))
				.append(field("education = :education",hasKey(p,"education")))
				.append(field("relationship = :relationship",hasKey(p,"relationship")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("remarks = :remarks",hasKey(p,"remarks")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("site_adr = :site_adr",hasKey(p,"site_adr")))
				.append(field("lng = :lng",hasKey(p,"lng")))
				.append(field("lat = :lat",hasKey(p,"lat")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("open_date = :open_date",hasKey(p,"open_date")))
				.append(field("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(field("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(field("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(field("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(field("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(field("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(field("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(field("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(field("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(field("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(field("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(field("creator = :creator",hasKey(p,"creator")))
				.append(field("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("marriage = :marriage",hasKey(p,"marriage")))
				.append(field("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(field("occupation = :occupation",hasKey(p,"occupation")))
				.append(field("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(field("retire = :retire",hasKey(p,"retire")))
				.append(field("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(field("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(field("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(field("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(field("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(field("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(field("stb_id = :stb_id ",hasKey(p,"stb_id")))
				.append(field("credit_num = :credit_num ",hasKey(p,"credit_num")))
				.append(field("merchant_num = :merchant_num ",hasKey(p,"merchant_num")))
				.append(field("pos_sn = :pos_sn ",hasKey(p,"pos_sn")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name",hasKey(p,"site_name")))
				.append(and("branch_id = :branch_id",hasKey(p,"branch_id")))
				.append(and("mgr_id = :mgr_id",hasKey(p,"mgr_id")))
				.append(and("card_no = :card_no",hasKey(p,"card_no")))
				.append(and("site_source = :site_source",hasKey(p,"site_source")))
				.append(and("recommender = :recommender",hasKey(p,"recommender")))
				.append(and("contract_date = :contract_date",hasKey(p,"contract_date")))
				.append(and("master_name = :master_name",hasKey(p,"master_name")))
				.append(and("master_tel = :master_tel",hasKey(p,"master_tel")))
				.append(and("master_adr = :master_adr",hasKey(p,"master_adr")))
				.append(and("master_age = :master_age",hasKey(p,"master_age")))
				.append(and("master_birthday = :master_birthday",hasKey(p,"master_birthday")))
				.append(and("cooperate_date = :cooperate_date",hasKey(p,"cooperate_date")))
				.append(and("education = :education",hasKey(p,"education")))
				.append(and("relationship = :relationship",hasKey(p,"relationship")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("remarks = :remarks",hasKey(p,"remarks")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("site_adr = :site_adr",hasKey(p,"site_adr")))
				.append(and("lng = :lng",hasKey(p,"lng")))
				.append(and("lat = :lat",hasKey(p,"lat")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("open_date = :open_date",hasKey(p,"open_date")))
				.append(and("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("marriage = :marriage",hasKey(p,"marriage")))
				.append(and("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("occupation = :occupation",hasKey(p,"occupation")))
				.append(and("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("retire = :retire",hasKey(p,"retire")))
				.append(and("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("stb_id = :stb_id ",hasKey(p,"stb_id")))
				.append(and("credit_num = :credit_num ",hasKey(p,"credit_num")))
				.append(and("merchant_num = :merchant_num ",hasKey(p,"merchant_num")))
				.append(and("pos_sn = :pos_sn ",hasKey(p,"pos_sn")))
				.toString();
		printSql(sql,p);
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
		String sql = Sql.create("select t1.*,t2.approval_status_name,t3.branch_name,t4.name mgr_name from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID left join SYS_BRANCH t3 on t1.branch_id = t3.branch_id left join SYS_ACCOUNT t4 on t1.mgr_id = t4.account_id where 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status = :status", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t2.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(orderBySql())
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
		Sql sql = Sql.create("select * from H_SITE where 1=1")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name", hasKey(p, "site_name")))
				.append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("card_no = :card_no", hasKey(p, "card_no")))
				.append(and("site_source = :site_source", hasKey(p, "site_source")))
				.append(and("recommender = :recommender", hasKey(p, "recommender")))
				.append(and("contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("master_name = :master_name", hasKey(p, "master_name")))
				.append(and("master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("master_age = :master_age", hasKey(p, "master_age")))
				.append(and("master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("education = :education", hasKey(p, "education")))
				.append(and("relationship = :relationship", hasKey(p, "relationship")))
				.append(and("status = :status", hasKey(p, "status")))
				.append(and("remarks = :remarks", hasKey(p, "remarks")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("lng = :lng", hasKey(p, "lng")))
				.append(and("lat = :lat", hasKey(p, "lat")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("open_date = :open_date",hasKey(p,"open_date")))
				.append(and("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("marriage = :marriage",hasKey(p,"marriage")))
				.append(and("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("occupation = :occupation",hasKey(p,"occupation")))
				.append(and("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("retire = :retire",hasKey(p,"retire")))
				.append(and("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("pos_sn = :pos_sn",hasKey(p,"pos_sn")))
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



	public List<Map<String, Object>> queryList(Map<String, Object> p) {
		Sql sql = Sql.create("select t1.* ,t2.approval_status,t2.approval_status_name,t4.branch_name,t5.branch_id org_id,t5.branch_name org_name,concat(t1.mgr_id,t3.name) mgr_name from H_SITE t1 " +
				"left join T_APPROVAL_APPLY t2 on t1.id = t2.relation_id " +
				"left join SYS_ACCOUNT t3 on t1.mgr_id = t3.account_id " +
				"left join SYS_BRANCH t4 on t1.branch_id = t4.branch_id " +
				"left join SYS_BRANCH t5 on t3.branch_id = t5.branch_id " +
				" WHERE 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status = :status", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t2.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(and("t2.update_time >= :start_date",hasKey(p,"start_date")))
				.append(and("t2.update_time <= :end_date",hasKey(p,"end_date")))
				;
		if(p.containsKey("approval_types")&&p.get("approval_types")!=null) {
			sql.append(and("t2.approval_type in ("+ p.get("approval_types")+")"));
		}
		if(p.containsKey("allOrgids")&&p.get("allOrgids")!=null) {
			sql.append(and("(t3.branch_id in ("+ p.get("allOrgids")+")"+" or t1.branch_id in ("+ p.get("allOrgids")+"))"));
		}
		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("t3.branch_id in ("+ p.get("orgids")+")"));
		}
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}

		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		//获取数据库类型
		String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType) {
			long count = count("select count(*) from (" + sqlStr + ")  ", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr = Sql.pageSqlInOracle(sql.append(" order by t1.create_time desc").toString());
			printSql(sqlStr, p);
			return queryForList(sqlStr, p);
		} else {
			long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
			PageInfo pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by t1.create_time desc").append(pageSql());
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
		String sql = Sql.create("select * from H_SITE where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name", hasKey(p, "site_name")))
				.append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("card_no = :card_no", hasKey(p, "card_no")))
				.append(and("site_source = :site_source", hasKey(p, "site_source")))
				.append(and("recommender = :recommender", hasKey(p, "recommender")))
				.append(and("contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("master_name = :master_name", hasKey(p, "master_name")))
				.append(and("master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("master_age = :master_age", hasKey(p, "master_age")))
				.append(and("master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("education = :education", hasKey(p, "education")))
				.append(and("relationship = :relationship", hasKey(p, "relationship")))
				.append(and("status = :status", hasKey(p, "status")))
				.append(and("remarks = :remarks", hasKey(p, "remarks")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("lng = :lng", hasKey(p, "lng")))
				.append(and("lat = :lat", hasKey(p, "lat")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("open_date = :open_date",hasKey(p,"open_date")))
				.append(and("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("marriage = :marriage",hasKey(p,"marriage")))
				.append(and("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("occupation = :occupation",hasKey(p,"occupation")))
				.append(and("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("retire = :retire",hasKey(p,"retire")))
				.append(and("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.toString();
		printSql(sql, p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String, Object> p) {
		String sql = Sql.create("select count(*) from H_SITE where 1=1 ")
				.append(and("id = :id", hasKey(p, "id")))
				.append(and("site_no = :site_no",hasKey(p,"site_no")))
				.append(and("site_name = :site_name", hasKey(p, "site_name")))
				.append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("card_no = :card_no", hasKey(p, "card_no")))
				.append(and("site_source = :site_source", hasKey(p, "site_source")))
				.append(and("recommender = :recommender", hasKey(p, "recommender")))
				.append(and("contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("master_name = :master_name", hasKey(p, "master_name")))
				.append(and("master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("master_age = :master_age", hasKey(p, "master_age")))
				.append(and("master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("education = :education", hasKey(p, "education")))
				.append(and("relationship = :relationship", hasKey(p, "relationship")))
				.append(and("status = :status", hasKey(p, "status")))
				.append(and("remarks = :remarks", hasKey(p, "remarks")))
				.append(and("create_time = :create_time", hasKey(p, "create_time")))
				.append(and("update_time = :update_time", hasKey(p, "update_time")))
				.append(and("site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("lng = :lng", hasKey(p, "lng")))
				.append(and("lat = :lat", hasKey(p, "lat")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("open_date = :open_date",hasKey(p,"open_date")))
				.append(and("p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("creator = :creator",hasKey(p,"creator")))
				.append(and("grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("marriage = :marriage",hasKey(p,"marriage")))
				.append(and("emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("occupation = :occupation",hasKey(p,"occupation")))
				.append(and("occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("retire = :retire",hasKey(p,"retire")))
				.append(and("credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.toString();
		printSql(sql, p);
		return count(sql, p);
	}


	public List<Map<String, Object>> query(Map<String,Object> p) {
		Sql sql = Sql.create("select t1.* ,t2.approval_status,t2.approval_status_name,t4.branch_name,t5.branch_id org_id," +
				"t5.branch_name org_name,concat(t1.mgr_id,t3.name) mgr_name from H_SITE t1 " +
				"left join T_APPROVAL_APPLY t2 on t1.id = t2.relation_id " +
				"left join SYS_ACCOUNT t3 on t1.mgr_id = t3.account_id " +
				"left join SYS_BRANCH t4 on t1.branch_id = t4.branch_id " +
				"left join SYS_BRANCH t5 on t3.branch_id = t5.branch_id " +
				" WHERE 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status = :status", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(and("t2.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t2.update_time >= :start_date",hasKey(p,"start_date")))
				.append(and("t2.update_time <= :end_date",hasKey(p,"end_date")))
				;
		if(p.containsKey("approval_types")&&p.get("approval_types")!=null) {
			sql.append(and("t2.approval_type in ("+ p.get("approval_types")+")"));
		}
		if(p.containsKey("allOrgids")&&p.get("allOrgids")!=null) {
			sql.append(and("(t3.branch_id in ("+ p.get("allOrgids")+")"+" or t1.branch_id in ("+ p.get("allOrgids")+"))"));
		}
		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("t3.branch_id in ("+ p.get("orgids")+")"));
		}
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}

		sql.append(" order by t1.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	public List<Map<String, Object>> queryForListBycondition(Map<String, Object> p) {
		Sql sql = Sql.create("select t1.*,t1.update_time approval_time,t3.phone phone from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID left join SYS_ACCOUNT t3 on t1.mgr_id = t3.account_id where 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status in ("+p.get("status")+")", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t2.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				;
		if(p.containsKey("statuss")&&p.get("statuss")!=null) {
			sql.append(and("t1.status in ("+ p.get("statuss")+")"));
		}
		sql.append(orderBySql());
		String sqlStr = sql.toString();
		printSql(sqlStr, p);
		return queryForList(sqlStr, p);
	}

	/**
	 * 查询最大site_no
	 */
	public List<Map<String, Object>> queryMaxSiteNo(Map<String, Object> p) {
		String sql = "select max(site_no) site_no from H_SITE";
		printSql(sql, p);
		return queryForList(sql, p);
	}
	/**
	 * 记录每天站点信息
	 */
	public void logSite() {
		String sql = "INSERT INTO H_SITE_LOG (" +
				"SELECT RAWTOHEX(SYS_GUID()) AS ID,s.ID AS SITE_ID,s.SITE_NO,s.SITE_NAME,s.BRANCH_ID,s.MGR_ID,s.STATUS,s.CREATE_TIME," +
				"s.UPDATE_TIME,TO_CHAR(SYSDATE,'yyyyMMdd') AS LOG_TIME " +
				"FROM H_SITE s LEFT JOIN T_APPROVAL_APPLY a ON s.id=a.RELATION_ID WHERE s.IS_DELETE = '0' AND a.APPROVAL_STATUS = '2')";
		this.getJdbcTemplate().execute(sql);
	}


	/**
	 * 单纯查询站点编号/站点名
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryLesList(Map<String,Object> p) {
		Sql sql = Sql.create("select t1.site_no,t1.site_name from H_SITE t1 " +
				"left join T_APPROVAL_APPLY t2 on t1.id = t2.relation_id " +
				"left join SYS_ACCOUNT t3 on t1.mgr_id = t3.account_id " +
				"left join SYS_BRANCH t4 on t1.branch_id = t4.branch_id " +
				"left join SYS_BRANCH t5 on t3.branch_id = t5.branch_id " +
				" WHERE 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status = :status", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(and("t2.approval_status = :approval_status",hasKey(p,"approval_status")))
				.append(and("t2.update_time >= :start_date",hasKey(p,"start_date")))
				.append(and("t2.update_time <= :end_date",hasKey(p,"end_date")))
				;
		if(p.containsKey("approval_types")&&p.get("approval_types")!=null) {
			sql.append(and("t2.approval_type in ("+ p.get("approval_types")+")"));
		}
		if(p.containsKey("allOrgids")&&p.get("allOrgids")!=null) {
			sql.append(and("(t3.branch_id in ("+ p.get("allOrgids")+")"+" or t1.branch_id in ("+ p.get("allOrgids")+"))"));
		}
		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("t3.branch_id in ("+ p.get("orgids")+")"));
		}
		if(p.containsKey("branchids")&&p.get("branchids")!=null) {
			sql.append(and("t1.branch_id in ("+ p.get("branchids")+")"));
		}

		sql.append(" order by t1.create_time desc");
		String sqlStr = sql.toString();
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	public List<Map<String, Object>> queryForListToEsb(Map<String, Object> p) {
		String sql = Sql.create("select t1.site_no,t1.site_name,t1.branch_id,t1.mgr_id,t1.status,t1.master_name,t3.branch_name,t4.name mgr_name from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID " +
				" left join SYS_BRANCH t3 on t1.branch_id = t3.branch_id left join SYS_ACCOUNT t4 on t1.mgr_id = t4.account_id where 1=1")
				.append(and("t1.id = :id", hasKey(p, "id")))
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.site_name = :site_name", hasKey(p, "site_name")))
				.append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
				.append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
				.append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
				.append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
				.append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
				.append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
				.append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
				.append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
				.append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
				.append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
				.append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
				.append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
				.append(and("t1.education = :education", hasKey(p, "education")))
				.append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
				.append(and("t1.status = :status", hasKey(p, "status")))
				.append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
				.append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
				.append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
				.append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
				.append(and("t1.lng = :lng", hasKey(p, "lng")))
				.append(and("t1.lat = :lat", hasKey(p, "lat")))
				.append(and("t2.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.open_date = :open_date",hasKey(p,"open_date")))
				.append(and("t1.p_site_adr = :p_site_adr",hasKey(p,"p_site_adr")))
				.append(and("t1.p_master_adr = :p_master_adr",hasKey(p,"p_master_adr")))
				.append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
				.append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
				.append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
				.append(and("t1.virtual_employee = :virtual_employee",hasKey(p,"virtual_employee")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_type = :terminal_type",hasKey(p,"terminal_type")))
				.append(and("t1.service_radius = :service_radius",hasKey(p,"service_radius")))
				.append(and("t1.temp_save = :temp_save",hasKey(p,"temp_save")))
				.append(and("t1.creator = :creator",hasKey(p,"creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
				.append(orderBySql())
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryForHisList(Map<String, Object> p) {
		String sql = Sql.create("select t2.*,t3.approval_type_name from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY " +
				"t2 on t1.id=t2.RELATION_ID left join T_APPROVAL_TYPE t3 on t2.approval_type = t3.approval_type where 1=1")
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t2.approval_type in (" + p.get("approval_type") + ")",hasKey(p,"approval_type")))
				.append(" order by t2.create_time desc")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

    public List<Map<String, Object>> querySites(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.* ,t2.approval_status,t2.approval_status_name,t4.branch_name,t5.branch_id org_id,t5.branch_name org_name,concat(t1.mgr_id,t3.name) mgr_name from H_SITE t1 " +
                "left join T_APPROVAL_APPLY t2 on t1.id = t2.relation_id " +
                "left join SYS_ACCOUNT t3 on t1.mgr_id = t3.account_id " +
                "left join SYS_BRANCH t4 on t1.branch_id = t4.branch_id " +
                "left join SYS_BRANCH t5 on t3.branch_id = t5.branch_id " +
                " WHERE 1=1")
                .append(and("t1.id = :id", hasKey(p, "id")))
                .append(and("t1.site_no = :site_no", hasKey(p, "site_no")))
                .append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("t1.mgr_id = :mgr_id", hasKey(p, "mgr_id")))
                .append(and("t1.card_no = :card_no", hasKey(p, "card_no")))
                .append(and("t1.site_source = :site_source", hasKey(p, "site_source")))
                .append(and("t1.recommender = :recommender", hasKey(p, "recommender")))
                .append(and("t1.contract_date = :contract_date", hasKey(p, "contract_date")))
                .append(and("t1.master_name = :master_name", hasKey(p, "master_name")))
                .append(and("t1.master_tel = :master_tel", hasKey(p, "master_tel")))
                .append(and("t1.master_adr = :master_adr", hasKey(p, "master_adr")))
                .append(and("t1.master_age = :master_age", hasKey(p, "master_age")))
                .append(and("t1.master_birthday = :master_birthday", hasKey(p, "master_birthday")))
                .append(and("t1.cooperate_date = :cooperate_date", hasKey(p, "cooperate_date")))
                .append(and("t1.education = :education", hasKey(p, "education")))
                .append(and("t1.relationship = :relationship", hasKey(p, "relationship")))
                .append(and("t1.status = :status", hasKey(p, "status")))
                .append(and("t1.remarks = :remarks", hasKey(p, "remarks")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.update_time = :update_time", hasKey(p, "update_time")))
                .append(and("t1.site_adr = :site_adr", hasKey(p, "site_adr")))
                .append(and("t1.lng = :lng", hasKey(p, "lng")))
                .append(and("t1.lat = :lat", hasKey(p, "lat")))
                .append(and("t1.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t1.open_date = :open_date", hasKey(p, "open_date")))
                .append(and("t1.p_site_adr = :p_site_adr", hasKey(p, "p_site_adr")))
                .append(and("t1.p_master_adr = :p_master_adr", hasKey(p, "p_master_adr")))
                .append(and("t2.approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(and("t1.master_identify_no = :master_identify_no", hasKey(p, "master_identify_no")))
                .append(and("t1.card_open_bank = :card_open_bank", hasKey(p, "card_open_bank")))
                .append(and("t1.card_account_name = :card_account_name", hasKey(p, "card_account_name")))
                .append(and("t1.virtual_employee = :virtual_employee", hasKey(p, "virtual_employee")))
                .append(and("t1.terminal_no = :terminal_no", hasKey(p, "terminal_no")))
                .append(and("t1.terminal_sn = :terminal_sn", hasKey(p, "terminal_sn")))
                .append(and("t1.terminal_type = :terminal_type", hasKey(p, "terminal_type")))
                .append(and("t1.service_radius = :service_radius", hasKey(p, "service_radius")))
                .append(and("t1.temp_save = :temp_save", hasKey(p, "temp_save")))
                .append(and("t1.creator = :creator", hasKey(p, "creator")))
				.append(and("t1.grade_score = :grade_score",hasKey(p,"grade_score")))
				.append(and("t1.sex = :sex",hasKey(p,"sex")))
				.append(and("t1.marriage = :marriage",hasKey(p,"marriage")))
				.append(and("t1.emergency_contact = :emergency_contact",hasKey(p,"emergency_contact")))
				.append(and("t1.occupation = :occupation",hasKey(p,"occupation")))
				.append(and("t1.occupation_explain = :occupation_explain",hasKey(p,"occupation_explain")))
				.append(and("t1.retire = :retire",hasKey(p,"retire")))
				.append(and("t1.credit_investigation = :credit_investigation",hasKey(p,"credit_investigation")))
				.append(and("t1.max_due_term = :max_due_term",hasKey(p,"max_due_term")))
				.append(and("t1.total_due_term = :total_due_term",hasKey(p,"total_due_term")))
				.append(and("t1.credit_bal = :credit_bal",hasKey(p,"credit_bal")))
				.append(and("t1.special_staff = :special_staff",hasKey(p,"special_staff")))
				.append(and("t1.over_due_term = :over_due_term",hasKey(p,"over_due_term")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.credit_num = :credit_num",hasKey(p,"credit_num")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.pos_sn = :pos_sn",hasKey(p,"pos_sn")))
                .append(and("t2.update_time >= :start_date", hasKey(p, "start_date")))
                .append(and("t2.update_time <= :end_date", hasKey(p, "end_date")));
        if (p.containsKey("site_name") && p.get("site_name") != null) {
            sql.append(and("t1.site_name like '%" + p.get("site_name") + "%'"));
        }
        if (p.containsKey("approval_types") && p.get("approval_types") != null) {
            sql.append(and("t2.approval_type in (" + p.get("approval_types") + ")"));
        }
        if (p.containsKey("allOrgids") && p.get("allOrgids") != null) {
            sql.append(and("(t3.branch_id in (" + p.get("allOrgids") + ")" + " or t1.branch_id in (" + p.get("allOrgids") + "))"));
        }
        if (p.containsKey("orgids") && p.get("orgids") != null) {
            sql.append(and("t3.branch_id in (" + p.get("orgids") + ")"));
        }
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t1.branch_id in (" + p.get("branchids") + ")"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);

        return queryForList(sqlStr, p);

    }

	public List<Map<String, Object>> queryForListToInspection1(Map<String, Object> p) {
		String sql = Sql.create("select t1.* from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID where 1=1")
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.status in (" + p.get("status") + ")",hasKey(p,"status")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(and("t2.approval_type in (" + p.get("approval_type") + ")",hasKey(p,"approval_type")))
				.append(" order by t1.update_time desc ")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> queryByTerminalInfo(Map<String, Object> p) {
		String sql = Sql.create("select t1.* from H_SITE t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID where 1=1")
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.status in (" + p.get("status") + ")",hasKey(p,"status")))
				.append(and("t2.approval_status in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(" order by t1.update_time desc ")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}

	public List<Map<String, Object>> querySiteName(Map<String, Object> p) {
		String sql = Sql.create("select t1.site_name from H_SITE t1 " +
				"LEFT JOIN T_APPROVAL_APPLY t2 on t1.id=t2.RELATION_ID where 1=1")
				.append(and("t1.site_no = :site_no",hasKey(p,"site_no")))
				.append(and("t1.is_delete = :is_delete", hasKey(p, "is_delete")))
				.append(and("t1.stb_id = :stb_id",hasKey(p,"stb_id")))
				.append(and("t1.terminal_sn = :terminal_sn",hasKey(p,"terminal_sn")))
				.append(and("t1.terminal_no = :terminal_no",hasKey(p,"terminal_no")))
				.append(and("t1.merchant_num = :merchant_num",hasKey(p,"merchant_num")))
				.append(and("t1.status in (" + p.get("status") + ")",hasKey(p,"status")))
				.append(and("t2.approval_status =:approval_status",hasKey(p,"approval_status")))
				.append(" order by t1.update_time desc ")
				.toString();
		printSql(sql, p);
		return queryForList(sql, p);
	}
}
