package inversionofcontrol;

public class SecretarioEmpleado implements Empleados {

	private CreacionInformes informeNuevo;
	private String email;
	private String nombreEmpresa;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	
	@Override
	public String getTareas() {
		// TODO Auto-generated method stub
		return "Gestionar agenda de jefes";
	}

	public CreacionInformes getInformeNuevo() {
		return informeNuevo;
	}

	public void setInformeNuevo(CreacionInformes informeNuevo) {
		this.informeNuevo = informeNuevo;
	}

	@Override
	public String getInformes() {
		// TODO Auto-generated method stub
		return "Informe generado por el secretario" + informeNuevo.getInformes();
	}

}
