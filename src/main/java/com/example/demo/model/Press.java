package com.example.demo.model;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.example.demo.base.DescriptiveEntity;
import com.example.demo.model.relationships.Publish;

/**
 * Entity which represents a book
 */
@NodeEntity
public class Press extends DescriptiveEntity {
	@Relationship(type = "出版")
	private Set<Publish> publishes;

	public Set<Publish> getPublishes() {
		return publishes;
	}

	public void setPublishes(Set<Publish> publishes) {
		this.publishes = publishes;
	}

	@Override
	public String toString() {
		return "{\"id\": \"" + this.getId() + "\"" + ", \"name\": \"" + this.getName() + "\"" + ", \"description\": \""
				+ this.getDescription() + "\"" + ", \"type\": \"" + this.getClass().getName() + "\"" + " }";
	}

}