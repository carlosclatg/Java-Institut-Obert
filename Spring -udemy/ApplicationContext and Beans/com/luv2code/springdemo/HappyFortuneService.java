package com.luv2code.springdemo;

public class HappyFortuneService implements FortuneService {

	//Classe que implementa la interfaz FortuneService que después es la que utilizamos en la clase que nos da la inversión de control.
	@Override
	public String getFortune() {
		return "Today is your lucky day";
	}

}
