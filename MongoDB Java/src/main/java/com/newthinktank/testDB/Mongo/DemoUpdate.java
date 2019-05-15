package com.newthinktank.testDB.Mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DemoUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MongoClient mongoClient = new MongoClient(); //by default localohost 27017
    	MongoDatabase database = mongoClient.getDatabase("skylabbooks");
    	MongoCollection<Document> collection = database.getCollection("books");
    	
    	collection.updateOne(Filters.eq("title", "Prueba con Java"), new Document("$set", new Document("content", "Texto Updateado!")));
    	collection.updateMany(Filters.eq("isTemplate", false), new Document("$set", new Document("isTemplate", true)));
    	

	}

}
