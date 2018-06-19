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

		//Verifica se o CPF é composto de 11 números
		if(cpf.length() != 11) {
			FacesMessage msg = new FacesMessage("O número do CPF deve possuir 11 dígitos numéricos.");
			throw new ValidatorException(msg);
		}

		// Verifica se todos os caracteres são numéricos
		if (!StringUtils.isNumeric(cpf)) {
			FacesMessage msg = new FacesMessage("O número digitado não é um número válido.");
			throw new ValidatorException(msg);
		}
	}
}
