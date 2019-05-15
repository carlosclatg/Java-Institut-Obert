package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			//create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			//read JSON File and map/convert to Java POJO: data/sample-lito.json
			Student theStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			//printout data read from JSON file
			System.out.println(theStudent);
			System.out.println(theStudent.getFirstName());
			
			System.out.println(theStudent.getAddress().getCity());
			System.out.println(theStudent.getAddress().getZip());
			
			for(String s: theStudent.getLanguages()) {
				System.out.println(s);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
