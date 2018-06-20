package app.jsf.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import app.jsf.model.Fornecedor;
import app.jsf.service.FornecedorService;
import app.jsf.service.ValidationException;

@Named("fornecedorBean")
@RequestScoped
public class FornecedorBean extends AbstractBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorService fornecedorService;
	
	private Fornecedor fornecedor;

	private List<Fornecedor> fornecedores;
	
	/**
	 * Salva ou atualiza o fornecedor
	 * @return
	 * @throws Exception
	 */
	public String gravar() throws Exception{
		try {
			//A operação a ser realizada depende da existência ou não do Id
			if(fornecedor.getId() == null) {
				fornecedorService.salvar(fornecedor);
			}else {
				fornecedorService.atualizar(fornecedor);
			}
			
			fornecedor = null;
			return redirect("fornecedor");
		}catch(ValidationException e) {
			// Ocorreu um erro de validação de negócio
			addMessageToRequest(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Carrega um fornecedor para que ele seja alterado
	 * @param fornecedorId
	 * @return
	 * @throws Exception
	 */
	public String alterar(Integer fornecedorId) throws Exception {
		fornecedor = fornecedorService.carregar(fornecedorId);
		fornecedores = null;
		return null;
	}
	
	/**
	 * Exclui um fornecedor
	 * @param fornecedorId
	 * @return
	 * @throws Exception
	 */
	public String excluir(Integer fornecedorId) throws Exception{
		fornecedorService.excluir(fornecedorId);
		fornecedores = null;
		return redirect("fornecedor");
	}

	/**
	 * Limpa formulário
	 * @return
	 * @throws Exception
	 */
	public String limpar() throws Exception{
		fornecedor = null;
		return null;
	}
	
	public Fornecedor getFornecedor() {
		if(fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	/**
	 * Obtem lista de fornecedores cadastrados
	 * @return
	 * @throws Exception
	 */
	public List<Fornecedor> getFornecedores() throws Exception{
		if(fornecedores == null) {
			fornecedores = fornecedorService.listarFornecedores();
		}
		return fornecedores;
	}
}
