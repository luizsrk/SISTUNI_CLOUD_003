package pe.egcc.eurekaapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.egcc.eurekaapp.db.AccesoDB;
import pe.egcc.eurekaapp.model.Empleado;
import pe.egcc.eurekaapp.service.espec.EmpleadoServiceEspec;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public class EmpleadoServiceImpl implements EmpleadoServiceEspec {

  private static final Logger LOGGER = Logger.getLogger(EmpleadoServiceImpl.class.getName());

  /**
   * Valida el usuario y la clase en la BD. 
   * Si son correctos retorna un objeto de tipo Emplado. 
   * Caso contrario, retorna null.
   *
   * @param usuario
   * @param clave
   * @return Retorna un objeto de tipo Empleado.
   */
  @Override
  public Empleado validar(String usuario, String clave) {
    Empleado bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_emplcodigo,vch_emplpaterno,"
              + "vch_emplmaterno,vch_emplnombre, "
              + "vch_emplciudad,vch_empldireccion,"
              + "vch_emplusuario "
              + "from empleado "
              + "where vch_emplusuario = ? "
              + "and vch_emplclave = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, usuario);
      pstm.setString(2, clave);
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        bean = mapRow(rs);
      }
      rs.close();
      pstm.close();
      if (bean == null) {
        throw new Exception("Datos ingresados no son correctos.");
      }
      LOGGER.log(Level.INFO, "Validación de usuario ok.");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.toString(), e);
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
    return bean;

  }

  @Override
  public Empleado traerPorCodigo(String codigo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Empleado> traerVarios(Empleado bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void crear(Empleado bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void actualizar(Empleado bean) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void eliminar(String codigo) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Empleado mapRow(ResultSet rs) throws SQLException {
    Empleado bean = new Empleado();
    bean.setCodigo(rs.getString("chr_emplcodigo"));
    bean.setPaterno(rs.getString("vch_emplpaterno"));
    bean.setMaterno(rs.getString("vch_emplmaterno"));
    bean.setNombre(rs.getString("vch_emplnombre"));
    bean.setCiudad(rs.getString("vch_emplciudad"));
    bean.setDireccion(rs.getString("vch_empldireccion"));
    bean.setUsuario(rs.getString("vch_emplusuario"));
    bean.setClave("*********");
    return bean;
  }

}
