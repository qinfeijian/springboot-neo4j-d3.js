package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Person;
import com.example.demo.model.relationships.ReaderOf;
import com.example.demo.model.relationships.WriterOf;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonManager;

@Scope("prototype")
@Service
@Transactional
public class PersonManagerImpl implements PersonManager {
	
	@Autowired
	private PersonRepository dao;
	
	/**
	 * 传入用户列表获取所有节点和关系，以json字符串格式返回
	 * {"nodes":[...], "links":[...]}
	 * @param pList
	 * @return json字符串
	 */
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

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Person findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	

	@Override
	public String findAllNodesAndLinks() {
		// TODO Auto-generated method stub
		return addNodesAndLinks(dao.findAll());
	}

	@Override
	public String findNodesAndLinksByPersonName(String name) {
		// TODO Auto-generated method stub
		List<Person> pList = new ArrayList<Person>();
		pList.add(dao.findByName(name));
		return addNodesAndLinks(pList);
	}
}
