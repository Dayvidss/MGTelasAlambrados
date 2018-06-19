package app.jsf.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import app.jsf.model.Cliente;
import app.jsf.service.ClienteService;
import app.jsf.service.ValidationException;

@Named("clienteBean")
@RequestScoped
public class ClienteBean extends AbstractBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	
	private Cliente cliente;

	private List<Cliente> clientes;
	
	/**
	 * Salva ou atualiza o cliente
	 * @return
	 * @throws Exception
	 */
	public String gravar() throws Exception{
		try {
			//A operação a ser realizada depende da existência ou não do Id
			if(cliente.getId() == null) {
				clienteService.salvar(cliente);
			}else {
				clienteService.atualizar(cliente);
			}
			
			cliente = null;
			return redirect("cliente");
		}catch(ValidationException e) {
			// Ocorreu um erro de validação de negócio
			addMessageToRequest(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Carrega um cliente para que ele seja alterado
	 * @param clienteId
	 * @return
	 * @throws Exception
	 */
	public String alterar(Integer clienteId) throws Exception {
		cliente = clienteService.carregar(clienteId);
		clientes = null;
		return null;
	}
	
	/**
	 * Exclui um cliente
	 * @param clienteId
	 * @return
	 * @throws Exception
	 */
	public String excluir(Integer clienteId) throws Exception{
		clienteService.excluir(clienteId);
		clientes = null;
		return redirect("cliente");
	}

	/**
	 * Limpa formulário
	 * @return
	 * @throws Exception
	 */
	public String limpar() throws Exception{
		cliente = null;
		return null;
	}
	
	public Cliente getCliente() {
		if(cliente == null) {
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Obtem lista de clientes cadastrados
	 * @return
	 * @throws Exception
	 */
	public List<Cliente> getClientes() throws Exception{
		if(clientes == null) {
			clientes = clienteService.listarClientes();
		}
		return clientes;
	}
}
