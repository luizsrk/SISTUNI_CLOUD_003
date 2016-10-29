package pe.egcc.eurekaapp.prueba;

import java.util.List;
import pe.egcc.eurekaapp.model.Cliente;
import pe.egcc.eurekaapp.service.impl.ClienteServiceImpl;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public class Prueba04 {
  
  public static void main(String[] args) {
    
    try {
      // Dato
      Cliente bean = new Cliente();
      bean.setCodigo("");
      bean.setPaterno("C");
      bean.setMaterno("");
      bean.setNombre("");
      // Proceso
      ClienteServiceImpl service = new ClienteServiceImpl();
      List<Cliente> lista = service.traerVarios(bean);
      // Reporte
      for (Cliente c : lista) {
        System.out.println(c.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
