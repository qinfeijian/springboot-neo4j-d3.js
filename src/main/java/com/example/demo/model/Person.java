package com.example.demo.model;


import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.example.demo.base.NamedEntity;
import com.example.demo.model.relationships.ReaderOf;
import com.example.demo.model.relationships.WriterOf;


/**
 * Entity which represents a person
 */
@NodeEntity
public class Person extends NamedEntity {
   @Relationship(type="WRITER_OF")
   private Set<WriterOf> writings;

   @Relationship(type="READER_OF")
   private Set<ReaderOf> readings;

   public Set<WriterOf> getWritings() {
      return writings;
   }

   public void setWritings(Set<WriterOf> writings) {
      this.writings = writings;
   }

  

   public Set<ReaderOf> getReadings() {
	   return readings;
   }

   public void setReadings(Set<ReaderOf> readings) {
	   this.readings = readings;
   }

	@Override
	public String toString() {
		return "{\"id\": \"" + this.getId() +"\""
				+", \"name\": \"" + this.getName() +"\""
				+", \"type\": \"" + this.getClass().getName() +"\""
				+ " }";
	}

   
	   
   
   
}