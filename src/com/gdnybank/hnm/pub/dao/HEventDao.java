package com.gdnybank.hnm.pub.dao;

import java.util.List;
import java.util.Map;
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
 * 表名:H_EVENT
 * 主键:
 * event_id
 **/
@Repository
public class HEventDao extends TXBaseDao{

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"event_id","account_id","site_id","event_name","event_date","event_way","is_card","event_staff","event_effect","event_photo","event_other","remark1","remark2","remark3","remark4"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"event_id"};

	@Override
	public int save(Map<String,Object> p) {
				p.put("event_id",sequenceService.getTableFlowNo("H_EVENT", "event_id"));
		String sql=Sql.create("insert into H_EVENT (")
				.append(field("event_id "))
				.append(field("account_id ",hasKey(p,"account_id")))
				.append(field("site_id ",hasKey(p,"site_id")))
				.append(field("event_name ",hasKey(p,"event_name")))
				.append(field("event_date ",hasKey(p,"event_date")))
				.append(field("event_way ",hasKey(p,"event_way")))
				.append(field("is_card ",hasKey(p,"is_card")))
				.append(field("event_staff ",hasKey(p,"event_staff")))
				.append(field("event_effect ",hasKey(p,"event_effect")))
				.append(field("event_photo ",hasKey(p,"event_photo")))
				.append(field("event_other ",hasKey(p,"event_other")))
				.append(field("remark1 ",hasKey(p,"remark1")))
				.append(field("remark2 ",hasKey(p,"remark2")))
				.append(field("remark3 ",hasKey(p,"remark3")))
				.append(field("remark4 ",hasKey(p,"remark4")))
				.append(") values (")
				.append(field(":event_id "))
				.append(field(":account_id ",hasKey(p,"account_id")))
				.append(field(":site_id ",hasKey(p,"site_id")))
				.append(field(":event_name ",hasKey(p,"event_name")))
				.append(field(":event_date ",hasKey(p,"event_date")))
				.append(field(":event_way ",hasKey(p,"event_way")))
				.append(field(":is_card ",hasKey(p,"is_card")))
				.append(field(":event_staff ",hasKey(p,"event_staff")))
				.append(field(":event_effect ",hasKey(p,"event_effect")))
				.append(field(":event_photo ",hasKey(p,"event_photo")))
				.append(field(":event_other ",hasKey(p,"event_other")))
				.append(field(":remark1 ",hasKey(p,"remark1")))
				.append(field(":remark2 ",hasKey(p,"remark2")))
				.append(field(":remark3 ",hasKey(p,"remark3")))
				.append(field(":remark4 ",hasKey(p,"remark4")))
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
		String sql=Sql.create("delete from H_EVENT where 1=1 ")
				.append(and("event_id = :event_id"))
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
		String sql=Sql.create("delete from H_EVENT where 1=1 ")
				.append(and("event_id = :event_id",hasKey(p,"event_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("event_name = :event_name",hasKey(p,"event_name")))
				.append(and("event_date = :event_date",hasKey(p,"event_date")))
				.append(and("event_way = :event_way",hasKey(p,"event_way")))
				.append(and("is_card = :is_card",hasKey(p,"is_card")))
				.append(and("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(and("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(and("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(and("event_other = :event_other",hasKey(p,"event_other")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("remark4 = :remark4",hasKey(p,"remark4")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}

	@Override
	public int updateByPk(Map<String,Object> p) {
		String sql=Sql.create("update H_EVENT set ")
				.append(field("account_id = :account_id",hasKey(p,"account_id")))
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("event_name = :event_name",hasKey(p,"event_name")))
				.append(field("event_date = :event_date",hasKey(p,"event_date")))
				.append(field("event_way = :event_way",hasKey(p,"event_way")))
				.append(field("is_card = :is_card",hasKey(p,"is_card")))
				.append(field("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(field("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(field("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(field("event_other = :event_other",hasKey(p,"event_other")))
				.append(field("remark1 = :remark1",hasKey(p,"remark1")))
				.append(field("remark2 = :remark2",hasKey(p,"remark2")))
				.append(field("remark3 = :remark3",hasKey(p,"remark3")))
				.append(field("remark4 = :remark4",hasKey(p,"remark4")))
				.append(" where 1=1 ")
				.append(and("event_id = :event_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}

	@Override
	public int update(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);
		String sql=Sql.create("update H_EVENT set ")
				.append(field("account_id = :account_id",hasKey(p,"account_id")))
				.append(field("site_id = :site_id",hasKey(p,"site_id")))
				.append(field("event_name = :event_name",hasKey(p,"event_name")))
				.append(field("event_date = :event_date",hasKey(p,"event_date")))
				.append(field("event_way = :event_way",hasKey(p,"event_way")))
				.append(field("is_card = :is_card",hasKey(p,"is_card")))
				.append(field("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(field("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(field("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(field("event_other = :event_other",hasKey(p,"event_other")))
				.append(field("remark1 = :remark1",hasKey(p,"remark1")))
				.append(field("remark2 = :remark2",hasKey(p,"remark2")))
				.append(field("remark3 = :remark3",hasKey(p,"remark3")))
				.append(field("remark4 = :remark4",hasKey(p,"remark4")))
				.append(" where 1=1 ")
				.append(and("event_id = :event_id",hasKey(p,"event_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("event_name = :event_name",hasKey(p,"event_name")))
				.append(and("event_date = :event_date",hasKey(p,"event_date")))
				.append(and("event_way = :event_way",hasKey(p,"event_way")))
				.append(and("is_card = :is_card",hasKey(p,"is_card")))
				.append(and("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(and("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(and("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(and("event_other = :event_other",hasKey(p,"event_other")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("remark4 = :remark4",hasKey(p,"remark4")))
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
		String sql=Sql.create("select e.*,a.NAME ACCOUNT_NAME,s.SITE_NAME from H_EVENT e " +
				"left join SYS_ACCOUNT a on e.ACCOUNT_ID=a.ACCOUNT_ID " +
				"left join H_SITE s on s.site_no = e.SITE_ID " +
				"left join  T_APPROVAL_APPLY p on p.relation_id=s.id " +
				"where 1=1 and s.is_delete=0  and p.approval_status=2 ")
				.append(and("e.event_id = :event_id",hasKey(p,"event_id")))
				.append(and("e.account_id = :account_id",hasKey(p,"account_id")))
				.append(and("e.site_id = :site_id",hasKey(p,"site_id")))
				.append(and("e.event_name = :event_name",hasKey(p,"event_name")))
				.append(and("e.event_date = :event_date",hasKey(p,"event_date")))
				.append(and("e.event_way = :event_way",hasKey(p,"event_way")))
				.append(and("e.is_card = :is_card",hasKey(p,"is_card")))
				.append(and("e.event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(and("e.event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(and("e.event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(and("e.event_other = :event_other",hasKey(p,"event_other")))
				.append(and("e.remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("e.remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("e.remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("e.remark4 = :remark4",hasKey(p,"remark4")))
				.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}

	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {
		Sql sql=Sql.create("select e.*,a.NAME ACCOUNT_NAME,s.SITE_NAME from H_EVENT e " +
				"left join SYS_ACCOUNT a on e.ACCOUNT_ID=a.ACCOUNT_ID " +
				"left join H_SITE s on s.site_no = e.SITE_ID " +
				"left join  T_APPROVAL_APPLY p on p.relation_id=s.id " +
				"where s.is_delete=0 and p.approval_status=2 ")
			.append(and("e.event_id = :event_id",hasKey(p,"event_id")))
						.append(and("e.account_id = :account_id",hasKey(p,"account_id")))
						.append(and("e.site_id = :site_id",hasKey(p,"site_id")))
						.append(and("e.event_name = :event_name",hasKey(p,"event_name")))
						.append(and("e.event_date = :event_date",hasKey(p,"event_date")))
						.append(and("e.event_date >= :event_start_date",hasKey(p,"event_start_date")))
						.append(and("e.event_date <= :event_end_date",hasKey(p,"event_end_date")))
						.append(and("e.event_way = :event_way",hasKey(p,"event_way")))
						.append(and("e.is_card = :is_card",hasKey(p,"is_card")))
						.append(and("e.event_staff = :event_staff",hasKey(p,"event_staff")))
						.append(and("e.event_effect = :event_effect",hasKey(p,"event_effect")))
						.append(and("e.event_photo = :event_photo",hasKey(p,"event_photo")))
						.append(and("e.event_other = :event_other",hasKey(p,"event_other")))
						.append(and("e.remark1 = :remark1",hasKey(p,"remark1")))
						.append(and("e.remark2 = :remark2",hasKey(p,"remark2")))
						.append(and("e.remark3 = :remark3",hasKey(p,"remark3")))
						.append(and("e.remark4 = :remark4",hasKey(p,"remark4")))
			;

		if(p.containsKey("orgids")&&p.get("orgids")!=null) {
			sql.append(and("a.branch_id in ("+ p.get("orgids")+")"));
		}

		String sqlStr=sql.toString();
		printSql(sqlStr,p);
		//获取数据库类型
		String dbType =  MfpContextHolder.getProps("jdbc.driverClassName");
		if ("oracle.jdbc.driver.OracleDriver".equals(dbType) || "oracle.jdbc.driver.OracleDriver" == dbType){
			long count = count("select count(*) from ("+sqlStr +")  ", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sqlStr=pageSqlInOracle(sql.append(" order by e.event_date desc ").toString());
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
		}else{
			long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
			PageInfo  pageInf = MfpContextHolder.getPageInfo();
			pageInf.setITotalDisplayRecords(count);
			MfpContextHolder.setPageInfo(pageInf);
			sql.append(" order by e.event_date desc ").append(pageSql());
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
		String sql=Sql.create("select * from H_EVENT where 1=1 ")
				.append(and("event_id = :event_id",hasKey(p,"event_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("event_name = :event_name",hasKey(p,"event_name")))
				.append(and("event_date = :event_date",hasKey(p,"event_date")))
				.append(and("event_way = :event_way",hasKey(p,"event_way")))
				.append(and("is_card = :is_card",hasKey(p,"is_card")))
				.append(and("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(and("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(and("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(and("event_other = :event_other",hasKey(p,"event_other")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("remark4 = :remark4",hasKey(p,"remark4")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from H_EVENT where 1=1 ")
				.append(and("event_id = :event_id",hasKey(p,"event_id")))
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("site_id = :site_id",hasKey(p,"site_id")))
				.append(and("event_name = :event_name",hasKey(p,"event_name")))
				.append(and("event_date = :event_date",hasKey(p,"event_date")))
				.append(and("event_way = :event_way",hasKey(p,"event_way")))
				.append(and("is_card = :is_card",hasKey(p,"is_card")))
				.append(and("event_staff = :event_staff",hasKey(p,"event_staff")))
				.append(and("event_effect = :event_effect",hasKey(p,"event_effect")))
				.append(and("event_photo = :event_photo",hasKey(p,"event_photo")))
				.append(and("event_other = :event_other",hasKey(p,"event_other")))
				.append(and("remark1 = :remark1",hasKey(p,"remark1")))
				.append(and("remark2 = :remark2",hasKey(p,"remark2")))
				.append(and("remark3 = :remark3",hasKey(p,"remark3")))
				.append(and("remark4 = :remark4",hasKey(p,"remark4")))
				.toString();
		printSql(sql,p);
		return count(sql, p);
	}


}
