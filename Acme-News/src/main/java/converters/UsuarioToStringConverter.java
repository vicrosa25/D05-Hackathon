package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Usuario;


@Component
@Transactional
public class UsuarioToStringConverter implements Converter<Usuario, String> {

	@Override
	public String convert(Usuario user) {
		String result;
		if(user == null)
			result = null;
		else
			result = String.valueOf(user.getId());
		return result;

	}

}
