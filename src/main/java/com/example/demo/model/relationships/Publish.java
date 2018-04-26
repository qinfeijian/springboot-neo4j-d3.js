package com.example.demo.model.relationships;


import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.example.demo.base.AbstractEntity;
import com.example.demo.model.Book;
import com.example.demo.model.Press;


@RelationshipEntity(type="出版")
public class Publish extends AbstractEntity {
   @StartNode
   private Press press;

   @EndNode
   private Book book;

   private Date startDate;

   private Date endDate;


   public Press getPress() {
		return press;
	}
	
	public void setPress(Press press) {
		this.press = press;
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
				+", \"TYPE\": \"出版\""
				+", \"source\": \"" + this.getPress().getId() +"\""
				+", \"target\": \"" + this.getBook().getId() +"\""
				+", \"startDate\": \"" + this.getStartDate() +"\""
				+", \"endtDate\": \"" + this.getEndDate() +"\""
				+ " }";
   }
   
}