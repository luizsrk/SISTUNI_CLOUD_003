package pe.egcc.eurekaapp.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import pe.egcc.eurekaapp.model.Sucursal;
import pe.egcc.eurekaapp.service.espec.SucursalServiceEspec;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public class SucursalServiceImpl implements SucursalServiceEspec{

  @Override
  public Sucursal traerPorCodigo(String codigo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Sucursal> traerVarios(Sucursal bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void crear(Sucursal bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void actualizar(Sucursal bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void eliminar(String codigo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Sucursal mapRow(ResultSet rs) throws SQLException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
