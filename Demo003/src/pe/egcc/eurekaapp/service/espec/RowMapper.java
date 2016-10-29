package pe.egcc.eurekaapp.service.espec;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Idea tomada de Spring Framework.
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public interface RowMapper<T> {

  /**
   * 
   * @param rs
   * @return Retorna un objeto de tipo T con los datos de la fila actual del ResultSet.
   * @throws SQLException 
   */
  T mapRow(ResultSet rs) throws SQLException;
  
  
}
