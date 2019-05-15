package com.newthinktank.testDB.Mongo;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * Hello world!
 *
 */
public class DemoFind 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws UnknownHostException{
    	
    	MongoClient mongoClient = new MongoClient();
    	MongoDatabase database = mongoClient.getDatabase("skylabbooks");
    	MongoCollection<Document> collection = database.getCollection("books");
    	FindIterable<Document> result = collection.find(Filters.and(Filters.eq("isTemplate", true)));
    	Iterator<Document> iterator = result.iterator();
    	while(iterator.hasNext()) {
    		Document doc = (Document) iterator.next().get("parameters");
    		try {
				System.out.println(doc.get("name"));
			} catch (NullPointerException e) { //in case there is no name a NullPointerException is thrown
				System.out.println("No name for this book");
			}
    	}
    }

}



/*
const Book = new Schema({

    title: {
        type: String,
        required: true
    },

    content :{
        type: String,
        required: true
    },

    coverphoto: {
        type: String,
        required: true
    },

    images: [String], //Array de strings de URL de photos

    parameters: {}, //Object con properties, normalmente name y place

    userId: { //Referencia del usuario.
        type: ObjectId,
        required: true,
        ref: 'User'
    },

    isTemplate: {
        type: Boolean,
        default: false
    }

})

**/