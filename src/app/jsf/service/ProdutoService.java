package app.jsf.service;

import java.util.List;

import javax.inject.Inject;

import app.jsf.dao.ProdutoDAO;
import app.jsf.model.Produto;

public class ProdutoService extends Service{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;
	
	/**
	 * Carrega um produto com base no seu Id 
	 * @param produtoId
	 * @return
	 */
	public Produto carregar(Integer produtoId) {
		return produtoDAO.carregar(produtoId, Produto.class);
	}
	
	/**
	 * Grava um produto
	 * @param produto
	 * @throws ValidationException
	 */
	public void salvar(Produto produto) throws ValidationException{
		try {
			beginTransaction();

			if(produtoDAO.existeProduto(produto.getNome(), null)) {
				//Verifica se um produto com o mesmo Nome já está cadastrado
				throw new ValidationException("Este produto já está cadastrado.");
			}
			
			produtoDAO.salvar(produto);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Atualiza um produto cadastrado
	 * @param produto
	 * @throws ValidationException
	 */
	public void atualizar(Produto produto) throws ValidationException {
		try {
			beginTransaction();

			if(produtoDAO.existeProduto(produto.getNome(), null)) {
				//Verifica se um produto com o mesmo CPF já está cadastrado
				throw new ValidationException("Este produto já está cadastrado.");
			}
			
			produtoDAO.alterar(produto);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Excluir produto cadastrado
	 * @param produtoId
	 */
	public void excluir(Integer produtoId) {
		try {
			beginTransaction();
			
			Produto produto = produtoDAO.carregar(produtoId, Produto.class);
			produtoDAO.excluir(produto);
			
			commitTransaction();
			
		} catch (Exception e) {
			rollbackTransaciotn();
			throw e;
		}
	}
	
	/**
	 * Obtem lista de produtos cadastrados
	 * @return
	 */
	public List<Produto> listarProdutos(){
		return produtoDAO.listarProdutos();
	}
}
