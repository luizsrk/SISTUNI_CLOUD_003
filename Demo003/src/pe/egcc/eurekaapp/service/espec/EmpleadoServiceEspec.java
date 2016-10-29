package pe.egcc.eurekaapp.service.espec;

import pe.egcc.eurekaapp.model.Empleado;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public interface EmpleadoServiceEspec 
  extends CrudServiceEspec<Empleado>, RowMapper<Empleado>{
  
  Empleado validar(String usuario, String clave);
  
}
