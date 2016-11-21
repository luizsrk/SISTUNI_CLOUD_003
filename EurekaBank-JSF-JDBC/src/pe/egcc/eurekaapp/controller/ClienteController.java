package pe.egcc.eurekaapp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.egcc.eurekaapp.model.Cliente;
import pe.egcc.eurekaapp.service.espec.ClienteServiceEspec;
import pe.egcc.eurekaapp.service.impl.ClienteServiceImpl;

@ManagedBean
@ViewScoped
public class ClienteController {

  private Cliente cliente;
  private List<Cliente> lista;
  
  @PostConstruct
  public void init(){
    cliente = new Cliente();
  }
  
  public List<Cliente> getLista() {
    return lista;
  }
  
  public Cliente getCliente() {
    return cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
	public void doConsultar() {

		try {
			ClienteServiceEspec service = new ClienteServiceImpl();
			lista = service.traerVarios(cliente);
		} catch (Exception e) {

		}

	}
  
}