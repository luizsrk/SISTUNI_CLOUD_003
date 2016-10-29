package pe.egcc.eurekaapp.model;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronec@gmail.com
 */
public class Cliente {

  private String codigo;
  private String paterno;
  private String materno;
  private String nombre;
  private String dni;
  private String ciudad;
  private String direccion;
  private String telefono;
  private String email;

  public Cliente() {
    codigo = "NONE";
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getPaterno() {
    return paterno;
  }

  public void setPaterno(String paterno) {
    this.paterno = paterno;
  }

  public String getMaterno() {
    return materno;
  }

  public void setMaterno(String materno) {
    this.materno = materno;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    String texto = codigo 
            + " | " + paterno
            + " | " + materno
            + " | " + nombre
            + " | " + dni
            + " | " + email;
    return texto;
  }
  
  

}
