package app.jsf.service;
/**
 * Super classe de todos os serviços
 * @author dssin
 *
 */

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

@RequestScoped
public abstract class Service implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Objeto que representa a transação
	 */
	@Resource
	private UserTransaction ut;

	/**
	 * Inicia uma nova transação
	 */
	protected void beginTransaction() {
		try {
			if(ut.getStatus() != Status.STATUS_ACTIVE) {
				ut.begin();
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Faz o commit da transação ativa
	 */
	protected void commitTransaction() {
		try {
			if(ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.commit();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Faz o rollback da transação ativa
	 */
	protected void rollbackTransaciotn() {
		try {
			if(ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
