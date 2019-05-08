package com.luv2code.springdemo;

//setter injection
//1. create setter methods in class for injections
//2. configure dependency injection in Spring config file (property name="enminusculaselsettersinelset" ref="bean")


public class CricketCoach implements Coach {

	private FortuneService fortuneService;

	//Inyectar propiedades a través de xml:
	//1. Crear campo de clase y crear los setters.
	//2. En el xml de config asignar properties en el bean y <property name="emailAdress/team" value="valores a asignar" >
	private String emailAdress;
	private String team;
	//create a no arg constructor
	
	
	public CricketCoach () {
		System.out.println("cricketcoach no arg constructor");
	}

	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("inside setter");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Shoot, tackle and score goals";
	}
	
	public String getEmailAdressString() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
