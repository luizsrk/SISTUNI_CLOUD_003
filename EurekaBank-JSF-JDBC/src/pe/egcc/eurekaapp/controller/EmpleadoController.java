package pe.egcc.eurekaapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.egcc.eurekaapp.model.Empleado;
import pe.egcc.eurekaapp.service.espec.EmpleadoServiceEspec;
import pe.egcc.eurekaapp.service.impl.EmpleadoServiceImpl;

@ManagedBean
@ViewScoped
public class EmpleadoController {

  private Empleado empleado;
  private List<Empleado> lista;
  
  @PostConstruct
  public void init(){
	  empleado = new Empleado();
  }
  
  public List<Empleado> getLista() {
    return lista;
  }
  
  public Empleado getEmpleado() {
    return empleado;
  }
  
  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }
  
	public void doConsultar() {

		try {
			EmpleadoServiceEspec service = new EmpleadoServiceImpl();
			lista = service.traerVarios(empleado);
		} catch (Exception e) {

		}

	}
  
}