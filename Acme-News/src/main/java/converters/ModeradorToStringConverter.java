package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Moderador;


@Component
@Transactional
public class ModeradorToStringConverter implements Converter<Moderador, String> {

	@Override
	public String convert(Moderador moderador) {
		String result;
		if(moderador == null)
			result = null;
		else
			result = String.valueOf(moderador.getId());
		return result;

	}

}