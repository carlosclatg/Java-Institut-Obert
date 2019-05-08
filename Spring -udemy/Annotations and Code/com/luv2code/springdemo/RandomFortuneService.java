package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	//create an array of strings
	private String[] data = {
			"beware of the waves",
			"some of you are idiot",
			"just go and fys"
	};
	
	private Random myRandom = new Random();
	
	
	@Override
	public String getFortune() {
		return data[myRandom.nextInt(this.data.length)];
	}
	

}
