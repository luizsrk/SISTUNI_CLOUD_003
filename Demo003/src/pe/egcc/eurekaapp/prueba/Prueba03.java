package pe.egcc.eurekaapp.prueba;

import pe.egcc.eurekaapp.model.Cliente;
import pe.egcc.eurekaapp.service.impl.ClienteServiceImpl;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public class Prueba03 {
  
  public static void main(String[] args) {
    
    try {
      // Dato
      String codigo = "00005";
      // Proceso
      ClienteServiceImpl service = new ClienteServiceImpl();
      Cliente bean = service.traerPorCodigo(codigo);
      // Reporte
      System.out.println("Codigo: " + bean.getCodigo());
      System.out.println("Paterno: " + bean.getPaterno());
      System.out.println("Materno: " + bean.getMaterno());
      System.out.println("Nombre: " + bean.getNombre());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
