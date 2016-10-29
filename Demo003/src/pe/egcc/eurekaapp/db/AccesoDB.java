package pe.egcc.eurekaapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public final class AccesoDB {

  private AccesoDB() {
  }

  public static Connection getConnection() throws SQLException {
    Connection cn = null;
    // Parámetros
    String driver = "oracle.jdbc.OracleDriver";
    String urlDB = "jdbc:oracle:thin:@172.17.1.193:1521:QUIPU";
    String user = "eureka";
    String pass = "admin";
    try {
      // Conexión con la BD
      Class.forName(driver).newInstance();
      cn = DriverManager.getConnection(urlDB, user, pass);
    } catch (SQLException e) {
      throw e;
    } catch (ClassNotFoundException e) {
      throw new SQLException("No se encontro el driver de la BD.");
    } catch (Exception e) {
      throw new SQLException("No se puede establecer "
              + "conexión de la BD.");
    }
    return cn;
  }

}
