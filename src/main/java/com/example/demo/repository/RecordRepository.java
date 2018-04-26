package com.example.demo.repository;


import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.impl.store.record.Record;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.base.BananaIndustryChain;


/**
 * The repository to perform CRUD operations on person entities
 */
public interface RecordRepository extends Neo4jRepository<Object, Long> {
	
	@Query("match p=(a:香蕉产业链)-[r]-(b:香蕉产业链) return p")
	List<Object> test1();
	
	List<Object> findAll();
}