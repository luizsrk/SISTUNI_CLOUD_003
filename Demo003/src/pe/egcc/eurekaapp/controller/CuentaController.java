package pe.egcc.eurekaapp.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

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
    try {
      CuentaServiceEspec service = new CuentaServiceImpl();
      lista = service.getMovimientos(cuenta);
    } catch (Exception e) {
    }
  }
  
}
