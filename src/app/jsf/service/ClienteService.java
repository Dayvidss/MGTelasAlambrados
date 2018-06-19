package app.jsf.service;

import java.util.List;

import javax.inject.Inject;

import app.jsf.dao.ClienteDAO;
import app.jsf.model.Cliente;

public class ClienteService extends Service{
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;
	
	/**
	 * Carrega um cliente com base no seu Id 
	 * @param clienteId
	 * @return
	 */
	public Cliente carregar(Integer clienteId) {
		return clienteDAO.carregar(clienteId, Cliente.class);
	}
	
	/**
	 * Grava um cliente
	 * @param cliente
	 * @throws ValidationException
	 */
	public void salvar(Cliente cliente) throws ValidationException{
		try {
			beginTransaction();

			if(clienteDAO.existeCliente(cliente.getCpf(), null)) {
				//Verifica se um cliente com o mesmo CPF já está cadastrado
				throw new ValidationException("Este cliente já está cadastrado.");
			}
			
			clienteDAO.salvar(cliente);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Atualiza um cliente cadastrado
	 * @param cliente
	 * @throws ValidationException
	 */
	public void atualizar(Cliente cliente) throws ValidationException {
		try {
			beginTransaction();

			if(clienteDAO.existeCliente(cliente.getCpf(), null)) {
				//Verifica se um cliente com o mesmo CPF já está cadastrado
				throw new ValidationException("Este cliente já está cadastrado.");
			}
			
			clienteDAO.alterar(cliente);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Excluir cliente cadastrado
	 * @param clienteId
	 */
	public void excluir(Integer clienteId) {
		try {
			beginTransaction();
			
			Cliente cliente = clienteDAO.carregar(clienteId, Cliente.class);
			clienteDAO.excluir(cliente);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Obtem lista de clientes cadastrados
	 * @return
	 */
	public List<Cliente> listarClientes(){
		return clienteDAO.listarClientes();
	}
}
