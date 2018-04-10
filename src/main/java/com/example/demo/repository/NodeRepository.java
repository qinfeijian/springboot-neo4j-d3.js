package com.example.demo.repository;


import java.util.List;

import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;


/**
 * The repository to perform CRUD operations on person entities
 */
public interface NodeRepository extends Neo4jRepository<Node, Long> {
	
	@Query("match p=(:Person{name:'李四'})-[*1..2]-(b) return p")
	List<Node> test1();
	
}