package app.jsf.dao;

import java.util.List;

import javax.persistence.Query;

import app.jsf.model.Produto;

public class ProdutoDAO extends DAO{
	private static final long serialVersionUID = 1L;

	/**
	 * Obtem a lista dos produtos cadastrados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutos(){
		Query q = criarQuery("SELECT p FROM PRODUTOS p ORDER BY p.nome");
		return q.getResultList();
	}
	/**
	 * Verifica se já existe produto cadastrado
	 * @param nome
	 * @param produtoId
	 * @return
	 */
	public boolean existeProduto(String nome, Integer produtoId) {
		String query = "SELECT COUNT(p) FROM PRODUTOS p WHERE p.nome = '" + nome + "'";
		if(produtoId != null) {
			query += " AND c.id = '" + produtoId + "'";
		}
		Query q = criarQuery(query);
		long count = (Long) q.getResultList().get(0);
		return count > 0;
	}
}
