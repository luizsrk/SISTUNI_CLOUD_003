package pe.egcc.eurekaapp.service.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JdbcUtil {

  
  private JdbcUtil() {
  }

  public static List<Map<String, ?>> rsToList(ResultSet rs) 
      throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<Map<String, ?>> results = new ArrayList<Map<String, ?>>();
    while (rs.next()) {
      Map<String, Object> row = new HashMap<String, Object>();
      for (int i = 1; i <= columns; i++) {
        row.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
      }
      results.add(row);
    }
    return results;
  }
  
}
