package com.example.demo.model;

import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;

import com.example.demo.base.BananaIndustryChain;
import com.example.demo.model.relationships.SubIndustry;


public class NodeBean extends BananaIndustryChain {
	@Relationship(type = "下游产业")
	private Set<SubIndustry> subIndustries;


	public Set<SubIndustry> getSubIndustries() {
		return subIndustries;
	}


	public void setSubIndustries(Set<SubIndustry> subIndustries) {
		this.subIndustries = subIndustries;
	}


	@Override
	public String toString() {
		return "{\"id\": \"" + this.getId() + "\"" + ", \"name\": \"" + this.getName() + "\"" + ", \"description\": \""
				+ this.getDescription() + "\"" + ", \"type\": \"" + this.getClass().getName() + "\"" + " }";
	}

}