package com.example.demo;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Person;
import com.example.demo.model.relationships.ReaderOf;
import com.example.demo.model.relationships.WriterOf;
import com.example.demo.utils.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoNeo4jApplicationTests {

	
	
	
	
	@Test
	public void contextLoads() {
		
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		// 查询点
		String sql1 = "MATCH (n) RETURN n LIMIT {1} ";
		// 查询线
		String sql2 = "MATCH ()-[r]->() RETURN r LIMIT {1} ";
		// 查询所有
		String sql3 = "MATCH (a)-[r]->(b) RETURN a,b,r LIMIT {1} ";
		try {
			List<Map<String, Object>> result = jdbcUtil.findList(sql3, 2);
			JSONObject pointLineJson = getPointLineJson(result);
			System.out.println(pointLineJson);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != jdbcUtil) {
				jdbcUtil.close();
			}
		}
		System.out.println("OVER!");
		
		
		
	}
	/** 封装线 */
	private static JSONObject getPointLineJson(List<Map<String, Object>> result) {
		Map<Integer, Map<String, Object>> pointMap = new HashMap<Integer, Map<String, Object>>();
		Map<Integer, Map<String, Object>> lineMap = new HashMap<Integer, Map<String, Object>>();

		for (Map<String, Object> resultMap : result) {
			Set<String> keySet = resultMap.keySet();
			for (String key : keySet) {
				Map<String, Object> resulValueMap = (Map<String, Object>) resultMap.get(key);
				Integer id = Integer.parseInt(String.valueOf(resulValueMap.get("_id")));
				// 如果是关系 那么它没有_labels属性
				if (null == resulValueMap.get("_labels")) {
					if (null == pointMap.get(id)) {
						String source = resulValueMap.get("_startId").toString();
						String target = resulValueMap.get("_endId").toString();
						resulValueMap.put("id", id);
						resulValueMap.put("source", source);
						resulValueMap.put("target", target);
						resulValueMap.remove("_startId");
						resulValueMap.remove("_endId");
						resulValueMap.remove("_id");
						lineMap.put(id, resulValueMap);
					}
				} else { //节点
					
					if (null == pointMap.get(id)) {
						resulValueMap.put("id", id);
						resulValueMap.remove("_id");
						pointMap.put(id, resulValueMap);
					}
					
				}
			}
		}

		// 转Json
		return mapToJson(lineMap, pointMap);
	}
	/** 转json */
	private static JSONObject mapToJson(Map<Integer, Map<String, Object>> lineMap, Map<Integer, Map<String, Object>> pointMap) {
		JSONObject jsonObject = new JSONObject();
		// 点集合
		// "data":[{"id":66,"category":1,"name":"福建","labels":"Company","symbolSize":40},{"id":68,"category":1,"name":"湖北","labels":"Company","symbolSize":40}]
		jsonObject.element("data", pointMap.values());
		// 线集合
		// "links":[{"source":'7',"target":'2'},{"source":'3',"target":'2'}]
		jsonObject.element("links", lineMap.values());
		return jsonObject;
	}
	
	public String addNodesAndLinks(List<Person> pList) {
		StringBuffer result = new StringBuffer();
		Set<String> nodeSet = new HashSet<String>();
		Set<String> linksSet = new HashSet<String>();
		StringBuffer nodes = new StringBuffer();
		StringBuffer links = new StringBuffer();
		nodes.append("\"nodes\": [");
		links.append("\"links\": [");
		
		pList.stream().forEach(person -> {
			
			nodeSet.add(person.toString());
			Set<WriterOf> writings = person.getWritings();
			Iterator<WriterOf> iterator = writings.iterator();
			while (iterator.hasNext()) {
				WriterOf next = iterator.next();
				nodeSet.add(next.getBook().toString());
				linksSet.add(next.toString());
				
			}
			
			Iterator<ReaderOf> readiter = person.getReadings().iterator();
			while (readiter.hasNext()) {
				ReaderOf next = readiter.next();
				nodeSet.add(next.getBook().toString());
				linksSet.add(next.toString());
			}
			
		});
		Iterator<String> nset = nodeSet.iterator();
		while (nset.hasNext()) {
			nodes.append(nset.next()).append(",");
		}
		Iterator<String> lset = linksSet.iterator();
		while (lset.hasNext()) {
			links.append(lset.next()).append(",");
		}
		
		String str = nodes.substring(0, nodes.length() - 1);
		str += "]";
		
		String linksStr = links.substring(0, links.length() - 1);
		linksStr += "]";
		result.append("{")
		.append(str)
		.append(",")
		.append(linksStr)
		.append("}");
		
		return result.toString();
	}
}
