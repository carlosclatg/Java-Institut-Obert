package pruebaAnnotations;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class UsoAnnotations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		///@Component("ComercialExperimentado") en la classe ComercialExperimentado, el primer campo es el id.
		Empleados Antonio = contexto.getBean("ComercialExperimentado", Empleados.class);
		
		///@Component en la classe ComercialExperimentado, el primer campo es el id, y la primera letra en minuscula.
		//Empleados Antonio = contexto.getBean("comercialExperimentado", Empleados.class);
		
		System.out.println(Antonio.getInforme());
		System.out.println(Antonio.getTareas());
		
		contexto.close();

	}

}
