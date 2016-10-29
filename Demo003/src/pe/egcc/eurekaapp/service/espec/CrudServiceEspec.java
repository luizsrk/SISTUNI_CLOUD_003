package pe.egcc.eurekaapp.service.espec;

import java.util.List;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public interface CrudServiceEspec<T> {
  
  T traerPorCodigo(String codigo);
  
  List<T> traerVarios(T bean);
  
  void crear(T bean);
  
  void actualizar(T bean);
  
  void eliminar(String codigo);
  
}
