package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PersonManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
	
	@Autowired
	private PersonManager manager;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<ObjectNode> findAllNodesAndLinks() {
		ObjectMapper objectMapper = new ObjectMapper();
		final ObjectNode jsonObject = objectMapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			String data = manager.findAllNodesAndLinks();
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "查询成功！");
			jsonObject.put("data", data);
		} catch(Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败:" + e.getMessage());
		}
		return new ResponseEntity<>(jsonObject, status);
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
	public ResponseEntity<ObjectNode> findNodesAndLinksByPersonName(@PathVariable("name") String name) {
		ObjectMapper objectMapper = new ObjectMapper();
		final ObjectNode jsonObject = objectMapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			String data = manager.findNodesAndLinksByPersonName(name);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "查询成功！");
			jsonObject.put("data", data);
		} catch(Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败:" + e.getMessage());
		}
		return new ResponseEntity<>(jsonObject, status);
	}
}
