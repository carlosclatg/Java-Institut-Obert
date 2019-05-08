package com.luv2code.springdemo;

public class TrackCoach implements Coach {
	
	FortuneService fortuneService;
	//Constructor con la inyeccion
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	public TrackCoach() {
	}

	
	
	@Override
	public String getDailyWorkOut() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Just do it " + fortuneService.getFortune();
	}	
	
	
	//init and destroy methods
	
	private void doBeforeSomeStuff() {
		System.out.println("init method for trackCoach");
	}
	
	private void doDestroySomeStuff() {
		System.out.println("destroy method for trackCoach");
	}
	
}
