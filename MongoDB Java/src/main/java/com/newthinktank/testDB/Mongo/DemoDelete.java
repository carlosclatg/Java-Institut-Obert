package com.newthinktank.testDB.Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DemoDelete {

	public static void main(String[] args) {
		
		
		MongoClient mongoClient = new MongoClient(); //by default localohost 27017
    	MongoDatabase database = mongoClient.getDatabase("skylabbooks");
    	MongoCollection<Document> collection = database.getCollection("books");
    	
    	collection.deleteOne(Filters.eq("title", "Nueva prueba"));

	}

}
