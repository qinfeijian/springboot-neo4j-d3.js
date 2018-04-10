package com.example.demo.repository;


import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Press;

/**
 * The repository to perform CRUD operations on person entities
 */
public interface PressRepository extends Neo4jRepository<Press, Long> {
	
	Press findByName(String name);
	
	List<Press> findAll();
	
}