package com.gdnybank.hnm.pub.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.pub.dao.TXBaseDao;

@Repository
public class HnmCommDao extends TXBaseDao {

	/**
	 * 根据传入的参数实现批量查询
	 *
	 * @param tableName
	 *            目标表名
	 * @param columns
	 *            列名数组
	 * @param batchValues
	 *            批量插入的数据集合
	 * @return 执行成功的条数
	 * @author chenhao
	 */
	@Transactional
	public int[] batchUpdate(String tableName, String[] columns, List<Map<String, Object>> batchValues) {
		int size = batchValues.get(0).size();
		// 如果传入的列的数量和传入的单条数据的个数不相符，抛错返回
		if (columns.length != size) {
			throw new BusinessException("hnmCommDao001", "传入的列的数量和传入的单条数据的个数不相符");
		}
		String str = "";
		for (int i = 0; i < columns.length; i++) {
			str += ":" + columns[i] + " ,";
		}
		str = str.substring(0, str.length() - 1);
		String sql = "insert into " + tableName + " values (" + str + ")";
		logger.info("本次HnmCommDao.batchUpdate()执行的sql： " + sql);
		Map<String, Object>[] paramMap = new Map[batchValues.size()];
		for (int i = 0; i < batchValues.size(); i++) {
			paramMap[i] = batchValues.get(i);
		}
		return super.getJdbcTemplateForMap().batchUpdate(sql, paramMap);
	}

	public void dropTable(String tableName){
		super.update("DROP TABLE "+tableName, null);
	}

	public void modifyTableName(String oldTableName,String newTableName){
		super.update("ALTER TABLE "+oldTableName+" RENAME TO "+newTableName, null);
	}


	/**
	 * 执行一条sql语句
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public int executeSql(String sql,Map<String, Object> paramMap){
		return super.getJdbcTemplateForMap().update(sql, paramMap);
	}

}
