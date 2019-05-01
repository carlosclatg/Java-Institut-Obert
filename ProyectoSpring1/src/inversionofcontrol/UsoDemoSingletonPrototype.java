package inversionofcontrol;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoDemoSingletonPrototype {

	public static void main(String[] args) {
		// Carga de xml de config
		
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		//peticion de beans al contenedor Spring
		
		SecretarioEmpleado Maria = contexto.getBean("miSecretarioEmpleado", SecretarioEmpleado.class);
		SecretarioEmpleado Pedro  = contexto.getBean("miSecretarioEmpleado", SecretarioEmpleado.class);
		//Estos dos beans apuntan al mismo objeto pq trabaja bajo singleton que asegura q solo hay una instancia.
		
		System.out.println(Maria); //printea referencia en memoria
		System.out.println(Pedro); //printea referencia en memoria
		
		System.out.println(Maria == Pedro); //true si trabajamos bajo singleton, si trabajamos con prototype es false.
		
		//para trabajar bajo prototype se hace desde el bean de configuracion (xml)
		
		

	}

}
