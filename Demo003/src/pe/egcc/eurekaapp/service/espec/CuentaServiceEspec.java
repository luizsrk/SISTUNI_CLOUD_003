package pe.egcc.eurekaapp.service.espec;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public interface CuentaServiceEspec {

  void procRetiro(String cuenta, double importe,
          String clave, String codEmp);

  List<Map<String,?>> getMovimientos(String cuenta);
  
  
}
