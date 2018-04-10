package com.example.demo.model.relationships;


import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.example.demo.base.AbstractEntity;
import com.example.demo.model.Book;
import com.example.demo.model.Person;


@RelationshipEntity(type="WRITER_OF")
public class WriterOf extends AbstractEntity {
   @StartNode
   private Person writer;

   @EndNode
   private Book book;

   private Date startDate;

   private Date endDate;

   public Person getWriter() {
      return writer;
   }

   public void setWriter(Person writer) {
      this.writer = writer;
   }

   public Book getBook() {
      return book;
   }

   public void setBook(Book book) {
      this.book = book;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }
   
   @Override
   public String toString() {
		return "{\"id\": \"" + this.getId() +"\""
				+", \"TYPE\": \"WRITER_OF\""
				+", \"source\": \"" + this.getWriter().getId() +"\""
				+", \"target\": \"" + this.getBook().getId() +"\""
				+", \"startDate\": \"" + this.getStartDate() +"\""
				+", \"endtDate\": \"" + this.getEndDate() +"\""
				+ " }";
   }
   
}