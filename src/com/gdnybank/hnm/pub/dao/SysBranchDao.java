package com.gdnybank.hnm.pub.dao;

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
 * 表名:SYS_BRANCH
 * 主键:branch_id
 **/
@Repository
public class SysBranchDao extends TXBaseDao {

    /**
     * 当前所有字段名
     **/
    String[] fieldNames = new String[]{"branch_id", "branch_name", "porgid", "creator", "create_time", "address", "branch_type", "lng", "lat", "rural_branch_id", "team_flag", "bran_level"};
    /**
     * 当前主键(包括多主键)
     **/
    String[] primaryKeys = new String[]{"branch_id"};

    @Override
    public int save(Map<String, Object> p) {
        String sql = Sql.create("insert into SYS_BRANCH (")
                .append(field("branch_id "))
                .append(field("branch_name ", hasKey(p, "branch_name")))
                .append(field("porgid ", hasKey(p, "porgid")))
                .append(field("creator ", hasKey(p, "creator")))
                .append(field("create_time ", hasKey(p, "create_time")))
                .append(field("address ", hasKey(p, "address")))
                .append(field("branch_type ", hasKey(p, "branch_type")))
                .append(field("lng ", hasKey(p, "lng")))
                .append(field("lat ", hasKey(p, "lat")))
                .append(field("rural_branch_id ", hasKey(p, "rural_branch_id")))
                .append(field("team_flag ", hasKey(p, "team_flag")))
                .append(field("bran_level ", hasKey(p, "bran_level")))
                .append(") values (")
                .append(field(":branch_id "))
                .append(field(":branch_name ", hasKey(p, "branch_name")))
                .append(field(":porgid ", hasKey(p, "porgid")))
                .append(field(":creator ", hasKey(p, "creator")))
                .append(field(":create_time ", hasKey(p, "create_time")))
                .append(field(":address ", hasKey(p, "address")))
                .append(field(":branch_type ", hasKey(p, "branch_type")))
                .append(field(":lng ", hasKey(p, "lng")))
                .append(field(":lat ", hasKey(p, "lat")))
                .append(field(":rural_branch_id ", hasKey(p, "rural_branch_id")))
                .append(field(":team_flag ", hasKey(p, "team_flag")))
                .append(field(":bran_level ", hasKey(p, "bran_level")))
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
        String sql = Sql.create("delete from SYS_BRANCH where 1=1 ")
                .append(and("branch_id = :branch_id"))
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
        String sql = Sql.create("delete from SYS_BRANCH where 1=1 ")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")))
                .toString();
        printSql(sql, p);
        return delete(sql, p);
    }

