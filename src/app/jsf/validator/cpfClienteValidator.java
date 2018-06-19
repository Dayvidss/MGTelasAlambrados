package app.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import app.jsf.util.StringUtils;

@FacesValidator("validator.cpf")
public class cpfClienteValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent componet, Object value) throws ValidatorException {
		String cpf = (String) value;

		if(cpf == null) {
			return;
		}

		cpf = cpf.trim();

		//Verifica se o CPF � composto de 11 n�meros
		if(cpf.length() != 11) {
			FacesMessage msg = new FacesMessage("O n�mero do CPF deve possuir 11 d�gitos num�ricos.");
			throw new ValidatorException(msg);
		}

		// Verifica se todos os caracteres s�o num�ricos
		if (!StringUtils.isNumeric(cpf)) {
			FacesMessage msg = new FacesMessage("O n�mero digitado n�o � um n�mero v�lido.");
			throw new ValidatorException(msg);
		}
	}
}
