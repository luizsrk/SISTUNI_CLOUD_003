package pe.egcc.eurekaapp.prueba;

import java.util.List;
import java.util.Map;

import pe.egcc.eurekaapp.service.espec.CuentaServiceEspec;
import pe.egcc.eurekaapp.service.impl.CuentaServiceImpl;

public class Prueba05 {

  public static void main(String[] args) {
    try {
      CuentaServiceEspec service = new CuentaServiceImpl();
      List<Map<String, ?>> lista = service.getMovimientos("00100001");
      lista.stream().forEach(r -> 
          System.out.println(r.get("MOVINUMERO") + " | " + 
      r.get("TIPONOMBRE") + " | " + r.get("MOVIIMPORTE") ));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
}
