package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BananaManager;
import com.example.demo.service.PersonManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/banana")
public class BananaController {
	
	@Autowired
	private BananaManager manager;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<ObjectNode> findAllNodesAndLinks() {
		ObjectMapper objectMapper = new ObjectMapper();
		final ObjectNode jsonObject = objectMapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			String data = manager.getPathByLimit(100).toString();
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
	@RequestMapping(value = "/findNodeByNameLike")
	public ResponseEntity<ObjectNode> findAllNodesByNameLike(HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		final ObjectNode jsonObject = objectMapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			String nodeName = request.getParameter("nodeName");
			String data = manager.getNodesByNameLike(nodeName).toString();
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
