package com.example.demo.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.BananaManager;
import com.example.demo.utils.JdbcUtil;
import com.example.demo.utils.StringUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@Service
@Transactional
public class BananaManagerImpl implements BananaManager {

	@Autowired
	private JdbcUtil jdbcUtil;
	
	
	
	@Override
	public JSONObject getPathByLimit(Integer limit) {
		
		// TODO Auto-generated method stub
		String sql = "MATCH (a)-[r]->(b) RETURN a,b,r LIMIT {1} ";
		try {
			jdbcUtil.getConnection();
			return jdbcUtil.getDataByCql(sql, limit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (null != jdbcUtil) {
				jdbcUtil.close();
			}
		}
		return null;
	}



	@Override
	public JSONObject getNodesByNameLike(String name) {
		
		// TODO Auto-generated method stub
		try {
			jdbcUtil.getConnection();
			String cql = "match (a) where a.name contains {1}  return a";
			if (StringUtil.isEmpty(name)) {
				cql =  "match (a)  return a";
				return jdbcUtil.getDataByCql(cql);
			}
			return jdbcUtil.getDataByCql(cql, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (null != jdbcUtil) {
				jdbcUtil.close();
			}
		}
		return null;
	}

}
