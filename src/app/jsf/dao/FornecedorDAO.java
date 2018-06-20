package app.jsf.dao;

import java.util.List;

import javax.persistence.Query;

import app.jsf.model.Fornecedor;

public class FornecedorDAO extends DAO{
	private static final long serialVersionUID = 1L;

	/**
	 * Obtem a lista dos fornecedors cadastrados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarFornecedores(){
		Query q = criarQuery("SELECT f FROM FORNECEDOR f ORDER BY f.nome");
		return q.getResultList();
	}
	/**
	 * Verifica se já existe cpf cadastrado
	 * @param cpf
	 * @param fornecedorId
	 * @return
	 */
	public boolean existeFornecedor(String cpf, Integer fornecedorId) {
		String query = "SELECT COUNT(f) FROM FORNECEDOR f WHERE f.cpf = '" + cpf + "'";
		if(fornecedorId != null) {
			query += " AND f.id = '" + fornecedorId + "'";
		}
		Query q = criarQuery(query);
		long count = (Long) q.getResultList().get(0);
		return count > 0;
	}
}
