package com.example.demo.model;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import lombok.Data;

@NodeEntity
@Data
public class Group {
    @GraphId
    private Long id;
    
    
    private String name;

    
}