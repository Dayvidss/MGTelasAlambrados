package app.jsf.dao;

import java.util.List;

import javax.persistence.Query;

import app.jsf.model.Cliente;

public class ClienteDAO extends DAO{
	private static final long serialVersionUID = 1L;

	/**
	 * Obtem a lista dos clientes cadastrados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes(){
		Query q = criarQuery("SELECT c FROM CLIENTE c ORDER BY c.nome");
		return q.getResultList();
	}
	/**
	 * Verifica se já existe cpf cadastrado
	 * @param cpf
	 * @param clienteId
	 * @return
	 */
	public boolean existeCliente(String cpf, Integer clienteId) {
		String query = "SELECT COUNT(c) FROM CLIENTE c WHERE c.cpf = '" + cpf + "'";
		if(clienteId != null) {
			query += " AND c.id = '" + clienteId + "'";
		}
		Query q = criarQuery(query);
		long count = (Long) q.getResultList().get(0);
		return count > 0;
	}
}
