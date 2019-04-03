package inversionofcontrol;

public class DirectorEmpleado implements Empleados {
	
	
	//Creacion de campo tipo creacion informe (interfaz)
	
	private CreacionInformes informeNuevo;
	
	//Creacion del constructor que inyecta la dependencia
	public DirectorEmpleado(CreacionInformes creacionInformes) {
		this.informeNuevo = creacionInformes;
	}
	
	@Override
	public String getTareas() {
		// TODO Auto-generated method stub
		return "gestiono al personal de la empresa";
	}

	@Override
	public String getInformes() {
		// TODO Auto-generated method stub
		return "informe creado por director: " + informeNuevo.getInformes() + "";
	}
	
}
