package com.example.demo.model.relationships;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.example.demo.base.NamedEntity;
import com.example.demo.model.NodeBean;

@RelationshipEntity(type = "下游产业")
public class SubIndustry extends NamedEntity {
	@StartNode
	private NodeBean startNode;

	@EndNode
	private NodeBean endNode;


	private Date startDate;

	private Date endDate;
	
	

	public NodeBean getStartNode() {
		return startNode;
	}

	public void setStartNode(NodeBean startNode) {
		this.startNode = startNode;
	}

	public NodeBean getEndNode() {
		return endNode;
	}

	public void setEndNode(NodeBean endNode) {
		this.endNode = endNode;
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
		return "{\"id\": \"" + this.getId() + "\"" + ", \"TYPE\": \"出版\"" + ", \"source\": \"" + this.getStartNode().getId()
				+ "\"" + ", \"target\": \"" + this.getEndNode().getId() + "\"" + ", \"startDate\": \""
				+ this.getStartDate() + "\"" + ", \"endtDate\": \"" + this.getEndDate() + "\"" + " }";
	}

}