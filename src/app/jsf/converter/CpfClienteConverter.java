package app.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class CpfClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	/**
	 * Converte o CPF do cliente para uma representação onde apenas os 3 primeiros dígitos são exibidos
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String cpf = (String) value;
		
		String inicio = cpf.substring(0, 3);
		String secreto = "***";
		
		return inicio + "." + secreto + "." + secreto + "- **";
	}

}
