package com.example.demo;

import org.neo4j.cypher.internal.ExecutionEngine;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;

import static org.neo4j.driver.v1.Values.parameters;

import java.util.Iterator;
import java.util.List;

public class SmallExample
{
    // Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public SmallExample(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    private void addPerson(String name)
    {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            try (Transaction tx = session.beginTransaction())
            {
                tx.run("MERGE (a:Person {name: {x}})", parameters("x", name));
                tx.success();  // Mark this write as successful.
            }
        }
    }

    private void printPeople(String initial)
    {
        Session session = driver.session();
        // Auto-commit transactions are a quick and easy way to wrap a read.
        StatementResult result = session.run(
                "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name",
                parameters("x", initial));
        // Each Cypher execution returns a stream of records.
        while (result.hasNext())
        {
        	// record是一行记录 取决于你return的是什么 
            Record record = result.next();
            // Values can be extracted from a record by index or name.
            System.out.println(record.get("name").asString());
        }
    }
    
    private void getAllData()
    {
        Session session = driver.session();
        // Auto-commit transactions are a quick and easy way to wrap a read.
        StatementResult result = session.run(
                "MATCH p=(:Person)-[]-()  RETURN p");
        // Each Cypher execution returns a stream of records.
        while (result.hasNext())
        {
        	// record是一行记录 取决于你return的是什么 
            Record record = result.next();
            List<Value> values = record.values();
            values.stream().forEachOrdered(v ->{
            	Path asPath = v.asPath();
            	Node start = asPath.start();
            	Node end = asPath.end();
            	Iterable<Relationship> relationship = asPath.relationships();
            	Iterator<Relationship> rite = relationship.iterator();
            	System.out.print("{");
        		System.out.print("id:" + start.id() + ", ");
        		System.out.print("labels:" + start.labels());
        		for (String key : start.keys()) {
        			
        			System.out.print(", " + key + ":" + start.get(key));
        		}
        		System.out.println("}");
        		while (rite.hasNext()) {
        			Relationship next = rite.next();
        			System.out.print("{");
        			System.out.print("id:" + next.id() + ", ");
        			System.out.print("type:" + next.type());
        			Iterable<String> keys = next.keys();
        			for (String key : keys) {
        				
        				System.out.print(", " + key + ":" +next.get(key));
        			}
        			System.out.println("}");
        		}
        		System.out.print("{");
        		System.out.print("id:" + end.id() + ", ");
        		System.out.print("labels:" + end.labels());
        		for (String key : start.keys()) {
        			
        			System.out.print(", " + key + ":" + start.get(key));
        		}
        		System.out.println("}");
        		System.out.println();
            	
            });
        }
    }

    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }

//    public static void main(String... args)
//    {
//        SmallExample example = new SmallExample("bolt://192.168.1.81:7687", "neo4j", "root");
////        example.addPerson("Ada");
////        example.addPerson("Alice");
////        example.addPerson("Bob");
////        example.printPeople("A");
//        example.getAllData();
//        example.close();
//    }
}