package com.gdnybank.hnm.pub.dao;

import com.nantian.mfp.framework.dao.sql.Sql;
import com.nantian.mfp.pub.dao.TXBaseDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.nantian.mfp.framework.dao.sql.Sql.*;

/**
 * 自动化工具生成数据库操作类
 * 表名:T_FILE_INFO
 * 主键:
 * id
 **/
@Repository
public class TFileInfoDao extends TXBaseDao {

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"id","flow_no","busi_seq_no","txcode","tx_user","generate_user","auth_user","oper_user","generate_time","generate_time1","create_time","oper_time","local_file_path","place","send_counts","send_error_msg","batchid","remark","zip_size","img_count","vedio_count","undefied_count"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"id"};
	
	@Override
	public int save(Map<String,Object> p) {			
				p.put("id",sequenceService.getTableFlowNo("T_FILE_INFO", "id"));
		String sql= Sql.create("insert into T_FILE_INFO (")
				.append(field("id "))
				.append(field("flow_no ",hasKey(p,"flow_no")))
				.append(field("busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(field("txcode ",hasKey(p,"txcode")))
				.append(field("tx_user ",hasKey(p,"tx_user")))
				.append(field("generate_user ",hasKey(p,"generate_user")))
				.append(field("auth_user ",hasKey(p,"auth_user")))
				.append(field("oper_user ",hasKey(p,"oper_user")))
				.append(field("generate_time ",hasKey(p,"generate_time")))
				.append(field("generate_time1 ",hasKey(p,"generate_time1")))
				.append(field("create_time ",hasKey(p,"create_time")))
				.append(field("oper_time ",hasKey(p,"oper_time")))
				.append(field("local_file_path ",hasKey(p,"local_file_path")))
				.append(field("place ",hasKey(p,"place")))
				.append(field("send_counts ",hasKey(p,"send_counts")))
				.append(field("send_error_msg ",hasKey(p,"send_error_msg")))
				.append(field("batchid ",hasKey(p,"batchid")))
				.append(field("remark ",hasKey(p,"remark")))
				.append(field("zip_size ",hasKey(p,"zip_size")))
				.append(field("img_count ",hasKey(p,"img_count")))
				.append(field("vedio_count ",hasKey(p,"vedio_count")))
				.append(field("undefied_count ",hasKey(p,"undefied_count")))
				.append(field("batchurl ",hasKey(p,"batchurl")))
				.append(") values (")
				.append(field(":id "))
				.append(field(":flow_no ",hasKey(p,"flow_no")))
				.append(field(":busi_seq_no ",hasKey(p,"busi_seq_no")))
				.append(field(":txcode ",hasKey(p,"txcode")))
				.append(field(":tx_user ",hasKey(p,"tx_user")))
				.append(field(":generate_user ",hasKey(p,"generate_user")))
				.append(field(":auth_user ",hasKey(p,"auth_user")))
				.append(field(":oper_user ",hasKey(p,"oper_user")))
				.append(field(":generate_time ",hasKey(p,"generate_time")))
				.append(field(":generate_time1 ",hasKey(p,"generate_time1")))
				.append(field(":create_time ",hasKey(p,"create_time")))
				.append(field(":oper_time ",hasKey(p,"oper_time")))
				.append(field(":local_file_path ",hasKey(p,"local_file_path")))
				.append(field(":place ",hasKey(p,"place")))
				.append(field(":send_counts ",hasKey(p,"send_counts")))
				.append(field(":send_error_msg ",hasKey(p,"send_error_msg")))
				.append(field(":batchid ",hasKey(p,"batchid")))
				.append(field(":remark ",hasKey(p,"remark")))
				.append(field(":zip_size ",hasKey(p,"zip_size")))
				.append(field(":img_count ",hasKey(p,"img_count")))
				.append(field(":vedio_count ",hasKey(p,"vedio_count")))
				.append(field(":undefied_count ",hasKey(p,"undefied_count")))
				.append(field(":batchurl ",hasKey(p,"batchurl")))
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
		String sql= Sql.create("delete from T_FILE_INFO where 1=1 ")
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
		String sql= Sql.create("delete from T_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(and("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(and("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(and("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(and("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(and("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}	

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql= Sql.create("update T_FILE_INFO set ")
				.append(field("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(field("txcode = :txcode",hasKey(p,"txcode")))
				.append(field("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(field("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(field("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(field("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(field("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(field("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("zip_size = :zip_size",hasKey(p,"zip_size")))
				.append(field("img_count = :img_count",hasKey(p,"img_count")))
				.append(field("vedio_count = :vedio_count",hasKey(p,"vedio_count")))
				.append(field("undefied_count = :undefied_count",hasKey(p,"undefied_count")))
				.append(field("batchurl = :batchurl",hasKey(p,"batchurl")))
				.append(" where 1=1 ")
				.append(and("id = :id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);	
		String sql= Sql.create("update T_FILE_INFO set ")
				.append(field("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(field("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(field("txcode = :txcode",hasKey(p,"txcode")))
				.append(field("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(field("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(field("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(field("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(field("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(field("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(field("create_time = :create_time",hasKey(p,"create_time")))
				.append(field("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(field("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(field("place = :place",hasKey(p,"place")))
				.append(field("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(field("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(field("batchid = :batchid",hasKey(p,"batchid")))
				.append(field("remark = :remark",hasKey(p,"remark")))
				.append(field("zip_size = :zip_size",hasKey(p,"zip_size")))
				.append(field("img_count = :img_count",hasKey(p,"img_count")))
				.append(field("vedio_count = :vedio_count",hasKey(p,"vedio_count")))
				.append(field("undefied_count = :undefied_count",hasKey(p,"undefied_count")))
				.append(" where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(and("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(and("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(and("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(and("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(and("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(and("zip_size = :zip_size",hasKey(p,"zip_size")))
				.append(and("img_count = :img_count",hasKey(p,"img_count")))
				.append(and("vedio_count = :vedio_count",hasKey(p,"vedio_count")))
				.append(and("undefied_count = :undefied_count",hasKey(p,"undefied_count")))
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
		String sql= Sql.create("select * from T_FILE_INFO where 1=1")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(and("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(and("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(and("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(and("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(and("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
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
		String sql= Sql.create("select * from T_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(and("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(and("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(and("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(and("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(and("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from T_FILE_INFO where 1=1 ")
				.append(and("id = :id",hasKey(p,"id")))
				.append(and("flow_no = :flow_no",hasKey(p,"flow_no")))
				.append(and("busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(and("txcode = :txcode",hasKey(p,"txcode")))
				.append(and("tx_user = :tx_user",hasKey(p,"tx_user")))
				.append(and("generate_user = :generate_user",hasKey(p,"generate_user")))
				.append(and("auth_user = :auth_user",hasKey(p,"auth_user")))
				.append(and("oper_user = :oper_user",hasKey(p,"oper_user")))
				.append(and("generate_time = :generate_time",hasKey(p,"generate_time")))
				.append(and("generate_time1 = :generate_time1",hasKey(p,"generate_time1")))
				.append(and("create_time = :create_time",hasKey(p,"create_time")))
				.append(and("oper_time = :oper_time",hasKey(p,"oper_time")))
				.append(and("local_file_path = :local_file_path",hasKey(p,"local_file_path")))
				.append(and("place = :place",hasKey(p,"place")))
				.append(and("send_counts = :send_counts",hasKey(p,"send_counts")))
				.append(and("send_error_msg = :send_error_msg",hasKey(p,"send_error_msg")))
				.append(and("batchid = :batchid",hasKey(p,"batchid")))
				.append(and("remark = :remark",hasKey(p,"remark")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}

	/**
	 * desc:查询双录逾期需要删除的文件信息
	 * @return
	 */
	public List<Map<String, Object>> queryNeedDelList(Map<String,Object> p) {
		String sql= Sql.create("SELECT * FROM T_FILE_INFO WHERE FLOW_NO IN (select FLOW_NO from T_FIN_TRANS_SERIAL where txcode = 'COM000040' AND (STATUS = '6' or (STATUS = '7' and TRAN_DATE < '"+p.get("tran_date")+"' )))")
				.toString();
		printSql(sql,p);
		return queryForList(sql,null);
	}
	
	/**
	 * desc:查询三个月以前未删除已上传的本地文件信息
	 * @return
	 */
	public List<Map<String, Object>> queryThreeMNeedDelList(Map<String,Object> p) {
		String sql= Sql.create("SELECT fi.* from T_FILE_INFO fi LEFT JOIN T_FIN_TRANS_SERIAL ft on fi.FLOW_NO = ft.FLOW_NO where ft.TRAN_DATE < '"+p.get("tran_date")+"' and fi.PLACE = 'LOCAL' and fi.BATCHID is not null ")
				.toString();
		printSql(sql,p);
		return queryForList(sql,null);
	}
	
	/**
	 * desc:查询从未发送到文件系统的记录 和 发送失败但是尝试发送次数小于配置的最大发送次数的记录
	 * @return
	 */
	public List<Map<String, Object>> queryNeedSendList(int maxSendCount) {
		String sql= Sql.create("select * from T_FILE_INFO where 1=1")
				.append(" and ((batchid is null and (send_counts is null or send_counts < 1))") //未发送数据
				.append(" or (batchid is null and send_error_msg is not null and send_counts <"+maxSendCount+")) ") //发送失败的数据，但尝试发送次数小于最大尝试次数
				.append(" and FLOW_NO in (select FLOW_NO from T_FIN_TRANS_SERIAL where STATUS='1' or STATUS ='2' ) ")
				.toString();
		printSql(sql,null);
		return queryForList(sql,null);
	}

	public List<Map<String, Object>> queryForListByBusiId(Map<String,Object> p) {
		String sql= Sql.create("select * from T_FILE_INFO where 1=1")
				.append(and(" busi_seq_no = :busi_seq_no",hasKey(p,"busi_seq_no")))
				.append(" order by CREATE_TIME desc ")
				.toString();
		printSql(sql,p);
		return queryForList(sql, p);
	}

	/**
	 * desc:根据remark查询文件列表
	 * @return
	 */
	public List<Map<String, Object>> queryInfoByHeadCaseid(String remark) {
		String sql= Sql.create("select * from T_FILE_INFO where 1=1")
				.append(" and remark = '" + remark + "'") //发送失败的数据，但尝试发送次数小于最大尝试次数
				.toString();
		return queryForList(sql,null);
	}

}
