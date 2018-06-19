package app.jsf.model;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity
@Table(name="PEDIDO")
public class Pedido implements Serializable {

	/**
	 * Tipo de venda
	 * */
	public enum Tipo{
		NORMAL, DEVOLUCAO
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipoVenda;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany
	private List<ItemPedido> itens;
	
	public Double totalPedido() {
		double soma = 0;
		for(int i = 0; i < itens.size(); i++) {
			soma = soma + 0;
		}
		return soma;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	  
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public Tipo getTipoVenda() {
		return this.tipoVenda;
	}

	public void setTipoVenda(Tipo tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
}
