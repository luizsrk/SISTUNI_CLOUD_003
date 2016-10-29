package pe.egcc.eurekaapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.eurekaapp.db.AccesoDB;
import pe.egcc.eurekaapp.model.Cliente;
import pe.egcc.eurekaapp.service.espec.ClienteServiceEspec;

public class ClienteServiceImpl implements ClienteServiceEspec {

  private final String SQL_SELECT
          = "select chr_cliecodigo, vch_cliepaterno, "
          + "vch_cliematerno, vch_clienombre, "
          + "chr_cliedni, vch_clieciudad, "
          + "vch_cliedireccion, vch_clietelefono, "
          + "vch_clieemail from cliente ";
  private final String SQL_INSERT = "";
  private final String SQL_UPDATE
          = "update cliente set vch_cliepaterno=?, vch_cliematerno=?,"
          + "vch_clienombre=?, chr_cliedni=?, vch_clieciudad=?, "
          + "vch_cliedireccion=?, vch_clietelefono=?, vch_clieemail=? "
          + "where chr_cliecodigo = ?";
  private final String SQL_DELETE = "";

  @Override
  public Cliente traerPorCodigo(String codigo) {
    Cliente bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT + " where chr_cliecodigo = ?";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, codigo);
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        bean = mapRow(rs);
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
    return bean;
  }

  /**
   * La consulta se realiza en función de los siguentes campos: - codigo - paterno - materno - nombre
   *
   * @param bean
   * @return
   */
  @Override
  public List<Cliente> traerVarios(Cliente bean) {
    List<Cliente> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = SQL_SELECT
              + " where chr_cliecodigo like concat(?,'%') "
              + " and vch_cliepaterno like concat(?,'%') "
              + " and vch_cliematerno like concat(?,'%') "
              + " and vch_clienombre like concat(?,'%') ";
      PreparedStatement pstm;
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getCodigo());
      pstm.setString(2, bean.getPaterno());
      pstm.setString(3, bean.getMaterno());
      pstm.setString(4, bean.getNombre());
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
  public void crear(Cliente bean) {

  }

  @Override
  public void actualizar(Cliente bean) {
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // Iniciar Tx
      cn.setAutoCommit(false);
      // Leer datos de la cuenta
      PreparedStatement pstm = cn.prepareStatement(SQL_UPDATE);
      pstm.setString(1, bean.getPaterno());
      pstm.setString(2, bean.getMaterno());
      pstm.setString(3, bean.getNombre());
      pstm.setString(4, bean.getDni());
      pstm.setString(5, bean.getCiudad());
      pstm.setString(6, bean.getDireccion());
      pstm.setString(7, bean.getTelefono());
      pstm.setString(8, bean.getEmail());
      pstm.setString(9, bean.getCodigo());
      int filas = pstm.executeUpdate();
      pstm.close();
      if (filas == 0) {
        throw new Exception("Cuenta no existe.");
      }
      // Fin de Tx
      cn.commit();
    } catch (Exception e) {
      try {
        cn.rollback();
      } catch (Exception e1) {
      }
      String texto = "Error en el proceso. "
              + e.getMessage();
      throw new RuntimeException(texto);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }
  }

  @Override
  public void eliminar(String codigo) {

  }

  @Override
  public Cliente mapRow(ResultSet rs) throws SQLException {
    Cliente bean = new Cliente();
    bean.setCodigo(rs.getString("chr_cliecodigo"));
    bean.setPaterno(rs.getString("vch_cliepaterno"));
    bean.setMaterno(rs.getString("vch_cliematerno"));
    bean.setNombre(rs.getString("vch_clienombre"));
    bean.setDni(rs.getString("chr_cliedni"));
    bean.setCiudad(rs.getString("vch_clieciudad"));
    bean.setDireccion(rs.getString("vch_cliedireccion"));
    bean.setTelefono(rs.getString("vch_clietelefono"));
    bean.setEmail(rs.getString("vch_clieemail"));
    return bean;
  }

}
