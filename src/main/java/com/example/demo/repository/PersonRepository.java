package com.example.demo.repository;


import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Person;

/**
 * The repository to perform CRUD operations on person entities
 */
public interface PersonRepository extends Neo4jRepository<Person, Long> {
	Person findByName(String name);
	
	List<Person> findAll();
	
}