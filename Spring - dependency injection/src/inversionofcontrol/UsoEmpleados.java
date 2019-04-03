package inversionofcontrol;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoEmpleados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Creacion de objetos de tipo Empleado
		
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext.xml");
		Empleados Juan = contexto.getBean("miEmpleado", Empleados.class);
		System.out.println("hellospring");
		System.out.println(Juan.getTareas());
		System.out.println(Juan.getInformes());
		System.out.println("-------------------");
		
		
		SecretarioEmpleado Maria = contexto.getBean("miSecretarioEmpleado", SecretarioEmpleado.class);
		System.out.println(Maria.getTareas());
		System.out.println(Maria.getInformes());
		//Maria implmenta Empleados solo dispone de tareas e informes, hemos de cambiar a secretarioEmpleado.class
		System.out.println(Maria.getEmail());
		System.out.println(Maria.getNombreEmpresa());
		//para acceder a los métodos de email y empresa.
		System.out.println("-------------------");
		
		JefeEmpleado jefecillo = contexto.getBean("miEmpleado", JefeEmpleado.class);
		System.out.println(jefecillo.getTareas());
		System.out.println(jefecillo.getInformes());
		//Maria implmenta Empleados solo dispone de tareas e informes, hemos de cambiar a secretarioEmpleado.class
		System.out.println(jefecillo.getEmail());
		System.out.println(jefecillo.getNombreEmpresa());
		
		
		contexto.close();
		
		//Uso de los objetos
	}

}
