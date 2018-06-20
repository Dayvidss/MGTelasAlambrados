package app.jsf.service;

import java.util.List;

import javax.inject.Inject;

import app.jsf.dao.FornecedorDAO;
import app.jsf.model.Fornecedor;

public class FornecedorService extends Service{
	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;
	
	/**
	 * Carrega um fornecedor com base no seu Id 
	 * @param fornecedorId
	 * @return
	 */
	public Fornecedor carregar(Integer fornecedorId) {
		return fornecedorDAO.carregar(fornecedorId, Fornecedor.class);
	}
	
	/**
	 * Grava um fornecedor
	 * @param fornecedor
	 * @throws ValidationException
	 */
	public void salvar(Fornecedor fornecedor) throws ValidationException{
		try {
			beginTransaction();

			if(fornecedorDAO.existeFornecedor(fornecedor.getCpf(), null)) {
				//Verifica se um fornecedor com o mesmo CPF já está cadastrado
				throw new ValidationException("Este fornecedor já está cadastrado.");
			}
			
			fornecedorDAO.salvar(fornecedor);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Atualiza um fornecedor cadastrado
	 * @param fornecedor
	 * @throws ValidationException
	 */
	public void atualizar(Fornecedor fornecedor) throws ValidationException {
		try {
			beginTransaction();

			if(fornecedorDAO.existeFornecedor(fornecedor.getCpf(), null)) {
				//Verifica se um fornecedor com o mesmo CPF já está cadastrado
				throw new ValidationException("Este fornecedor já está cadastrado.");
			}
			
			fornecedorDAO.alterar(fornecedor);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Excluir fornecedor cadastrado
	 * @param fornecedorId
	 */
	public void excluir(Integer fornecedorId) {
		try {
			beginTransaction();
			
			Fornecedor fornecedor = fornecedorDAO.carregar(fornecedorId, Fornecedor.class);
			fornecedorDAO.excluir(fornecedor);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Obtem lista de fornecedors cadastrados
	 * @return
	 */
	public List<Fornecedor> listarFornecedores(){
		return fornecedorDAO.listarFornecedores();
	}
}
