package pe.egcc.eurekaapp.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import pe.egcc.eurekaapp.service.espec.CuentaServiceEspec;
import pe.egcc.eurekaapp.service.impl.CuentaServiceImpl;

@ManagedBean
public class CuentaController {

  private String cuenta;
  List<Map<String, ?>> lista;

  public String getCuenta() {
    return cuenta;
  }

  public void setCuenta(String cuenta) {
    this.cuenta = cuenta;
  }

  public List<Map<String, ?>> getLista() {
    return lista;
  }
  
  public void doConsultar(){
    FacesMessage message;
    try {
      CuentaServiceEspec service = new CuentaServiceImpl();
      lista = service.getMovimientos(cuenta);
      if(lista.size() == 0){
        throw new Exception("Cuenta no existe.");
      }
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
          "Proceso ok.", "Cuenta si existe.");
    } catch (Exception e) {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
          "Error en el proceso.", e.getMessage());
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
  }
  
}
