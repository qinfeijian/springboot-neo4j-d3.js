package com.example.demo.model;


import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.example.demo.base.DescriptiveEntity;
import com.example.demo.model.relationships.WriterOf;


/**
 * Entity which represents a book
 */
@NodeEntity
public class Book extends DescriptiveEntity {
   @Relationship(type="WRITER_OF", direction=Relationship.INCOMING)
   private Set<WriterOf> writers;

   @Relationship(type="READER_OF", direction=Relationship.INCOMING)
   private Set<Person> readers;

   public Set<WriterOf> getWriters() {
      return writers;
   }

   public void setWriters(Set<WriterOf> writers) {
      this.writers = writers;
   }

   public Set<Person> getReaders() {
      return readers;
   }

   public void setReaders(Set<Person> readers) {
      this.readers = readers;
   }

   @Override
	public String toString() {
		return "{\"id\": \"" + this.getId() +"\""
				+ ", \"name\": \"" + this.getName() +"\""
				+ ", \"description\": \"" + this.getDescription() +"\""
				+", \"type\": \"" + this.getClass().getName() +"\""
				+ " }";
	}

}