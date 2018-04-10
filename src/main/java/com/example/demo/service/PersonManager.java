package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Person;

public interface PersonManager {
	
	public List<Person> findAll();
	
	public Person findByName(String name);

	public String findAllNodesAndLinks();
	
	public String findNodesAndLinksByPersonName(String name);
}
