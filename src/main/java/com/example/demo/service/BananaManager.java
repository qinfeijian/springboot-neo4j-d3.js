package com.example.demo.service;

import java.util.List;

import net.sf.json.JSONObject;

public interface BananaManager {
	
	public JSONObject getPathByLimit(Integer limit);
	
	public JSONObject getNodesByNameLike(String name);

}
