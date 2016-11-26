package pe.egcc.eurekaapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.egcc.eurekaapp.model.Empleado;
import pe.egcc.eurekaapp.service.espec.EmpleadoServiceEspec;
import pe.egcc.eurekaapp.service.impl.EmpleadoServiceImpl;

@ManagedBean
@ViewScoped
public class EmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5502060235338764649L;
	private Empleado empleado;
	private List<Empleado> lista;

	@PostConstruct
	public void init() {
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

	public void doConsultar(ActionEvent actionEvent) {

		try {
			EmpleadoServiceEspec service = new EmpleadoServiceImpl();
			lista = service.traerVarios(empleado);
		} catch (Exception e) {

		}

	}

//	public void buttonAction(ActionEvent actionEvent) {
//		addMessage("Welcome to Primefaces!!");
//	}
//
//	public void addMessage(String summary) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}

}