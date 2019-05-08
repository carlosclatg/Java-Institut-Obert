package com.luv2code.springdemo;

/*Pasos para inyeccion de dependencias
 * 
 * 1.DEfinit la dependencia de interfaz y clases
 * 2. Crear un constructor en la clase para las inyeccions
 * 3.Configurar dependencia en el xml
 * 
 */


public class BaseBallCoach implements Coach {
	
	//Define a private field for the dependency
	private FortuneService fortuneService;
	
	
	//Define a constructor for dependency injection
	public BaseBallCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Spend 30 min on batting practice";
	}

	@Override
	public String getDailyFortune() {
		//use my fortuneService to get a fortune
		//dependency = helper
		
		return fortuneService.getFortune();
	}

}
