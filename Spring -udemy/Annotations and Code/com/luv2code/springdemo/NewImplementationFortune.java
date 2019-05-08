package com.luv2code.springdemo;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;


@Component
public class NewImplementationFortune implements FortuneService {

	private ArrayList<String> list=new ArrayList<String>();
	
	public NewImplementationFortune() {
		System.out.println("inside empty constructor of newImplementationFortune");
	}
	
	@PostConstruct
	private void PostConstructionMethod() {
		System.out.println("REading file in newImplementationFortune");
		java.io.File file = new java.io.File("C:\\Users\\Usuario\\eclipse-workspace\\spring-demo-annotations\\src\\properties2.txt");
		
		// read fortunes from file
		try (BufferedReader br = new BufferedReader(
				new FileReader(file))) {

			String tempLine;

			while ((tempLine = br.readLine()) != null) {
				list.add(tempLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String getFortune() {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		return list.get(random.nextInt(list.size()));
	}

}