    @Override
    public int updateByPk(Map<String, Object> p) {
        String sql = Sql.create("update SYS_BRANCH set ")
                .append(field("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(field("porgid = :porgid", hasKey(p, "porgid")))
                .append(field("creator = :creator", hasKey(p, "creator")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("address =:address", hasKey(p, "address")))
                .append(field("branch_type =:branch_type", hasKey(p, "branch_type")))
                .append(field("lng =:lng", hasKey(p, "lng")))
                .append(field("lat =:lat", hasKey(p, "lat")))
                .append(field("rural_branch_id =:rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(field("team_flag =:team_flag", hasKey(p, "team_flag")))
                .append(field("bran_level =:bran_level", hasKey(p, "bran_level")))
                .append(" where 1=1 ")
                .append(and("branch_id = :branch_id"))
                .toString();
        printSql(sql, p);
        return update(sql, p);
    }

    @Override
    public int update(Map<String, Object> p) {
        checkParameter(p, primaryKeys, fieldNames);
        String sql = Sql.create("update SYS_BRANCH set ")
                .append(field("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(field("porgid = :porgid", hasKey(p, "porgid")))
                .append(field("creator = :creator", hasKey(p, "creator")))
                .append(field("create_time = :create_time", hasKey(p, "create_time")))
                .append(field("address =:address", hasKey(p, "address")))
                .append(field("branch_type =:branch_type", hasKey(p, "branch_type")))
                .append(field("lng =:lng", hasKey(p, "lng")))
                .append(field("lat =:lat", hasKey(p, "lat")))
                .append(field("rural_branch_id =:rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(field("team_flag =:team_flag", hasKey(p, "team_flag")))
                .append(field("bran_level =:bran_level", hasKey(p, "bran_level")))
                .append(" where 1=1 ")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")))
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
        String sql = Sql.create("select * from SYS_BRANCH where 1=1")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    @Override
    public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from SYS_BRANCH where 1=1")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")));

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
        String sql = Sql.create("select * from SYS_BRANCH where 1=1 ")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")))
                .toString();
        printSql(sql, p);
        return queryForMap(sql, p);
    }

    @Override
    public long count(Map<String, Object> p) {
        String sql = Sql.create("select count(*) from SYS_BRANCH where 1=1")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")))
                .toString();
        printSql(sql, p);
        return count(sql, p);
    }


    //批量插入方法
    public int[] saveList(List<Map<String, Object>> paramlist) {

        Sql sql = Sql.create("");
        sql.append("insert into SYS_BRANCH (")
                .append(field("branch_id "))
                .append(field("branch_name "))
                .append(field("porgid "))
                .append(field("creator "))
                .append(field("create_time "))
                .append(field("address "))
                .append(field("branch_type "))
                .append(field("lng "))
                .append(field("lat "))
                .append(field("rural_branch_id "))
                .append(field("team_flag "))
                .append(field("bran_level "))
                .append(") values (")
                .append(field(":branch_id "))
                .append(field(":branch_name "))
                .append(field(":porgid "))
                .append(field(":creator "))
                .append(field(":create_time "))
                .append(field(":address "))
                .append(field(":branch_type "))
                .append(field(":lng "))
                .append(field(":lat "))
                .append(field(":rural_branch_id "))
                .append(field(":team_flag "))
                .append(field(":bran_level "))
                .append(")");


        return batchUpdate(sql.toString(), paramlist);
    }

    /**
     * 根据上级部门id查询部门id
     */
    public List<Map<String, Object>> queryBeforeOrgid(Map<String, Object> p) {
        String sql = "select * from SYS_BRANCH where porgid=:branch_id";
        printSql(sql, p);
        return queryForList(sql, p);
    }

    /**
     * 查询部门
     *
     * @param p
     * @return
     */
    public List<Map<String, Object>> queryForListBycondition(Map<String, Object> p) {
        Sql sql = Sql.create("select * from SYS_BRANCH where 1=1 ")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")));
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("branch_id in (" + p.get("branchids") + ")"));
        }
        //sql.append(orderBySql());
        sql.append(" order by branch_id desc");
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    /**
     * 查询部门 分页
     *
     * @param p
     * @return
     */
    public List<Map<String, Object>> queryForListByconditionByPage(Map<String, Object> p) {
        Sql sql = Sql.create("select * from SYS_BRANCH where 1=1")
                .append(and("branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("porgid = :porgid", hasKey(p, "porgid")))
                .append(and("creator = :creator", hasKey(p, "creator")))
                .append(and("create_time = :create_time", hasKey(p, "create_time")))
                .append(and("address = :address", hasKey(p, "address")))
                .append(and("branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("lng = :lng", hasKey(p, "lng")))
                .append(and("lat = :lat", hasKey(p, "lat")))
                .append(and("rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("bran_level = :bran_level", hasKey(p, "bran_level")));
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("branch_id in (" + p.get("branchids") + ")"));
        }
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        //获取数据库类型
        String dbType = MfpContextHolder.getProps("jdbc.driverClassName");
        if ("oracle.jdbc.driver.OracleDriver".equals(dbType)) {
            long count = count("select count(*) from (" + sqlStr + ")  ", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sqlStr = pageSqlInOracle(sql.append(" order by branch_id asc ").toString());
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        } else {
            long count = count("select count(*) from (" + sqlStr + ")  as t123321", p);
            PageInfo pageInf = MfpContextHolder.getPageInfo();
            pageInf.setITotalDisplayRecords(count);
            MfpContextHolder.setPageInfo(pageInf);
            sql.append(" order by branch_id asc ").append(pageSql());
            sqlStr = sql.toString();
            printSql(sqlStr, p);
            return queryForList(sqlStr, p);
        }
    }

    // 获取法人编号，去重复
    public List<Map<String, Object>> queryRuralBranchId(Map<String, Object> p) {
        String sql = Sql.create(
                "select distinct rural_branch_id from SYS_BRANCH where 1=1")
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    /**
     * 查询部门(包括机构和团队)
     *
     * @param p
     * @return
     */
    public List<Map<String, Object>> queryForListByconditionAll(Map<String, Object> p) {
        Sql sql = Sql.create("select t1.*,t2.branch_name porgname from SYS_BRANCH t1 left join SYS_BRANCH t2 on t1.porgid = t2.branch_id where 1=1")
                .append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("t1.branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("t1.porgid = :porgid", hasKey(p, "porgid")))
                .append(and("t1.creator = :creator", hasKey(p, "creator")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.address = :address", hasKey(p, "address")))
                .append(and("t1.branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("t1.lng = :lng", hasKey(p, "lng")))
                .append(and("t1.lat = :lat", hasKey(p, "lat")))
                .append(and("t1.rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("t1.team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("t1.bran_level = :bran_level", hasKey(p, "bran_level")));
        if (p.containsKey("branchids") && p.get("branchids") != null) {
            sql.append(and("t1.branch_id in (" + p.get("branchids") + ")"));
        }
        //sql.append(orderBySql());
        sql.append(" order by t1.branch_id desc");
        String sqlStr = sql.toString();
        printSql(sqlStr, p);
        return queryForList(sqlStr, p);
    }

    /**
     * 同步“TEMP_BRANCH_INFO”表中的数据，存在则更新，不存在则插入
     *
     * @return
     * @author chenhao
     */
    public int synByTempBranchInfo() {
        String sql = "MERGE INTO SYS_BRANCH T1 USING TEMP_BRANCH_INFO T2 ON (T1.branch_id = T2.bran_code) "
                + "WHEN MATCHED THEN UPDATE SET "
                + "T1.branch_name = T2.bran_name, "
                + "T1.rural_branch_id = T2.lp_ind,"
                + "T1.porgid = T2.adm_bran_code, "
                + "T1.team_flag = '0', "
                + "T1.bran_level = T2.bran_level "
                + "WHEN NOT MATCHED THEN INSERT (branch_id,branch_name,rural_branch_id,porgid,team_flag,bran_level)"
                + "VALUES (T2.bran_code,T2.bran_name,T2.lp_ind,T2.adm_bran_code,'0',T2.bran_level)";
        logger.info(sql);
        return super.update(sql, BaseUtils.map());
    }

    public List<Map<String, Object>> queryForListDetail(Map<String, Object> p) {
        String sql = Sql.create("select t1.*,t2.generate_date,t2.team_leader,t2.remarks,t4.branch_name porgname,(select count(t4.branch_id) team_count from SYS_BRANCH t4 where t4.porgid = t1.branch_id) team_count,(select count(t5.account_id) team_num from SYS_ACCOUNT t5 where t5.branch_id = t1.branch_id and t5.deleted = 0) team_num," +
                " (select count(t6.account_id) area_num from SYS_ACCOUNT t6 where t6.branch_id in (select t7.branch_id from SYS_BRANCH t7 where t1.branch_id = t7.porgid or t1.branch_id = t7.branch_id)) area_num " +
                " from SYS_BRANCH t1 left join H_TEAM t2 on t1.branch_id = t2.branch_id left join T_APPROVAL_APPLY t3 on t2.id = t3.relation_id left join SYS_BRANCH t4 on t1.porgid = t4.branch_id" +
                " where 1=1")
                .append(and("t1.branch_id = :branch_id", hasKey(p, "branch_id")))
                .append(and("t1.branch_name = :branch_name", hasKey(p, "branch_name")))
                .append(and("t1.porgid = :porgid", hasKey(p, "porgid")))
                .append(and("t1.creator = :creator", hasKey(p, "creator")))
                .append(and("t1.create_time = :create_time", hasKey(p, "create_time")))
                .append(and("t1.address = :address", hasKey(p, "address")))
                .append(and("t1.branch_type = :branch_type", hasKey(p, "branch_type")))
                .append(and("t1.lng = :lng", hasKey(p, "lng")))
                .append(and("t1.lat = :lat", hasKey(p, "lat")))
                .append(and("t1.rural_branch_id = :rural_branch_id", hasKey(p, "rural_branch_id")))
                .append(and("t1.team_flag = :team_flag", hasKey(p, "team_flag")))
                .append(and("t1.bran_level = :bran_level", hasKey(p, "bran_level")))
                .append(and("t2.is_delete = :is_delete", hasKey(p, "is_delete")))
                .append(and("t3.approval_status = :approval_status", hasKey(p, "approval_status")))
                .append(orderBySql())
                .toString();
        printSql(sql, p);
        return queryForList(sql, p);
    }

    /**
     * 根据下级部门递归查询本部门及所有上级部门
     * prior 右边是父id，就往父级的方向查询，右边是子id，就往子级的方向查询
     */
    public List<Map<String, Object>> queryAllPorgid(Map<String, Object> p) {
        String sql = "SELECT * FROM SYS_BRANCH START WITH BRANCH_ID = " + p.get("branch_id") + " CONNECT BY nocycle prior PORGID = BRANCH_ID";
        printSql(sql, p);
        return queryForList(sql, p);
    }

}
