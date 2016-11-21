package pe.egcc.eurekaapp.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pe.egcc.eurekaapp.service.espec.CuentaServiceEspec;
import pe.egcc.eurekaapp.service.impl.CuentaServiceImpl;

@ManagedBean
@RequestScoped
public class CuentaController {

	private String cuenta;
	private String moneda = "None";
	private double importe;
	private String clave;
	List<Map<String, ?>> lista;

	/*
	 * @ManagedProperty(value="#{logonController}") private LogonController
	 * logonController;
	 */
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List<Map<String, ?>> getLista() {
		return lista;
	}

	public void doConsultar() {
		FacesMessage message;
		try {
			CuentaServiceEspec service = new CuentaServiceImpl();
			lista = service.getMovimientos(cuenta);
			if (lista.size() == 0) {
				throw new Exception("Cuenta no existe.");
			}
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "Cuenta si existe.");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void doProcRetiro() {
		FacesMessage message;
		try {
			// Codigo del empleado
			String codEmp = "0003";// logonController.getEmpleado().getCodigo();
			// Procesi
			CuentaServiceEspec service = new CuentaServiceImpl();
			service.procRetiro(cuenta, importe, clave, codEmp);
			// Clear Form
			this.cuenta = "";
			this.importe = 0.0;
			this.clave = "";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "Retiro registrado correctamente.");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	  public void doProcDeposito(){
		    FacesMessage msg;
		    try {
		    CuentaServiceEspec service = new CuentaServiceImpl();
		      service.procDeposito(cuenta, importe, "0004");
		      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "El depóiso se realizò con éxito.");
		    } catch (Exception e) {
		      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
		    }
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		  }

}
