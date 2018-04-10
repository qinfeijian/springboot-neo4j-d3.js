package com.example.demo.repository;



import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.model.Book;


/**
 * The repository to perform CRUD operations on book entities
 */
public interface BookRepository extends Neo4jRepository<Book, Long> {
	
	Book findByName(String name);
	
}