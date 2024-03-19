package com.gdnybank.hnm.pub.dao;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

@Repository
public class HFileInfoDao extends TXBaseDao {

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","busi_id","file_type","code","upload_user_id","file_num","local_file_path","create_time","update_time","is_delete","business","place","batchid","send_error_msg","file_url","busi_seq_no"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};

	@Override
	public int save(Map<String,Object> p) {
		//p.put("id",sequenceService.getTableFlowNo("H_FILE_INFO", "id"));
		String sql=Sql.create("insert into H_FILE_INFO (")
				.append(field("id "))
				.append(field("busi_id ",hasKey(p,"busi_id")))
				.append(field("file_type ",hasKey(p,"file_type")))
				.append(field("code ",hasKey(p,"code")))
				.append(field("upload_user_id ",hasKey(p,"upload_user_id")))
				.append(field("file_num ",hasKey(p,"file_num")))
				.append(field("local_file_path ",hasKey(p,"local_file_path")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("update_time ",hasKey(p,"update_time")))
				.append(field("is_delete ",hasKey(p,"is_delete")))
				.append(field("business ",hasKey(p,"business")))
				.append(field("place ",hasKey(p,"place")))
				.append(field("batchid ",hasKey(p,"batchid")))
				.append(field("send_error_msg ",hasKey(p,"send_error_msg")))
				.append(field("file_url ",hasKey(p,"file_url")))
				.append(field("busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":busi_id ",hasKey(p,"busi_id")))
				.append(field(":file_type ",hasKey(p,"file_type")))
				.append(field(":code ",hasKey(p,"code")))
				.append(field(":upload_user_id ",hasKey(p,"upload_user_id")))
				.append(field(":file_num ",hasKey(p,"file_num")))
				.append(field(":local_file_path ",hasKey(p,"local_file_path")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":update_time ",hasKey(p,"update_time")))
				.append(field(":is_delete ",hasKey(p,"is_delete")))
				.append(field(":business ",hasKey(p,"business")))
				.append(field(":place ",hasKey(p,"place")))
				.append(field(":batchid ",hasKey(p,"batchid")))
				.append(field(":send_error_msg ",hasKey(p,"send_error_msg")))
				.append(field(":file_url ",hasKey(p,"file_url")))
				.append(field(":busi_seq_no ",hasKey(p,"busi_seq_no")))
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
		String sql=Sql.create("delete from H_FILE_INFO where 1=1 ")
				.append(and("id = :id"))
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
		String sql=Sql.create("delete from H_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_FILE_INFO set ")
				.append(field("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(field("file_type = :file_type",hasKey(p,"file_type")))
				.append(field("code = :code",hasKey(p,"code")))
				.append(field("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(field("file_num = :file_num",hasKey(p,"file_num")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("business = :business",hasKey(p,"business")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("file_url = :file_url",hasKey(p,"file_url")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_FILE_INFO set ")
				.append(field("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(field("file_type = :file_type",hasKey(p,"file_type")))
				.append(field("code = :code",hasKey(p,"code")))
				.append(field("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(field("file_num = :file_num",hasKey(p,"file_num")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("business = :business",hasKey(p,"business")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("file_url = :file_url",hasKey(p,"file_url")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int saveOrUpdate(Map<String,Object> p) {

		if(count(p)>0){
			return update(p);
		}else{
			return save(p);
		}
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select t1.*,t2.approval_status,t2.approval_status_name from H_FILE_INFO t1 LEFT JOIN T_APPROVAL_APPLY t2 on t1.BUSI_ID=t2.RELATION_ID where 1=1")
				.append(and("t1.id = :id",hasKey(p,"id")))
				.append(and("t1.busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("t1.file_type = :file_type",hasKey(p,"file_type")))
				.append(and("t1.code = :code",hasKey(p,"code")))
				.append(and("t1.upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("t1.file_num = :file_num",hasKey(p,"file_num")))
				.append(and("t1.local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("t1.create_time = :create_time",hasKey(p,"create_time")))
				.append(and("t1.update_time = :update_time",hasKey(p,"update_time")))
				.append(and("t1.is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("t1.business = :business",hasKey(p,"business")))
				.append(and("t1.place = :place",hasKey(p,"place")))
				.append(and("t1.batchid = :batchid",hasKey(p,"batchid")))
				.append(and("t1.send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("t1.file_url = :file_url",hasKey(p,"file_url")))
				.append(and("t1.busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("t2.approval_status  in (" + p.get("approval_status") + ")",hasKey(p,"approval_status")))
				.append(" order by t1.create_time")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select * from H_FILE_INFO where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
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
			sqlStr=Sql.pageSqlInOracle(sql.append(orderBySql()).toString());
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
		String sql=Sql.create("select * from H_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}

	public int updateByBusiId(Map<String,Object> p) {
		String sql=Sql.create("update H_FILE_INFO set ")
				.append(field("file_type = :file_type",hasKey(p,"file_type")))
				.append(field("code = :code",hasKey(p,"code")))
				.append(field("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(field("file_num = :file_num",hasKey(p,"file_num")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("business = :business",hasKey(p,"business")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("file_url = :file_url",hasKey(p,"file_url")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("busi_id = :busi_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	public int updateByPkS(Map<String,Object> p) {
		String sql=Sql.create("update H_FILE_INFO set ")
				.append(field("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(field("file_type = :file_type",hasKey(p,"file_type")))
				.append(field("code = :code",hasKey(p,"code")))
				.append(field("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(field("file_num = :file_num",hasKey(p,"file_num")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("update_time = :update_time",hasKey(p,"update_time")))
				.append(field("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(field("business = :business",hasKey(p,"business")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("file_url = :file_url",hasKey(p,"file_url")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" where 1=1 ")
				.append(and("id in (" + p.get("ids") + ")", hasKey(p, "ids")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
	public List<Map<String, Object>> queryForPhotoList(Map<String,Object> p) {
		String sql=Sql.create("select id name,file_url url,code from H_FILE_INFO where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("id  in (" + p.get("ids") + ")",hasKey(p,"ids")))
				.append(" order by create_time")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}
	public List<Map<String, Object>> queryPhotoList(Map<String,Object> p) {
		String sql=Sql.create("select id name,local_file_path path,code,create_time from H_FILE_INFO where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("id  in (" + p.get("ids") + ")",hasKey(p,"ids")))
				.append(" order by create_time")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}
	public List<Map<String, Object>> queryForListByIds(Map<String,Object> p) {
		String sql=Sql.create("select * from H_FILE_INFO where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("busi_id = :busi_id",hasKey(p,"busi_id")))
				.append(and("file_type = :file_type",hasKey(p,"file_type")))
				.append(and("code = :code",hasKey(p,"code")))
				.append(and("upload_user_id = :upload_user_id",hasKey(p,"upload_user_id")))
				.append(and("file_num = :file_num",hasKey(p,"file_num")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("update_time = :update_time",hasKey(p,"update_time")))
				.append(and("is_delete = :is_delete",hasKey(p,"is_delete")))
				.append(and("business = :business",hasKey(p,"business")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("file_url = :file_url",hasKey(p,"file_url")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("id  in (" + p.get("ids") + ")",hasKey(p,"ids")))
				.append(" order by create_time")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	public int updateBusiId(Map<String,Object> p) {
		String sql=Sql.create("update H_FILE_INFO set busi_id = NULL ")
				.append(" where 1=1 ")
				.append(and("busi_id = :busi_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

}