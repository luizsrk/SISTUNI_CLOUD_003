package pe.egcc.eurekaapp.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pe.egcc.eurekaapp.model.Empleado;
import pe.egcc.eurekaapp.service.espec.EmpleadoServiceEspec;
import pe.egcc.eurekaapp.service.impl.EmpleadoServiceImpl;

@ManagedBean
@SessionScoped
public class LogonController {

  private String usuario;
  private String clave;
  private Empleado empleado;
  
  public String getUsuario() {
    return usuario;
  }
  
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
  
  public String getClave() {
    return clave;
  }
  
  public void setClave(String clave) {
    this.clave = clave;
  }
  
  public Empleado getEmpleado() {
    return empleado;
  }
  
  public String doProcesar(){
    String destino;
    FacesMessage message = null;
    try {
      // Proceso
      EmpleadoServiceEspec service;
      service = new EmpleadoServiceImpl();
      this.empleado = service.validar(usuario, clave);
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
          "Datos correctos.", "Bienvenido (a) " + this.usuario + ".");
      destino = "main";
    } catch (Exception e) {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
          "Error en el proceso.", e.getMessage());
      destino = "index";
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
    return destino;
  }
  
  
  public String doSalir(){
        
    return "index";
  }
}
