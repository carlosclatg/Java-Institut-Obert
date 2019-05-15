package com.newthinktank.testDB.Mongo;

import java.util.Arrays;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


@SuppressWarnings("resource")
public class DemoInsert {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
    	MongoDatabase database = mongoClient.getDatabase("skylabbooks");
    	MongoCollection<Document> collection = database.getCollection("books");

    	Document document = new Document("images",Arrays.asList(""))
    			.append("isTemplate", true)
    			.append("title", "Prueba con Java")
    			.append("content", "Texto de pruebaaaaa")
    			.append("coverphoto", "coverphotoooo")
    			.append("parameters", 
    					new Document("name","Carlos")
    						.append("place", "Lorena"))
    			.append("userId", new ObjectId());
    	
    	
    	collection.insertOne(document);

	}

}
