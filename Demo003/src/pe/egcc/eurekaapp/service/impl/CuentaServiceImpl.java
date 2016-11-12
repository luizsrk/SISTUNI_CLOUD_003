package pe.egcc.eurekaapp.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pe.egcc.eurekaapp.db.AccesoDB;
import pe.egcc.eurekaapp.service.espec.CuentaServiceEspec;

public class CuentaServiceImpl implements CuentaServiceEspec{

  @Override
  public void procRetiro(String cuenta,
          double importe, String clave,
          String codEmp) {
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // Iniciar Tx
      cn.setAutoCommit(false);
      // Leer datos de la cuenta
      String sql = "select DEC_CUENSALDO, INT_CUENCONTMOV "
              + "from cuenta where CHR_CUENCODIGO = ? "
              + "for update wait 5";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      if (!rs.next()) {
        throw new Exception("Cuenta no exist.");
      }
      double saldo = rs.getDouble("DEC_CUENSALDO");
      int cont = rs.getInt("INT_CUENCONTMOV");
      rs.close();
      pstm.close();
      // Verificar saldo
      saldo -= importe;
      if (saldo < 0) {
        throw new Exception("Saldo no es suficiente.");
      }
      // Actualizar tabla CUENTA
      cont++;
      sql = "update cuenta set DEC_CUENSALDO = ?,"
              + "INT_CUENCONTMOV = ? "
              + "where CHR_CUENCODIGO = ?";
      pstm = cn.prepareStatement(sql);
      pstm.setDouble(1, saldo);
      pstm.setInt(2, cont);
      pstm.setString(3, cuenta);
      int filas = pstm.executeUpdate();
      if (filas == 0) {
        throw new Exception("Cuenta no es correcta.");
      }

      pstm.close();
      // Registrar movimiento
      sql = "insert into movimiento(CHR_CUENCODIGO,"
              + "INT_MOVINUMERO,DTT_MOVIFECHA,"
              + "CHR_EMPLCODIGO,CHR_TIPOCODIGO,"
              + "DEC_MOVIIMPORTE) "
              + "values(?, ?, SYSDATE, ?, '004', ?)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      pstm.setInt(2, cont);
      pstm.setString(3, codEmp);
      pstm.setDouble(4, importe);
      pstm.executeUpdate();
      pstm.close();
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
  public List<Map<String, ?>> getMovimientos(String cuenta) {
    List<Map<String, ?>> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "SELECT SUCUCODIGO, SUCUNOMBRE, CLIECODIGO, "
          + "CLIEPATERNO, CLIEMATERNO, CLIENOMBRE, CUENCODIGO, "
          + "CUENSALDO, CUENESTADO, MOVINUMERO, MOVIFECHA, "
          + "MOVIIMPORTE, CUENREFERENCIA, TIPOCODIGO, "
          + "TIPONOMBRE, TIPOACCION, MONECODIGO, MONENOMBRE  "
          + "FROM V_MOVIMIENTO WHERE CUENCODIGO = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      lista = JdbcUtil.rsToList(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }

}
