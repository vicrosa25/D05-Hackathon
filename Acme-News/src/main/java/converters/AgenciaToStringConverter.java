package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Agencia;


@Component
@Transactional
public class AgenciaToStringConverter implements Converter<Agencia, String> {

	@Override
	public String convert(Agencia agencia) {
		String result;
		if(agencia == null)
			result = null;
		else
			result = String.valueOf(agencia.getId());
		return result;
	}

}
