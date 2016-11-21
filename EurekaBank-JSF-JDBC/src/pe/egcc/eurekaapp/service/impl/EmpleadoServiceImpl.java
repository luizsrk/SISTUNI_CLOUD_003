package pe.egcc.eurekaapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.egcc.eurekaapp.db.AccesoDB;
import pe.egcc.eurekaapp.model.Cliente;
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
  
	private final String SQL_SELECT = "select chr_emplcodigo, vch_emplpaterno, " + "vch_emplmaterno, vch_emplnombre, "
			+ "vch_emplciudad, vch_empldireccion, " + "vch_emplusuario from empleado ";
	
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
	    List<Empleado> lista = new ArrayList<>();
	    Connection cn = null;
	    try {
	      cn = AccesoDB.getConnection();
	      String sql = SQL_SELECT
	              + " where chr_emplcodigo like concat(?,'%') "
	              + " and vch_emplpaterno like concat(?,'%') "
	              + " and vch_emplmaterno like concat(?,'%') "
	              + " and vch_emplnombre like concat(?,'%') "
	      		  + " and vch_emplciudad like concat(?,'%') ";
	      PreparedStatement pstm;
	      pstm = cn.prepareStatement(sql);
	      pstm.setString(1, bean.getCodigo());
	      pstm.setString(2, bean.getPaterno());
	      pstm.setString(3, bean.getMaterno());
	      pstm.setString(4, bean.getNombre());
	      pstm.setString(5, bean.getCiudad());
	      ResultSet rs = pstm.executeQuery();
	      while (rs.next()) {
	        lista.add(mapRow(rs));
	      }
	      rs.close();
	      pstm.close();
	    } catch (Exception e) {
	      throw new RuntimeException(e.getMessage());
	    } finally {
	      try {
	        cn.close();
	      } catch (Exception e) {
	      }
	    }
	    return lista;
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
